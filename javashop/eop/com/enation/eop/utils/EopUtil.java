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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * eop工具类
 * @author kingapex
 *2010-4-10下午01:55:50
 */
public class EopUtil {
	private EopUtil(){}
	
	/**
	 * 包装 html中的css路径<br>
	 * 将所有style link引入加入 指定的前缀,http开头被忽略
	 * @param html 
	 * @param wrapPath 要包装的前缀
	 * @return
	 */
	public  static  String  wrapcss(String html,String wrapPath){
		
		String pattern ="<link([^<|^>]*?)href=\"([^http|/eop|].*?)\"([^<|^>]*?)>";
	 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(html);
		if(m.find()){
			html  =m.replaceAll("<link$1href=\""+wrapPath+"$2\"$3>");
		}
		
		return html;
	}

 
	/**
	 * 包装 html中的javascript路径<br>
	 * 将所有javascript引入加入 指定的前缀,http开头被忽略
	 * @param html
	 * @param wrapPath
	 * @return
	 */
	public  static  String  wrapjavascript(String html,String wrapPath){
		
		String pattern ="<script([^<|^>]*?)src=\"([^http|/eop].*?)\"([^<|^>]*?)>";
	 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(html);
		
		if(m.find()){
			html  =m.replaceAll("<script$1src=\""+wrapPath+"$2\"$3>");
		}
		
		return html;
	}

	 
	/**
	 * 包装 html中的图片路径<br>
	 * 将所有图片引入加入 指定的前缀,http开头被忽略
	 * @param content
	 * @param wrapPath
	 * @return
	 */
	public  static  String  wrapimage(String content,String wrapPath){
		
		String pattern ="<img([^<|^>]*?)src=\"([^http|/eop].*?)\"([^<|^>]*?)>";
	 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(content);
		
		if(m.find()){
			content  =m.replaceAll("<img$1src=\""+wrapPath+"$2\"$3>");
		}
		
		return content;
	}
	
}
