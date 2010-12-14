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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SessionDTO;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.sdk.user.UserContext;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.eop.utils.ValidCodeServlet;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.StringUtil;
/**
 * 用户注册挂件
 * @author kingapex
 *2010-3-22上午11:42:38
 */
public class RegisterWidget extends AbstractWidget {
 

	private IUserManager userManager;
 
 
	private  HttpServletRequest request;
	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		  request  = ThreadContextHolder.getHttpRequest();
 
		String action = request.getParameter("action");
		String productid= request.getParameter("productid");
		//this.setPageFolder("/widgets/user");
		//注册
		if( action==null || "".equals(action)){
			this.putData("productid", productid);
			this.setPageName("reg");
		}
		
 		if("reg".equals(action)){
			this.reg(params);
			if(productid!=null && !productid.equals("")){
				this.putData("url", "install.html?action=confirm&productid="+productid);
			}else{
				this.putData("url",null);
			}
			
		} 

	}
	
	
	private void reg(Map<String, String> params){
		try {
			String licence  = request.getParameter("licence") ;
			String username =  request.getParameter ("username");
			String password =  request.getParameter ("password");
			String verifyCode = request.getParameter("verifyCode");
			if(validcode(verifyCode)==0) throw new RuntimeException("验证码输入错误");
			
			Integer usertype = Integer.valueOf( request.getParameter("usertype"));
			String companyName = request.getParameter("company_name");
			String email = request.getParameter("email");
			String linkman = request.getParameter("linkman");
			String tel = request.getParameter("tel");
			String mobile = request.getParameter("mobile");
			String qq = request.getParameter("qq");
			
			if(StringUtil.isEmpty(licence)){ throw new  IllegalArgumentException("同意服务条款才能注册!") ;}
			if(StringUtil.isEmpty(username)){ throw new  IllegalArgumentException("username is null") ;}
			if(StringUtil.isEmpty(password)){ throw new  IllegalArgumentException("password is null") ;}
			if(StringUtil.isEmpty(email)){ throw new  IllegalArgumentException("email is null") ;}
			if(StringUtil.isEmpty(companyName)){ throw new  IllegalArgumentException("companyName is null") ;}
			
			
			EopUser eopUser = new EopUser();
			eopUser.setUsertype(usertype);
			eopUser.setUsername(companyName);
			eopUser.setLinkman(linkman);
			eopUser.setEmail(email);
		 
			
			EopUserAdmin userAdmin = new EopUserAdmin();
			userAdmin.setUsername(username);
			userAdmin.setPassword(password);
			userAdmin.setEmail(email);
			userAdmin.setMobile(mobile);
			userAdmin.setTel(tel);
			userAdmin.setQq(qq);
			
			Integer userid = userManager.createUser(eopUser,userAdmin);
			eopUser.setId(userid);			

			SessionDTO sessionDTO = userManager.login_bysystem(username, password);
//			WebSessionContext<UserContext> sessonContext = ThreadContextHolder
//			.getSessionContext();	
//			UserContext userContext = new UserContext(sessionDTO.getUserid(),
//					sessionDTO.getDefaultsiteid(), sessionDTO.getManagerid());
//			sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);
//			ThreadContextHolder.getSessionContext().setAttribute("userAdmin", userAdmin);
			this.setPageName("regsuccess");
		} catch (RuntimeException e) {
			 this.setPageName("message");
			 this.putData("message", e.getMessage());
			if (this.logger.isDebugEnabled()){
				this.logger.debug(e.getMessage(), e);
			}
		}		
	}

	/**
	 * 校验验证码
	 * @param validcode
	 * @return 1成功  0失败
	 */
	private int validcode(String validcode){
		if(validcode==null   ){
			return 0;
		}
		
		String code  = (String)ThreadContextHolder.getSessionContext().getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"userreg");
		if(code==null){
			return 0 ;
		}else{
			if(!code.equals(validcode)){
				return 0;
			}
		}
		
		return 1;
	}

	
	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

 
	
 

}
