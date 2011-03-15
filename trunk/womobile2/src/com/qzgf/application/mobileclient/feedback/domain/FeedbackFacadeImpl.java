package com.qzgf.application.mobileclient.feedback.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;


/**
 * 任务
 * @author dpl
 * 
 */
public class FeedbackFacadeImpl implements FeedbackFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(FeedbackFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	

	   /*插入反馈表*/
	@SuppressWarnings("unchecked")
	public int insertFeedback(String dataStr){
		int i=baseSqlMapDAO.update("feedback.insertFeedback", dataStr);
		return 1;
	}
	
    /*获取反馈表名*/
	@SuppressWarnings("unchecked")
	public String selectTableName(String patternId){
		String str=null;
		try{
		str=(String)baseSqlMapDAO.queryForObject("feedback.selectTableName", patternId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
