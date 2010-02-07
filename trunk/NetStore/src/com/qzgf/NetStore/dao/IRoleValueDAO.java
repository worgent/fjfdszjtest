package com.qzgf.NetStore.dao;

import java.util.List;
import java.util.Map;


import com.qzgf.NetStore.persistence.RoleValue;

public interface IRoleValueDAO {
	@SuppressWarnings("unchecked")
	/** 
	 * 返回所有的菜单
	 * @return
	 */
	public List queryRoleValues(String roleid);
	
	public List queryChileRoleValues(String roleid,String root);
	
	@SuppressWarnings("unchecked")
	/**
	 * 根据厂家ID号查询这个菜单的信息,可用做修改用
	 * @param id
	 * @return
	 */
	public Map queryRoleValueById(Integer id);
	/**
	 * 更新某个菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean updateRoleValue(RoleValue mfr);
	/**
	 * 添加某一菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean addRoleValue(RoleValue mfr);
	/**
	 * 根据编号删除某一菜单
	 * @param id
	 * @return
	 */
	public boolean deleteRoleValue(Integer id);
	
	/**
	 * 根据某一角色的权限值
	 * @param id
	 * @return
	 */	
	public boolean deleteAllRoleValue(String rname,String rid);
}
