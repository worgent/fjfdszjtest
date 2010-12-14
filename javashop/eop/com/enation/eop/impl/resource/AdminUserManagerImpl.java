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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.IAdminUserManager;
import com.enation.eop.core.resource.model.EopSiteAdmin;
import com.enation.eop.core.resource.model.EopSiteAdminView;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.core.resource.model.SiteManagerView;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;

public class AdminUserManagerImpl implements IAdminUserManager {
	
	private IDaoSupport daoSupport;
	
	
	public Integer add(EopUserAdmin eopUserAdmin) {
		 
		this.daoSupport.insert("eop_useradmin", eopUserAdmin);
		return  this.daoSupport.getLastId("eop_useradmin");
	}
	
	
	
	public void changePassword( Integer managerid,
			String password) {
		String sql ="update eop_useradmin set password=? where id=?";
		 this.daoSupport.execute(sql, StringUtil.md5(password),managerid);
	}

	
	public void changePassword(  Integer managerid,
			String password, String oldpassword) {
	 
		checkCurrentPassword(managerid, StringUtil.md5(oldpassword));
		changePassword(managerid,password);
	 
	}
	
	private void checkCurrentPassword(Integer managerid, String password) {
		String sql = "select * from eop_useradmin where id = ?";
		EopUserAdmin admin = null;
		try {
			admin = (EopUserAdmin) daoSupport.queryForObject(sql, EopUserAdmin.class, managerid);
		} catch (RuntimeException e) {
			throw new RuntimeException("用户不存在");
		}

		if (!admin.getPassword().equals(password)) {
			throw new RuntimeException("密码不匹配");
		}
	}


	
	public List<SiteManagerView> getViewList( Integer managerid) {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		String sql ="select a.id, a.sitename,a.userid, b.siteid from eop_site a left join (select siteid from eop_siteadmin where managerid = ?) b on b.siteid = a.id where a.deleteflag = 0 and a.userid = ?";
		
		return  this.daoSupport.queryForList(sql,SiteManagerView.class, managerid, userid);
	}


	
	public void putView( Integer managerid, Integer[] siteList) {

		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		String sql  ="delete from eop_siteadmin where managerid = ?";
		this.daoSupport.execute(sql,managerid );
		for(Integer siteid:siteList){
			EopSiteAdmin siteAdmin = new EopSiteAdmin();
			siteAdmin.setManagerid(managerid);
			siteAdmin.setSiteid(siteid);
			siteAdmin.setUserid(userid);
			daoSupport.insert("eop_siteadmin", siteAdmin);
		}
	 
	}
	
	
	public Page list(int pageNo, int pageSize,  String order, String search) {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		List<EopSiteAdminView> listSiteAdmin =	 
			daoSupport.queryForList(
					  "select a.*,b.sitename from eop_siteadmin a left join eop_site b on b.id = a.siteid where a.userid = ?",
						EopSiteAdminView.class, userid);
		
		Page page = getResourceList(pageNo, pageSize, userid, order, search);
		List<Map> listadmin = (List<Map>) (page.getResult());

		for (Map admin : listadmin) {
			List<EopSiteAdminView> siteAdminList = new ArrayList<EopSiteAdminView>();

			for (EopSiteAdminView siteAdmin : listSiteAdmin) {
				if (admin.get("id").toString().equals(
						siteAdmin.getManagerid().toString())) {
					siteAdminList.add(siteAdmin);
				}
			}
			admin.put("eopSiteAdminList", siteAdminList);
		}
		return page;
	}

	
 
	
	
	/**
	 * 分页读取某用户的管理员列表
	 * @param pageNo
	 * @param pageSize
	 * @param userid
	 * @param order
	 * @param search
	 * @return
	 */
	private  Page getResourceList(int pageNo, int pageSize, int userid,
			String order, String search) {
		if (search == null)
			search = "";
		else
			search = " and ((username like '%" + search
					+ "%') or (realname like '%" + search + "%'))";
		if (order == null)
			order = "";
		else
			order = " order by " + order.replace(":", " ");
		return daoSupport.queryForPage( "select * from eop_useradmin where userid = " + userid + search
						+ order, pageNo, pageSize, null);
	}
	
	
	
	public void changeDefaultSite(Integer managerid, Integer siteid) {
		String sql ="update eop_useradmin set defaultsiteid=? where id=?";
		this.daoSupport.execute(sql, siteid,managerid);
	}


	public IDaoSupport<EopUserAdmin> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopUserAdmin> daoSupport) {
		this.daoSupport = daoSupport;
	}


	
	public EopUserAdmin login(String username, String password) {
	 
		String sql = "select * from eop_useradmin where username = ? or email = ?";
		List<EopUserAdmin> adminList = daoSupport.queryForList(sql, EopUserAdmin.class,
				username, username);

		if (adminList == null || adminList.isEmpty()) {
			throw new EopException("用户不存在");
		}

		EopUserAdmin admin = adminList.get(0);


		if (!admin.getPassword().equals( StringUtil.md5( password))) {
			throw new EopException("密码不匹配");
		}

		return admin;
	}


	
	public EopUserAdmin get(Integer id) {
		
		return (EopUserAdmin)this.daoSupport.queryForObject("select * from eop_useradmin where id = ?", EopUserAdmin.class,
				id);
	}


	
	public void edit(EopUserAdmin eopUserAdmin) {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		eopUserAdmin.setUserid(userid);
		this.daoSupport.update("eop_useradmin", eopUserAdmin, " id = "
				+ eopUserAdmin.getId());

	}


	
	public void delete(Integer id) {
		 this.daoSupport.execute("delete from eop_useradmin where id=?", id);
		
	}

}
