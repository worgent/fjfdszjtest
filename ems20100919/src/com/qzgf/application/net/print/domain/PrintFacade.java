package com.qzgf.application.net.print.domain;


import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 用户管理接口
 *
 */
public interface PrintFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	@SuppressWarnings("unchecked")
	public int insertRecMsg(HashMap map);
	
	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertPrint(HashMap map);

	/**
	 * 总页数
	 * Purpose      : 说明
	 * @return
	 */
	public int countPrint(HashMap map);
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deletePrintById(HashMap map);

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updatePrintById(HashMap map);

	/**
	 * 更新打印数据信息
	 * @param map
	 * @return
	 */
	public int updatePrintDateEms(HashMap map) ;
	
	public int updatePrintConfig(HashMap map) ;
	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findPrint(HashMap map);
	
	/**
	 * 打印专用
	 * @param map
	 * @return
	 */
	public List findPrintDate(HashMap map) ;
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findPrintPage(HashMap map,Pages pages);
	
	//设置默认地址
	@SuppressWarnings("unchecked")
	public List setAddress(HashMap map);
	
	public HashMap findSessionUser(HashMap map);
	
	public HashMap getFeeArea(String paddress);
	
	public int alldelPrintById(HashMap map) ;
}