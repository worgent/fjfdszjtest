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
package com.enation.javashop.core.model;
/**
 * 货运明细
 * @author kingapex
 *2010-4-8上午09:57:52
 */
public class DeliveryItem {
 
	private Integer    item_id;              
	private Integer delivery_id;  
	private Integer goods_id;
	private Integer product_id;
	private String sn;                  
	private String name;                 
	private Integer  num;
	private Integer itemtype;  //货物类型
	
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer itemId) {
		item_id = itemId;
	}
	public Integer getDelivery_id() {
		return delivery_id;
	}
	public void setDelivery_id(Integer deliveryId) {
		delivery_id = deliveryId;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer productId) {
		product_id = productId;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goodsId) {
		goods_id = goodsId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getItemtype() {
		return itemtype;
	}
	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}                 
	
}
