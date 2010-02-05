package com.qzgf.application.systemManage.manager.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork.ActionContext;
import com.qzgf.PaginactionAction;
import com.qzgf.application.systemManage.manager.domain.ManagerFacade;
import com.qzgf.application.systemManage.manager.dto.UserInfo;
import com.qzgf.security.MD5;

@SuppressWarnings("serial")
public class ManagerAction extends PaginactionAction {
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(ManagerAction.class);
	private ManagerFacade managerFacade;

	private UserInfo userInfo = new UserInfo();

	@SuppressWarnings("unchecked")
	private List userList;
	private String actionType;
	private String action;
	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private String serviceName;

	@SuppressWarnings("unchecked")
	private List roleList;// 角色列表
	private String[] roleId;// 要分析角色的ID集

	@SuppressWarnings("unchecked")
	private List cityList;// 区域列表
	private String[] cityId;// 要分配的区域ID集

	private String oldPassword;// 旧密码
	private String password; // 新密码

	private String xml;
	@SuppressWarnings("unused")
	private String json;

	// 处理机构信息的过滤
	private String jgid;
	private String jg_mc;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		@SuppressWarnings("unused")
		UserInfo userInfo2 = (UserInfo) ActionContext.getContext().getSession()
				.get("UserInfo");
		search.put("pdeptid", userInfo2.getDeptId());
		search.put("commstaffId", userInfo2.getStaffId());

