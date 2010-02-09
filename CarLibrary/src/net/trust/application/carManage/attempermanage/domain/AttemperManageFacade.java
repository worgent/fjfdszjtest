package net.trust.application.carManage.attempermanage.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 调度管理
 *
 */
public interface AttemperManageFacade {
	/**
	 * 调度管理记录数
	 * @param map
	 * @return
	 */
	public int findAttemperCount(HashMap map);
	/**
	 * 调度管理记录
	 * @param map
	 * @return
	 */
	public List findAttemper(HashMap map);
	/**
	 * 创建调度记录
	 * @param map
	 * @return
	 */
	public HashMap insertAttemper(HashMap map);
	/**
	 * 修改调度记录
	 * @param map
	 * @return
	 */
	public int updateAttemper(HashMap map);
	/**
	 * 删除调度记录
	 * @param map
	 * @return
	 */
	public int deleteAttemper(HashMap map);
	/**
	 * 查询GPS系统中与传入位置匹配的车辆数据
	 * @param map
	 * @return
	 */
	public List getGPSCarLocus(HashMap map);
}
