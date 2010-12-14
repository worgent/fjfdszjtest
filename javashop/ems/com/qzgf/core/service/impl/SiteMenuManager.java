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
package com.qzgf.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.impl.support.BaseSupport;
import com.qzgf.core.model.SiteMenu;
import com.qzgf.core.service.ISiteMenuManager;

public class SiteMenuManager extends BaseSupport<SiteMenu> implements ISiteMenuManager {

	
	public void add(SiteMenu siteMenu) {
		this.baseDaoSupport.insert("ems_site_menu", siteMenu);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	
	public void delete(Integer id) {
		
		String sql  ="delete from ems_site_menu where parentid =?";
		this.baseDaoSupport.execute(sql,  id );
		sql =  "delete from  ems_site_menu   where menuid=?";
		this.baseDaoSupport.execute(sql,  id );
	}

	
	public void edit(SiteMenu siteMenu) {
		this.baseDaoSupport.update("ems_site_menu", siteMenu,"menuid="+siteMenu.getMenuid());
	}

	
	public List<SiteMenu> list(Integer parentid) {
		String sql  ="select * from ems_site_menu order by parentid,sort";
		List<SiteMenu> menuList  = this.baseDaoSupport.queryForList(sql, SiteMenu.class);
		List<SiteMenu> topMenuList  = new ArrayList<SiteMenu>();
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找"+parentid+"的子...");
		}
		for(SiteMenu menu :menuList){
			if(menu.getParentid().compareTo(parentid)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug("发现子["+menu.getName()+"-"+menu.getMenuid()+"]");
				}
				List<SiteMenu> children = this.getChildren(menuList, menu.getMenuid());
				menu.setChildren(children);
				topMenuList.add(menu);
			}
		}
		
		return topMenuList;
	}
	
	/**
	 * 
	 * Purpose      : 叠代叶子结点
	 * @param menuList
	 * @param parentid
	 * @return
	 */
	private List<SiteMenu> getChildren(List<SiteMenu> menuList ,Integer parentid){
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找["+parentid+"]的子");
		}
		List<SiteMenu> children =new ArrayList<SiteMenu>();
		for(SiteMenu menu :menuList){
			if(menu.getParentid().compareTo(parentid)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug(menu.getName()+"-"+menu.getMenuid()+"是子");
				}
			 	menu.setChildren(this.getChildren(menuList, menu.getMenuid()));
				children.add(menu);
			}
		}
		return children;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	
	public void updateSort(Integer[] menuid, Integer[] sort) {
		
		if(menuid==null || sort == null )  throw new  IllegalArgumentException("menuid or sort is NULL");
		if(menuid.length != sort.length )  throw new  IllegalArgumentException("menuid or sort length not same");
		for(int i=0;i<menuid.length;i++){
			this.baseDaoSupport.execute("update ems_site_menu set sort=? where menuid=?",sort[i],menuid[i]);
		}
		
	}




	
	public SiteMenu get(Integer menuid) {
		 
		return this.baseDaoSupport.queryForObject("select * from ems_site_menu where menuid=?", SiteMenu.class, menuid);
	}

}
