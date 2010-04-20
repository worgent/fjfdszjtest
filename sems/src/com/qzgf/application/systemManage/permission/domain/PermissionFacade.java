package com.qzgf.application.systemManage.permission.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/** 
 * 权限配置接口
 * @author lsr
 *
 */
public interface PermissionFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 取得所有Permission列表
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findPermissionsAll();
	
	/**
	 * 添加权限
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public long insertPermission(HashMap map)throws Exception;
	
	@SuppressWarnings("unchecked")
	public List findSubPermissionById(String id);
	
	/**
	 * 删除某一权限
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deletePermission(String id) throws Exception;
	
	//判断是否有下一级菜单
	@SuppressWarnings("unchecked")
	public int checkSubPermissionById(String id);

}