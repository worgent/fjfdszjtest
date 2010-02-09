package net.trust.application.baseArchives.deptInfo.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

public class DeptInfoFacadeImpl implements DeptInfoFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询部门总记录信息
	 * @param map
	 * @return
	 */
    public int findDeptInfoCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("DeptInfoManage.findDeptInfoCount",map)).intValue();
    }
    /**
	 * 查询部门信息
	 * @param map
	 * @return
	 */
    public List findDeptInfo(HashMap map){
    	return baseSqlMapDAO.queryForList("DeptInfoManage.findDeptInfo",map);
    }
    /**
	 * 添加部门信息
	 * @param userInfo
	 * @return
	 */
    public int insertDeptInfo(HashMap map){
    	return baseSqlMapDAO.update("DeptInfoManage.insertDeptInfo",map);
    }
    /**
	 * 修改部门信息
	 * @param userInfo
	 * @return
	 */
	public int updateDeptInfo(HashMap map){
		return baseSqlMapDAO.update("DeptInfoManage.updateDeptInfo",map);
	}
	
	/**
	 * 删除部门信息
	 * @param userInfo
	 * @return
	 */
	public int deleteDeptInfo(HashMap map){
		return baseSqlMapDAO.update("DeptInfoManage.deleteDeptInfo",map);
	
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
