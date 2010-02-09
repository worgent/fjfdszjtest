package com.qzgf.NetStore.service;

import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.persistence.Administrator;
import com.qzgf.NetStore.persistence.Menu;

public interface IMenuService {
	/**
	 * 菜单列表
	 */
	public List queryMenus();
	/**
	 * 根据id得到菜单信息
	 */
	public Map queryMenuById(String id);
	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	public boolean updateMenu(Menu menu);
	/**
	 * 由id号删除菜单
	 * @param id
	 * @return
	 */
	public boolean deleteMenuByID(String id);
	/**
	 * 增加菜单
	 * @param menu
	 * @return
	 */
	public boolean addMenu(Menu menu);
}
