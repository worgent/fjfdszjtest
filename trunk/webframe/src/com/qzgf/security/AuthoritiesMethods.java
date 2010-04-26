package com.qzgf.security;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import com.qzgf.security.dao.jdbc.JdbcDaoImpl;

public class AuthoritiesMethods {
	
	private Log log = LogFactory.getLog(AuthoritiesMethods.class);
	//需要进行权限判断的页面及所对应的角色
	@SuppressWarnings("unchecked")
	private Map authoritiesMethods = new HashMap();

	private JdbcDaoImpl daoImpl ;
	
	public AuthoritiesMethods(){
	}

	public void setAuthoritiesMethodList(String list){
		setAuthoritiesMethods(parsetList(list,true));
	}
	@SuppressWarnings({ "unchecked", "unchecked" })
	public void loadMethodRole(){
		java.util.List list = daoImpl.getMethodRoleData();
		//把数据按method这key，roles为value的方式存放在authoritiesMethods,多个角色之间用[,]分割
		int len = list.size();
		MethodRoles pr1 = null;
		String method = null;
		StringBuffer roles = new StringBuffer();
		for(int i = 0; i<len; i++ ){
			pr1 = (MethodRoles)list.get(i);
			if(null == method || !method.equals(pr1.getMethod())){
				if(method!=null){
					//int idx = method.indexOf("?");
					//if(idx > -1){
					//	method =method.substring(0,idx);
					//}
					authoritiesMethods.put(method,roles.toString());
				}
				method = pr1.getMethod();
				roles = null;
			}
			if(roles == null)
				roles = new StringBuffer();
			roles.append(pr1.getRole()).append(",");
		}
		if(log.isDebugEnabled()){
			log.debug("需要权限判断的method:"+authoritiesMethods);
		}
	}
	public JdbcDaoImpl getDaoImpl() {
		return daoImpl;
	}
	public void setDaoImpl(JdbcDaoImpl daoImpl) {
		this.daoImpl = daoImpl;
		loadMethodRole();
	}
	@SuppressWarnings({ "unchecked", "unchecked" })
	private Map parsetList(String str,boolean b){
		HashMap map = new HashMap();
		String[] tmp = StringUtils.tokenizeToStringArray(str, "; \t\n");
		String[] tmp2 = null;
		for(int i=0;i<tmp.length;i++){
			if(b){
				tmp2 = StringUtils.tokenizeToStringArray(tmp[i],"=");
				if(!tmp2[1].trim().endsWith(","))
					tmp2[1]=tmp2[1].trim()+",";
				map.put(tmp2[0],tmp2[1]);
			}else{
				map.put(tmp[i],"");
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map getAuthoritiesMethods() {
		return authoritiesMethods;
	}

	@SuppressWarnings("unchecked")
	public void setAuthoritiesMethods(Map authoritiesMethods) {
		this.authoritiesMethods = authoritiesMethods;
	}
}
