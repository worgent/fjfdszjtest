package com.qzgf.application.systemManage.log.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

public class LogFacadeImpl implements LogFacade {
	BaseSqlMapDAO baseSqlMapDAO;

	@SuppressWarnings("unchecked")
	public int findLogCount(HashMap map) {
		if(!map.get("pdeptid").toString().equals("110")){
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		}
		return ((Integer) baseSqlMapDAO.queryForObject("OperateLog.findLogCount", map)).intValue();
	}

	@SuppressWarnings("unchecked")
	public List findLogInfo(HashMap map) {
		if(!map.get("pdeptid").toString().equals("110")){
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		}
		return baseSqlMapDAO.queryForList("OperateLog.findLogInfo", map);
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

}
