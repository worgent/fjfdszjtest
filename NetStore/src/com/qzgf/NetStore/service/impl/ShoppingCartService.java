package com.qzgf.NetStore.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IShoppingCartDAO;
import com.qzgf.NetStore.persistence.Item;
import com.qzgf.NetStore.persistence.User;
import com.qzgf.NetStore.service.IShoppingCartService;

public class ShoppingCartService implements IShoppingCartService {
    private IShoppingCartDAO shoppingCartDAO;
	@SuppressWarnings("unchecked")
	@Override
	public Map listProducts() {
		return this.shoppingCartDAO.listProducts();
	}
	public IShoppingCartDAO getShoppingCartDAO() {
		return shoppingCartDAO;
	}
	public void setShoppingCartDAO(IShoppingCartDAO shoppingCartDAO) {
		this.shoppingCartDAO = shoppingCartDAO;
	}
	@SuppressWarnings("unchecked")
	public Map listOrderUserInfo(String userId){
		return this.shoppingCartDAO.listOrderUserInfo(userId);
	}
	public boolean updateUserRes(User user){
		return this.shoppingCartDAO.updateUserRes(user);
	}
	public boolean updateUserResSed(User user){
		return this.shoppingCartDAO.updateUserResSed(user);
	}
	@SuppressWarnings("unchecked")
	public List querySendMode(String userId){
		return this.shoppingCartDAO.querySendMode(userId); 
	}
	@SuppressWarnings("unchecked")
	public List queryPayType(String userId){
		return this.shoppingCartDAO.queryPayType(userId);
	}
	@SuppressWarnings("unchecked")
	public Map listSendInfo(String orderId){  
		return this.shoppingCartDAO.listSendInfo(orderId);
	}
	public boolean updateSendPayMode(String userId,String sendModeId,String payTypeId){
		return this.shoppingCartDAO.updateSendPayMode(userId, sendModeId, payTypeId);
	}
	public boolean insertOrderDetails(HashSet<Item> items,String userId,float cost,String orderId){
		return this.shoppingCartDAO.insertOrderDetails(items, userId, cost,orderId);
	}
	@SuppressWarnings("unchecked")
	public Map listPayInfo(String orderId){
		return this.shoppingCartDAO.listPayInfo(orderId);
	}
	public String queryAllSum(String orderId){
		return this.shoppingCartDAO.queryAllSum(orderId);
	}
	public float getProductPrice(String productId){
		return this.shoppingCartDAO.getProductPrice(productId);
	}
	public float getSendFee(String userId){
		return this.shoppingCartDAO.getSendFee(userId);
	}
}
