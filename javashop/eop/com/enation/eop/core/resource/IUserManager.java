/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.eop.core.resource;

import com.enation.eop.core.resource.dto.SessionDTO;
import com.enation.eop.core.resource.dto.UserDTO;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-11 下午02:02:19
 *         </p>
 * @version 1.0
 */
public interface IUserManager {
	
	
	/**
	 * 获取某用户的详细信息
	 * @param userid
	 * @return
	 */
	public EopUser get(Integer userid);
 
	
	
	
	/**
	 * 创建用户
	 * 
	 * @param userDTO
	 * @see com.enation.eop.core.resource.dto.UserDTO
	 * @return
	 */
	public Integer createUser(UserDTO userDTO);

	 
	/**
	 * 创建一个企业用户
	 * 不创建任何站点
	 * 会创建一个同名管理员
	 * @param user 
	 * @return 用户 id
	 * @author kingapex
	 */ 	
	public Integer createUser(EopUser user,EopUserAdmin userAdmin);
	
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void edit(EopUser user);
	
	
	/**
	 * 用户管理员登录
	 * 
	 * @param username
	 *            ,用户名称或email
	 * @param password
	 *            ,密码，此处应是未加密过的
	 * @return SessionDTO
	 */
	public SessionDTO login(String username, String password);

 
	public SessionDTO login_bysystem(String username, String password) ;
	
	/**
	 * 注销当前用户
	 */
	public void logout();
	
	
	
	public void  changeDefaultSite(Integer userid, Integer managerid, Integer defaultsiteid);

}
