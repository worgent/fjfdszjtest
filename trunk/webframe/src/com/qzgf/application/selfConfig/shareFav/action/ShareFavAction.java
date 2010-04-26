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
package com.qzgf.application.selfConfig.shareFav.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.model.action.ModelAction;
import com.qzgf.application.selfConfig.shareFav.domain.ShareFavFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.ListParseToJson;


/**
 * Purpose      : 优惠分享
 *
 * @author fjfdszj
 * @see     ModelAction.java
 *
 */
@SuppressWarnings("serial")
public class ShareFavAction extends BaseAction {
	Log log = LogFactory.getLog(ShareFavAction.class);

	private ShareFavFacade shareFavFacade;		//优惠实现类接口

	private HashMap product = new HashMap();   // 商品信息
	private HashMap shareFav = new HashMap();  // 优惠信息
	private List productList; 					// 特色商品信息
	private PageList pageList; 				// 封装分页信息
	private String xml; 						// 页面返回
	private String selectType;  //地图查询类型(0:地图周边查询 1:框选查询)

	// 读取用户的配置信息
	private HashMap userInfo;
	
	private String json500m=null;//json供前台显法调用,地图泡泡的显示

	/**
	 * Purpose      : 利用反射机制，所有程序的入口地址
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
	/**
	 * Purpose      :前台主页入口
	 * @return 
	 */
	public String index() {
		// 实力,口碑,人气与商家信息相关
		Pages pages = new Pages();
		// 生成前台菜单的结构
		
		pages.setPage(this.getPage());// 默认第一页
		pages.setPerPageNum(10); // 设置每页大小
		pages.setFileName("shareFav.do?action=index");
		this.setPageList(this.shareFavFacade.findShareFavPage(search, pages));
		return "index";
	}

	/**
	 * Purpose      : 明细信息
	 * @return 
	 */
	public String detail() {
		this.setShareFav((HashMap) this.shareFavFacade.findShareFav(search).get(0));// 商家信息
		
		//2009-11-15前6位商品信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		search.put("START", 0);
		search.put("END", 6);
		
		return "detail";
	}


	// 后台
	/**
	 * Purpose      : 后台列表
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
		this.setPageList(this.shareFavFacade.findShareFavPage(search, pages));
		return "list";
	}


	/**
	 * Purpose      : 编辑信息
	 * @return 
	 */
	public String edit() {
		//用户过滤,根据采编人员2009-11-24
		search.put("puserid", userInfo.get("USERID").toString());
		shareFav = (HashMap) this.shareFavFacade.findShareFav(search).get(0); // 商家信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		

		this.setAction("update");
		return "edit";
	}


	/**
	 * Purpose      : 新增初始化
	 * @return 
	 */
	public String add() {
		this.setAction("insert");
		return "edit";
	}


	/**
	 * Purpose      : 新增处理
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

		
		// 转到列表页面
		search.clear();
		return list();
	}

	
	/**
	 * Purpose      : 数据更新
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


	/**
	 * Purpose      : 记录查看
	 * @return 
	 */
	public String view() {
		this.setShareFav((HashMap) this.shareFavFacade.findShareFav(search).get(0)); // 商家信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		return "view";
	}


	/**
	 * Purpose      : 删除信息
	 * @return 
	 */
	public String delete() {
		// 主表
		int i = this.shareFavFacade.deleteShareFavById(search);
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

	
	//=======================================================2010-04-26======================================================
	/**
	 * Purpose      : 查询向导
	 * @return 
	 */
	public String searchUser() {
		search.put("selectType", selectType);
		if((selectType.equals("1"))&&!(null==selectType)){
			//当selectType为框选时
			/*
			search.put("swLat", sw.split(",")[0]);//切割西南经续度
			search.put("swLng", sw.split(",")[1]);
			search.put("neLat", ne.split(",")[0]);//切割东北经续度
			search.put("neLng", ne.split(",")[1]);
			*/
		}
		
		json500m=ListParseToJson.parseToJson(null);
		return "searchUser";
	}
	
	/**
	 * Purpose      : 分享他人
	 * @return 
	 */
	public String shareUser() {
		json500m=ListParseToJson.parseToJson(null);
		return "shareUser";
	}
	
	/**
	 * Purpose      : 短消息处理
	 * @return 
	 */
	public String smsPro() {
		json500m=ListParseToJson.parseToJson(null);
		return "smsPro";
	}
	
	//=========================================================================================================================
	
	
	
	
	
	


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
		return shareFavFacade;
	}

	/**
	 * @param ShareFavFacade
	 *            the ShareFavFacade to set
	 */
	public void setShareFavFacade(ShareFavFacade ShareFavFacade) {
		this.shareFavFacade = ShareFavFacade;
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
		return shareFav;
	}

	/**
	 * @param ShareFav
	 *            the ShareFav to set
	 */
	public void setShareFav(HashMap ShareFav) {
		this.shareFav = ShareFav;
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

	/**
	 * Purpose      : 取得500m范围数据
	 * @return the json500m
	 */
	public String getJson500m() {
		return json500m;
	}

	/**
	 * Purpose      : 设置500m范围数据
	 * @param json500m the json500m to set
	 */
	
	public void setJson500m(String json500m) {
		this.json500m = json500m;
	}
}
