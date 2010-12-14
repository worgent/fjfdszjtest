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
package com.enation.eop.core.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.enation.eop.core.Request;
import com.enation.eop.core.Response;


/**
 * @author kingapex
 * @version 1.0
 * @created 09-十月-2009 22:22:30
 */
public class RemoteRequest implements Request {

	public HttpClient httpClient;
	private Map<String ,String> params;
	
	public void setExecuteParams(Map<String,String> params){
		this.params = params;
	}

	private HttpClient getHttpClient(HttpServletRequest httpRequest){
		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("httpClient")==null){
			
			HttpClient httpclient = new DefaultHttpClient();
			session.setAttribute("httpClient", httpclient);
			
		}
		
		return (HttpClient)session.getAttribute("httpClient");
		
		
	}

	/**
	 * 
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response execute(String uri, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		String method = httpRequest.getMethod();
		
		method = method.toUpperCase();
		
		HttpClient httpclient = getHttpClient(httpRequest);
		HttpUriRequest httpUriRequest =null;
		
//		if(method.equals("POST")){
			HttpPost httppost = new HttpPost(uri);
			HttpEntity entity = HttpEntityFactory.buildEntity(httpRequest,this.params);
			httppost.setEntity(entity);
			httpUriRequest = httppost;
//		}
//		
//		if(method.equals("GET")){
//			HttpGet httpget = new HttpGet(uri);
//			httpUriRequest =  httpget;
//		}
//		
		
		try{
			
			HttpResponse httpresponse = httpclient.execute(httpUriRequest);
			HttpEntity rentity = httpresponse.getEntity();
			String content = EntityUtils.toString(rentity,"utf-8");
			Response response  = new StringResponse();
//			response.getStatusCode()
			response.setContent(content);
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	

	/**
	 * 
	 * @param uri
	 */
	public Response execute(String uri) {
		
		return null;
	}

}