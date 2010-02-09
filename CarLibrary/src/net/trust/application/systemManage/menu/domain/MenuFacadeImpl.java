package net.trust.application.systemManage.menu.domain;

import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.application.systemManage.menu.dto.MenuInfo;

import org.springframework.dao.DataAccessException;

public class MenuFacadeImpl implements MenuFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	public int deleteMenuInfo(MenuInfo menuInfo) throws DataAccessException {
		
		return baseSqlMapDAO.update("MenuInfo.updateMenuInfo", menuInfo);
	}

	public MenuInfo findMenuInfoById(String menuId) throws DataAccessException {
		
		return (MenuInfo)baseSqlMapDAO.queryForObject("MenuInfo.findMenuInfoById", menuId);
	}

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
