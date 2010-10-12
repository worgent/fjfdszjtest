package com.womobile.dao;

import com.womobile.entity.User;


public interface UserDao {
	public User login(String username,String password);
}
