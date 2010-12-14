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
package com.enation.eop.processor.facade;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.core.ContextType;
import com.enation.eop.core.Response;
import com.enation.eop.core.impl.InputStreamResponse;
import com.enation.eop.processor.Processor;
/**
 * web资源处理器
 * @author kingapex
 *2010-2-27上午12:11:26
 */
public class WebResourceProcessor implements Processor {

	
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		String path  = httpRequest.getServletPath();
	
		path = path.replaceAll("/resource/","");
	 
	 
		try{
			InputStream in =   getClass().getClassLoader().getResourceAsStream(path);
			Response response  = new InputStreamResponse(in);
			
			//IWebResourceReader reader  = new WebResourceReader();
		///	response.setContent(reader.read(path));
			 
			if(path.toLowerCase().endsWith(".js")) response.setContentType(ContextType.JAVASCRIPT);
			if(path.toLowerCase().endsWith(".css")) response.setContentType(ContextType.CSS);
			if(path.toLowerCase().endsWith(".jpg")) response.setContentType(ContextType.JPG);
			if(path.toLowerCase().endsWith(".gif")) response.setContentType(ContextType.GIF);
			if(path.toLowerCase().endsWith(".png")) response.setContentType(ContextType.PNG);
			
			return response;
		}catch(RuntimeException e){
			//response.setContent(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		
	}

}
