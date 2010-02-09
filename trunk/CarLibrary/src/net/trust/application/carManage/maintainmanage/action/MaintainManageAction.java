package net.trust.application.carManage.maintainmanage.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.carManage.maintainmanage.domain.MaintainManageFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;
/**
 * 保养管理
 *
 */
public class MaintainManageAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(MaintainManageAction.class);
	private MaintainManageFacade maintainManageFacade;	
	private List maintainManageList;
	private HashMap search=new HashMap();
	private String actionType;		
	private String action;
	private String xml;
	
	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(maintainManageFacade.findMaintainManageCount(search));//获取查询结果总记录数
			}
			
			this.exportFlag = "maintainManage";
			search = (HashMap)super.getParameter();
			maintainManageList = maintainManageFacade.findMaintainManage(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			
			String carNoId = (String)search.get("carNoId");
			search.put("carNoId", carNoId.substring(0, carNoId.indexOf("|")));
			
			int i = maintainManageFacade.insertMaintainManage(search);
			if (i>0){
				super.addActionMessage("添加保养管理信息,操作成功");
				addActionScript("try{");
				addActionScript("	parent.document.ifrm_CareManage.window.location.reload();");	
				addActionScript("}catch(e){ }");
				return SUCCESS;
			}else {
				super.addActionMessage("添加保养管理信息,操作失败");
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(maintainManageFacade.findMaintainManage(search).get(0));			
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = maintainManageFacade.updateMaintainManage(search);
			if (i>=0){				
				super.addActionMessage("修改保养管理信息,操作成功");
				addActionScript("parent.document.ifrm_CareManage.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改保养管理信息,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = maintainManageFacade.deleteMaintainManage(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){;
			search = (HashMap)(maintainManageFacade.findMaintainManage(search).get(0));			
			return "view";
			
		}
		
		return ERROR;
	}
	public MaintainManageFacade getMaintainManageFacade() {
		return maintainManageFacade;
	}
	public void setMaintainManageFacade(MaintainManageFacade maintainManageFacade) {
		this.maintainManageFacade = maintainManageFacade;
	}
	public List getMaintainManageList() {
		return maintainManageList;
	}
	public void setMaintainManageList(List maintainManageList) {
		this.maintainManageList = maintainManageList;
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
}
