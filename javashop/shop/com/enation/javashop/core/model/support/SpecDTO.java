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

import java.util.ArrayList;
import java.util.List;


/**
 * 用于属性信息传递
 * @author apexking
 *
 */
public class SpecDTO {
	
	private String[] spec_store; //库存
	private String[] spec_price; //价格	
	private String[] spec_sn;   //货号	
	private String spec_str;  //用于存在商品表里的 规格字串字段
	private List specs;  //都包含有什么规格组合的.,存储在规格表里,里面存储的是规格的值
	
	public SpecDTO(){
		specs = new ArrayList();
	}
	
	public void addSpec(String spec){
		specs.add(spec);
	}
	public String[] getSpec_price() {
		return spec_price;
	}
	public void setSpec_price(String[] spec_price) {
		this.spec_price = spec_price;
	}
	public String[] getSpec_store() {
		return spec_store;
	}
	public void setSpec_store(String[] spec_store) {
		this.spec_store = spec_store;
	}
	public String getSpec_str() {
		return spec_str;
	}
	public void setSpec_str(String spec_str) {
		this.spec_str = spec_str;
	}
	public String[] getSpec_sn() {
		return spec_sn;
	}
	public void setSpec_sn(String[] spec_sn) {
		this.spec_sn = spec_sn;
	}
	public List getSpecs() {
		return specs;
	}
	public void setSpecs(List specs) {
		this.specs = specs;
	}
 
	
	
}
