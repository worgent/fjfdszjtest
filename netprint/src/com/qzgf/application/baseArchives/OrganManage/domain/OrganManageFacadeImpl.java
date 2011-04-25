package com.qzgf.application.baseArchives.OrganManage.domain;

import java.util.HashMap;
import java.util.List;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 机构部门管理
 * @author lsr
 *
 */
public class OrganManageFacadeImpl implements OrganManageFacade{
	BaseSqlMapDAO baseSqlMapDAO;
	/**
	 * 查询机构部门树,查询下级
	 */
	@SuppressWarnings("unchecked")
	public List findOrganTree(HashMap map){
		return baseSqlMapDAO.queryForList("OrganManage.findOrganTree", map);
	}
	
    /**
	 * 查询本身机构信息,包括父结点信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List findOrganManage(HashMap map){
    	return baseSqlMapDAO.queryForList("OrganManage.findOrganManage",map);
    }
       
	/**
	 * 添加机构部门信息
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int insertOrganManage(HashMap map){
    	int st=0;    	
    	st=baseSqlMapDAO.update("OrganManage.insertOrganManage",map);
    	return st;
    }
    
    /**
	 * 修改部门合同信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int updateOrganManage(HashMap map) {
		return baseSqlMapDAO.update("OrganManage.updateOrganManage",map);
	}
    
	/**
	 * 删除机构部门信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteOrganManage(HashMap map){
		return baseSqlMapDAO.update("OrganManage.deleteOrganManage",map);
	}

	/**
	 * 查询与要删除的机构相关的记录
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int findOrganXgCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("OrganManage.findOrganXgCount",map)).intValue();
    }
	
    
	/**
	 * 查询机构所拥有的租赁合同总记录数
	 * @param map
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public int findOrganBargainCount(HashMap map){
    	return ((Integer)baseSqlMapDAO.queryForObject("OrganManage.findOrganBargainCount",map)).intValue();
    }
    

    
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	
	
}
