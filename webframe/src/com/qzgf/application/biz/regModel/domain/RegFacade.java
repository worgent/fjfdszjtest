package com.qzgf.application.biz.regModel.domain;

import java.util.HashMap;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface RegFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * Ôö
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertTest(HashMap map);

	/**
	 * É¾
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteTestById(HashMap map);

	/**
	 * ¸Ä
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateTestById(HashMap map);

	/**
	 * ²é
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findTest(HashMap map,Pages pages);

}