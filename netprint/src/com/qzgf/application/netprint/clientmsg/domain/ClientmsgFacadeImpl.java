package com.qzgf.application.netprint.clientmsg.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 客户信息
 * @author lsr
 *
 */
public class ClientmsgFacadeImpl implements ClientmsgFacade {
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询客户信息总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findClientmsgCount(HashMap map) {
		if(!map.get("pdeptid").toString().equals("110")){
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		}
		return ((Integer)baseSqlMapDAO.queryForObject("Clientmsg.findClientmsgCount",map)).intValue();
	}
	
	/**
	 * 查询客户信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findClientmsg(HashMap map) {
		if(!map.get("pdeptid").toString().equals("110")){
			map.remove("commstaffId");
			map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		}
		return baseSqlMapDAO.queryForList("Clientmsg.findClientmsg",map);
	}

	/**
	 * 修改客户信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateClientmsg(HashMap map) {
		return baseSqlMapDAO.update("Clientmsg.updateClientmsg",map);
	}	
	/**
	 * 添加客户信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int insertClientmsg(HashMap map){
    	return baseSqlMapDAO.update("Clientmsg.insertClientmsg",map);
    }



	/**
	 * 删除客户信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteClientmsg(HashMap map){
		return baseSqlMapDAO.update("Clientmsg.deleteClientmsg",map);
	
	}

	
	
	
	/**
	 * 查询省份
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findProvince(HashMap map) {
		return baseSqlMapDAO.queryForList("Clientmsg.findProvince",map);
	}	

	/**
	 * 查询地市
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCity(HashMap map) {
		return baseSqlMapDAO.queryForList("Clientmsg.findCity",map);
	}	
	
	/**
	 * 查询区县
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCounty(HashMap map) {
		return baseSqlMapDAO.queryForList("Clientmsg.findCounty",map);
	}	
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	
}
