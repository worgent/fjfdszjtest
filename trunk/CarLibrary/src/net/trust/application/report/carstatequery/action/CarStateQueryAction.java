package net.trust.application.report.carstatequery.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.report.carstatequery.domain.CarStateQueryFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

/**
 * 综合报表 -》 车辆状态查询
 * @author chenqf
 *
 */
public class CarStateQueryAction extends PaginactionAction{
	private Log log = LogFactory.getLog(CarStateQueryAction.class);
	private CarStateQueryFacade carStateQueryFacade;
	
	private HashMap search = new HashMap();
	private List carStateQueryList;
	
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
				super.setCount(carStateQueryFacade.findCarStateQueryCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "carStateQuery";
			search = (HashMap)super.getParameter();
			carStateQueryList = carStateQueryFacade.findCarStateQuery(search);
			return "search";
			
		}
		return ERROR;
	}

	public CarStateQueryFacade getCarStateQueryFacade() {
		return carStateQueryFacade;
	}

	public void setCarStateQueryFacade(CarStateQueryFacade carStateQueryFacade) {
		this.carStateQueryFacade = carStateQueryFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getCarStateQueryList() {
		return carStateQueryList;
	}

	public void setCarStateQueryList(List carStateQueryList) {
		this.carStateQueryList = carStateQueryList;
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
