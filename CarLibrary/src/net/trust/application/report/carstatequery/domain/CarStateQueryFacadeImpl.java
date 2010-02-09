package net.trust.application.report.carstatequery.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 综合报表 -》 车辆状态查询
 * @author chenqf
 *
 */
public class CarStateQueryFacadeImpl implements CarStateQueryFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	/**
	 * 查询车辆状态记录总数
	 * @param map
	 * @return
	 */
	public int findCarStateQueryCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("CarStateQuery.findCarStateQueryCount", map));
	}
	/**
	 * 查询车辆状态记录
	 * @param map
	 * @return
	 */
	public List findCarStateQuery(HashMap map){
		return baseSqlMapDAO.queryForList("CarStateQuery.findCarStateQuery", map);
	}

}
