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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;


public abstract class HttpEntityFactory {

	
	
	
	public static HttpEntity buildEntity(HttpServletRequest request,Map<String,String> otherParams) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		HttpEntity entity =  null;
		if(isMultipart){
			entity= buildMultipartFormEntity(request,otherParams);
		}else{
			entity= buildFormEntity(request,otherParams);
		}
		return entity;
	}

	
	
	private static HttpEntity buildFormEntity(HttpServletRequest request,Map<String,String> otherParams) {
		try {
//			Map paramMap = request.getParameterMap();
			Enumeration<String> paramNames =   request.getParameterNames();
			if(paramNames==null) return null;
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			
			
			while(paramNames.hasMoreElements()){
				String name  = paramNames.nextElement();
				String value  = request.getParameter(name);
				formparams.add(new BasicNameValuePair(name, value));
			}
			
			if(otherParams!=null){
				Iterator<String> iterator = otherParams.keySet().iterator();
			    while(iterator.hasNext()){
			    	String key = iterator.next();
			    	String value = otherParams.get(key);
			    	formparams.add(new BasicNameValuePair(key, value));	    	
			    }
			    
			}			
			
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					"UTF-8");

			return entity;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	
	private static HttpEntity buildMultipartFormEntity(HttpServletRequest request,Map<String,String> otherParams){
		 

		try {
			MultipartEntity entity = new MultipartEntity();
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);			
			List<FileItem >  items = upload.parseRequest(request);
			for (FileItem item:items) {
			    String fieldName = item.getFieldName();
			  
			    if (item.isFormField()) { 
			    	
			    	StringBody strBody = new StringBody( item.getString());
			    	//entity.addPart(fieldName, strBody);
			    } else {
			    	 String name = item.getName();
			    	InputStream dataIn = new ByteArrayInputStream(item.get());
			    	InputStreamBody isbody= new InputStreamBody( dataIn,name);
			    	//entity.addPart(fieldName, isbody);
			    }
			    
			}

			
			if(otherParams!=null){
				Iterator<String> iterator = otherParams.keySet().iterator();
			    while(iterator.hasNext()){
			    	String key = iterator.next();
			    	String value = otherParams.get(key);
			    	StringBody strBody = new StringBody(value);
			    	//entity.addPart(key, strBody);    	
			    }
			    
			}	
			
			
			
			return entity;
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
