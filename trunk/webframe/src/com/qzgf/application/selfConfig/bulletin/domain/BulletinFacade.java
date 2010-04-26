package com.qzgf.application.selfConfig.bulletin.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface BulletinFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertBulletin(HashMap map);

	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteBulletinById(HashMap map);

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateBulletinById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findBulletinPage(HashMap map,Pages pages);
	
	/**
	 * 查单行记录
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findBulletin(HashMap map);
	
	
	
	
	
	
	/*反馈信息的增加，删除，改，查*/
	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertRepBulletin(HashMap map);

	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteRepBulletinById(HashMap map);

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateRepBulletinById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findRepBulletinPage(HashMap map,Pages pages);
	
	/**
	 * 查单行记录
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findRepBulletin(HashMap map);

}