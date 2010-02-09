package net.trust.application.carManage.roadBridgeCharge.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 过路过桥费用管理
 *
 */
public class RoadBridgeChargeFacadeImpl implements RoadBridgeChargeFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	/**
	 * 查询过路过桥费用
	 * @param map
	 * @return
	 */
	public List findRoadBridgeCharge(HashMap map) {
		return baseSqlMapDAO.queryForList("RoadBridgeCharge.findRoadBridgeCharge", map);
	}
	
	/**
	 * 查询过路过桥费用总记录数
	 * @param map
	 * @return
	 */
	public int findRoadBridgeChargeCount(HashMap map) {
		return (Integer)baseSqlMapDAO.queryForObject("RoadBridgeCharge.findRoadBridgeChargeCount", map);
	}
	
	/**
	 * 创建过路过桥费用
	 * @param map
	 * @return
	 */
	public int insertRoadBridgeCharge(HashMap map) throws Exception{
		String roadBridgeChargeId = baseSqlMapDAO.sequences("road_bridge_charge_id");
		map.put("roadBridgeChargeId", roadBridgeChargeId);
		int st = baseSqlMapDAO.update("RoadBridgeCharge.insertRoadBridgeCharge", map);
		if (st == 0){
    		throw new Exception("创建过路过桥费用单据时出错");
    	}
		
		//创建财务单据
    	map.put("sourceOrderType", "4");    	
		map.put("sourceOrderCode", roadBridgeChargeId);
		st=baseSqlMapDAO.update("FinanceManage.insertFinance", map);
		if (st == 0){
    		throw new Exception("创建财务单据时出错");
    	}
		return st;
	}
	
	/**
	 * 修改过路过桥费用
	 * @param map
	 * @return
	 */
	public int updateRoadBridgeCharge(HashMap map) throws Exception{
		int st = baseSqlMapDAO.update("RoadBridgeCharge.updateRoadBridgeCharge", map);
		if (st == 0){
    		throw new Exception("修改过路过桥费用单据时出错");
    	}
		
		//修改财务单据
		map.put("sourceOrderCode", map.get("roadBridgeChargeId"));
		map.put("sourceOrderType", "4");
		st = baseSqlMapDAO.update("FinanceManage.updateFinance", map);
		if (st == 0){
    		throw new Exception("修改财务单据时出错");
    	}
		return st;
	}
	/**
	 * 删除过路过桥费用
	 * @param map
	 * @return
	 */
	public int deleteRoadBridgeCharge(HashMap map) throws Exception{
		int st = baseSqlMapDAO.update("RoadBridgeCharge.deleteRoadBridgeCharge", map);
		if (st == 0){
    		throw new Exception("删除过路过桥费用单据时出错");
    	}
		
		//删除财务单据
		map.put("sourceOrderCode", map.get("roadBridgeChargeId"));
		map.put("sourceOrderType", "4");
		st = baseSqlMapDAO.update("FinanceManage.deleteFinance",map);
		if (st == 0){
    		throw new Exception("删除财务单据时出错");
    	}
		return st;
	}
}
