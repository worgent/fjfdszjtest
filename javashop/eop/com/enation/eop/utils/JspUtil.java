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
package com.enation.eop.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class JspUtil {
	public static String getJspOutput(String jsppath, HttpServletRequest request,
			HttpServletResponse response)  {
		WrapperResponse wrapperResponse = new WrapperResponse(response);
		try {
			request.getRequestDispatcher(jsppath).include(request, wrapperResponse);
			
		} catch (ServletException e) {
		 
			e.printStackTrace();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		return wrapperResponse.getContent();
	}
	
	public static String getJspOutput1(String jsppath, HttpServletRequest request,
			HttpServletResponse response)  {
		WrapperResponse wrapperResponse = new WrapperResponse(response);
		try {
		 
			request.getRequestDispatcher(jsppath).forward(request, wrapperResponse);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wrapperResponse.getContent();
	}
	
	
}
