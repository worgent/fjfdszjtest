package com.qzgf.application.biz.news.domain;
import java.util.HashMap;

import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

public interface NewsFacade {


	@SuppressWarnings("unchecked")
	public abstract PageList findNewsPage(HashMap map,Pages pages);
	
	public abstract int insertTArticlesNews(HashMap map);

	
	public abstract PageList findOnlyfNewsList(HashMap map,Pages pages);
	
	public abstract PageList findTopEightNewsList(HashMap map,Pages pages);
	
	public abstract PageList findTopSixNewsList(HashMap map,Pages pages);
	
	public  boolean updateT_ARTICLES_NEWS(HashMap map);
	
	public abstract int deleteNews(HashMap map);	
	
	
}