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
package com.enation.framework.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * httpRequest常用方法工具
 * @author kingapex
 * 2010-2-12上午11:34:48
 */
public abstract class RequestUtil {
	private RequestUtil(){}
	/**
	 * 将request中的参数转为Map
	 * @param request
	 * @return
	 */
	public static Map<String,String> paramToMap(HttpServletRequest request){
		Map<String,String> params = new HashMap<String, String>();
		
		Map rMap = request.getParameterMap();
		Iterator rIter = rMap.keySet().iterator();
		
		while(rIter.hasNext()){
			Object key = rIter.next();
			String value = request.getParameter(key.toString());
			if(key==null || value==null)continue;
			params.put(key.toString(), value.toString());
		}
		
		return params;
		
	}	
	
	
	public static String getRequestUrl(HttpServletRequest request) {
		String pathInfo = (request).getPathInfo();
		String queryString = (request).getQueryString();

		String uri = (request).getServletPath();

		if (uri == null) {
			uri = (request).getRequestURI();
			uri = uri.substring((request).getContextPath().length());
		}

		return uri + ((pathInfo == null) ? "" : pathInfo)
				+ ((queryString == null) ? "" : ("?" + queryString));
	}
	
	
	public static String getRequestMethod(HttpServletRequest request){
		String method  = request.getParameter("_method");
		method=method==null?"get":method;
		method=method.toUpperCase();
		return method;
	}
	
	
	
}
