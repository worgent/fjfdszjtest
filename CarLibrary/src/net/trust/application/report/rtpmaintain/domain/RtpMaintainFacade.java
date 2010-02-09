package net.trust.application.report.rtpmaintain.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 保养管理
 * @author zhengmh
 *
 */
public interface RtpMaintainFacade {
	/**
	 * 查询保养管理总记录数
	 * @param map
	 * @return
	 */
    public int findRtpMaintainCount(HashMap map);
    /**
	 * 查询保养管理信息
	 * @param map
	 * @return
	 */
    public List findRtpMaintain(HashMap map);
}
