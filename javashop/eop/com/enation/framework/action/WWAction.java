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
package com.enation.framework.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.enation.framework.database.Page;
import com.opensymphony.xwork2.ActionSupport;

public class WWAction extends ActionSupport implements SessionAware {
	protected final Logger logger = Logger.getLogger(getClass());
	protected Page webpage;

	protected Map session = null;

	protected List msgs = new ArrayList();

	protected Map urls = new HashMap();

	protected static final String MESSAGE = "message";

	protected static final String JSON_MESSAGE = "json_message";

	

	protected String script = "";

	protected String json;
 

	protected int page;

	protected int pageSize;

	// 页面id,加载页面资源所用
	protected String pageId;


 

	public String list_ajax() {
	
		return "list";
	}

	public String add_input() {

		return "add";
	}

	public String edit_input() {

		return "edit";
	}

	public int getPageSize() {
		return 15;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		page = page < 1 ? 1 : page;
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

 

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 直接输出.
	 * 
	 * @param contentType
	 *            内容的类型.html,text,xml的值见后，json为"text/x-json;charset=UTF-8"
	 */
	protected void render(String text, String contentType) {
		try {
			HttpServletResponse response = getResponse();
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 直接输出纯字符串.
	 */
	protected void renderText(String text) {
		render(text, "text/plain;charset=UTF-8");
	}

	/**
	 * 直接输出纯HTML.
	 */
	protected void renderHtml(String text) {
		render(text, "text/html;charset=UTF-8");
	}

	/**
	 * 直接输出纯XML.
	 */
	protected void renderXML(String text) {
		render(text, "text/xml;charset=UTF-8");
	}


	public List getMsgs() {
		return msgs;
	}

	public void setMsgs(List msgs) {
		this.msgs = msgs;
	}

	public Map getUrls() {
		return urls;
	}

	public void setUrls(Map urls) {
		this.urls = urls;
	}

	public Page getWebpage() {
		return webpage;
	}

	public void setWebpage(Page webpage) {
		this.webpage = webpage;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
