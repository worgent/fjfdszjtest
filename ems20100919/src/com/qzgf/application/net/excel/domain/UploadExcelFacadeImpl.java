package com.qzgf.application.net.excel.domain;

import java.util.HashMap;
import java.util.List;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;


public class UploadExcelFacadeImpl implements UploadExcelFacade{
	BaseSqlMapDAO baseSqlMapDAO;

    @SuppressWarnings("unchecked")
	public int insertUploadExcel(HashMap map){
    	int st=0;    	
    	st=baseSqlMapDAO.update("Print.insertPrintEms",map);
    	return st;
    }
    
    
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}


	/**
	 * 
	 * Purpose      : 用户地址默认信息
	 * @param map
	 * @return
	 */
	public List setAddress(HashMap map) {
		return baseSqlMapDAO.queryForList("Order.setAddress", map);
	}
	
	/**
	 * 
	 * Purpose      : 用户基础信息,session
	 * @param map
	 * @return
	 */
	public List setUserMsg(HashMap map) {
		return baseSqlMapDAO.queryForList("User.findSessionUserById", map);
	}
	
	/**
	 * 
	 * Purpose      : 地址信息列表
	 * @param map
	 * @return
	 */
	public List findAddress(HashMap map) {
		return baseSqlMapDAO.queryForList("Address.findAddress", map);
	}
	
	/**
	 * 
	 * Purpose      : 客户信息列表
	 * @param map
	 * @return
	 */
	public List findClientMsg(HashMap map) {
		return baseSqlMapDAO.queryForList("ClientMsg.findClientMsg", map);
	}

}
