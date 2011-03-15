package com.qzgf.application.report.reportpattern.domain;


import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public interface TreportpatternFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertTreportpattern(HashMap map);
	
	public List findpattern(HashMap map);
	
	public abstract int countTreportpattern(HashMap map);
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteTreportpatternById(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract int allproTreportpatternById(HashMap map);
	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateTreportpatternById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findTreportpattern(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findTreportpatternPage(HashMap map,Pages pages);
	
	//动态列表头,查询头
	public List findTreportpatterncall(HashMap map);
	public List findTreportpatternc(HashMap map);
	public List findTreportpatternfall(HashMap map);
	public List findTreportpatternf(HashMap map);
}