package com.qzgf.application.archives.user.domain;


import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 用户管理接口
 *
 */
public interface UserFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertUser(HashMap map);

	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteUserById(HashMap map);

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateUserById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findUser(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findUserPage(HashMap map,Pages pages);
	


	/**
	 * 修改密码
	 * @param map
	 * @return
	 */
	public abstract int changePwd(HashMap map);
	
	
	//================================相关参数==============================
	/**
	 * 参数值
	 * 传入参数 map.put("psort",1);
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List parameterValue(HashMap map);

	
	//====================================区域信息========================================
	
	/**
	 * 省份
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List ajaxProvince(HashMap map);
	
	/**
	 * 地市
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List ajaxCity(HashMap map);
	
	/**
	 * 县区
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List ajaxCounty(HashMap map);
	

	
	
}