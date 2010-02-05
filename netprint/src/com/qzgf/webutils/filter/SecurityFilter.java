package com.qzgf.webutils.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.Def;
import com.qzgf.application.systemManage.manager.dto.UserInfo;
import com.qzgf.context.ContextHolder;
import com.qzgf.context.ContextImpl;
import com.qzgf.security.AuthoritiesPages;


public class SecurityFilter implements Filter {
	private String DEFAULT_SESSION_KEY = "UserInfo";
	private Log log = LogFactory.getLog(SecurityFilter.class);
	//无权限访问时，转向的页面
	private String errorPage;
	//无会话时转向的页面
	private String noSessionPage;
	//用于判断会话是否为空的关键值
	private String sessionKey = DEFAULT_SESSION_KEY;
	
	private AuthoritiesPages authoritiesPages ;
	
	
	public SecurityFilter(){
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		long before = System.currentTimeMillis();
		
		
		String url = "";
		HttpSession session = null;
		if (request instanceof HttpServletRequest) {
			// 获取请求页面的URL地址
			url = ((HttpServletRequest) request).getRequestURI();
			//不需要权限和会话判断的url
			if(authoritiesPages.getNotSessionPages().get(url)!=null){
				filterChain.doFilter(request,response);
				return ;
			}
			// 取得会话 280620046
			session = ((HttpServletRequest) request).getSession();
			
			if(session!=null){
				if(ContextHolder.getContext()!=null){
					ContextHolder.setContext(null);
				}
				UserInfo userInfo = (UserInfo)session.getAttribute(Def.LOGIN_SESSION_NAME);
				if(userInfo!=null){
					ContextImpl context = new ContextImpl();
					context.setUserInfo(userInfo);
					ContextHolder.setContext(context);
					//session.setAttribute(Def.CONTEXT_SESSION_NAME,context);
				}else{
					if(log.isDebugEnabled()){
						log.debug("session LOGIN_SESSION_NAME is null");
					}
				}
				
			}
		}
		
		if (session == null) {
			request.getRequestDispatcher(noSessionPage).forward(request,response);
		}else if(null != sessionKey && null == session.getAttribute(sessionKey)){
			request.getRequestDispatcher(noSessionPage).forward(request,response);
		}else{
			String role=(String)authoritiesPages.getAuthoritiesPages().get(url);
			if(role!=null){
				List roles = (List)session.getAttribute("loginRoles");
				boolean go = false;
				for(int i=0;i<roles.size();i++){
					if(role.indexOf(roles.get(i)+",") != -1){
						go = true;
						break;
					}
				}
				if(go){
					filterChain.doFilter(request,response);
				}else{
					request.getRequestDispatcher(errorPage).forward(request,response);
				}
			}else{
				filterChain.doFilter(request,response);
			}
		}
		
		if(request.getAttribute("javax.servlet.jsp.jspException")!=null){
			//int i=0;
			//i = 2/i;
			Exception ex = (Exception)request.getAttribute("javax.servlet.jsp.jspException");
			if(ex.getCause()!=null){
				String msg = ex.getCause().getMessage();
				if(msg.indexOf("403")!=-1){
					//request.getRequestDispatcher("/error.jsp").forward(request,response);
					throw new IOException(msg);
				}
			}
		}
		if(log.isDebugEnabled()){
			log.debug(url+":"+(before-System.currentTimeMillis()));
		}
		
		
		
	}

	public void destroy() {

	}
	public void doInit(){
	
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getNoSessionPage() {
		return noSessionPage;
	}

	public void setNoSessionPage(String noSessionPage) {
		this.noSessionPage = noSessionPage;
	}

	public AuthoritiesPages getAuthoritiesPages() {
		return authoritiesPages;
	}

	public void setAuthoritiesPages(AuthoritiesPages authoritiesPages) {
		this.authoritiesPages = authoritiesPages;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	
}
