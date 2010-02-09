package net.trust.application.baseArchives.carInfo.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 车辆信息
 * @author zhengmh
 *
 */
public interface CarFacade {
	/**
	 * 查询车辆总记录信息
	 * @param map
	 * @return
	 */
    public int findCarCount(HashMap map);
    /**
	 * 查询车辆信息
	 * @param map
	 * @return
	 */
    public List findCarInfo(HashMap map);
    /**
	 * 添加车辆信息
	 * @param map
	 * @return
	 */
    public int insertCar(HashMap map);
    /**
	 * 修改车辆信息
	 * @param map
	 * @return
	 */
	public int updateCarInfo(HashMap map);
	
	/**
	 * 删除车辆信息
	 * @param map
	 * @return
	 */
	public int deleteCarInfo(HashMap map);
	
	/**
	 * 查询车辆与设备关系
	 * @param map
	 * @return
	 */
	public List findCarFixing(HashMap map);
	/**
	 * 查询GPS系统上车辆当前里程
	 */
	public String findGPSCarCurrMileage(HashMap map);
	/**
	 * 查询车辆油箱总容量
	 * @param map
	 * @return
	 */
	public String findOilTotal(HashMap map);
	
	/**
	 * 2009-12-23车辆信息,派车登记中使用
	 * @param map
	 * @return
	 */
    public List ajaxJsonCar(HashMap map);
}
