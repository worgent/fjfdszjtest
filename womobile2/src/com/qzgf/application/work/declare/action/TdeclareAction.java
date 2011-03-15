package com.qzgf.application.work.declare.action;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.work.declare.domain.TdeclareFacade;
import com.qzgf.comm.Constant;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;


public class TdeclareAction  extends BaseAction{

	Log log = LogFactory.getLog(TdeclareAction.class);

	private TdeclareFacade tdeclareFacade;
	private HashMap tdeclare = new HashMap(); // 商品信息
	private List tdeclareList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private List head; // 特色商品信息
	private String xml; // 页面返回
	private String json; //页面返回
	private AjaxMessagesJson ajaxMessagesJson;
	
	private List missiongradeList;  // 任务级别
	private List declaretypeList; // 工作类别
	private List patternList;       // 任务级别

	// 入口函数
	@SuppressWarnings("unchecked")
	public String execute() {
		// 从session中读取用户信息
		UserSession userInfo= (UserSession)(ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY));

		//初始化基本信息
		search.put("maker", userInfo.getUserId());//创建人
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			HashMap  dictmap=new HashMap();
			dictmap.put("pid", "1003");
			declaretypeList=tdeclareFacade.finddeclaretype(dictmap); // 工作类别
			
			return "list";
		} 
		//反馈详细列表
		else if ("feeddetail".equals(super.getAction())) {
			//基础参数设定
			//search.put("pid", "2010112500000009");
			//2.表头信息
			head=tdeclareFacade.findfeedhead(search);
			HashMap tmp=(HashMap)(head.get(0));
			search.put("tablename",tmp.get("tablename").toString());
			//1.数据信息
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("tdailywork.do?action=feeddetail");
			setPageList(tdeclareFacade.findfeeddatePage(search, pages));
			return "feeddetail";
		}else if ("feedback".equals(super.getAction())) {
			int i =  tdeclareFacade.updateFeedbackById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//归档
		}
		else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("tdeclare.do?action=listdetail");
			setPageList(tdeclareFacade.findTdeclarePage(search, pages));
			return "listdetail";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1001");
			missiongradeList=tdeclareFacade.findmissiongrade(dictmap);  // 任务级别
			dictmap.put("pid", "1003");
			declaretypeList=tdeclareFacade.finddeclaretype(dictmap); // 工作类别
			patternList=tdeclareFacade.findpattern(dictmap);       // 任务级别
			
			this.setAction("insert");
			return "edit";
			//新增
		}else if ("insert".equals(super.getAction())) {
			//增加数据
			int i = tdeclareFacade.insertTdeclare(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			HashMap  dictmap=new HashMap();
			dictmap.put("pid", "1003");
			declaretypeList=tdeclareFacade.finddeclaretype(dictmap); // 工作类别
			search.clear();
			return "list";
			//编辑
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1001");
			missiongradeList=tdeclareFacade.findmissiongrade(dictmap);  // 任务级别
			dictmap.put("pid", "1003");
			declaretypeList=tdeclareFacade.finddeclaretype(dictmap); // 工作类别
			patternList=tdeclareFacade.findpattern(dictmap);       // 任务级别
			
			tdeclare = (HashMap) tdeclareFacade.findTdeclare(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
			//更新
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			//初始化基本信息
			search.put("maker", userInfo.getUserId());//创建人
			int i = tdeclareFacade.updateTdeclareById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			HashMap  dictmap=new HashMap();
			dictmap.put("pid", "1003");
			declaretypeList=tdeclareFacade.finddeclaretype(dictmap); // 工作类别
			search.clear();
			return "list";
			//查看
		} else if ("view".equals(super.getAction())) {
			tdeclare=((HashMap) tdeclareFacade.findTdeclare(search).get(0)); 
			return "view";
			//删除
		}else if ("delete".equals(super.getAction())) {
			int i = tdeclareFacade.deleteTdeclareById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			//多选删除
		}else if ("alldel".equals(super.getAction())) {
			search.put("pstate", "-1");//删除数据
			search.put("pexstate", "0");//删除数据
			int i = tdeclareFacade.allproTdeclareById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			//审批
		}else if ("auth".equals(super.getAction())) {
			search.put("pstate", "1");//修改状态
			int i = tdeclareFacade.allproTdeclareById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//中止
		}else if ("stop".equals(super.getAction())) {
			search.put("pstate", "3");//修改状态
			int i =  tdeclareFacade.allproTdeclareById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//归档
		}else if ("archive".equals(super.getAction())) {
			search.put("pstate", "9");//修改状态
			int i = tdeclareFacade.allproTdeclareById(search);
			json = "[{value:"+i+"}]";
    		return "json";
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
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * @return the tdeclareFacade
	 */
	public TdeclareFacade getTdeclareFacade() {
		return tdeclareFacade;
	}



	/**
	 * @param tdeclareFacade the tdeclareFacade to set
	 */
	public void setTdeclareFacade(TdeclareFacade tdeclareFacade) {
		this.tdeclareFacade = tdeclareFacade;
	}



	/**
	 * @return the tdeclare
	 */
	public HashMap getTdeclare() {
		return tdeclare;
	}



	/**
	 * @param tdeclare the tdeclare to set
	 */
	public void setTdeclare(HashMap tdeclare) {
		this.tdeclare = tdeclare;
	}



	/**
	 * @return the tdeclareList
	 */
	public List getTdeclareList() {
		return tdeclareList;
	}



	/**
	 * @param tdeclareList the tdeclareList to set
	 */
	public void setTdeclareList(List tdeclareList) {
		this.tdeclareList = tdeclareList;
	}



	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}



	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}


	/**
	 * Purpose      : 说明
	 * @return the head
	 */
	public List getHead() {
		return head;
	}


	/**
	 * Purpose      : 说明
	 * @param head the head to set
	 */
	
	public void setHead(List head) {
		this.head = head;
	}


	/**
	 * Purpose      : 说明
	 * @return the missiongradeList
	 */
	public List getMissiongradeList() {
		return missiongradeList;
	}


	/**
	 * Purpose      : 说明
	 * @param missiongradeList the missiongradeList to set
	 */
	
	public void setMissiongradeList(List missiongradeList) {
		this.missiongradeList = missiongradeList;
	}


	/**
	 * Purpose      : 说明
	 * @return the declaretypeList
	 */
	public List getDeclaretypeList() {
		return declaretypeList;
	}


	/**
	 * Purpose      : 说明
	 * @param declaretypeList the declaretypeList to set
	 */
	
	public void setDeclaretypeList(List declaretypeList) {
		this.declaretypeList = declaretypeList;
	}


	/**
	 * Purpose      : 说明
	 * @return the patternList
	 */
	public List getPatternList() {
		return patternList;
	}


	/**
	 * Purpose      : 说明
	 * @param patternList the patternList to set
	 */
	
	public void setPatternList(List patternList) {
		this.patternList = patternList;
	}
		
	
}
