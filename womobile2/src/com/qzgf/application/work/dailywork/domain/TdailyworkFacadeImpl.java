
package com.qzgf.application.work.dailywork.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

public class TdailyworkFacadeImpl implements TdailyworkFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TdailyworkFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTdailywork(HashMap map){
		String id=baseSqlMapDAO.sequences("t_dailywork");
		map.put("pid", id);
		//id=String.valueOf(baseSqlMapDAO.insert("Tdailywork.insertTdailywork", map));
		int i=baseSqlMapDAO.update("Tdailywork.insertTdailywork", map);
		String pexecutors=map.get("pexecutors").toString();
		if(pexecutors.length()>0){
			pexecutors=pexecutors.substring(0,pexecutors.length()-1);
			String[] pexecutorsarr=pexecutors.split(",");
			for(String now:pexecutorsarr){
				now=now.substring(0,now.length()-3);
				map.put("pexecutorid", now);
				i=i+baseSqlMapDAO.update("Tdailywork.insertTdailyworkexcute", map);
			}
			
		}
		return i;
	}
	
	public List findUser(HashMap map) {
		return baseSqlMapDAO.queryForList("User.findUser", map);
	}
	
	/*******************************************相应类别*******************************************/
	/**
	 * 
	 * Purpose      : 说明
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findmissiongrade(HashMap map) {
		return baseSqlMapDAO.queryForList("parameterValue", map);
	}
	
	public List finddailyworktype(HashMap map) {
		return baseSqlMapDAO.queryForList("parameterValue", map);
	}
	
	public List findpattern(HashMap map) {
		return baseSqlMapDAO.queryForList("Tdailywork.findpattern", map);
	}
	
	/***********************************************************************************************/
	
	
	/**
	 * 
	 * Purpose      : 记录数返回
	 * @param map
	 * @return
	 */
	public int countTdailywork(HashMap map){
		return  ((Integer)baseSqlMapDAO.queryForObject("Tdailywork.findTdailyworkCount", map)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public int deleteTdailyworkById(HashMap map) {
		baseSqlMapDAO.update("Tdailywork.deleteTdailyworkexcuteById",map);
		return baseSqlMapDAO.update("Tdailywork.deleteTdailyworkById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int allproTdailyworkById(HashMap map) {
		return baseSqlMapDAO.update("Tdailywork.allproTdailyworkById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateTdailyworkById(HashMap map) {
		//修改主表
		int i=baseSqlMapDAO.update("Tdailywork.updateTdailyworkById",map);
		//清空子表
		i=i+baseSqlMapDAO.update("Tdailywork.deleteTdailyworkexcuteById",map);
		String pexecutors=map.get("pexecutors").toString();
		if(pexecutors.length()>0){
			pexecutors=pexecutors.substring(0,pexecutors.length()-1);
			String[] pexecutorsarr=pexecutors.split(",");
			for(String now:pexecutorsarr){
				now=now.substring(0,now.length()-3);
				map.put("pexecutorid", now);
				i=i+baseSqlMapDAO.update("Tdailywork.insertTdailyworkexcute", map);
			}
			
		}
		return i;
	}
	@SuppressWarnings("unchecked")
	public List findTdailywork(HashMap map) {
		return baseSqlMapDAO.queryForList("Tdailywork.findTdailywork", map);
	}
	/*******************************************************************************************/
	/**
	 * 
	 * Purpose      : 动态表头
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findfeedhead(HashMap map) {
		return baseSqlMapDAO.queryForList("Tdailywork.findfeedhead", map);
	}
	
	/**
	 * 
	 * Purpose      : 动态分页
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findfeeddatePage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Tdailywork.findfeeddatecount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Tdailywork.findfeeddate", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	
	/**
	 * 
	 * Purpose      : 反馈修改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateFeedbackById(HashMap map) {
		return baseSqlMapDAO.update("Tdailywork.updateFeedbackById", map);
	}
	
	/*******************************************************************************************/
	@SuppressWarnings("unchecked")
	public PageList findTdailyworkPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Tdailywork.findTdailyworkCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Tdailywork.findTdailyworkPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}


	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
