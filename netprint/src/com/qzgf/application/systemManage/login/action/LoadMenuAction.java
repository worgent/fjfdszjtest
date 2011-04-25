package com.qzgf.application.systemManage.login.action;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.systemManage.login.domain.LoginFacade;
import com.qzgf.application.systemManage.manager.dto.UserInfo;
import com.qzgf.application.systemManage.menu.dto.MenuInfo;

public class LoadMenuAction implements Action{
	private Log log = LogFactory.getLog(LoadMenuAction.class);
	
	private MenuInfo menuInfo = new MenuInfo();
	private LoginFacade loginFacade ;
	@SuppressWarnings("unchecked")
	private List menus ;
	private String curMenu ;
	private String type = "popu";
	private String json = "";
	private String menuId;
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		UserInfo userInfo = ((UserInfo)ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
    	if(log.isDebugEnabled()){
    		log.debug("getmenu by loginer:"+userInfo.getStaffId()+";menu id:"+menuInfo.getMenuId());
    	}
    	
    	if (null != menuId && !"".equals(menuId)){
    		menuInfo.setMenuId(menuId);
    	}
    	
    	menus=loginFacade.findMenuById(String.valueOf(userInfo.getStaffId()),menuInfo.getMenuId());
    	StringBuffer menu = new StringBuffer();
    	java.util.Iterator it= menus.iterator();
    	
    	if("popu".equals(type)){

	    	menu.append("<?xml version=\"1.0\" encoding=\"gb2312\" ?>");
	    	
	    	if(menuInfo.getMenuId()==null || menuInfo.getMenuId().equals("0") || menuInfo.getMenuId().equals("Root")){
	    		menu.append("<MenuNodes id=\"Root\" root=\"1\">");
	    	}else{
	    		menu.append("<MenuNodes id=\""+menuInfo.getMenuId()+"\">");
	    	}
	    		while (it.hasNext()){
	    			menuInfo = (MenuInfo)it.next();
	    			menu.append("<MenuNode  text=\""+menuInfo.getMenuName()+"\"");
	    			if("1".equals(menuInfo.getIsSeperator())){
	    				menu.append(" Seperator=\"1\" ");
	    			}
	    			if("1".equals(menuInfo.getHaveSub())){
	    				menu.append(" submenu=\"").append(menuInfo.getMenuId()).append("\"");
	    			}else if(menuInfo.getActiveEvent()!=null && menuInfo.getActiveEvent().length()>0){
	    	
	    				menu.append(" onclick=\""+menuInfo.getActiveEvent()+"\"");
	    			}
	    			if(menuInfo.getImgSrc()!=null && menuInfo.getImgSrc().trim().length()>0 ){
	    				menu.append(" img=\"").append(menuInfo.getImgSrc()).append("\"");
	    			}else{
	    				menu.append(" img=\"/themes/default/images/transparent.gif\"");
	    			}
	    			if("1".equals(menuInfo.getHaveSub())){
	    				menu.append(" havSub=\"1\"");
	    			}
	    			if(menuInfo.getTitle()!=null && menuInfo.getTitle().trim().length()>0){
	    				menu.append(" title=\"").append(menuInfo.getTitle()).append("\"");
	    			}
	    			menu.append("/>");			
	    		}
	    	menu.append("</MenuNodes>");
	    	curMenu = menu.toString();
    	}else if("tree".equals(type)){
    		menu.append("<TreeNode>");
    		while (it.hasNext()){
    			
    			menuInfo = (MenuInfo)it.next();
    			if("1".equals(menuInfo.getIsSeperator()))
    				continue;
    			menu.append("<TreeNode text=\"").append(menuInfo.getMenuName()).append("\" value=\"")
    			.append(menuInfo.getMenuId()).append("\"");
    			if("1".equals(menuInfo.getHaveSub())){
    				menu.append(" src=\"/loadMenu.shtml?menuInfo.menuId=").append(menuInfo.getMenuId()).append("&amp;type=tree\"");
    			}
    			if(menuInfo.getUrl()!=null && menuInfo.getUrl().trim().length()>0){
    				menu.append(" action=\"").append(menuInfo.getUrl());
    				if(menuInfo.getUrl().indexOf("?")==-1){
    					menu.append("?");
    				}else{
    					menu.append("&amp;");
    				}
    				menu.append("TitleItem=").append(menuInfo.getMenuName()).append("\"");
    			}
    			menu.append("  target=\"MainFrm\" />");
    			
    		}
    		menu.append("</TreeNode>");
    		curMenu = menu.toString();
    		return "treemenu";
    	}else if ("json".equals(type)){
    		while (it.hasNext()){
    			menuInfo = (MenuInfo)it.next();
	    		if (null != menu && !"".equals(menu.toString())){
	    			menu.append(",");
	    		}
	    		menu.append("{");
	    		menu.append("\"text\":\"").append(menuInfo.getMenuName()).append("\"");
	    		menu.append(",\"id\":\"").append(menuInfo.getMenuId()).append("\"");
	    		
	    		//取点击时的URL
	    		if (null != menuInfo.getActiveEvent() && !"".equals(menuInfo.getActiveEvent()))
	    			menu.append(",\"url\":\"").append(menuInfo.getActiveEvent()).append("\"");
	    		
	    		//取出标题
	    		if (null != menuInfo.getTitle() && !"".equals(menuInfo.getTitle()))
	    			menu.append(",\"title\":\"").append(menuInfo.getTitle()).append("\"");
	    		
	    		//判断是否有包含子节点
	    		if ("1".equals(menuInfo.getHaveSub())){
	    			menu.append(",\"cls\":\"folder\"");
	    		}else{
	    			//取图片，
	    			if (null != menuInfo.getImgSrc() && !"".equals(menuInfo.getImgSrc())){
	    				menu.append(",\"cls\":\"").append(menuInfo.getImgSrc()).append("\"");
	    			}else{
	    				menu.append(",\"cls\":\"file\"");
	    			}
	    			menu.append(",\"leaf\":true");
	    		}
	    		menu.append("}");
    		}
    		json = "[" +menu.toString()+ "]";
    		return "json";
    	}

    	
        return SUCCESS;
	}

	public MenuInfo getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(MenuInfo menuInfo) {
		this.menuInfo = menuInfo;
	}

	public LoginFacade getLoginFacade() {
		return loginFacade;
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	@SuppressWarnings("unchecked")
	public List getMenus() {
		return menus;
	}

	@SuppressWarnings("unchecked")
	public void setMenus(List menus) {
		this.menus = menus;
	}

	public String getCurMenu() {
		return curMenu;
	}

	public void setCurMenu(String curMenu) {
		this.curMenu = curMenu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
