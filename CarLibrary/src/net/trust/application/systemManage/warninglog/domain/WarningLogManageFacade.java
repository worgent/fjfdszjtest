package net.trust.application.systemManage.warninglog.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 预警管理
 * @author chenqf
 *
 */
public interface WarningLogManageFacade {
	/**
	 * 预警记录数总数
	 * @param map
	 * @return
	 */
	public int findWarningLogCount(HashMap map);
	/**
	 * 预警记录
	 * @param map
	 * @return
	 */
	public List findWarningLog(HashMap map);
	/**
	 * 修改预警信息
	 * @param map
	 * @return
	 */
	public int updateWarningLog(HashMap map);
}
