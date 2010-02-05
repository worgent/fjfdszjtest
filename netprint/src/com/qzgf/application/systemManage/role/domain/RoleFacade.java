package com.qzgf.application.systemManage.role.domain;

import java.util.HashMap;
import java.util.List;

public interface RoleFacade {
	/**
	 * 查询角色总记录信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findRoleCount(HashMap map);

	/**
	 * 查询角色信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findRoleInfo(HashMap map);

	/**
	 * 添加角色信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertRole(HashMap map);

	/**
	 * 修改角色信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateRoleInfo(HashMap map);

	/**
	 * 删除角色信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteRoleInfo(HashMap map);

	/**
	 * 获取当前人员所捅有的权限树
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findMenuTree(HashMap map);

	/**
	 * 角色权限分配
	 * @param menuId
	 * @param roleId
	 * @return
	 */
//	public int insertRoleMenu(String menuIds, String roleId);
//	
	/**
	 * 角色权限分配
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public int insertRoleMenu(String[] CheckData,String roleId, String superId);
	
	/**
	 * 菜单树结构
	 * @param map
	 * @return
	 */
	public List execMenuTreeProc(HashMap map);

}
