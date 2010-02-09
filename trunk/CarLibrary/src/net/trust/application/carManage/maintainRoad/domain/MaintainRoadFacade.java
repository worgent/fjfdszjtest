package net.trust.application.carManage.maintainRoad.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 养路费管理
 *
 */
public interface MaintainRoadFacade {
	/**
	 * 查询养路费信息总记录数
	 * @param map
	 * @return
	 */
    public int findMaintainRoadCount(HashMap map);
    /**
	 * 查询养路费信息
	 * @param map
	 * @return
	 */
    public List findMaintainRoad(HashMap map);
    /**
	 * 添加养路费信息
	 * @param map
	 * @return
	 */
    public int insertMaintainRoad(HashMap map);
    /**
	 * 修改养路费信息
	 * @param map
	 * @return
	 */
	public int updateMaintainRoad(HashMap map);
	
	/**
	 * 删除养路费信息
	 * @param map
	 * @return
	 */
	public int deleteMaintainRoad(HashMap map);
}
