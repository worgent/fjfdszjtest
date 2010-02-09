package net.trust.application.report.runrecord.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public class RunRecordFacadeImpl implements RunRecordFacade {
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
	public int findRunRecordCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("RunRecord.findRunRecordCount", map));
	}
	/**
	 * 查询行车记录
	 * @param map
	 * @return
	 */
	public List findRunRecord(HashMap map){
		return baseSqlMapDAO.queryForList("RunRecord.findRunRecord", map);
	}
}
