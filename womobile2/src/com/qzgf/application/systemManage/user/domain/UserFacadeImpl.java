package com.qzgf.application.systemManage.user.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 向导锦囊持久层实现类
 * @author lsr
 * 
 */
public class UserFacadeImpl implements UserFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(UserFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;

	
	@SuppressWarnings("unchecked")
	public PageList findUser(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		String branchId=(String)map.get("branchId");
		if(branchId!=null&&!branchId.equals("")){
			//所有子机构
			map.put("branchId", branchId);
			String allSubBranch=(String)baseSqlMapDAO.queryForObject("User.getAllSubBranch",map);
			map.put("allSubBranch", allSubBranch);
		}
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("User.findUserCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		
		List testList = baseSqlMapDAO.queryForList("User.findUserPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	/**
	 * 2010-11-19
	 * Purpose      : 由用户所属机构查询上级所有机构,包括本身,同时以,号隔开
	 * @param branchId
	 * @return
	 */
	public String findSuperBranch(String branchId) {
		return (String) baseSqlMapDAO.queryForObject("User.findSuperBranch",branchId);
	}
	/**
	 * 根据机构编号查询该机构名称
	 * @param id
	 * @return
	 */
	public String findBranchNameByBranchId(String id){
		return (String)baseSqlMapDAO.queryForObject("User.findBranchNameByBranchId",id);
	}
	
	
	
	/**
	 * 根据id查询某一用户
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap findUserById(String id) {
		HashMap map = (HashMap)baseSqlMapDAO.queryForObject("User.findUserById", id);
		if (!map.isEmpty() && map != null) { 
			return map;
		} else
			return null;
	}
	
	
	/**
	 * 查询商品
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findProducts(HashMap map) {
		
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Coup.findProductPage", map);
		
		return testList;
	}
	
	/**
	 * 根据锦囊编号查询该锦囊的商品信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findProductsByCoupId(HashMap map) {
		
		//商品信息内容
		List testList = baseSqlMapDAO.queryForList("Coup.findProductByCoupId", map);
		
		return testList;
	}
	
	/**
	 * 新增用户
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertUser(HashMap map)throws Exception{
		int st = baseSqlMapDAO.update("User.insertUser", map);
		
		return st;
	}
	
	/**
	 * 根据系统Id查询数据字典
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findDictionaryBySysId(String id) {  
		List dictionaryList = baseSqlMapDAO.queryForList("User.findDictionaryBySysId", id);
		return dictionaryList;
	}
	
	/**
	 * 更新用户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateUserById(HashMap map) {
		baseSqlMapDAO.update("User.updateUserById", map);
		// 修改成功
		return true;
		
	}
	
	/**
	 * 更新用户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateCheckWorkById(HashMap map) {
		baseSqlMapDAO.update("User.updateCheckWorkById", map);
		// 修改成功
		return true;
		
	}
	
	/**
	 * 根据某一ID删除用户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteUserById(HashMap map) {
		int num = baseSqlMapDAO.update("User.deleteUserById", map);
		return num;
	}
	
	/**
	 * 查询所有可用的组
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findGroupsAll(String userId) {
		List groupList = baseSqlMapDAO.queryForList("User.findGroups", userId);
		return groupList;
	}
	
	/**
	 * 根据上级机构，查询所有的下级机构
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSubBranchById(Map map) {
		List branchList = baseSqlMapDAO.queryForList("User.findBranchTreeByBranchID", map);
		return branchList;
	}
	
	
	/**
	 * Purpose      : 人员与机构显示
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSubBranchUserById(Map map) {
		List branchList = baseSqlMapDAO.queryForList("User.findSubBranchUserById", map);
		return branchList;
	}
	
	
	/**
	 * 查询所有执行人信息
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findworkexcute(Map map) {
		return  baseSqlMapDAO.queryForList("User.findworkexcute", map);
	}
	
	@SuppressWarnings("unchecked")
	public List findtomonitorexcute(Map map) {
		return  baseSqlMapDAO.queryForList("User.findtomonitorexcute", map);
	}
	
	@SuppressWarnings("unchecked")
	public List finddeclareexcute(Map map) {
		return  baseSqlMapDAO.queryForList("User.finddeclareexcute", map);
	}
	
	/**
	 * 根据上级机构，查询所有的下级机构,用于修改
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSubBranchForChangeById(Map map) {
		List branchList = baseSqlMapDAO.queryForList("User.findBranchTreeForChangeByBranchID", map);
		return branchList;
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}   
