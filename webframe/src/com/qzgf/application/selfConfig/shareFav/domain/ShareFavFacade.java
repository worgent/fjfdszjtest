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

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.selfConfig.model.domain.ModelFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


/**
 * Purpose      : 优惠分享接口
 *
 * @author fjfdszj
 * @see     ModelFacade.java
 *
 */
public interface ShareFavFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);


	/**
	 * Purpose      : 新增加数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertShareFav(HashMap map);

	
	/**
	 * Purpose      : 删除数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteShareFavById(HashMap map);

	
	/**
	 * Purpose      : 更改数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateShareFavById(HashMap map);

	
	/**
	 * Purpose      : 查询数据
	 * @param map
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public abstract List findShareFav(HashMap map);
	
	
	/**
	 * Purpose      : 查询页面数据
	 * @param map
	 * @param pages
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findShareFavPage(HashMap map,Pages pages);
	
}