package net.trust.security;

public class MethodRoles {
	private String method;
	private String role;
	public MethodRoles(String method,String role){
		this.method = method;
		this.role = role;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
