package com.qzgf.application.systemManage.group.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 用户组配置��ܲ���ģ��
 * @author lsr
 * 
 */
public class GroupFacadeImpl implements GroupFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(GroupFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public List findGroupsAll(String userId) {
		List groupList = baseSqlMapDAO.queryForList("group.findGroups", userId);
		return groupList;
	}
	
	/**
	 * 新增用户组
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertGroup(HashMap map)throws Exception{
		//1.获得用户组ID
		long id=Long.parseLong((String)baseSqlMapDAO.queryForObject("group.createNewId",null));
		
		map.put("groupId", id);
		//2.插入用户组
		int st=baseSqlMapDAO.update("group.insertGroup", map);
		if(st==1){
			//3.插入角色
			@SuppressWarnings("unused")
			List roles=(List)map.get("roles");
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				Map map1=new HashMap();
				map1.put("groupId", id);
				map1.put("roleId", it.next());
				
				baseSqlMapDAO.update("group.insertGroupRole", map1);
			}
			
			
		}
		
		return 0;
	}
	
	/**
	 * 根据用户组ID查询该用户组及角色
	 */
	@SuppressWarnings("unchecked")
	public List findGroupByGroupId(String groupId) {
		List groupList = baseSqlMapDAO.queryForList("group.findGroupByGroupId", groupId);
		if (!groupList.isEmpty() && groupList != null) { 

			return groupList;
		} else
			return null;
	}
	
	/**
	 * 更新用户组(角色)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateGroup(HashMap map) {
		
		int st=baseSqlMapDAO.update("group.updateGroup", map);
		if(st==1){
			@SuppressWarnings("unused")
			List roles=(List)map.get("roles");
			Iterator it = roles.iterator();
			 baseSqlMapDAO.update("group.deleteGroupRoleByGroupId", map);
			while (it.hasNext()) {
				Map map1=new HashMap();
				map1.put("groupId", map.get("groupId"));
				map1.put("roleId", it.next());
				baseSqlMapDAO.update("group.insertGroupRole", map1);
			}
			return true;
		}
		return false;
	}

	/**
	 * 删除用户组
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteGroupByGroupId(HashMap map) throws Exception {
		baseSqlMapDAO.update("group.deleteGroupByGroupId", map);
		baseSqlMapDAO.update("group.deleteGroupRoleByGroupId", map);
		return 1;
	}
	

	@SuppressWarnings("unchecked") 
	public List findRolesAll(String userId) {
		List roleList = baseSqlMapDAO.queryForList("group.findRoles", userId);
		return roleList;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
