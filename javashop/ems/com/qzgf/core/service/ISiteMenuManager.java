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
package com.qzgf.core.service;

import java.util.List;

import com.qzgf.core.model.SiteMenu;



/**
 * 站点菜单管理接口
 * @author kingapex
 * 2010-5-20下午02:54:53
 */
public interface ISiteMenuManager {

	/**
	 * 添加
	 * 
	 * @param siteMenu
	 */
	public void add(SiteMenu siteMenu);

	
	/**
	 * 读取菜单详细
	 * @param menuid
	 * @return
	 */
	public SiteMenu get(Integer menuid);
	
	
	
	/**
	 * 修改
	 * 
	 * @param siteMenu
	 */
	public void edit(SiteMenu siteMenu);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(Integer id);

	
	
	/**
	 * 读取子菜单列表，包括其所有子的
	 * 
	 * @param parentid
	 * @return
	 */
	public List<SiteMenu> list(Integer parentid);
	
	
	
	
	/**
	 * 更新排序
	 * @param menuid 菜单id数组
	 * @param sort	排序值数组
	 * menuid 和sort要一一对应
	 */
	public void updateSort(Integer[] menuid,Integer[] sort);	

}
