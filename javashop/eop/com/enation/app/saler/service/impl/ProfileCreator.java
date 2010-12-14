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

import java.io.FileOutputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IMenuManager;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.IThemeUriManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.Menu;
import com.enation.eop.core.resource.model.Theme;
import com.enation.eop.core.resource.model.ThemeUri;
import com.enation.framework.util.StringUtil;

public class ProfileCreator  {
	
	private IThemeUriManager themeUriManager;
	private IMenuManager menuManager;
	private IThemeManager themeManager; 
	
	public void createProfile(String path){
		
		//product根结点
		Element product = new Element("product");
		
		
		/**
		 * 创建app结点，当前版本为写死的shop 和cms
		 */
		Element apps = new Element("apps");
		Element shopApp = new Element("app");
		shopApp.setAttribute("id","shop");
		apps.addContent(shopApp);
		
		Element cmsApp = new Element("app");
		cmsApp.setAttribute("id","cms");
		apps.addContent(cmsApp);
		
		product.addContent(apps);
		
		
		
		
		
		/**
		 * url映射结点
		 */
		Element urlsEl = new Element("urls");
		this.fillUrlElement(urlsEl);
		product.addContent(urlsEl);
		
		/**
		 * menu结点
		 */
		Element menusEl = new Element("menus");
		this.fillMenuElement(menusEl);
		product.addContent(menusEl);
		
		Element themesEl = new Element("themes");
		fillThemesElement(themesEl);
		product.addContent(themesEl);
		
		Document pfDocument = new   Document(product);
		this.outputDocumentToFile(pfDocument, path);
		
	}
	
	/**
	 * 读取uri数据并且形成element 添加到uris 元素 
	 * @param uris  要填充的uri Element
	 */
	private void fillUrlElement(Element uris){
		List<ThemeUri> uriList = themeUriManager.list();
		
		for(ThemeUri themeUri:uriList){
			
			String uri =themeUri.getUri();
			String path  = themeUri.getPath();
			Element urlEl = new Element("url");
			urlEl.setAttribute("from", uri);
			urlEl.setAttribute("to",path);
			uris.addContent(urlEl);
		}
	 
	}
	
	
	/**
	 * 为menus结点填充menu子结点
	 * 数据来源为menu表
	 * @param menuParentEl menus结点
	 */
	private void fillMenuElement(Element menuParentEl){
		List<Menu> menuTree = menuManager.getMenuTree(0);
		this.fillChildMenu(menuTree, menuParentEl);
		
	}
	
	
	/**
	 * 由一个集合中查找子并填充子结点，有子孙则递归调用
	 * @param menuList 集合
	 * @param parentEl 要填充的结点
	 */
	private void fillChildMenu(List<Menu> menuList,Element parentEl){
		for(Menu menu : menuList){
			if(menu.getMenutype().intValue() == 1) continue;
			Element menuEl = new Element("menu");
			menuEl.setAttribute("text",menu.getTitle());
			String url = menu.getUrl();
			if(!StringUtil.isEmpty(url)){
				menuEl.setAttribute("url",url);
			}
			
			List<Menu> children = menu.getChildren();
			if(children!=null && !children.isEmpty()){
				fillChildMenu(children,menuEl);
			}
			parentEl.addContent(menuEl);
		}
	}
	
	
	private void fillThemesElement(Element parentEl){
		List<Theme> themeList =  this.themeManager.getThemeList();
		EopSite site  = EopContext.getContext().getCurrentSite();
		for(Theme theme:themeList){
			Element themeEl = new Element("theme");
			themeEl.setAttribute("id",theme.getPath());
			themeEl.setAttribute("name",theme.getThemename());
			if(site.getThemeid().intValue() == theme.getId().intValue())
				themeEl.setAttribute("default","yes");
			
			parentEl.addContent(themeEl);
		}
	 
	}
	
	
   private void outputDocumentToFile(Document  myDocument,String path) {
     
        try {
            Format format = Format.getCompactFormat();    
            format.setEncoding("UTF-8"); 
            format.setIndent("    ");        	
            XMLOutputter outputter =    new XMLOutputter(format);
            outputter.output(myDocument, new FileOutputStream(path));
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }
   
   
   


	public IThemeUriManager getThemeUriManager() {
		return themeUriManager;
	}

	public void setThemeUriManager(IThemeUriManager themeUriManager) {
		this.themeUriManager = themeUriManager;
	}

	public IMenuManager getMenuManager() {
		return menuManager;
	}

	public void setMenuManager(IMenuManager menuManager) {
		this.menuManager = menuManager;
	}

	public IThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}
   
   
}
