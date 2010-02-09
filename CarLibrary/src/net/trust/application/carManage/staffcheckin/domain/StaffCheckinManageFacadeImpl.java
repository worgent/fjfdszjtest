package net.trust.application.carManage.staffcheckin.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 人员考勤管理
 *
 */
public class StaffCheckinManageFacadeImpl implements StaffCheckinManageFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	/**
	 * 人员考勤记录
	 * @param map
	 * @return
	 */
	public List findStaffCheckin(HashMap map) {
		
		return baseSqlMapDAO.queryForList("StaffCheckinManage.findStaffCheckin", map);
	}
	/**
	 * 人员考勤记录总数
	 * @param map
	 * @return
	 */
	public int findStaffCheckinCount(HashMap map) {
		return ((Integer)baseSqlMapDAO.queryForObject("StaffCheckinManage.findStaffCheckinCount", map)).intValue();
	}
}
