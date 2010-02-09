package net.trust.application.baseArchives.institutionManage.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 管理机购
 * @author zhengmh
 *
 */
public class InstitutionFacadeImpl implements InstitutionFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询机构总记录信息
	 * @param map
	 * @return
	 */
    public int findInstitutionCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("InstitutionManage.findInstitutionCount",map)).intValue();
    }
    /**
	 * 查询机构信息
	 * @param map
	 * @return
	 */
    public List findInstitutionInfo(HashMap map){
    	return baseSqlMapDAO.queryForList("InstitutionManage.findManagerInfo",map);
    }
    /**
	 * 修改机构信息
	 * @param userInfo
	 * @return
	 */
    public int insertInstitution(HashMap map){
    	return baseSqlMapDAO.update("InstitutionManage.insertInstitution",map);
    }
    /**
	 * 修改机构信息
	 * @param userInfo
	 * @return
	 */
	public int updateInstitutionInfo(HashMap map){
		return baseSqlMapDAO.update("InstitutionManage.updateInstitution",map);
	}
	
	/**
	 * 删除机构信息
	 * @param userInfo
	 * @return
	 */
	public int deleteInstitutionInfo(HashMap map){
		return baseSqlMapDAO.update("InstitutionManage.deleteInstitution",map);
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
}
