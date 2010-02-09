package net.trust.application.carManage.staffcheckin.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.carManage.staffcheckin.domain.StaffCheckinManageFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
/**
 * 人员考勤管理
 *
 */
public class StaffCheckinManageAction extends PaginactionAction{
	private Log log = LogFactory.getLog(StaffCheckinManageAction.class);
	
	private StaffCheckinManageFacade staffCheckinManageFacade;

	private HashMap search = new HashMap();
	private List staffCheckinList;
	
	private String actionType;		
	private String action;
	private String xml;
	
	private String method;//设置为ic卡1,非ic卡
	
	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		search.put("method", "1");
		if ("".equals(actionType) || null == actionType){
			//method:1为(插IC行车记录) 其它为(未插IC)
			method=search.get("method").toString();
			if(super.queryFlag==0){//判断查询状态				
				search.put("maintainState", "1");
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(staffCheckinManageFacade.findStaffCheckinCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "staffCheckinManage";
			search = (HashMap)super.getParameter();
			staffCheckinList = staffCheckinManageFacade.findStaffCheckin(search);
			return "search";
		}
		
		return ERROR;
	}
	
	public String staffCheckinex() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		search.put("method", "0");
		if ("".equals(actionType) || null == actionType){
			//method:1为(插IC行车记录) 其它为(未插IC)
			method=search.get("method").toString();//移除分类
			search.remove("method");
			if(super.queryFlag==0){//判断查询状态				
				search.put("maintainState", "1");
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(staffCheckinManageFacade.findStaffCheckinCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "staffCheckinManage";
			search = (HashMap)super.getParameter();
			search.remove("method");
			staffCheckinList = staffCheckinManageFacade.findStaffCheckin(search);
			return "search";
		}
		return ERROR;
	}


	public StaffCheckinManageFacade getStaffCheckinManageFacade() {
		return staffCheckinManageFacade;
	}

	public void setStaffCheckinManageFacade(
			StaffCheckinManageFacade staffCheckinManageFacade) {
		this.staffCheckinManageFacade = staffCheckinManageFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getStaffCheckinList() {
		return staffCheckinList;
	}

	public void setStaffCheckinList(List staffCheckinList) {
		this.staffCheckinList = staffCheckinList;
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
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	
}
