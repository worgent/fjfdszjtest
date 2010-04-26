package com.qzgf.application.archives.space.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface SpaceFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertSpace(HashMap map);

	/**
	 * ɾ
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteSpaceById(HashMap map);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateSpaceById(HashMap map);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findSpace(HashMap map,Pages pages);
	
	
	
	@SuppressWarnings("unchecked")
	public abstract PageList pageOnlyOneList(HashMap map,Pages pages);
	
	
	public abstract List isExistSpaceList(HashMap map);
	

	
	public abstract int deleteSpace(HashMap map);
	
	
	

}