package com.qzgf.application.systemManage.manager.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.manager.dto.UserInfo;


public class ManagerFacadeImpl implements ManagerFacade {
	BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	public UserInfo findManagerById(UserInfo userInfo){
		return (UserInfo)baseSqlMapDAO.queryForObject("ManagerInfo.findManagerById", userInfo);
	}
	/**
	 * 根据转入的staffId获取该员工所能展示的菜单
	 * @param staffId
	 * @param subMenuId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findMenuById(String staffId,String subMenuId){
		Map map = new HashMap();
		map.put("staffId",staffId);
		map.put("superId",subMenuId);
		return baseSqlMapDAO.queryForList("ManagerInfo.findMenuByManagerId", map);
	}
	
	/**
	 * 查询管理人员信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findManagerInfo(HashMap map){
		map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		return baseSqlMapDAO.queryForList("ManagerInfo.findManagerInfo", map);
	}
	
	/**
	 * 查询管理人员信息总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findManagerInfoCount(HashMap map){
		map.put("commdept", baseSqlMapDAO.getAllSubDept(map.get("pdeptid").toString()));
		return ((Integer)baseSqlMapDAO.queryForObject("ManagerInfo.findManagerInfoCount", map)).intValue();
	}
	
	/**
	 * 添加管理人员信息
	 * @param userInfo
	 * @return
	 */
	public int insertManagerInfo(UserInfo userInfo){
		//userInfo.setStaffId(baseSqlMapDAO.sequences("staff_id"));
		return baseSqlMapDAO.update("ManagerInfo.insertManagerInfo", userInfo);
	}
	
	/**
	 * 修改管理员信息
	 * @param userInfo
	 * @return
	 */
	public int updateManagerInfo(UserInfo userInfo){
		return baseSqlMapDAO.update("ManagerInfo.updateManagerInfo", userInfo);
	}
	
	/**
	 * 删除管理员信息
	 * @param userInfo
	 * @return
	 */
	public int deleteManagerInfo(UserInfo userInfo){
		return baseSqlMapDAO.update("ManagerInfo.updateManagerInfo", userInfo);
	}
	/**
	 * 列出人员已有的角色与没有的角色
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findRole(UserInfo userInfo){
		return baseSqlMapDAO.queryForList("ManagerInfo.findRole", userInfo);
	}
	/**
	 * 添加人员与角色关系
	 * @param roleId
	 * @param staffId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertManagerRoleAllot(String[] roleId, String staffId){
		if (roleId != null){
			baseSqlMapDAO.update("ManagerInfo.deleteManagerRole", staffId);
			
			HashMap map;
			for (int i=0; i<roleId.length; i++){
				map = new HashMap();
				map.put("staffId",staffId);
				map.put("roleId",roleId[i]);
				baseSqlMapDAO.insert("ManagerInfo.insertManagerRole", map);
			}
		}else{
			baseSqlMapDAO.update("ManagerInfo.deleteManagerRole", staffId);
		}
		return 1;
	}
	/**
	 * 列出人员已有的区域与没有的区域
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findCity(UserInfo userInfo){
		return baseSqlMapDAO.queryForList("ManagerInfo.findCity", userInfo);
	}
	/**
	 * 添加人员与区域关系
	 * @param roleId
	 * @param staffId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertManagerCityAllot(String[] cityId, String staffId){
		if (cityId != null){
			baseSqlMapDAO.update("ManagerInfo.deleteManagerCity", staffId);
			
			HashMap map;
			for (int i=0; i<cityId.length; i++){
				map = new HashMap();
				map.put("staffId",staffId);
				map.put("cityId",cityId[i]);
				baseSqlMapDAO.insert("ManagerInfo.insertManagerCity", map);
			}
		}else{
			baseSqlMapDAO.update("ManagerInfo.deleteManagerCity", staffId);
		}
		return 1;
	}

	/**
	 * 查询管理人员信息总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int isexitManagerInfoCount(HashMap map){
		return ((Integer)baseSqlMapDAO.queryForObject("ManagerInfo.isexitManagerInfoCount", map)).intValue();
	}
	
	/**
	 * 添加管理人员信息
	 * @param userInfo
	 * @return
	 */
	public int regManagerInfo(UserInfo userInfo){
		HashMap map=new HashMap();
		String pid=baseSqlMapDAO.sequences("staff_id");
		//增加用户关连
		map.put("staffId",pid);
		map.put("roleId","110");
		baseSqlMapDAO.insert("ManagerInfo.insertManagerRole", map);
		//增加用户信息
		userInfo.setStaffId(pid);
		int i=baseSqlMapDAO.update("ManagerInfo.regManagerInfo", userInfo);
		return i;
	}
	
	
	/**
	 * 修改用户的配置信息(左边距,上边距,默认寄件人信息)
	 * 2009-10-28
	 * @param map
	 * @return
	 */
	public int prouserConfig(HashMap search){
		int i=baseSqlMapDAO.update("ManagerInfo.prouserConfig", search);
		return i;
	}
}
