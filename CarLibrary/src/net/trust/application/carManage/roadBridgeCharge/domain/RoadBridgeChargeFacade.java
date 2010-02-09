package net.trust.application.carManage.roadBridgeCharge.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 过路过桥费用管理
 *
 */
public interface RoadBridgeChargeFacade {
	/**
	 * 查询过路过桥费用总记录数
	 * @param map
	 * @return
	 */
	public int findRoadBridgeChargeCount(HashMap map);
	/**
	 * 查询过路过桥费用
	 * @param map
	 * @return
	 */
	public List findRoadBridgeCharge(HashMap map);
	/**
	 * 创建过路过桥费用
	 * @param map
	 * @return
	 */
	public int insertRoadBridgeCharge(HashMap map) throws Exception;
	/**
	 * 修改过路过桥费用
	 * @param map
	 * @return
	 */
	public int updateRoadBridgeCharge(HashMap map) throws Exception;
	/**
	 * 删除过路过桥费用
	 * @param map
	 * @return
	 */
	public int deleteRoadBridgeCharge(HashMap map) throws Exception;
}
