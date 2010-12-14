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
package com.enation.javashop.core.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.service.IMemberOrderManager;


public class MemberOrderManager extends BaseSupport implements
		IMemberOrderManager {

	
	public Page pageOrders(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		
		String sql = "select * from order where member_id = ? order by create_time desc";
		Page rpage = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, member.getMember_id());
		List<Map> list = (List<Map>) (rpage.getResult());
		return rpage;
	}
	
	
	public Order getOrder(int order_id) {
		Order order = (Order)this.baseDaoSupport.queryForObject("select * from order where order_id = ?", Order.class, order_id);
		return order;
	}

	
	public Delivery getOrderDelivery(int order_id) {
		Delivery delivery = (Delivery)this.baseDaoSupport.queryForObject("select * from delivery where order_id = ?", Delivery.class, order_id);
		return delivery;
	}
	
	
	public List listOrderLog(int order_id) {
		String sql = "select * from order_log where order_id = ?";
		List list = this.baseDaoSupport.queryForList(sql, order_id);
		list = list == null ? new ArrayList() : list;
		return list;
	}

	
	public List listGoodsItems(int order_id) {
		String sql = "select * from order_items where order_id = ?";
		List list = this.baseDaoSupport.queryForList(sql, order_id);
		list = list == null ? new ArrayList() : list;
		return list;
	}

	
	public List listGiftItems(int orderid) {
		String sql  ="select * from order_gift where order_id=?";
		return this.baseDaoSupport.queryForList(sql, orderid);
	}

}
