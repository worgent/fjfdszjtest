package net.trust.application.systemManage.login.action;

import net.trust.Def;
import net.trust.application.systemManage.login.domain.LoginFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ModelDriven;

public class LoginAction implements Action,ModelDriven {
	private Log log = LogFactory.getLog(LoginAction.class);
	private LoginFacade loginFacade;
	private UserInfo userInfo = new UserInfo();
	
	public String execute() throws Exception {
		userInfo = loginFacade.checkLogin(userInfo);
		
		if(null != userInfo){
			ActionContext.getContext().getSession().put(Def.ROLE_LIST_SESSION, userInfo.getRoles());
			
			userInfo.setAgent(ServletActionContext.getRequest().getHeader("User-Agent"));
    		userInfo.setIp(ServletActionContext.getRequest().getRemoteAddr());

    		ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME,userInfo);
    		return SUCCESS;
		}
		return "loginfail";
	}

	public Object getModel() {
		return userInfo;
	}

	public LoginFacade getLoginFacade() {
		return loginFacade;
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
