package net.trust.application.report.rtpdistanceerror.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public class RtpDistanceErrorFacadeImpl implements RtpDistanceErrorFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	/**
	 * 查询行车记录总数
	 * @param map
	 * @return
	 */
	public int findRtpDistanceErrorCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("RtpDistanceError.findRtpDistanceErrorCount", map));
	}
	/**
	 * 查询行车记录
	 * @param map
	 * @return
	 */
	public List findRtpDistanceError(HashMap map){
		return baseSqlMapDAO.queryForList("RtpDistanceError.findRtpDistanceError", map);
	}
}
