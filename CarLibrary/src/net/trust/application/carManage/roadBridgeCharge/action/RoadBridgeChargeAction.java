package net.trust.application.carManage.roadBridgeCharge.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.carManage.roadBridgeCharge.domain.RoadBridgeChargeFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
/**
 * 过路过桥费用管理
 *
 */
public class RoadBridgeChargeAction extends PaginactionAction{
	private Log log = LogFactory.getLog(RoadBridgeChargeAction.class);
	
	private RoadBridgeChargeFacade roadBridgeChargeFacade;
	
	private HashMap search = new HashMap();
	private List roadBridgeChargeList;
	
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
				super.setCount(roadBridgeChargeFacade.findRoadBridgeChargeCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "roadBridgeCharge";
			search = (HashMap)super.getParameter();
			roadBridgeChargeList = roadBridgeChargeFacade.findRoadBridgeCharge(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			
			int i = roadBridgeChargeFacade.insertRoadBridgeCharge(search);
			if (i>0){
				super.addActionMessage("添加过路过桥费用,操作成功");
				addActionScript("parent.document.ifrm_RoadBridgeCharge.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加过路过桥费用,操作失败");
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(roadBridgeChargeFacade.findRoadBridgeCharge(search).get(0));			
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			
			int i = roadBridgeChargeFacade.updateRoadBridgeCharge(search);
			if (i>=0){				
				super.addActionMessage("修改过路过桥费用,操作成功");
				addActionScript("parent.document.ifrm_RoadBridgeCharge.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改过路过桥费用,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = roadBridgeChargeFacade.deleteRoadBridgeCharge(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){
			search = (HashMap)(roadBridgeChargeFacade.findRoadBridgeCharge(search).get(0));			
			return "view";
			
		}
		
		return ERROR;
	}

	public RoadBridgeChargeFacade getRoadBridgeChargeFacade() {
		return roadBridgeChargeFacade;
	}

	public void setRoadBridgeChargeFacade(
			RoadBridgeChargeFacade roadBridgeChargeFacade) {
		this.roadBridgeChargeFacade = roadBridgeChargeFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getRoadBridgeChargeList() {
		return roadBridgeChargeList;
	}

	public void setRoadBridgeChargeList(List roadBridgeChargeList) {
		this.roadBridgeChargeList = roadBridgeChargeList;
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
