package com.qzgf.application.systemManage.user.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

public interface UserFacade {
	@SuppressWarnings("unchecked")
	public PageList findUser(HashMap map,Pages pages);
	
	/**
	 * 新增用户
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertUser(HashMap map)throws Exception;
	
	/**
	 * 根据上级机构，查询所有的下级机构,用于修改
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSubBranchForChangeById(Map map);
	
	
	/**
	 * 查询所有执行人信息
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findworkexcute(Map map);
	@SuppressWarnings("unchecked")
	public List findtomonitorexcute(Map map);
	@SuppressWarnings("unchecked")
	public List finddeclareexcute(Map map);
	
	
	
	/**
	 * 更新用户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateCheckWorkById(HashMap map);
	
	/**
	 * 根据系统Id查询数据字典
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findDictionaryBySysId(String id);
	
	/**
	 * 更新用户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateUserById(HashMap map) ;
	
	/**
	 * 
	 * Purpose      :上级和本级部门以,隔开
	 * @param branchId
	 * @return
	 */
	public String findSuperBranch(String branchId);
	
	/**
	 * 根据某一ID删除用户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteUserById(HashMap map);
	
	/**
	 * 查询商品
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findProducts(HashMap map);
	
	/**
	 * 根据锦囊编号查询该锦囊的商品信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findProductsByCoupId(HashMap map);
	
	/**
	 * 根据上级机构，查询所有的下级机构
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSubBranchById(Map map);
	
	/**
	 * 查询所有可用的组
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findGroupsAll(String userId);
	
	/**
	 * 根据id查询某一用户
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap findUserById(String id);
	
	/**
	 * 根据机构编号查询该机构名称
	 * @param id
	 * @return
	 */
	public String findBranchNameByBranchId(String id);
	
	/**
	 * 
	 * Purpose      : 人员与机构显示
	 * @param map
	 * @return
	 */
	public List findSubBranchUserById(Map map) ;
}