package com.qzgf.NetStore.service.impl;

import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IMenuDAO;
import com.qzgf.NetStore.persistence.Menu;
import com.qzgf.NetStore.service.IMenuService;

public class MenuService implements IMenuService {
    IMenuDAO menuDAO;
    
	public IMenuDAO getMenuDAO() {
		return menuDAO;
	}
	public void setMenuDAO(IMenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 菜单列表
	 */
	public List queryMenus() {
		return this.menuDAO.queryMenus();
	}

	@SuppressWarnings("unchecked")
	/**
	 * 根据id得到菜单信息
	 */
	public Map queryMenuById(String id){
		return this.menuDAO.queryMenuById(id);
	}
	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	public boolean updateMenu(Menu menu){
		return this.menuDAO.updateMenu(menu);
	}
	/**
	 * 由id号删除菜单
	 * @param id
	 * @return
	 */
	public boolean deleteMenuByID(String id){
		return this.menuDAO.deleteMenu(id);
	}
	/**
	 * 增加菜单
	 * @param menu
	 * @return
	 */
	public boolean addMenu(Menu menu){
		return this.menuDAO.addMenu(menu);
	}
}
