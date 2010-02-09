package net.trust.application.carManage.putonsteam.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 加油管理
 *
 */
public class PutonsteamFacadeImpl implements PutonsteamFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询加油总记录信息
	 * @param map
	 * @return
	 */
    public int findPutonsteamCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("PutonsteamManage.findPutonsteamCount",map)).intValue();
    }
    /**
	 * 查询加油信息
	 * @param map
	 * @return
	 */
    public List findPutonsteam(HashMap map){
    	return baseSqlMapDAO.queryForList("PutonsteamManage.findPutonsteam",map);
    }
    /**
	 * 添加加油信息
	 * @param map
	 * @return
	 */
    public int insertPutonsteam(HashMap map) throws Exception{
    	//创建加油单据
    	String pb_seq = baseSqlMapDAO.sequences("expedite_car_id");
    	map.put("putOnSteamId",pb_seq);
    	int st = baseSqlMapDAO.update("PutonsteamManage.insertPutonsteam",map);
    	
    	if (st == 0){
    		throw new Exception("创建加油单据时出错");
    	}
    	//创建财务单据
    	map.put("sourceOrderType", "1");    	
		map.put("sourceOrderCode",pb_seq);
		st=baseSqlMapDAO.update("FinanceManage.insertFinance", map);
		if (st == 0){
    		throw new Exception("创建财务单据时出错");
    	}
    	return st;
    }
    /**
	 * 修改加油信息
	 * @param map
	 * @return
	 */
	public int updatePutonsteam(HashMap map) throws Exception{
		//修改加油单据
		int st = baseSqlMapDAO.update("PutonsteamManage.updatePutonsteam",map);
		if (st == 0){
    		throw new Exception("修改加油单据时出错");
    	}
		
		//修改财务单据
		map.put("sourceOrderCode", map.get("putonsteamId"));
		map.put("sourceOrderType", "1");
		st = baseSqlMapDAO.update("FinanceManage.updateFinance", map);
		if (st == 0){
    		throw new Exception("修改财务单据时出错");
    	}
		return st;
	}
	
	/**
	 * 删除加油信息
	 * @param map
	 * @return
	 */
	public int deletePutonsteam(HashMap map) throws Exception{
		//删除加油信息单据
		int st = baseSqlMapDAO.update("PutonsteamManage.deletePutonsteam",map);
		if (st == 0){
    		throw new Exception("删除加油单据时出错");
    	}
		
		//删除财务单据
		map.put("sourceOrderCode", map.get("putonsteamId"));
		map.put("sourceOrderType", "1");
		st = baseSqlMapDAO.update("FinanceManage.deleteFinance",map);
		if (st == 0){
    		throw new Exception("删除财务单据时出错");
    	}
		
		return st;
	
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
