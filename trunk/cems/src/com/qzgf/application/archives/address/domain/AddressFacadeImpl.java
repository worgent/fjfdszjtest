package com.qzgf.application.archives.address.domain;

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
public class AddressFacadeImpl implements AddressFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(AddressFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertAddress(HashMap map){
		return baseSqlMapDAO.update("Address.insertAddress", map);
	}
	
	@SuppressWarnings("unchecked")
	public int deleteAddressById(HashMap map) {
		return baseSqlMapDAO.update("Address.deleteAddressById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateAddressById(HashMap map) {
		return baseSqlMapDAO.update("Address.updateAddressById", map);
	}
	@SuppressWarnings("unchecked")
	public List findAddress(HashMap map) {
		return baseSqlMapDAO.queryForList("Address.findAddress", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findAddressPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Address.findAddressCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Address.findAddressPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}

	//保证默认地址唯一性
	@SuppressWarnings("unchecked")
	public int addressCheck(HashMap map) {
		return baseSqlMapDAO.update("Address.addressCheck", map);
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
