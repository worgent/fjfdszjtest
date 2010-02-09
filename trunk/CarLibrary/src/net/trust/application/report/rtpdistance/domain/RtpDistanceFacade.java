package net.trust.application.report.rtpdistance.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public interface RtpDistanceFacade {
	/**
	 * 查询行车记录总数
	 * @param map
	 * @return
	 */
	public int findRtpDistanceCount(HashMap map);
	/**
	 * 查询行车记录
	 * @param map
	 * @return
	 */
	public List findRtpDistance(HashMap map);
}
