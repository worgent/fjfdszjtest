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
package com.enation.eop.impl.backend;

import java.util.ArrayList;
import java.util.List;

import com.enation.eop.EopSetting;
import com.enation.eop.core.Response;
import com.enation.eop.core.facade.AbstractFacadeProcessor;
import com.enation.eop.core.impl.StringResponse;
import com.enation.eop.core.resource.IMenuManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.Menu;
import com.enation.eop.model.FacadePage;
import com.enation.framework.context.spring.SpringContextHolder;

public class MenuJsonGetter extends AbstractFacadeProcessor {
	public MenuJsonGetter (FacadePage page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	
	protected Response process() {		
	 
		Response response = new StringResponse();
		String menu = getMenuJson();
		response.setContent(menu);
		return response;
	}
	
	public  String getMenuJson(){
		StringBuffer json = new StringBuffer();
		
		/*
		 * 调用核心api读取站点的菜单
		 */
		IMenuManager menuManager = SpringContextHolder.getBean("menuManager");
		List<Menu> menuList  = menuManager.getMenuList();
		
		List<Menu> syslist  = getMenuList(Menu.MENU_TYPE_SYS,menuList);
		List<Menu> applist  = getMenuList(Menu.MENU_TYPE_APP,menuList);
		List<Menu> extlist  = getMenuList(Menu.MENU_TYPE_EXT,menuList);
		
		json.append("var menu ={");
			json.append("'sys':[");
			json.append(toJson(syslist,menuList));
			json.append("]");
			
			json.append(",'app':[");
			json.append(toJson(applist,menuList));
			json.append("]");	
	
			json.append(",'ext':[");
			json.append(toJson(extlist,menuList));
			json.append("]");	
		json.append("};");
		return json.toString();
		

	}
	
	/**
	 * 将一个menuList转为json数组
	 * @param menuList
	 * @return
	 */
	public  String toJson(List<Menu> menuList,List<Menu> allList){
		StringBuffer menuItem = new StringBuffer();
		int i =0;
		
		for(Menu menu:menuList ){
			if(i!=0) menuItem.append(",");
			menuItem.append( toJson (menu,allList));
			i++;
			
		}
		
		return menuItem.toString();
	}
	

	
	/**
	 * 根据menu实体生成json字串
	 * @param menu
	 * @return
	 */
	private  String toJson(Menu menu,List<Menu> menuList){
		String title  =  menu.getTitle();
		String url = menu.getUrl();
		Integer selected = menu.getSelected();
		String type  = menu.getDatatype();
		String target=menu.getTarget();
 
		if(!"_blank".equals(target)){
			String ctx= EopSetting.CONTEXT_PATH;
			ctx=ctx.equals("/")?"":ctx;
			url=ctx + url;
		}
		
 
		StringBuffer menuItem = new StringBuffer();
		
		menuItem.append("{");
		
		menuItem.append("text:'");
		menuItem.append(title);
		menuItem.append("'");
		
		menuItem.append(",url:'");
		menuItem.append(url);
		menuItem.append("'");
		
		
		menuItem.append(",'default':");
		menuItem.append(selected);
 
		menuItem.append(",children:");
		menuItem.append(getChildrenJson(menu.getId(), menuList));
		
		menuItem.append(",type:'");
		menuItem.append(type);
		menuItem.append("'");
		
		menuItem.append(",target:'");
		menuItem.append(target);
		menuItem.append("'");
		
		menuItem.append("}");
		
		return menuItem.toString();
	}
	
	
	/**
	 * 根据菜单某种类型过滤出menuList
	 * @param menuType
	 * @param menuList
	 * @return
	 */
	public  List<Menu> getMenuList(int menuType,List<Menu> menuList){
		List<Menu> mlist = new ArrayList<Menu>();
		
		for(Menu menu :menuList ){
			if(menu.getMenutype().intValue() ==  menuType &&  menu.getPid().intValue()==0){
				mlist.add(menu);
			}
		}
		
		return mlist;
	}
	
	
	/**
	 * 读取
	 * @param menuId
	 * @param menuList
	 * @return
	 */
	private  String  getChildrenJson(Integer menuId,List<Menu> menuList){
		StringBuffer json = new StringBuffer(); 
		json.append("[");
		int i=0;
		for (Menu menu : menuList) {
			
			if (menuId.intValue() == menu.getPid().intValue()) {
				if(i!=0)
					json.append(",");
				json.append(toJson(menu, menuList));
				i++;
			}
		}
		json.append("]");
		return json.toString();
	}
	

	
	

}
