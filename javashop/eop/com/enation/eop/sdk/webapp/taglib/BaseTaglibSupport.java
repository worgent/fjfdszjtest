/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.eop.sdk.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class BaseTaglibSupport extends BodyTagSupport {
	private static final long serialVersionUID = -8939393391060656141L;
	
	/**
	 * 获取ServletContext
	 * @return
	 */
	protected ServletContext getServletContext() {
		ServletContext servletContext = this.pageContext.getServletContext();
	
		return servletContext;
	}
	
	/**
	 * 获取Request
	 * @return
	 */
	public HttpServletRequest getRequest(){
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		
		return request;
	}
	
	public HttpServletResponse getResponse(){
		HttpServletResponse response = (HttpServletResponse)this.pageContext.getResponse();
		
		return response;
	}
	
	/**
	 * 获取JspWriter
	 * @return
	 */
	protected JspWriter getWriter() {
		return this.pageContext.getOut();
	}

	/**
	 * 向客户端输出字串
	 * @param s
	 */
	protected void print(String s) {
		try {
		 
			this.getWriter().write(s);
			this.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向客户端输出字串并且换行
	 * @param s
	 */
	protected void println(String s) {
		print(s  +"\n");
	}
	
	protected String getContextPath(){
		return this.getRequest().getContextPath();
		 
	}
}