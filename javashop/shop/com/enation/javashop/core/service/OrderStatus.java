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
package com.enation.javashop.core.service;

/**
 * 订单状态
 * 
 * @author apexking
 * 
 */
public interface OrderStatus {

	//订单状态
	
	public static final int ORDER_CANCEL_SHIP = -2; // 退货

	public static final int ORDER_CANCEL_PAY = -1; // 退款

	public static final int ORDER_NOT_PAY = 0; // 未付款

	public static final int ORDER_PAY = 1; // 已支付

	public static final int ORDER_SHIP = 2; // 已发货

	public static final int ORDER_COMPLETE = 3; // 已完成

	public static final int ORDER_CANCELLATION = 4; // 作废

	//付款状态
	public static final int PAY_NO= 0;  
	public static final int PAY_YES= 1;
	public static final int PAY_CANCEL =2; //已经退款
	public static final int PAY_PARTIAL_REFUND = 3; //部分退款
	public static final int PAY_PARTIAL_PAYED =4 ;//部分付款
	
	//货运状态
	public static final int SHIP_NO= 0;  //	0未发货
	public static final int SHIP_YES= 1;//	1已发货
	public static final int SHIP_CANCEL= 2;//	2.已退货
	public static final int SHIP_PARTIAL_SHIPED= 4; //	4 部分发货
	public static final int SHIP_PARTIAL_CANCEL= 3;// 3 部分退货
	

	
	
	
}
