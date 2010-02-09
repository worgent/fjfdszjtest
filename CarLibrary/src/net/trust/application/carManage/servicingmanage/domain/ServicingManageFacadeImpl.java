package net.trust.application.carManage.servicingmanage.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

public class ServicingManageFacadeImpl implements ServicingManageFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	/**
	 * 查询车辆维修记录总数 
	 * @param map
	 * @return
	 */
	public int findServiceingCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("ServicingManage.findServiceingCount", map)).intValue();
	}
	/**
	 * 查询车辆维修记录
	 * @param map
	 * @return
	 */
	public List findServiceing(HashMap map){
		return baseSqlMapDAO.queryForList("ServicingManage.findServiceing", map);
	}
	/**
	 * 车辆维修登记 
	 * @param map
	 * @return
	 */
	public int insertServiceingApply(HashMap map) throws Exception{
		int st = 0;
		st = baseSqlMapDAO.update("ServicingManage.insertServiceingApply", map);
		if (st == 0){
			throw new Exception("创建维修申请时出错");
		}
		
		HashMap carInfoMap = new HashMap();
		//设置车辆状态为 3：报修
		carInfoMap.put("carState", "3");
		carInfoMap.put("carNoId", map.get("carNoId"));
		st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
		if (st == 0){
			throw new Exception("修改车辆状态时出错");
		}
		return st;
	}
	/**
	 * 修改车辆维修信息 
	 * @param map
	 * @return
	 */
	public int updateServiceingApply(HashMap map) throws Exception{
		int st = 0;
		
		HashMap servicingMap = new HashMap();
		servicingMap.put("carNoId", map.get("carNoId"));
		servicingMap.put("maintainId", map.get("maintainId"));
		
		//判断当前修改的维修申请，是否有改变维修的车辆编号
		int ifAlteration = ((Integer)baseSqlMapDAO.queryForObject("ServicingManage.findServiceingCount", servicingMap)).intValue();
		//改变了维修车辆编号，需将原来的车辆状态改回空闲，再将新的车辆状态置为报修
		if (ifAlteration == 0){
			servicingMap.remove("carNoId");
			servicingMap = (HashMap)baseSqlMapDAO.queryForObject("ServicingManage.findServiceing", servicingMap);
			
			HashMap carInfoMap = new HashMap();
			//将原来的车辆状态改回空闲
			carInfoMap.put("carNoId", servicingMap.get("car_no_id"));	//取得原来的车辆ID
			carInfoMap.put("carState", "1");							//设定状态为空闲
			st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
			if (st == 0){
				throw new Exception("修改原车辆状态时出错");
			}
			
			//将新的车辆状态置为报修
			carInfoMap.put("carNoId", map.get("carNoId"));	//取得新的车辆ID
			carInfoMap.put("carState", "3");							//设定状态为空闲
			st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
			if (st == 0){
				throw new Exception("修改原车辆状态时出错");
			}
		}
		
		st = baseSqlMapDAO.update("ServicingManage.updateServiceing", map);
		if (st == 0){
			throw new Exception("修改维修申请时出错");
		}
		return st;
	}
	/**
	 * 删除车辆维修记录 
	 * @param map
	 * @return
	 */
	public int deleteServiceingApply(HashMap map) throws Exception{
		int st = 0;
		
		//设置车辆状态为 1：空闲
		HashMap servicingMap = (HashMap)baseSqlMapDAO.queryForObject("ServicingManage.findServiceing", map);
		HashMap carInfoMap = new HashMap();
		carInfoMap.put("carNoId", servicingMap.get("car_no_id"));	//取得车辆ID
		carInfoMap.put("carState", "1");							//设定状态为空闲
		st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
		if (st == 0){
			throw new Exception("修改车辆状态时出错");
		}
		
		st = baseSqlMapDAO.update("ServicingManage.deleteServiceing", map);
		if (st == 0){
			throw new Exception("删除维修申请时出错");
		}
		
		return st;
	}
	/**
	 * 车辆维修明细记录 
	 * @param map
	 * @return
	 */
	public List findServiceingDetail(HashMap map){
		return baseSqlMapDAO.queryForList("ServicingManage.findServiceingDetail", map);
	}
	/**
	 * 添加车辆维修明细 
	 * @param map
	 * @return
	 */
	public int insertServiceingBooking(HashMap servicingMap, HashMap detail) throws Exception{
		servicingMap.put("maintainState", "2");
		int st = baseSqlMapDAO.update("ServicingManage.updateServiceing", servicingMap);
		if (st == 0){
			throw new Exception("创建车辆维修登记时出错");
		}
		
		//修改车辆状态
		HashMap carInfoMap = new HashMap();
		carInfoMap.put("carNoId", servicingMap.get("carNoId"));	//取得车辆ID
		carInfoMap.put("carState", "1");							//设定状态为空闲
		st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
		if (st == 0){
			throw new Exception("修改车辆状态时出错");
		}

		//创建明细
		String maintainId = servicingMap.get("maintainId").toString();
		String cityId = servicingMap.get("cityId").toString();
		int detailRowNum = Integer.valueOf(servicingMap.get("detailRowNum").toString());
		
		try{
			String[] maintainItem = (String[])detail.get("maintainItem");
			String[] unit = (String[])detail.get("unit");
			String[] num = (String[])detail.get("num");
			String[] price = (String[])detail.get("price");
			String[] charge = (String[])detail.get("charge");
			String[] memo = (String[])detail.get("memo");
			
			HashMap detailMap = null;
			for (int i=0; i<detailRowNum; i++){
				if (null != maintainItem[i] && !"".equals(maintainItem[i])){
					detailMap = new HashMap();
					detailMap.put("maintainItem", maintainItem[i]);
					detailMap.put("unit", unit[i]);
					detailMap.put("num", num[i]);
					detailMap.put("price", price[i]);
					detailMap.put("charge", charge[i]);
					detailMap.put("memo", memo[i]);
					detailMap.put("maintainId", maintainId);
					detailMap.put("cityId", cityId);
					
					baseSqlMapDAO.update("ServicingManage.insertServiceingDetail", detailMap);
				}
			}
		}catch (Exception e){
			if (null != detail.get("maintainItem") && !"".equals(detail.get("maintainItem"))){
				detail.put("maintainId", maintainId);
				detail.put("cityId", cityId);
				baseSqlMapDAO.update("ServicingManage.insertServiceingDetail", detail);
			}
		}
		
		
		//创建财务单据
		HashMap financemap = new HashMap();
		financemap.put("sourceOrderType", 2);    	
		financemap.put("sourceOrderCode", maintainId);
		financemap.put("charge", servicingMap.get("charge"));
		financemap.put("carNoId", servicingMap.get("carNoId"));
		financemap.put("createMan", servicingMap.get("editorMan"));
		financemap.put("cityId", servicingMap.get("cityId"));
		st = baseSqlMapDAO.update("FinanceManage.insertFinance", financemap);
		if (st == 0){
			throw new Exception("创建财务单据时出错");
		}
		
		return st;
	}
	/**
	 * 修改维修明细记录
	 * @param map
	 * @return
	 */
	public int updateServiceingBooking(HashMap servicingMap, HashMap detail) throws Exception{
		int st = 0;
		st = baseSqlMapDAO.update("ServicingManage.updateServiceing", servicingMap);
		if (st == 0){
			throw new Exception("修改维修明细时出错");
		}
		
		//修改财务单据
		servicingMap.put("sourceOrderCode", servicingMap.get("maintainId"));
		servicingMap.put("sourceOrderType", "2");
		st = baseSqlMapDAO.update("FinanceManage.updateFinance", servicingMap);
		if (st == 0){
			throw new Exception("修改财务单据时出错");
		}
		
		//先删除以前的明细记录
		baseSqlMapDAO.update("ServicingManage.deleteServiceingDetail", servicingMap);
		
		//重新创建明细
		String maintainId = servicingMap.get("maintainId").toString();
		String cityId = servicingMap.get("cityId").toString();
		int detailRowNum = Integer.valueOf(servicingMap.get("detailRowNum").toString());
		
		try{
			String[] maintainItem = (String[])detail.get("maintainItem");
			String[] unit = (String[])detail.get("unit");
			String[] num = (String[])detail.get("num");
			String[] price = (String[])detail.get("price");
			String[] charge = (String[])detail.get("charge");
			String[] memo = (String[])detail.get("memo");
			
			HashMap detailMap = null;
			for (int i=0; i<detailRowNum; i++){
				if (null != maintainItem[i] && !"".equals(maintainItem[i])){
					detailMap = new HashMap();
					detailMap.put("maintainItem", maintainItem[i]);
					detailMap.put("unit", unit[i]);
					detailMap.put("num", num[i]);
					detailMap.put("price", price[i]);
					detailMap.put("charge", charge[i]);
					detailMap.put("memo", memo[i]);
					detailMap.put("maintainId", maintainId);
					detailMap.put("cityId", cityId);
					
					baseSqlMapDAO.update("ServicingManage.insertServiceingDetail", detailMap);
				}
			}
		}catch (Exception e){
			if (null != detail.get("maintainItem") && !"".equals(detail.get("maintainItem"))){
				detail.put("maintainId", maintainId);
				detail.put("cityId", cityId);
				baseSqlMapDAO.update("ServicingManage.insertServiceingDetail", detail);
			}
		}
		
		return st;
	}
	/**
	 * 删除车辆维修明细 
	 * @param map
	 * @return
	 */
	public int deleteServiceingBooking(HashMap map) throws Exception{
		int st = 0;
		
		//删除维修明细项
		baseSqlMapDAO.update("ServicingManage.deleteServiceingDetail", map);
		
		//设置车辆状态为 1：空闲
		HashMap servicingMap = (HashMap)baseSqlMapDAO.queryForObject("ServicingManage.findServiceing", map);
		HashMap carInfoMap = new HashMap();
		carInfoMap.put("carNoId", servicingMap.get("car_no_id"));	//取得车辆ID
		carInfoMap.put("carState", "1");							//设定状态为空闲
		st = baseSqlMapDAO.update("CarManage.updateCar", carInfoMap);
		if (st == 0){
			throw new Exception("修改车辆状态时出错");
		}
		
		st = baseSqlMapDAO.update("ServicingManage.deleteServiceing", map);
		if (st == 0){
			throw new Exception("删除维修登记时出错");
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

	/**
	 * 删除维修明细
	 * @param map
	 * @return
	 */
	public int deleteServiceingDetail(HashMap map){
		return baseSqlMapDAO.update("ServicingManage.deleteServiceingDetail", map);
	}
}
