package com.qzgf.application.net.order.domain;

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
public class OrderFacadeImpl implements OrderFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(OrderFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertOrder(HashMap map){
		return baseSqlMapDAO.update("Order.insertOrder", map);
	}
	//得到主键id
	public String getpid(){
		return  baseSqlMapDAO.sequences("t_net_order");
	}
	@SuppressWarnings("unchecked")
	public int deleteOrderById(HashMap map) {
		return baseSqlMapDAO.update("Order.deleteOrderById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateOrderById(HashMap map) {
		return baseSqlMapDAO.update("Order.updateOrderById", map);
	}
	@SuppressWarnings("unchecked")
	public List findOrder(HashMap map) {
		return baseSqlMapDAO.queryForList("Order.findOrder", map);
	}
	
	//设置默认地址
	@SuppressWarnings("unchecked")
	public List setAddress(HashMap map) {
		return baseSqlMapDAO.queryForList("Order.setAddress", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findOrderPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Order.findOrderCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Order.findOrderPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	@SuppressWarnings("unchecked")
	public int addordernum(HashMap map) {
		return baseSqlMapDAO.update("Order.addordernum", map);
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
