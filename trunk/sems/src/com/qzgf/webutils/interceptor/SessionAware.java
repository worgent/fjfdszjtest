package com.qzgf.webutils.interceptor;

import com.qzgf.utils.servlet.UserSession;

 
public interface SessionAware {
	public void setUserSession(UserSession userSession);

}
