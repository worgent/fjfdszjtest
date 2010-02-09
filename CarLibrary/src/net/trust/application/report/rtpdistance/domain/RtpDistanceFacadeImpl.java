package net.trust.application.report.rtpdistance.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public class RtpDistanceFacadeImpl implements RtpDistanceFacade {
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
	public int findRtpDistanceCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("RtpDistance.findRtpDistanceCount", map));
	}
	/**
	 * 查询行车记录
	 * @param map
	 * @return
	 */
	public List findRtpDistance(HashMap map){
		return baseSqlMapDAO.queryForList("RtpDistance.findRtpDistance", map);
	}
}
