package com.qzgf.application.archives.selfAdvertise.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;



public class SelfAdvertiseFacadeImpl implements SelfAdvertiseFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(SelfAdvertiseFacadeImpl.class);
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
			int total= ((Integer)baseSqlMapDAO.queryForObject("SelfAdver.findUserCount", map)).intValue();
			pages.setTotalNum(total);
		}
	
		pages.executeCount();
	
		map.put("START", pages.getSpage());
	
		map.put("END", pages.getEpage());
	
		List testList = baseSqlMapDAO.queryForList("SelfAdver.findUserList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
	}
	
	public PageList findBusinessGuideList(HashMap map,Pages pages) {
		
		PageList pl = new PageList();
	
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("SelfAdver.findBusinessGuideCount", map)).intValue();
			pages.setTotalNum(total);
		}
	
		pages.executeCount();
		map.put("START", pages.getSpage());
		map.put("END", pages.getEpage());
	
		List testList = baseSqlMapDAO.queryForList("SelfAdver.findBusinessGuideList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
	}   
	
       public PageList findBusinessGuideAddList(HashMap map,Pages pages) {
		 
		PageList pl = new PageList();
	
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("SelfAdver.findBusinessGuideAddCount", map)).intValue();
			pages.setTotalNum(total);
		}
	
		pages.executeCount();
		map.put("START", pages.getSpage());
		map.put("END", pages.getEpage());
	
		List testList = baseSqlMapDAO.queryForList("SelfAdver.findBusinessGuideAddList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
	}  
       
   	public int insertBusinessGuideRelate(HashMap map){
    	int st=0;
		st= baseSqlMapDAO.update("SelfAdver.insertBusinessGuideRelate", map);
        return st;
	}
  
   	public int deleteGuide(HashMap map) {
		int num=0;
		 num = baseSqlMapDAO.update("SelfAdver.deleteGuide", map);
	
		return num;
	}
       
       
       
       
       
       
	
            	
	
	
}
