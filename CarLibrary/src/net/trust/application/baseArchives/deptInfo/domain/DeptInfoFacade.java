package net.trust.application.baseArchives.deptInfo.domain;

import java.util.HashMap;
import java.util.List;

public interface DeptInfoFacade {
	/**
	 * 查询部门信息总记录数
	 * @param map
	 * @return
	 */
    public int findDeptInfoCount(HashMap map);
    /**
	 * 查询部门信息
	 * @param map
	 * @return
	 */
    public List findDeptInfo(HashMap map);
    /**
	 * 添加部门信息
	 * @param map
	 * @return
	 */
    public int insertDeptInfo(HashMap map);
    /**
	 * 修改部门信息
	 * @param map
	 * @return
	 */
	public int updateDeptInfo(HashMap map);
	
	/**
	 * 删除部门信息
	 * @param map
	 * @return
	 */
	public int deleteDeptInfo(HashMap map);
}
