package net.trust.application.report.localitymanage.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.report.localitymanage.domain.LocalityManageFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;
/**
 * 综合报表 -》 位置管理
 * @author chenqf
 *
 */
public class LocalityManageAction extends PaginactionAction{
	private Log log = LogFactory.getLog(LocalityManageAction.class);
	private LocalityManageFacade localityManageFacade;
	
	private HashMap search = new HashMap();
	private List localityList;
	
	private String actionType;		
	private String action;
	private String xml;

	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态		
				if (search == null || search.size()==0){
					localityList = new java.util.ArrayList();
					ActionContext.getContext().getSession().put("Qpagination",null);
					return "search";
				}
				this.exportFlag = "localityManage";
				search.put("staffId", userInfo.getStaffId());
				search.put("maintainState", "1");
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(localityManageFacade.findLocalityManageCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			localityList = localityManageFacade.findLocalityManage(search);
			return "search";
		}
		return ERROR;
	}

	public LocalityManageFacade getLocalityManageFacade() {
		return localityManageFacade;
	}

	public void setLocalityManageFacade(LocalityManageFacade localityManageFacade) {
		this.localityManageFacade = localityManageFacade;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getLocalityList() {
		return localityList;
	}

	public void setLocalityList(List localityList) {
		this.localityList = localityList;
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
