package net.trust.application.datacollection.interfetchconfig.domain;

import java.util.HashMap;
import java.util.List;
/**
 * GPS接口数据采集规则配置 
 *
 */
public interface InterFatchConfigFacade {
	/**
	 * 接口数据采集规则记录数 
	 * @param map
	 * @return
	 */
	public int findInterFatchConfigCount(HashMap map);
	/**
	 * 接口数据采集规则记录
	 * @param map
	 * @return
	 */
	public List findInterFatchConfig(HashMap map);
	/**
	 * 创建接口数据采集规则
	 * @param map
	 * @return
	 */
	public int insertInterFatchConfig(HashMap map);
	/**
	 * 修改接口数据采集规则
	 * @param map
	 * @return
	 */
	public int updateInterFatchConfig(HashMap map);
	/**
	 * 删除接口数据采集规则
	 * @param map
	 * @return
	 */
	public int deleteInterFatchConfig(HashMap map);
}
