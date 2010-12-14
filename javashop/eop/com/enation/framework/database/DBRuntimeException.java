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
package com.enation.framework.database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据库操作运行期异常
 * @author kingapex
 * 2010-1-6下午06:16:47
 */
public class DBRuntimeException extends RuntimeException {

 
	private static final long serialVersionUID = -368646349014580765L;
	
	private static final Log loger = LogFactory
			.getLog(DBRuntimeException.class);

	
	public DBRuntimeException(String message){
		super(message);
	}
	public DBRuntimeException(Exception e,String sql) {
	
		super("数据库运行期异常");
		loger.error("数据库运行期异常", e);
		if(loger.isErrorEnabled()){
			loger.error("数据库运行期异常，相关sql语句为"+ sql);
			loger.error(e);
		}
		
	}
	
	
	public DBRuntimeException(String message,String sql) {
		
		super(message);
		if(loger.isErrorEnabled()){
			loger.error(message+"，相关sql语句为"+ sql);
		 
		}
		
	}
	
	
}
