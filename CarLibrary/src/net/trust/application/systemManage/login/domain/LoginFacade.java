package net.trust.application.systemManage.login.domain;

import java.util.List;

import net.trust.application.systemManage.manager.dto.UserInfo;

import org.springframework.dao.DataAccessException;

/**
 * 系统管理员登录
 *
 */
public interface LoginFacade {
	public UserInfo findManagerInfoByNo(UserInfo userInfo) throws DataAccessException ;
	
	/**
	 * @author chenf
	 * 
	 *  根据转入的userID获取该员工所能展示的菜单
	 * @param
	 *  userID 登录者的标志号（流水号）
	 * @return
	 *  含有MenuInof对象的集合
	 * */
	public List findMenuById(String staffId,String subMenuId);
	
	/**
	 * 根据登录者取得所对应的全部权限
	 * @param
	 *  userId 登录者的标志号（流水号）
	 * @return
	 *  包含string对象的集合
	 * @throws 无
	 * */
	public List findRoleIdById(String staffId);
	
	/**
	 * 判断当前登录者是否可以进入系统
	 * @param
	 * 登录者的输入的工号和密码
	 * @return UserInfo 1成功 0失败，其他
	 * */
	public UserInfo checkLogin(UserInfo userInfo);
	
	
	
}
