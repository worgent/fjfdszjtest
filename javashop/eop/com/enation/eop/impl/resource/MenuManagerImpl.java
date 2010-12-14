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
package com.enation.eop.impl.resource;

import java.util.ArrayList;
import java.util.List;

import com.enation.eop.core.resource.IMenuManager;
import com.enation.eop.core.resource.model.Menu;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.javashop.core.model.Cat;

/**
 * 菜单管理
 * @author kingapex
 *2010-5-10下午02:00:10
 */
public class MenuManagerImpl extends BaseSupport<Menu> implements IMenuManager {

	
	public List<Menu> getMenuList() {
		return this.baseDaoSupport.queryForList("select * from menu where deleteflag = '0'", Menu.class);
	}

	
	public Integer add(Menu menu) {
		this.baseDaoSupport.insert("menu", menu);
		return this.daoSupport.getLastId("menu");
	}


	public List<Menu> getMenuTree(Integer menuid) {
		List<Menu> menuList  = this.getMenuList();
		List<Menu> topMenuList  = new ArrayList<Menu>();
		for(Menu menu :menuList){
			if(menu.getPid().compareTo(menuid)==0){
				List<Menu> children = this.getChildren(menuList, menu.getId());
				menu.setChildren(children);
				topMenuList.add(menu);
			}
		}
		return topMenuList;
	}

	/**
	 * 在一个集合中查找子
	 * @param menuList 所有菜单集合
	 * @param parentid 父id
	 * @return 找到的子集合
	 */
	private List<Menu> getChildren(List<Menu> menuList ,Integer parentid){
		List<Menu> children =new ArrayList<Menu>();
		for(Menu menu :menuList){
			if(menu.getPid().compareTo(parentid)==0){
			 	menu.setChildren(this.getChildren(menuList, menu.getId()));
				children.add(menu);
			}
		}
		return children;
	}
	
}
