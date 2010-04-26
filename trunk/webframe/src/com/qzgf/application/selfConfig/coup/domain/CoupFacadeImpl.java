package com.qzgf.application.selfConfig.coup.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 向导锦囊持久层实现类
 * @author lsr
 * 
 */
public class CoupFacadeImpl implements CoupFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(CoupFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;

	
	@SuppressWarnings("unchecked")
	public PageList findCoup(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Coup.findCoupCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Coup.findCoupPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	/**
	 * 插入新向导锦囊
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertGuideCoup(HashMap map)throws Exception{
		int st=0;
		st = baseSqlMapDAO.update("Coup.insertGuideCoup", map);
		return st;
	}
	
	/**
	 * 根据id查询该向导锦囊
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map findCoupById(String id) {
		Map map = (HashMap)baseSqlMapDAO.queryForObject("Coup.findCoupById", id);
		if (!map.isEmpty() && map != null) { 
			return map;
		} else
			return null;
	}
	
	/**
	 * 更新向导锦囊
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateCoupById(HashMap map) {
		int num = baseSqlMapDAO.update("Coup.updateCoupById", map);
		if (num == 1) {
			// 修改成功
			return true;
		}else{
			// 修改失败
			return false;
		}
	}
	
	/**
	 * 根据某一ID删除向导锦囊
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteCoupById(HashMap map) {
		int num = baseSqlMapDAO.update("Coup.deleteCoupById", map);
		return num;
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}   
