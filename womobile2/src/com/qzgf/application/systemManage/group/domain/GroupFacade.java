package com.qzgf.application.systemManage.group.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 用户组配置接口
 * @author lsr
 *
 */
public interface GroupFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 取得所有的用户组列表
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findGroupsAll(String userId);

	/**
	 * 新增用户组
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertGroup(HashMap map)throws Exception;

	/**
	 * 根据用户组ID查询该用户组及角色
	 */
	@SuppressWarnings("unchecked")
	public List findGroupByGroupId(String groupId);

	/**
	 * 更新用户组(角色)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateGroup(HashMap map);

	/**
	 * 删除用户组
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteGroupByGroupId(HashMap map) throws Exception;
	
	/**
	 * 取得所有角色列表
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findRolesAll(String userId);

}