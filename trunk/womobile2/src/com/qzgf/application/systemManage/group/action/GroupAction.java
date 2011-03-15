package com.qzgf.application.systemManage.group.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.group.domain.GroupFacade;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.utils.ui.OptionsString; 
import com.qzgf.webutils.interceptor.SessionAware;

/**
 * 用户组管理
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class GroupAction extends BaseAction implements SessionAware {
	private static final Log logger = LogFactory.getLog(GroupAction.class);
	
	
	@SuppressWarnings("unchecked")
	private String groupName;
	private String groupDesc;
	private List<String> roleIDs = new ArrayList<String>();
	@SuppressWarnings("unchecked")
	private List<String> permissions = new ArrayList<String>();
	@SuppressWarnings("unchecked")
	private List roleList = new ArrayList();
	private GroupFacade groupFacade;
	private AjaxMessagesJson ajaxMessagesJson;
	@SuppressWarnings("unchecked")
	private List groupList;
	private String groupId;
	private List<OptionsString> roleValues = new ArrayList<OptionsString>();
	private UserSession userSession;
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
		//只能查看或修改自己创建的组
		this.setGroupList(this.getGroupFacade().findGroupsAll(this.getUserSession().getUserId())); 
		return "list";
	}
	
	//进入添加角色页面
	public String add() {
		this.setAction("addsave");
		this.setRoleValuesInit();
		return "groupSet";
	}
	
	//用户组添加
	@SuppressWarnings("unchecked")
	public String addsave() {
		if (StringUtils.isBlank(this.getGroupName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", "用户组名称不能为空!");
			return RESULT_AJAXJSON;
		}
		
		try {
			groupName=URLDecoder.decode(groupName, "UTF-8");
			groupDesc=URLDecoder.decode(groupDesc, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		if (this.getRoleIDs().isEmpty()) {
			this.getRoleIDs().add("0");//这边可能会有问题,等下再测
		}
		
		search.put("groupName", groupName);
		search.put("groupDesc", groupDesc);
		search.put("roles", this.getRoleIDs());
		search.put("userId", this.getUserSession().getUserId());
		try {
			this.getGroupFacade().insertGroup(search);
			this.getAjaxMessagesJson().setMessage("0", this.getText("添加用户组成功!"));
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_ROLE_ADDFAILED", this.getText("添加用户组出错!"));
		}
		return RESULT_AJAXJSON;
	}
	
	/**
	 * 进入修改用户组页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String edit() {
		this.setAction("editsave");
		List groupList = this.getGroupFacade().findGroupByGroupId(this.getGroupId());
		
		for(int i=0;i<groupList.size();i++){
			//封装这个用户组的所有的角色
			Map roleMap=(Map)groupList.get(i);
			roleList.add(""+roleMap.get("RoleId"));
		}
		this.setGroupId(this.getGroupId());
		this.setGroupName((String)((Map)groupList.get(0)).get("groupname"));
		this.setGroupDesc((String)((Map)groupList.get(0)).get("groupDesc"));
		//这个用户组原来所拥有的角色
		this.setRoleIDs(roleList);
		this.setRoleValuesInit();//所有的权限值
		return "groupSet";
	}
	
	/**
	 * 角色(权限)修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editsave() {
		if (StringUtils.isBlank(this.getGroupName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", "用户组名称不能为空!");
			return RESULT_AJAXJSON;
		}
		try {
			groupName=URLDecoder.decode(groupName, "UTF-8");
			groupDesc=URLDecoder.decode(groupDesc, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		search.put("groupId", groupId);
		search.put("groupName", groupName);
		search.put("groupDesc", groupDesc);
		search.put("roles", this.getRoleIDs());

		try {
			this.getGroupFacade().updateGroup(search);
			this.getAjaxMessagesJson().setMessage("0", "用户组修改成功");
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("Group_FAILED", "修改用户组出错");
		}
		return RESULT_AJAXJSON;
	}
	
	
	//删除角色
	@SuppressWarnings("unchecked")
	public String del() {
		
		try {
			search.clear();
			search.put("groupId", groupId); 
			this.getGroupFacade().deleteGroupByGroupId(search);
			this.getAjaxMessagesJson().setMessage("0", "用户组删除成功!");
		} catch (Exception ex1) {
			this.getAjaxMessagesJson().setMessage("GROUP_DELFAILED","用户组删除失败!");
		}
		return RESULT_AJAXJSON;
	}

	@SuppressWarnings("unchecked")
	private void setRoleValuesInit() {
		List roleList = this.getGroupFacade().findRolesAll(this.getUserSession().getUserId());
		for (int i = 0; i < roleList.size(); i++) {
			Map p = (Map) roleList.get(i);
			roleValues.add(new OptionsString(""+p.get("roleId"), (String)p.get("roleName")));
		}
	}

	@SuppressWarnings("unchecked")
	public List getGroupList() {
		return groupList;
	}

	@SuppressWarnings("unchecked")
	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}
	
	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	@SuppressWarnings("unchecked")
	public List getRoleList() {
		return roleList;
	}

	@SuppressWarnings("unchecked")
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List<OptionsString> getRoleValues() {
		return roleValues;
	}

	public void setRoleValues(List<OptionsString> roleValues) {
		this.roleValues = roleValues;
	}

	public GroupFacade getGroupFacade() {
		return groupFacade;
	}

	public void setGroupFacade(GroupFacade groupFacade) {
		this.groupFacade = groupFacade;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public List<String> getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(List<String> roleIDs) {
		this.roleIDs = roleIDs;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	
	
}
