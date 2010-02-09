package net.trust.application.carManage.insurance.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 保险管理
 *
 */
public class InsuranceFacadeImpl implements InsuranceFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询保险总记录信息
	 * @param map
	 * @return
	 */
    public int findInsuranceCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("InsuranceManage.findInsuranceCount",map)).intValue();
    }
    /**
	 * 查询保险信息
	 * @param map
	 * @return
	 */
    public List findInsurance(HashMap map){
    	return baseSqlMapDAO.queryForList("InsuranceManage.findInsurance",map);
    }
    /**
	 * 添加保险信息
	 * @param map
	 * @return
	 */
    public int insertInsurance(HashMap map){
    	return baseSqlMapDAO.update("InsuranceManage.insertInsurance",map);
    }
    /**
	 * 修改保险信息
	 * @param map
	 * @return
	 */
	public int updateInsurance(HashMap map){
		return baseSqlMapDAO.update("InsuranceManage.updateInsurance",map);
	}
	
	/**
	 * 删除保险信息
	 * @param map
	 * @return
	 */
	public int deleteInsurance(HashMap map){
		return baseSqlMapDAO.update("InsuranceManage.deleteInsurance",map);
	
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
