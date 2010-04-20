package com.qzgf.application.systemManage.login.domain;

import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.utils.servlet.UserSession;

/** 
 * 系统登录接口
 * @author lsr
 *
 */
public interface LoginFacade { 

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	
	public UserInfo findUserInfoByName(String username); 
	
	/**
	 * 获得用户Session信息
	 * @param ui
	 * @return
	 */
	public UserSession getUserSession(UserInfo ui);
	
	/**
	 * 根据用户ID,查询该用户的菜单信息
	 * @param userID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findMenuByUserId(String userID);

}