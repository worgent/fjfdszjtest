package net.trust.application.carManage.servicingmanage.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 车辆维修管理
 *
 */
public interface ServicingManageFacade {
	/**
	 * 查询车辆维修记录总数 
	 * @param map
	 * @return
	 */
	public int findServiceingCount(HashMap map);
	/**
	 * 查询车辆维修记录
	 * @param map
	 * @return
	 */
	public List findServiceing(HashMap map);
	/**
	 * 车辆维修登记 
	 * @param map
	 * @return
	 */
	public int insertServiceingApply(HashMap map) throws Exception;
	/**
	 * 修改车辆维修信息 
	 * @param map
	 * @return
	 */
	public int updateServiceingApply(HashMap map) throws Exception;
	/**
	 * 删除车辆维修记录 
	 * @param map
	 * @return
	 */
	public int deleteServiceingApply(HashMap map) throws Exception;
	/**
	 * 车辆维修明细记录 
	 * @param map
	 * @return
	 */
	public List findServiceingDetail(HashMap map);
	/**
	 * 添加车辆维修明细 
	 * @param map
	 * @return
	 */
	public int insertServiceingBooking(HashMap servicingMap, HashMap detail) throws Exception;
	/**
	 * 修改维修明细记录
	 * @param map
	 * @return
	 */
	public int updateServiceingBooking(HashMap servicingMap, HashMap detail) throws Exception;
	/**
	 * 删除车辆维修明细 
	 * @param map
	 * @return
	 */
	public int deleteServiceingBooking(HashMap map) throws Exception;
	
	/**
	 * 删除维修明细
	 * @param map
	 * @return
	 */
	public int deleteServiceingDetail(HashMap map);
}
