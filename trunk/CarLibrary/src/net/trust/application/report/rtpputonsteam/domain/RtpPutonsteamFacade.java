package net.trust.application.report.rtpputonsteam.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 综合报表-->加油管理(里程数与加油量异常)
 * @author zhemgmh
 *
 */
public interface RtpPutonsteamFacade {
	/**
	 * 查询加油信息总记录数
	 * @param map
	 * @return
	 */
    public int findRtpPutonsteamCount(HashMap map);
    /**
	 * 查询加油信息
	 * @param map
	 * @return
	 */
    public List findRtpPutonsteam(HashMap map);
}
