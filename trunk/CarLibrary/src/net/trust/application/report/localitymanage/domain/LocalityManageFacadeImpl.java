package net.trust.application.report.localitymanage.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 综合报表 -》 位置管理
 * @author chenqf
 *
 */
public class LocalityManageFacadeImpl implements LocalityManageFacade {
	public BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	public List findLocalityManage(HashMap map) {
		return baseSqlMapDAO.queryForList("LocalityManage.findLocalityManage", map);
	}

	public int findLocalityManageCount(HashMap map) {
		return ((Integer)baseSqlMapDAO.queryForObject("LocalityManage.findLocalityManageCount", map));
	}

}
