
package com.qzgf.application.work.declare.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


public class TdeclareFacadeImpl implements TdeclareFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TdeclareFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTdeclare(HashMap map){
		String id=baseSqlMapDAO.sequences("t_declare");
		map.put("pid", id);
		int i=baseSqlMapDAO.update("Tdeclare.insertTdeclare", map);
		String pexecutors=map.get("pexecutors").toString();
		if(pexecutors.length()>0){
			pexecutors=pexecutors.substring(0,pexecutors.length()-1);
			String[] pexecutorsarr=pexecutors.split(",");
			for(String now:pexecutorsarr){
				now=now.substring(0,now.length()-3);//去除_ex
				map.put("pexecutorid", now);
				i=i+baseSqlMapDAO.update("Tdeclare.insertTdeclareexcute", map);
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
	
	public List finddeclaretype(HashMap map) {
		return baseSqlMapDAO.queryForList("parameterValue", map);
	}
	
	public List findpattern(HashMap map) {
		return baseSqlMapDAO.queryForList("Tdeclare.findpattern", map);
	}
	
	/***********************************************************************************************/
	
	
	
	/**
	 * 
	 * Purpose      : 记录数返回
	 * @param map
	 * @return
	 */
	public int countTdeclare(HashMap map){
		return  ((Integer)baseSqlMapDAO.queryForObject("Tdeclare.findTdeclareCount", map)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public int deleteTdeclareById(HashMap map) {
		baseSqlMapDAO.update("Tdeclare.deleteTdeclareexcuteById", map);
		return baseSqlMapDAO.update("Tdeclare.deleteTdeclareById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int allproTdeclareById(HashMap map) {
		return baseSqlMapDAO.update("Tdeclare.allproTdeclareById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateTdeclareById(HashMap map) {
		//更新主表
		int i=baseSqlMapDAO.update("Tdeclare.updateTdeclareById", map);
		//清除子表
		i=i+baseSqlMapDAO.update("Tdeclare.deleteTdeclareexcuteById", map);
		String pexecutors=map.get("pexecutors").toString();
		if(pexecutors.length()>0){
			pexecutors=pexecutors.substring(0,pexecutors.length()-1);
			String[] pexecutorsarr=pexecutors.split(",");
			for(String now:pexecutorsarr){
				now=now.substring(0,now.length()-3);//去除_ex
				map.put("pexecutorid", now);
				i=i+baseSqlMapDAO.update("Tdeclare.insertTdeclareexcute", map);
			}
			
		}
		return i;
	}
	@SuppressWarnings("unchecked")
	public List findTdeclare(HashMap map) {
		return baseSqlMapDAO.queryForList("Tdeclare.findTdeclare", map);
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
		return baseSqlMapDAO.queryForList("Tdeclare.findfeedhead", map);
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
			int total= ((Integer)baseSqlMapDAO.queryForObject("Tdeclare.findfeeddatecount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Tdeclare.findfeeddate", map);
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
		return baseSqlMapDAO.update("Tdeclare.updateFeedbackById", map);
	}
	
	/*******************************************************************************************/
	
	
	@SuppressWarnings("unchecked")
	public PageList findTdeclarePage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Tdeclare.findTdeclareCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Tdeclare.findTdeclarePage", map);
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
