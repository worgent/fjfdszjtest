package com.qzgf.application.biz.ConsumeAuth.domain;

import java.util.HashMap;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

public class ConsumeAuthFacadeImpl implements ConsumeAuthFacade {
	BaseSqlMapDAO baseSqlMapDAO;
	
	/**
	 * 检查累积卡用户是否有效
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findAvailable(HashMap map) {
		String ss="";
		try {
			ss = (String)baseSqlMapDAO.queryForObject("consumeAuth.findAvailable", map);
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
