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
import com.enation.javashop.core.model.FreeOffer;

/**
 * 赠品管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-15 上午10:17:48<br/>
 *         version 1.0<br/>
 * <br/>
 */
public interface IFreeOfferManager {
	
	public FreeOffer get(int fo_id);
	
	public void saveAdd(FreeOffer freeOffer);
	
	public void update(FreeOffer freeOffer);
	
	public void delete(String bid);
	
	public void revert(String bid);
	
	public void clean(String bid);
	
	
	
	
	/**
	 * 分页读取所有赠品列表
	 * @param page
	 * @param pageSize
	 * @return modify by kingapex - 2010-5-4
	 */
	public Page list(int page,int pageSize);
	
	
	
	
	public Page list(String name,String order,int page,int pageSize);
	
	public Page pageTrash(String name,String order,int page,int pageSize);
	
	/**
	 * 取得订单对应的赠品列表
	 * @param order_id
	 * @return
	 */
	public List getOrderGift(int order_id);
	
	
	/**
	 * 根据某些赠品id读取赠品列表
	 * @param ids 赠品id数组 
	 * @return 赚品列表
	 * @author kingapex 
	 */
	public List list(Integer[] ids);
	
	

}
