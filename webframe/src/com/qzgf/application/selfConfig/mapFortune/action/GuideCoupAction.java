package com.qzgf.application.selfConfig.mapFortune.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.mapFortune.domain.GuideCoupFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.OptionsString;

/**
 * 向导锦囊
 * @author lsr
 *
 */  
@SuppressWarnings("serial")
public class GuideCoupAction extends BaseAction {
	Log log = LogFactory.getLog(GuideCoupAction.class);

	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	
	private String spcialtySort;    //专长分类
	
	private List<OptionsString> sortList = new ArrayList<OptionsString>();
	
	private GuideCoupFacade guideCoupFacade;
	private PageList pageList=null;

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
		//排序方式
		this.sortList.add(new OptionsString("1", "时间"));
		this.sortList.add(new OptionsString("2", "浏览数"));
		
		
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("guideCoup.do?action=" + this.getAction());
		this.setPageList(this.guideCoupFacade.findGuideCoupList(search, pages));
		
		return "index";
	}
	
	@SuppressWarnings("unchecked")
	public String query() {
		//排序方式
		this.sortList.add(new OptionsString("1", "时间"));
		this.sortList.add(new OptionsString("2", "浏览数"));
		
		
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("guideCoup.do?action=" + this.getAction()+"&search.sortType="+(String)search.get("sortType"));
		this.setPageList(this.guideCoupFacade.findGuideCoupList(search, pages));
		
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

	public String getSpcialtySort() {
		return spcialtySort;
	}

	public void setSpcialtySort(String spcialtySort) {
		this.spcialtySort = spcialtySort;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public List<OptionsString> getSortList() {
		return sortList;
	}

	public void setSortList(List<OptionsString> sortList) {
		this.sortList = sortList;
	}

	public GuideCoupFacade getGuideCoupFacade() {
		return guideCoupFacade;
	}

	public void setGuideCoupFacade(GuideCoupFacade guideCoupFacade) {
		this.guideCoupFacade = guideCoupFacade;
	}

}
