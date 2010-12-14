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
package com.enation.javashop.core.model.support;

import java.util.List;

import com.enation.framework.database.NotDbField;
import com.enation.javashop.core.model.Promotion;

/**
 * 购物项
 * @author kingapex
 *2010-5-5上午10:13:27
 */
public class CartItem {

	private Integer id;
	private Integer product_id;
	private Integer goods_id;
	private String name;

	private Double mktprice;
	private Double price;

	private Double coupPrice; // 优惠后的价格
	private Double subtotal; // 小计

	private int num;
	private Integer limitnum; //限购数量(对于赠品)
	private String image_default;
	private Integer point;
	private Integer itemtype; //物品类型(0商品，1捆绑商品，2赠品)
	private String sn; // 捆绑促销的货号 
	private String addon; //配件字串
	
	//此购物项所享有的优惠规则
	private List<Promotion > pmtList;
	
	
	public void setPmtList(List<Promotion > pmtList){
		this.pmtList = pmtList;
	}
	
	@NotDbField
	public List<Promotion > getPmtList(){
		return this.pmtList;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer productId) {
		product_id = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMktprice() {
		return mktprice;
	}

	public void setMktprice(Double mktprice) {
		this.mktprice = mktprice;
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

	public Double getSubtotal() {
		this.subtotal= this.num * this.coupPrice;
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getImage_default() {
		return image_default;
	}

	public void setImage_default(String imageDefault) {
		image_default = imageDefault;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goodsId) {
		goods_id = goodsId;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

 
	public Integer getLimitnum() {
		return limitnum;
	}

	public void setLimitnum(Integer limitnum) {
		this.limitnum = limitnum;
	}

	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAddon() {
		return addon;
	}

	public void setAddon(String addon) {
		this.addon = addon;
	}
	
	

}
