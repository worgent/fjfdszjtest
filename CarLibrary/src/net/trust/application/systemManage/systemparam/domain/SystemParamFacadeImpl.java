package net.trust.application.systemManage.systemparam.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 系统参数
 * 
 *
 */
public class SystemParamFacadeImpl implements SystemParamFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	/**
	 * 系统参数大类记录数
	 * @param map
	 * @return
	 */
	public int findSystemParamTypeCount(HashMap map){
		
		return ((Integer)baseSqlMapDAO.queryForObject("SystemParam.findSystemParamTypeCount",map)).intValue();
	}
	/**
	 * 系统参数大类
	 * @param map
	 * @return
	 */
	public List findSystemParamType(HashMap map){
		return baseSqlMapDAO.queryForList("SystemParam.findSystemParamType", map);
	}
	/**
	 * 系统参数详细信息 
	 * @param map
	 * @return
	 */
	public List findSystemParam(HashMap map){
		return baseSqlMapDAO.queryForList("SystemParam.findSystemParam", map);
	}
	/**
	 * 添加系统参数
	 * @param map
	 * @return
	 */
	public int insertSystemParam(HashMap map){
		return baseSqlMapDAO.update("SystemParam.insertSystemParam", map);
	}
	/**
	 * 修改系统参数
	 * @param map
	 * @return
	 */
	public int updateSystemParam(HashMap map){
		return baseSqlMapDAO.update("SystemParam.updateSystemParam", map);
	}
	
	/**
	 * 删除系统参数
	 * @param map
	 * @return
	 */
	public int deleteSystemParam(HashMap map){
		return baseSqlMapDAO.update("SystemParam.updateSystemParam", map);
	}
}
