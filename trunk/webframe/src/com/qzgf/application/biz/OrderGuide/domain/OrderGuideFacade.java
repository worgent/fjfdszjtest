package com.qzgf.application.biz.OrderGuide.domain;

import java.util.HashMap;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/*
 *订阅向导 
 */
public interface OrderGuideFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);


	/**
	 * 检查累积卡用户是否有效
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract String insertAvailable(HashMap map);
	
	/**
	 * 直接指定向导订阅
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String insertGuide(HashMap map);

}