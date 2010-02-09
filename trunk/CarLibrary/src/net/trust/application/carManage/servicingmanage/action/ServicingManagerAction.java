package net.trust.application.carManage.servicingmanage.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.carManage.servicingmanage.domain.ServicingManageFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

public class ServicingManagerAction extends PaginactionAction{
	private Log log = LogFactory.getLog(ServicingManagerAction.class);
	
	private ServicingManageFacade servicingManageFacade;
	
	private HashMap search = new HashMap();
	private List servicingList;
	
	private String actionType;		
	private String action;
	private String xml;

	public String execute() throws Exception {
		return ERROR;
	}
	
	/**
	 * 车辆维修申请
	 * @return
	 * @throws Exception
	 */
	public String servicingApply() throws Exception{
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				search.put("maintainState", "1");
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(servicingManageFacade.findServiceingCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			servicingList = servicingManageFacade.findServiceing(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			
			String carNoId = (String)search.get("carNoId");
			search.put("carNoId", carNoId.substring(0, carNoId.indexOf("|")));
			
			int i = servicingManageFacade.insertServiceingApply(search);
			if (i>0){
				super.addActionMessage("添加车辆纵申请,操作成功");
				addActionScript("parent.document.ifrm_MaintainApply.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加车辆纵申请,操作失败");
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(servicingManageFacade.findServiceing(search).get(0));			
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			
			String carNoId = (String)search.get("carNoId");
			search.put("carNoId", carNoId.substring(0, carNoId.indexOf("|")));
			
			int i = servicingManageFacade.updateServiceingApply(search);
			if (i>=0){				
				super.addActionMessage("修改车辆纵申请,操作成功");
				addActionScript("parent.document.ifrm_MaintainApply.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改车辆纵申请,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = servicingManageFacade.deleteServiceingApply(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){
			search = (HashMap)(servicingManageFacade.findServiceing(search).get(0));			
			return "view";
			
		}
		return ERROR;
	}
	
	
	private HashMap detail = new HashMap();
	
	/**
	 * 车辆维修登记
	 * @return
	 * @throws Exception
	 */
	public String servicingBooking() throws Exception{
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(servicingManageFacade.findServiceingCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "servicingBooking";
			search = (HashMap)super.getParameter();
			servicingList = servicingManageFacade.findServiceing(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			search = (HashMap)(servicingManageFacade.findServiceing(search).get(0));
			action = "insert";
			return "new";
			
		}else if ("insert".equals(actionType)){
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = servicingManageFacade.insertServiceingBooking(search, detail);
			if (i>=0){				
				super.addActionMessage("添加车辆纵登记,操作成功");
				addActionScript("parent.document.ifrm_MaintainBooking.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("添加车辆纵登记,操作失败");
				return ERROR;
			}

		}else if ("edit".equals(actionType)){
			List detailList = servicingManageFacade.findServiceingDetail(search);
			search = (HashMap)(servicingManageFacade.findServiceing(search).get(0));	
			search.put("detailList", detailList);
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = servicingManageFacade.updateServiceingBooking(search, detail);
			if (i>=0){				
				super.addActionMessage("修改车辆纵登记,操作成功");
				addActionScript("parent.document.ifrm_MaintainBooking.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改车辆纵登记,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = servicingManageFacade.deleteServiceingBooking(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){
			List detailList = servicingManageFacade.findServiceingDetail(search);
			search = (HashMap)(servicingManageFacade.findServiceing(search).get(0));
			search.put("detailList", detailList);
			
			return "view";
			
		}else if ("delDetail".equals(actionType)){
			int i = servicingManageFacade.deleteServiceingDetail(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("	<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}
		return ERROR;
	}

	public ServicingManageFacade getServicingManageFacade() {
		return servicingManageFacade;
	}

	public void setServicingManageFacade(ServicingManageFacade servicingManageFacade) {
		this.servicingManageFacade = servicingManageFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getServicingList() {
		return servicingList;
	}

	public void setServicingList(List servicingList) {
		this.servicingList = servicingList;
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

	public HashMap getDetail() {
		return detail;
	}

	public void setDetail(HashMap detail) {
		this.detail = detail;
	}
}
