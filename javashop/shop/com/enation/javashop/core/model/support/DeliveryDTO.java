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

import com.enation.javashop.core.model.Delivery;

public class DeliveryDTO  {
	
	private Delivery delivery;
	private String reason;
	private String[] sn;
	private String[] name;
	private String[] num;
	private Integer[] item_id;
	
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getNum() {
		return num;
	}
	public void setNum(String[] num) {
		this.num = num;
	}
	public String[] getSn() {
		return sn;
	}
	public void setSn(String[] sn) {
		this.sn = sn;
	}
	
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	public Integer[] getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer[] item_id) {
		this.item_id = item_id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
