package com.qzgf.application.systemManage.firstpage.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.qzgf.PaginactionAction;
import com.qzgf.application.systemManage.firstpage.domain.FirstPageFacade;



/**
 * 人员档案管理
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class FirstPageAction extends PaginactionAction {
	Log log = LogFactory.getLog(FirstPageAction.class);

	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private String actionType;
	private String xml;
	private String action;
	private FirstPageFacade firstPageFacade;
	@SuppressWarnings("unchecked")
	
	private List firstPageList;

	@SuppressWarnings("unchecked")
	private List  gobackFirstList;
	
	@SuppressWarnings("unchecked")
	private HashMap roldInfo = new HashMap();
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		if("".equals(actionType)||null==actionType){
			//人员档案列表	
			//列出某一工号可查看的租赁合同
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(firstPageFacade.findFirstPageCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			//search最终要补条件进去
			firstPageList=this.firstPageFacade.findFirstPageList(search);
			search.put("contentList", firstPageList);
			return "search";
		}
		else if("firstPageManage".equals(actionType))
		{
			gobackFirstList=firstPageFacade.gobackFirstList(search);
			return "firstMan";
		}
		else if("firstPageSave".equals(actionType))
		{
			firstPageFacade.insertFirstPage(search, roldInfo);	
			gobackFirstList=firstPageFacade.gobackFirstList(search);
			return "firstMan";
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}


	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	

	public FirstPageFacade getFirstPageFacade() {
		return firstPageFacade;
	}

	public void setFirstPageFacade(FirstPageFacade firstPageFacade) {
		this.firstPageFacade = firstPageFacade;
	}

	@SuppressWarnings("unchecked")
	public List getFirstPageList() {
		return firstPageList;
	}

	@SuppressWarnings("unchecked")
	public void setFirstPageList(List firstPageList) {
		this.firstPageList = firstPageList;
	}

	@SuppressWarnings("unchecked")
	public List getGobackFirstList() {
		return gobackFirstList;
	}

	public HashMap getRoldInfo() {
		return roldInfo;
	}

	public void setRoldInfo(HashMap roldInfo) {
		this.roldInfo = roldInfo;
	}

	public void setGobackFirstList(List gobackFirstList) {
		this.gobackFirstList = gobackFirstList;
	}
}
