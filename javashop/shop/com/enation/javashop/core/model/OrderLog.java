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
 * 订单日志
 * @author kingapex
 *2010-4-6下午05:24:16
 */
public class OrderLog {
	 	
	private Integer  log_id;		
	private Integer order_id;	
	private Integer op_id;		
	private String op_name;		
	private String message;		
	private Long op_time;
	public Integer getLog_id() {
		return log_id;
	}
	public void setLog_id(Integer logId) {
		log_id = logId;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer orderId) {
		order_id = orderId;
	}
	public Integer getOp_id() {
		return op_id;
	}
	public void setOp_id(Integer opId) {
		op_id = opId;
	}
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String opName) {
		op_name = opName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getOp_time() {
		return op_time;
	}
	public void setOp_time(Long opTime) {
		op_time = opTime;
	}
 
	
}
