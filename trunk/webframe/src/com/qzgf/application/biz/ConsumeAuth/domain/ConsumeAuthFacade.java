package com.qzgf.application.biz.ConsumeAuth.domain;

import java.util.HashMap;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

public interface ConsumeAuthFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);


	/**
	 * 检查累积卡用户是否有效
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract String findAvailable(HashMap map);

}