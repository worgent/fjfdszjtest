package com.qzgf.application.netprint.clientmsg.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 客户信息
 * @author lsr
 *
 */
public interface ClientmsgFacade {
	
	/**
	 * 查询客户信息总记录数
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int findClientmsgCount(HashMap map);
    
    /**
	 * 查询客户信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List findClientmsg(HashMap map);
    
    /**
	 * 修改客户信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateClientmsg(HashMap map);
	
	/**
	 * 添加客户信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int insertClientmsg(HashMap map);
    
    /**
	 * 删除合同管理信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteClientmsg(HashMap map);

	
	/**
	 * 查询省份
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findProvince(HashMap map);
	/**
	 * 查询地市
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCity(HashMap map);
	
	/**
	 * 查询区县
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCounty(HashMap map);
	
	
	

}
