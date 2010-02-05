package com.qzgf.application.netprint.clientmsg.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
import com.qzgf.PaginactionAction;
import com.qzgf.application.netprint.clientmsg.domain.ClientmsgFacade;
import com.qzgf.application.systemManage.manager.dto.UserInfo;

/**
 * 客户信息管理
 * 
 * @author lsr
 * 
 */
@SuppressWarnings("serial")
public class ClientmsgAction extends PaginactionAction {
	// 用于调试或打印信息的Log

	Log log = LogFactory.getLog(ClientmsgAction.class);
	private ClientmsgFacade clientmsgFacade;
	@SuppressWarnings("unchecked")
	private List clientmsgList;
	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private String actionType;
	private String action;
	private String xml;
	private String json;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("pdeptid", userInfo.getDeptId());
		search.put("commstaffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType) {
			//列出某一工号可查看的租赁合同
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(clientmsgFacade.findClientmsgCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			clientmsgList = clientmsgFacade.findClientmsg(search);
			return "search";
		} else if ("edit".equals(actionType)) {
            //列出某一租赁合同信息
			search = (HashMap) (clientmsgFacade.findClientmsg(search).get(0));
			action = "update";
			return "edit";
		} else if ("new".equals(actionType)) {
			//进入到租赁合同添加页面
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
			//添加一条新的租赁合同
			search.put("createMan", userInfo.getStaffId());// 创建人
			int i = clientmsgFacade.insertClientmsg(search);
			if (i == 1) {
				super.addActionMessage("添加客户信息,操作成功");
				//search.clear();
				addActionScript("try{");
				addActionScript("parent.document.ifrm_SendClientMsg.window.location.reload();");
				addActionScript("}catch(e){ }");
				
				
				addActionScript("try{");
				addActionScript("parent.document.ifrm_RecClientMsg.window.location.reload();");
				addActionScript("}catch(e){ }");

				return SUCCESS;
			} else {
				search.clear();
				super.addActionMessage("添加客户信息,操作失败");
				return ERROR;
			}
		} else if ("update".equals(actionType)) {
			//修改租赁合同信息
			int i = clientmsgFacade.updateClientmsg(search);
			if (i == 1) {
				super.addActionMessage("修改客户信息,操作成功");
				addActionScript("try{");
				addActionScript("parent.document.ifrm_SendClientMsg.window.location.reload();");
				addActionScript("}catch(e){ }");
				
				
				addActionScript("try{");
				addActionScript("parent.document.ifrm_RecClientMsg.window.location.reload();");
				addActionScript("}catch(e){ }");
				return SUCCESS;
			} else {
				super.addActionMessage("修改客户信息,操作失败");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			//删除某一租赁合同
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = clientmsgFacade.deleteClientmsg(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("view".equals(actionType)) {
			//查看某一租赁合同
			search = (HashMap) (clientmsgFacade.findClientmsg(search).get(0));
			return "view";
		} //EMS邮件信息调用,寄件人信息与收件人信息
		else if("exsearch".equals(actionType))
		{
			//列出某一工号可查看的租赁合同
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(clientmsgFacade.findClientmsgCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			clientmsgList = clientmsgFacade.findClientmsg(search);
			return "exsearch";
		}
		return ERROR;
	}

    //省份Ajax的实现
	public String ajaxProvince(){
//		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
//		search.put("staffId", userInfo.getStaffId());

		//search.put("belongCompany", belongCompany);
		Iterator it = clientmsgFacade.findProvince(search).iterator();
		StringBuffer sb = new StringBuffer();
		HashMap tmp = null;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			if (null != sb && !"".equals(sb.toString())){
				sb.append(",");
    		}
			sb.append("{");
			sb.append("\"text\":\"").append(tmp.get("the_name")).append("\"");
			sb.append(",\"id\":\"").append(tmp.get("the_code")).append("\"");
			sb.append(",\"leaf\":true");
			sb.append("}");
		}
		json = "[" +sb.toString()+ "]";
		return "json";
	}	
    //地市Ajax的实现
	public String ajaxCity(){
//		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
//		search.put("staffId", userInfo.getStaffId());

		//search.put("belongCompany", belongCompany);
		Iterator it = clientmsgFacade.findCity(search).iterator();
		StringBuffer sb = new StringBuffer();
		HashMap tmp = null;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			if (null != sb && !"".equals(sb.toString())){
				sb.append(",");
    		}
			sb.append("{");
			sb.append("\"text\":\"").append(tmp.get("the_name")).append("\"");
			sb.append(",\"id\":\"").append(tmp.get("the_code")).append("\"");
			sb.append(",\"leaf\":true");
			sb.append("}");
		}
		json = "[" +sb.toString()+ "]";
		return "json";
	}
    //县区Ajax的实现
	public String ajaxCounty(){
//		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
//		search.put("staffId", userInfo.getStaffId());

		//search.put("belongCompany", belongCompany);
		Iterator it = clientmsgFacade.findCounty(search).iterator();
		StringBuffer sb = new StringBuffer();
		HashMap tmp = null;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			if (null != sb && !"".equals(sb.toString())){
				sb.append(",");
    		}
			sb.append("{");
			sb.append("\"text\":\"").append(tmp.get("the_name")).append("\"");
			sb.append(",\"id\":\"").append(tmp.get("the_code")).append("\"");
			sb.append(",\"leaf\":true");
			sb.append("}");
		}
		json = "[" +sb.toString()+ "]";
		return "json";
	}
	
	public String ajaxClientMsg(){
		//用户信息的取得,属于客户权限的读取有关
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("pdeptid", userInfo.getDeptId());
		search.put("commstaffId", userInfo.getStaffId());
		//得到满足条件的信息
		String name=search.get("pname").toString();
		search.remove("pname");//去除用户查询,仅通过电话查询
		List ls=clientmsgFacade.findClientmsg(search);
 		java.lang.StringBuffer sb = new StringBuffer();
		if(ls.size()>0){
			HashMap pclient =(HashMap)ls.get(0);
			sb.append("<ClientMsg>");
			sb.append("	<code>").append(pclient.get("code")).append("</code>");
			sb.append("	<name>").append(pclient.get("name")).append("</name>");
			sb.append("	<unit>").append(pclient.get("unit")).append("</unit>");
			sb.append("	<address>").append(pclient.get("address")).append("</address>");
			sb.append("	<tel>").append(pclient.get("tel")).append("</tel>");
			sb.append("	<post>").append(pclient.get("post")).append("</post>");
			sb.append("</ClientMsg>");
		}else
		{
			sb.append("<ClientMsg>");
			sb.append("	<code>").append("").append("</code>");
			sb.append("	<name>").append(name).append("</name>");
			sb.append("	<unit>").append("").append("</unit>");
			sb.append("	<address>").append("").append("</address>");
			sb.append("	<tel>").append(search.get("ptel")).append("</tel>");
			sb.append("	<post>").append("").append("</post>");
			sb.append("</ClientMsg>");			
		}
		xml = sb.toString();
		return "xml";
	}
	
	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}


	/**
	 * @return the clientmsgFacade
	 */
	public ClientmsgFacade getClientmsgFacade() {
		return clientmsgFacade;
	}


	/**
	 * @param clientmsgFacade the clientmsgFacade to set
	 */
	public void setClientmsgFacade(ClientmsgFacade clientmsgFacade) {
		this.clientmsgFacade = clientmsgFacade;
	}


	/**
	 * @return the clientmsgList
	 */
	public List getClientmsgList() {
		return clientmsgList;
	}


	/**
	 * @param clientmsgList the clientmsgList to set
	 */
	public void setClientmsgList(List clientmsgList) {
		this.clientmsgList = clientmsgList;
	}


	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}


	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}
}
