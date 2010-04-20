package com.qzgf.application.archives.clientMsg.action;
 
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.clientMsg.domain.ClientMsgFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;

/**
 * 网上下单
 * 
 * 
 */
@SuppressWarnings("serial")
public class ClientMsgAction extends BaseAction {
	Log log = LogFactory.getLog(ClientMsgAction.class);

	private ClientMsgFacade clientMsgFacade;
	private HashMap clientMsg = new HashMap(); // 商品信息
	private List clientMsgList; // 特色商品信息
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
			int i=clientMsgFacade.countClientMsg(search);
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
			pages.setFileName("clientMsg.do?action=listdetail");
			setPageList(clientMsgFacade.findClientMsgPage(search, pages));
			//数据为空时,跳到新增页面
			return "listdetail";
		} else if ("frame".equals(super.getAction())) {
			//框架信息列表
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(3); // 设置每页大小
			pages.setFileName("/archives/clientMsg.do");
			setPageList(clientMsgFacade.findClientMsgPage(search, pages));
			
			long i=pages.getTotalNum();
			if(i==0){
				this.setAction("insertNew");
				return "newClient";
			}else{
				return "frame";
			}
		}else if ("frameex".equals(super.getAction())) {
			//框架信息列表
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("/archives/clientMsg.do?action=frameex");
			setPageList(clientMsgFacade.findClientMsgPage(search, pages));
			return "frameex";
		}  else if ("new".equals(super.getAction())) {
			//order = (HashMap) clientMsgFacade.findClientMsg(search).get(0); // 用户信息
			this.setAction("insert");
			return "edit";
		}else if ("newClient".equals(super.getAction())) {
			this.setAction("insertNew");
			return "newClient";
		}else if("insertNew".equals(super.getAction())){
			try{
				int i =clientMsgFacade.insertClientMsg(search);
				if(i!=1){
					this.getAjaxMessagesJson().setMessage("E_CLIENT_ADDFAILED", this.getText("添加联系人出错!"));
				}else{
					this.getAjaxMessagesJson().setMessage("0", this.getText("添加联系人成功!"));
				}
			}catch(Exception e){
				this.getAjaxMessagesJson().setMessage("E_CLIENT_ADDFAILED", this.getText("添加联系人出错!"));
			}
			
			return RESULT_AJAXJSON;
		}else if ("insert".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i =clientMsgFacade.insertClientMsg(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			
			clientMsg = (HashMap) clientMsgFacade.findClientMsg(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
		}else if ("editClient".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			
			clientMsg = (HashMap) clientMsgFacade.findClientMsg(search).get(0); // 用户信息
			this.setAction("updateClient");
			return "newClient";
		}else if ("updateClient".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			try{
				int i = clientMsgFacade.updateClientMsgById(search);
				
				if(i!=1){
					this.getAjaxMessagesJson().setMessage("E_CLINET_ADDFAILED", this.getText("修改联系人出错!"));
				}else{
					this.getAjaxMessagesJson().setMessage("0", this.getText("修改联系人成功!"));
				}
			}catch(Exception e){
				this.getAjaxMessagesJson().setMessage("E_CLINET_ADDFAILED", this.getText("修改联系人出错!"));
			}
			return RESULT_AJAXJSON;
		}else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = clientMsgFacade.updateClientMsgById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
		} else if ("view".equals(super.getAction())) {
			clientMsg=((HashMap)clientMsgFacade.findClientMsg(search).get(0)); 
			return "view";
		} else if ("delete".equals(super.getAction())) {
			// 主表
			int i = clientMsgFacade.deleteClientMsgById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}else if("deleteClient".equals(super.getAction())){
			try{
				clientMsgFacade.deleteClientMsgById(search);
				this.getAjaxMessagesJson().setMessage("0", this.getText("删除联系人成功!"));
				
			}catch(Exception e){
				this.getAjaxMessagesJson().setMessage("E_CLIENT_DELFAILED", this.getText("删除联系人出错!"));
			}
			
			return RESULT_AJAXJSON;
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



	/**
	 * @return the clientMsgFacade
	 */
	public ClientMsgFacade getClientMsgFacade() {
		return clientMsgFacade;
	}



	/**
	 * @param clientMsgFacade the clientMsgFacade to set
	 */
	public void setClientMsgFacade(ClientMsgFacade clientMsgFacade) {
		this.clientMsgFacade = clientMsgFacade;
	}



	/**
	 * @return the clientMsg
	 */
	public HashMap getClientMsg() {
		return clientMsg;
	}



	/**
	 * @param clientMsg the clientMsg to set
	 */
	public void setClientMsg(HashMap clientMsg) {
		this.clientMsg = clientMsg;
	}



	/**
	 * @return the clientMsgList
	 */
	public List getClientMsgList() {
		return clientMsgList;
	}



	/**
	 * @param clientMsgList the clientMsgList to set
	 */
	public void setClientMsgList(List clientMsgList) {
		this.clientMsgList = clientMsgList;
	}



	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}



	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}
	
	
}
