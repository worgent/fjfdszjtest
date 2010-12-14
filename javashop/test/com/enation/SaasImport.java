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
package com.enation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.enation.framework.util.FileUtil;

public class SaasImport {
	
	@Test
	public void impl(){
		int userid =2;
		int siteid  = 6;
		String sql = FileUtil.read("d:/javashop.sql","UTF-8");
		String pattern = "drop table if exists js_(\\w+);";
	 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(sql);
		if (m.find()) {
			 sql = m.replaceAll("drop table if exists js_$1_"+userid+"_"+siteid+";");
		} 
		pattern="create table js_(\\w+)";
		p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		m = p.matcher(sql);
		if (m.find()) {
			 sql = m.replaceAll("create table js_$1_"+userid+"_"+siteid);
		} 
		String temp = sql;
		sql = FileUtil.read("d:/data.sql", "UTF-8");
		pattern="INSERT INTO `(\\w+)` VALUES";
		p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		m = p.matcher(sql);
		if (m.find()) {
			 sql = m.replaceAll("INSERT INTO `$1_"+userid+"_"+siteid+"` VALUES");
		} 		
		System.out.println(temp+sql);
		
	}
}
