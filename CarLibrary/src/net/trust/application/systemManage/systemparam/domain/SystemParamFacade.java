package net.trust.application.systemManage.systemparam.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 系统参数
 * @author chenqf
 *
 */
public interface SystemParamFacade {
	/**
	 * 系统参数大类记录数
	 * @param map
	 * @return
	 */
	public int findSystemParamTypeCount(HashMap map);
	/**
	 * 系统参数大类
	 * @param map
	 * @return
	 */
	public List findSystemParamType(HashMap map);
	/**
	 * 系统参数详细信息 
	 * @param map
	 * @return
	 */
	public List findSystemParam(HashMap map);
	/**
	 * 添加系统参数
	 * @param map
	 * @return
	 */
	public int insertSystemParam(HashMap map);
	/**
	 * 修改系统参数
	 * @param map
	 * @return
	 */
	public int updateSystemParam(HashMap map);
	
	/**
	 * 删除系统参数
	 * @param map
	 * @return
	 */
	public int deleteSystemParam(HashMap map);
}
