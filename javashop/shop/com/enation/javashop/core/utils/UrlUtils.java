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
package com.enation.javashop.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网店工具类
 * 
 * @author apexking
 * 
 */
public abstract class UrlUtils {

	public static String getParamStr(String servletPath){
		String pattern = "/search-(.*).html";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(servletPath);
		String str="" ;
		if (m.find()) {
			str = m.replaceAll("$1");
		}
		return str;
		
	}
	/**
	 * 解析一个搜索地址字串（在网店搜索页面中），根据传递的参数字串解析出参数值如：<br/>
	 * 有参数字串：cat{5}keyword{测试}<br/>
	 * 则cat参数值为:5,keyword参数值为:测试<br/>
	 * 
	 * @param url搜索地址字串
	 * @param name 参数名称
	 * @return
	 */
	public static String getParamStringValue(String param, String name) {
		String pattern = "(.*)" + name + "\\{(.[^\\{]*)\\}(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(param);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	} 
	
	
	
	public static int getParamInitValue(String param, String name) {
		String temp_str = getParamStringValue(param,name);
		
		int value = Integer.valueOf(temp_str);
		return value;
		
	}
	
	/**
	 * 解析一个搜索地址字串（在网店搜索页面中），得到排除某个参数后的字串
	 * @param url 搜索字串
	 * @param name 要排除的字串
	 * @return
	 */
	public static String getExParamUrl(String url,String name){
		String pattern = "(.*)" + name + "\\{(.[^\\{]*)\\}(.*)";

		String value = "";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);

		if (m.find()) {
			value = m.replaceAll("$1$3"); 
		}else{
			value=url;
		}
		return value;
 
	}
	
	
	/**
	 * 操作一个搜索字串：向字串中添加参数值。<br/>
	 * 在原有参数值的基础上加入新的参数值，用“,”号隔开。
	 * @param url
	 * @param name
	 * @param value
	 * @return
	 */
	public static String appendParamValue(String url,String name,String value){
		
		String old_value= getParamStringValue(url, name);
		String new_url =  getExParamUrl(url,name);
		
		if(old_value!=null){
			value=","+value;
		}else{
			old_value="";
		}
		
		new_url =new_url+name+"{"+old_value+value+"}";
		
		return new_url;
	}
	
	
	/**
	 * 解析一个伪静态url字串<br/>
	 * 得到这个伪静脉url字串的前缀<br/>
	 * 如：/goods_list_tag-tag_id{3}.html 得到goods_list_tag<br/>
	 * /search-cat_id{3}page{1}.html 得到search
	 * @param url
	 * @return
	 */
	public static String getUrlPrefix(String url){
		String pattern = "/(.*)-(.*)";

		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);

		if (m.find()) {
			value = m.replaceAll("$1"); 
		}else{
			value=null;
		}
		return value;
	}
	
	public static void main(String args[]){
		String str = UrlUtils.getParamStr("/search-cat{1}.html");
		System.out.println(str);
	}
}
