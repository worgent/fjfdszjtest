package com.qzgf.application.systemManage.menu.domain;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.qzgf.application.systemManage.menu.dto.MenuInfo;

public interface MenuFacade {
	@SuppressWarnings("unchecked")
	public List findMenuNodesBySuperId(MenuInfo menuInfo);
	public MenuInfo findMenuInfoById(String menuId) throws DataAccessException ;
	public int deleteMenuInfo(MenuInfo menuInfo) throws DataAccessException ;
	public int insertMenuInfo(MenuInfo menuInfo) throws DataAccessException ;
	public int updateMenuInfo(MenuInfo menuInfo) throws DataAccessException ;
}
