package com.qzgf.application.archives.clientMsg.domain;
 
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
public class ClientMsgFacadeImpl implements ClientMsgFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(ClientMsgFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertClientMsg(HashMap map){
		return baseSqlMapDAO.update("ClientMsg.insertClientMsg", map);
	}
	
	public int countClientMsg(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("ClientMsg.findClientMsgCount", map)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public int deleteClientMsgById(HashMap map) {
		return baseSqlMapDAO.update("ClientMsg.deleteClientMsgById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateClientMsgById(HashMap map) {
		return baseSqlMapDAO.update("ClientMsg.updateClientMsgById", map);
	}
	@SuppressWarnings("unchecked")
	public List findClientMsg(HashMap map) {
		return baseSqlMapDAO.queryForList("ClientMsg.findClientMsg", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findClientMsgPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("ClientMsg.findClientMsgCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("ClientMsg.findClientMsgPage", map);
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
