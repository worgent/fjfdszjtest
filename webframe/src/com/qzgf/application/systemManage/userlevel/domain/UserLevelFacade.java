package com.qzgf.application.systemManage.userlevel.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 用户等级持久层接口
 * @author lsr
 *
 */
public interface UserLevelFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	
	/**
	 * 查询所有的用户等级
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findUserLevel(HashMap map);
	
	/**
	 * 插入新用户级别
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertUserLevel(HashMap map)throws Exception;
	
	@SuppressWarnings("unchecked")
	public int deleteUserLevelById(HashMap map);
	
	@SuppressWarnings("unchecked")
	public boolean updateUserLevelById(HashMap map);
}