package com.qzgf.application.systemManage.permission.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 权限配置
 * @author lsr
 * 
 */
public class PermissionFacadeImpl implements PermissionFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(PermissionFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	

	@SuppressWarnings("unchecked")
	public List findPermissionsAll() {
		List permissionList = baseSqlMapDAO.queryForList("permission.findPermissions", "");
		return permissionList;
	}
	
	@SuppressWarnings("unchecked")
	public List findSubPermissionById(String id) {
		List permissionList = baseSqlMapDAO.queryForList("permission.findSubPermissionById", id);
		return permissionList;
	}
	
	//判断是否有下一级菜单
	@SuppressWarnings("unchecked")
	public int checkSubPermissionById(String id) {
		return ((Integer)baseSqlMapDAO.queryForObject("permission.checkSubPermissionById",id)).intValue();
	}
	
	@SuppressWarnings("unchecked") 
	public String insertPermission(HashMap map)throws Exception{ 
		//1.获得角色ID
		String id=(String)baseSqlMapDAO.queryForObject("permission.createNewId",null);
		map.put("permissionId", id);
		baseSqlMapDAO.update("permission.insertPermission", map);
		baseSqlMapDAO.update("permission.insertPermissionForAdmin", map);
		return id;
	}
	
	/**
	 * 删除某一权限
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deletePermission(String id) throws Exception {
		Map map=new HashMap();
		map.put("permissionId", id);
		
		//1.先删除角色-权限表里面的相关记录
		baseSqlMapDAO.update("permission.deleteRPById", map);
		//2.再删除权限表里面的该节点及节点下面的相关记录
		
		baseSqlMapDAO.update("permission.deletePermissionById", map);
		return 1;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	
}
