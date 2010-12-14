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

/**
 * html工具类
 * @author kingapex
 * 2010-2-9上午01:08:02
 */
public class HtmlUtil {
	private HtmlUtil(){};
	
	/**
	 * 将某内容添加至某元素末尾
	 * @param html
	 * @param nodeName
	 * @param content
	 * @return
	 */
	public static String appendTo(String html,String nodeName,String content){
		
		return html.replaceAll("</"+nodeName+">", content+"</"+nodeName+">");
	}
	
	/**
	 * 将内容插入的某元素的开始
	 * @param html
	 * @param nodeName
	 * @param content
	 * @return
	 */
	public static String insertTo(String html,String nodeName,String content){
		return html.replaceAll("<"+nodeName+">", "<"+nodeName+">"+content);
	}
	
	public static void main(String[] args){
		String html ="<html><head>adfbb</head><body>adfadsf</body></html>";
		html = insertTo(html,"head","abc");
		System.out.println(html);
	}
}
