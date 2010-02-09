package net.trust.application.systemManage.manager.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.application.systemManage.manager.dto.UserInfo;


public interface ManagerFacade {
	public UserInfo findManagerById(UserInfo userInfo);
	/**
	 * 根据转入的staffId获取该员工所能展示的菜单
	 * @param staffId
	 * @param subMenuId
	 * @return
	 */
	public List findMenuById(String staffId,String subMenuId);
	
	/**
	 * 查询管理人员信息
	 * @param map
	 * @return
	 */
	public List findManagerInfo(HashMap map);
	
	/**
	 * 查询管理人员信息总记录数
	 * @param map
	 * @return
	 */
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
	public List findCity(UserInfo userInfo);
	
	/**
	 * 添加人员与区域关系
	 * @param roleId
	 * @param staffId
	 * @return
	 */
	public int insertManagerCityAllot(String[] cityId, String staffId);
}
