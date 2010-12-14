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
package com.qzgf.core.model;

import java.util.ArrayList;
import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 货品实体
 * @author kingapex
 *2010-3-9下午05:57:16
 */
public class Product {

	private Integer product_id;
	private Integer goods_id;
	private String name;
	private String sn;
	private Integer store;
	private Double price;
	private Double cost;
	private Double weight;
	private String specs;
	
	//货品对应规格信息，在添加货品时用到了
	private List<SpecValue> specList;
	
	public Product(){
		specList= new ArrayList();
	}
	
	@NotDbField
	public List<SpecValue> getSpecList() {
		return specList;
	}
	public void setSpecList(List<SpecValue> specList) {
		this.specList = specList;
	}
	
	//添加一种规格
	public void addSpec(SpecValue spec){
		this.specList.add(spec);
	}
	
	/**
	 * 返回根据 specList得到的 specvid json
	 * @return
	 */
	@NotDbField
	public String getSpecsvIdJson(){
		String json="[";
		int i=0;
		for(SpecValue value :specList){
			if(i!=0)json+=",";
			json+=value.getSpec_value_id();
			i++;
		}
		json+="]";
		return json;
	}
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer productId) {
		product_id = productId;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goodsId) {
		goods_id = goodsId;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
