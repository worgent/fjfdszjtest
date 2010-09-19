package com.qzgf.application.net.excel.domain;

import java.util.HashMap;
import java.util.List;

public interface UploadExcelFacade {
	
    @SuppressWarnings("unchecked")
	public int insertUploadExcel(HashMap map);

	/**
	 * 
	 * Purpose      : 用户默认地址信息
	 * @param map
	 * @return
	 */
	public List setAddress(HashMap map);
	
	/**
	 * 
	 * Purpose      : 用户默认基础信息
	 * @param map
	 * @return
	 */
	public List setUserMsg(HashMap map);
   
	
	/**
	 * 
	 * Purpose      : 地址信息列表
	 * @param map
	 * @return
	 */
	public List findAddress(HashMap map) ;
	/**
	 * 
	 * Purpose      : 客户信息列表
	 * @param map
	 * @return
	 */
	public List findClientMsg(HashMap map) ;
}
