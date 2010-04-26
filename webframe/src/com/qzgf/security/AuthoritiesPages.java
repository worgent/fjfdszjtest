package com.qzgf.security;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import com.qzgf.security.dao.jdbc.JdbcDaoImpl;

public class AuthoritiesPages {

	private Log log = LogFactory.getLog(AuthoritiesPages.class);
	// 需要进行权限判断的页面及所对应的角色
	@SuppressWarnings("unchecked")
	private Map authoritiesPages = new HashMap();
	// 不需要进行会话判断的页面，如登录页面、错误页面等
	@SuppressWarnings("unchecked")
	private Map notSessionPages = new HashMap();
	// 读取需要进行权限判断的页面，就是url与role的对应
	private JdbcDaoImpl daoImpl;

	public AuthoritiesPages() {
	}

	@SuppressWarnings("unchecked")
	public Map getAuthoritiesPages() {
		return authoritiesPages;
	}

	@SuppressWarnings("unchecked")
	public void setAuthoritiesPages(Map authoritiesPages) {
		this.authoritiesPages = authoritiesPages;
	}

	public void setAuthoritiesPageList(String list) {
		setAuthoritiesPages(parsetList(list, true));
	}

	@SuppressWarnings("unchecked")
	public Map getNotSessionPages() {
		return notSessionPages;
	}

	@SuppressWarnings("unchecked")
	public void setNotSessionPages(Map notSessionPages) {
		this.notSessionPages = notSessionPages;
	}

	public void setNotSessionPageList(String list) {
		setNotSessionPages(parsetList(list, false));
	}

	@SuppressWarnings("unchecked")
	public void loadPageRole() {
		java.util.List list = daoImpl.getPageRoleData();
		// 把数据按url这key，roles为value的方式存放在authoritiesPages,多个角色之间用[,]分割
		int len = list.size();
		PageRoles pr1 = null;
		String url = null;
		StringBuffer roles = new StringBuffer();
		for (int i = 0; i < len; i++) {
			pr1 = (PageRoles) list.get(i);
			if (null == url || !url.equals(pr1.getPageUrl())) {
				if (url != null) {
					int idx = url.indexOf("?");
					if (idx > -1) {
						url = url.substring(0, idx);
					}
					authoritiesPages.put(url, roles.toString());
				}
				url = pr1.getPageUrl();
				roles = null;
			}
			if (roles == null)
				roles = new StringBuffer();
			roles.append(pr1.getRole()).append(",");
		}
		if (log.isDebugEnabled()) {
			log.debug("需要权限判断的url:" + authoritiesPages);
		}
	}

	public JdbcDaoImpl getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(JdbcDaoImpl daoImpl) {
		this.daoImpl = daoImpl;
		loadPageRole();
	}

	@SuppressWarnings("unchecked")
	private Map parsetList(String str, boolean b) {
		HashMap map = new HashMap();
		String[] tmp = StringUtils.tokenizeToStringArray(str, "; \t\n");
		String[] tmp2 = null;
		for (int i = 0; i < tmp.length; i++) {
			if (b) {
				tmp2 = StringUtils.tokenizeToStringArray(tmp[i], "=");
				if (!tmp2[1].trim().endsWith(","))
					tmp2[1] = tmp2[1].trim() + ",";
				map.put(tmp2[0], tmp2[1]);
			} else {
				map.put(tmp[i], "");
			}
		}
		return map;
	}
}
