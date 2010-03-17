package com.qzgf.application.systemManage.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.login.domain.LoginFacade;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.webutils.interceptor.SessionAware;

/**
 * 左侧菜单动态加载
 * 
 * @author lsr
 * 
 */
@SuppressWarnings("serial")
public class MenuAction extends BaseAction implements SessionAware{

	private LoginFacade loginFacade;
	private String menuTree;
	private UserSession userSession;

	@SuppressWarnings("unchecked")
	public String execute() {
		
		List menuList = loginFacade.findMenuByUserId(this.getUserSession().getUserId());
		StringBuffer menustr = new StringBuffer();
		menustr.append("<script type='text/javascript'>\r\n");
		menustr.append("var d = new dTree('d','images/system/menu/');");
		menustr.append("d.config.folderLinks=true\r\n");
		menustr.append("d.config.target = 'ems_mainFrame'\r\n");
		java.util.Iterator it= menuList.iterator();
		@SuppressWarnings("unused")
		Map menuitem=new HashMap();
		while (it.hasNext()){
			menuitem = (HashMap)it.next();
			menustr.append("d.add("+menuitem.get("PERMISSIONID")+","+menuitem.get("PARENTPERMISSIONID")+
					",'"+menuitem.get("PERMISSIONNAME")+"'"); 
					String url=(String)menuitem.get("PERMISSIONRESOURCE");
					if(null!=url&&!"".equals(url)){
						//是一个URL
						String action=(String)menuitem.get("ACTION");
						if(null!=action&&!"".equals(action)){
							menustr.append(",'"+url+".do?action="+action+"')\r\n");
						}else{
							menustr.append(",'"+url+".do')\r\n");
						}
					}else{
						menustr.append(")\r\n");
					}
		}	
		menustr.append("document.write(d);");
		menustr.append("</script>\r\n");
		menuTree = menustr.toString();
		return "rolemenu";
	}

	public String getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(String menuTree) {
		this.menuTree = menuTree;
	}

	public LoginFacade getLoginFacade() {
		return loginFacade;
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	
	
}
