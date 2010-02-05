package com.qzgf.application.systemManage.manager.domain;
import java.util.HashMap;
import java.util.List;

import com.qzgf.application.systemManage.manager.dto.UserInfo;

public interface ManagerFacade {
	public UserInfo findManagerById(UserInfo userInfo);
	/**
	 * 根据转入的staffId获取该员工所能展示的菜单
	 * @param staffId
	 * @param subMenuId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findMenuById(String staffId,String subMenuId);
	
	/**
	 * 查询管理人员信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findManagerInfo(HashMap map);
	
	/**
	 * 查询管理人员信息总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findManagerInfoCount(HashMap map);
	
	/**
	 * 添加管理人员信息
	 * @param userInfo
	 * @return
	 */
	public int insertManagerInfo(UserInfo userInfo);
	
	/**
	 * 修改管理员信息
	 * @param userInfo
	 * @return
	 */
	public int updateManagerInfo(UserInfo userInfo);
	
	/**
	 * 删除管理员信息
	 * @param userInfo
	 * @return
	 */
	public int deleteManagerInfo(UserInfo userInfo);
	/**
	 * 列出人员已有的角色与没有的角色
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findRole(UserInfo userInfo);
	/**
	 * 添加人员与角色关系
	 * @param roleId
	 * @param staffId
	 * @return
	 */
	public int insertManagerRoleAllot(String[] roleId, String staffId);
	
	/**
	 * 列出人员已有的区域与没有的区域
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCity(UserInfo userInfo);
	
	/**
	 * 添加人员与区域关系
	 * @param roleId
	 * @param staffId
	 * @return
	 */
	public int insertManagerCityAllot(String[] cityId, String staffId);
	
	/**
	 * 查询管理人员信息总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int isexitManagerInfoCount(HashMap map);	
	/**
	 * 添加管理人员信息
	 * @param userInfo
	 * @return
	 */
	public int regManagerInfo(UserInfo userInfo);
	
	/**
	 * 修改用户的配置信息(左边距,上边距,默认寄件人信息)
	 * 2009-10-28
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int prouserConfig(HashMap map);
}
