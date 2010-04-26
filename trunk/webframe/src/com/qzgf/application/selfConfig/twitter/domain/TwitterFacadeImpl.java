package com.qzgf.application.selfConfig.twitter.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 地图日志持久层实现类
 * @author lsr
 * 
 */
public class TwitterFacadeImpl implements TwitterFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TwitterFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;

	@SuppressWarnings("unchecked")
	public int insertTwitter(HashMap map){
		int st=0;
		st = baseSqlMapDAO.update("Twitter.insertTwitter", map);
		return st;
	}
	
	
	@SuppressWarnings("unchecked")
	public int deleteTwitterById(HashMap map) {
		int num = baseSqlMapDAO.update("Twitter.deleteTwitterById", map);
		return num;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean updateTwitterById(HashMap map) {
		int num = baseSqlMapDAO.update("Twitter.updateTwitterById", map);
		if (num == 1) {
			// 修改成功
			return true;
		}else{
			// 修改失败
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public PageList findTwitter(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Twitter.findTwitterCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Twitter.findTwitterPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findTwittersByTwitterType(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Twitter.findTwitterCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Twitter.findTwitterPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	
	@SuppressWarnings("unchecked")
	public List findTwitterById(String twitterId) {
		List twitterList = baseSqlMapDAO.queryForList("Twitter.findTwitterById", twitterId);
		if (!twitterList.isEmpty() && twitterList != null) { 

			return twitterList;
		} else
			return null;
	}
	
	/**
	 * 列出所有的日志类别
	 * @param twitterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findTwitterTypeByUserId(HashMap map) {
		List twitterTypeList = baseSqlMapDAO.queryForList("TwitterType.findTwitterType", map);
		if (!twitterTypeList.isEmpty() && twitterTypeList != null) { 
			return twitterTypeList;
		} else
			return null;
	}

	/**
	 * 插入日志评论
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked") 
	public int insertTwitterWord(HashMap map)throws Exception{
		int st=0;
		st = baseSqlMapDAO.update("Twitter.insertTwitterWord", map);
		return st;
	}
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}   
