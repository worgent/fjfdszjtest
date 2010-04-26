package com.qzgf.application.selfConfig.reward.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 *  悬赏揭榜
 * @author lsr
 *
 */
public class RewardFacadeImpl implements RewardFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(RewardFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertReward(HashMap map){
		return baseSqlMapDAO.update("Reward.insertReward", map);

	}
	
	@SuppressWarnings("unchecked")
	public int deleteRewardById(HashMap map) {
		return baseSqlMapDAO.update("Reward.deleteRewardById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateRewardById(HashMap map) {
		return baseSqlMapDAO.update("Reward.updateRewardById", map);
	}

	@SuppressWarnings("unchecked")
	public  List findReward(HashMap map){
		return baseSqlMapDAO.queryForList("Reward.findReward", map);
	}	
	
	@SuppressWarnings("unchecked")
	public PageList findRewardPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Reward.findRewardCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		try{
		List testList = baseSqlMapDAO.queryForList("Reward.findRewardPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}
		return pl;
	}

	//20091022提示的查询信息分页
	@SuppressWarnings("unchecked")
	public PageList findRewardSolvePage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Reward.findRewardCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Reward.findRewardSolvePage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}

	@SuppressWarnings("unchecked")
	public int insertSolve(HashMap map){
		return baseSqlMapDAO.update("Reward.insertSolve", map);

	}
	
	@SuppressWarnings("unchecked")
	public int deleteSolveById(HashMap map) {
		return baseSqlMapDAO.update("Reward.deleteSolveById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateSolveById(HashMap map) {
		return baseSqlMapDAO.update("Reward.updateSolveById", map);
	}

	@SuppressWarnings("unchecked")
	public  List findSolve(HashMap map){
		return baseSqlMapDAO.queryForList("Reward.findSolve", map);
	}	
	
	@SuppressWarnings("unchecked")
	public PageList findSolvePage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Reward.findSolveCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Reward.findSolvePage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	

	
	
	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List findRewardSolve(HashMap map){
		return baseSqlMapDAO.queryForList("Reward.findRewardSolve", map);
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
