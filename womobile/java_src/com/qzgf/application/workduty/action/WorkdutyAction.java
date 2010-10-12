/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2010
 */

package com.qzgf.application.workduty.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.base.BaseStruts2Action;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.qzgf.application.workduty.model.Workduty;
import com.qzgf.application.workduty.service.WorkdutyManager;
import com.qzgf.application.workduty.vo.query.WorkdutyQuery;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class WorkdutyAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/workduty/Workduty/query.jsp";
	protected static final String LIST_JSP= "/workduty/Workduty/list.jsp";
	protected static final String CREATE_JSP = "/workduty/Workduty/create.jsp";
	protected static final String EDIT_JSP = "/workduty/Workduty/edit.jsp";
	protected static final String SHOW_JSP = "/workduty/Workduty/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/workduty/Workduty/list.do";
	
	protected static final String View_JSP = "/workduty/Workduty/view.jsp";
	
	private WorkdutyManager workdutyManager;
	
	private Workduty workduty;
	java.lang.Long id = null;
	private String[] items;
	
	//工作流参数
    private ProcessEngine processEngine;//set方法
    
    private RepositoryService repositoryService;
    private ExecutionService executionService;
    private TaskService taskService ;
    
    private ActivityCoordinates ac; 
    String deploymentId;   
    
    
    private List<ProcessDefinition> processDefinitionList;
    private List<ProcessInstance> processInstanceList;
    private List<Task> taskList;

    

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			workduty = new Workduty();
		} else {
			workduty = (Workduty)workdutyManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setWorkdutyManager(WorkdutyManager manager) {
		this.workdutyManager = manager;
	}	
	
	public Object getModel() {
		return workduty;
	}
	
	public void setId(java.lang.Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		init();
		 
		 
		WorkdutyQuery query = newQuery(WorkdutyQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = workdutyManager.findPage(query);
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
		//新增时,启动工作流
		deploy();
		//deploymentId
		for(ProcessDefinition pd :processDefinitionList){
			workduty.setLevelid(pd.getId());
			workduty.setDeploymentid(pd.getDeploymentId());
		}
		//保存数据之前生成流程id
		workdutyManager.save(workduty);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息


		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		//新增时,启动工作流
		deploy();
		//deploymentId
		for(ProcessDefinition pd :processDefinitionList){
			workduty.setLevelid(pd.getId());
			workduty.setDeploymentid(pd.getDeploymentId());
		}
		//保存数据之前生成流程id
		workdutyManager.update(this.workduty);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Long id = new java.lang.Long((String)params.get("id"));
			workdutyManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	
	//工作流处理
    /**
     * 
     * Purpose      : 初始化相关任务列表信息
     */
    private void init()
    {
        repositoryService=processEngine.getRepositoryService();//仓库
        executionService=processEngine.getExecutionService();//执行
        taskService=processEngine.getTaskService();//任务
        
        //取得流程定义
        processDefinitionList=repositoryService.createProcessDefinitionQuery().list();
        //流程实例
        processInstanceList=executionService.createProcessInstanceQuery().list();
        
        
    }
    
    /**
     * 部署
     * @return
     */
    public void deploy()
    {
    	init();
        deploymentId = repositoryService.createDeployment().addResourceFromClasspath("com/qzgf/application/workduty/flowchart/flower.jpdl.xml").deploy();
       // return LIST_ACTION;
    }
    
    /**
     * 启动
     * @return
     */
    @SuppressWarnings("unchecked")
    public String start()
    {
        init();
        Map map = new HashMap();
        //在启动任务时，等于就是一个用户要请假了，那么，此时，要把流程信息关联到此用户上，在开始的下一个节点（也就是第一个任务节点），是指派给。所以用户名要与其对应的变量关联起来
        map.put("owner", "now");
        ProcessInstance processInstance=executionService.startProcessInstanceById(deploymentId, map);
        System.out.println("启动时processInstance: "+processInstance.getId());
        return LIST_ACTION;
    }
    
    /**
     * 移除
     * @return
     */
    public String remove()
    {
    	//初始必要参数
        init();
        //删除例程
        repositoryService.deleteDeploymentCascade(deploymentId);
        return LIST_ACTION;
    }
    
    /**
     * 显示
     * @return
     */
    public String view()
    {
        init();
      //通过id查到流程实例
        ProcessInstance processInstance = executionService.findProcessInstanceById(deploymentId);
        Set<String> activityNames = processInstance.findActiveActivityNames();
        
        //Coordinates为相依衣物
        ac = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),activityNames.iterator().next());
        return View_JSP;
    }
    
    /**
     * 显示图片
     * @throws IOException
     */
    public void pic() throws IOException
    {
        init();
        ProcessInstance processInstance = executionService.findProcessInstanceById(deploymentId);
        String processDefinitionId = processInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
        
        String path=ServletActionContext.getRequest().getRealPath("");
        String filePath=path+"\\WEB-INF\\classes\\com\\qzgf\\application\\workduty\\flowchart"+"\\flower.png";
        File file=new File(filePath);
       InputStream inputStream =new FileInputStream(new File(filePath));
        byte[] b = new byte[1024];
        int len = -1; 
        ServletOutputStream sos=ServletActionContext.getResponse().getOutputStream();
        while ((len = inputStream.read(b, 0, 1024)) != -1)
        {
            sos.write(b, 0, len);
        }
        sos.close();
    }

	/**
	 * Purpose      : 说明
	 * @return the processDefinitionList
	 */
	public List<ProcessDefinition> getProcessDefinitionList() {
		return processDefinitionList;
	}

	/**
	 * Purpose      : 说明
	 * @param processDefinitionList the processDefinitionList to set
	 */
	
	public void setProcessDefinitionList(
			List<ProcessDefinition> processDefinitionList) {
		this.processDefinitionList = processDefinitionList;
	}

	/**
	 * Purpose      : 说明
	 * @return the processInstanceList
	 */
	public List<ProcessInstance> getProcessInstanceList() {
		return processInstanceList;
	}

	/**
	 * Purpose      : 说明
	 * @param processInstanceList the processInstanceList to set
	 */
	
	public void setProcessInstanceList(List<ProcessInstance> processInstanceList) {
		this.processInstanceList = processInstanceList;
	}

	/**
	 * Purpose      : 说明
	 * @return the taskList
	 */
	public List<Task> getTaskList() {
		return taskList;
	}

	/**
	 * Purpose      : 说明
	 * @param taskList the taskList to set
	 */
	
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	/**
	 * Purpose      : 说明
	 * @param processEngine the processEngine to set
	 */
	
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	/**
	 * Purpose      : 说明
	 * @return the deploymentId
	 */
	public String getDeploymentId() {
		return deploymentId;
	}

	/**
	 * Purpose      : 说明
	 * @param deploymentId the deploymentId to set
	 */
	
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	/**
	 * Purpose      : 说明
	 * @return the ac
	 */
	public ActivityCoordinates getAc() {
		return ac;
	}
}
