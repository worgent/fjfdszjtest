package com.qzgf.application.biz.testModel.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.testModel.domain.TestFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 框架测试模块
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class TestAction extends BaseAction {
	Log log = LogFactory.getLog(TestAction.class);

	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private TestFacade testFacade;
	@SuppressWarnings("unchecked")
	private List testList;
	private PageList pageList;

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}

	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("list.do?action=" + this.getAction());
		this.setPageList(this.testFacade.findTest(search, pages));
		return "index";
	}
	
	

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public TestFacade getTestFacade() {
		return testFacade;
	}

	public void setTestFacade(TestFacade testFacade) {
		this.testFacade = testFacade;
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

}
