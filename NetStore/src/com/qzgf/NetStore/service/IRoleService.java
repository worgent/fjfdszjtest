package com.qzgf.NetStore.service;

import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.persistence.Role;
import com.qzgf.NetStore.pub.Page;

public interface IRoleService {
	/**
	 * 菜单列表
	 */
	public Page queryRoles(int npage);
	
	public List queryRoles();
	/**
	 * 根据id得到菜单信息
	 */
	public Map queryRoleById(String id);
	/**
	 * 更新菜单
	 * @param Role
	 * @return
	 */
	public boolean updateRole(Role role);
	/**
	 * 由id号删除菜单
	 * @param id
	 * @return
	 */
	public boolean deleteRoleByID(String id);
	/**
	 * 增加菜单
	 * @param Role
	 * @return
	 */
	public boolean addRole(Role role);
}
