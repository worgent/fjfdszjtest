package net.trust.application.carManage.peccancymanage.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 违章管理
 *
 */
public class PeccancyManageFacadeImpl implements PeccancyManageFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询违章总记录信息
	 * @param map
	 * @return
	 */
    public int findPeccancyCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("PeccancyManage.findPeccancyCount",map)).intValue();
    }
    /**
	 * 查询违章信息
	 * @param map
	 * @return
	 */
    public List findPeccancy(HashMap map){
    	return baseSqlMapDAO.queryForList("PeccancyManage.findPeccancy",map);
    }
    /**
	 * 添加违章信息
	 * @param map
	 * @return
	 */
    public int insertPeccancy(HashMap map){
    	return baseSqlMapDAO.update("PeccancyManage.insertPeccancy",map);
    }
    /**
	 * 修改违章信息
	 * @param map
	 * @return
	 */
	public int updatePeccancy(HashMap map){
		return baseSqlMapDAO.update("PeccancyManage.updatePeccancy",map);
	}
	
	/**
	 * 删除违章信息
	 * @param map
	 * @return
	 */
	public int deletePeccancy(HashMap map){
		return baseSqlMapDAO.update("PeccancyManage.deletePeccancy",map);
	
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
