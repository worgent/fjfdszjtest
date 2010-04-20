package com.qzgf.application.biz.query.domain;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/** 
 * 用户管理实现类
 *
 */
public class QueryFacadeImpl implements QueryFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(QueryFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	

	/**
	 * 省份
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List ajaxProvince(HashMap map){
		return baseSqlMapDAO.queryForList("Query.findProvince", map);
	}
	
	/**
	 * 地市
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List ajaxCity(HashMap map){
		return baseSqlMapDAO.queryForList("Query.findCity", map);
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
