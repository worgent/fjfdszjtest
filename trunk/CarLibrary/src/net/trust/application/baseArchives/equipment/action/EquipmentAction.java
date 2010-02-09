package net.trust.application.baseArchives.equipment.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.baseArchives.equipment.domain.EquipmentFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

/**
 * 设备管理
 * @author chenqf
 *
 */
public class EquipmentAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(EquipmentAction.class);
	private EquipmentFacade equipmentFacade;	
	
	private List equipmentList;
	private List equipentParamList;
	private HashMap search=new HashMap();
	
	private String actionType;		
	private String action;
	private String xml;
	
	public String execute() throws Exception {
		String res = "";		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(equipmentFacade.findEquipmentCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			equipmentList = equipmentFacade.findEquipmentInfo(search);
			if (search.containsKey("action")) {
				if ("extSearch".equals(search.get("action").toString())) {
					return "extSearch";
				}
			} else {
				return "search";
			}
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			int i = equipmentFacade.insertEquipment(search);
			if (i>0){
				
				super.addActionMessage("添加设备信息,操作成功");
				addActionScript("parent.document.ifrm_EquipmentInfo.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("添加设备信息,操作失败");				
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			equipentParamList = equipmentFacade.findEquipmentParam(search);
			
			search = (HashMap)(equipmentFacade.findEquipmentInfo(search).get(0));
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){			
			int i = equipmentFacade.updateEquipmentInfo(search);
			if (i>=0){				
				super.addActionMessage("修改设备信息,操作成功");
				addActionScript("parent.document.ifrm_EquipmentInfo.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("修改设备信息,操作失败");				
				return ERROR;
			}
		}else if ("delete".equals(actionType)){			
			int i = equipmentFacade.deleteEquipmentInfo(search);							
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
				
		}else if ("view".equals(actionType)){
			equipentParamList = equipmentFacade.findEquipmentParam(search);
			search = (HashMap)(equipmentFacade.findEquipmentInfo(search).get(0));
			return "view";
			
		}
		return res;
	}
	public EquipmentFacade getEquipmentFacade() {
		return equipmentFacade;
	}
	public void setEquipmentFacade(EquipmentFacade equipmentFacade) {
		this.equipmentFacade = equipmentFacade;
	}
	public List getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List equipmentList) {
		this.equipmentList = equipmentList;
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
	public List getEquipentParamList() {
		return equipentParamList;
	}
	public void setEquipentParamList(List equipentParamList) {
		this.equipentParamList = equipentParamList;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
}
