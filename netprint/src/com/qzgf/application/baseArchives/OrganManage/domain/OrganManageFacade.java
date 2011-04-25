package com.qzgf.application.baseArchives.OrganManage.domain;

import java.util.HashMap;
import java.util.List;

public interface OrganManageFacade {
	/**
	 * 获取当前人员所拥有的机构树
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findOrganTree(HashMap map);

    /**
	 * 查询机构部门信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List findOrganManage(HashMap map);
    
    /**
	 * 修改机构部门信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateOrganManage(HashMap map);
	
	 /**
	 * 删除机构部门信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteOrganManage(HashMap map);

	/**
	 * 添加机构部门信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int insertOrganManage(HashMap map);
	
    
    
	/**
	 * 查询与要删除的机构相关的记录
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int findOrganXgCount(HashMap map);
    
	/**
	 * 查询机构所拥有的租赁合同总记录数
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int findOrganBargainCount(HashMap map);
}
