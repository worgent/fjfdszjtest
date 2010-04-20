package com.qzgf.application.systemManage.permission.action;
 
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.permission.domain.PermissionFacade;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.utils.ui.RadioInt;
import com.qzgf.webutils.interceptor.SessionAware;

/**
 * 权限管理
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class PermissionAction extends BaseAction implements SessionAware  {
	private static final Log logger = LogFactory.getLog(PermissionAction.class);
	
	
	@SuppressWarnings("unchecked")
	private List permissionList;
	private String permissionTree;// 权限树
	private PermissionFacade permissionFacade;
	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private String isMenu;//是否菜单
	private String permissionName;
	private String resource;//资源
	private String actionName;
	private String perentPermissionId;//父级权限ID
	private String id;
	private AjaxMessagesJson ajaxMessagesJson;
	private UserSession userSession;
	private String curMenu;
	private String permissionOrder;
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}
	
	//罗列出所有的权限
	@SuppressWarnings("unchecked")
	public String list() {
		return "list";
	}
	
	@SuppressWarnings("unchecked")
	public String check(){
		int count =this.permissionFacade.checkSubPermissionById(id);
		if(count>0){
			this.getAjaxMessagesJson().setMessage("E_Permission_DELFAILED", "该菜单还包括子菜单，须先删除子菜单项!");
		}else if(count==0){
			this.getAjaxMessagesJson().setMessage("0", "可以删除");
		}
		return RESULT_AJAXJSON;
	}
	
	@SuppressWarnings("unchecked")
	public String index(){
		List menuList =this.permissionFacade.findSubPermissionById(perentPermissionId);
		StringBuffer menustr = new StringBuffer();
		menustr.append("<tree>");
		java.util.Iterator it = menuList.iterator();
		Map menuitem = new HashMap();
		
		while (it.hasNext()) {
			menuitem = (HashMap) it.next();
			menustr.append("<tree text=\""+menuitem.get("PERMISSIONNAME")+"("+menuitem.get("PERMISSIONORDER")+")"+"\" src=\"permission.do?perentPermissionId="+
					menuitem.get("PERMISSIONID")+"&amp;date="+new Date()+"\" />");
		}
		menustr.append("</tree>");
		curMenu=menustr.toString();
		return "treemenu";
	}
	
	//进入添加页面
	public String add() {
		this.setAction("addsave");
		this.setRadioYesNoListValues();
		return INPUT;
	}
	
	private void setRadioYesNoListValues() {
		this.setIsMenu("1");
		radioYesNoList.add(new RadioInt(0, "否"));
		radioYesNoList.add(new RadioInt(1, "是"));
	}

	//添加保存
	@SuppressWarnings("unchecked")
	public String addsave() {
		try {
			if (StringUtils.isBlank(this.getPermissionName())) {
				this.getAjaxMessagesJson().setMessage("E_NULL", "菜单名称不能为空!");
				return RESULT_AJAXJSON;
			}

			try {
				permissionName = URLDecoder.decode(permissionName, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			search.put("permissionName", permissionName);
			search.put("isMenu", this.getIsMenu());
			search.put("resource", this.getResource());
			search.put("actionName", this.getActionName());
			search.put("perentPermissionId",this.getPerentPermissionId());
			search.put("userId", this.getUserSession().getUserId());
			search.put("permissionOrder", this.getPermissionOrder());
			long result=this.getPermissionFacade().insertPermission(search);
			this.getAjaxMessagesJson().setMessage("0", ""+result);
			return RESULT_AJAXJSON;
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_Permission_ADDFAILED", "添加菜单出错!");
			return RESULT_AJAXJSON;
		}
	}
	
	//删除菜单
	@SuppressWarnings("unchecked")
	public String del() {
		try {
			if (StringUtils.isBlank(this.getId())) {
				this.getAjaxMessagesJson().setMessage("E_NULL", "请先选择父节点菜单!");
				return RESULT_AJAXJSON;
			}
			this.getPermissionFacade().deletePermission(this.getId());
			this.getAjaxMessagesJson().setMessage("0", "菜单删除成功");
			return RESULT_AJAXJSON;
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_Permission_DELFAILED", "菜单删除出错!");
			return RESULT_AJAXJSON;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List getPermissionList() {
		return permissionList;
	}

	@SuppressWarnings("unchecked")
	public void setPermissionList(List permissionList) {
		this.permissionList = permissionList;
	}

	public PermissionFacade getPermissionFacade() {
		return permissionFacade;
	}

	public void setPermissionFacade(PermissionFacade permissionFacade) {
		this.permissionFacade = permissionFacade;
	}
	
	public List<RadioInt> getRadioYesNoList() {
		return radioYesNoList;
	}

	public void setRadioYesNoList(List<RadioInt> radioYesNoList) {
		this.radioYesNoList = radioYesNoList;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getPerentPermissionId() {
		return perentPermissionId;
	}

	public void setPerentPermissionId(String perentPermissionId) {
		this.perentPermissionId = perentPermissionId;
	}

	public String getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String getPermissionTree() {
		return permissionTree;
	}

	public void setPermissionTree(String permissionTree) {
		this.permissionTree = permissionTree;
	}

	public String getCurMenu() {
		return curMenu;
	}

	public void setCurMenu(String curMenu) {
		this.curMenu = curMenu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermissionOrder() {
		return permissionOrder;
	}

	public void setPermissionOrder(String permissionOrder) {
		this.permissionOrder = permissionOrder;
	}

	
	
}
