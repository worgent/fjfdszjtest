package com.qzgf.NetStore.service.impl;

import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IOrderDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.IOrderService;

public class OrderService implements IOrderService {
	@SuppressWarnings("unchecked")
	@Override
	public List queryOrdersByCondition(String condition,String userid) {
		return orderDAO.queryOrdersByCondition(condition,userid);
	}

	@SuppressWarnings("unchecked")
	public List queryOperateHistory(String orderId) {
		return orderDAO.queryOperateHistory(orderId);
	} 

	private IOrderDAO orderDAO;
	
	public IOrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List listOrderDetails(String orderId) {
		return this.orderDAO.listOrderDetails(orderId);
	}

	@SuppressWarnings("unchecked")
	public Map listOrderUserInfo(String orderId) {
		return this.orderDAO.listOrderUserInfo(orderId);
	}

	@SuppressWarnings("unchecked")
	public List queryAllPayType() {
		return this.orderDAO.queryAllPayType();
	}

	@SuppressWarnings("unchecked")
	public Page queryOrders(String orderId,String orderStatus,String userId,String allSum,String payTypeId,String beginDay,String endDay,int npage){
		return this.orderDAO.queryOrders(orderId, orderStatus, userId, allSum, payTypeId, beginDay, endDay, npage);
	}

	@Override
	public boolean updateOrderStatus(String orderId, String nextOrderStatus,String operatePerson) {
		return this.orderDAO.updateOrderStatus(orderId, nextOrderStatus,operatePerson);
	}

	@SuppressWarnings("unchecked")
	public List listOrdersByStatus(String orderStatus) {
		return this.orderDAO.listOrdersByStatus(orderStatus);
	}

	public boolean updateReceiveInfo(String orderId,String receiveUserName,String postCode,String phone,String cellPhone,String receiveUserAddress){
		return this.orderDAO.updateReceiveInfo(orderId, receiveUserName, postCode, phone, cellPhone, receiveUserAddress);
	}
	
	@SuppressWarnings("unchecked")
	public List querySendMode(String orderId){
		return this.orderDAO.querySendMode(orderId);
	}
	
	public boolean updateSendMode(String orderId,String sendModeId ,String newSendFee,String oldSendFee){
		return this.orderDAO.updateSendMode(orderId, sendModeId,newSendFee,oldSendFee);
	}
	
	@SuppressWarnings("unchecked")
	public List queryPayType(String orderId){
		return this.orderDAO.queryPayType(orderId);
	}
	
	public boolean updatePayType(String orderId,String newPayTypeId){
		return this.orderDAO.updatePayType(orderId, newPayTypeId);
	}
	
	@SuppressWarnings("unchecked")
	public Map queryUserInfo(String userId){
		return this.orderDAO.queryUserInfo(userId);
	}
	
	public boolean updateUserInfo(String userId,String realName,String sex,
			String email,String postCode,String phone,String cellPhone,String address){
		return this.orderDAO.updateUserInfo(userId, realName, sex, email, postCode, phone, cellPhone, address);
	}
	
	@SuppressWarnings("unchecked")
	public Map queryUserEmail(String userId){
		return this.orderDAO.queryUserEmail(userId);
	}
	
	public boolean updateUserEmail(String userId,String password,String newEmail){
		return this.orderDAO.updateUserEmail(userId, password, newEmail);
	}
	
	public boolean updateUserPwd(String userId,String old_password,String new_password){
		return this.orderDAO.updateUserPwd(userId, old_password, new_password);
	}
	//修改订单的状态为已付款的状态
	public boolean updateOrderStatus(String orderId){
		return this.orderDAO.updateOrderStatus(orderId);
	}
}
