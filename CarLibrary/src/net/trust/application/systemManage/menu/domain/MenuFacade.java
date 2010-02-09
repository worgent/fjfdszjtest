package net.trust.application.systemManage.menu.domain;

import java.util.List;

import net.trust.application.systemManage.menu.dto.MenuInfo;

import org.springframework.dao.DataAccessException;


public interface MenuFacade {
	public List findMenuNodesBySuperId(MenuInfo menuInfo);
	
	public MenuInfo findMenuInfoById(String menuId) throws DataAccessException ;
	
	public int deleteMenuInfo(MenuInfo menuInfo) throws DataAccessException ;
	
	public int insertMenuInfo(MenuInfo menuInfo) throws DataAccessException ;
	
	public int updateMenuInfo(MenuInfo menuInfo) throws DataAccessException ;
	
}
