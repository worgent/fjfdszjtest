package com.qzgf.NetStore.service;

import java.util.List;

import com.qzgf.NetStore.persistence.User;
import com.qzgf.NetStore.pub.Page;

public interface IUserService {
	public boolean checkUserId(String userid);

	// 沈伟庆08.08.27
	@SuppressWarnings("unchecked")
	public Page userList(int npage);

	@SuppressWarnings("unchecked")
	public List onlyUserList(String uId);

	public void updateUsable(String state, String id);

	// 沈伟庆08.08.27
	public boolean userRegister(User user);

	// 根据userId获得该用户信息
	public User login(String userId);

}
