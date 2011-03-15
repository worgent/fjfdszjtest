package com.qzgf.application.mobileclient.attendance.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.utils.servlet.UserSession;

/**
 * 任务接口
 * @author dpl
 *
 */
public interface AttendanceFacade { 

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	

	
    /*插入反馈表*/
	@SuppressWarnings("unchecked")
	public int insertAttendance(HashMap map);

}