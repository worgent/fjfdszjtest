package com.qzgf.application.selfConfig.mapFortune.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.mapFortune.domain.GuideToffFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 *  向导名人榜
 * @author lsr
 *
 */  
@SuppressWarnings("serial")
public class GuideToffAction extends BaseAction {
	Log log = LogFactory.getLog(GuideToffAction.class);

	private GuideToffFacade guideToffFacade;
	@SuppressWarnings({ "unchecked", "unused" })
	private List guideList=new ArrayList();
	private PageList pageList;
	
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
		return "index";
	}
	
	/**
	 * 列出白领前二十位
	 * @return
	 */
	public String listDefault() {
		guideList=this.guideToffFacade.findWhiteCollarGuideList(search);
		
		return "listDefault";
	}
	
	/**
	 * 列出更多关于白领的
	 * @return
	 */
	public String listMore(){
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		pages.setFileName("guideToff.do");
		this.setPageList(this.guideToffFacade.findWhiteCollarGuideMoreList(search, pages));
		return "listMore";
	}
	
	
	/**
	 * 列出名望前二十位
	 * @return
	 */
	public String listFame() {
		guideList=this.guideToffFacade.findFameGuideList(search);
		
		return "listFame";
	}
	
	/**
	 * 列出赋闲前二十位
	 * @return
	 */
	public String listDally() {
		guideList=this.guideToffFacade.findDallyGuideList(search);
		
		return "listDally";
	}
	
	/**
	 * 列出更多关于名望的
	 * @return
	 */
	public String listFameMore(){
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		pages.setFileName("guideToff.do");
		this.setPageList(this.guideToffFacade.findFameGuideMoreList(search, pages));
		return "listFameMore";
	}
	
	/**
	 * 列出更多关于赋闲的
	 * @return
	 */
	public String listDallyMore(){
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		pages.setFileName("guideToff.do");
		this.setPageList(this.guideToffFacade.findDallyGuideMoreList(search, pages));
		return "listDallyMore";
	}

	public GuideToffFacade getGuideToffFacade() {
		return guideToffFacade;
	}

	public void setGuideToffFacade(GuideToffFacade guideToffFacade) {
		this.guideToffFacade = guideToffFacade;
	}

	@SuppressWarnings("unchecked")
	public List getGuideList() {
		return guideList;
	}

	@SuppressWarnings("unchecked")
	public void setGuideList(List guideList) {
		this.guideList = guideList;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	
}
