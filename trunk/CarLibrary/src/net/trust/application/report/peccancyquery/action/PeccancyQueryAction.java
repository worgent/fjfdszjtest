package net.trust.application.report.peccancyquery.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.report.peccancyquery.domain.PeccancyQueryFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
/**
 * 综合报表 -》 报警查询
 * @author chenqf
 *
 */
public class PeccancyQueryAction extends PaginactionAction{
	private Log log = LogFactory.getLog(PeccancyQueryAction.class);
	private PeccancyQueryFacade peccancyQueryFacade;
	
	private String table;
	private HashMap search = new HashMap();
	
	private String actionType;		
	private String action;
	private String xml;
	
	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		
		if ("".equals(actionType) || null == actionType){
//			if (search == null || search.size()==0){
//				peccancyQueryList = new java.util.ArrayList();
//				ActionContext.getContext().getSession().put("Qpagination",null);
//				return "search";
//			}
			table = peccancyQueryFacade.findPeccancyQuery(search);
			return "search";
		}else if ("export".equals(actionType)){
			search.put("type", "export");
			table = peccancyQueryFacade.findPeccancyQuery(search);
			return "export";
		}
		
		return ERROR;
	}

	public PeccancyQueryFacade getPeccancyQueryFacade() {
		return peccancyQueryFacade;
	}

	public void setPeccancyQueryFacade(PeccancyQueryFacade peccancyQueryFacade) {
		this.peccancyQueryFacade = peccancyQueryFacade;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
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
