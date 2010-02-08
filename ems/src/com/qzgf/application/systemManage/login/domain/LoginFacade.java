package com.qzgf.application.systemManage.login.domain;

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

}