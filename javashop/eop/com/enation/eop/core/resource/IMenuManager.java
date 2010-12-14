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

import com.enation.eop.core.resource.model.Menu;

/**
 * 
 * 菜单管理
 * @author lzf
 *         <p>
 *         2009-12-16 上午11:05:28
 *         </p>
 * @version 1.0
 */
public interface IMenuManager {
	
	 
	/**
	 * 添加菜单项
	 * @param menu
	 * @return
	 */
	public Integer add(Menu menu);
	
	
	/**
	 * 读取菜单列表
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List<Menu> getMenuList();


	/**
	 * 读取某菜单列表并形成Tree格式
	 * @param menuid 要读取的顶级菜单id ,0为读取所有菜单
	 * @return
	 * @since 2.1.3 
	 * @author kingapex
	 */
	public List<Menu> getMenuTree(Integer menuid);
	
}
