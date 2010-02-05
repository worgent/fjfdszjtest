package com.qzgf.application.systemManage.firstpage.domain;
import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 人员档案
 * 
 * @author lsr
 * 
 */

public class FirstPageFacadeImpl implements FirstPageFacade {
	BaseSqlMapDAO baseSqlMapDAO;

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	
	@SuppressWarnings("unchecked")
	public int findFirstPageCount(HashMap map) {
		
		return ((Integer) baseSqlMapDAO.queryForObject(
				"FirstPage.findFirstPageCount", map)).intValue();
	}

	@SuppressWarnings("unchecked")
	public List findFirstPageList(HashMap map) {

		return baseSqlMapDAO.queryForList("FirstPage.findFirstPageList", map);
	}

	@SuppressWarnings("unchecked")
	public List gobackFirstList(HashMap map) {
		return baseSqlMapDAO.queryForList("FirstPage.gobackFirstList", map);
	}


	@SuppressWarnings("unchecked")
	public int updateFirstPage(HashMap map) {
		return baseSqlMapDAO.update("FirstPage.updateFirstPage",map);
	}

	
	
	@SuppressWarnings("unchecked")
	public void addRoldInfo(HashMap roldInfo, int num)
			throws Exception {
		int st = 0;
		HashMap map;
		if (num == 1) {
			st = baseSqlMapDAO.update("FirstPage.insertFirstPage", roldInfo);// 添加中转站点情况
			if (st < 1) {
				throw new Exception("发布失败.");
			}
		} else if (num > 1) {
			String[] noticeContent = (String[]) roldInfo.get("noticeContent");
			for (int i = 0; i < num; i++) {

				map = new HashMap();
			
				map.put("noticeContent", noticeContent[i]);
			
				st = baseSqlMapDAO.update("FirstPage.insertFirstPage", map);// 添加到婚姻信息表
				if (st < 1) {
					throw new Exception("发布失败.");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap insertFirstPage(HashMap map, HashMap roldInfo)
			throws Exception {

		this.baseSqlMapDAO.update("FirstPage.delFirstPage", "");

		if (null != roldInfo && roldInfo.size() > 0) {
			this.addRoldInfo(roldInfo, Integer.valueOf(
					roldInfo.get("num").toString()).intValue());
		}

		return null;
	}

}
