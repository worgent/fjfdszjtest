package net.trust.application.report.carstatequery.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 综合报表 -》 车辆状态查询
 * @author chenqf
 *
 */
public interface CarStateQueryFacade {
	/**
	 * 查询车辆状态记录总数
	 * @param map
	 * @return
	 */
	public int findCarStateQueryCount(HashMap map);
	/**
	 * 查询车辆状态记录
	 * @param map
	 * @return
	 */
	public List findCarStateQuery(HashMap map);
}
