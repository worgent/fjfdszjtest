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
 * 商品类型
 * @author apexking
 *
 */
public class GoodsType  implements java.io.Serializable {


    // Fields    

     private Integer type_id;
     private String name;
     private String props;
     private String params;
     private int disabled;
     private int is_physical;
     private int have_prop;
     private int have_parm;
     private int join_brand;
     private Integer[] brand_ids;

	 
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	public int getHave_parm() {
		return have_parm;
	}
	public void setHave_parm(int have_parm) {
		this.have_parm = have_parm;
	}
	public int getHave_prop() {
		return have_prop;
	}
	public void setHave_prop(int have_prop) {
		this.have_prop = have_prop;
	}
	public int getIs_physical() {
		return is_physical;
	}
	public void setIs_physical(int is_physical) {
		this.is_physical = is_physical;
	}
	public int getJoin_brand() {
		return join_brand;
	}
	public void setJoin_brand(int join_brand) {
		this.join_brand = join_brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer[] getBrand_ids() {
		return brand_ids;
	}
	public void setBrand_ids(Integer[] brand_ids) {
		this.brand_ids = brand_ids;
	}


	

}