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

import com.enation.javashop.core.model.Cart;


/**
 * 购物车业务接口
 * @author kingapex
 * @see com.enation.shop.cart.CartTest#testAdd
 *2010-3-23下午03:26:12
 */
public interface ICartManager {
	
	/**
	 * 添加一个购物项
	 * @param cart
	 * 
	 */
	public void add(Cart cart);
	
	
	
	
	/**
	 * 计算购物车中货物总数
	 * @param sessionid
	 * @return
	 */
	public Integer countItemNum(String sessionid);
	
	
	
	/**
	 * 读取某用户的购物车中项列表
	 * @param sessionid
	 * @return
	 */
	public List listGoods(String sessionid);
	
	
	
	/**
	 * 读取购物车中的赠品货物列表
	 * @param sessionid
	 * @return
	 */
	public List listGift(String sessionid);
	
	
	
	/**
	 * 读取购物车中捆绑促销的货物列 表
	 * @param sessionid
	 * @return
	 */
	public List listPgk(String sessionid);
	
	
	
	
	/**
	 * 清空某用户的购物车
	 * @param sessionid
	 */
	public void  clean(String sessionid);
	
	
	public void clean(String sessionid,Integer userid,Integer siteid);
	
	/**
	 * 更新购物数量
	 * @param sessionid
	 * @param cartid
	 */
	public void updateNum(String sessionid,Integer cartid,Integer num);
	
	
	/**
	 * 删除购物车中的一项
	 * @param cartid
	 */
	public void delete(String sessionid,Integer cartid);
	
	/**
	 * 计算购物商品货物总价(原始的，未处理优惠数据的)
	 * @param sessionid
	 * @return
	 */
	public Double countGoodsTotal(String sessionid);
	
	
	/**
	 * 计算捆绑商品总价格
	 * @param sessionid
	 * @return
	 */
	public Double countPgkTotal(String sessionid);
	
	
	
	/**
	 * 计算购物真正结算价格<br>
	 * 即应用了商品促销规则的价格后 
	 * @param sessionid
	 * @return
	 */
	public Double  countGoodsDiscountTotal(String sessionid);
	
	
	/**
	 * 计算购买商品重量，包括商品、捆绑商品、赠品
	 * @param sessionid
	 * @return
	 */
	public Double countGoodsWeight(String sessionid);
	
 
	/**
	 * 计算购物车中货物的总积分
	 * @param sessionid
	 * @return
	 */
	public  Integer countPoint(String sessionid);
	
	
}
