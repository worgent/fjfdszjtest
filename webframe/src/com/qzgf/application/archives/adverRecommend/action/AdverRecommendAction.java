package com.qzgf.application.archives.adverRecommend.action;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.adverRecommend.domain.AdverRecommendFacade;



import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


@SuppressWarnings("serial")
public class AdverRecommendAction extends BaseAction {
	Log log = LogFactory.getLog(AdverRecommendAction.class);

	@SuppressWarnings("unchecked")

	private AdverRecommendFacade adverRecommendFacade;
	@SuppressWarnings("unchecked")
	private List testList;
	private PageList pageList;
	
	private PageList pageUserList;
	
	private PageList  pageGuideList;
	
	private PageList pageBusinessList;
	
	
	
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
	
    public String adverRec() {
		
		
		return  "adverRec";
	}
 	

	public String userList() {
		
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
	
			pages.setFileName("adverRec.do?action=userList"); //分页功能使用
			this.pageUserList=this.adverRecommendFacade.findUserList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return  "userList";
	}
	
	
	public String guideSpace() { //进入向导空间
		
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
	
			pages.setFileName("adverRec.do?action=guideSpace"); //分页功能使用
			this.pageGuideList=this.adverRecommendFacade.findGuideList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "guideSpace";
	}
	
	
	public String business() {  //查找此向导下有哪些商家可以推荐 guideUser
		
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
	
			pages.setFileName("adverRec.do?action=business"); //分页功能使用
			this.pageBusinessList=this.adverRecommendFacade.findBusinessList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		   
		
		return "business";
	}
	@SuppressWarnings("unchecked")
	public String businessSpace() { //用户进去商家空间
		
		   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
           String pusername=userInfo.get("USERNAME").toString();
		   search.put("pusername", pusername);
		  
		   
		      try {
			   InetAddress address = InetAddress.getLocalHost();
			   search.put("ip", address.getHostAddress());
			   System.out.println(address.getHostAddress());
			   } catch (UnknownHostException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			   }
			   
		
		   //系统识别用户的身份 与ip，避免恶意操作
		    int i=0;
	        try{
		      i= this.adverRecommendFacade.insertT_USER_IDENTITYSPOT(search);
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    //系统识别用户的身份 与ip，避免恶意操作
		
		    return "businessSpace";	
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

	public AdverRecommendFacade getAdverRecommendFacade() {
		return adverRecommendFacade;
	}

	public void setAdverRecommendFacade(AdverRecommendFacade adverRecommendFacade) {
		this.adverRecommendFacade = adverRecommendFacade;
	}

	public PageList getPageUserList() {
		return pageUserList;
	}

	public void setPageUserList(PageList pageUserList) {
		this.pageUserList = pageUserList;
	}

	public PageList getPageGuideList() {
		return pageGuideList;
	}

	public void setPageGuideList(PageList pageGuideList) {
		this.pageGuideList = pageGuideList;
	}

	public PageList getPageBusinessList() {
		return pageBusinessList;
	}

	public void setPageBusinessList(PageList pageBusinessList) {
		this.pageBusinessList = pageBusinessList;
	}

	


}
