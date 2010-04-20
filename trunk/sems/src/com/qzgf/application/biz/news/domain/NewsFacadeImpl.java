package com.qzgf.application.biz.news.domain;
 
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.application.biz.news.domain.model;

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

		st = baseSqlMapDAO.update("News.insertTArticlesNews", map);//insertTArticlesNews
		
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

	
	public List<model> findOnlyfNewsList(HashMap map) {
		
		 List<model> testList = baseSqlMapDAO.queryForList("News.findOnlyfNewsList", map);
		
		return testList;
		
	}
	
	public PageList findOneNewsList(HashMap map,Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("News.findOneNewsList", map);
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
	
	
	public PageList findHangEightNewsList(HashMap map,Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("News.findHangEightNewsList", map);
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
			 baseSqlMapDAO.update("News.deleteNews", map);
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
	
	public List<model> findOnlyfNews(HashMap map) {
		 List<model> lt=baseSqlMapDAO.queryForList("News.findOnlyfNews", map);
		 return lt;
	}
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	
}
