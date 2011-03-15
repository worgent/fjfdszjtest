package com.qzgf.application.work.dailywork.action;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.work.dailywork.domain.TdailyworkFacade;
import com.qzgf.comm.Constant;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;


public class TdailyworkAction  extends BaseAction{

	Log log = LogFactory.getLog(TdailyworkAction.class);

	 
	private TdailyworkFacade tdailyworkFacade;
	private HashMap tdailywork = new HashMap(); // 商品信息
	private List tdailyworkList; // 特色商品信息
	private List head; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回
	private String json; //页面返回
	private AjaxMessagesJson ajaxMessagesJson;
	
	
	private List missiongradeList;  // 任务级别
	private List dailyworktypeList; // 工作类别
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
			//转到框架页面
			/*int i=tdailyworkFacade.countTdailywork(search);
			if(i==0){
				this.setAction("insert");
				return "edit";
			}else{
				return "list";
			}*/
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1002");
			dailyworktypeList=tdailyworkFacade.finddailyworktype(dictmap); // 工作类别
	
			return "list";
		} 
		//反馈详细列表
		else if ("feeddetail".equals(super.getAction())) {
			//基础参数设定
			//search.put("pid", "2010112400000004");
			//2.表头信息
			head=tdailyworkFacade.findfeedhead(search);
			HashMap tmp=(HashMap)(head.get(0));
			search.put("tablename",tmp.get("tablename").toString());
			//1.数据信息
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("tdailywork.do?action=feeddetail");
			setPageList(tdailyworkFacade.findfeeddatePage(search, pages));
			return "feeddetail";
		}else if ("feedback".equals(super.getAction())) {
			int i =  tdailyworkFacade.updateFeedbackById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//归档
		}else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("tdailywork.do?action=listdetail");
			setPageList(tdailyworkFacade.findTdailyworkPage(search, pages));
			return "listdetail";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1001");
			missiongradeList=tdailyworkFacade.findmissiongrade(dictmap);  // 任务级别
			dictmap.put("pid", "1002");
			dailyworktypeList=tdailyworkFacade.finddailyworktype(dictmap); // 工作类别
			patternList=tdailyworkFacade.findpattern(dictmap);       // 任务级别
			
			this.setAction("insert");
			return "edit";
			//新增
		}else if ("insert".equals(super.getAction())) {
			//增加数据
			//search.put("pexecutors", "1");
			int i = tdailyworkFacade.insertTdailywork(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			search.clear();
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1002");
			dailyworktypeList=tdailyworkFacade.finddailyworktype(dictmap); // 工作类别
	
			return "list";
			//编辑
		} else if ("edit".equals(super.getAction())) {
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1001");
			missiongradeList=tdailyworkFacade.findmissiongrade(dictmap);  // 任务级别
			dictmap.put("pid", "1002");
			dailyworktypeList=tdailyworkFacade.finddailyworktype(dictmap); // 工作类别
			patternList=tdailyworkFacade.findpattern(dictmap);       // 任务级别
			// 2.编辑时取后台数据信息（得到单行记录）
			tdailywork = (HashMap) tdailyworkFacade.findTdailywork(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
			//更新
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			//初始化基本信息
			search.put("maker", userInfo.getUserId());//创建人
			//search.put("pexecutors", "1");
			int i = tdailyworkFacade.updateTdailyworkById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			search.clear();
			HashMap dictmap=new HashMap();
			dictmap.put("pid", "1002");
			dailyworktypeList=tdailyworkFacade.finddailyworktype(dictmap); // 工作类别
				
			return "list";
			//查看
		} else if ("view".equals(super.getAction())) {
			tdailywork=((HashMap) tdailyworkFacade.findTdailywork(search).get(0)); 
			return "view";
			//删除
		}else if ("delete".equals(super.getAction())) {
			int i = tdailyworkFacade.deleteTdailyworkById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			//多选删除
		}else if ("alldel".equals(super.getAction())) {
			search.put("pstate","-1");//删除数据
			search.put("pexstate", "0");//删除数据
			int i = tdailyworkFacade.allproTdailyworkById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			//审批
		}else if ("auth".equals(super.getAction())) {
			search.put("pstate", "1");//修改状态
			int i = tdailyworkFacade.allproTdailyworkById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//中止
		}else if ("stop".equals(super.getAction())) {
			search.put("pstate", "3");//修改状态
			int i =  tdailyworkFacade.allproTdailyworkById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//归档
		}else if ("archive".equals(super.getAction())) {
			search.put("pstate", "9");//修改状态
			int i = tdailyworkFacade.allproTdailyworkById(search);
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
	 * @return the tdailyworkFacade
	 */
	public TdailyworkFacade getTdailyworkFacade() {
		return tdailyworkFacade;
	}



	/**
	 * @param tdailyworkFacade the tdailyworkFacade to set
	 */
	public void setTdailyworkFacade(TdailyworkFacade tdailyworkFacade) {
		this.tdailyworkFacade = tdailyworkFacade;
	}



	/**
	 * @return the tdailywork
	 */
	public HashMap getTdailywork() {
		return tdailywork;
	}



	/**
	 * @param tdailywork the tdailywork to set
	 */
	public void setTdailywork(HashMap tdailywork) {
		this.tdailywork = tdailywork;
	}



	/**
	 * @return the tdailyworkList
	 */
	public List getTdailyworkList() {
		return tdailyworkList;
	}



	/**
	 * @param tdailyworkList the tdailyworkList to set
	 */
	public void setTdailyworkList(List tdailyworkList) {
		this.tdailyworkList = tdailyworkList;
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
	 * @return the dailyworktypeList
	 */
	public List getDailyworktypeList() {
		return dailyworktypeList;
	}


	/**
	 * Purpose      : 说明
	 * @param dailyworktypeList the dailyworktypeList to set
	 */
	
	public void setDailyworktypeList(List dailyworktypeList) {
		this.dailyworktypeList = dailyworktypeList;
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
