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
package com.enation.framework.context.webcontext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.enation.framework.context.webcontext.impl.WebSessionContextImpl;


/**
 *  用ThreadLocal来存储Session,以便实现Session any where 
 * @author kingapex
 * <p>2009-12-17 下午03:10:09</p>
 * @version 1.1
 * 新增request any where
 */
public class ThreadContextHolder  {
	protected static final Logger logger = Logger.getLogger(ThreadContextHolder.class);
	
	private static ThreadLocal<WebSessionContext> SessionContextThreadLocalHolder = new ThreadLocal<WebSessionContext>();
	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();
	
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestThreadLocalHolder.set(request);
	}
	
	public static HttpServletRequest getHttpRequest(){
		return  HttpRequestThreadLocalHolder.get();
	}
	
	
	
	
	
	
	public static void setHttpResponse(HttpServletResponse response){
		HttpResponseThreadLocalHolder.set(response);	
	}
	
	public static HttpServletResponse getHttpResponse(){
		
		return HttpResponseThreadLocalHolder.get();
	}
	
	
	
	public static void setSessionContext(WebSessionContext context) {
		SessionContextThreadLocalHolder.set(context);
	}

	public static void destorySessionContext() {
		WebSessionContext context = SessionContextThreadLocalHolder.get();
		if (context != null) {
			context.destory();
		}
	}

	public static   WebSessionContext  getSessionContext() {
		if (SessionContextThreadLocalHolder.get() == null) {
			if(logger.isDebugEnabled())
				logger.debug("create new webSessionContext.");
			SessionContextThreadLocalHolder.set(new WebSessionContextImpl());
		}else{
			if(logger.isDebugEnabled())
				logger.debug(" webSessionContext not null and return ...");
		}
		return SessionContextThreadLocalHolder.get();
	}
	
}
