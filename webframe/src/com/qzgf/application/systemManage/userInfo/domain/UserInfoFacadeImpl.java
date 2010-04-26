package com.qzgf.application.systemManage.userInfo.domain;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 地图日志持久层实现类
 * @author lsr
 * 
 */
public class UserInfoFacadeImpl implements UserInfoFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(UserInfoFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public List findUserInfoById(String userId) {
		List twitterList = baseSqlMapDAO.queryForList("UserInfoTag.findUserInfoById", userId);
		if (!twitterList.isEmpty() && twitterList != null) { 
			return twitterList;
		} else
			return null;
	}
	
	/**
	 * 根据一些条件查询符合条件的用户级别
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findUserLevelById(Map map) {
		return (String)baseSqlMapDAO.queryForObject("UserInfoTag.findUserLevelById", map);
	}

	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}   
