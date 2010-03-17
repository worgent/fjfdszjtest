package com.qzgf.application.biz.news.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * ��ܲ���ģ��
 * @author lsr
 *
 */
public class NewsFacadeImpl implements NewsFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(NewsFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTArticlesNews(HashMap map){
		int st=0;

		st = baseSqlMapDAO.update("News.insertTArticlesNews", map);
		
		return st;
	}
	

	
	@SuppressWarnings("unchecked")
	public PageList findNewsPage(HashMap map,Pages pages) {
	   PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("News.findNewsCount", map)).intValue();
			pages.setTotalNum(total);
		}
        pages.executeCount();
		map.put("START", pages.getSpage());
		map.put("END", pages.getEpage());

		List testList = baseSqlMapDAO.queryForList("News.findNewsPage", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
	}

	
	public PageList findOnlyfNewsList(HashMap map,Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("News.findOnlyfNewsList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
		
	}
	
	public PageList findTopEightNewsList(HashMap map,Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("News.findTopEightNewsList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
		
	}
	
	public PageList findTopSixNewsList(HashMap map,Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("News.findTopSixNewsList", map);
		pl.setObjectList(testList);
	
		pl.setPages(pages);
		return pl;
		
	}
	
	
	
	
	public boolean updateT_ARTICLES_NEWS(HashMap map) {
		int num =0;
	
		try
		{
		  num = baseSqlMapDAO.update("News.updateT_ARTICLES_NEWS", map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(num==1)
		{
		return true;
		}
		else{
		return false;
		}
	}
	
	public int deleteNews(HashMap map) {
		int num=0;
		 num = baseSqlMapDAO.update("News.deleteNews", map);
		 return num;
	}
	
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	
}
