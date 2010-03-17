package com.qzgf.application.biz.news.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.news.domain.NewsFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * �ռ�����
 * @author swq
 *com.qzgf.webutils.interceptor.MapParametersInterceptor
 */
@SuppressWarnings("serial")
public class NewsAction extends BaseAction {
	Log log = LogFactory.getLog(NewsAction.class);
	private NewsFacade newsFacade;
	private PageList pageList;
	private PageList newsOnlyList; 
	private PageList topEightNews; 
	private PageList topSixNews; 
	
	
	private String xml;
	/*private PageList newsOnlyList; 
	private String xml;*/
	
	public String execute() {
		try {
			System.out.println("action:"+this.getAction());
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
		
	}
	
	
	public String fir(){
		
		this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);	
		
		
		this.topSixNews=this.newsFacade.findTopSixNewsList(this.getSearch(), null);	
		
	
		return "fir";
	}
	
	public String index(){
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(10); 
		
		pages.setFileName("news.do?action=index");
		try{
		this.setPageList(this.newsFacade.findNewsPage(search, pages));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "index";
	}
	
public String add() {
		
		
		return "add";
	}
	
public String addSave() {
	
	    System.out.println(search.get("articlestype"));
	    System.out.println(search.get("title"));
		System.out.println(search.get("content"));
		System.out.println(search.get("pisaudit"));
		System.out.println(search.get("releasetime"));
		
	        int i=0;
	       try{
		   i= this.newsFacade.insertTArticlesNews(search);
		   System.out.println(i);
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    
		    
		    this.setAction("index");
		  
		return index();	
	}
	
	public String editOnlyNews() {
		this.newsOnlyList=this.newsFacade.findOnlyfNewsList(this.getSearch(), null);	
		if(!newsOnlyList.getObjectList().isEmpty())
    	{
    		search=(HashMap)(newsOnlyList.getObjectList().get(0));  		
    	}
        return "edit";
	}
	@SuppressWarnings("unchecked")
	public String modifySave() {
	     boolean i=false;	
	     
	     
	      System.out.print(search.get("isaudit"));
	      if(search.get("isaudit")==null){
	    	  search.put("isaudit", "0");
	      }
	     try
	    	{
	      i=   newsFacade.updateT_ARTICLES_NEWS(search);
		   }catch(Exception e){
			e.getStackTrace();
	    	}
	        
		
		return index();		
	}


	
	
	public String FrontNews() {
	
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(10);
		
		if((search.get("artype")==null)||(search.get("artype").equals("null")))
		{
			search.put("artype", "");
		}
		
		pages.setFileName("news.do?action=FrontNews&search.artype="+search.get("artype"));
		
		try{
		this.setPageList(this.newsFacade.findNewsPage(search, pages));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		search.put("ARTICLESTYPE", search.get("artype"));
		this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);		
		
	//	topEightNews
		
		return "FrontNews";
	}
	
	public String delRecord(){

		int i=0;
		try {
			i=this.newsFacade.deleteNews(search);
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
	
	
	
	
	public String newsDetail() {
		
		this.newsOnlyList=this.newsFacade.findOnlyfNewsList(this.getSearch(), null);	
		if(!newsOnlyList.getObjectList().isEmpty())
    	{
    		search=(HashMap)(newsOnlyList.getObjectList().get(0));  		
    	}
		
		
		
		this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);		
		
		return "newsDetail";
	}
	
	public String test()
	{
		
		return "test";
	}
	
	
	

	
	
	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	
	public NewsFacade getNewsFacade() {
		return newsFacade;
	}

	public void setNewsFacade(NewsFacade newsFacade) {
		this.newsFacade = newsFacade;
	}

	public PageList getNewsOnlyList() {
		return newsOnlyList;
	}

	public void setNewsOnlyList(PageList newsOnlyList) {
		this.newsOnlyList = newsOnlyList;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public PageList getTopEightNews() {
		return topEightNews;
	}

	public void setTopEightNews(PageList topEightNews) {
		this.topEightNews = topEightNews;
	}


	public PageList getTopSixNews() {
		return topSixNews;
	}


	public void setTopSixNews(PageList topSixNews) {
		this.topSixNews = topSixNews;
	}

	/*public PageList getNewsOnlyList() {
		return newsOnlyList;
	}

	public void setNewsOnlyList(PageList newsOnlyList) {
		this.newsOnlyList = newsOnlyList;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	*/
	
}
