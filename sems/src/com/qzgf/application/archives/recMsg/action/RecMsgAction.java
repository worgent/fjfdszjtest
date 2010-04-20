package com.qzgf.application.archives.recMsg.action;
 
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.recMsg.domain.RecMsgFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;

/**
 * 网上下单
 * 
 * 
 */
@SuppressWarnings("serial")
public class RecMsgAction extends BaseAction {
	Log log = LogFactory.getLog(RecMsgAction.class);

	private RecMsgFacade recMsgFacade;
	private HashMap recMsg = new HashMap(); // 商品信息
	private List recMsgList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回
	private AjaxMessagesJson ajaxMessagesJson;

	// 入口函数
	public String execute() {

		// 从session中读取用户信息
		HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		// 当匿名登录时
		if (userInfo == null) {
			HashMap tmp = new HashMap();
			tmp.put("USERID", "0");
			userInfo = tmp;
		}
		//初始化基本信息
		search.put("pcreate_code", userInfo.get("ID").toString());//创建人
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			int i=recMsgFacade.countRecMsg(search);
			if(i==0){
				this.setAction("insert");
				return "edit";
			}else{
				return "list";
			}
		} 
		else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("recMsg.do?action=listdetail");
			setPageList(recMsgFacade.findRecMsgPage(search, pages));
			//数据为空时,跳到新增页面
			return "listdetail";
		} else if ("frame".equals(super.getAction())) {
			//框架信息列表
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("/archives/recMsg.do?action=frame");
			setPageList(recMsgFacade.findRecMsgPage(search, pages));
			return "frame";
		} else if ("new".equals(super.getAction())) {
			this.setAction("insert");
			return "edit";
		}else if ("insert".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i =recMsgFacade.insertRecMsg(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			recMsg = (HashMap) recMsgFacade.findRecMsg(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
		}else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = recMsgFacade.updateRecMsgById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
		} else if ("view".equals(super.getAction())) {
			recMsg=((HashMap)recMsgFacade.findRecMsg(search).get(0)); 
			return "view";
		} else if ("delete".equals(super.getAction())) {
			// 主表
			int i = recMsgFacade.deleteRecMsgById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
	}



	/**
	 * @return the pageList
	 */
	public PageList getPageList() {
		return pageList;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}



	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}



	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}



	/**
	 * @return the recMsgFacade
	 */
	public RecMsgFacade getRecMsgFacade() {
		return recMsgFacade;
	}



	/**
	 * @param recMsgFacade the recMsgFacade to set
	 */
	public void setRecMsgFacade(RecMsgFacade recMsgFacade) {
		this.recMsgFacade = recMsgFacade;
	}



	/**
	 * @return the recMsg
	 */
	public HashMap getRecMsg() {
		return recMsg;
	}



	/**
	 * @param recMsg the recMsg to set
	 */
	public void setRecMsg(HashMap recMsg) {
		this.recMsg = recMsg;
	}



	/**
	 * @return the recMsgList
	 */
	public List getRecMsgList() {
		return recMsgList;
	}



	/**
	 * @param recMsgList the recMsgList to set
	 */
	public void setRecMsgList(List recMsgList) {
		this.recMsgList = recMsgList;
	}
	
	
}
