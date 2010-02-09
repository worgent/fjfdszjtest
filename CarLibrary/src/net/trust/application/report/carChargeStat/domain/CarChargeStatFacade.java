package net.trust.application.report.carChargeStat.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 综合报表 -》 车辆费用统计
 *
 */
public interface CarChargeStatFacade {
	public List findCarChargeStat(HashMap map);
}
