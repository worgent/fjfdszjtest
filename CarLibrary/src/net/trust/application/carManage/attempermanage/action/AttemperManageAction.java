package net.trust.application.carManage.attempermanage.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.carManage.attempermanage.domain.AttemperManageFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
/**
 * 调度管理
 *
 */
public class AttemperManageAction extends PaginactionAction{
	private Log log = LogFactory.getLog(AttemperManageAction.class);
	private AttemperManageFacade attemperManageFacade;
	
	private HashMap search = new HashMap();
	public List attemperList;
	
	private String actionType;		
	private String action;
	private String xml;

	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				search.put("maintainState", "1");
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(attemperManageFacade.findAttemperCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "attemperManage";
			search = (HashMap)super.getParameter();
			attemperList = attemperManageFacade.findAttemper(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			search = attemperManageFacade.insertAttemper(search);
			int i = (Integer)search.get("st");
			
			switch(i){
				case 1:super.addActionMessage("添加车辆调度记录,操作成功");
						addActionScript("parent.document.ifrm_AttemperManage.window.location.reload();");	
						search.put("sourceOrderType", "2");
						search.put("sourceOrderCode", search.get("attemperManageId"));

						return "AttemperSuccess";
				case 0:super.addActionMessage("添加车辆调度记录,操作失败");
						return ERROR;
				case -100:super.addActionMessage("车辆编号为空,操作失败");
						return ERROR;
				case -101:super.addActionMessage("本系统中无该车辆信息,操作失败");
						return ERROR;
				case -200:super.addActionMessage("司机号为空,操作失败");
						return ERROR;
				case -201:super.addActionMessage("本系统中无司机信息,操作失败");
						return ERROR;
			}
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(attemperManageFacade.findAttemper(search).get(0));			
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = attemperManageFacade.updateAttemper(search);
			if (i>=0){				
				super.addActionMessage("修改车辆调度记录,操作成功");
				addActionScript("parent.document.ifrm_AttemperManage.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改车辆调度记录,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = attemperManageFacade.deleteAttemper(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){
			search = (HashMap)(attemperManageFacade.findAttemper(search).get(0));			
			return "view";
			
		}else if ("findGPS".equals(actionType)){
			attemperList = attemperManageFacade.getGPSCarLocus(search);
			
			return "findGPS";
		}
		return ERROR;
	}

	public AttemperManageFacade getAttemperManageFacade() {
		return attemperManageFacade;
	}

	public void setAttemperManageFacade(AttemperManageFacade attemperManageFacade) {
		this.attemperManageFacade = attemperManageFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getAttemperList() {
		return attemperList;
	}

	public void setAttemperList(List attemperList) {
		this.attemperList = attemperList;
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
