package com.qzgf.webutils.interceptor;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.qzgf.comm.Constant;
import com.qzgf.utils.servlet.UserSession;

/**
 * 登录判断 a.有session,b.做为游客登录
 * 
 * @author lsr
 * 
 */
@SuppressWarnings("serial")
public class UserLoginInterceptor extends AbstractInterceptor {

	private static final Log logger = LogFactory
			.getLog(UserLoginInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();

		//HttpServletRequest request = (HttpServletRequest) ac
			//	.get(ServletActionContext.HTTP_REQUEST);
		//HttpServletResponse response = (HttpServletResponse) ac
			//	.get(ServletActionContext.HTTP_RESPONSE);

		ServletContext servletContext = (ServletContext) ac
				.get(ServletActionContext.SERVLET_CONTEXT);
		WebApplicationContext wc = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);

		if (wc == null) {
			logger.error("ApplicationContext could not be found.");
		} else {

			UserSession us = (UserSession) ac.getSession().get(
					Constant.USER_SESSION_KEY);

			if (us == null) {// 用户未登录，做游客登录(前台)
				//
			}
		}

		return invocation.invoke();
	}

}
