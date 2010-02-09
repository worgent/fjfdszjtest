package net.trust.application.systemManage.role.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.security.AuthoritiesMethods;
import net.trust.security.AuthoritiesPages;

public class RoleFacadeImpl implements RoleFacade{
	private Log log = LogFactory.getLog(RoleFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	private AuthoritiesMethods authoritiesMethods;
	private AuthoritiesPages authoritiesPages;

	/**
	 * 查询角色总记录信息
	 * @param map
	 * @return
	 */
    public int findRoleCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("RoleManage.findRoleCount",map)).intValue();
    }
    /**
	 * 查询角色信息
	 * @param map
	 * @return
	 */
    public List findRoleInfo(HashMap map){
    	return baseSqlMapDAO.queryForList("RoleManage.findRoleInfo",map);
    }
    /**
	 * 创角色信息
	 * @param map
	 * @return
	 */
    public int insertRole(HashMap map){
    	return baseSqlMapDAO.update("RoleManage.insertRole",map);
    }
    /**
	 * 修改角色信息
	 * @param map
	 * @return
	 */
	public int updateRoleInfo(HashMap map){
		return baseSqlMapDAO.update("RoleManage.updateRole",map);
	}
	
	/**
	 * 删除角色信息
	 * @param map
	 * @return
	 */
	public int deleteRoleInfo(HashMap map){
		int st = baseSqlMapDAO.update("RoleManage.deleteRole",map);
		if(log.isDebugEnabled()){
			log.debug("重新加载权限配置开始");
		}
		authoritiesPages.loadPageRole();
		authoritiesMethods.loadMethodRole();
		if(log.isDebugEnabled()){
			log.debug("重新加载权限配置结束");
		}
		return st;
	
	}
	
	/**
	 * 获取当前人员所捅有的权限树
	 * @param map
	 * @return
	 */
	public List findMenuTree(HashMap map){
		return baseSqlMapDAO.queryForList("RoleManage.findMenuTree", map);
	}
	
	/**
	 * 角色权限分配
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public int insertRoleMenu(String[] CheckData,String roleId, String superId){
		HashMap param = new HashMap();
		
		//分配角色权限前先删除原有的记录
		param.put("roleId", roleId);
		param.put("superId", superId);
		baseSqlMapDAO.delete("RoleManage.deleteRoleMenu", param);
		
		if(null == CheckData || CheckData.length==0)
			return 1;

		//将字符串数组中的值添加到库中
		for (int i=0;i<CheckData.length;i++){
			param = new HashMap();
			param.put("menuId", CheckData[i]);
			param.put("roleId", roleId);
			baseSqlMapDAO.insert("RoleManage.insertRoleMenu", param);
		}
		
//		String[] menuId = menuIds.split(",");
//		HashMap map = new HashMap();
//		
//		//将menuId每个数据前后各加上'号
//		menuIds = "";
//		for (int i=0; i<menuId.length; i++){
//			if (!"".equals(menuIds)){
//				menuIds += ",";
//			}
//			menuIds += "'" + menuId[i] + "'";
//		}
//		map.put("menuIds", menuIds);
//		map.put("roleId", roleId);
//		//清除权限
//		baseSqlMapDAO.delete("RoleManage.deleteRoleMenu", map);
//		//创建权限
//		baseSqlMapDAO.insert("RoleManage.insertRoleMenu", map);

		if(log.isDebugEnabled()){
			log.debug("重新加载权限配置开始");
		}
		authoritiesPages.loadPageRole();
		authoritiesMethods.loadMethodRole();
		if(log.isDebugEnabled()){
			log.debug("重新加载权限配置结束");
		}
		
		return 1;
	}
	
	public List execMenuTreeProc(HashMap map){
		return baseSqlMapDAO.queryForList("RoleManage.findMenuTreeProc", map);
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	public AuthoritiesMethods getAuthoritiesMethods() {
		return authoritiesMethods;
	}
	public void setAuthoritiesMethods(AuthoritiesMethods authoritiesMethods) {
		this.authoritiesMethods = authoritiesMethods;
	}
	public AuthoritiesPages getAuthoritiesPages() {
		return authoritiesPages;
	}
	public void setAuthoritiesPages(AuthoritiesPages authoritiesPages) {
		this.authoritiesPages = authoritiesPages;
	}
}
