package com.qzgf.application.archives.address.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.address.domain.AddressFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 网上下单
 * 
 * 
 */
@SuppressWarnings("serial")
public class AddressAction extends BaseAction {
	Log log = LogFactory.getLog(AddressAction.class);

	private AddressFacade addressFacade;
	private HashMap address = new HashMap(); // 商品信息
	private List addressList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回


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
		else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("address.do?action=listdetail");
			setPageList(addressFacade.findAddressPage(search, pages));
			return "listdetail";
		}else if ("frame".equals(super.getAction())) {
			//框架信息列表
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("address.do?action=frame");
			setPageList(addressFacade.findAddressPage(search, pages));
			return "frame";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			this.setAction("insert");
			return "edit";
		} else if ("insert".equals(super.getAction())) {
			//默认未审核
			search.put("pbill_type", 0);
			int i = addressFacade.insertAddress(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
		
			address = (HashMap) addressFacade.findAddress(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = addressFacade.updateAddressById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
		} else if ("view".equals(super.getAction())) {
			address=((HashMap) addressFacade.findAddress(search).get(0)); 
			return "view";
		}else if ("delete".equals(super.getAction())) {
			// 主表
			int i = addressFacade.deleteAddressById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
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
	 * @return the addressFacade
	 */
	public AddressFacade getAddressFacade() {
		return addressFacade;
	}



	/**
	 * @param addressFacade the addressFacade to set
	 */
	public void setAddressFacade(AddressFacade addressFacade) {
		this.addressFacade = addressFacade;
	}



	/**
	 * @return the address
	 */
	public HashMap getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(HashMap address) {
		this.address = address;
	}



	/**
	 * @return the addressList
	 */
	public List getAddressList() {
		return addressList;
	}



	/**
	 * @param addressList the addressList to set
	 */
	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}
}
