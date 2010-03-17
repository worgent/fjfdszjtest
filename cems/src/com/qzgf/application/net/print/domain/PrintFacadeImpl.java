package com.qzgf.application.net.print.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 用户管理实现类
 *
 */
public class PrintFacadeImpl implements PrintFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(PrintFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertRecMsg(HashMap map){
		return baseSqlMapDAO.update("RecMsg.insertRecMsg", map);
	}
	
	@SuppressWarnings("unchecked")
	public int insertPrint(HashMap map){
		return baseSqlMapDAO.update("Print.insertPrintEms", map);
	}
	
	@SuppressWarnings("unchecked")
	public int deletePrintById(HashMap map) {
		return baseSqlMapDAO.update("Print.deletePrintEmsById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updatePrintById(HashMap map) {
		return baseSqlMapDAO.update("Print.updatePrintEmsById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updatePrintDateEms(HashMap map) {
		return baseSqlMapDAO.update("Print.updatePrintDateEms", map);
	}
	
	public int updatePrintConfig(HashMap map) {
		return baseSqlMapDAO.update("Print.updatePrintConfig", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public List findPrint(HashMap map) {
		return baseSqlMapDAO.queryForList("Print.findPrintEms", map);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 打印专用
	 * @param map
	 * @return
	 */
	public List findPrintDate(HashMap map) {
		return baseSqlMapDAO.queryForList("Print.findPrintDateEms", map);
	}
	
	
	public HashMap getFeeArea(String paddress) {
		String dynamicSql=(String)baseSqlMapDAO.queryForObject("Print.gerFeeArea", paddress);
		HashMap hs=(HashMap)baseSqlMapDAO.queryForObject("dynamicSql", dynamicSql);
		return hs;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findPrintPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Print.findPrintEmsCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Print.findPrintEmsPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	
	//设置默认地址
	@SuppressWarnings("unchecked")
	public List setAddress(HashMap map) {
		return baseSqlMapDAO.queryForList("Order.setAddress", map);
	}
	
	public HashMap findSessionUser(HashMap map) {
	  return (HashMap) baseSqlMapDAO.queryForList("User.findSessionUserById", map).get(0);
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
