package net.trust.application.carManage.financemanage.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

public class FinanceManageFacadeImpl implements FinanceManageFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	/**
	 * 查询财务单据总数
	 * @param map
	 * @return
	 */
	public int findFinanceCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("FinanceManage.findFinanceCount", map)).intValue();
	}
	/**
	 * 查询财务单据
	 * @param map
	 * @return
	 */
	public List findFinance(HashMap map){
		return baseSqlMapDAO.queryForList("FinanceManage.findFinance", map);
	}
	/**
	 * 创建财物管理交单据 
	 * @param map
	 * @return
	 */
	public int insertFinance(HashMap map){
		return baseSqlMapDAO.update("FinanceManage.insertFinance", map);
	}
	/**
	 * 修改财务单据
	 * @param map
	 * @return
	 */
	public int updateFinance(HashMap map){
		return baseSqlMapDAO.update("FinanceManage.updateFinance", map);
	}
	/**
	 * 删除财务单据
	 * @param map
	 * @return
	 */
	public int deleteFinance(HashMap map){
		return baseSqlMapDAO.update("FinanceManage.deleteFinance", map);
	}

}
