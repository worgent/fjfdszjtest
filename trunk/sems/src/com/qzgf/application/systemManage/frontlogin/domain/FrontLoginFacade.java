package com.qzgf.application.systemManage.frontlogin.domain;

import java.util.HashMap;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/** 
 * 用户登录接口
 *
 */

public interface FrontLoginFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 验证用户,同时提用户信息
	 * @param map
	 * @return
	 */
	public abstract HashMap checkLogin(HashMap map);
	
	/**
	 * 用户信息注册
	 * @param map
	 * @return
	 */
	public abstract boolean regedit(HashMap map);
	/**
	 * 用户是否存在
	 * @param map
	 * @return
	 */
	public abstract int isExist(HashMap map);
	
	
	public String getpid();
	
	/**
	 * 2010-02-04
	 * 生成html格式菜单
	 * @param map
	 * @return
	 */
	public String menu(HashMap map);
}