package com.qzgf.application.systemManage.branch.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.systemManage.branch.domain.BranchFacade;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.webutils.interceptor.SessionAware;

/**
 * 机构管理
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class BranchAction extends BaseAction implements SessionAware  {
	private static final Log logger = LogFactory.getLog(BranchAction.class);
	
	private BranchFacade branchFacade;
	private String branchName;
	private String perentBranchId;//父级机构ID
	private String id;
	private AjaxMessagesJson ajaxMessagesJson;
	private UserSession userSession;
	private String curMenu;
	private String branchOrder;
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}
	
	//罗列出所有的机构
	@SuppressWarnings("unchecked")
	public String list() {
		return "list";
	}
	
	@SuppressWarnings("unchecked")
	public String check(){
		int count =this.branchFacade.checkSubBranchById(id);
		if(count>0){
			this.getAjaxMessagesJson().setMessage("E_Branch_DELFAILED", "该结构还包括子机构，须先删除子机构!");
		}else if(count==0){
			this.getAjaxMessagesJson().setMessage("0", "可以删除");
		}
		return RESULT_AJAXJSON;
	}
	
	@SuppressWarnings("unchecked")
	public String index(){
		List menuList =this.branchFacade.findSubBranchById(perentBranchId); 
		StringBuffer menustr = new StringBuffer();
		menustr.append("<tree>");
		java.util.Iterator it = menuList.iterator();
		Map menuitem = new HashMap();
		
		while (it.hasNext()) {
			menuitem = (HashMap) it.next();
			menustr.append("<tree text=\""+menuitem.get("branchName")+"("+menuitem.get("branchorder")+")"+"\" src=\"branch.do?perentBranchId="+
					menuitem.get("branchId")+"&amp;date="+new Date()+"\" />");
		}
		menustr.append("</tree>");
		curMenu=menustr.toString();
		return "treemenu";
	}
	
	//进入添加页面
	public String add() {
		this.setAction("addsave");
		return INPUT;
	}
	
	//添加保存
	@SuppressWarnings("unchecked")
	public String addsave() {
		try {
			if (StringUtils.isBlank(this.getBranchName())) {
				this.getAjaxMessagesJson().setMessage("E_NULL", "机构名称不能为空!");
				return RESULT_AJAXJSON;
			}

			try {
				branchName = URLDecoder.decode(branchName, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			search.put("creator", "1111");
			search.put("branchName", branchName);
			search.put("perentBranchId",this.getPerentBranchId());
			//search.put("userId", this.getUserSession().getUserId());
			search.put("branchOrder", this.getBranchOrder());
			String result=this.getBranchFacade().insertBranch(search);
			this.getAjaxMessagesJson().setMessage("0", ""+result);
			return RESULT_AJAXJSON;
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_Branch_ADDFAILED", "添加机构出错!");
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
			this.getBranchFacade().deleteBranch(this.getId());
			this.getAjaxMessagesJson().setMessage("0", "机构删除成功");
			return RESULT_AJAXJSON;
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_Branch_DELFAILED", "机构删除出错!");
			return RESULT_AJAXJSON;
		}
	}

	public BranchFacade getBranchFacade() {
		return branchFacade;
	}

	public void setBranchFacade(BranchFacade branchFacade) {
		this.branchFacade = branchFacade;
	}

	
	public String getPerentBranchId() {
		return perentBranchId;
	}

	public void setPerentBranchId(String perentBranchId) {
		this.perentBranchId = perentBranchId;
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchOrder() {
		return branchOrder;
	}

	public void setBranchOrder(String branchOrder) {
		this.branchOrder = branchOrder;
	}

	
}
