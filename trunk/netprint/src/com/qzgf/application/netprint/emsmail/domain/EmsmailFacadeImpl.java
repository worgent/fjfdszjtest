package com.qzgf.application.netprint.emsmail.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 租赁合同管理
 * @author lsr
 *
 */
public class EmsmailFacadeImpl implements EmsmailFacade {
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询客户信息总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findEmsmailCount(HashMap map) {
		if(!map.get("pdeptid").toString().equals("110")){
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		}
		return ((Integer)baseSqlMapDAO.queryForObject("Emsmail.findEmsmailCount",map)).intValue();
	}
	
	/**
	 * 查询客户信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findEmsmail(HashMap map) {
		if(!map.get("pdeptid").toString().equals("110")){
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		}
		return baseSqlMapDAO.queryForList("Emsmail.findEmsmail",map);
	}

	/**
	 * 修改客户信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateEmsmail(HashMap map) {
		return baseSqlMapDAO.update("Emsmail.updateEmsmail",map);
	}	
	/**
	 * 添加客户信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int insertEmsmail(HashMap map){
    	return baseSqlMapDAO.update("Emsmail.insertEmsmail",map);
    }



	/**
	 * 删除客户信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteEmsmail(HashMap map){
		return baseSqlMapDAO.update("Emsmail.deleteEmsmail",map);
	
	}
	
	/**
	 * 增加客户信息(收件人信息,发件人信息);
	 */
    @SuppressWarnings("unchecked")
	public int insertClientmsg(HashMap map){
    	return baseSqlMapDAO.update("Clientmsg.insertClientmsg",map);
    }
    
    /**
	 * 查询邮件费用区域
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List procEmsmailFeeArea(String psendaddress){
    	HashMap hs=new HashMap();
    	hs.put("psendaddress", psendaddress);
    	return baseSqlMapDAO.queryForList("Emsmail.procEmsmailFeeArea",hs);
    }
    
    
    /**
	 * 查询打印数据集
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List findEmsmailPrint(HashMap map){
    	return baseSqlMapDAO.queryForList("Emsmail.findEmsmailPrint",map);
    }
    
    /**
	 * 更新打印
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateEmsmailPrint(HashMap map){
		return baseSqlMapDAO.update("Emsmail.updateEmsmailPrint",map);
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List getUserConfig(HashMap map) {
		return baseSqlMapDAO.queryForList("ManagerInfo.finduserConfig",map);
	}
}
