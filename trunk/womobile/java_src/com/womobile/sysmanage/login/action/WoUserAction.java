/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.sysmanage.login.action;

import org.apache.commons.lang.StringUtils;
import cn.org.rapid_framework.web.scope.Flash;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;
import java.util.*;
import javacommon.base.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import com.womobile.sysmanage.login.model.*;
import com.womobile.sysmanage.login.service.*;
import com.womobile.sysmanage.login.vo.query.*;

/**
 * @author lsr
 * @version 1.0
 * @since 1.0
 */

public class WoUserAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LOGIN_HTML = "/login.jsp";
	protected static final String MAIN_JSP = "/main.jsp";
	protected static final String QUERY_JSP = "/sys/WoUser/query.jsp";
	protected static final String LIST_JSP= "/sys/WoUser/list.jsp";
	protected static final String CREATE_JSP = "/sys/WoUser/create.jsp";
	protected static final String EDIT_JSP = "/sys/WoUser/edit.jsp";
	protected static final String SHOW_JSP = "/sys/WoUser/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/sys/WoUser/list.do";
	
	private WoUserManager woUserManager;
	private String username;
	private String passwd;
	private WoUser woUser;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			woUser = new WoUser();
		} else {
			woUser = (WoUser)woUserManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setWoUserManager(WoUserManager manager) {
		this.woUserManager = manager;
	}	
	
	public Object getModel() {
		return woUser;
	}
	
	public void setUserId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public String login(){
		
		if (StringUtils.isBlank(this.getUsername()) || StringUtils.isBlank(this.getPasswd())) {
			this.addActionError("用户名或密码为空");
			return LOGIN_HTML;
		}
		return MAIN_JSP;
	}
	
	/** 执行搜索 */
	public String list() {
		WoUserQuery query = newQuery(WoUserQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = woUserManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		woUserManager.save(woUser);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		woUserManager.update(this.woUser);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("userId"));
			woUserManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	

}
