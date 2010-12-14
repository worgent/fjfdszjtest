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
package com.enation.eop.context;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.EopSetting;
import com.enation.eop.core.resource.model.EopSite;

public class EopContext {
	private static ThreadLocal<HttpServletRequest> HttpRequestHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<EopContext> EopContextHolder = new ThreadLocal<EopContext>();
	
	public static void setContext(EopContext context){
		EopContextHolder.set(context);
	}
	
	public static EopContext getContext(){
		return EopContextHolder.get();
	}
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestHolder.set(request);
	}
	
	
	public static HttpServletRequest getHttpRequest(){
		return HttpRequestHolder.get();
	}
	
	private EopSite currentSite;
	public  EopSite getCurrentSite(){
		return currentSite;
	}
	
	public  void setCurrentSite(EopSite site){
		currentSite = site;
	}
	
	//得到当前站点上下文
	public String getContextPath(){
		if("2".equals(EopSetting.RUNMODE) ){
			EopSite site  = this.getCurrentSite();
			StringBuffer context = new StringBuffer("/user");
			context.append("/");
			context.append(site.getUserid());
			context.append("/");
			context.append(site.getId());
			return context.toString();
		}else{
			return "";
		}
	}
}
