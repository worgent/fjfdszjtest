package com.qzgf.application.systemManage.menu.dto;

public class MenuInfo {
	private String menuId;  //    字符[50]	菜单标志*
	private String menuName;//	字符[50]	菜单名称
	private String menuOrder;//	数字[8]		菜单顺序
	private String    isRoot="0"	;// 	数字[1]		是否是根菜单0不是1是
	private String superId;// 	字符[50]	父菜单ID
	private String imgSrc;// 		字符[200]	图标地址
	private String title;//		字符[100]	提示
	private String haveSub = "0";// 	数字[1]		是否有下级0没有1有
	private String activeEvent;//	字符[500]	点击所要触发的事件
	private String isSeperator;//	数字[1]		分割条 1是 0不是
	private String state = "1";//		    数字[1]		状态 1可用 0不可用 
	private String isMenu;//是菜单还是功能节点
	private String url; //对应的地址
	private String method ;//对应的方法  
	private String checked ="0" ;//此属性是在给角色分配权限时使用，判断要分配的角色是否以有分配过权限
	public String getActiveEvent() {
		return activeEvent;
	}
	public void setActiveEvent(String activeEvent) {
		this.activeEvent = activeEvent;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getHaveSub() {
		return haveSub;
	}
	public void setHaveSub(String haveSub) {
		this.haveSub = haveSub;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
	public String getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}
	public String getIsSeperator() {
		return isSeperator;
	}
	public void setIsSeperator(String isSeperator) {
		this.isSeperator = isSeperator;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSuperId() {
		return superId;
	}
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}	
