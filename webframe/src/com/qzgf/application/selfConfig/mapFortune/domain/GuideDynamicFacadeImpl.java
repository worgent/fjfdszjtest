package com.qzgf.application.selfConfig.mapFortune.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 周边向导动态持久层实现类
 * @author lsr
 * 
 */
public class GuideDynamicFacadeImpl implements GuideDynamicFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(GuideDynamicFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	/**
	 * 根据时间/浏览数查询向导锦囊
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findGuideDynamicList(HashMap map){
		/*//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("MapFortune.findGuideDynamicCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());*/
		//列表内容
		List testList = baseSqlMapDAO.queryForList("MapFortune.findGuideDynamicfList",map);
		//pl.setObjectList(testList);
		///分页信息
		//pl.setPages(pages);
		//return pl;n 
		return testList;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	
}   
