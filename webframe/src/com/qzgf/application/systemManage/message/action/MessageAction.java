package com.qzgf.application.systemManage.message.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.message.domain.MessageFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 框架测试模块
 * @author lsr
 */
@SuppressWarnings("serial")
public class MessageAction extends BaseAction {
	Log log = LogFactory.getLog(MessageAction.class);
	private MessageFacade messageFacade;
	@SuppressWarnings("unchecked")
	private List messageList;
	private HashMap message =new HashMap();
	private PageList pageList;
	private String xml;

	// 读取用户的配置信息2009-11-24
	private HashMap userInfo;
	
	
	public String execute() {
		try {
			// 从session中读取用户信息
			userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}
	//发件箱
	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("message.do?action=" + this.getAction());
		search.put("pstate", "1");
		//用户编号
		search.put("psendcode", userInfo.get("USERID").toString());
		this.setPageList(this.messageFacade.findMessagePage(search, pages));
		return "index";
	}
	
	//收件箱
	public String recmessage() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("message.do?action=" + this.getAction());
		search.put("pstate", "2");
		//用户编号
		search.put("preccode", userInfo.get("USERID").toString());
		this.setPageList(this.messageFacade.findMessagePage(search, pages));
		return "recmessage";
	}
	
	//转到站内消息页面
	public String insertmessage() {
		return "insertmessage";
	}

	//写站内消息
	public String insertmessagedata() {
		//用户编号
		search.put("psendcode", userInfo.get("USERID").toString());
		int i=this.messageFacade.insertMessage(search);
		search.clear();
		return index();
	}
	
	
	//删除站内消息
	public String delete() {
		//主表
		int i=this.messageFacade.deleteMessageById(search);
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
	}
	
	
	//查看收件箱信息
	public String viewmessage() {
		messageList=this.messageFacade.findMessage(search);
		message=(HashMap)messageList.get(0);
		return "viewmessage";
	}
	
	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the messageFacade
	 */
	public MessageFacade getMessageFacade() {
		return messageFacade;
	}

	/**
	 * @param messageFacade the messageFacade to set
	 */
	public void setMessageFacade(MessageFacade messageFacade) {
		this.messageFacade = messageFacade;
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
	/**
	 * @return the messageList
	 */
	public List getMessageList() {
		return messageList;
	}
	/**
	 * @param messageList the messageList to set
	 */
	public void setMessageList(List messageList) {
		this.messageList = messageList;
	}
	/**
	 * @return the message
	 */
	public HashMap getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(HashMap message) {
		this.message = message;
	}
}
