package com.qzgf.application.report.attendance.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.report.attendance.domain.RepAttendanceFacade;
import com.qzgf.comm.Constant;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;
/**
 * 任务管理
 * dpl
 */
@SuppressWarnings("serial")
public class RepAttendanceAction extends BaseAction {
	Log log = LogFactory.getLog(RepAttendanceAction.class);
	// 实现类方法
	private RepAttendanceFacade repattendanceFacade;
	private PageList pageList; // 封装分页信息
	private List attendanceList;//位置列表
	private String xml; // 页面返回
	private String json; //页面返回
	private AjaxMessagesJson ajaxMessagesJson;	

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public RepAttendanceFacade getRepattendanceFacade() {
		return repattendanceFacade;
	}

	public void setRepattendanceFacade(RepAttendanceFacade repattendanceFacade) {
		this.repattendanceFacade = repattendanceFacade;
	}

	public String execute() {
		// 从session中读取用户信息
		UserSession userInfo= (UserSession)(ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY));

		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			return "list";
	
		}else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("repattendance.do?action=listdetail");
			setPageList(repattendanceFacade.findUser(search, pages));
			return "listdetail";
		} else if ("view".equals(super.getAction())) {
			attendanceList=repattendanceFacade.AttendanceRep(search); 
			JSONArray jo= JSONArray.fromObject(attendanceList);
			json=jo.toString();
			return "view";
		
		}
		return ERROR;
	}	
	
	public String index() throws Exception {
	  return "";
	}

	public List getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List attendanceList) {
		this.attendanceList = attendanceList;
	}
	


}
