/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.company.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.company.test.model.*;
import com.company.test.dao.*;
import com.company.test.service.*;
import com.company.test.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class UserInfoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/test/UserInfo/query.jsp";
	protected static final String LIST_JSP= "/test/UserInfo/list.jsp";
	protected static final String CREATE_JSP = "/test/UserInfo/create.jsp";
	protected static final String EDIT_JSP = "/test/UserInfo/edit.jsp";
	protected static final String SHOW_JSP = "/test/UserInfo/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/test/UserInfo/list.do";
	
	private UserInfoManager userInfoManager;
	
	private UserInfo userInfo;
	java.lang.Long id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			userInfo = new UserInfo();
		} else {
			userInfo = (UserInfo)userInfoManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUserInfoManager(UserInfoManager manager) {
		this.userInfoManager = manager;
	}	
	
	public Object getModel() {
		return userInfo;
	}
	
	public void setUserId(java.lang.Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		UserInfoQuery query = newQuery(UserInfoQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = userInfoManager.findPage(query);
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
		userInfoManager.save(userInfo);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		userInfoManager.update(this.userInfo);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Long id = new java.lang.Long((String)params.get("userId"));
			userInfoManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

}
