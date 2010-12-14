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
package com.enation.javashop.core.action.backend;

import java.util.List;
import java.util.Map;

import com.enation.eop.model.Member;
import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.service.IFreeOfferManager;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IOrderFlowManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IOrderReportManager;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 订单管理action
 * 
 * @author kingapex 2010-4-7下午01:51:48
 */
public class OrderAction extends WWAction {

	private Integer orderId;
	private String searchKey;
	private String searchValue;
	private String order;
	private IOrderManager orderManager;
	private IMemberManager memberManager;
	private IRegionsManager regionsManager;
	private IOrderFlowManager orderFlowManager;
	private IFreeOfferManager freeOfferManager;//赠品
	private IOrderReportManager orderReportManager;
	private IPromotionManager promotionManager;
	private Order ord;
	private List itemList;
	private List payLogList;
	private List refundList;
	private List shipLogList;
	private List reshipLogList;
	private List logList;
	private List provinceList;
	private List orderGiftList;//赠品列表
	private List pmtList; // 订单的优惠方案列表
	private Integer[] id;
	private int giftCount;
	
	
	private Map ordermap;

	private Member member;

	/**
	 * 分页读取订单列表
	 * 
	 * @return
	 */
	public String list() {
		this.webpage = this.orderManager.list(this.getPage(), this
				.getPageSize(), 0, searchKey, searchValue, order);
		return "list";
	}

	public String trash_list() {
		this.webpage = this.orderManager.list(this.getPage(), this
				.getPageSize(), 1, searchKey, searchValue, order);

		return "trash_list";
	}

	/**
	 * 显示订单的详细内容 到订单详细页
	 * 
	 * @return
	 */
	public String detail() {
		this.ord = this.orderManager.get(orderId);
		provinceList = this.regionsManager.listProvince();
		return "detail";
	}

	/**
	 * 显示基本信息
	 * 
	 * @return
	 */
	public String base() {

		itemList = this.orderManager.listGoodsItems(orderId); // 订单商品列表
		this.ord = this.orderManager.get(orderId); // 订单信息
		if (ord.getMember_id() != null)
			this.member = this.memberManager.get(ord.getMember_id());
		return "base";
	}

	/**
	 * 商品信息
	 * 
	 * @return
	 */
	public String items() {
		itemList = this.orderManager.listGoodsItems(orderId); // 订单商品列表
		this.orderGiftList = this.freeOfferManager.getOrderGift(orderId);
		this.giftCount = orderGiftList.size();
		return "items";
	}

	/**
	 * 收退款记录
	 * 
	 * @return
	 */
	public String payLog() {
		payLogList = this.orderReportManager.listPayLogs(orderId, 1);
		refundList = this.orderReportManager.listPayLogs(orderId, 2);
		return "pay_log";
	}

	/**
	 * 发退货记录
	 * 
	 * @return
	 */
	public String shipLog() {
		shipLogList = orderReportManager.listDelivery(orderId, 1);
		reshipLogList = orderReportManager.listDelivery(orderId, 2);
		return "ship_log";
	}

	/**
	 * 订单日志
	 * 
	 * @return
	 */
	public String log() {
		this.logList = this.orderManager.listLogs(orderId);
		return "log";
	}

	public String delete() {
		try {
			this.orderManager.delete(id);
			this.json = "{result:0,message:'订单删除成功'}";
		} catch (RuntimeException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
			this.json = "{result:1,message:\"订单删除失败：" + e.getMessage() + "\"}";

		}
		return this.JSON_MESSAGE;

	}

	public String revert() {
		try {
			this.orderManager.revert(id);
			this.json = "{result:0,message:'订单还原成功'}";
		} catch (RuntimeException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
			this.json = "{result:1,message:\"订单还原失败：" + e.getMessage() + "\"}";

		}
		return this.JSON_MESSAGE;

	}

	public String clean() {
		try {
			this.orderManager.clean(id);
			this.json = "{result:0,message:'订单清除成功'}";
		} catch (RuntimeException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
			this.json = "{result:1,message:\"订单清除失败：" + e.getMessage() + "\"}";

		}
		return this.JSON_MESSAGE;

	}

	/**
	 * 优惠方案
	 * 
	 * @return
	 */
	public String pmt() {
		 pmtList  =this.promotionManager.listOrderPmt(this.orderId);
		return "pmt";
	}

	/**
	 * 完成订单
	 * 
	 * @return
	 */
	public String complete() {
		try {
			this.orderFlowManager.complete(orderId);
			Order order = this.orderManager.get(orderId);
			this.json = "{result:1,message:'订单[" + order.getSn()
					+ "]成功标记为完成状态',orderStatus:" + order.getStatus() + "}";
		} catch (RuntimeException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
			this.json = "{result:0,message:\"订单完成失败：" + e.getMessage() + "\"}";

		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 作废订单
	 * 
	 * @return
	 */
	public String cancel() {
		try {
			this.orderFlowManager.cancel(orderId);
			Order order = this.orderManager.get(orderId);
			this.json = "{result:1,message:'订单[" + order.getSn()
					+ "]成功作废',orderStatus:" + order.getStatus() + "}";
		} catch (RuntimeException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
			this.json = "{result:0,message:\"订单作废失败：" + e.getMessage() + "\"}";

		}
		return this.JSON_MESSAGE;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Order getOrd() {
		return ord;
	}

	public void setOrd(Order ord) {
		this.ord = ord;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List getItemList() {
		return itemList;
	}

	public void setItemList(List itemList) {
		this.itemList = itemList;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getLogList() {
		return logList;
	}

	public void setLogList(List logList) {
		this.logList = logList;
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

	public List getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List provinceList) {
		this.provinceList = provinceList;
	}

	public IOrderFlowManager getOrderFlowManager() {
		return orderFlowManager;
	}

	public void setOrderFlowManager(IOrderFlowManager orderFlowManager) {
		this.orderFlowManager = orderFlowManager;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public Map getOrdermap() {
		return ordermap;
	}

	public void setOrdermap(Map ordermap) {
		this.ordermap = ordermap;
	}

	public IFreeOfferManager getFreeOfferManager() {
		return freeOfferManager;
	}

	public void setFreeOfferManager(IFreeOfferManager freeOfferManager) {
		this.freeOfferManager = freeOfferManager;
	}

	public List getOrderGiftList() {
		return orderGiftList;
	}

	public void setOrderGiftList(List orderGiftList) {
		this.orderGiftList = orderGiftList;
	}

	public int getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(int giftCount) {
		this.giftCount = giftCount;
	}

	public IOrderReportManager getOrderReportManager() {
		return orderReportManager;
	}

	public void setOrderReportManager(IOrderReportManager orderReportManager) {
		this.orderReportManager = orderReportManager;
	}

	public List getPayLogList() {
		return payLogList;
	}

	public void setPayLogList(List payLogList) {
		this.payLogList = payLogList;
	}

	public List getRefundList() {
		return refundList;
	}

	public void setRefundList(List refundList) {
		this.refundList = refundList;
	}

	public List getShipLogList() {
		return shipLogList;
	}

	public void setShipLogList(List shipLogList) {
		this.shipLogList = shipLogList;
	}

	public List getReshipLogList() {
		return reshipLogList;
	}

	public void setReshipLogList(List reshipLogList) {
		this.reshipLogList = reshipLogList;
	}

	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}

	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}

	public List getPmtList() {
		return pmtList;
	}

	public void setPmtList(List pmtList) {
		this.pmtList = pmtList;
	}
	

}
