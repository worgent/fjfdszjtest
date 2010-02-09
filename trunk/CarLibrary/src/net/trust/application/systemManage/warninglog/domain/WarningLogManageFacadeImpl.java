package net.trust.application.systemManage.warninglog.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 预警管理
 * @author chenqf
 *
 */
public class WarningLogManageFacadeImpl implements WarningLogManageFacade {
	private BaseSqlMapDAO baseSqlMapDAO;

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	/**
	 * 预警记录数总数
	 * @param map
	 * @return
	 */
	public int findWarningLogCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("WarningLogManage.findWarningLogCount", map));
	}
	/**
	 * 预警记录
	 * @param map
	 * @return
	 */
	public List findWarningLog(HashMap map){
		return baseSqlMapDAO.queryForList("WarningLogManage.findWarningLog", map);
	}
	/**
	 * 修改预警信息
	 * @param map
	 * @return
	 */
	public int updateWarningLog(HashMap map){
		return baseSqlMapDAO.update("WarningLogManage.updateWarningLog", map);
	}

}
