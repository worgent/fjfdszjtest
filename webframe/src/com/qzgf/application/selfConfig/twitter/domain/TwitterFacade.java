package com.qzgf.application.selfConfig.twitter.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface TwitterFacade {

	@SuppressWarnings("unchecked")
	public abstract int insertTwitter(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract int deleteTwitterById(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract boolean updateTwitterById(HashMap map);

	@SuppressWarnings("unchecked")
	public abstract PageList findTwitter(HashMap map, Pages pages);

	/**
	 * 根据日志编号查询该日志
	 * @param twitterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findTwitterById(String twitterId);

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	
	/**
	 * 列出所有的日志类别
	 * @param twitterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findTwitterTypeByUserId(HashMap map);
	
	@SuppressWarnings("unchecked")
	public PageList findTwittersByTwitterType(HashMap map,Pages pages);

	/**
	 * 插入日志评论
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertTwitterWord(HashMap map)throws Exception;
}