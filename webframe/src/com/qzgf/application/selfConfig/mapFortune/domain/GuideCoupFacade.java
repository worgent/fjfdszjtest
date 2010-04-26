package com.qzgf.application.selfConfig.mapFortune.domain;

import java.util.HashMap;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


/**
 * 向导锦囊持久层接口
 * @author lsr
 *
 */
public interface GuideCoupFacade {

	/**
	 * 根据时间/浏览数查询向导锦囊
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findGuideCoupList(HashMap map,Pages pages);
}