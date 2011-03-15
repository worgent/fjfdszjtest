package com.qzgf.application.systemManage.login.dto;

public class UserInfo {
	private String id;
	private String userId;      //用户编号(系统唯一值)
	private String userName;    //用户昵称(登录名且是唯一值)
	private String groupId;     //用户组编号(每个用户在注册时都得属于一个用户组)
	private String passwd;		//密码明文
	private String rePasswd;    //密码密文
	private String branchId;    //所属机构
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRePasswd() {
		return rePasswd;
	}
	public void setRePasswd(String rePasswd) {
		this.rePasswd = rePasswd;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
