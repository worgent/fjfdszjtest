package com.qzgf.application.systemManage.menu.domain;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.menu.dto.MenuInfo;

public class MenuFacadeImpl implements MenuFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	public int deleteMenuInfo(MenuInfo menuInfo) throws DataAccessException {
		
		return baseSqlMapDAO.update("MenuInfo.deleteMenuInfo", menuInfo);
	}

	public MenuInfo findMenuInfoById(String menuId) throws DataAccessException {
		
		return (MenuInfo)baseSqlMapDAO.queryForObject("MenuInfo.findMenuInfoById", menuId);
	}

	@SuppressWarnings("unchecked")
	public List findMenuNodesBySuperId(MenuInfo menuInfo) {
		
		return baseSqlMapDAO.queryForList("MenuInfo.findMenuNodes", menuInfo);
	}

	public int insertMenuInfo(MenuInfo menuInfo) throws DataAccessException {
		
		return baseSqlMapDAO.update("MenuInfo.insertMenuInfo", menuInfo);
	}

	public int updateMenuInfo(MenuInfo menuInfo) throws DataAccessException {
		
		return baseSqlMapDAO.update("MenuInfo.updateMenuInfo", menuInfo);
	}

}
