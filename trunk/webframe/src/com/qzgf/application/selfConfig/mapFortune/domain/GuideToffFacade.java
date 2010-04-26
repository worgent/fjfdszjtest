package com.qzgf.application.selfConfig.mapFortune.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


/**
 * 向导名人榜持久层接口
 * @author lsr
 *
 */
public interface GuideToffFacade {

	/**
	 * 查询白领向导
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findWhiteCollarGuideList(HashMap map);
	
	/**
	 * 更多白领向导
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findWhiteCollarGuideMoreList(HashMap map,Pages pages);
	
	/**
	 * 前二十位名望向导
	 */
	@SuppressWarnings("unchecked")
	public List findFameGuideList(HashMap map);
	
	/**
	 * 更多名望向导
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findFameGuideMoreList(HashMap map,Pages pages);
	
	/**
	 * 前二十位赋闲向导
	 */
	@SuppressWarnings("unchecked")
	public List findDallyGuideList(HashMap map);
	
	/**
	 * 更多赋闲向导
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findDallyGuideMoreList(HashMap map,Pages pages);
}