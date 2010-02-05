package com.qzgf.application.gpsdata.domain;

import java.util.HashMap;
import java.util.List;
/**
 */
public interface GpsDataFacade {
	/**
	 * 查询车辆记录
	 * @param map
	 * @return
	 */
	public List findGpsRecord(HashMap map);
	/**
	 * 查询车辆信息
	 * @param map
	 * @return
	 */
	public List findGpsCarMsg(HashMap map);
	/**
	 * 查询交结信息
	 * @param map
	 * @return
	 */
	public List findGpsWarnMsg(HashMap map);
	
	/**
	 * 定时器查询当天数据AreaId(103)的所有车辆数据信息
	 * @param map
	 * @return
	 */
	public List findTimeGpsRecord(HashMap map);
	/**
	 * 动态sql查询
	 * @param map
	 * @return
	 */
	public List DynamicSql(HashMap map);
	
}
