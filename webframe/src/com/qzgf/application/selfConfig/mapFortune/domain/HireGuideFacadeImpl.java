package com.qzgf.application.selfConfig.mapFortune.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 向导名人榜持久层实现类
 * @author lsr
 * 
 */
public class HireGuideFacadeImpl implements HireGuideFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(HireGuideFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	//雇请向导
	@SuppressWarnings("unchecked")
	public PageList findGuideByHire(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("MapFortune.findGuideByHireCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("MapFortune.findGuideByHire", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	
}   
