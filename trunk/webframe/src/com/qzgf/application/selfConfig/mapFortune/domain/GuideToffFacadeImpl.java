package com.qzgf.application.selfConfig.mapFortune.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 雇请向导持久层实现类
 * @author lsr
 * 
 */
public class GuideToffFacadeImpl implements GuideToffFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(GuideToffFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	/**
	 * 前二十位白领向导
	 */
	@SuppressWarnings("unchecked")
	public List findWhiteCollarGuideList(HashMap map){
		return baseSqlMapDAO.queryForList("MapFortune.findWhiteCollarGuide",map);
	}
	
	/**
	 * 前二十位名望向导
	 */
	@SuppressWarnings("unchecked")
	public List findFameGuideList(HashMap map){
		return baseSqlMapDAO.queryForList("MapFortune.findFameGuide",map);
	}
	
	/**
	 * 前二十位赋闲向导
	 */
	@SuppressWarnings("unchecked")
	public List findDallyGuideList(HashMap map){
		return baseSqlMapDAO.queryForList("MapFortune.findDallyGuide",map);
	}
	
	/**
	 * 更多白领向导
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findWhiteCollarGuideMoreList(HashMap map,Pages pages){
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("MapFortune.findGuideCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("MapFortune.findWhiteCollarGuideMore",map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
		
	}
	
	/**
	 * 更多名望向导
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findFameGuideMoreList(HashMap map,Pages pages){
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("MapFortune.findGuideCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("MapFortune.findFameGuideMore",map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
		
	}
	
	/**
	 * 更多赋闲向导
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findDallyGuideMoreList(HashMap map,Pages pages){
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("MapFortune.findGuideCountByDally", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("MapFortune.findDallyGuideMore",map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
		
	}
}   
