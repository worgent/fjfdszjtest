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
package com.enation.javashop.core.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PaymentLog;
import com.enation.javashop.core.service.IOrderFlowManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPaymentManager;

public abstract class AbstractPaymentPlugin extends AutoRegisterPlugin {
	private IPaymentManager paymentManager;
	private IOrderFlowManager orderFlowManager;
	private IOrderManager orderManager; 
	protected final Logger logger = Logger.getLogger(getClass());
	protected String getCallBackUrl(){
		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		String serverName =request.getServerName();
		String paymentId = request.getParameter("paymentid");
		int port  = request.getLocalPort();
		String contextPath = request.getContextPath();
		String orderId = request.getParameter("orderid"); 
		return "http://"+serverName+":"+port+contextPath+"/widget?type=paywidget&action=callback&paymentid="+paymentId+"&orderid="+orderId;
	}
	
	protected String getShowUrl(){
		
		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		String paymentId = request.getParameter("paymentid");
		String serverName =request.getServerName();	
		int port  = request.getLocalPort();
		String contextPath = request.getContextPath();		
		return "http://"+serverName+":"+port+contextPath+"/widget?type=paywidget&action=show&paymentid="+paymentId;
	}
	
	protected Map<String,String> getConfigParams(){
		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		String paymentId = request.getParameter("paymentid");
		
		return this.paymentManager.getConfigParams(Integer.valueOf(paymentId));
	}
	
	protected void paySuccess(Integer orderId){
		Order order  = orderManager.get(orderId);
		PaymentLog paymentLog =  new PaymentLog();
		Member member  = UserServiceFactory.getUserService().getCurrentMember();
		if(member!=null){
			paymentLog.setMember_name(member.getUname());
			paymentLog.setMember_id(member.getMember_id());
		}
		
		paymentLog.setPay_type(order.getPayment_type());
		paymentLog.setMoney(order.getOrder_amount());		
		paymentLog.setOrder_id(orderId);
		paymentLog.setPay_method(order.getPayment_name());
		this.orderFlowManager.pay(paymentLog);
	}
	
	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}
	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}

	public IOrderFlowManager getOrderFlowManager() {
		return orderFlowManager;
	}

	public void setOrderFlowManager(IOrderFlowManager orderFlowManager) {
		this.orderFlowManager = orderFlowManager;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
	
}
