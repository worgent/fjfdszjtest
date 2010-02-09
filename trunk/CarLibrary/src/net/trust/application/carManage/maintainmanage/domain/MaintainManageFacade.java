package net.trust.application.carManage.maintainmanage.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 保养管理
 *
 */
public interface MaintainManageFacade {
	/**
	 * 查询保养管理总记录数
	 * @param map
	 * @return
	 */
    public int findMaintainManageCount(HashMap map);
    /**
	 * 查询保养管理信息
	 * @param map
	 * @return
	 */
    public List findMaintainManage(HashMap map);
    /**
	 * 添加保养管理信息
	 * @param map
	 * @return
	 */
    public int insertMaintainManage(HashMap map) throws Exception;
    /**
	 * 修改保养管理信息
	 * @param map
	 * @return
	 */
	public int updateMaintainManage(HashMap map) throws Exception;
	
	/**
	 * 删除保养管理信息
	 * @param map
	 * @return
	 */
	public int deleteMaintainManage(HashMap map) throws Exception;
}
