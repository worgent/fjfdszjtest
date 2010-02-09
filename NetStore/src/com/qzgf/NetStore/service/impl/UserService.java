package com.qzgf.NetStore.service.impl;

import java.util.List;

import com.qzgf.NetStore.dao.IUserDAO;
import com.qzgf.NetStore.persistence.User;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.IUserService;


public class UserService implements IUserService {
	public boolean checkUserId(String userid) {
		return this.userDAO.checkUserId(userid);
	}
	private IUserDAO userDAO;
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	//沈伟庆０８.０８.２７
	@SuppressWarnings("unchecked")
	public Page userList(int npage)
	{ 
		return this.userDAO.userList(npage);
	}
	
	@SuppressWarnings("unchecked")
	public  List  onlyUserList(String uId)
	{
		return this.userDAO.onlyUserList(uId);
	}
	
	public void updateUsable(String state,String id){
		this.userDAO.updateUsable(state, id);
	}
	
	public boolean userRegister(User user){
		return this.userDAO.userRegister(user);
	}
	//根据userId获得该用户信息
	public User login(String userId){
		return this.userDAO.login(userId);
	}
	
}
