package com.qzgf.application.biz.news.domain;
import java.util.HashMap;
import java.util.List;

import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.application.biz.news.domain.model;

public interface NewsFacade {


	@SuppressWarnings("unchecked")
	public abstract PageList findNewsPage(HashMap map,Pages pages);
	
	public abstract int insertTArticlesNews(HashMap map);

	
	public abstract List<model> findOnlyfNewsList(HashMap map);
	
	public abstract PageList findTopEightNewsList(HashMap map,Pages pages);
	public abstract PageList findHangEightNewsList(HashMap map,Pages pages);
	
	
	
	public abstract PageList findTopSixNewsList(HashMap map,Pages pages);
	
	public  boolean updateT_ARTICLES_NEWS(HashMap map);
	
	public abstract int deleteNews(HashMap map);
	
	public abstract PageList findOneNewsList(HashMap map,Pages pages);
	
	public List<model> findOnlyfNews(HashMap map);
	
}