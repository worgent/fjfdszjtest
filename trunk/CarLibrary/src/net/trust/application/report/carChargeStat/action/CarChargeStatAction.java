package net.trust.application.report.carChargeStat.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.report.carChargeStat.domain.CarChargeStatFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
/**
 * 综合报表 -》 车辆费用统计
 *
 */
public class CarChargeStatAction extends PaginactionAction{
	private Log log = LogFactory.getLog(CarChargeStatAction.class);
	private CarChargeStatFacade carChargeStatFacade;
	
	private HashMap search = new HashMap();
	private List carChargeStatList;
	
	private String actionType;		
	private String action;
	private String xml;

	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");	
		if ("".equals(actionType) || null == actionType){
			if (search == null || search.size()==0){
				carChargeStatList = new java.util.ArrayList();
				return "search";
			}
			
			this.exportFlag = "carChargeStat"; 
			search.put("staffId", userInfo.getStaffId());
			carChargeStatList = carChargeStatFacade.findCarChargeStat(search);
			return "search";
		}
		return ERROR;
	}

	public CarChargeStatFacade getCarChargeStatFacade() {
		return carChargeStatFacade;
	}

	public void setCarChargeStatFacade(CarChargeStatFacade carChargeStatFacade) {
		this.carChargeStatFacade = carChargeStatFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getCarChargeStatList() {
		return carChargeStatList;
	}

	public void setCarChargeStatList(List carChargeStatList) {
		this.carChargeStatList = carChargeStatList;
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
