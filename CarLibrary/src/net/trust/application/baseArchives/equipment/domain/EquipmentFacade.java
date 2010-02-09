package net.trust.application.baseArchives.equipment.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 设备管理
 * @author chenqf
 *
 */
public interface EquipmentFacade {
	/**
	 * 查询设备总记录信息
	 * @param map
	 * @return
	 */
    public int findEquipmentCount(HashMap map);
    /**
	 * 查询设备信息
	 * @param map
	 * @return
	 */
    public List findEquipmentInfo(HashMap map);
    /**
	 * 添加设备信息
	 * @param map
	 * @return
	 */
    public int insertEquipment(HashMap map);
    /**
	 * 修改设备信息
	 * @param map
	 * @return
	 */
	public int updateEquipmentInfo(HashMap map);
	
	/**
	 * 删除设备信息
	 * @param map
	 * @return
	 */
	public int deleteEquipmentInfo(HashMap map);
	
	/**
	 * 获取设备保养参数
	 * @param map
	 * @return
	 */
	public List findEquipmentParam(HashMap map);
}
