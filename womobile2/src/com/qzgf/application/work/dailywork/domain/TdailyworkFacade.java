package com.qzgf.application.work.dailywork.domain;


import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public interface TdailyworkFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertTdailywork(HashMap map);
	

	public abstract int countTdailywork(HashMap map);
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteTdailyworkById(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract int allproTdailyworkById(HashMap map);
	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateTdailyworkById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findTdailywork(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findTdailyworkPage(HashMap map,Pages pages);
	
	
	/*******************************************相应类别*******************************************/
	/**
	 * 
	 * Purpose      : 说明
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findmissiongrade(HashMap map);
	public abstract List finddailyworktype(HashMap map);
	public abstract List findpattern(HashMap map);
	
	/***********************************************************************************************/
	
	
	
	/*******************************************************************************************/
	/**
	 * 
	 * Purpose      : 动态表头
	 * @param map
	 * @return
	 */
	
	public List findfeedhead(HashMap map) ;
	public PageList findfeeddatePage(HashMap map,Pages pages);
	/**
	 * 
	 * Purpose      : 反馈修改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateFeedbackById(HashMap map);
	
}