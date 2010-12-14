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
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.enation.eop.core.ContextType;
import com.enation.eop.core.Response;

/**
 * @author kingapex
 * @version 1.0
 * @created 09-十月-2009 22:45:17
 */
public class StringResponse implements Response {
    private String content;
    private String contentType;
	public StringResponse(){
		contentType= ContextType.HTML;
	}

	public void finalize() throws Throwable {

	}

	public String getContent(){
		
		return content;
	}

	public String getStatusCode(){
		return "";
	}

	public String getContentType(){
		return this.contentType;
	}
  
	/**
	 * 
	 * @param content
	 */
	public void setContent(String content){
		this.content = content;
	}

	/**
	 * 
	 * @param code
	 */
	public void setStatusCode(String code){

	}

	/**
	 * 
	 * @param contentType
	 */
	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	
	public InputStream getInputStream() {
	 
		try {
			InputStream  in = new   ByteArrayInputStream(this.content.getBytes("UTF-8"));
			return  in;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}