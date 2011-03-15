package com.qzgf.application.systemManage.branch.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 机构配置接口
 * @author lsr
 *
 */
public interface BranchFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 取得所有Permission列表
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findPermissionsAll();
	
	/**
	 * 添加机构
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String insertBranch(HashMap map)throws Exception;
	
	/**
	 * 查询下一级机构
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	public List findSubBranchById(String id);
	
	/**
	 * 删除某一机构
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteBranch(String id) throws Exception;
	
	//判断是否有下一级机构
	@SuppressWarnings("unchecked")
	public int checkSubBranchById(String id);

}