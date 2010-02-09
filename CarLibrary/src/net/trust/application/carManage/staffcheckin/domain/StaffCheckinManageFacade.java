package net.trust.application.carManage.staffcheckin.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 人员考勤管理
 *
 */
public interface StaffCheckinManageFacade {
	/**
	 * 人员考勤记录总数
	 * @param map
	 * @return
	 */
	public int findStaffCheckinCount(HashMap map);
	/**
	 * 人员考勤记录
	 * @param map
	 * @return
	 */
	public List findStaffCheckin(HashMap map);
}
