package net.trust.application.report.rtpmotorman.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 综合报表 -》 报警查询
 * @author chenqf
 *
 */
public interface RtpMotormanFacade {
	/**
	 * 查询保养管理总记录数
	 * @param map
	 * @return
	 */
    public int findRtpMotormanCount(HashMap map);
    /**
	 * 查询保养管理信息
	 * @param map
	 * @return
	 */
    public List findRtpMotorman(HashMap map);
}
