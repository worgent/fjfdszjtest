package com.apricot.eating.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apricot.app.eating.LoginData;
import com.apricot.eating.engine.BO;

public class SessionFilter implements Filter {

	public void destroy() {
		

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		LoginData ld = (LoginData) httpRequest.getSession().getAttribute(LoginData.LOGIN_SESSION_KEY);
		if (ld==null || !ld.isLogin()){
			if(!"/apricot_ed/apricot/login.do".equals(httpRequest.getRequestURI())){
//				return getMessage("bookbill_001", null);
			}
			System.out.println("hah");
		}else{
			
		}
			
		fc.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		

	}

}
