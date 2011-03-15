package com.qzgf.application.report.reportpattern.action;


import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.application.BaseAction;
import com.qzgf.application.report.reportpattern.domain.TreportpatternFacade;
import com.qzgf.comm.Constant;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;


public class TreportpatternAction  extends BaseAction{
	Log log = LogFactory.getLog(TreportpatternAction.class);
	private TreportpatternFacade treportpatternFacade;
	private HashMap treportpattern = new HashMap(); // 商品信息
	private List treportpatternList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回
	private String json; //页面返回
	private AjaxMessagesJson ajaxMessagesJson;
	
	private List callList; // 查询条件
	private List cList; // 已选查询条件
	private List fallList; // 列表头
	private List fList; // 已经列表头
	
	private List patternList; // 查询条件

	// 入口函数
	@SuppressWarnings("unchecked")
	public String execute() {
		// 从session中读取用户信息
		UserSession userInfo= (UserSession)(ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY));
		//初始化基本信息
		search.put("maker",  userInfo.getUserId());//创建人
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
				return "list";
	
		} 
		else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("treportpattern.do?action=listdetail");
			setPageList(treportpatternFacade.findTreportpatternPage(search, pages));
			return "listdetail";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			this.setAction("insert");
			return "edit";
			//新增
		}else if ("insert".equals(super.getAction())) {
			//增加数据
			int i = treportpatternFacade.insertTreportpattern(search);
			
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
			//编辑
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			treportpattern = (HashMap) treportpatternFacade.findTreportpattern(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
			//更新
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			//初始化基本信息
			//search.put("maker", userInfo.get("ID").toString());//创建人
			int i = treportpatternFacade.updateTreportpatternById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
			//查看
		} else if ("view".equals(super.getAction())) {
			treportpattern=((HashMap) treportpatternFacade.findTreportpattern(search).get(0)); 
			return "view";
			//删除
		}else if ("delete".equals(super.getAction())) {
		
			int i = treportpatternFacade.deleteTreportpatternById(search);
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
			int i = treportpatternFacade.allproTreportpatternById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
	}
	
	public String selectdata(){
		 cList=treportpatternFacade.findTreportpatternc(search);       //反馈模板
		 callList=treportpatternFacade.findTreportpatterncall(search);       //反馈模板
		 fList=treportpatternFacade.findTreportpatternf(search);       //反馈模板
		 fallList=treportpatternFacade.findTreportpatternfall(search);       //反馈模板
		 
		 JSONArray cjson = JSONArray.fromObject(cList); 
		 JSONArray calljson = JSONArray.fromObject(callList); 
		 JSONArray fjson = JSONArray.fromObject(fList); 
		 JSONArray falljson = JSONArray.fromObject(fallList); 
		 
		 //json="[{\"cjson\","+cjson.toString()+"},{\"calljson\","+calljson.toString()+"},{\"fjson\","+fjson.toString()+"},{\"falljson\","+falljson.toString()+"}]";
		 json="{\"cjson\":"+cjson.toString()+",\"calljson\":"+calljson.toString()+",\"fjson\":"+fjson.toString()+",\"falljson\":"+falljson.toString()+"}";
		 
		return "json";
	}

	
	/**
	 * 
	 * Purpose      : 查询模板
	 * @return
	 */
	public String selectpattern(){
		 patternList=treportpatternFacade.findpattern(search);       //反馈模板
		 JSONArray vjson = JSONArray.fromObject(patternList); 
		 json=vjson.toString();
		 return "json";
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
	 * @return the treportpatternFacade
	 */
	public TreportpatternFacade getTreportpatternFacade() {
		return treportpatternFacade;
	}



	/**
	 * @param treportpatternFacade the treportpatternFacade to set
	 */
	public void setTreportpatternFacade(TreportpatternFacade treportpatternFacade) {
		this.treportpatternFacade = treportpatternFacade;
	}



	/**
	 * @return the treportpattern
	 */
	public HashMap getTreportpattern() {
		return treportpattern;
	}



	/**
	 * @param treportpattern the treportpattern to set
	 */
	public void setTreportpattern(HashMap treportpattern) {
		this.treportpattern = treportpattern;
	}



	/**
	 * @return the treportpatternList
	 */
	public List getTreportpatternList() {
		return treportpatternList;
	}



	/**
	 * @param treportpatternList the treportpatternList to set
	 */
	public void setTreportpatternList(List treportpatternList) {
		this.treportpatternList = treportpatternList;
	}



	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}



	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}


	/**
	 * Purpose      : 说明
	 * @return the callList
	 */
	public List getCallList() {
		return callList;
	}


	/**
	 * Purpose      : 说明
	 * @param callList the callList to set
	 */
	
	public void setCallList(List callList) {
		this.callList = callList;
	}


	/**
	 * Purpose      : 说明
	 * @return the cList
	 */
	public List getCList() {
		return cList;
	}


	/**
	 * Purpose      : 说明
	 * @param list the cList to set
	 */
	
	public void setCList(List list) {
		cList = list;
	}


	/**
	 * Purpose      : 说明
	 * @return the fallList
	 */
	public List getFallList() {
		return fallList;
	}


	/**
	 * Purpose      : 说明
	 * @param fallList the fallList to set
	 */
	
	public void setFallList(List fallList) {
		this.fallList = fallList;
	}


	/**
	 * Purpose      : 说明
	 * @return the fList
	 */
	public List getFList() {
		return fList;
	}


	/**
	 * Purpose      : 说明
	 * @param list the fList to set
	 */
	
	public void setFList(List list) {
		fList = list;
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
