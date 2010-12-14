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
package com.enation.app.saler.widget.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SessionDTO;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.EncryptionUtil;

/**
 * 登陆挂件
 * @author kingapex
 *2010-3-22下午05:11:58
 */
public class LoginWidget extends AbstractWidget {
	private  HttpServletRequest request;
	private IUserManager userManager;
	private ISiteManager siteManager;
 
	
	protected void config(Map<String, String> params) {

	}
	
	

	
	protected void execute(Map<String, String> params) {
		 request  = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String productid= request.getParameter("productid");
		this.putData("productid", productid);
		WebSessionContext  sessonContext = ThreadContextHolder
		.getSessionContext();		
		//this.setPageFolder("/widgets/user");
		if("login".equals(action)){
			String json ="";
			try{
				 
				SessionDTO sdto = userManager.login_bysystem(username, password);
//		
//				UserContext userContext = new UserContext(sdto.getUserid(),
//						sdto.getDefaultsiteid(), sdto.getManagerid());
//				sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);
//				
//				EopUser eopUser = new EopUser();
//				eopUser.setUsername(username);
//				eopUser.setPassword(password);
//				eopUser.setId(sdto.getUserid());
//				eopUser.setDefaultsiteid(sdto.getDefaultsiteid());
//				
//				sessonContext.setAttribute("EopUser", eopUser);
//				
				if(sdto.getDefaultsiteid()==null){
					json="{result:1}";
				}else{
					String url = this.createUrl(sdto.getUserid(), sdto.getDefaultsiteid(), username, password);
					
				    if(url!=null ){
				    	json="{result:0,url:'"+url+"'}";
				    }else{
				    	json="{result:1}";
				    }
					
				}
				
			}catch(EopException e){
				json="{result:2,message:'"+e.getMessage()+"'}";
			}
			this.putData("json", json);
			this.setPageName("login_json");
		}else{
			IUserService userService = UserServiceFactory.getUserService();
			if(userService.isUserLoggedIn()){
				EopUserAdmin user =(EopUserAdmin)sessonContext.getAttribute("userAdmin");
				if(user!=null){
					username= user.getUsername();
					password = user.getPassword();
					this.putData("isLogin", Boolean.TRUE);
					if(userService.getCurrentSiteId()!=null){
						this.putData("url", createUrl(userService.getCurrentUserId(),userService.getCurrentSiteId(),username,password));
						this.putData("haveSite", Boolean.TRUE);
					}else{
						this.putData("haveSite", Boolean.FALSE);
					}
				}else{
					this.putData("isLogin", Boolean.FALSE);
				}
			}else{
				this.putData("isLogin", Boolean.FALSE);
				
			}
			this.setPageName("login");
		}
	}
	
	private String createUrl(Integer userid,Integer siteid,String username,String password){
		  List<EopSiteDomain> list = this.siteManager.listDomain(userid, siteid);
		    if(list!=null && !list.isEmpty() ){
				String str =  username+","+password+","+ new Date().getTime();
				str =EncryptionUtil.authCode(str, "ENCODE");
		    	String domain = list.get(0).getDomain();
				int port  = request.getLocalPort();
				String contextPath = request.getContextPath();
		    	String url ="http://"+domain+":"+port+contextPath+"/admin/login?s="+str+"&type=sys";
		    	return url;
		    }else{
		    	return null;
		    }
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

 
	

}
