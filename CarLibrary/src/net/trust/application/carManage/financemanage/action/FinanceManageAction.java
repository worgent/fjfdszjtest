package net.trust.application.carManage.financemanage.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.carManage.financemanage.domain.FinanceManageFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

public class FinanceManageAction extends PaginactionAction{
	private Log log = LogFactory.getLog(FinanceManageAction.class);
	private FinanceManageFacade financeManageFacade;
	
	private HashMap search = new HashMap();
	private List financeList;
	
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
				super.setCount(financeManageFacade.findFinanceCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "financeManage";
			search = (HashMap)super.getParameter();
			financeList = financeManageFacade.findFinance(search);
			return "search";
			
		}else if ("view".equals(actionType)){
			search = (HashMap)financeManageFacade.findFinance(search).get(0);
			return "view";
			
		}
		return ERROR;
	}

	public FinanceManageFacade getFinanceManageFacade() {
		return financeManageFacade;
	}

	public void setFinanceManageFacade(FinanceManageFacade financeManageFacade) {
		this.financeManageFacade = financeManageFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getFinanceList() {
		return financeList;
	}

	public void setFinanceList(List financeList) {
		this.financeList = financeList;
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
