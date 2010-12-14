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

/**
 * 排名
 * 
 * @author lzf<br/>
 *         2010-3-10 上午10:41:24<br/>
 *         version 1.0<br/>
 */
public interface IRankManager {

	/**
	 * 取得销售量（额）排名
	 * 
	 * @param page
	 * @param pageSize
	 * @param condition
	 * @param sort
	 * @return
	 */
	public List rank_goods(int page, int pageSize, String condition, String sort);

	/**
	 * 取得会员购物量（额）排名
	 * 
	 * @param page
	 * @param pageSize
	 * @param condition
	 * @param sort
	 * @return
	 */
	public List rank_member(int page, int pageSize, String condition,
			String sort);

	/**
	 * 商品访问/购买次数<br/>
	 * 因为其访问次数并未详细记录明细，故不做时间段查询
	 * 
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @return
	 */
	public List rank_buy(int page, int pageSize, String sort);
	
	/**
	 * 销售指标分析
	 * @return
	 */
	public Map rank_all();

}
