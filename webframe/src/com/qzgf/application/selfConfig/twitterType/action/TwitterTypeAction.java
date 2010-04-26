package com.qzgf.application.selfConfig.twitterType.action;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.twitterType.domain.TwitterTypeFacade;
import com.qzgf.context.Pages;

/** 
 * 日志分类表现层
 * @author lsr
 *  
 */
@SuppressWarnings("serial")
public class TwitterTypeAction extends BaseAction {
	Log log = LogFactory.getLog(TwitterTypeAction.class);

	private TwitterTypeFacade twitterTypeFacade;
	@SuppressWarnings("unchecked")
	private List testList;
	@SuppressWarnings("unchecked")
	private List pageList;
	private String twitterTypeName;
	private String twitterTypeId;



	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}

	@SuppressWarnings("unchecked")
	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("twitterType.do?action=" + this.getAction());
		this.getSearch().put("UserId", '1');
		this.pageList=this.twitterTypeFacade.findTwitterType(this.getSearch(), pages);
		this.setTwitterTypeName("");
		return "index";
	}
	
	@SuppressWarnings("unchecked")
	public String add() {
		
		this.getSearch().put("TwitterTypeName", twitterTypeName);
		this.getSearch().put("UserId", new String("1"));
		try {
			@SuppressWarnings("unused")
			int n=this.twitterTypeFacade.insertTwitterType(this.getSearch());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		
		this.pageList=this.twitterTypeFacade.findTwitterType(this.getSearch(), null);
		return "index";
		
	}
	
	@SuppressWarnings("unchecked")
	public String del() {
		
		this.getSearch().put("TwitterTypeId", twitterTypeId);
		this.getSearch().put("UserId", new String("1"));
		try {
			@SuppressWarnings("unused")
			int n=this.twitterTypeFacade.deleteTwitterTypeById(this.getSearch());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		/*Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("twitterType.do?action=index" );*/
		
		this.pageList=this.twitterTypeFacade.findTwitterType(this.getSearch(), null);
		return "index";
		
	}
	
	

	

	@SuppressWarnings("unchecked")
	public List getTestList() {
		return testList;
	}

	@SuppressWarnings("unchecked")
	public void setTestList(List testList) {
		this.testList = testList;
	}


	public TwitterTypeFacade getTwitterTypeFacade() {
		return twitterTypeFacade;
	}

	public void setTwitterTypeFacade(TwitterTypeFacade twitterTypeFacade) {
		this.twitterTypeFacade = twitterTypeFacade;
	}

	@SuppressWarnings("unchecked")
	public List getPageList() {
		return pageList;
	}

	@SuppressWarnings("unchecked")
	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public String getTwitterTypeName() {
		return twitterTypeName;
	}

	public void setTwitterTypeName(String twitterTypeName) {
		this.twitterTypeName = twitterTypeName;
	}

	public String getTwitterTypeId() {
		return twitterTypeId;
	}

	public void setTwitterTypeId(String twitterTypeId) {
		this.twitterTypeId = twitterTypeId;
	}

}
