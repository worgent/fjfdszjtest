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
package com.enation.javashop.widget.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.service.IMemberOrderManager;
import com.enation.javashop.core.service.IOrderManager;

public class MemberOrderDetailWidget extends AbstractMemberWidget {
	
	private IMemberOrderManager memberOrderManager;
	private IOrderManager orderManager;
	
	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("orderdetail");
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String order_id = request.getParameter("order_id");
		int orderid = Integer.valueOf(order_id);
		Order order = memberOrderManager.getOrder(orderid);
		List orderLogList = memberOrderManager.listOrderLog(orderid);
		List orderItemsList = memberOrderManager.listGoodsItems(orderid);
		List pgk = orderManager.listGiftItems(0);
		List giftList  = memberOrderManager.listGiftItems(orderid);
		this.putData("order", order);
		this.putData("orderLogList", orderLogList);
		this.putData("orderItemsList", orderItemsList);
		this.putData("giftList", giftList);
	}

	public IMemberOrderManager getMemberOrderManager() {
		return memberOrderManager;
	}

	public void setMemberOrderManager(IMemberOrderManager memberOrderManager) {
		this.memberOrderManager = memberOrderManager;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

}
