package net.trust.application.systemManage.role.domain;

import java.util.HashMap;
import java.util.List;

public interface RoleFacade {
	/**
	 * 查询角色总记录信息
	 * @param map
	 * @return
	 */
    public int findRoleCount(HashMap map);
    /**
	 * 查询角色信息
	 * @param map
	 * @return
	 */
    public List findRoleInfo(HashMap map);
    /**
	 * 添加角色信息
	 * @param map
	 * @return
	 */
    public int insertRole(HashMap map);
    /**
	 * 修改角色信息
	 * @param map
	 * @return
	 */
	public int updateRoleInfo(HashMap map);
	
	/**
	 * 删除角色信息
	 * @param map
	 * @return
	 */
	public int deleteRoleInfo(HashMap map);
	/**
	 * 获取当前人员所捅有的权限树
	 * @param map
	 * @return
	 */
	public List findMenuTree(HashMap map);
	
	/**
	 * 角色权限分配
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public int insertRoleMenu(String[] CheckData,String roleId, String superId);
	public List execMenuTreeProc(HashMap map);
}
