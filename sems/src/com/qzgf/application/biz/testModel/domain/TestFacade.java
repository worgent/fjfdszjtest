package com.qzgf.application.biz.testModel.domain;

import java.util.HashMap;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

 
/**
 * 测试接口
 * @author lsr
 *
 */
public interface TestFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertTest(HashMap map);

	/**
	 * ɾ
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteTestById(HashMap map);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateTestById(HashMap map);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findTest(HashMap map,Pages pages);

}