package com.qzgf.application.archives.serTime.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 网上下单实现类
 *
 */
public class SerTimeFacadeImpl implements SerTimeFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(SerTimeFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertSerTime(HashMap map){
		return baseSqlMapDAO.update("SerTime.insertSerTime", map);
	}
	
	@SuppressWarnings("unchecked")
	public int deleteSerTimeById(HashMap map) {
		return baseSqlMapDAO.update("SerTime.deleteSerTimeById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateSerTimeById(HashMap map) {
		return baseSqlMapDAO.update("SerTime.updateSerTimeById", map);
	}
	@SuppressWarnings("unchecked")
	public List findSerTime(HashMap map) {
		return baseSqlMapDAO.queryForList("SerTime.findSerTime", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findSerTimePage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("SerTime.findSerTimeCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("SerTime.findSerTimePage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	public int serTimeVerify(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("SerTime.serTimeVerify", map)).intValue();
	}
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
