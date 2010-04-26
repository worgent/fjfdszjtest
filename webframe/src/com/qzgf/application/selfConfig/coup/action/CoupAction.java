package com.qzgf.application.selfConfig.coup.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.coup.domain.CoupFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;


/**
 * 向导锦囊
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class CoupAction extends BaseAction {
	Log log = LogFactory.getLog(CoupAction.class);
	private CoupFacade coupFacade;
	private PageList pageList;
	private String coupTitle;
	private String coupContent;
	private String id;
	private AjaxMessagesJson ajaxMessagesJson;
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String index() {
		return SUCCESS;
	}
	
	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		pages.setFileName("coup.do");
		this.pageList=this.coupFacade.findCoup(search, pages);
		return "list";
	}
	
	public String add() {
		this.setAction("addsave");
		return INPUT;
	}
	
	@SuppressWarnings("unchecked")
	public String addsave(){
		try {
			search.put("coupTitle",URLDecoder.decode(StringUtils.trimToEmpty(coupTitle),"UTF-8"));
			search.put("coupContent",URLDecoder.decode(StringUtils.trimToEmpty(coupContent),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		search.put("coupCreater","11111");
		try {
			this.coupFacade.insertGuideCoup(search);
			this.getAjaxMessagesJson().setMessage("0", "向导锦囊添加成功");
		} catch (Exception e) {
			log.error(e);
			this.getAjaxMessagesJson().setMessage("E_NOTE_ADDFAILED", "向导锦囊添加失败");
		}
		return RESULT_AJAXJSON;
	}
	
	/**
	 * 进入向导锦囊编辑页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String edit(){
		this.setAction("editdo");
		search.put("id",id);
		Map coupMap=this.coupFacade.findCoupById(id);
		
		this.coupTitle=(String)coupMap.get("COUPTITLE");
		this.coupContent=(String)coupMap.get("COUPCONTENT");
		
		return INPUT;
	}
	
	/**
	 * 保存编辑
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editdo(){
		try {
			search.put("coupTitle",URLDecoder.decode(StringUtils.trimToEmpty(coupTitle),"UTF-8"));
			search.put("coupContent",URLDecoder.decode(StringUtils.trimToEmpty(coupContent),"UTF-8"));
			search.put("id",id);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		search.put("coupCreater","11111");
		try {
			this.coupFacade.updateCoupById(search);
			this.getAjaxMessagesJson().setMessage("0", "向导锦囊更新成功");
		} catch (Exception e) {
			log.error(e);
			this.getAjaxMessagesJson().setMessage("1", "向导锦囊更新失败");
		}
		return RESULT_AJAXJSON;
	}
	
	/**
	 * 删除向导锦囊
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String del(){
		
		search.put("id",id);
		
		try {
			this.coupFacade.deleteCoupById(search);
			this.getAjaxMessagesJson().setMessage("0", "向导锦囊删除成功");
		} catch (Exception e) {
			log.error(e);
			this.getAjaxMessagesJson().setMessage("1", "向导锦囊删除失败");
		}
		return RESULT_AJAXJSON;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public CoupFacade getCoupFacade() {
		return coupFacade;
	}

	public void setCoupFacade(CoupFacade coupFacade) {
		this.coupFacade = coupFacade;
	}

	public String getCoupTitle() {
		return coupTitle;
	}

	public void setCoupTitle(String coupTitle) {
		this.coupTitle = coupTitle;
	}

	public String getCoupContent() {
		return coupContent;
	}

	public void setCoupContent(String coupContent) {
		this.coupContent = coupContent;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
