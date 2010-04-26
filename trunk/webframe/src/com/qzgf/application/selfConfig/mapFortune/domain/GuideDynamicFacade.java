package com.qzgf.application.selfConfig.mapFortune.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


/**
 * 周边向导动态持久层接口
 * @author lsr
 *
 */
public interface GuideDynamicFacade {

	/**
	 * 根据时间/浏览数查询向导锦囊
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findGuideDynamicList(HashMap map);
}