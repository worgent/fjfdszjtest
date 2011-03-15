package com.qzgf.application.systemManage.branch.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 机构配置
 * @author lsr
 * 
 */
public class BranchFacadeImpl implements BranchFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(BranchFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	

	@SuppressWarnings("unchecked")
	public List findPermissionsAll() {
		List permissionList = baseSqlMapDAO.queryForList("branch.findPermissions", "");
		return permissionList;
	}
	
	@SuppressWarnings("unchecked")
	public List findSubBranchById(String id) {
		List permissionList = baseSqlMapDAO.queryForList("branch.findSubBranchById", id);
		return permissionList;
	}
	
	//判断是否有下一级机构
	@SuppressWarnings("unchecked")
	public int checkSubBranchById(String id) {
		return ((Integer)baseSqlMapDAO.queryForObject("branch.checkSubBranchById",id)).intValue();
	}
	
	@SuppressWarnings("unchecked") 
	public String insertBranch(HashMap map)throws Exception{ 
		baseSqlMapDAO.update("branch.insertBranch", map);
		return "1";
	}
	
	/**
	 * 删除某一机构
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteBranch(String id) throws Exception {
		Map map=new HashMap();
		map.put("branchId", id);
		
		baseSqlMapDAO.update("branch.deleteBranchById", map);
		return 1;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	
}
