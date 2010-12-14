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
package com.enation.framework.database.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enation.framework.database.DBRuntimeException;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;
/**
 * 默认sql文件执行器
 * @author kingapex
 * 2010-1-25上午11:50:19
 */
final public class DefaultSqlFileExecutor implements ISqlFileExecutor {
	
	private String dbType="mysql"; //数据库类型
	private JdbcTemplate jdbcTemplate;
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	
	public void execute(String sqlPath) { 
		String content ;
		if(sqlPath.startsWith("file:")){
			content = FileUtil.readFile(sqlPath.replaceAll("file:", ""));
		 
		}else{
			content = sqlPath;
		}
		if(dbType.equals("mysql")){
			mysqlExecute(content);
		}
	}

 
	
	private void mysqlExecute(String content){
		String pattern ="/\\*(.|[\\r\\n])*?\\*/";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(content);
		if (m.find()) {
			content = m.replaceAll("");
		}		
 
		String[] sql_ar = content.split(";\n");
		
		if(StringUtil.isEmpty(content) || sql_ar== null || sql_ar.length==0) return ;
		
		if(logger.isDebugEnabled()){
			logger.debug("开始安装...." );
		}
		
		try{
		 	this.jdbcTemplate.batchUpdate(sql_ar);
////			for(int i=0;i<sql_ar.length;i++){
////			String s  = sql_ar[i];
////			if(!StringUtil.isEmpty(s)){
////				logger.debug("execute->"+s );
////				System.out.println("execute->"+s);
////				this.jdbcTemplate.execute(s); 
////			}
////			else{ 
////				System.out.println("is empty");
////			}
//		  }				
		}catch(RuntimeException e){
			this.logger.debug("安装示例数据出错",e);
			throw  e;
		}
		
 
		if(logger.isDebugEnabled()){
			logger.debug("安装完成");
		}
		
	}
	
 


	private String mysql_escape_string(String str){
	       if( str == null || str.length() == 0 )
	           return str;
	       str = str.replaceAll("'", "\\'");
	       str = str.replaceAll("\"", "\\\"");        
	       return str;
	}


	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
