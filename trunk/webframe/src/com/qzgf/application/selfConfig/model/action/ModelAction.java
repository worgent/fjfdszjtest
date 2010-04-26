/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :webframe
* Package        :com.qzgf.utils.sms
* File	         :Test.java
* Written by     :fjfdszj
* Created Date   :Mar 20, 2010
* Purpose        :优惠分享

======================================

* Modifyer by    :fjfdszj
* Update Date    :Mar 20, 2010
* Purpose        :优惠分享

*/
package com.qzgf.application.selfConfig.model.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.shareFav.domain.ShareFavFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.ListParseToXML;


/**
 * Purpose      : 优惠分享
 *
 * @author fjfdszj
 * @see     ModelAction.java
 *
 */
@SuppressWarnings("serial")
public class ModelAction extends BaseAction {
	Log log = LogFactory.getLog(ModelAction.class);

	private ShareFavFacade ShareFavFacade;		//优惠实现类接口

	private HashMap product = new HashMap();   // 商品信息
	private HashMap ShareFav = new HashMap();  // 优惠信息
	private List productList; 					// 特色商品信息
	private PageList pageList; 				// 封装分页信息
	private String xml; 						// 页面返回

	// 读取用户的配置信息
	private HashMap userInfo;

	// 入口函数
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String execute() {
		try {
			// 从session中读取用户信息
			 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
				//当匿名登录时
				if(userInfo==null)
				{
					HashMap tmp=new HashMap();
					tmp.put("USERID","0");
					userInfo=tmp;
				}
			 return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	// 前台
	// 1.地图名片列表(得到列表)
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String index() {
		// 实力,口碑,人气与商家信息相关
		Pages pages = new Pages();
		// 生成前台菜单的结构
		
		pages.setPage(this.getPage());// 默认第一页
		pages.setPerPageNum(10); // 设置每页大小
		pages.setFileName("ShareFav.do?action=index");
		this.setPageList(this.ShareFavFacade.findShareFavPage(search, pages));
		return "index";
	}

	// 2.商家信息（得到单行记录）
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String detail() {
		this.setShareFav((HashMap) this.ShareFavFacade.findShareFav(search).get(0));// 商家信息
		
		//2009-11-15前6位商品信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		search.put("START", 0);
		search.put("END", 6);
		//数据存储中
		
		
		// 地图泡泡
		search.clear();
		search.put("plng", ShareFav.get("LNG").toString());
		search.put("plat", ShareFav.get("LAT").toString());
		search.put("END", 10);
		search.put("START", 0);
		//数据存储
		
		return "detail";
	}

	// 3.特色商品信息（得到商品信息）(商品详细信息)
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String detailitem() {
		
		String mid = search.get("ppid").toString();
		search.clear();
		search.put("pid", mid);
		this.setShareFav((HashMap) this.ShareFavFacade.findShareFav(search)
						.get(0)); // 商家信息
		return "detailitem";
	}

	// 后台 struts.ui.theme=simple
	// 1.地图名片列表(得到列表)
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());// 默认第一页
		pages.setPerPageNum(10); // 设置每页大小
		pages.setFileName("ShareFav.do?action=list");
		search.put("orderby", "1");
		//用户过滤,根据采编人员
		search.put("puserid", userInfo.get("USERID").toString());
		this.setPageList(this.ShareFavFacade.findShareFavPage(search, pages));
		return "list";
	}

	// 2.编辑时取后台数据信息（得到单行记录）
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String edit() {
		//用户过滤,根据采编人员2009-11-24
		search.put("puserid", userInfo.get("USERID").toString());
		ShareFav = (HashMap) this.ShareFavFacade.findShareFav(search).get(0); // 商家信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		

		this.setAction("update");
		return "edit";
	}

	// 3.新增时增加的页面信息（得到单行记录）
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String add() {
		this.setAction("insert");
		return "edit";
	}

	// 4.在新增加的页面保存时的处理（得到单行记录）
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String insert() {
		// 保存图片

		// 生成的文件名,保存到数据库
		//search.put("pmerchanticon", result);
		//用户过滤,根据采编人员2009-11-24
		search.put("puserid", userInfo.get("USERID").toString());
		int i=0;
		if (i > 0) {
			this.addActionMessage(this.getText("新增成功!"));
		} else {
			this.addActionError(this.getText("新增失败!"));
		}
		// 增加数据
		// i=i+this.ShareFavFacade.insertSpecialintro(search);

		// 转到列表页面
		search.clear();
		return list();
	}

