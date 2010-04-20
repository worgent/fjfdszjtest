package com.qzgf.application.systemManage.role.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.permission.domain.PermissionFacade;
import com.qzgf.application.systemManage.role.domain.RoleFacade;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.utils.ui.OptionsString;
import com.qzgf.webutils.interceptor.SessionAware;
 
/**
 * 角色管理
 * 
 * @author lsr
 * 
 */
@SuppressWarnings("serial")
public class RoleAction extends BaseAction implements SessionAware {
	private static final Log logger = LogFactory.getLog(RoleAction.class);

	@SuppressWarnings("unchecked")
	private RoleFacade roleFacade;
	private String roleName;
	@SuppressWarnings("unchecked")
	private List<String> permissions = new ArrayList<String>();
	@SuppressWarnings("unchecked")
	private List permissionList = new ArrayList();
	private PermissionFacade permissionFacade;
	private AjaxMessagesJson ajaxMessagesJson;
	@SuppressWarnings("unchecked")
	private List roleList;
	private String roleId;
	private String menuTree;// 权限树
	private UserSession userSession;
	private List<OptionsString> permissionValues = new ArrayList<OptionsString>();

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}

	public String index() {
		return "index";
	}

	public String list() {
		this.setRoleList(this.getRoleFacade().findRolesAll(this.getUserSession().getUserId()));
		return "list";
	}

	// 进入添加角色页面
	@SuppressWarnings("unchecked")
	public String add() {
		this.setAction("addsave");
		List menuList = roleFacade.findAllPermission(this.getUserSession().getUserId());
		StringBuffer menustr = new StringBuffer();
		StringBuffer checkstr = new StringBuffer();
		menustr.append("<script type='text/javascript'>");
		menustr.append("var d = new dTree('d','images/system/menu/');");
		menustr.append("d.config.folderLinks=true;");
		menustr.append("d.config.useCookies=false;");
		menustr.append("d.config.check=true;");
		java.util.Iterator it = menuList.iterator();
		@SuppressWarnings("unused")
		Map menuitem = new HashMap();
		while (it.hasNext()) {
			menuitem = (HashMap) it.next();
			menustr.append("d.add(" + menuitem.get("PERMISSIONID") + ","
					+ menuitem.get("PARENTPERMISSIONID") + ",'"
					+ menuitem.get("PERMISSIONNAME") + "');");
			int checked = Integer.parseInt("" + menuitem.get("CHECKED"));
			if (checked > 0) {
				if(checkstr.length()==0){
					checkstr.append("{menudm:'" + menuitem.get("PERMISSIONID")+ "'}");
				}else{
					checkstr.append(",{menudm:'" + menuitem.get("PERMISSIONID")+ "'}");
				}
			}
		}
		checkstr.append("]})");
		menustr.append("var funcs = eval(({funcs:[" + checkstr.toString() + ");");
		menustr.append("document.write(d);");
		menustr.append("</script>");
		menuTree = menustr.toString();
		return "roleAdd";
	}

	// 角色添加
	@SuppressWarnings("unchecked")
	public String addsave() {
		if (StringUtils.isBlank(this.getRoleName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", "角色名称不能为空!");
			return RESULT_AJAXJSON;
		}

		try {
			roleName = URLDecoder.decode(roleName, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		search.put("roleName", roleName);
		search.put("permissions", this.getPermissions());
		search.put("userId", this.getUserSession().getUserId());
		try {
			this.getRoleFacade().insertRole(search);
			this.getAjaxMessagesJson().setMessage("0", this.getText("添加角色成功!"));
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_ROLE_ADDFAILED",
					this.getText("添加角色出错!"));
		}
		return RESULT_AJAXJSON;
	}

	/**
	 * 进入修改角色页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String edit() {
		this.setAction("editsave");
		List roleList = this.getRoleFacade().findRoleByRoleId(this.getRoleId());

		for (int i = 0; i < roleList.size(); i++) {
			// 封装这个角色的所有的权限
			Map roleMap = (Map) roleList.get(i);
			permissionList.add("" + roleMap.get("PERMISSIONID"));
		}
		this.setRoleId(this.getRoleId());
		this.setRoleName((String) ((Map) roleList.get(0)).get("ROLENAME"));
		
		search.put("roleId", this.getRoleId());
		search.put("userId", this.getUserSession().getUserId());
		List menuList = roleFacade.findMenuByUserId(search);
		StringBuffer menustr = new StringBuffer();
		StringBuffer checkstr = new StringBuffer();
		menustr.append("<script type='text/javascript'>");
		menustr.append("var d = new dTree('d','images/system/menu/');");
		menustr.append("d.config.folderLinks=true;");
		menustr.append("d.config.useCookies=false;");
		menustr.append("d.config.check=true;");
		java.util.Iterator it = menuList.iterator();
		@SuppressWarnings("unused")
		Map menuitem = new HashMap();
		while (it.hasNext()) {
			menuitem = (HashMap) it.next();
			menustr.append("d.add(" + menuitem.get("PERMISSIONID") + ","
					+ menuitem.get("PARENTPERMISSIONID") + ",'"
					+ menuitem.get("PERMISSIONNAME") + "');");
			int checked = Integer.parseInt("" + menuitem.get("CHECKED"));
			if (checked > 0) {
				if(checkstr.length()==0){
					checkstr.append("{menudm:'" + menuitem.get("PERMISSIONID")+ "'}");
				}else{
					checkstr.append(",{menudm:'" + menuitem.get("PERMISSIONID")+ "'}");
				}
			}
		}
		checkstr.append("]})");
		menustr.append("var funcs = eval(({funcs:[" + checkstr.toString() + ");");
		menustr.append("document.write(d);");
		menustr.append("</script>");
		menuTree = menustr.toString();
		return "roleSet";
	}

	/**
	 * 角色(权限)修改
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editsave() {
		if (StringUtils.isBlank(this.getRoleName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", "角色名称不能为空!");
			return RESULT_AJAXJSON;
		}
		try {
			roleName = URLDecoder.decode(roleName, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		search.put("roleId", roleId);
		search.put("roleName", roleName);
		search.put("permissions", this.getPermissions());

		try {
			this.getRoleFacade().updateRole(search);
			this.getAjaxMessagesJson().setMessage("0", "角色修改成功");
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("ROLE_FAILED", "修改角色出错");
		}
		return RESULT_AJAXJSON;
	}

	// 删除角色
	@SuppressWarnings("unchecked")
	public String del() {

		try {
			search.clear();
			search.put("roleId", roleId);
			if(roleId.equals("1")){
				this.getAjaxMessagesJson().setMessage("ROLE_DELFAILED", "角色删除失败!不能删除超级管理员");
				return RESULT_AJAXJSON;
			}
				
			this.getRoleFacade().deleteRoleByRoleId(search);
			this.getAjaxMessagesJson().setMessage("0", "角色删除成功!");
		} catch (Exception ex1) {
			this.getAjaxMessagesJson().setMessage("ROLE_DELFAILED", "角色删除失败!");
		}
		return RESULT_AJAXJSON;
	}

	public RoleFacade getRoleFacade() {
		return roleFacade;
	}

	public void setRoleFacade(RoleFacade roleFacade) {
		this.roleFacade = roleFacade;
	}

	@SuppressWarnings("unchecked")
	public List getRoleList() {
		return roleList;
	}

	@SuppressWarnings("unchecked")
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public PermissionFacade getPermissionFacade() {
		return permissionFacade;
	}

	public void setPermissionFacade(PermissionFacade permissionFacade) {
		this.permissionFacade = permissionFacade;
	}

	public List<OptionsString> getPermissionValues() {
		return permissionValues;
	}

	public void setPermissionValues(List<OptionsString> permissionValues) {
		this.permissionValues = permissionValues;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	@SuppressWarnings("unchecked")
	public List getPermissionList() {
		return permissionList;
	}

	@SuppressWarnings("unchecked")
	public void setPermissionList(List permissionList) {
		this.permissionList = permissionList;
	}

	public String getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(String menuTree) {
		this.menuTree = menuTree;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	
	
}
