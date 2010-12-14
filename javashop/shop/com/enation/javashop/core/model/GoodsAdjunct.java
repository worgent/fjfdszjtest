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
 * 商品配件实体
 * 
 * @author lzf<br/>
 *         2010-4-1 上午09:43:29<br/>
 *         version 1.0<br/>
 */
public class GoodsAdjunct implements java.io.Serializable {
	private int adjunct_id;
	private int goods_id;
	private String adjunct_name;
	private int min_num;
	private int max_num;
	private String set_price; //enum('discount','minus') DEFAULT NULL
	private Double price;
	private String items;
	public int getAdjunct_id() {
		return adjunct_id;
	}
	public void setAdjunct_id(int adjunctId) {
		adjunct_id = adjunctId;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goodsId) {
		goods_id = goodsId;
	}
	public String getAdjunct_name() {
		return adjunct_name;
	}
	public void setAdjunct_name(String adjunctName) {
		adjunct_name = adjunctName;
	}
	public int getMin_num() {
		return min_num;
	}
	public void setMin_num(int minNum) {
		min_num = minNum;
	}
	public int getMax_num() {
		return max_num;
	}
	public void setMax_num(int maxNum) {
		max_num = maxNum;
	}
	public String getSet_price() {
		return set_price;
	}
	public void setSet_price(String setPrice) {
		set_price = setPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	
}
