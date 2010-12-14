/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.eop.impl.resource;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.IAdminUserManager;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SessionDTO;
import com.enation.eop.core.resource.dto.UserDTO;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.sdk.user.UserContext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.util.StringUtil;

public class UserManagerImpl implements IUserManager {
	private IDaoSupport<EopUser> daoSupport;
	private ISiteManager siteManager;
	private IAdminUserManager adminUserManager;
	protected final Logger logger = Logger.getLogger(getClass());
	
	public void changeDefaultSite(Integer userid, Integer managerid,
			Integer defaultsiteid) {
		UserUtil.validUser(userid);
		String sql  ="update eop_user set defaultsiteid=? where id=?";
		  daoSupport.execute(sql, defaultsiteid,userid);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	
	public Integer createUser(UserDTO userDTO) {
		userDTO.vaild();
		boolean isExists = siteManager.checkInDomain(userDTO.getUser().getUsername() + "."+ EopSetting.APP_DOMAIN_NAME);
		if(isExists){
			throw new RuntimeException("已存在'"+userDTO.getUser().getUsername() + "."+ EopSetting.APP_DOMAIN_NAME+"'二级域名！");
		}
		this.daoSupport.insert("eop_user", userDTO.getUser());
		Integer userid = this.daoSupport.getLastId("eop_user");
		userDTO.setUserId(userid);
		
		this.daoSupport.insert("eop_userdetail", userDTO.getUserDetail());
		this.daoSupport.insert("eop_useradmin", userDTO.getUserAdmin());
		Integer managerid = this.daoSupport.getLastId("eop_useradmin");
		userDTO.getSiteDTO().setManagerid(managerid);
		Integer siteid = this.siteManager.add(userDTO.getSiteDTO());
		userDTO.setSiteid(siteid);
		
		this.adminUserManager.changeDefaultSite(managerid, siteid);
		return userid;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	
	public Integer createUser(EopUser user,EopUserAdmin userAdmin) { 
		
		String username = user.getUsername();
		if(username==null) throw new IllegalArgumentException("username is null! ");
		if(this.checkUserName(username)){
			throw new IllegalArgumentException("username["+username+"] is exists! ");
		}
		
		this.daoSupport.insert("eop_user", user);
		Integer userid = this.daoSupport.getLastId("eop_user");
 
		userAdmin.setUserid(userid);
		userAdmin.setPassword(  StringUtil.md5( userAdmin.getPassword() )) ;
		this.adminUserManager.add(userAdmin);
		return userid;
	}

	
	
	/**
	 * 检测用户名是否存在
	 * @param username
	 * @return 存在返回true，不存在返回false
	 * @author kingapex
	 */
	private boolean checkUserName(String username){
		String sql ="select * from eop_user where username=?";
		List list  = this.daoSupport.queryForList(sql, username);
		if(list== null || list.isEmpty() || list.size()==0) 
			return false;
		else
			return true;
	}
	
	
	public SessionDTO login(String username, String password) {
		if (logger.isDebugEnabled()) {
			logger.debug("user login[usename:"+username+",password:"+password+"]");
		}
		EopUserAdmin admin = adminUserManager.login(username, password);

		EopSite site = EopContext.getContext().getCurrentSite();

		if (logger.isDebugEnabled()) {
			logger.debug("logined user->[userid:" + admin.getUserid()
					+ ",siteid:" + admin.getDefaultsiteid() + "]");
			logger.debug("current site->[userid:" + site.getUserid()
					+ ",siteid:" + site.getId() + "]");
		}
		
		if (site.getUserid().intValue() != admin.getUserid().intValue()) {
			throw new EopException("非本用户");
		}
		
		try{
			this.get(admin.getUserid());
		}catch(ObjectNotFoundException e){
			throw new EopException("该管理员的站点尚未审核");
		}
		
		String sql ="select count(0) from eop_siteadmin where managerid = ? and siteid = ?";
		int count = this.daoSupport.queryForInt(sql, admin.getId(),site.getId());
		//判断该管理员是否可以管理此站点
		if (count<=0){
			throw new EopException("该管理员不具备管理此站点的权限");
		}

 

		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setUserid(admin.getUserid());
		sessionDTO.setDefaultsiteid(admin.getDefaultsiteid());
		sessionDTO.setManagerid(admin.getId());
		return sessionDTO;
	}
 
 

	
	public EopUser get(Integer userid) {
		String sql ="select * from eop_user where deleteflag = 0 and id = ?";
		return this.daoSupport.queryForObject(sql, EopUser.class, userid);
	}
	
	
	public SessionDTO login_bysystem(String username, String password) {
		if (logger.isDebugEnabled()) {
			logger.debug("user login[usename:"+username+",password:"+password+"]");
		}
		EopUserAdmin admin =  adminUserManager.login(username, password);
		admin.setPassword(password);
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setUserid(admin.getUserid());
		sessionDTO.setManagerid(admin.getId());
		sessionDTO.setDefaultsiteid(admin.getDefaultsiteid());
		
		WebSessionContext<UserContext> sessonContext = ThreadContextHolder
		.getSessionContext();		
		UserContext userContext = new UserContext(sessionDTO.getUserid(),
				sessionDTO.getDefaultsiteid(), sessionDTO.getManagerid());
		sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);
		//登录成功后记录当前管理员，以便生成登录加密字串
		ThreadContextHolder.getSessionContext().setAttribute("userAdmin", admin);
		return sessionDTO;
	}

	

	public IDaoSupport<EopUser> getDaoSupport() {
		return daoSupport;
	}



	public void setDaoSupport(IDaoSupport<EopUser> daoSupport) {
		this.daoSupport = daoSupport;
	}



	public ISiteManager getSiteManager() {
		return siteManager;
	}



	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}



	public IAdminUserManager getAdminUserManager() {
		return adminUserManager;
	}



	public void setAdminUserManager(IAdminUserManager adminUserManager) {
		this.adminUserManager = adminUserManager;
	}


	
	public void edit(EopUser user) {
		this.daoSupport.update("eop_user", user, "id = "+user.getId());
	}


	
	public void logout() {
		
		WebSessionContext<UserContext> sessonContext = ThreadContextHolder.getSessionContext();		
		sessonContext.removeAttribute(UserContext.CONTEXT_KEY);
		
		//登录成功后记录当前管理员，以便生成登录加密字串
		ThreadContextHolder.getSessionContext().removeAttribute("userAdmin");	
		
	}





	
	
}
