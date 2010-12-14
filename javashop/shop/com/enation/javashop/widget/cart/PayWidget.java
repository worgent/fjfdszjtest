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
package com.enation.javashop.widget.cart;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.IPaymentEvent;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPaymentManager;

/**
 * 支付挂件
 * @author kingapex
 *2010-4-12上午10:58:01
 */
public class PayWidget extends AbstractWidget {
	private  HttpServletRequest  request;
	private IPaymentManager paymentManager;
	private IOrderManager orderManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		request  = ThreadContextHolder.getHttpRequest();
	//	this.setPageFolder("/widgets/cart");
		this.setPageName("pay");
		String action = request.getParameter("action");
		if(null== action || "".equals(action) ){
			this.pay();
		}else if("callback".equals(action)){
			this.callback();
		}else if("show".equals(action)){
			this.show();
		}
		
	}

	private void pay(){
		Integer orderId = this.getIntParam("orderid"); 
		Integer paymentId = this.getIntParam("paymentid");
		 
		Order order  = this.orderManager.get(orderId);
		PayCfg payCfg=  this.paymentManager.get(paymentId);
		
		IPaymentEvent  paymentPlugin =  SpringContextHolder.getBean(payCfg.getType());
		String payhtml = paymentPlugin.onPay(payCfg, order);

		this.putData("payhtml", payhtml);		
	}
	
	
	private void callback(){

		Integer paymentId = this.getIntParam("paymentid"); //因有的支付接口会全部转为小写故参数全部为小写
		PayCfg payCfg=  this.paymentManager.get(paymentId);		
		IPaymentEvent  paymentPlugin =  SpringContextHolder.getBean(payCfg.getType());
		String payhtml  = paymentPlugin.onCallBack();
		String orderId = request.getParameter("orderid"); 
		this.showSuccess(payhtml,"查看此订单","member_orderdetail.html?order_id="+orderId);
		
	//	this.putData("payhtml", payhtml);			
		
	}
	
	
	private void show(){
	
		this.putData("payhtml", "订单支付成功!");	
	}
	
	private Integer getIntParam(String name){
		try{
		 return Integer.valueOf( request.getParameter(name) );
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}

	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}

	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
	
}
