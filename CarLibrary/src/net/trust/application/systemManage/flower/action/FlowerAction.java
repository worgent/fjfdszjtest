package net.trust.application.systemManage.flower.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.systemManage.flower.domain.FlowerFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;


public class FlowerAction extends PaginactionAction{
	// 用于调试或打印信息的Log
	private Log log = LogFactory.getLog(FlowerAction.class);
	private FlowerFacade flowerFacade;//接口声明
	
    //用户前台交互的List数据
	private List flowerDefineList;//流程定义
	private List flowerNodeList;  //流程结点
	
	//数据存放数组
	private HashMap search = new HashMap();
	private HashMap flowerNode = new HashMap();

	//基本类型
	private String actionType;
	private String action;
	private String xml;
	
	public String execute() throws Exception {
		//用户中的seesion信息处理
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(flowerFacade.findFlowerDefineCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			flowerDefineList = flowerFacade.findFlowerDefine(search);
			return "search";
		} else if ("new".equals(actionType)) {
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
			search.put("createMan", userInfo.getStaffId());// 创建人
			//主表与子表一起保存，其中用search的List中的"st"保存处理结果。
			search = flowerFacade.insertFlowerDefine(search, flowerNode);
			int i = Integer.valueOf(search.get("st").toString());
			if (i > 0) {
				super.addActionMessage("添加流程定义,操作成功");
				addActionScript("parent.document.ifrm_FlowerInfo.window.location.reload();");
				return  SUCCESS;
			} else {
				super.addActionMessage("添加派车信息,操作失败");
				return ERROR;
			}

		} else if ("edit".equals(actionType)) {
			flowerNodeList = flowerFacade.findFlowerNode(search);
			search = (HashMap) (flowerFacade.findFlowerDefine(search).get(0));
			action = "update";
			return "edit";
		} else if ("update".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			//主表与子表一起更新，其中用search的List中的"st"保存处理结果。
			search = flowerFacade.updateFlowerDefine(search, flowerNode);
			int i = Integer.valueOf(search.get("st").toString());
			if (i > 0) {
				super.addActionMessage("修改派车信息,操作成功");
				addActionScript("parent.document.ifrm_FlowerInfo.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("修改派车信息,操作失败");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());// 编辑人
			int i = flowerFacade.deleteFlowerDefine(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("view".equals(actionType)) {
			flowerNodeList = flowerFacade.findFlowerNode(search);
			search = (HashMap) (flowerFacade.findFlowerDefine(search).get(0));
			return "view";
		}
		return ERROR;
	}

	/**
	 * @return the flowerFacade
	 */
	public FlowerFacade getFlowerFacade() {
		return flowerFacade;
	}

	/**
	 * @param flowerFacade the flowerFacade to set
	 */
	public void setFlowerFacade(FlowerFacade flowerFacade) {
		this.flowerFacade = flowerFacade;
	}

	/**
	 * @return the flowerDefineList
	 */
	public List getFlowerDefineList() {
		return flowerDefineList;
	}

	/**
	 * @param flowerDefineList the flowerDefineList to set
	 */
	public void setFlowerDefineList(List flowerDefineList) {
		this.flowerDefineList = flowerDefineList;
	}

	/**
	 * @return the flowerNodeList
	 */
	public List getFlowerNodeList() {
		return flowerNodeList;
	}

	/**
	 * @param flowerNodeList the flowerNodeList to set
	 */
	public void setFlowerNodeList(List flowerNodeList) {
		this.flowerNodeList = flowerNodeList;
	}

	/**
	 * @return the search
	 */
	public HashMap getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(HashMap search) {
		this.search = search;
	}

	/**
	 * @return the flowerNode
	 */
	public HashMap getFlowerNode() {
		return flowerNode;
	}

	/**
	 * @param flowerNode the flowerNode to set
	 */
	public void setFlowerNode(HashMap flowerNode) {
		this.flowerNode = flowerNode;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
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
}
