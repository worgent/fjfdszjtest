package com.qzgf.application.systemManage.role.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 角色配置接口
 * @author lsr
 *
 */
public interface RoleFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 取得所有角色列表
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findRolesAll(String userId);

	/**
	 * 新增角色
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertRole(HashMap map) throws Exception;

	/**
	 * 根据角色ID查询该角色及权限
	 */
	@SuppressWarnings("unchecked")
	public List findRoleByRoleId(String roleId);

	/**
	 * 更新角色(权限)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateRole(HashMap map);

	/**
	 * 删除角色
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteRoleByRoleId(HashMap map) throws Exception;
	
	/**
	 * 加载该角色的权限
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findMenuByUserId(HashMap map);
	
	/**
	 * 加载所有的权限
	 */
	@SuppressWarnings("unchecked")
	public List findAllPermission(String userId);

}