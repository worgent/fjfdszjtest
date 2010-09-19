package com.qzgf.application.biz.testModel.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;


/**
 * ��ܲ���ģ��
 * @author lsr
 *
 */
public class TestFacadeImpl implements TestFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TestFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertTest(HashMap map){
		/*int st=0;
		st = baseSqlMapDAO.update("TestManage.insertTest", map);
		return st;*/
		System.out.println("执行了插入动作");
		return 0;
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
			return 1;
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findTest(HashMap map,Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(((Integer) baseSqlMapDAO.queryForObject(
					"TestManage.findTestCount", map)).intValue());
		}
		pages.executeCount();
		map.put("START", pages.getSpage());
		map.put("END", pages.getPerPageNum());
		List testList = baseSqlMapDAO.queryForList("TestManage.queryTest", map);
		pl.setObjectList(testList);
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
