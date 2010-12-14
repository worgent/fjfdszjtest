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
package com.enation.eop.sdk.webapp.taglib.html;

import com.enation.eop.sdk.webapp.bean.Grid;
import com.enation.eop.sdk.webapp.taglib.HtmlTaglib;
import com.enation.eop.sdk.webapp.taglib.html.support.PageHtmlBuilder;
import com.enation.framework.database.Page;

public class GridTaglib extends HtmlTaglib {

	private String from;
	private String pager; //是否显示分页
	private String panel;
	
	
	protected String postStart() {
		String str = "";
			
		str += "<table>";
		return str;
	}
	
	
	protected String postEnd() {
		StringBuffer str = new StringBuffer();
		str.append("</table>");
		if(pager==null || pager.equals("yes")){
			str.append(this.buildPageHtml());
		}
		return str.toString();
	}

	private String getUrl(){
		return getRequest().getContextPath();
	}
	
	private String buildQueryString(String except){
		String result = "";
		String [] parameter = {"page","order"};
		for(int i=0;i<parameter.length;i++){
			if(!parameter[i].equals(except)){
				String value = this.getRequest().getParameter(parameter[i]);
				if(value!=null) {
					result += result.equals("") ? "?" : "&";
					result += parameter[i] + "=" + value;
				}
			}
		}
		return result.equals("") ? "?" : result + "&";
	}
	
	private String buildPageHtml(){

		String tempPage = this.getRequest().getParameter("page");
		int pageNo = 1;
		if(tempPage!=null && !tempPage.toString().equals("")){
			pageNo = Integer.valueOf(tempPage.toString());
		}
	
		//String queryString = this.getRequest().getQueryString();

		Object obj = this.pageContext.getAttribute(from);
		
		if(obj==null){
			obj = this.getRequest().getAttribute(from);
			
			if(obj==null){
				return "";
			}
			this.pageContext.setAttribute(from,obj);
		}
		Page page = null;
		if(obj instanceof Page)
			page = (Page)obj;
		else if(obj instanceof Grid)
			page = ((Grid)obj).getWebpage();
		else
			return "";

		int pageSize = page.getPageSize();
		long totalCount = page.getTotalCount();
		
		PageHtmlBuilder pageHtmlBuilder = new PageHtmlBuilder( pageNo, totalCount,  pageSize,  this.getRequest());

		return pageHtmlBuilder.buildPageHtml();
/*
		int pageCount = totalCount % pageSize == 0 ? (int)(totalCount / pageSize) : (int)(totalCount / pageSize + 1);
				
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<div class='dataTables_paginate'>");

		for(int i=1;i<pageCount+1;i++){
			stringBuffer.append("<span><a href=\"" + buildQueryString("page") + "page=" + i + "\">" + i + "</a></span>"); 
		}
		stringBuffer.append("</div>");
		return stringBuffer.toString();
*/
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getPanel() {
		return panel;
	}

	public void setPanel(String panel) {
		this.panel = panel;
	}
}
