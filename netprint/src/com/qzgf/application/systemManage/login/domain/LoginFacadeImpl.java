package com.qzgf.application.systemManage.login.domain;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.manager.dto.UserInfo;
import com.qzgf.application.systemManage.menu.dto.MenuInfo;
import com.qzgf.security.MD5;

public class LoginFacadeImpl implements LoginFacade{
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	public UserInfo findManagerInfoByNo(UserInfo userInfo) throws DataAccessException{
		return (UserInfo)baseSqlMapDAO.queryForObject("ManagerInfo.findManagerById", userInfo);
	}
	
	/**
	 * @author lsr
	 * 
	 *  根据转入的userID获取该员工所能展示的菜单
	 * @param
	 *  userID 登录者的标志号（流水号）
	 * @return
	 *  含有MenuInof对象的集合
	 * */
	@SuppressWarnings("unchecked")
	public List findMenuById(String staffId,String subMenuId){
		HashMap map = new HashMap();
		map.put("staffId", staffId);
		map.put("superId", subMenuId);
		return baseSqlMapDAO.queryForList("ManagerInfo.findMenuByStaffId", map);
	}
	
	/**
	 * 根据登录者取得所对应的全部权限
	 * @param
	 *  userId 登录者的标志号（流水号）
	 * @return
	 *  包含string对象的集合
	 * @throws 无
	 * */
	@SuppressWarnings("unchecked")
	public List findRoleIdById(String staffId){
		return baseSqlMapDAO.queryForList("ManagerInfo.findRoleIdByStaffId", staffId);
	}
	
	/**
	 * 判断当前登录者是否可以进入系统
	 * @param
	 * 登录者的输入的工号和密码
	 * @return UserInfo 1成功 0失败，其他
	 * */
	@SuppressWarnings("unchecked")
	public UserInfo checkLogin(UserInfo userInfo){
		userInfo.setIsUse("1");
		UserInfo temp = (UserInfo)baseSqlMapDAO.queryForObject("ManagerInfo.findManagerById", userInfo);
		
		if(null != temp){
			String userpwd = new MD5().StrToMd5(userInfo.getPassword());
			if(null != temp && temp.getPassword().toUpperCase().equals(userpwd)){
				//如果登录成功,取得当前登录者的权限
	    		List powers = baseSqlMapDAO.queryForList("ManagerInfo.findManagePower", temp.getStaffId());
	    		int ilen = powers.size();
	    		StringBuffer power = new StringBuffer();
	    		
	    		MenuInfo menu ;
	    		for(int i=0;i<ilen;i++){
	    			menu = (MenuInfo)powers.get(i);
	    			power.append((char)2).append(menu.getMenuId()).append((char)2);
	    			if(menu.getMethod()!=null){
	    				power.append((char)2).append(menu.getMethod()).append((char)2);
	    			}
	    		}
	    		
	    		temp.setPowers(power.toString());
	    		
	    		//取当前登录者的角色
	    		temp.setRoles(findRoleIdById(temp.getStaffId()));
	    		
	    		//将当前登录的人员登录状态置为在线
	    		UserInfo tmp = new UserInfo();
	    		tmp.setIsOnline("1");
	    		tmp.setStaffId(temp.getStaffId());
	    		baseSqlMapDAO.update("ManagerInfo.updateManagerInfo", tmp);
	    		
	    		return temp;
			}
		}
		return null;
	}
	
	//取得首先信息,公告处理方式
	public List findFirstPageList() {

		return baseSqlMapDAO.queryForList("ManagerInfo.findFirstPageList", "");
	}
	
	//2009-10-29,用户配置信息,上边距,左边距,默认寄件人信息
	@SuppressWarnings("unchecked")
	public List getUserConfig(HashMap map) {
		return baseSqlMapDAO.queryForList("ManagerInfo.finduserConfig",map);
	}
}
