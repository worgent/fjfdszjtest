package com.qzgf.NetStore.util.context;

import com.qzgf.NetStore.persistence.Administrator;

public class ContextImpl implements Context{
	private static final long serialVersionUID = 1L;
	private Administrator userInfo;
	public ContextImpl(){}

	public Administrator getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(Administrator userInfo) {
		this.userInfo = userInfo;
	}

	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("adminId:").append(userInfo.getAdminId());
		str.append(";realName:").append(userInfo.getRealName());
		return str.toString();
	}
	public boolean equals(Object obj){
		return true;
	}
	public void validate(){
		
	}
}
