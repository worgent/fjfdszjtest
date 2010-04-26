package com.qzgf.application.selfConfig.mapFortune.domain;

import java.util.HashMap;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 雇请向导持久层接口
 * @author lsr
 *
 */
public interface HireGuideFacade {

	//雇请向导
	@SuppressWarnings("unchecked")
	public PageList findGuideByHire(HashMap map,Pages pages);
}