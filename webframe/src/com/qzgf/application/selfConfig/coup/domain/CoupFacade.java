package com.qzgf.application.selfConfig.coup.domain;

import java.util.HashMap;
import java.util.Map;

import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface CoupFacade {
	@SuppressWarnings("unchecked")
	public PageList findCoup(HashMap map,Pages pages);
	
	/**
	 * 插入新向导锦囊
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertGuideCoup(HashMap map)throws Exception;
	
	/**
	 * 根据id查询该向导锦囊
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map findCoupById(String id);
	
	/**
	 * 更新向导锦囊
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateCoupById(HashMap map);
	
	/**
	 * 根据某一ID删除向导锦囊
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteCoupById(HashMap map);
}