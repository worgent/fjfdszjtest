/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.womobile.collect.action;

import java.util.Hashtable;

import javacommon.base.BaseStruts2Action;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.womobile.collect.model.WoTask;
import com.womobile.collect.service.WoTaskManager;
import com.womobile.collect.vo.query.WoTaskQuery;
import com.womobile.task.model.WoTaskfield;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class WoTaskAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/collect/WoTask/query.jsp";
	protected static final String LIST_JSP= "/collect/WoTask/list.jsp";
	protected static final String CREATE_JSP = "/collect/WoTask/create.jsp";
	protected static final String EDIT_JSP = "/collect/WoTask/edit.jsp";
	protected static final String SHOW_JSP = "/collect/WoTask/show.jsp";
	protected static final String URGE_JSP = "/collect/WoTask/urge.jsp";
	
	
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/collect/WoTask/list.do";
	
	protected final static String URGE_SUCCESS = "督办成功";
	
	private WoTaskManager woTaskManager;
	
	private WoTask woTask;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			woTask = new WoTask();
		} else {
			woTask = (WoTask)woTaskManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setWoTaskManager(WoTaskManager manager) {
		this.woTaskManager = manager;
	}	
	
	public Object getModel() {
		return woTask;
	}
	
	public void setTaskId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		WoTaskQuery query = newQuery(WoTaskQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = woTaskManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		@SuppressWarnings("unused")
		WoTaskfield taskfield=woTaskManager.findTaskField();
		this.woTask.setALIAS_FIELD1((taskfield.getField1()==null)||("".equals(taskfield.getField1()))?woTask.getALIAS_FIELD1():taskfield.getField1()); 
		this.woTask.setALIAS_FIELD2((taskfield.getField2()==null)||("".equals(taskfield.getField2()))?woTask.getALIAS_FIELD2():taskfield.getField2()); 
		this.woTask.setALIAS_FIELD3((taskfield.getField3()==null)||("".equals(taskfield.getField3()))?woTask.getALIAS_FIELD3():taskfield.getField3()); 
		this.woTask.setALIAS_FIELD4((taskfield.getField4()==null)||("".equals(taskfield.getField4()))?woTask.getALIAS_FIELD4():taskfield.getField4()); 
		this.woTask.setALIAS_FIELD5((taskfield.getField5()==null)||("".equals(taskfield.getField5()))?woTask.getALIAS_FIELD5():taskfield.getField5()); 
		this.woTask.setALIAS_FIELD6((taskfield.getField6()==null)||("".equals(taskfield.getField6()))?woTask.getALIAS_FIELD6():taskfield.getField6()); 
		this.woTask.setALIAS_FIELD7((taskfield.getField7()==null)||("".equals(taskfield.getField7()))?woTask.getALIAS_FIELD7():taskfield.getField7()); 
		this.woTask.setALIAS_FIELD8((taskfield.getField8()==null)||("".equals(taskfield.getField8()))?woTask.getALIAS_FIELD8():taskfield.getField8()); 
		this.woTask.setALIAS_FIELD9((taskfield.getField9()==null)||("".equals(taskfield.getField9()))?woTask.getALIAS_FIELD9():taskfield.getField9()); 
		this.woTask.setALIAS_FIELD10((taskfield.getField10()==null)||("".equals(taskfield.getField10()))?woTask.getALIAS_FIELD10():taskfield.getField10());
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		woTask.setStatusId(1);//状态1.新任务
		woTaskManager.save(woTask);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 督办 */
	public String urge() {
		return URGE_JSP;
	}
	
	/** 处理中 */ 
	public String dispose() {
		woTask.setStatusId(2);//状态2.处理中
		woTaskManager.update(woTask);
		Flash.current().success(woTask.getTopic()+URGE_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		woTaskManager.update(this.woTask);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("taskId"));
			woTaskManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

}
