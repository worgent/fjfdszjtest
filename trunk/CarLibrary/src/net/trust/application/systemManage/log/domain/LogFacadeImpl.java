package net.trust.application.systemManage.log.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

public class LogFacadeImpl implements LogFacade{
	 BaseSqlMapDAO baseSqlMapDAO; 
	public int findLogCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("OperateLog.findLogCount",map)).intValue();
	}
	   public List findLogInfo(HashMap map){
		   return baseSqlMapDAO.queryForList("OperateLog.findLogInfo",map);
	   }
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	   
}
