package com.qzgf.application.systemManage.login.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ModelDriven;
import com.qzgf.Def;
import com.qzgf.application.systemManage.firstpage.domain.FirstPageFacade;
import com.qzgf.application.systemManage.login.domain.LoginFacade;
import com.qzgf.application.systemManage.manager.dto.UserInfo;

@SuppressWarnings("serial")
public class LoginAction implements Action, ModelDriven {
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(LoginAction.class);
	private LoginFacade loginFacade;
	private UserInfo userInfo = new UserInfo();

	private String actionType;
	@SuppressWarnings("unchecked")
	private List gobackFirstList;
	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private FirstPageFacade firstPageFacade;
	@SuppressWarnings("unchecked")
	private List firstPageList;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		if (!"firstPageManage".equals(actionType)) {
			userInfo = loginFacade.checkLogin(userInfo);
			if (null != userInfo) {
				ActionContext.getContext().getSession().put(
						Def.ROLE_LIST_SESSION, userInfo.getRoles());

				userInfo.setAgent(ServletActionContext.getRequest().getHeader(
						"User-Agent"));
				userInfo.setIp(ServletActionContext.getRequest()
						.getRemoteAddr());
				
				
				//设置当前用户的session信息,便于前台调用<ww:property value="#session['UserInfo'].staffName"/>
				ActionContext.getContext().getSession().put(
						Def.LOGIN_SESSION_NAME, userInfo);
				//设置当前用户的配置信息的取得
				search.put("staffId", userInfo.getStaffId());
				List ls=loginFacade.getUserConfig(search);
				if(ls.size()>0)
				{	
					search=(HashMap)ls.get(0);
					ActionContext.getContext().getSession().put("left_margin", search.get("left_margin").toString());
					ActionContext.getContext().getSession().put("top_margin", search.get("top_margin").toString());
					ActionContext.getContext().getSession().put("send_code", search.get("send_code").toString());
					ActionContext.getContext().getSession().put("sendname", search.get("sendname").toString());
				}
				return SUCCESS;
			}
		} else {
			firstPageList = this.loginFacade.findFirstPageList();
			search.put("contentList", firstPageList);
			return "firstMan";
		}

		return "loginfail";
	}

	public Object getModel() {
		return userInfo;
	}

	public LoginFacade getLoginFacade() {
		return loginFacade;
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public List getGobackFirstList() {
		return gobackFirstList;
	}

	public void setGobackFirstList(List gobackFirstList) {
		this.gobackFirstList = gobackFirstList;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public FirstPageFacade getFirstPageFacade() {
		return firstPageFacade;
	}

	public void setFirstPageFacade(FirstPageFacade firstPageFacade) {
		this.firstPageFacade = firstPageFacade;
	}

	public List getFirstPageList() {
		return firstPageList;
	}

	public void setFirstPageList(List firstPageList) {
		this.firstPageList = firstPageList;
	}

}
