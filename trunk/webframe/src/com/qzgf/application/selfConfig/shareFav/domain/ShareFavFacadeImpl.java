/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :优惠分享

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :优惠分享

*/
package com.qzgf.application.selfConfig.shareFav.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.selfConfig.model.domain.ModelFacadeImpl;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


/**
 * Purpose      : 优惠分享接口实现
 *
 * @author fjfdszj
 * @see     ModelFacadeImpl.java
 *
 */
public class ShareFavFacadeImpl implements ShareFavFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(ShareFavFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	/**
	 * Purpose      : 增加数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public int insertShareFav(HashMap map){
		return baseSqlMapDAO.update("ShareFav.insertShareFav", map);
	}
	
	/**
	 * Purpose      : 删除数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public int deleteShareFavById(HashMap map) {
		return baseSqlMapDAO.update("ShareFav.deleteShareFavById", map);
	}
	
	/**
	 * Purpose      : 更新数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public int updateShareFavById(HashMap map) {
		return baseSqlMapDAO.update("ShareFav.updateShareFavById", map);
	}
	
	/**
	 * Purpose      : 查询数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List findShareFav(HashMap map) {
		return baseSqlMapDAO.queryForList("ShareFav.findShareFav", map);
	}
	
	/**
	 * Purpose      : 查询页面数据
	 * @param map
	 * @param pages
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public PageList findShareFavPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("ShareFav.findShareFavCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findShareFavPage
		List testList = baseSqlMapDAO.queryForList("ShareFav.findShareFavPage", map);
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
