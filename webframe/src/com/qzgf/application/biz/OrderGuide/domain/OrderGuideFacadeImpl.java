package com.qzgf.application.biz.OrderGuide.domain;

import java.util.HashMap;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/*
 *订阅向导 
 */
public class OrderGuideFacadeImpl implements OrderGuideFacade {
	BaseSqlMapDAO baseSqlMapDAO;
	
	/**
	 * 利用存储过程查询向导并发起订阅
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String insertAvailable(HashMap map) {
		String ss="";
		try {
			ss = (String)baseSqlMapDAO.queryForObject("OrderGuide.orderGuide", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss;
	}
	
	/**
	 * 直接指定向导订阅
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String insertGuide(HashMap map) {
		String ss="";
		try {
			ss = (String)baseSqlMapDAO.queryForObject("OrderGuide.insertUserGuide", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
}
