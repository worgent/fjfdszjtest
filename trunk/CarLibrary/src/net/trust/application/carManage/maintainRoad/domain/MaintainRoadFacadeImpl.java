package net.trust.application.carManage.maintainRoad.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 养路费管理
 *
 */
public class MaintainRoadFacadeImpl implements MaintainRoadFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询养路费总记录信息
	 * @param map
	 * @return
	 */
    public int findMaintainRoadCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("MaintainRoadManage.findMaintainRoadCount",map)).intValue();
    }
    /**
	 * 查询养路费信息
	 * @param map
	 * @return
	 */
    public List findMaintainRoad(HashMap map){
    	return baseSqlMapDAO.queryForList("MaintainRoadManage.findMaintainRoad",map);
    }
    /**
	 * 添加养路费信息
	 * @param map
	 * @return
	 */
    public int insertMaintainRoad(HashMap map){
    	return baseSqlMapDAO.update("MaintainRoadManage.insertMaintainRoad",map);
    }
    /**
	 * 修改养路费信息
	 * @param map
	 * @return
	 */
	public int updateMaintainRoad(HashMap map){
		return baseSqlMapDAO.update("MaintainRoadManage.updateMaintainRoad",map);
	}
	
	/**
	 * 删除养路费信息
	 * @param map
	 * @return
	 */
	public int deleteMaintainRoad(HashMap map){
		return baseSqlMapDAO.update("MaintainRoadManage.deleteMaintainRoad",map);
	
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
