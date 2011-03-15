package com.qzgf.application.mobileclient.mission.domain;

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
public class MissionFacadeImpl implements MissionFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(MissionFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	

	@SuppressWarnings("unchecked")
	public List findDailywork(String executorID) {
		List lt = baseSqlMapDAO.queryForList("mission.findDailywork", executorID);
		return lt;
	}
	
	@SuppressWarnings("unchecked")
	public List findTomonitor(String executorID) {
		List lt = baseSqlMapDAO.queryForList("mission.findTomonitor", executorID);
		return lt;
	}
	@SuppressWarnings("unchecked")
	public List findDeclare(String executorID) {
		List lt = baseSqlMapDAO.queryForList("mission.findDeclare", executorID);
		return lt;
	}	
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
