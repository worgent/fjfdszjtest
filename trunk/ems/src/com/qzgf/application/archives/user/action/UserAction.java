package com.qzgf.application.archives.user.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.user.domain.UserFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.webutils.ListParseToXML;

/**
 * 用户信息
 * 
 * 
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {
	Log log = LogFactory.getLog(UserAction.class);

	private UserFacade userFacade;
	private HashMap user = new HashMap(); // 商品信息
	private List userList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回

	
	//参数List
	private List clienttypeList;   //客户类型
	private List clientbalanceList;//结算方式

	// 入口函数
	public String execute() {

		// 从session中读取用户信息
		HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		// 当匿名登录时
		if (userInfo == null) {
			HashMap tmp = new HashMap();
			tmp.put("USERID", "0");
			userInfo = tmp;
		}
		
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			return "list";
		} 
		if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("user.do?action=listdetail");
			setPageList(userFacade.findUserPage(search, pages));
			return "listdetail";
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			// 用户过滤,根据采编人员2009-11-24
			// search.put("puserid", userInfo.get("USERID").toString());
			HashMap map=new HashMap();
			map.put("psort", "clienttype");//客户类型
			clienttypeList=userFacade.parameterValue(map);
			map.put("psort", "clientbalance");//支付方式
			clientbalanceList=userFacade.parameterValue(map);
			
			user = (HashMap) userFacade.findUser(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
		} else if ("view".equals(super.getAction())) {
			user=((HashMap) userFacade.findUser(search).get(0)); // 商家信息
			return "view";
		
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = userFacade.updateUserById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "edit";
		} else if ("updateauding".equals(super.getAction())) {
			search.put("peditor_date", "1");//启用编辑日期,即审核日期
			search.put("pbill_type", "1");//审核通过
			//用户信息更新,包括管理人员的审核,用户的修改
 			int i = userFacade.updateUserById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "edit";
		}else if ("perchangeuser".equals(super.getAction())) {
			//用户的信息修改前读取用户信息
			search=userInfo;//查看原用户信息,客户代码等
			return "changeuser";
		}else if ("changeuser".equals(super.getAction())) {
			//用户的信息修改
			int i = userFacade.updateUserById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionMessage(this.getText("用户更新失败!"));
			}
			//查看原用户信息,客户代码等
			String pid=search.get("pid").toString();
			search.clear();
			search.put("pid", pid);
			//1.放入session池中 2.返回页面
			search=(HashMap) userFacade.findUser(search).get(0);
			ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME, search);
			return "changeuser";
		}else if ("perchangepwd".equals(super.getAction())) {
			//用户的信息修改前读取用户信息
			search=userInfo;//取得用户id
			return "changepwd";
		}else if ("changepwd".equals(super.getAction())) {
			//修改密码,比较原密码是否正确
			search.put("curpasswd", userInfo.get("PASSWD").toString());
			int i=userFacade.changePwd(search);
			if (i ==-1) {
				this.addActionMessage(this.getText("原密码有误!"));
			}else if (i == 1) {
				this.addActionMessage(this.getText("修改密码成功!"));
			} else {
				this.addActionMessage(this.getText("修改出现异常!"));
			}
			return "changepwd";
		}  else if ("delete".equals(super.getAction())) {
			// 主表
			int i = userFacade.deleteUserById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
	}

	
	// 区域信息
	public String area() {
		//具体操作方式
		List ls=null;
		if ("provincevalue".equals(super.getAction())) {
			ls=userFacade.ajaxProvince(search); 
		} else if ("cityvalue".equals(super.getAction())) {
			ls=userFacade.ajaxCity(search); 
		} else if ("countyvalue".equals(super.getAction())) {
			ls=userFacade.ajaxCounty(search); 
		}
		xml=ListParseToXML.parseToXML(ls);
		return "xml";
	}
	/**
	 * @return the userFacade
	 */
	public UserFacade getUserFacade() {
		return userFacade;
	}

	/**
	 * @param userFacade the userFacade to set
	 */
	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	/**
	 * @return the user
	 */
	public HashMap getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(HashMap user) {
		this.user = user;
	}

	/**
	 * @return the userList
	 */
	public List getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List userList) {
		this.userList = userList;
	}

	/**
	 * @return the pageList
	 */
	public PageList getPageList() {
		return pageList;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}


	/**
	 * @return the clienttypeList
	 */
	public List getClienttypeList() {
		return clienttypeList;
	}


	/**
	 * @param clienttypeList the clienttypeList to set
	 */
	public void setClienttypeList(List clienttypeList) {
		this.clienttypeList = clienttypeList;
	}


	/**
	 * @return the clientbalanceList
	 */
	public List getClientbalanceList() {
		return clientbalanceList;
	}


	/**
	 * @param clientbalanceList the clientbalanceList to set
	 */
	public void setClientbalanceList(List clientbalanceList) {
		this.clientbalanceList = clientbalanceList;
	}
}
