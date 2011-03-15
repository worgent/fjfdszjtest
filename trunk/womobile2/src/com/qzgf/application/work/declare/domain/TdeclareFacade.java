package com.qzgf.application.work.declare.domain;


import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public interface TdeclareFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertTdeclare(HashMap map);
	

	/*******************************************相应类别*******************************************/
	/**
	 * 
	 * Purpose      : 说明
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findmissiongrade(HashMap map);
	public abstract List finddeclaretype(HashMap map);
	public abstract List findpattern(HashMap map);
	
	/***********************************************************************************************/
	
	
	
	
	public abstract int countTdeclare(HashMap map);
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteTdeclareById(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract int allproTdeclareById(HashMap map);
	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateTdeclareById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findTdeclare(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findTdeclarePage(HashMap map,Pages pages);
	
	
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