package com.qzgf.application.biz.query.domain;


import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 用户管理接口
 *
 */
public interface QueryFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 省份
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List ajaxProvince(HashMap map);
	
	/**
	 * 地市
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List ajaxCity(HashMap map);
	
	
}