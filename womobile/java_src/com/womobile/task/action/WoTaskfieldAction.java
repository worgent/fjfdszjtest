/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.task.action;

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

import com.womobile.task.model.*;
import com.womobile.task.dao.*;
import com.womobile.task.service.*;
import com.womobile.task.vo.query.*;

/**
 * @author lsr
 * @version 1.0
 * @since 1.0
 */


public class WoTaskfieldAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/task/WoTaskfield/query.jsp";
	protected static final String LIST_JSP= "/task/WoTaskfield/list.jsp";
	protected static final String CREATE_JSP = "/task/WoTaskfield/create.jsp";
	protected static final String EDIT_JSP = "/task/WoTaskfield/edit.jsp";
	protected static final String SHOW_JSP = "/task/WoTaskfield/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/task/WoTaskfield/list.do";
	
	private WoTaskfieldManager woTaskfieldManager;
	
	private WoTaskfield woTaskfield;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			woTaskfield = new WoTaskfield();
		} else {
			woTaskfield = (WoTaskfield)woTaskfieldManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setWoTaskfieldManager(WoTaskfieldManager manager) {
		this.woTaskfieldManager = manager;
	}	
	
	public Object getModel() {
		return woTaskfield;
	}
	
	public void setFieldId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		WoTaskfieldQuery query = newQuery(WoTaskfieldQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = woTaskfieldManager.findPage(query);
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
		woTaskfieldManager.save(woTaskfield);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		woTaskfieldManager.update(this.woTaskfield);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("fieldId"));
			woTaskfieldManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

}
