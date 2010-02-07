package com.qzgf.NetStore.dao;

import java.util.List;
import java.util.Map;


import com.qzgf.NetStore.persistence.Menu;

public interface IMenuDAO {
	@SuppressWarnings("unchecked")
	/** 
	 * 返回所有的菜单
	 * @return
	 */
	public List queryMenus();
	
	@SuppressWarnings("unchecked")
	/**
	 * 根据厂家ID号查询这个菜单的信息,可用做修改用
	 * @param id
	 * @return
	 */
	public Map queryMenuById(String id);
	/**
	 * 更新某个菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean updateMenu(Menu mfr);
	/**
	 * 添加某一菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean addMenu(Menu mfr);
	/**
	 * 根据编号删除某一菜单
	 * @param id
	 * @return
	 */
	public boolean deleteMenu(String id);
}
