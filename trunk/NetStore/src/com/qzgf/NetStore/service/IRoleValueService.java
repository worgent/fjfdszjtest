package com.qzgf.NetStore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.persistence.RoleValue;


public interface IRoleValueService {
	/**
	 * 菜单列表
	 */
	public List queryRoleValues(String rid);
	/**
	 * 根据id得到菜单信息
	 */
	public Map queryRoleValueById(Integer id);
	/**
	 * 更新菜单
	 * @param RoleValue
	 * @return
	 */
	public boolean updateRoleValue(RoleValue roleValue);
	/**
	 * 由id号删除菜单
	 * @param id
	 * @return
	 */
	public boolean deleteRoleValueByID(Integer id);
	/**
	 * 增加菜单
	 * @param RoleValue
	 * @return
	 */
	public boolean addRoleValue(RoleValue roleValue);
	/**
	 * 功能增加多结点
	 * @param roldInfo
	 * @param num
	 * @param expediteCarId
	 * @param roadType
	 */
	public boolean updateRoleValuesShow(String[]date,String rid);
	/**
	 * 生成动态树
	 * @param id
	 * @return
	 */
	public String getTree(String rid,String root);
}
