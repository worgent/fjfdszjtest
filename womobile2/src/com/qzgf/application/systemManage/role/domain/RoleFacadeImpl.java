package com.qzgf.application.systemManage.role.domain;

import java.util.HashMap;
import java.util.Iterator;
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
public class RoleFacadeImpl implements RoleFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(RoleFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked") 
	public List findRolesAll(String userId) {
		List roleList = baseSqlMapDAO.queryForList("role.findRoles", userId);
		return roleList;
	}
	
	/**
	 * 新增角色
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertRole(HashMap map)throws Exception{
		//1.获得角色ID
		String id=(String)baseSqlMapDAO.queryForObject("role.createNewId",null);
		
		map.put("roleId", id);
		//2.插入角色
		int st=baseSqlMapDAO.update("role.insertRole", map);
		if(st==1){
			
			@SuppressWarnings("unused")
			List permissions=(List)map.get("permissions");
			Iterator it = permissions.iterator();
			while (it.hasNext()) {
				Map map1=new HashMap();
				map1.put("roleId", id);
				map1.put("permissionId", it.next());
				//3.插入该角色的权限列表
				baseSqlMapDAO.update("role.insertRolePermission", map1);
			}
		}
		
		return 0;
	}
	
	/**
	 * 根据角色ID查询该角色及权限
	 */
	@SuppressWarnings("unchecked")
	public List findRoleByRoleId(String roleId) {
		List roleList = baseSqlMapDAO.queryForList("role.findRoleByRoleId", roleId);
		if (!roleList.isEmpty() && roleList != null) { 

			return roleList;
		} else
			return null;
	}
	
	/**
	 * 更新角色(权限)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateRole(HashMap map) {
		
		int st=baseSqlMapDAO.update("role.updateRole", map);
		if(st==1){
			@SuppressWarnings("unused")
			List permissions=(List)map.get("permissions");
			Iterator it = permissions.iterator();
			 baseSqlMapDAO.update("role.deleteRPByRoleId", map);
			while (it.hasNext()) {
				Map map1=new HashMap();
				map1.put("roleId", map.get("roleId"));
				map1.put("permissionId", it.next());
				baseSqlMapDAO.update("role.insertRolePermission", map1);
			}
			return true;
		}
		return false;
	}

	/**
	 * 删除角色
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteRoleByRoleId(HashMap map) throws Exception {
		baseSqlMapDAO.update("role.deleteRoleByRoleId", map);
		baseSqlMapDAO.update("role.deleteRPByRoleId", map);
		return 1;
	}
	
	/**
	 * 加载该角色的权限
	 */
	@SuppressWarnings("unchecked")
	public List findMenuByUserId(HashMap map) {
		List permissionList = baseSqlMapDAO.queryForList("role.findMenuByUserID", map);
		return permissionList;
	}
	
	/**
	 * 加载所有的权限
	 */
	@SuppressWarnings("unchecked")
	public List findAllPermission(String userId) {
		Map map=new HashMap();
		map.put("userId", userId);
		List permissionList = baseSqlMapDAO.queryForList("role.findAllPermission", map);
		return permissionList;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
