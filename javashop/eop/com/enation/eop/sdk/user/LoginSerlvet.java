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
package com.enation.eop.sdk.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.enation.eop.sdk.EopServlet;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.StringUtil;

/**
 * 登陆的Eop Servlet
 * @author kingapex
 *
 */
public class LoginSerlvet implements EopServlet{
	protected static final Logger logger = Logger.getLogger(LoginSerlvet.class);
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
		response.setContentType("text/plain;charset=utf-8");
		try{
 		
			Integer userid = StringUtil.toInt(request.getParameter("userid"));
			Integer siteid  = StringUtil.toInt(request.getParameter("siteid"));
			Integer adminid  = StringUtil.toInt(request.getParameter("adminid"));
			String jsoncallback  = request.getParameter("jsoncallback");
			 
			if(logger.isDebugEnabled()){
				logger.debug("do user login...");
			}
			UserContext userContext = new UserContext(userid,siteid,adminid);
	
			WebSessionContext<UserContext> webSessionContext = ThreadContextHolder.getSessionContext();
			
			webSessionContext.setAttribute(UserContext.CONTEXT_KEY, userContext);
//			System.out.println("login ok111");
			if(jsoncallback==null){
				response.getWriter().print("{result:\"0\"}");
			}else{
				response.getWriter().print(jsoncallback +"({result:\"0\"})");
			}
		}catch(Exception e){
			response.getWriter().print("{'result':'1'}");
		} 
		
	}

 

}
