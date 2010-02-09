package net.trust.application.systemManage.role.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.sf.json.JSONArray;
import net.trust.PaginactionAction;
import net.trust.application.systemManage.manager.dto.UserInfo;
import net.trust.application.systemManage.role.domain.RoleFacade;
import net.trust.utils.PubFunction;
import net.trust.utils.node.NodesUtil;

/**
 * 管理员用户组
 *
 */
public class RoleInfoAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(RoleInfoAction.class);
	private RoleFacade roleFacade;	
	private List roleInfoList;
	private HashMap search=new HashMap();
	
	private String actionType;		
	private String action;
	
	private String xml;
	private String json = "";
	
	private String superId = "";
	
	private String CheckData[];//用户选择的角色
	
	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());	
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(roleFacade.findRoleCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			roleInfoList = roleFacade.findRoleInfo(search);
			
			return "search";
			
		}else if ("new".equals(actionType)){			
			action = "insert";
			return "new";	
			
		}else if ("insert".equals(actionType)){			
			search.put("staffId", userInfo.getStaffId());			
			int i = roleFacade.insertRole(search);
			if (i>0){				
				super.addActionMessage("添加角色信息,操作成功");
				addActionScript("parent.document.ifrm_UserRole.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("添加角色信息,操作失败");				
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){			
			search = (HashMap)(roleFacade.findRoleInfo(search).get(0));				
			action = "update";
			return "edit";
		}else if ("update".equals(actionType)){			
			int i = roleFacade.updateRoleInfo(search);
			if (i>=0){				
				super.addActionMessage("修改角色信息,操作成功");
				addActionScript("parent.document.ifrm_UserRole.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("修改角色信息,操作失败");				
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){			
			int i = roleFacade.deleteRoleInfo(search);							
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";			
		}else if ("menuTreeJson".equals(actionType)){
			
			search.put("superId", superId);
			roleInfoList = roleFacade.execMenuTreeProc(search);
			NodesUtil nu = new NodesUtil();
			xml = nu.getTreeNodes(roleInfoList,true);
			return "xml";

			
		}else if ("allot".equals(actionType)){
			roleFacade.insertRoleMenu(CheckData, PubFunction.getNulltoStr(search.get("roleId")), superId);
			
			addActionMessage("分配权限操作成功");
			return SUCCESS;
		}
		
		return ERROR;
	}
	public RoleFacade getRoleFacade() {
		return roleFacade;
	}
	public void setRoleFacade(RoleFacade roleFacade) {
		this.roleFacade = roleFacade;
	}
	public List getRoleInfoList() {
		return roleInfoList;
	}
	public void setRoleInfoList(List roleInfoList) {
		this.roleInfoList = roleInfoList;
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
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getSuperId() {
		return superId;
	}
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	public String[] getCheckData() {
		return CheckData;
	}
	public void setCheckData(String[] checkData) {
		CheckData = checkData;
	}
	
}
