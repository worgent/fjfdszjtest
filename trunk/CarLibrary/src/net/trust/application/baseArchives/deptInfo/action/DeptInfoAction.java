package net.trust.application.baseArchives.deptInfo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.baseArchives.deptInfo.domain.DeptInfoFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;


public class DeptInfoAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(DeptInfoAction.class);
	private DeptInfoFacade deptInfoFacade;	
	private List deptInfoList;
	private HashMap search=new HashMap();
	private String actionType;		
	private String action;
	private String xml;
	private String json;
	
	private String belongCompany;
	
	public String execute() throws Exception {
		String res = "";
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		 search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(deptInfoFacade.findDeptInfoCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			deptInfoList = deptInfoFacade.findDeptInfo(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			int i = deptInfoFacade.insertDeptInfo(search);
			if (i>0){
				super.addActionMessage("添加部门信息,操作成功");
				addActionScript("parent.document.ifrm_DeptInfoManage.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加部门信息,操作失败");
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(deptInfoFacade.findDeptInfo(search).get(0));
			action = "update";
			return "edit";
		}else if ("update".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = deptInfoFacade.updateDeptInfo(search);
			if (i>=0){				
				super.addActionMessage("修改部门信息,操作成功");
				addActionScript("parent.document.ifrm_DeptInfoManage.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改部门信息,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){			
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = deptInfoFacade.deleteDeptInfo(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}
		return res;
	}
	
	public String ajaxJson(){
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());

		search.put("belongCompany", belongCompany);
		deptInfoList = deptInfoFacade.findDeptInfo(search);
		Iterator it = deptInfoList.iterator();
		StringBuffer sb = new StringBuffer();
		HashMap tmp = null;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			if (null != sb && !"".equals(sb.toString())){
				sb.append(",");
    		}
			sb.append("{");
			sb.append("\"text\":\"").append(tmp.get("dept_name")).append("\"");
			sb.append(",\"id\":\"").append(tmp.get("dept_id")).append("\"");
			sb.append(",\"leaf\":true");
			sb.append("}");
		}
		json = "[" +sb.toString()+ "]";
		return "json";
	}
	
	public DeptInfoFacade getDeptInfoFacade() {
		return deptInfoFacade;
	}
	public void setDeptInfoFacade(DeptInfoFacade deptInfoFacade) {
		this.deptInfoFacade = deptInfoFacade;
	}
	public List getDeptInfoList() {
		return deptInfoList;
	}
	public void setDeptInfoList(List deptInfoList) {
		this.deptInfoList = deptInfoList;
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
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getBelongCompany() {
		return belongCompany;
	}
	public void setBelongCompany(String belongCompany) {
		this.belongCompany = belongCompany;
	}
}
