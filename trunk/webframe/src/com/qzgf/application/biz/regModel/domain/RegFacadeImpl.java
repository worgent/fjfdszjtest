package com.qzgf.application.biz.regModel.domain;

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
public class RegFacadeImpl implements RegFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(RegFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTest(HashMap map){
		int st=0;
		st = baseSqlMapDAO.update("TestManage.insertTest", map);
		return st;
	}
	
	@SuppressWarnings("unchecked")
	public int deleteTestById(HashMap map) {
		int num = baseSqlMapDAO.update("TestManage.deleteTestById", map);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public int updateTestById(HashMap map) {
		int num = baseSqlMapDAO.update("TestManage.updateTestById", map);
		if (num == 1) {
			// 修改成功
			return 1;
		}
		// 修改失败
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findTest(HashMap map,Pages pages) {
		//未加分页控制
		/*System.out.println(baseSqlMapDAO.toString());
		List testList = baseSqlMapDAO.queryForList("TestManage.queryTest", map);
		if (!testList.isEmpty() && testList != null) {
			return testList;
		} else
			return null;*/
		
		
		//加分页控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(((Integer) baseSqlMapDAO.queryForObject(
					"TestManage.findTestCount", map)).intValue());
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getPerPageNum());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("TestManage.queryTest", map);
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
