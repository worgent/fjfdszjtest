/**
 * 
 */
package com.apricot.app.eating;

import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.PlugIn;
import com.apricot.eating.engine.ResponseData;
/**
 * @author Administrator
 */
public class LoginPulgIn implements PlugIn {
	/**
	 * 
	 */
	public LoginPulgIn() {
		// TODO Auto-generated constructor stub
	}

	public void invoke(ServletContext servlet, HttpServletRequest request, HttpServletResponse response, Object obj) {
		// TODO Auto-generated method stub
		request.getSession().setAttribute(LoginData.LOGIN_SESSION_KEY, ((ResponseData) obj).getData());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.apricot.eating.engine.PlugIn#run(javax.servlet.ServletContext,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      com.apricot.eating.engine.DyncParameterMap)
	 */
	public void init(ServletContext servlet, HttpServletRequest request, HttpServletResponse response, DyncParameterMap map) {
		// TODO Auto-generated method stub
		LoginData ld = (LoginData) request.getSession().getAttribute(LoginData.LOGIN_SESSION_KEY);
		if (ld == null)
			ld = new LoginData();
		
		
		map.putAll(ld.toMap());
		map.setMenus(ld.getMenus());
		map.set("state_date", new Date());
		map.set("modify_date", new Date());
	}
}
