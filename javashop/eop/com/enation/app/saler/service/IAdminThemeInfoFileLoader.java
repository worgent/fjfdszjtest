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
package com.enation.app.saler.service;

import org.w3c.dom.Document;


public interface IAdminThemeInfoFileLoader {
	
	/**
	 * 根据产品唯一标识（非数据库索引 ）加载产品包下的profile.xml并返回其document对象
	 * @param productName
	 * @return
	 */
	public Document load(String productId, String path, Boolean isCommonTheme);
	
}