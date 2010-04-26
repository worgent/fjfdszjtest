package com.qzgf.application.systemManage.login.action;

import java.util.List;

import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.login.domain.LoginFacade;
import com.qzgf.application.systemManage.login.dto.MenuInfo;

public class LoadMenu extends BaseAction{
	private String curMenu ;
	@SuppressWarnings("unchecked")
	private List menus ;
	private LoginFacade loginFacade ;
	private MenuInfo menuInfo = new MenuInfo();
	
	@SuppressWarnings("unchecked")
	public String execute() {
		menus=loginFacade.findMenuById("1","");
		
		StringBuffer menu = new StringBuffer();
		menu.append("<?xml version=\"1.0\" encoding=\"gb2312\" ?>");
    	
		java.util.Iterator it= menus.iterator();
		
    	if(menuInfo.getId()==null || menuInfo.getId().equals("0") || menuInfo.getId().equals("Root")){
    		menu.append("<MenuNodes id=\"Root\" root=\"1\">");
    	}else{
    		menu.append("<MenuNodes id=\""+menuInfo.getId()+"\">");
    	}
    		while (it.hasNext()){
    			menuInfo = (MenuInfo)it.next();
    			menu.append("<MenuNode  text=\""+menuInfo.getMenuName()+"\"");
    			
    			if("1".equals(menuInfo.getHaveSub())){
    				menu.append(" submenu=\"").append(menuInfo.getId()).append("\"");
    			}else if(menuInfo.getUrl()!=null && menuInfo.getUrl().length()>0){
    	
    				menu.append(" onclick=\""+menuInfo.getUrl()+"\"");
    			}
    			
    			if("1".equals(menuInfo.getHaveSub())){
    				menu.append(" havSub=\"1\"");
    			}
    			if(menuInfo.getMenuName()!=null && menuInfo.getMenuName().trim().length()>0){
    				menu.append(" title=\"").append(menuInfo.getMenuName()).append("\"");
    			}
    			menu.append("/>");			
    		}
    	menu.append("</MenuNodes>");
    	curMenu = menu.toString();
    	return "treemenu";
	}
	public String getCurMenu() {
		return curMenu;
	}
	public void setCurMenu(String curMenu) {
		this.curMenu = curMenu;
	}
	@SuppressWarnings("unchecked")
	public List getMenus() {
		return menus;
	}
	
	@SuppressWarnings("unchecked")
	public void setMenus(List menus) {
		this.menus = menus;
	}
	public LoginFacade getLoginFacade() {
		return loginFacade;
	}
	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}
	
	
}
