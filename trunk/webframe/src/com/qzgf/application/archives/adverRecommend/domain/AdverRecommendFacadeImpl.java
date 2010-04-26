package com.qzgf.application.archives.adverRecommend.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;



public class AdverRecommendFacadeImpl implements AdverRecommendFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(AdverRecommendFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}


	public PageList findUserList(HashMap map,Pages pages) {
	
		PageList pl = new PageList();
	
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("AdverRec.findUserCount", map)).intValue();
			pages.setTotalNum(total);
		}
	
		pages.executeCount();
	
		map.put("START", pages.getSpage());
	
		map.put("END", pages.getEpage());
	
		List testList = baseSqlMapDAO.queryForList("AdverRec.findUserList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
	}
	
	public PageList findGuideList(HashMap map,Pages pages) {
		
		PageList pl = new PageList();
	
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("AdverRec.findGuideCount", map)).intValue();
			pages.setTotalNum(total);
		}
	
		pages.executeCount();
	
		map.put("START", pages.getSpage());
	
		map.put("END", pages.getEpage());
	
		List testList = baseSqlMapDAO.queryForList("AdverRec.findGuideList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
	}    
	
public PageList findBusinessList(HashMap map,Pages pages) {////查找此向导下有哪些商家推荐
		
		PageList pl = new PageList();
	
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("AdverRec.findBusinessCount", map)).intValue();
			pages.setTotalNum(total);
		}
	
		pages.executeCount();
	
		map.put("START", pages.getSpage());
	
		map.put("END", pages.getEpage());
	
		List testList = baseSqlMapDAO.queryForList("AdverRec.findBusinessList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
	}    
	
      public int insertT_USER_IDENTITYSPOT(HashMap map){
	  int st=0;
	  st= baseSqlMapDAO.update("AdverRec.insertT_USER_IDENTITYSPOT", map);
      return st;
       }
            	
	
	
}
