package com.qzgf.application.archives.businessGuide.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface BusinessGuideFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	public abstract PageList findUserList(HashMap map,Pages pages);//查找向导用户
	
	public abstract PageList findBusinessGuideList(HashMap map,Pages pages);//查找当前商家指定有多少个向导
	
	public abstract PageList findBusinessGuideAddList(HashMap map,Pages pages);//查找当前不是该商家的向导，选择出来是否加入
	
	public abstract int insertBusinessGuideRelate(HashMap map);//做插入操作商家跟向导的关系
	
	
	public abstract int deleteGuide(HashMap map); //删除向导用户
}