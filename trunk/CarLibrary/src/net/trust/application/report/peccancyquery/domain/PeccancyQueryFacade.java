package net.trust.application.report.peccancyquery.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 综合报表 -》 报警查询
 * @author chenqf
 *
 */
public interface PeccancyQueryFacade {
	/**
	 * 综合报表 -》 报警查询
	 * @param map
	 * @return
	 */
	public String findPeccancyQuery(HashMap map);
}
