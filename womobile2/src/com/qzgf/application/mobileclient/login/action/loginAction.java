package com.qzgf.application.mobileclient.login.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.login.domain.LoginFacade;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.utils.Util;
/**
 * 用户登录
 * 
 */
@SuppressWarnings("serial")
public class loginAction extends BaseAction {
	Log log = LogFactory.getLog(loginAction.class);
	// 实现类方法
	private LoginFacade loginFacade;
	private String username;
	private String passwd;
	private String authCode;
    private String jsonstr;
	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			return "index";
		}
	}	
	
	public String index() throws Exception {
	  	HttpServletRequest request=ServletActionContext.getRequest(); 
    	this.username=request.getParameter("username");
    	this.passwd=request.getParameter("pwd").toUpperCase();	
     	UserInfo ui = this.getLoginFacade().findUserInfoByName(this.username);
    	if (ui == null) {
    		jsonstr="FAIL";
		}else if (!this.getPasswd().equals(ui.getRePasswd())){
			jsonstr="FAIL";
		}else{
    		jsonstr=ui.getId().toString();
    	}   	
		return "json";
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

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
