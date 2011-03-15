package com.qzgf.application.mobileclient.mission.domain;

import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.utils.servlet.UserSession;

/**
 * 任务接口
 * @author dpl
 *
 */
public interface MissionFacade { 

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	

	
    /*日常工作*/
	@SuppressWarnings("unchecked")
	public List findDailywork(String executorID);
	
    /*督办工作*/
	@SuppressWarnings("unchecked")
	public List findTomonitor(String executorID);
    /*督办工作*/
	@SuppressWarnings("unchecked")
	public List findDeclare(String executorID);	
}