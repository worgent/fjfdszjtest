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

import java.util.List;

import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.core.resource.model.SiteManagerView;
import com.enation.framework.database.Page;

public interface IAdminUserManager {
	
	
	/**
	 * 添加一个管理员
	 * @param eopUserAdmin
	 */
	public Integer add(EopUserAdmin eopUserAdmin);
	
	
	
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public EopUserAdmin  login(String username,String password);
	
	
	/**
	 * 读取管理员信息
	 * @param id
	 * @return
	 */
	public EopUserAdmin get(Integer id);
	
	
	/**
	 * 修改管理员信息 
	 * @param eopUserAdmin
	 */
	public void edit(EopUserAdmin eopUserAdmin);
	
	
	/**
	 * 删除管理员
	 * @param id
	 */
	public void delete(Integer id);
	
	
	
	
	/**
	 * 管理员修改密码 
	 * @param userid
	 * @param managerid
	 * @param password
	 */
	public void changePassword(Integer managerid, String password);
	
	

	/**
	 * 管理员修改密码
	 * @param userid
	 * @param managerid
	 * @param password
	 * @param oldpassword
	 * @return
	 */
	public void changePassword( Integer managerid, String password, String oldpassword);

	
	
	
	/**
	 * 切换某管理员的默认站点
	 * @param managerid
	 * @param siteid
	 */
	public void changeDefaultSite(Integer managerid,Integer siteid );
	
	public List<SiteManagerView> getViewList( Integer managerid);
	public void putView(Integer managerid, Integer[] siteList);
	public Page list(int pageNo, int pageSize, 	String order, String search) ;
	
}
