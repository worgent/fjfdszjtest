package net.trust.application.baseArchives.employeeInfo.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 员工信息管理
 * @author chenqf
 *
 */
public interface EmployeeFacade {
	/**
	 * 查询员工信息总记录数
	 * @param map
	 * @return
	 */
    public int findEmployeeInfoCount(HashMap map);
    /**
	 * 查询员工信息
	 * @param map
	 * @return
	 */
    public List findEmployeeInfo(HashMap map);
    /**
	 * 添加员工信息
	 * @param map
	 * @return
	 */
    public int insertEmployeeInfo(HashMap map);
    /**
	 * 修改员工信息
	 * @param map
	 * @return
	 */
	public int updateEmployeeInfo(HashMap map);
	
	/**
	 * 删除员工信息
	 * @param map
	 * @return
	 */
	public int deleteEmployeeInfo(HashMap map);
	
	/**
	 * 员工信息(驾驶员),通过部门过滤
	 */
    public List ajaxJsonEmp(HashMap map);
}
