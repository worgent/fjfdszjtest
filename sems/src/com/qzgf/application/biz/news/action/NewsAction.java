package com.qzgf.application.biz.news.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Encoder;



import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.news.domain.NewsFacade;
import com.qzgf.application.biz.news.domain.model;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import oracle.sql.*;
import oracle.jdbc.driver.*;

 

@SuppressWarnings("serial")
public class NewsAction extends BaseAction {
	Log log = LogFactory.getLog(NewsAction.class);
	private NewsFacade newsFacade;
	private PageList pageList;
	private PageList newsOnlyList; 
	
	private PageList  newsBrandList;
	private PageList topEightNews; 
	private PageList hangEightNews; 
	
    private PageList topSixNews; 
  
	
	private String xml;
	
	
	private CLOB content_byte;
	

	
	/*private PageList newsOnlyList; 
	private String xml;*/
	
	public String execute() {
		
		try {		
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			e.printStackTrace();
			return "fir";//跳转到首页
		}
		
	}
	
	
	public String fir(){
		
		this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);	
		if(topEightNews.getObjectList().isEmpty())
		{
			topEightNews=null;
		}
		
		this.hangEightNews=this.newsFacade.findHangEightNewsList(this.getSearch(), null);	
		if(hangEightNews.getObjectList().isEmpty())
		{
			hangEightNews=null;
		}
		
		this.topSixNews=this.newsFacade.findTopSixNewsList(this.getSearch(), null);	
		if(topSixNews.getObjectList().isEmpty())
		{
			topSixNews=null;
		}

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
	
@SuppressWarnings("unchecked")
public String addSave() {

   
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
	
	@SuppressWarnings("unchecked")
	public String editOnlyNews() {
		List<model> lt=this.newsFacade.findOnlyfNewsList(this.getSearch());	
		
		if(!lt.isEmpty())
    	{

			
			model m=lt.get(0);
			search.clear();
			search.put("NEWSID", m.getNEWSID());
			search.put("ARTICLESTYPE", m.getARTICLESTYPE());
			search.put("TITLE", m.getTITLE());
			search.put("RELEASETIME", m.getRELEASETIME());
			search.put("ISAUDIT",m.getISAUDIT());
			search.put("SOURCE", m.getSOURCE());
			search.put("AUTHOR", m.getAUTHOR());		
			search.put("CONTENT",m.getCONTENT());		
           
    	}
		
		
        return "edit";
	}
	@SuppressWarnings("unchecked")
	public String modifySave() {
	     boolean i=false;	
	     
	     
	   
	      if(search.get("isaudit")==null){
	    	  search.put("isaudit", "0");
	      }
	     try
	    	{
	        i=newsFacade.updateT_ARTICLES_NEWS(search);
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
		if(topEightNews.getObjectList().isEmpty())
		{
			topEightNews=null;
		}
		
		this.hangEightNews=this.newsFacade.findHangEightNewsList(this.getSearch(), null);	
		if(hangEightNews.getObjectList().isEmpty())
		{
			hangEightNews=null;
		}
		
	    //topEightNews
		
		return "FrontNews";
	}
	
	
	public String topEight(){
		
		search.put("ARTICLESTYPE", search.get("artype"));
		this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);	
		
		return "topEight";
	}
	
	
	
	
	
	public String brandNews() {
		
		
		
		this.newsBrandList=this.newsFacade.findOneNewsList(this.getSearch(), null);	
		if(!newsBrandList.getObjectList().isEmpty())
    	{
    		search=(HashMap)(newsBrandList.getObjectList().get(0));  		
    	}
		
		this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);	
		return "brandNews";
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
		List<model> lt=this.newsFacade.findOnlyfNews(this.getSearch());	
		if(!lt.isEmpty())
    	{
			model m=(model)lt.get(0);
			search.clear();
			search.put("NEWSID", m.getNEWSID());
			search.put("ARTICLESTYPE", m.getARTICLESTYPE());
			search.put("TITLE", m.getTITLE());
			search.put("RELEASETIME", m.getRELEASETIME());
			search.put("ISAUDIT",m.getISAUDIT());
			search.put("SOURCE", m.getSOURCE());
			search.put("AUTHOR", m.getAUTHOR());		
			search.put("CONTENT",m.getCONTENT());		
           
    	}
    	
		
		this.hangEightNews=this.newsFacade.findHangEightNewsList(this.getSearch(), null);	
		if(hangEightNews.getObjectList().isEmpty())
		{
			hangEightNews=null;
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


	public PageList getHangEightNews() {
		return hangEightNews;
	}


	public void setHangEightNews(PageList hangEightNews) {
		this.hangEightNews = hangEightNews;
	}


	public CLOB getContent_byte() {
		return content_byte;
	}


	public void setContent_byte(CLOB content_byte) {
		this.content_byte = content_byte;
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
