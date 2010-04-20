package com.qzgf.application.systemManage.login.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.qzgf.application.BaseAction;
import com.qzgf.comm.Constant;
 
@SuppressWarnings("serial")
public class AdminLogout extends BaseAction implements SessionAware {


	@SuppressWarnings("unchecked")
	private Map session;

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public Map getSession() {
		return session;
	}

	public String execute() {
		this.getSession().remove(Constant.USER_SESSION_KEY);
		return SUCCESS;
	}

}
