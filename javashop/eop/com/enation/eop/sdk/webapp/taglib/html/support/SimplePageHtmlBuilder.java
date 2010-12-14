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
package com.enation.eop.sdk.webapp.taglib.html.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.webapp.taglib.html.IPageHtmlBuilder;


/**
 * 正常的页面跳转的翻页
 * @author apexking
 *
 */
public class SimplePageHtmlBuilder extends PageHtmlBuilder implements IPageHtmlBuilder  {
	
	public SimplePageHtmlBuilder(long _pageNum, long _totalCount, int _pageSize, HttpServletRequest _request) {
		super(_pageNum, _totalCount, _pageSize, _request);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 生成href的字串
	 */
	
	protected String getUrlStr(long page) {
		
		StringBuffer urlStr = new StringBuffer();
		
		if(!url.startsWith("?")){
			url="?" +url;
			
		}
		urlStr = new StringBuffer(url);
		urlStr.append("page=");
		urlStr.append(page);
		
		return urlStr.toString();
	}
	
 
}