	// 5.数据更新的处理（得到单行记录）
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String update() {

        int i=0;
		if (i > 0) {
			this.addActionMessage(this.getText("更新成功!"));
		} else {
			this.addActionError(this.getText("更新失败!"));
		}
		// 转到列表页面
		search.clear();
		return list();
	}

	// 6.查看商家信息（得到单行记录）
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String view() {
		this.setShareFav((HashMap) this.ShareFavFacade.findShareFav(search).get(0)); // 商家信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		return "view";
	}

	// 7.删除商家信息（得到商品信息）(商品详细信息)
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String delete() {
		// 主表
		int i = this.ShareFavFacade.deleteShareFavById(search);
		// 子表
		HashMap hs = new HashMap();
		hs.put("pmerchantid", search.get("pid").toString());

		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";

	}

	// 生成菜单
	/**
	 * Purpose      : 说明
	 * @param id
	 * @return 
	 */
	private String getmenu(String id) {
		StringBuffer sb = new StringBuffer();
		HashMap hs = new HashMap();
		/*
		List ls = this.ShareFavFacade.getmenu(hs);// 传入空值得到菜单的结构
		for (int i = 0; i < ls.size(); i++) {
			hs = (HashMap) ls.get(i);
			if (hs.get("superId").toString().equals("0")) {
				// 尾部
				sb.append("</ul></div></li>");
				// 头部
				sb
						.append("<li id='navSel"
								+ i
								+ "' onmouseover='showType("
								+ i
								+ ")' onmouseout='hidType("
								+ i
								+ ")'>"
								+ "<a href='/selfconfig/ShareFav.do?action=index&search.pmerchanttype="
								+ hs.get("id")
								+ "' target='_self'>"
								+ hs.get("title")
								+ "</a>"
								+ "<span><em>"
								+ i
								+ "</em></span>"
								+ "<div id='Nav"
								+ i
								+ "' class='Nav' style='display:none;'><ul class='subNav'>");
			} else {// 主体
				sb
						.append("<li>><a href='/selfconfig/ShareFav.do?action=index&search.pmerchanttype="
								+ hs.get("id")
								+ "'>"
								+ hs.get("title")
								+ "</a></li>");
			}
		}
		*/
		String strmenu = "";
		strmenu = sb.toString();
		if (strmenu.length() > 16) {
			strmenu = strmenu.substring(16, strmenu.length()).concat(
					"</ul></div></li>");
		}
		return strmenu;
	}

	// 商家类别的增加,删除,修改,查,等动作
	/**
	 * Purpose      : 说明
	 * @return 
	 */
	public String ShareFavsort() {
        int i=0;
		if (this.getAction().equals("")) {

		} else if (this.getAction().equals("insert")) {
			
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			// return this.SUCCESS;
		} else if (this.getAction().equals("delete")) {
			
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if (this.getAction().equals("update")) {
			//int i = this.ShareFavFacade.updatemenu(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}else if (this.getAction().equals("combomenu")) {
			List ls=null;
			//List ls = this.ShareFavFacade.findmenu(search);
			xml =ListParseToXML.parseToXML(ls);
			return "xml";
		}
		return "index";
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the ShareFavFacade
	 */
	public ShareFavFacade getShareFavFacade() {
		return ShareFavFacade;
	}

	/**
	 * @param ShareFavFacade
	 *            the ShareFavFacade to set
	 */
	public void setShareFavFacade(ShareFavFacade ShareFavFacade) {
		this.ShareFavFacade = ShareFavFacade;
	}

	/**
	 * @return the productList
	 */
	public List getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the productList to set
	 */
	public void setProductList(List productList) {
		this.productList = productList;
	}

	/**
	 * @return the ShareFav
	 */
	public HashMap getShareFav() {
		return ShareFav;
	}

	/**
	 * @param ShareFav
	 *            the ShareFav to set
	 */
	public void setShareFav(HashMap ShareFav) {
		this.ShareFav = ShareFav;
	}

	/**
	 * @return the product
	 */
	public HashMap getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(HashMap product) {
		this.product = product;
	}

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * @param xml
	 *            the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}
}
