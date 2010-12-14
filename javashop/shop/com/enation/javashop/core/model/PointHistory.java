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

/**
 * 会员积分历史记录
 * 
 * @author lzf<br/>
 *         2010-3-22 上午11:01:31<br/>
 *         version 1.0<br/>
 */
public class PointHistory  {
	private int id;
	private int member_id;
	private int point;
	private Long time;
	private String reason;
	private String cnreason;
	private int related_id;
	private int type;
	private String operator;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int memberId) {
		member_id = memberId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getRelated_id() {
		return related_id;
	}
	public void setRelated_id(int relatedId) {
		related_id = relatedId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@NotDbField
	public String getCnreason() {
		if(reason.equals("order_pay_use")) cnreason = "订单消费积分";
		if(reason.equals("order_pay_get")) cnreason = "订单获得积分";
		if(reason.equals("order_refund_use")) cnreason = "退还订单消费积分";
		if(reason.equals("order_refund_get")) cnreason = "扣掉订单所得积分";
		if(reason.equals("order_cancel_refund_consume_gift")) cnreason = "Score deduction for gifts refunded for order cancelling.";
		if(reason.equals("exchange_coupon")) cnreason = "兑换优惠券";
		if(reason.equals("operator_adjust")) cnreason = "管理员改变积分";
		if(reason.equals("consume_gift")) cnreason = "积分换赠品";
		if(reason.equals("recommend_member")) cnreason = "发表评论奖励积分";
		return cnreason;
	}
	public void setCnreason(String cnreason) {
		this.cnreason = cnreason;
	}
	
	
}
