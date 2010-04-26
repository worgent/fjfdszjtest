package com.qzgf.application.archives.businessGuide.action;



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
public class BusinessGuideAction extends BaseAction {
	Log log = LogFactory.getLog(BusinessGuideAction.class);

	@SuppressWarnings("unchecked")

	private BusinessGuideFacade businessGuideFacade;
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
	
	@SuppressWarnings("unchecked")
	public String guideList() {
		      HashMap userInfo=new HashMap();
		      userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
              String pusername=userInfo.get("USERNAME").toString();
		      search.put("pusername", pusername);
		
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			pages.setFileName("businGud.do?action=guideList"); //分页功能使用
			this.pageGuideList=this.businessGuideFacade.findBusinessGuideList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return  "businessGuideList";
	}
	
	public String guideListAdd() {  //查找未被选择加入为该商家的  向导
       
		
		 HashMap userInfo=new HashMap();
	      userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
	      search.put("pusername", pusername);
		
		
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			pages.setFileName("businGud.do?action=guideListAdd"); //分页功能使用
			this.pageGuideAddList=this.businessGuideFacade.findBusinessGuideAddList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return "guideListAdd";
	}
	
	public String businessGuideAdd() { 	
		
		
		 HashMap userInfo=new HashMap();
	      userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
          String pusername=userInfo.get("USERNAME").toString();
	      search.put("pusername", pusername);
		
		
		
		
		int i=0;
		try {
			i=this.businessGuideFacade.insertBusinessGuideRelate(search);//做插入操作商家跟向导的关系
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		

		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";

	}
	
	public String delete() { 	
		
		
		int i=0;
		try {
			i=this.businessGuideFacade.deleteGuide(search);
		} catch (Exception e) {
			
			e.getMessage();
		}
		

		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
	}
	
	
	
	

	
	public String business() { 
		

		
		return "business";
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

	public BusinessGuideFacade getBusinessGuideFacade() {
		return businessGuideFacade;
	}

	public void setBusinessGuideFacade(BusinessGuideFacade businessGuideFacade) {
		this.businessGuideFacade = businessGuideFacade;
	}

	public PageList getPageGuideAddList() {
		return pageGuideAddList;
	}

	public void setPageGuideAddList(PageList pageGuideAddList) {
		this.pageGuideAddList = pageGuideAddList;
	}

	


}
