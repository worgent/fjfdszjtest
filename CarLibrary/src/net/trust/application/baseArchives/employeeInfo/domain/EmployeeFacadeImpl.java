package net.trust.application.baseArchives.employeeInfo.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
/**
 * 员工信息管理
 * @author chenqf
 *
 */
public class EmployeeFacadeImpl implements EmployeeFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询部门总记录信息
	 * @param map
	 * @return
	 */
    public int findEmployeeInfoCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("EmployeeInfoManage.findEmployeeInfoCount",map)).intValue();
    }
    /**
	 * 查询部门信息
	 * @param map
	 * @return
	 */
    public List findEmployeeInfo(HashMap map){
    	return baseSqlMapDAO.queryForList("EmployeeInfoManage.findEmployeeInfo",map);
    }
    /**
	 * 添加部门信息
	 * @param userInfo
	 * @return
	 */
    public int insertEmployeeInfo(HashMap map){
    	return baseSqlMapDAO.update("EmployeeInfoManage.insertEmployeeInfo",map);
    }
    /**
	 * 修改部门信息
	 * @param userInfo
	 * @return
	 */
	public int updateEmployeeInfo(HashMap map){
		return baseSqlMapDAO.update("EmployeeInfoManage.updateEmployeeInfo",map);
	}
	
	/**
	 * 删除部门信息
	 * @param userInfo
	 * @return
	 */
	public int deleteEmployeeInfo(HashMap map){
		return baseSqlMapDAO.update("EmployeeInfoManage.deleteEmployeeInfo",map);
	}
	/*
	 * 员工信息(驾驶员),通过部门过滤
	 */
    public List ajaxJsonEmp(HashMap map){
    	return baseSqlMapDAO.queryForList("EmployeeInfoManage.ajaxJsonEmp",map);
    }
    
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
