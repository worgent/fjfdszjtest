package com.qzgf.application.systemManage.login.dto;

public class MenuInfo {
	private String id;
	private String menuName;
	private String url;
	private String action;
	private String state;
	private String parentId;
	private String menuOrder;
	private String haveSub;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getHaveSub() {
		return haveSub;
	}
	public void setHaveSub(String haveSub) {
		this.haveSub = haveSub;
	}
	
	
}	
