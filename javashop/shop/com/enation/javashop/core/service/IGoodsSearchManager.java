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
package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.Page;

public interface IGoodsSearchManager {
	
	
	public Page search( int page, int pageSize,Map<String,String> params);

	
	

	/**
	 * 读取某个类别下的属性列表<br/> 同时计算每个属性的商品数量
	 * 
	 * @param type_id
	 * @param cat_path
	 * @return
	 */
	public List[] getPropListByCat(final int type_id, String cat_path,
			String brand_str, String propStr);
	
	
	
	
}
