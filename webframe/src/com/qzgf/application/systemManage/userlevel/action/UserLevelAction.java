package com.qzgf.application.systemManage.userlevel.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.userlevel.domain.UserLevelFacade;

/**
 * 用户等级业务层
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class UserLevelAction extends BaseAction {
	Log log = LogFactory.getLog(UserLevelAction.class);
	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	@SuppressWarnings({ "unchecked", "unused" })
	private List userLevelList=new ArrayList();
	private UserLevelFacade userLevelFacade;
	private String xml;

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}

	@SuppressWarnings("unchecked")
	public String index() {
		//列出所有的等级,这个地方不用分页
		userLevelList=this.userLevelFacade.findUserLevel(search);
		return "index";
	}
	
	public String add() {
		this.setAction("addnew");
		return INPUT;
	}
	
	public String addnew() {
		if (StringUtils.isBlank((String)search.get("LEVELNAME"))) {
			this.addActionMessage(this.getText("error.nullerror"));
			return INPUT;
		}

		try {
			@SuppressWarnings("unused")
			int num = this.userLevelFacade.insertUserLevel(search);
		} catch (Exception e) {
			log.error(e);
			this.addActionError(this.getText("error.admin.ul.add"));
			return INPUT;
		}

		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String del() {
		
		try {
			this.userLevelFacade.deleteUserLevelById(search);
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String edit() {
		
		try {
			search = (HashMap) (userLevelFacade.findUserLevel(search).get(0));
			this.setAction("editdo");
		} catch (Exception e) {
			log.error(e);
			this.addActionError(this.getText("error.admin.ul.add"));
			return INPUT;
		}
		return INPUT;
	}
	
	public String editdo() {
		
		try {
			boolean flag = this.userLevelFacade.updateUserLevelById(search);
		} catch (Exception e) {
			log.error(e);
			this.addActionError(this.getText("error.admin.ul.add"));
			return INPUT;
		}

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@SuppressWarnings("unchecked")
	public List getUserLevelList() {
		return userLevelList;
	}

	@SuppressWarnings("unchecked")
	public void setUserLevelList(List userLevelList) {
		this.userLevelList = userLevelList;
	}

	public UserLevelFacade getUserLevelFacade() {
		return userLevelFacade;
	}

	public void setUserLevelFacade(UserLevelFacade userLevelFacade) {
		this.userLevelFacade = userLevelFacade;
	}

	
}
