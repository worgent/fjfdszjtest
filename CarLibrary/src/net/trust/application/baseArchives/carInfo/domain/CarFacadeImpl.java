package net.trust.application.baseArchives.carInfo.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.PubFunction;
/**
 * 车辆信息
 * @author zhengmh
 *
 */
public class CarFacadeImpl implements CarFacade{
	private BaseSqlMapDAO baseSqlMapDAO;
	private BaseSqlMapDAO sqlServerBaseSqlMapDAO;
	
	public BaseSqlMapDAO getSqlServerBaseSqlMapDAO() {
		return sqlServerBaseSqlMapDAO;
	}
	public void setSqlServerBaseSqlMapDAO(BaseSqlMapDAO sqlServerBaseSqlMapDAO) {
		this.sqlServerBaseSqlMapDAO = sqlServerBaseSqlMapDAO;
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	/**
	 * 查询机构总记录信息
	 * @param map
	 * @return
	 */
    public int findCarCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("CarManage.findCarCount",map)).intValue();
    }
    /**
	 * 查询机构信息
	 * @param map
	 * @return
	 */
    public List findCarInfo(HashMap map){
    	return baseSqlMapDAO.queryForList("CarManage.findCarInfo",map);
    }
    /**
	 * 修改机构信息
	 * @param userInfo
	 * @return
	 */
    public int insertCar(HashMap map){
    	String carNoId = baseSqlMapDAO.sequences("car_id");
    	map.put("carNoId", carNoId);
    	int st = baseSqlMapDAO.update("CarManage.insertCar",map);
    	if (st > 0){
    		int num = Integer.valueOf(PubFunction.getNulltoStr(map.get("fixingNum")));
    		if (num > 1){
    			String[] fixingId = (String[])map.get("fixingId");
    			for (int i=0; i<num; i++){
    				map.clear();
    				map.put("carNoId", carNoId);
    				map.put("fixingId", fixingId[i]);
    				baseSqlMapDAO.update("CarManage.insertCarFixing",map);
    			}
    		}else if(num == 1){
    			String fixingId = PubFunction.getNulltoStr(map.get("fixingId"));
    			map.clear();
				map.put("carNoId", carNoId);
				map.put("fixingId", fixingId);
				baseSqlMapDAO.update("CarManage.insertCarFixing",map);
    		}
    	}
    	return st;
    }
    /**
	 * 修改机构信息
	 * @param userInfo
	 * @return
	 */
	public int updateCarInfo(HashMap map){
		int st = baseSqlMapDAO.update("CarManage.updateCar",map);
		if (st > 0){
			String carNoId = map.get("carNoId").toString();
			
			HashMap param = new HashMap();
			param.put("carNoId", carNoId);
			//清除原选设备关系
			baseSqlMapDAO.update("CarManage.deleteCarFixing", param);
			
			int num = Integer.valueOf(PubFunction.getNulltoStr(map.get("fixingNum")));
    		if (num > 1){
    			String[] fixingId = (String[])map.get("fixingId");
    			for (int i=0; i<num; i++){
    				map.clear();
    				map.put("carNoId", carNoId);
    				map.put("fixingId", fixingId[i]);
    				baseSqlMapDAO.update("CarManage.insertCarFixing",map);
    			}
    		}else if(num == 1){
    			String fixingId = PubFunction.getNulltoStr(map.get("fixingId"));
    			map.clear();
				map.put("carNoId", carNoId);
				map.put("fixingId", fixingId);
				baseSqlMapDAO.update("CarManage.insertCarFixing",map);
    		}
		}
		return st;
	}
	
	/**
	 * 删除机构信息
	 * @param userInfo
	 * @return
	 */
	public int deleteCarInfo(HashMap map){
		return baseSqlMapDAO.update("CarManage.deleteCar",map);
	
	}
	
	/**
	 * 查询车辆与设备关系
	 * @param map
	 * @return
	 */
	public List findCarFixing(HashMap map){
		return baseSqlMapDAO.queryForList("CarManage.findCarFixing",map);
	}
	/**
	 * 查询GPS系统上车辆当前里程
	 */
	public String findGPSCarCurrMileage(HashMap map){
		return (String)sqlServerBaseSqlMapDAO.queryForObject("CarManage.findGPSCarCurrMileage", map);
	}
	/**
	 * 查询车辆油箱总容量
	 * @param map
	 * @return
	 */
	public String findOilTotal(HashMap map){
		return (String)baseSqlMapDAO.queryForObject("CarManage.findOilTotal", map);
	}
	
	
	/**
	 * 2009-12-23车辆信息,派车登记中使用
	 * @param map
	 * @return
	 */
    public List ajaxJsonCar(HashMap map){
    	return baseSqlMapDAO.queryForList("CarManage.ajaxJsonCar",map);
    }
}
