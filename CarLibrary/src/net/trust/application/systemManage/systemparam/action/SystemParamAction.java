package net.trust.application.systemManage.systemparam.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.systemManage.systemparam.domain.SystemParamFacade;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

public class SystemParamAction extends PaginactionAction{
	private Log log = LogFactory.getLog(SystemParamAction.class);
	private SystemParamFacade systemParamFacade;
	
	private HashMap search = new HashMap();
	private List systemParamList ;
	
	private String actionType;		
	private String action;
	
	private String xml;
	private String json = "";

	public String execute() throws Exception {
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){
			super.setPageRecord(15);// 设置页面单页显示总记录数
			super.setParameter(search); // 设置查询参数
			super.setCount(systemParamFacade.findSystemParamTypeCount(search));
			}
			search = (HashMap) super.getParameter();
			systemParamList = systemParamFacade.findSystemParamType(search);
			return "searchType";
			
		}else if ("detail".equals(actionType)){
			systemParamList = systemParamFacade.findSystemParam(search);
			return "searchDetail";
			
		}else if ("new".equals(actionType)){			
			action = "insert";
			return "new";	
			
		}else if ("insert".equals(actionType)){			
			int i = systemParamFacade.insertSystemParam(search);
			if (i>0){				
				super.addActionMessage("添加数据字典,操作成功");
				addActionScript("parent.document.ifrm_systemParamDetail.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("添加数据字典,操作失败");				
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){			
			search = (HashMap)(systemParamFacade.findSystemParam(search).get(0));				
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){			
			int i = systemParamFacade.updateSystemParam(search);
			if (i>=0){				
				super.addActionMessage("修改数据字典,操作成功");
				addActionScript("parent.document.ifrm_systemParamDetail.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("修改数据字典,操作失败");				
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){	
			search.put("paraState", "0");
			int i = systemParamFacade.deleteSystemParam(search);							
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";			
			
		}else if ("isExist".equals(actionType)){
			int i = systemParamFacade.findSystemParam(search).size();
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<isExist>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</isExist>");
			xml = sb.toString();
			return "xml";			

		}
		return ERROR;
	}

	public SystemParamFacade getSystemParamFacade() {
		return systemParamFacade;
	}

	public void setSystemParamFacade(SystemParamFacade systemParamFacade) {
		this.systemParamFacade = systemParamFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getSystemParamList() {
		return systemParamList;
	}

	public void setSystemParamList(List systemParamList) {
		this.systemParamList = systemParamList;
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

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	
}
