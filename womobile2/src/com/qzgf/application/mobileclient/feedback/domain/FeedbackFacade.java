package com.qzgf.application.mobileclient.feedback.domain;

import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.login.dto.UserInfo;
import com.qzgf.utils.servlet.UserSession;

/**
 * 任务接口
 * @author dpl
 *
 */
public interface FeedbackFacade { 

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	

	
    /*插入反馈表*/
	@SuppressWarnings("unchecked")
	public int insertFeedback(String jsonStr);
    /*获取反馈表名*/
	@SuppressWarnings("unchecked")
	public String selectTableName(String patternId);

}