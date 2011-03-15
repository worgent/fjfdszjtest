
package com.qzgf.application.work.tomonitor.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public class TtomonitorFacadeImpl implements TtomonitorFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TtomonitorFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTtomonitor(HashMap map){
		String id=baseSqlMapDAO.sequences("t_tomonitor");
		map.put("pid", id);
		//id=String.valueOf(baseSqlMapDAO.insert("Tdailywork.insertTdailywork", map));
		int i=baseSqlMapDAO.update("Ttomonitor.insertTtomonitor", map);
		String pexecutors=map.get("pexecutors").toString();
		if(pexecutors.length()>0){
			pexecutors=pexecutors.substring(0,pexecutors.length()-1);
			String[] pexecutorsarr=pexecutors.split(",");
			for(String now:pexecutorsarr){
				now=now.substring(0,now.length()-3);//去除_ex
				map.put("pexecutorid", now);
				i=i+baseSqlMapDAO.update("Ttomonitor.insertTtomonitorexcute", map);
			}
			
		}
		return i;
	}
	
	@SuppressWarnings("unchecked")
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
	
	public List findtomonitortype(HashMap map) {
		return baseSqlMapDAO.queryForList("parameterValue", map);
	}
	
	public List findpattern(HashMap map) {
		return baseSqlMapDAO.queryForList("Ttomonitor.findpattern", map);
	}
	
	/***********************************************************************************************/
	
	
	
	/**
	 * 
	 * Purpose      : 记录数返回
	 * @param map
	 * @return
	 */
	public int countTtomonitor(HashMap map){
		return  ((Integer)baseSqlMapDAO.queryForObject("Ttomonitor.findTtomonitorCount", map)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public int deleteTtomonitorById(HashMap map) {
		baseSqlMapDAO.update("Ttomonitor.deleteTtomonitorexcuteById", map);
		return baseSqlMapDAO.update("Ttomonitor.deleteTtomonitorById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int allproTtomonitorById(HashMap map) {
		return baseSqlMapDAO.update("Ttomonitor.allproTtomonitorById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateTtomonitorById(HashMap map) {
		//更新主表
		int i=baseSqlMapDAO.update("Ttomonitor.updateTtomonitorById", map);
		//删除子表
		i=i+baseSqlMapDAO.update("Ttomonitor.deleteTtomonitorexcuteById", map);
		String pexecutors=map.get("pexecutors").toString();
		if(pexecutors.length()>0){
			pexecutors=pexecutors.substring(0,pexecutors.length()-1);
			String[] pexecutorsarr=pexecutors.split(",");
			for(String now:pexecutorsarr){
				now=now.substring(0,now.length()-3);//去除_ex
				map.put("pexecutorid", now);
				i=i+baseSqlMapDAO.update("Ttomonitor.insertTtomonitorexcute", map);
			}
			
		}
		return i;
	}
	@SuppressWarnings("unchecked")
	public List findTtomonitor(HashMap map) {
		return baseSqlMapDAO.queryForList("Ttomonitor.findTtomonitor", map);
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
		return baseSqlMapDAO.queryForList("Ttomonitor.findfeedhead", map);
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
			int total= ((Integer)baseSqlMapDAO.queryForObject("Ttomonitor.findfeeddatecount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Ttomonitor.findfeeddate", map);
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
		return baseSqlMapDAO.update("Ttomonitor.updateFeedbackById", map);
	}
	
	/*******************************************************************************************/
	
	
	@SuppressWarnings("unchecked")
	public PageList findTtomonitorPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Ttomonitor.findTtomonitorCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Ttomonitor.findTtomonitorPage", map);
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