		jgid = userInfo2.getDeptId();
		jg_mc = userInfo2.getDeptName();
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0) {// 判断查询状态
				search.put("userInfo", userInfo);
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(managerFacade.findManagerInfoCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			userList = managerFacade.findManagerInfo(search);
			return "search";
		} else if ("new".equals(actionType)) {
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
			userInfo.setPassword(new MD5().StrToMd5(userInfo.getPassword()));
			int i = managerFacade.insertManagerInfo(userInfo);
			if (i > 0) {
				super.addActionMessage("添加管理员信息,操作成功");
				addActionScript("parent.document.ifrm_SystemManager.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("添加管理员信息,操作失败");
				return ERROR;
			}
		} else if ("edit".equals(actionType)) {
			userInfo = managerFacade.findManagerById(userInfo);
			action = "update";

			return "edit";
		} else if ("update".equals(actionType)) {
			if (userInfo.getPassword() != null
					&& !"".equals(userInfo.getPassword())) {
				userInfo
						.setPassword(new MD5().StrToMd5(userInfo.getPassword()));
			}
			int i = managerFacade.updateManagerInfo(userInfo);
			if (i > 0) {
				super.addActionMessage("修改管理员信息,操作成功");
				addActionScript("parent.document.ifrm_SystemManager.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("修改管理员信息,操作失败");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			userInfo.setStaffState("0");
			int i = managerFacade.deleteManagerInfo(userInfo);
			serviceName = "删除人员信息";

			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("role".equals(actionType)) {
			userInfo.setTemp(userInfo2.getStaffId());
			roleList = managerFacade.findRole(userInfo);
			actionType = "roleAllot";

			return "role";
		} else if ("roleAllot".equals(actionType)) {
			@SuppressWarnings("unused")
			int i = managerFacade.insertManagerRoleAllot(roleId, userInfo
					.getStaffId());
			this.addActionMessage("给工号为" + userInfo.getStaffNo() + "的"
					+ userInfo.getStaffName() + "人员分配角色操作成功。");
			return SUCCESS;

		} else if ("city".equals(actionType)) {
			cityList = managerFacade.findCity(userInfo);
			actionType = "cityAllot";

			return "city";
		} else if ("cityAllot".equals(actionType)) {
			@SuppressWarnings("unused")
			int i = managerFacade.insertManagerCityAllot(cityId, userInfo
					.getStaffId());
			this.addActionMessage("给工号为" + userInfo.getStaffNo() + "的"
					+ userInfo.getStaffName() + "人员分配区域操作成功。");
			return SUCCESS;

		} else if ("ifExist".equals(actionType)) {
			search.put("userInfo", userInfo);
			int ifExist = managerFacade.findManagerInfoCount(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<isExist>");
			sb.append("	<value>").append("" + ifExist).append("</value>");
			sb.append("</isExist>");
			xml = sb.toString();
			return "xml";
		}
		return SUCCESS;
	}
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	public String modifyPassword() throws Exception {
		// 取出会话中的登陆者信息
		UserInfo temp = (UserInfo) ActionContext.getContext().getSession().get(
				"UserInfo");
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<Result>");

		if (!temp.getPassword().toUpperCase().equals(
				new MD5().StrToMd5(oldPassword).toUpperCase())) {
			sb.append("<errCode>0</errCode>");
			sb.append("<errMsg>对不起,您输入的旧密码不正确。</errMsg>");
			sb.append("</Result>");
			xml = sb.toString();
			return SUCCESS;
		}

		userInfo.setPassword(new MD5().StrToMd5(password).toUpperCase());
		userInfo.setStaffId(temp.getStaffId());
		int i = managerFacade.updateManagerInfo(userInfo);

		// 将修改完后的密码存入Session
		temp.setPassword(userInfo.getPassword());
		ActionContext.getContext().getSession().put("UserInfo", temp);

		if (i == 0) {
			sb.append("<errCode>0</errCode>");
			sb.append("<errMsg>对不起,密码修改失败。</errMsg>");
			sb.append("</Result>");
			xml = sb.toString();
			return SUCCESS;
		}

		
		sb.append("<errCode>1</errCode>");
		sb.append("<errMsg>密码修改成功。</errMsg>");
		sb.append("</Result>");
		xml = sb.toString();
		return SUCCESS;
	}
	/**
	 * 用户信息配置
	 * @return
	 * @throws Exception
	 * 2009-10-28
	 */
	public String userConfig() throws Exception {
		//获取当前用户信息
		UserInfo temp = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<Result>");	
		//用户信息,注册保存,同时保存与角色的关连
		search.put("staffId", temp.getStaffId());
		int flag = managerFacade.prouserConfig(search);
		if(flag>0){
			sb.append("<errCode>1</errCode>");
			sb.append("<errMsg>用户信息配置成功。</errMsg>");
		}else{
			sb.append("<errCode>2</errCode>");
			sb.append("<errMsg>用户信息配置异常。</errMsg>");	
		}
		sb.append("</Result>");
		xml = sb.toString();
		return SUCCESS;
	}
	
	/**
	 * 用户注册
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<Result>");	
		//用户名是否已经被注册
		search.put("userInfo", userInfo);
		int flag = managerFacade.isexitManagerInfoCount(search);
		if(flag>0)
		{
			sb.append("<errCode>0</errCode>");
			sb.append("<errMsg>对不起,该用户已经注册。</errMsg>");
			sb.append("</Result>");
			xml = sb.toString();
			return SUCCESS;
		}
		//增加用户信息
		userInfo.setDeptId("110");//普通注册用户(专用)(与部门档案相关连)
		userInfo.setIsUse("1");   //默认启用
		userInfo.setPassword(new MD5().StrToMd5(userInfo.getPassword()).toUpperCase());//加密密码保存 
		userInfo.setCityId(59);   //所属地市,由上次系统继承
		
		//用户信息,注册保存,同时保存与角色的关连
		flag = managerFacade.regManagerInfo(userInfo);
		if(flag>0){
			sb.append("<errCode>1</errCode>");
			sb.append("<errMsg>用户注册成功。</errMsg>");
			sb.append("</Result>");
		}else{
			sb.append("<errCode>2</errCode>");
			sb.append("<errMsg>用户注册出现异常。</errMsg>");
			sb.append("</Result>");			
		}
		
		xml = sb.toString();
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	// public String modifyPassword()throws Exception{
	// // 取出会话中的登陆者信息
	// UserInfo temp =
	// (UserInfo)ActionContext.getContext().getSession().get("UserInfo");
	//		
	// if (!temp.getPassword().toUpperCase().equals(new
	// MD5().StrToMd5(oldPassword).toUpperCase())){
	// addActionMessage("对不起,您输入的旧密码不正确。");
	// return ERROR;
	// }
	//			
	// userInfo.setPassword(new MD5().StrToMd5(password).toUpperCase());
	// userInfo.setStaffId(temp.getStaffId());
	// int i = managerFacade.updateManagerInfo(userInfo);
	//		
	// //将修改完后的密码存入Session
	// temp.setPassword(userInfo.getPassword());
	// ActionContext.getContext().getSession().put("UserInfo",temp);
	//		
	// if (i ==0){
	// json = "{errors:[{id:'password',msg:'密码错误!'}]}";
	// }
	// return ERROR;
	// }
	public ManagerFacade getManagerFacade() {
		return managerFacade;
	}

	public void setManagerFacade(ManagerFacade managerFacade) {
		this.managerFacade = managerFacade;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@SuppressWarnings("unchecked")
	public List getUserList() {
		return userList;
	}

	@SuppressWarnings("unchecked")
	public void setUserList(List userList) {
		this.userList = userList;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@SuppressWarnings("unchecked")
	public List getRoleList() {
		return roleList;
	}

	@SuppressWarnings("unchecked")
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public String[] getRoleId() {
		return roleId;
	}

	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@SuppressWarnings("unchecked")
	public List getCityList() {
		return cityList;
	}

	@SuppressWarnings("unchecked")
	public void setCityList(List cityList) {
		this.cityList = cityList;
	}

	public String[] getCityId() {
		return cityId;
	}

	public void setCityId(String[] cityId) {
		this.cityId = cityId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}

	/**
	 * @param jgid
	 *            the jgid to set
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}

	/**
	 * @return the jg_mc
	 */
	public String getJg_mc() {
		return jg_mc;
	}

	/**
	 * @param jg_mc
	 *            the jg_mc to set
	 */
	public void setJg_mc(String jg_mc) {
		this.jg_mc = jg_mc;
	}

}
