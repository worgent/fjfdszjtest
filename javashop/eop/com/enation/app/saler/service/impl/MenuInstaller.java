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
package com.enation.app.saler.service.impl;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.saler.service.IInstaller;
import com.enation.eop.core.resource.IMenuManager;
import com.enation.eop.core.resource.model.Menu;

/**
 * 菜单安装器
 * @author kingapex
 * 2010-1-20下午05:37:23
 */
public class MenuInstaller implements IInstaller {
	
	private IMenuManager menuManager;
 
	
	public void install(String productId,Node fragment)  {
		NodeList menuList = fragment.getChildNodes();
		this.addMenu(menuList,0);
	}
	

	
	/**
	 * 将一个NodeList集合中的menu结点数据保存到库中
	 * @param nodeList
	 * @param parentId
	 */
	private void addMenu(NodeList nodeList,Integer parentId){
		 
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() ==Node.ELEMENT_NODE){
				this.addMenu((Element)node,parentId);
			}
		}
	}
	
	
	
	
	/**
	 * 将一个menu结点数据保存在库中
	 * @param ele
	 * @param parentId
	 */
	private void addMenu(Element ele,Integer parentId){

		try {
			
			Menu menu = new Menu();
			menu.setPid(parentId);
	 
			
			String text = ele.getAttribute("text");
			String url = ele.getAttribute("url");
			String type = ele.getAttribute("type");
			String selected  = ele.getAttribute("selected");
			String target  = ele.getAttribute("target");
			if(type==null){
				type =( (Element)ele.getParentNode()).getAttribute("type");
			}
			
			if(target!=null){
				menu.setTarget(target);
			}
			
			int menuType=Menu.MENU_TYPE_APP;
			if("sys".equals(type)) menuType = Menu.MENU_TYPE_SYS;
			if("app".equals(type)) menuType = Menu.MENU_TYPE_APP;
			if("ext".equals(type)) menuType = Menu.MENU_TYPE_EXT;
			
			menu.setMenutype(menuType);
			menu.setTitle(text);
			if((selected!=null)&&(!selected.equals(""))){
				menu.setSelected(Integer.valueOf(selected));
			}
			if(url!=null) menu.setUrl(url);	
			Integer menuid =this.menuManager.add(menu);
			NodeList children = ele.getChildNodes();
			 
			if(children!=null){
				this.addMenu(children,menuid);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException("install menu error");
		}
	}



	public IMenuManager getMenuManager() {
		return menuManager;
	}



	public void setMenuManager(IMenuManager menuManager) {
		this.menuManager = menuManager;
	}


	
}
