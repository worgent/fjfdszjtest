package com.qzgf.application.systemManage.login.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.user.action.UserAction;
import com.qzgf.application.systemManage.login.domain.LoginFacade;


import com.qzgf.context.PageList;



@SuppressWarnings("serial")
public class AdminLogout extends BaseAction{
	Log log = LogFactory.getLog(UserAction.class);
	private LoginFacade loginFacade;
	
	@SuppressWarnings("unchecked")
	public  HashMap userInfo;
	
	
	private PageList userOnlyList;
	
	@SuppressWarnings("unchecked")
	public String execute() {
		
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return "index";
		}
		

		//ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME,search.get("pusername"));
		//userInfo.setUsername((String)search.get("pusername"));
		//System.out.println(userInfo.getUsername());
		
		
		//2010-4-22
		/*System.out.println(search.get("pusername"));
        userInfo =(HashMap) loginFacade.checkLogin(search).get(0);
        ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME,userInfo);*/
       //2010-4-22
	
	/*	if(null != userInfo){
			ActionContext.getContext().getSession().put(Def.ROLE_LIST_SESSION, userInfo.getRoles());
			
			//userInfo.setAgent(ServletActionContext.getRequest().getHeader("User-Agent"));
    		//userInfo.setIp(ServletActionContext.getRequest().getRemoteAddr());

    		ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME,userInfo.getUsername());
    		ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_USERID,userInfo.getUserid());
    		
    		//System.out.println(userInfo.getUserid());
    		
    		return SUCCESS;
		}
		
		
		*/
		
		
		

		/*userInfo = loginFacade.checkLogin(userInfo);
		if (null != userInfo) {
			ActionContext.getContext().getSession().put(Def.ROLE_LIST_SESSION,
					userInfo.getRoles());
			
			//登记操作系统信息和IP地址
			userInfo.setAgent("aaaaaa");
			userInfo.setIp("127.0.0.11");

			ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME,
					userInfo);
			return SUCCESS;
		}
*/

	}
	
	 public String backLogin() { //登录后台
		 
		  return SUCCESS;
	 }
	
	
    public String userLogin() {
		
		System.out.println(search.get("pusername"));
        userInfo =(HashMap) loginFacade.checkLogin(search).get(0);
        ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME,userInfo);
		
		return "userLogin";
	}
	
	
	
	
	
	
	public LoginFacade getLoginFacade() {
		return loginFacade;
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	

	public PageList getUserOnlyList() {
		return userOnlyList;
	}

	public void setUserOnlyList(PageList userOnlyList) {
		this.userOnlyList = userOnlyList;
	}

	
}
