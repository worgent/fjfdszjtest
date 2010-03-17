package com.qzgf.application.net.proLog.domain;

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
public class ProLogFacadeImpl implements ProLogFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(ProLogFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertProLog(HashMap map){
		return baseSqlMapDAO.update("ProLog.insertProLog", map);
	}
	
	@SuppressWarnings("unchecked")
	public int deleteProLogById(HashMap map) {
		return baseSqlMapDAO.update("ProLog.deleteProLogById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateProLogById(HashMap map) {
		return baseSqlMapDAO.update("ProLog.updateProLogById", map);
	}
	@SuppressWarnings("unchecked")
	public List findProLog(HashMap map) {
		return baseSqlMapDAO.queryForList("ProLog.findProLog", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findProLogPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("ProLog.findProLogCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("ProLog.findProLogPage", map);
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
