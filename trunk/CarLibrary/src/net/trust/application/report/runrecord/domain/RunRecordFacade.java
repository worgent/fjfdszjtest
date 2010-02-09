package net.trust.application.report.runrecord.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public interface RunRecordFacade {
	/**
	 * 查询行车记录总数
	 * @param map
	 * @return
	 */
	public int findRunRecordCount(HashMap map);
	/**
	 * 查询行车记录
	 * @param map
	 * @return
	 */
	public List findRunRecord(HashMap map);
}
