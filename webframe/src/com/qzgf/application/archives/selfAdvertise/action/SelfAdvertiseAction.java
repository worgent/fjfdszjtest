package com.qzgf.application.archives.selfAdvertise.action;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.BaseAction;

import com.qzgf.application.archives.businessGuide.domain.BusinessGuideFacade;



import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


@SuppressWarnings("serial")
public class SelfAdvertiseAction extends BaseAction {
	Log log = LogFactory.getLog(SelfAdvertiseAction.class);

	@SuppressWarnings("unchecked")

	private BusinessGuideFacade selfAdvertiseFacade;
	@SuppressWarnings("unchecked")
	private List testList;
	private PageList pageList;

	private PageList  pageGuideList;
	private PageList  pageGuideAddList;
	
	
	
	private String xml;
	BaseSqlMapDAO baseSqlMapDAO;
	

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return "index";
		}
	}
	
	public String vipAdver() { //优惠/vip
		
		
		return "vipAdver";
	}
	
    public String pageAdver() { //页面广告
    	
    	
		
		return "pageAdver";
	 }

    
	@SuppressWarnings("unchecked")
	public List getTestList() {
		return testList;
	}

	@SuppressWarnings("unchecked")
	public void setTestList(List testList) {
		this.testList = testList;
	}

	public PageList getPageList() {
		return pageList;
	}



	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}



	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}




	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

   

	public PageList getPageGuideList() {
		return pageGuideList;
	}

	public void setPageGuideList(PageList pageGuideList) {
		this.pageGuideList = pageGuideList;
	}

	
	public PageList getPageGuideAddList() {
		return pageGuideAddList;
	}

	public void setPageGuideAddList(PageList pageGuideAddList) {
		this.pageGuideAddList = pageGuideAddList;
	}

	public BusinessGuideFacade getSelfAdvertiseFacade() {
		return selfAdvertiseFacade;
	}

	public void setSelfAdvertiseFacade(BusinessGuideFacade selfAdvertiseFacade) {
		this.selfAdvertiseFacade = selfAdvertiseFacade;
	}

	


}
