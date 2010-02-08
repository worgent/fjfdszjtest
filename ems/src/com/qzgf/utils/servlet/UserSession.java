package com.qzgf.utils.servlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private String userId = "";//用户编号
	private String userName = "";//用户名称
	
	@SuppressWarnings("unchecked")
	private Map userPermission = new HashMap();//权限列表

	public UserSession() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 设置权限列表
	 * @param permissionMap
	 */
	@SuppressWarnings("unchecked")
	public void setUserPermissionArray(Map permissionMap) {
		Set pset = permissionMap.entrySet();
		Iterator it = pset.iterator();
		while (it.hasNext()) {
			Map.Entry p = (Map.Entry) it.next();
			Map permission = (Map) p.getValue();
			String[] actions = ((String)permission.get("ACTION")).split(",");
			String permissionResource = ((String)permission.get("PERMISSIONRESOURCE"));
			for (int i = 0; i < actions.length; i++) {
				this.getUserPermission().put( permissionResource+ "?action=" + actions[i], p.getValue());
			}
		}

	}

	@SuppressWarnings("unchecked")
	public Map getUserPermission() {
		return userPermission;
	}

	@SuppressWarnings("unchecked")
	public void setUserPermission(Map userPermission) {
		this.userPermission = userPermission;
	}

	
	
	
}
