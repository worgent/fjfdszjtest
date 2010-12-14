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

import com.enation.framework.database.Page;

/**
 * 商品收藏管理
 * 
 * @author lzf<br/>
 *         2010-3-24 下午02:39:25<br/>
 *         version 1.0<br/>
 */
public interface IFavoriteManager {

	/**
	 * 列表我的收藏
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page list(int pageNo, int pageSize);
	
	
	
	/**
	 * 读取会员的所有收藏商品
	 * @return
	 */
	public List list();

	/**
	 * 删除收藏
	 * 
	 * @param favorite_id
	 */
	public void delete(int favorite_id);
	
	
	
	/**
	 * 添加一个收藏
	 * @param goodsid
	 * @param memberid
	 */
	public void add(Integer goodsid);
	
	
}
