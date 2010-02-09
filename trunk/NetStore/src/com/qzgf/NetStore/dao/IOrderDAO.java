package com.qzgf.NetStore.dao;

import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.pub.Page;

public interface IOrderDAO {
	@SuppressWarnings("unchecked")
	public List listOrdersByStatus(String orderStatus);
	@SuppressWarnings("unchecked")
	public Map listOrderUserInfo(String orderId);
	@SuppressWarnings("unchecked")
	public List listOrderDetails(String orderId);
	public boolean updateOrderStatus(String orderId,String nextOrderStatus,String operatePerson);
	@SuppressWarnings("unchecked")
	public List queryAllPayType();
	@SuppressWarnings("unchecked")
	public Page queryOrders(String orderId,String orderStatus,String userId,String allSum,String payTypeId,String beginDay,String endDay,int npage);
	@SuppressWarnings("unchecked")
	public List queryOperateHistory(String orderId);
	@SuppressWarnings("unchecked")
	public List queryOrdersByCondition(String condition,String userid);
	public boolean updateReceiveInfo(String orderId,String receiveUserName,String postCode,String phone,String cellPhone,String receiveUserAddress);
	@SuppressWarnings("unchecked")
	public List querySendMode(String orderId);
	public boolean updateSendMode(String orderId,String sendModeId ,String newSendFee,String oldSendFee);
	@SuppressWarnings("unchecked")
	public List queryPayType(String orderId);
	public boolean updatePayType(String orderId,String newPayTypeId);
	@SuppressWarnings("unchecked")
	public Map queryUserInfo(String userId);
	public boolean updateUserInfo(String userId,String realName,String sex,
			String email,String postCode,String phone,String cellPhone,String address);
	@SuppressWarnings("unchecked")
	public Map queryUserEmail(String userId);
	public boolean updateUserEmail(String userId,String password,String newEmail);
	public boolean updateUserPwd(String userId,String old_password,String new_password);
	public boolean updateOrderStatus(String orderId);
}
