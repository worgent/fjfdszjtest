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

import java.io.Serializable;

/**
 * 优惠券表
 * @author lzf<br/>
 * 2010-3-23 上午10:41:55<br/>
 * version 1.0<br/>
 */
public class Coupons implements Serializable {
	private int cpns_id;
	private String cpns_name;
	private int pmt_id;
	private String cpns_prefix;
	private int cpns_gen_quantity;
	private String cpns_key;
	private int cpns_status;
	private int cpns_type;
	private int cpns_point;
	private String disabled;//enum('true','false') default 'false'
	public int getCpns_id() {
		return cpns_id;
	}
	public void setCpns_id(int cpnsId) {
		cpns_id = cpnsId;
	}
	public String getCpns_name() {
		return cpns_name;
	}
	public void setCpns_name(String cpnsName) {
		cpns_name = cpnsName;
	}
	public int getPmt_id() {
		return pmt_id;
	}
	public void setPmt_id(int pmtId) {
		pmt_id = pmtId;
	}
	public String getCpns_prefix() {
		return cpns_prefix;
	}
	public void setCpns_prefix(String cpnsPrefix) {
		cpns_prefix = cpnsPrefix;
	}
	public int getCpns_gen_quantity() {
		return cpns_gen_quantity;
	}
	public void setCpns_gen_quantity(int cpnsGenQuantity) {
		cpns_gen_quantity = cpnsGenQuantity;
	}
	public String getCpns_key() {
		return cpns_key;
	}
	public void setCpns_key(String cpnsKey) {
		cpns_key = cpnsKey;
	}
	public int getCpns_status() {
		return cpns_status;
	}
	public void setCpns_status(int cpnsStatus) {
		cpns_status = cpnsStatus;
	}
	public int getCpns_type() {
		return cpns_type;
	}
	public void setCpns_type(int cpnsType) {
		cpns_type = cpnsType;
	}
	public int getCpns_point() {
		return cpns_point;
	}
	public void setCpns_point(int cpnsPoint) {
		cpns_point = cpnsPoint;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
}
