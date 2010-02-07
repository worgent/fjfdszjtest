package com.qzgf.NetStore.dao;

import java.util.List;
import java.util.Map;


import com.qzgf.NetStore.persistence.Role;
import com.qzgf.NetStore.pub.Page;

public interface IRoleDAO {
	@SuppressWarnings("unchecked")
	/** 
	 * 返回所有的菜单
	 * @return
	 */
	public Page queryRoles(int npage);
	
	public List queryRoles();
	
	@SuppressWarnings("unchecked")
	/**
	 * 根据厂家ID号查询这个菜单的信息,可用做修改用
	 * @param id
	 * @return
	 */
	public Map queryRoleById(String id);
	/**
	 * 更新某个菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean updateRole(Role mfr);
	/**
	 * 添加某一菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean addRole(Role mfr);
	/**
	 * 根据编号删除某一菜单
	 * @param id
	 * @return
	 */
	public boolean deleteRole(String id);
}
