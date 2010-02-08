package com.qzgf.application.net.proLog.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.net.proLog.domain.ProLogFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 网上下单
 * 
 * 
 */
@SuppressWarnings("serial")
public class ProLogAction extends BaseAction {
	Log log = LogFactory.getLog(ProLogAction.class);

	private ProLogFacade proLogFacade;
	private HashMap proLog = new HashMap(); // 商品信息
	private List proLogList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回


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
		
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			return "list";
		} 
		if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("proLog.do?action=listdetail");
			setPageList(proLogFacade.findProLogPage(search, pages));
			return "listdetail";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			this.setAction("insert");
			return "edit";
		} else if ("insert".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = proLogFacade.insertProLog(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			// 用户过滤,根据采编人员2009-11-24
			// search.put("puserid", userInfo.get("USERID").toString());
			/*
			HashMap map=new HashMap();
			map.put("psort", "clienttype");//客户类型
			clienttypeList=userFacade.parameterValue(map);
			map.put("psort", "clientbalance");//支付方式
			clientbalanceList=userFacade.parameterValue(map);
			*/
			
			proLog = (HashMap) proLogFacade.findProLog(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = proLogFacade.updateProLogById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
		} else if ("send".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = proLogFacade.updateProLogById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
		}else if ("view".equals(super.getAction())) {
			proLog=((HashMap) proLogFacade.findProLog(search).get(0)); 
			return "view";
		} else if ("updateauding".equals(super.getAction())) {
			search.put("peditor_date", "1");//启用编辑日期,即审核日期
			search.put("pbill_type", "1");//审核通过
			//用户信息更新,包括管理人员的审核,用户的修改
 			int i = proLogFacade.updateProLogById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "edit";
		} else if ("delete".equals(super.getAction())) {
			// 主表
			int i = proLogFacade.deleteProLogById(search);
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



	/**
	 * @return the proLogFacade
	 */
	public ProLogFacade getProLogFacade() {
		return proLogFacade;
	}



	/**
	 * @param proLogFacade the proLogFacade to set
	 */
	public void setProLogFacade(ProLogFacade proLogFacade) {
		this.proLogFacade = proLogFacade;
	}



	/**
	 * @return the proLog
	 */
	public HashMap getProLog() {
		return proLog;
	}



	/**
	 * @param proLog the proLog to set
	 */
	public void setProLog(HashMap proLog) {
		this.proLog = proLog;
	}



	/**
	 * @return the proLogList
	 */
	public List getProLogList() {
		return proLogList;
	}



	/**
	 * @param proLogList the proLogList to set
	 */
	public void setProLogList(List proLogList) {
		this.proLogList = proLogList;
	}


}
