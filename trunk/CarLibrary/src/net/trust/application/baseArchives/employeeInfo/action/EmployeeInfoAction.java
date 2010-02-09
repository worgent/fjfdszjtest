package net.trust.application.baseArchives.employeeInfo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.baseArchives.employeeInfo.domain.EmployeeFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

/**
 * 员工信息管理
 * @author chenqf
 *
 */
public class EmployeeInfoAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(EmployeeInfoAction.class);
	private EmployeeFacade employeeFacade;	
	private List employeeList;
	private HashMap search=new HashMap();
	private String actionType;		
	private String action;
	private String xml;
	private String json;
	
	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());	
		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(employeeFacade.findEmployeeInfoCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			employeeList = employeeFacade.findEmployeeInfo(search);
			return "search";
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("renew".equals(actionType)){
			search = (HashMap)(employeeFacade.findEmployeeInfo(search).get(0));
			action = "insert";
			return "new";
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			int i = employeeFacade.insertEmployeeInfo(search);
			if (i>0){
				super.addActionMessage("添加员工信息,操作成功");
				addActionScript("parent.document.ifrm_EmployeeInfo.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加员工信息,操作失败");
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(employeeFacade.findEmployeeInfo(search).get(0));
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = employeeFacade.updateEmployeeInfo(search);
			if (i>=0){				
				super.addActionMessage("修改员工信息,操作成功");
				addActionScript("parent.document.ifrm_EmployeeInfo.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改员工信息,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = employeeFacade.deleteEmployeeInfo(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){;
			search = (HashMap)(employeeFacade.findEmployeeInfo(search).get(0));
			return "view";
			
		}
		return ERROR;
	}
	/*
	 * 派车登记的短信中处用.
	 */
	public String ajaxXML(){
		search = (HashMap)(employeeFacade.findEmployeeInfo(search).get(0));
		
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<EmployeeInfo>");
		sb.append("	<staffInfoId>").append(search.get("staff_info_id")).append("</staffInfoId>");
		sb.append("	<staffCode>").append(search.get("staff_code")).append("</staffCode>");
		sb.append("	<staffName>").append(search.get("staff_name")).append("</staffName>");
		sb.append("	<smsPhone>").append(search.get("sms_phone")).append("</smsPhone>");
		sb.append("	<belongToCompany>").append(search.get("belong_to_company")).append("</belongToCompany>");
		sb.append("	<belongToDept>").append(search.get("belong_to_dept")).append("</belongToDept>");
		sb.append("</EmployeeInfo>");
		xml = sb.toString();
		return "xml";
	}
	
	/**
	 * 2009-12-19:员工信息(驾驶员),通过部门过滤
	 * @return
	 */
	public String ajaxJsonEmp(){
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());

		search.put("belongDept", search.get("belongDept").toString());
		employeeList = employeeFacade.ajaxJsonEmp(search);
		Iterator it = employeeList.iterator();
		StringBuffer sb = new StringBuffer();
		HashMap tmp = null;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			if (null != sb && !"".equals(sb.toString())){
				sb.append(",");
    		}
			sb.append("{");
			sb.append("\"text\":\"").append(tmp.get("text")).append("\"");
			sb.append(",\"id\":\"").append(tmp.get("id")).append("\"");
			sb.append(",\"leaf\":true");
			sb.append("}");
		}
		json = "[" +sb.toString()+ "]";
		return "json";
	}
	public EmployeeFacade getEmployeeFacade() {
		return employeeFacade;
	}

	public void setEmployeeFacade(EmployeeFacade employeeFacade) {
		this.employeeFacade = employeeFacade;
	}

	public List getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List employeeList) {
		this.employeeList = employeeList;
	}
	public HashMap getSearch() {
		return search;
	}
	public void setSearch(HashMap search) {
		this.search = search;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}
	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}
	
}
