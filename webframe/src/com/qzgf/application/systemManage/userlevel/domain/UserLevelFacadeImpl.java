package com.qzgf.application.systemManage.userlevel.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 用户等级持久层实现类
 * @author lsr
 *
 */
public class UserLevelFacadeImpl implements UserLevelFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(UserLevelFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public List findUserLevel(HashMap map){
		List userLevelList = baseSqlMapDAO.queryForList("UserLevel.findUserLevel", map);
		return userLevelList;
	}
	
	/**
	 * 插入新用户级别
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertUserLevel(HashMap map)throws Exception{
		int st=0;
		st = baseSqlMapDAO.update("UserLevel.insertUserLevel", map);
		return st;
	}

	@SuppressWarnings("unchecked")
	public int deleteUserLevelById(HashMap map) {
		int num = baseSqlMapDAO.update("UserLevel.deleteUserLevelById", map);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public boolean updateUserLevelById(HashMap map) {
		int num = baseSqlMapDAO.update("UserLevel.updateUserLevelById", map);
		if (num == 1) {
			// 修改成功
			return true;
		}else{
			// 修改失败
			return false;
		}
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
