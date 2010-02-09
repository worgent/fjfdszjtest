package net.trust.application.carManage.peccancymanage.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.carManage.peccancymanage.domain.PeccancyManageFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;
/**
 * 违章管理
 *
 */
public class PeccancyManageAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(PeccancyManageAction.class);
	private PeccancyManageFacade peccancyManageFacade;	
	private List peccancyList;
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
				super.setCount(peccancyManageFacade.findPeccancyCount(search));//获取查询结果总记录数
			}
			
			this.exportFlag = "peccancyManage";
			search = (HashMap)super.getParameter();
			peccancyList = peccancyManageFacade.findPeccancy(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			int i = peccancyManageFacade.insertPeccancy(search);
			if (i>0){
				
				super.addActionMessage("添加违章信息,操作成功");
				addActionScript("parent.document.ifrm_PeccancyManage.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加违章信息,操作失败");
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(peccancyManageFacade.findPeccancy(search).get(0));
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){	
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = peccancyManageFacade.updatePeccancy(search);
			if (i>=0){				
				super.addActionMessage("修改违章信息,操作成功");
				addActionScript("parent.document.ifrm_PeccancyManage.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改违章信息,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){			
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = peccancyManageFacade.deletePeccancy(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){;
			search = (HashMap)(peccancyManageFacade.findPeccancy(search).get(0));
			return "view";
			
		}	
		return ERROR;
}
	
	public List getPeccancyList() {
		return peccancyList;
	}

	public void setPeccancyList(List peccancyList) {
		this.peccancyList = peccancyList;
	}

	public PeccancyManageFacade getPeccancyManageFacade() {
		return peccancyManageFacade;
	}

	public void setPeccancyManageFacade(PeccancyManageFacade peccancyManageFacade) {
		this.peccancyManageFacade = peccancyManageFacade;
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
