package net.trust.application.datacollection.interfetchconfig.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.PubFunction;
/**
 * GPS接口数据采集规则配置 
 *
 */
public class InterFatchConfigFacadeImpl implements InterFatchConfigFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	

	/**
	 * 接口数据采集规则记录数 
	 * @param map
	 * @return
	 */
	public int findInterFatchConfigCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("InterFatchConfig.findInterFatchConfigCount", map)).intValue();
	}
	/**
	 * 接口数据采集规则记录
	 * @param map
	 * @return
	 */
	public List findInterFatchConfig(HashMap map){
		return baseSqlMapDAO.queryForList("InterFatchConfig.findInterFatchConfig", map);
	}
	/**
	 * 创建接口数据采集规则
	 * @param map
	 * @return
	 */
	public int insertInterFatchConfig(HashMap map){
		return baseSqlMapDAO.update("InterFatchConfig.insertInterFatchConfig", map);
	}
	/**
	 * 修改接口数据采集规则
	 * @param map
	 * @return
	 */
	public int updateInterFatchConfig(HashMap map){
		return baseSqlMapDAO.update("InterFatchConfig.updateInterFatchConfig", map);
	}
	/**
	 * 删除接口数据采集规则
	 * @param map
	 * @return
	 */
	public int deleteInterFatchConfig(HashMap map){
		return baseSqlMapDAO.update("InterFatchConfig.deleteInterFatchConfig", map);
	}
}
