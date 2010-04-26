package com.qzgf.application.selfConfig.bulletin.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 框架测试模块
 * @author lsr
 *
 */
public class BulletinFacadeImpl implements BulletinFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(BulletinFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	
	@SuppressWarnings("unchecked")
	public int insertBulletin(HashMap map){
		int st = 0;
		// 正向发送
		st = baseSqlMapDAO.update("Bulletin.insertBulletin", map);
		return st;
	}
	
	
	@SuppressWarnings("unchecked")
	public int deleteBulletinById(HashMap map) {
		int num = baseSqlMapDAO.update("Bulletin.deleteBulletinById", map);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public int updateBulletinById(HashMap map) {
		int num = baseSqlMapDAO.update("Bulletin.updateBulletinById", map);
		if (num == 1) {
			// 修改成功
			return 1;
		}
		// 修改失败
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findBulletinPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Bulletin.findBulletinCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Bulletin.findBulletinPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	/*查询单行记录*/
	public List findBulletin(HashMap map) {
		return baseSqlMapDAO.queryForList("Bulletin.findBulletin", map);
	}
	
	
	
	
	//反馈信息
	@SuppressWarnings("unchecked")
	public int insertRepBulletin(HashMap map){
		int st = 0;
		// 正向发送
		st = baseSqlMapDAO.update("Bulletin.insertRepBulletin", map);
		return st;
	}
	
	
	@SuppressWarnings("unchecked")
	public int deleteRepBulletinById(HashMap map) {
		int num = baseSqlMapDAO.update("Bulletin.deleteRepBulletinById", map);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public int updateRepBulletinById(HashMap map) {
		int num = baseSqlMapDAO.update("Bulletin.updateRepBulletinById", map);
		if (num == 1) {
			// 修改成功
			return 1;
		}
		// 修改失败
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findRepBulletinPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Bulletin.findRepBulletinCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Bulletin.findRepBulletinPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	/*查询单行记录*/
	public List findRepBulletin(HashMap map) {
		return baseSqlMapDAO.queryForList("Bulletin.findRepBulletin", map);
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
