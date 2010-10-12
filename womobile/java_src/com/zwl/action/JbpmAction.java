package com.zwl.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zwl.vo.ProcessVo;

public class JbpmAction extends ActionSupport
{
    
    private ProcessEngine processEngine;
    private RepositoryService repositoryService;
    private ExecutionService executionService;
    private TaskService taskService ;
    private String id;
    private List<ProcessDefinition> processDefinitionList;
    private List<ProcessInstance> processInstanceList;
    private List<Task> taskList;
    private ActivityCoordinates ac; 
    private ProcessVo processVo;
    private Map<String, Object> map;
    private String result;
    
    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

  

    public ActivityCoordinates getAc()
    {
        return ac;
    }

    public List<ProcessInstance> getProcessInstanceList()
    {
        return processInstanceList;
    }

    public List<Task> getTaskList()
    {
        return taskList;
    }
    
    /**
     * 
     * Purpose      : 初始化相关任务列表信息
     */
    private void init()
    {
        repositoryService=processEngine.getRepositoryService();//仓库
        executionService=processEngine.getExecutionService();//执行
        taskService=processEngine.getTaskService();//任务
    }
    
    public List<ProcessDefinition> getProcessDefinitionList()
    {
        return processDefinitionList;
    }

    private String userName;
    
    public String getUserName()
    {
        return userName;
    }


    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    @SuppressWarnings("unchecked")
    public String login()
    {
        Map map=ActionContext.getContext().getSession();
        map.put("user", userName);
        return SUCCESS;
    }
    
    /**
     * 加载首页
     * @return
     */
    public String loadIndexPage()
    {
    	//先初始化基本参数
        init();
        //取得流程定义
        processDefinitionList=repositoryService.createProcessDefinitionQuery().list();
        //流程实例
        processInstanceList=executionService.createProcessInstanceQuery().list();
        
        
        //这里模拟不同的角色登陆系统，会接到不同的待办任务
        String manager="zwllxs1,zwllxs2,zwllxs3,zwllxs4";
        String boss="zhangweilin,zhangweilin2,zhangweilin3,zhangweilin4";
        String roleName="";//要按此名去查找它所在的节点
        
        //当前用户名
        String name=getLoginedUserName();
        
        //经理登陆
        if(manager.contains(name))
        {
            roleName="manager";
        }
        //老板登陆
        else if(boss.contains(name))
        {
            roleName="boss";
        }
        //普通用户
        else
        {
            roleName=name;
        }
        
        System.out.println("roleName: "+roleName);
        //当前任务列表
        taskList=taskService.findPersonalTasks(roleName);
        System.out.println("taskList: "+taskList);
        return SUCCESS;
    }
     
    /**
     * 部署
     * @return
     */
    public String deploy()
    {
        System.out.println("部署");
        init();
//        String did=repositoryService.createDeployment().addResourceFromClasspath("leave2.jpdl.xml").deploy();
        ZipInputStream zis = new ZipInputStream(this.getClass().getResourceAsStream("/flowchart/leave2.zip"));
        //发起流程，仅仅就是预定义任务，即在系统中创建一个流程，这是全局的，与具体的登陆 用户无关。然后，在启动流程时，才与登陆用户关联起来
        String did=repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();
        
        
        
        //使用zip方式
//        ZipInputStream zis = new ZipInputStream(this.getClass().getResourceAsStream("/leave2.zip"));
//        //发起流程，仅仅就是预定义任务，即在系统中创建一个流程，这是全局的，与具体的登陆 用户无关。然后，在启动流程时，才与登陆用户关联起来
//        String did=repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();
    
        System.out.println("部署时生成的id号: "+did);
        return SUCCESS;
    }
    
