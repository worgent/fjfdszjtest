package com.qzgf.NetStore.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.persistence.Item;
import com.qzgf.NetStore.persistence.User;

public interface IShoppingCartDAO {
	@SuppressWarnings("unchecked")
	public Map listProducts();
	@SuppressWarnings("unchecked")
	public Map listOrderUserInfo(String userId);
	public boolean updateUserRes(User user);
	public boolean updateUserResSed(User user);
	@SuppressWarnings("unchecked")
	public List querySendMode(String userId);
	@SuppressWarnings("unchecked")
	public List queryPayType(String userId);
	@SuppressWarnings("unchecked")
	public Map listSendInfo(String orderId);
	public boolean updateSendPayMode(String userId,String sendModeId,String payTypeId);
	public boolean insertOrderDetails(HashSet<Item> items,String userId,float cost,String orderId);
	@SuppressWarnings("unchecked")
	public Map listPayInfo(String orderId);
	public String queryAllSum(String orderId);
	public float getProductPrice(String productId);
	public float getSendFee(String userId);
}
