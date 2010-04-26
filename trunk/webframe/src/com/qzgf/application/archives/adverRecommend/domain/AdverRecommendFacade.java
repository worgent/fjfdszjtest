package com.qzgf.application.archives.adverRecommend.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface AdverRecommendFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	public abstract PageList findUserList(HashMap map,Pages pages);//查找向导用户
	
	public abstract PageList findGuideList(HashMap map,Pages pages);//查找用户说在向导 
	
	
	public abstract PageList findBusinessList(HashMap map,Pages pages);//查找此向导下有哪些商家推荐
	
	
	public  abstract int insertT_USER_IDENTITYSPOT(HashMap map); //系统识别用户的身份 与ip，避免恶意操作
	
}