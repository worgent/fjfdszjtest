package com.qzgf.application.systemManage.userInfo.domain;

import java.util.List;
import java.util.Map;


public interface UserInfoFacade {
	@SuppressWarnings("unchecked")
	public List findUserInfoById(String userId);
	
	/**
	 * 根据一些条件查询符合条件的用户级别
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findUserLevelById(Map map);
}