    /**
     * 部署
     * @return

    public String deployTest()
    {
        System.out.println("部署Test");
		// Extract a process definition from the processdefinition.xml file.
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlResource("simple/processdefinition.xml");
		
		assertNotNull("Definition should not be null", processDefinition);

		// Create an instance of the process definition.
		ProcessInstance instance = new ProcessInstance(processDefinition);
		assertEquals(
				"Instance is in start state", 
				instance.getRootToken().getNode().getName(), 
				"start");
		assertNull(
				"Message variable should not exist yet", 
				instance.getContextInstance().getVariable("message"));

		// Move the process instance from its start state to the first state.
		// The configured action should execute and the appropriate message
		// should appear in the message process variable.
		instance.signal();
		assertEquals(
				"Instance is in first state", 
				instance.getRootToken().getNode().getName(), 
				"first");
		assertEquals(
				"Message variable contains message", 
				instance.getContextInstance().getVariable("message"),
				"Going to the first state!");

		// Move the process instance to the end state. The configured action 
		// should execute again. The message variable contains a new value.
		instance.signal();
		assertEquals(
				"Instance is in end state", 
				instance.getRootToken().getNode().getName(), 
				"end");
		assertTrue("Instance has ended", instance.hasEnded());
		assertEquals(
				"Message variable is changed", 
				instance.getContextInstance().getVariable("message"),
				"About to finish!");
        return SUCCESS;
    }
         */
    
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
        map.put("owner", getLoginedUserName());
        System.out.println("启动时用户id_: "+id);
        ProcessInstance processInstance=executionService.startProcessInstanceById(id, map);
        System.out.println("启动时processInstance: "+processInstance.getId());
        return SUCCESS;  
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
        repositoryService.deleteDeploymentCascade(id);
        return SUCCESS;
    }
    
    
    /**
     * 显示
     * @return
     */
    public String view()
    {
        init();
      //通过id查到流程实例
        ProcessInstance processInstance = executionService.findProcessInstanceById(id);
        Set<String> activityNames = processInstance.findActiveActivityNames();
        
        //Coordinates为相依衣物
        ac = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),activityNames.iterator().next());
        return SUCCESS;
    }
    
    
    /**
     * 显示图片
     * @throws IOException
     */
    public void pic() throws IOException
    {
        init();
        ProcessInstance processInstance = executionService.findProcessInstanceById(id);
        String processDefinitionId = processInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
        
        String path=ServletActionContext.getRequest().getRealPath("");
//        String filePath=path+"\\WEB-INF\\classes\\leave2.png";
        String filePath=path+"\\WEB-INF\\classes\\"+"flowchart\\leave2.png";
        System.out.println("filePath: "+filePath);
        File file=new File(filePath);
        System.out.println("存在么: "+file.exists());
        
        //此处不知道为什么还要特别的通过repositoryService和processDefinition.getDeploymentId()来显示此图片
        //InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),filePath);
        //System.out.println("inputStream2: "+inputStream);
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
     * 经理事件
     * @return
     */
    public String manager()
    {
        init();
        Task task = taskService.getTask(id);
        String taskId=task.getId();
        Set<String> strSet=new HashSet<String>();
        strSet.add("owner");
        strSet.add("day");
        strSet.add("reason");
        strSet.add("name");
//        taskService.getVariable(taskId, "owner");
        map=taskService.getVariables(taskId, strSet);
        System.out.println("map: "+map);
        return SUCCESS;
    }
    
    /**
     * 经理提交 
     * @return
     */
    public String submitManager()
    {
        init();
        taskService.completeTask(id, result);
        return SUCCESS;
    }
    
    /**
     * 老板来了
     * @return
     */
    public String boss()
    {
        init();
        Task task = taskService.getTask(id);
        String taskId=task.getId();
        Set<String> strSet=new HashSet<String>();
        strSet.add("owner");
        strSet.add("day");
        strSet.add("reason");
        strSet.add("name");
        strSet.add("sex");
        strSet.add("age");
//        taskService.getVariable(taskId, "owner");
        map=taskService.getVariables(taskId, strSet);
        return SUCCESS;
    }
    
    /**
     * 老板处理
     * @return
     */
    public String submitBoss()
    {
        init();
        taskService.completeTask(id);
        return SUCCESS;
    }
    
    

    /**
     * 提交
     * @return
     */
    @SuppressWarnings("unchecked")
    public String submit()
    {
        init();
        Map map = new HashMap();
        //此day, 在xml文件中，有一个decision标签，里面有一个表达式:expr="#{day > 3 ? '老板审批' : '结束'}" 
        //即它与表达式中的day关联
        map.put("day", processVo.getDay());
        map.put("reason", processVo.getReason());
        map.put("name", "张伟林");
        //如果第二个参数为字符串，则是指定要向哪个方向完成，此是指定要向testhaha完成,并且如果线条上指定了文字，
        //就只能按着文字去指定方向,如果没有文字，才能用map,
        //也就是说，如果传递的参数为map,则流程会去寻找没定义名称的线条走，如果没找到。就抛出No unnamed transitions were found for the task 异常
        //taskService.completeTask(taskId, "testhaha");
        //如果第二个参数为map,则表示只是传递参数而已
        System.out.println("提交完毕");
        taskService.completeTask(processVo.getTaskId(), map);
        return SUCCESS;
    }
    
    public String getLoginedUserName()
    {
        return (String) ActionContext.getContext().getSession().get("user");
    }
    
    public void setProcessEngine(ProcessEngine processEngine)
    {
        this.processEngine = processEngine;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 7726442371304781094L;

    public ProcessVo getProcessVo()
    {
        return processVo;
    }

    public void setProcessVo(ProcessVo processVo)
    {
        this.processVo = processVo;
    }

    public Map<String, Object> getMap()
    {
        return map;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
    
    
    

}
