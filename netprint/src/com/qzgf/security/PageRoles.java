package com.qzgf.security;

public class PageRoles {
	private String pageUrl;
	private String role;
	public PageRoles(String pageUrl,String role){
		this.pageUrl = pageUrl;
		this.role = role;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
