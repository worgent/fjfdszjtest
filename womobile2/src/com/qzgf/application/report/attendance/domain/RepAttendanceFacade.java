package com.qzgf.application.report.attendance.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.servlet.UserSession;

/**
 * 任务接口
 * @author dpl
 *
 */
public interface RepAttendanceFacade { 

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	
	public PageList findUser(HashMap map,Pages pages);
	
	
    /*插入反馈表*/
	@SuppressWarnings("unchecked")
	public List AttendanceRep(HashMap map);

}