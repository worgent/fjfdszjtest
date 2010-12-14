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

import com.enation.framework.database.NotDbField;
import com.enation.javashop.core.model.support.AdjunctGroup;

/**
 * 配件列表实体
 * 
 * @author lzf<br/>
 *         2010-3-31 上午09:49:07<br/>
 *         version 1.0<br/>
 */
public class AdjunctItem  {
	
	private int productid;
	private int goodsid;
	private String name;
	private String specs;
	private Double price;
	private Double coupPrice; //优惠后的价格
 
	private int num;
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getCoupPrice() {
		return coupPrice;
	}
	public void setCoupPrice(Double coupPrice) {
		this.coupPrice = coupPrice;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
 
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	 
}
