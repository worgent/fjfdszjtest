package net.trust.application.report.rtpputonsteam.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 综合报表-->加油管理(里程数与加油量异常)
 * @author zhemgmh
 *
 */
public class RtpPutonsteamFacadeImpl implements RtpPutonsteamFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询加油总记录信息
	 * @param map
	 * @return
	 */
    public int findRtpPutonsteamCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("RtpPutonsteam.findRtpPutonsteamCount",map)).intValue();
    }
    /**
	 * 查询加油信息
	 * @param map
	 * @return
	 */
    public List findRtpPutonsteam(HashMap map){
    	return baseSqlMapDAO.queryForList("RtpPutonsteam.findRtpPutonsteam",map);
    }
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
