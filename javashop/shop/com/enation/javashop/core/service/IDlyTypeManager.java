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
import com.enation.javashop.core.model.DlyType;
import com.enation.javashop.core.model.support.DlyTypeConfig;
import com.enation.javashop.core.model.support.TypeAreaConfig;


/**
 * 配送方式管理
 * @author kingapex
 *2010-3-29上午07:26:30
 */
public interface IDlyTypeManager{
	
	
	
	
	/**
	 * 添加配送方式（统一配置式）
	 * @param type 配送方式实体
	 * @param config 配送费用设置
	 * @see com.enation.shop.delivery.DeliveryTest#addSameTest
	 */
	public void add(DlyType type,DlyTypeConfig config );
	
	
	
	
	
	
	/**
	 * 添加配送方式（分别不同地区费用）
	 * @param type 配送方式实体
	 * @param config 配送费用设置
	 * @param configArray 地区费用配置数组
	 * @see com.enation.shop.delivery.DeliveryTest#addDiffTest
	 */
	public void add(DlyType type, DlyTypeConfig config, TypeAreaConfig[] configArray);
	
	
	
	
	public void edit(DlyType type, DlyTypeConfig config);
	
	
	public void edit(DlyType type, DlyTypeConfig config, TypeAreaConfig[] configArray);
	
	/**
	 * 删除配送方式
	 * @param type_ids
	 */
	public void delete(String type_ids);
	/**
	 * 根据配送地区查询配送方式的所有列表
	 * @param area_id
	 * @return
	 */
	public List listByAreaId(Integer area_id);
	
	 /**
	  * 分页读取配送方式
	  * @param order
	  * @param page
	  * @param pageSize
	  * @return
	  */
	public Page pageDlyType(int page,int pageSize);
	
	
	/**
	 * 读取配送方式列表</br>
	 * 读取的条件为在配送范围的配送方式，可配送此地区的配送方式被返回</br>
	 * @param weight 商品重量
	 * @param orderPrice 订单价格
	 * @param regoinStr  地区id字串(联动的第三级)
	 * @return 可配送此地区的配送方式，此列表中的DlyType是计算好price(需要对订单增加的费用DlyType)的 
	 * @see com.enation.shop.delivery.DeliveryTest#testGetTypeForOrder
	 */
	public List<DlyType> list(Double weight,Double orderPrice,String regoinId );
	
	
	/**
	 * 读取所有的配送方式
	 * @return
	 */
	public List<DlyType> list();
	
	
	/**
	 * 计算某个配送方式的配送费用和保价费用
	 * @param typeId 配送方式id
	 * @param weight 商品重量
	 * @param orderPrice 订单价格
	 * @param regionId 地区id字串(联动的第三级)
	 * @param isProtected  是否保价
	 * @return 返回配送费用，如果保价则返回同时保价费用</br>
	 * 		数组的第一个元素为配送费用，第二个元素为保价费用</br>
	 * 	 	不保价时数组保价费用为null
	 * @see com.enation.shop.delivery.DeliveryTest#
	 */
	public Double[] countPrice(Integer typeId,Double weight,Double orderPrice,String regionId,boolean isProtected);
	
	
	
	              
	
	/**
	 * 获取配送方式
	 * @param type_id
	 * @return
	 */
	public DlyType getDlyTypeById(Integer type_id);

	
	/**
	 * 校验公式
	 * @param exp
	 * @return
	 */
	public Double  countExp(String exp,Double weight,Double orderprice);

}