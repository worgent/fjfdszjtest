package com.qzgf.application.archives.recMsg.domain;
 
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
public class RecMsgFacadeImpl implements RecMsgFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(RecMsgFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertRecMsg(HashMap map){
		return baseSqlMapDAO.update("RecMsg.insertRecMsg", map);
	}
	
	public int countRecMsg(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("RecMsg.findRecMsgCount", map)).intValue();
	}
	
	
	@SuppressWarnings("unchecked")
	public int deleteRecMsgById(HashMap map) {
		return baseSqlMapDAO.update("RecMsg.deleteRecMsgById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateRecMsgById(HashMap map) {
		return baseSqlMapDAO.update("RecMsg.updateRecMsgById", map);
	}
	@SuppressWarnings("unchecked")
	public List findRecMsg(HashMap map) {
		return baseSqlMapDAO.queryForList("RecMsg.findRecMsg", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findRecMsgPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("RecMsg.findRecMsgCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("RecMsg.findRecMsgPage", map);
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
