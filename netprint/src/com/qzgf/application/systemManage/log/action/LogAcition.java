package com.qzgf.application.systemManage.log.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork.ActionContext;
import com.qzgf.PaginactionAction;
import com.qzgf.application.systemManage.log.domain.LogFacade;
import com.qzgf.application.systemManage.log.dto.OperateLog;
import com.qzgf.application.systemManage.manager.dto.UserInfo;

@SuppressWarnings("serial")
public class LogAcition extends PaginactionAction {
	private Log log = LogFactory.getLog(LogAcition.class);
	private OperateLog operateLog = new OperateLog();
	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	@SuppressWarnings("unchecked")
	private List logList;
	private LogFacade logFacade;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("pdeptid", userInfo.getDeptId());
		search.put("commstaffId", userInfo.getStaffId());
		if (super.queryFlag == 0) {// 判断查询状态
			search.put("operateLog", operateLog);
			super.setPageRecord(15);// 设置页面单页显示总记录数
			super.setParameter(search); // 设置查询参数
			super.setCount(logFacade.findLogCount(search));// 获取查询结果总记录数
		}
		search = (HashMap) super.getParameter();
		logList = logFacade.findLogInfo(search);
		return "search";
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public OperateLog getOperateLog() {
		return operateLog;
	}

	public void setOperateLog(OperateLog operateLog) {
		this.operateLog = operateLog;
	}

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	@SuppressWarnings("unchecked")
	public List getLogList() {
		return logList;
	}

	@SuppressWarnings("unchecked")
	public void setLogList(List logList) {
		this.logList = logList;
	}

	public LogFacade getLogFacade() {
		return logFacade;
	}

	public void setLogFacade(LogFacade logFacade) {
		this.logFacade = logFacade;
	}
}
