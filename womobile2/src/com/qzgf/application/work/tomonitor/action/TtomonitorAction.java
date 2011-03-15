package com.qzgf.application.work.tomonitor.action;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.work.tomonitor.domain.TtomonitorFacade;
import com.qzgf.comm.Constant;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;


public class TtomonitorAction  extends BaseAction{

	Log log = LogFactory.getLog(TtomonitorAction.class);

	private TtomonitorFacade ttomonitorFacade;
	private HashMap ttomonitor = new HashMap(); // 商品信息
	private List ttomonitorList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private List head; // 特色商品信息
	private String xml; // 页面返回
	private String json; //页面返回
	private AjaxMessagesJson ajaxMessagesJson;
	
	private List missiongradeList;  // 任务级别
	private List tomonitortypeList; // 工作类别
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
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1004");
			tomonitortypeList=ttomonitorFacade.findtomonitortype(dictmap); // 工作类别
			return "list";
		} 
		//反馈详细列表
		else if ("feeddetail".equals(super.getAction())) {
			//基础参数设定
			//search.put("pid", "2010112500000008");
			//2.表头信息
			head=ttomonitorFacade.findfeedhead(search);
			HashMap tmp=(HashMap)(head.get(0));
			search.put("tablename",tmp.get("tablename").toString());
			//1.数据信息
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("tdailywork.do?action=feeddetail");
			setPageList(ttomonitorFacade.findfeeddatePage(search, pages));
			return "feeddetail";
		}else if ("feedback".equals(super.getAction())) {
			int i =  ttomonitorFacade.updateFeedbackById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//归档
		}
		else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("ttomonitor.do?action=listdetail");
			setPageList(ttomonitorFacade.findTtomonitorPage(search, pages));
			return "listdetail";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1001");
			missiongradeList=ttomonitorFacade.findmissiongrade(dictmap);  // 任务级别
			dictmap.put("pid", "1004");
			tomonitortypeList=ttomonitorFacade.findtomonitortype(dictmap); // 工作类别
			patternList=ttomonitorFacade.findpattern(dictmap);       // 任务级别
			
			this.setAction("insert");
			return "edit";
			//新增
		}else if ("insert".equals(super.getAction())) {
			//增加数据
			int i = ttomonitorFacade.insertTtomonitor(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1004");
			tomonitortypeList=ttomonitorFacade.findtomonitortype(dictmap); // 工作类别
			search.clear();
			return "list";
			//编辑
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1001");
			missiongradeList=ttomonitorFacade.findmissiongrade(dictmap);  // 任务级别
			dictmap.put("pid", "1004");
			tomonitortypeList=ttomonitorFacade.findtomonitortype(dictmap); // 工作类别
			patternList=ttomonitorFacade.findpattern(dictmap);       // 任务级别
			
			ttomonitor = (HashMap) ttomonitorFacade.findTtomonitor(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
			//更新
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			//初始化基本信息
			search.put("maker", userInfo.getUserId());//创建人
			int i = ttomonitorFacade.updateTtomonitorById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1004");
			tomonitortypeList=ttomonitorFacade.findtomonitortype(dictmap); // 工作类别
			search.clear();
			return "list";
			//查看
		} else if ("view".equals(super.getAction())) {
			ttomonitor=((HashMap) ttomonitorFacade.findTtomonitor(search).get(0)); 
			return "view";
			//删除
		}else if ("delete".equals(super.getAction())) {
			int i = ttomonitorFacade.deleteTtomonitorById(search);
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
			int i = ttomonitorFacade.allproTtomonitorById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			//审批
		}else if ("auth".equals(super.getAction())) {
			search.put("pstate", "1");//修改状态
			int i = ttomonitorFacade.allproTtomonitorById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//中止
		}else if ("stop".equals(super.getAction())) {
			search.put("pstate", "3");//修改状态
			int i =  ttomonitorFacade.allproTtomonitorById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//归档
		}else if ("archive".equals(super.getAction())) {
			search.put("pstate", "9");//修改状态
			int i = ttomonitorFacade.allproTtomonitorById(search);
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
	 * @return the ttomonitorFacade
	 */
	public TtomonitorFacade getTtomonitorFacade() {
		return ttomonitorFacade;
	}



	/**
	 * @param ttomonitorFacade the ttomonitorFacade to set
	 */
	public void setTtomonitorFacade(TtomonitorFacade ttomonitorFacade) {
		this.ttomonitorFacade = ttomonitorFacade;
	}



	/**
	 * @return the ttomonitor
	 */
	public HashMap getTtomonitor() {
		return ttomonitor;
	}



	/**
	 * @param ttomonitor the ttomonitor to set
	 */
	public void setTtomonitor(HashMap ttomonitor) {
		this.ttomonitor = ttomonitor;
	}



	/**
	 * @return the ttomonitorList
	 */
	public List getTtomonitorList() {
		return ttomonitorList;
	}



	/**
	 * @param ttomonitorList the ttomonitorList to set
	 */
	public void setTtomonitorList(List ttomonitorList) {
		this.ttomonitorList = ttomonitorList;
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
	 * @return the tomonitortypeList
	 */
	public List getTomonitortypeList() {
		return tomonitortypeList;
	}


	/**
	 * Purpose      : 说明
	 * @param tomonitortypeList the tomonitortypeList to set
	 */
	
	public void setTomonitortypeList(List tomonitortypeList) {
		this.tomonitortypeList = tomonitortypeList;
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
