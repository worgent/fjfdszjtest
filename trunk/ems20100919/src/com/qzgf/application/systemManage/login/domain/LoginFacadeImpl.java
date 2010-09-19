package com.qzgf.application.systemManage.login.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.utils.servlet.UserSession;

/**
 * 登录��ܲ���ģ��
 * @author lsr
 * 
 */
public class LoginFacadeImpl implements LoginFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(LoginFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public UserInfo findUserInfoByName(String username){
		return (UserInfo)baseSqlMapDAO.queryForObject("Login.findUserInfoByName", username);
	}
	
	@SuppressWarnings("unchecked")
	public UserSession getUserSession(UserInfo ui){
		UserSession us = new UserSession();
		us.setUserId(ui.getUserId()); 
		us.setUserName(ui.getUserName());
		//往session里面设置权限
		Map permissionMap = this.getUserPermission(ui.getGroupId());
		us.setUserPermissionArray(permissionMap);
		return us;
	}
	
	@SuppressWarnings("unchecked")
	public Map getUserPermission(String groupID) {
		Map userPermission = new HashMap();
		//获得该用户组的权限列表
		List permissionList=this.findPermissions(groupID); 
			for (int i = 0; i < permissionList.size(); i++) {
				Map permission = (Map) permissionList.get(i);
				userPermission.put(permission.get("PERMISSIONRESOURCE") + "," + permission.get("ACTION"), permission);
			}
		return userPermission;
	} 
	
	@SuppressWarnings("unchecked")
	public List findMenuByUserId(String userID) {
		List permissionList = baseSqlMapDAO.queryForList("Login.findMenuByUserID", userID);
		return permissionList;
	}
	
	
	

	/**
	 * 查询用户权限列表
	 */
	@SuppressWarnings("unchecked")
	public List findPermissions(String groupId) {
		List permissionList = baseSqlMapDAO.queryForList("Login.findPermissions", groupId);
		return permissionList;
	}
	
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
