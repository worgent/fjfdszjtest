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

/**
 * 数据库路由器
 * @author kingapex
 * 2010-1-5下午05:14:44
 */
public interface IDBRouter {
	
	/**
	 * 得到一个用户某模块的表名
	 * @param userid 用户id
	 * @param moudle 模块名
	 * @return
	 */
	public String getTableName(String moudle);
	
	
	
	/**
	 * 生成一个用户某模块的数据表
	 * @param userid 用户id
	 * @param moudle 模块名
	 */
	public void createTable( String moudle);
	
	
	
	
}
