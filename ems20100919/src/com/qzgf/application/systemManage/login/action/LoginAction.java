package com.qzgf.application.systemManage.login.action;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.qzgf.application.BaseAction;  
import com.qzgf.application.systemManage.login.domain.LoginFacade;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.comm.Constant;
import com.qzgf.utils.Util;
import com.qzgf.utils.servlet.UserSession;

/**
 * 登录
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends BaseAction implements SessionAware  {
	
	@SuppressWarnings("unchecked")
	private Map session;
	private LoginFacade loginFacade;
	private String username;
	private String passwd;
	private String authCode;
	
	public String execute() {
		if (this.getAction().equalsIgnoreCase("index")) {
			this.setAction("login");
			return "index";
		}

		if (this.getAction().equalsIgnoreCase("login")) {
			return this.login();
		}

		return "index";
	}
	
	public String input(){
		this.setAction("login");
		return INPUT;
	}

	@SuppressWarnings("unchecked")
	public String login(){
		if (StringUtils.isBlank(this.username) || StringUtils.isBlank(this.passwd)) {
			this.addActionError("用户名或密码为空");
			return INPUT;
		}
		
		UserInfo ui = this.getLoginFacade().findUserInfoByName(this.username);
		
		if (ui == null) {
			this.addActionError("用户不存在");
			return INPUT;
		}
		
		//把明文密码执行MD5加密
		if (!Util.hash(this.getPasswd()).equals(ui.getRePasswd())) { // 密码错误
			this.addActionError("密码不正确");
			return INPUT;
		}
		
		String cauthCode = this.getSessionAuthCode();

        if (StringUtils.isBlank(cauthCode) || !cauthCode.equals(this.getAuthCode())) {
			this.addActionError("验证码输入有误");
			this.setAuthCode("");
			return INPUT;
		}
		
		UserSession us = this.getLoginFacade().getUserSession(ui); //获得用户UserSession信息,里面有加载用户权限列表
		this.getSession().put(Constant.USER_SESSION_KEY, us);  
		return SUCCESS;
	}
	
	public LoginFacade getLoginFacade() {
		return loginFacade;
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@SuppressWarnings("unchecked")
	public Map getSession() {
		return session;
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}
	
	private String getSessionAuthCode() {
        return (String)this.getSession().get("verifycode");
    }

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
	
}
