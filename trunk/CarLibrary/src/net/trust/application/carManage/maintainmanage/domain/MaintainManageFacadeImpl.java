package net.trust.application.carManage.maintainmanage.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.PubFunction;
/**
 * 保养管理
 *
 */
public class MaintainManageFacadeImpl implements MaintainManageFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询保养管理记录信息
	 * @param map
	 * @return
	 */
    public int findMaintainManageCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("MaintainManageManage.findMaintainManageCount",map)).intValue();
    }
    /**
	 * 查询保养管理信息
	 * @param map
	 * @return
	 */
    public List findMaintainManage(HashMap map){
    	return baseSqlMapDAO.queryForList("MaintainManageManage.findMaintainManage",map);
    }
    /**
	 * 添加保养管理信息
	 * @param map
	 * @return
	 */
    public int insertMaintainManage(HashMap map) throws Exception{
    	
    	int st=0;    	
    	String pb_seq=baseSqlMapDAO.sequences("put_on_steam_id");
    	map.put("nurseId",pb_seq);
    	st=baseSqlMapDAO.update("MaintainManageManage.insertMaintainManage",map);
    	if (st == 0)
    		throw new Exception("创建保养记录时出错！");
    	
    	//创建财务单据
    	map.put("sourceOrderType",3);    	
		map.put("sourceOrderCode",pb_seq);
		st=baseSqlMapDAO.update("FinanceManage.insertFinance", map);
		if (st == 0)
    		throw new Exception("创建财务单据时出错！");
		
		//创建保养设备记录
		int num = Integer.valueOf(PubFunction.getNulltoStr(map.get("carFixingNum")));
		if (num > 1){
			String[] fixingId = (String[])map.get("fixingId");
			HashMap param = null;
			
			for (int i=0; i<num; i++){
				param =  new HashMap();
				param.put("nurseId", pb_seq);
				param.put("carNoId", map.get("carNoId"));
				param.put("fixingId", fixingId[i]);
				st = baseSqlMapDAO.update("MaintainManageManage.insertNurseItem", param);
				if (st == 0)
		    		throw new Exception("创建要保养的设备记录 ！");
			}
		}else{
			String fixingId = map.get("fixingId").toString();
			map.put("fixingId", fixingId);
			st = baseSqlMapDAO.update("MaintainManageManage.insertNurseItem", map);
			if (st == 0)
	    		throw new Exception("创建要保养的设备记录 ！");
		}
		
    	return st;
    }
    /**
	 * 修改保养管理信息
	 * @param map
	 * @return
	 */
	public int updateMaintainManage(HashMap map) throws Exception{
		int st = 0;
		st = baseSqlMapDAO.update("MaintainManageManage.updateMaintainManage",map);
		if (st == 0)
    		throw new Exception("修改保养记录时出错！");
		
		//修改财务单据
		map.put("sourceOrderCode", map.get("nurseId"));
		map.put("sourceOrderType", "3");
		map.put("charge", map.get("nurseCharge"));
		st = baseSqlMapDAO.update("FinanceManage.updateFinance", map);
		if (st == 0)
    		throw new Exception("修改财务单据时出错！");
		
		//清除原先保养的设备记录
		HashMap param = new HashMap();
		param.put("nurseId", map.get("nurseId"));
		param.put("carNoId", map.get("carNoId"));
		baseSqlMapDAO.update("MaintainManageManage.deleteNurseItem", param);
		
		//创建保养设备记录
		int num = Integer.valueOf(PubFunction.getNulltoStr(map.get("carFixingNum")));
		if (num > 1){
			String[] fixingId = (String[])map.get("fixingId");
			
			
			for (int i=0; i<num; i++){
				param.put("nurseId", map.get("nurseId"));
				param.put("carNoId", map.get("carNoId"));
				param.put("fixingId", fixingId[i]);
				st = baseSqlMapDAO.update("MaintainManageManage.insertNurseItem", param);
				if (st == 0)
		    		throw new Exception("创建要保养的设备记录 ！");
			}
		}else{
			String fixingId = map.get("fixingId").toString();
			map.put("fixingId", fixingId);
			st = baseSqlMapDAO.update("MaintainManageManage.insertNurseItem", map);
			if (st == 0)
	    		throw new Exception("创建要保养的设备记录 ！");
		}
		return st;
	}
	
	/**
	 * 删除保养管理信息
	 * @param map
	 * @return
	 */
	public int deleteMaintainManage(HashMap map) throws Exception{
		int st = 0;
		st = baseSqlMapDAO.update("MaintainManageManage.deleteMaintainManage",map);
		if (st == 0)
    		throw new Exception("删除保养记录时出错！");
		
		//删除财务单据
		map.put("sourceOrderCode", map.get("nurseId"));
		map.put("sourceOrderType", "3");
		map.put("charge", map.get("nurseCharge"));
		st = baseSqlMapDAO.update("FinanceManage.deleteFinance",map);
		if (st == 0)
    		throw new Exception("删除财务单据时出错！");
		
		//清除原先保养的设备记录
		baseSqlMapDAO.update("MaintainManageManage.deleteNurseItem", map);
		
		return st;
	
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
