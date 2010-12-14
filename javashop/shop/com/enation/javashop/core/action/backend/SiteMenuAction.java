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
package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.SiteMenu;
import com.enation.javashop.core.service.ISiteMenuManager;

/**
 * 站点菜单
 * @author kingapex
 *
 */
public class SiteMenuAction extends WWAction {
	private ISiteMenuManager siteMenuManager ;
	private List menuList;
	private Integer[] sortArray;
	private Integer[] menuidArray;
	private Integer menuid;
	private SiteMenu siteMenu;
	private boolean isEdit;
	
	
	public String list(){
		menuList  = siteMenuManager.list(0);
		return "list";
	}
	
	public String updateSort(){
		siteMenuManager.updateSort(menuidArray, sortArray);
		return this.list();
	}
	
	
	public String add(){
		isEdit =false;
		this.menuList = this.siteMenuManager.list(0);
		siteMenu= new SiteMenu();
		return this.INPUT;
	}
	
	public String edit(){
		isEdit=true;
		this.menuList = this.siteMenuManager.list(0);
		siteMenu  =siteMenuManager.get(menuid);
		return this.INPUT;
	}
	
	public String save(){
		if(menuid==null){
			this.siteMenuManager.add(siteMenu);
			this.msgs.add("菜单添加成功");
		}else{
			siteMenu.setMenuid(menuid);
			this.siteMenuManager.edit(siteMenu);
			this.msgs.add("菜单修改成功");
		}
		
		this.urls.put("菜单列表", "siteMenu!list.do");
		return this.MESSAGE;
	}
	
	public String delete(){
		
		this.siteMenuManager.delete(menuid);
		this.msgs.add("菜单删除成功");
		this.urls.put("菜单列表", "siteMenu!list.do");
		return this.MESSAGE;
	}
	
	public ISiteMenuManager getSiteMenuManager() {
		return siteMenuManager;
	}
	public void setSiteMenuManager(ISiteMenuManager siteMenuManager) {
		this.siteMenuManager = siteMenuManager;
	}
	public List getMenuList() {
		return menuList;
	}
	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public Integer[] getSortArray() {
		return sortArray;
	}

	public void setSortArray(Integer[] sortArray) {
		this.sortArray = sortArray;
	}

	public Integer[] getMenuidArray() {
		return menuidArray;
	}

	public void setMenuidArray(Integer[] menuidArray) {
		this.menuidArray = menuidArray;
	}

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public SiteMenu getSiteMenu() {
		return siteMenu;
	}

	public void setSiteMenu(SiteMenu siteMenu) {
		this.siteMenu = siteMenu;
	}

	public boolean getIsEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	
}
