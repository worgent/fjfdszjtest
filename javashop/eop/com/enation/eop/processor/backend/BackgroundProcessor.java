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
package com.enation.eop.processor.backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.context.ConnectType;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.Request;
import com.enation.eop.core.Response;
import com.enation.eop.core.impl.LocalRequest;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.impl.backend.BackPageGetter;
import com.enation.eop.impl.backend.MenuJsonGetter;
import com.enation.eop.model.FacadePage;
import com.enation.eop.processor.Processor;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;

/**
 * @author kingapex
 * @version 1.0
 * @created 11-十月-2009 16:20:08
 */
public class BackgroundProcessor implements Processor {
 
	/**
	 * 
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int model,HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		String uri = httpRequest.getServletPath(); 
		
		
		//用户身份校验
		 if( ! uri.startsWith("/admin/login")
			&& ! uri.startsWith("/admin/index.jsp") 
			&& !uri.equals("/admin/")
			&& !uri.equals("/admin")
			){
			 IUserService userService = UserServiceFactory.getUserService();
			 if(!userService.isUserLoggedIn()){
				 	List<String> msgs = new ArrayList<String>();
				 	Map<String ,String> urls = new HashMap<String,String>();
				 	msgs.add("您尚未登陆或登陆已经超时，请重新登录。");
				 	String ctx = httpRequest.getContextPath();
				 	urls.put("点进这里进入登录页面", ctx+"/admin/");
				 	httpRequest.setAttribute("msgs", msgs);
				 	httpRequest.setAttribute("urls", urls);
					httpRequest.setAttribute("target", "_top");
//					httpRequest.setAttribute("message", "您尚未登陆或登陆已经超时，请重新登录。");
//					httpRequest.setAttribute("link", "index.jsp");
//					httpRequest.setAttribute("linkmessage", "点进这里进入登录页面");		
					Request request = new LocalRequest();
					Response response = request.execute("/admin/message.jsp", httpResponse,
							httpRequest);
					return response;
			 }
		 }
		

		Processor processor  =null;
		
		EopSite site  = EopContext.getContext().getCurrentSite();
		
 
		model =ConnectType.local;
		FacadePage page    = new FacadePage(site);
 
		page.setUri(uri);
		
		if(uri.startsWith("/admin/menu")){
			processor = new MenuJsonGetter(page);
		}else if( uri.startsWith("/admin/login") ){
			processor = new LoginProcessor();
		}else if( uri.startsWith("/admin/logout") ) {
			processor = new LogoutProcessor();
		}else{
			processor  = new BackPageGetter(page);
		}
 		
		Response response  = processor.process(model, httpResponse, httpRequest);		
		return response;
	}
}
