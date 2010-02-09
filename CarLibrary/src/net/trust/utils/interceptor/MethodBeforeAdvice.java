package net.trust.utils.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.trust.application.systemManage.manager.dto.UserInfo;
import net.trust.context.LoginContextUtil;
import net.trust.security.AuthoritiesMethods;

public class MethodBeforeAdvice implements org.springframework.aop.MethodBeforeAdvice{
	
	private AuthoritiesMethods authoritiesMethods ;
	
	private Map authMethods;
	private void init(){
		authMethods = authoritiesMethods.getAuthoritiesMethods();
	}
	private Map getMethods(){
		if(authMethods==null)
			init();
		if(authMethods==null){
			authMethods = new HashMap();
		}
		return authMethods;
	}
	public void before(Method arg0, Object[] arg1, Object arg2) throws Exception{
		
		String method = arg0.getDeclaringClass().getName()+"."+arg0.getName();
		String role = (String)getMethods().get(method);
		if(role!=null){
			UserInfo userInfo = getLogin();
			List roles = userInfo.getRoles();
			boolean go = false;
			for(int i=0;i<roles.size();i++){
				if(role.indexOf(roles.get(i)+",") != -1){
					go = true;
					break;
				}
			}
			if(!go){
				
				throw new Exception("403");

			}
		}
		
		/*
		
		
		if(method.endsWith("Count")){
			method=method.substring(0,method.length()-5);
		}
		if(login.getPowers().indexOf((char)2+method+(char)2)==-1){
			
		}
		*/
			
		
	}
	/**
	 * 取得操作用户
	 * */
	private UserInfo getLogin(){
		UserInfo userInfo = LoginContextUtil.getLoginContext();
		return userInfo;
	}
	public AuthoritiesMethods getAuthoritiesMethods() {
		return authoritiesMethods;
	}
	public void setAuthoritiesMethods(AuthoritiesMethods authoritiesMethods) {
		this.authoritiesMethods = authoritiesMethods;
	}
}
