package net.trust.application.baseArchives.recunitInfo.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 来往单位信息管理
 * @author zhengmh
 *
 */
public class RecUnitFacadeImpl implements RecUnitFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询往来单位总记录信息
	 * @param map
	 * @return
	 */
    public int findRecUnitInfoCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("RecUnitInfoManage.findRecUnitInfoCount",map)).intValue();
    }
    /**
	 * 查询往来单位信息
	 * @param map
	 * @return
	 */
    public List findRecUnitInfo(HashMap map){
    	return baseSqlMapDAO.queryForList("RecUnitInfoManage.findRecUnitInfo",map);
    }
    /**
	 * 添加往来单位信息
	 * @param map
	 * @return
	 */
    public int insertRecUnitInfo(HashMap map){
    	return baseSqlMapDAO.update("RecUnitInfoManage.insertRecUnitInfo",map);
    }
    /**
	 * 修改往来单位信息
	 * @param map
	 * @return
	 */
	public int updateRecUnitInfo(HashMap map){
		return baseSqlMapDAO.update("RecUnitInfoManage.updateRecUnitInfo",map);
	}
	
	/**
	 * 删除往来单位信息
	 * @param map
	 * @return
	 */
	public int deleteRecUnitInfo(HashMap map){
		return baseSqlMapDAO.update("RecUnitInfoManage.deleteRecUnitInfo",map);
	
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
