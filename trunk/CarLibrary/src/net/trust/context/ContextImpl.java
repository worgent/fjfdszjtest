package net.trust.context;

import net.trust.application.systemManage.manager.dto.UserInfo;


public class ContextImpl implements Context{
	private static final long serialVersionUID = 1L;
	private UserInfo userInfo;
	public ContextImpl(){}

	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("UserNo:").append(userInfo.getStaffNo());
		str.append(";IP:").append(userInfo.getIp());
		return str.toString();
	}
	public boolean equals(Object obj){
		return true;
	}
	public void validate(){
		
	}
}
