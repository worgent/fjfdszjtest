package com.qzgf.application.selfConfig.mapcard.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.mapcard.domain.MapcardFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.ListParseToJson;
import com.qzgf.utils.ListParseToXML;
import com.qzgf.utils.node.NodesUtil;

/**
 * 地图名片
 * 
 * @author lsr
 * 
 */
@SuppressWarnings("serial")
public class MapcardAction extends BaseAction {
	Log log = LogFactory.getLog(MapcardAction.class);

	private MapcardFacade mapcardFacade;

	// private HashMap search = new HashMap(); //共用查询信息
	private HashMap product = new HashMap(); // 商品信息
	private HashMap mapcard = new HashMap(); // 商家信息
	private List productList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回
	private String menustr; // 生成动态菜单

	private List productListex; // 特色商品信息(地图显示)
	private String json500m;// json供前台显法调用,地图泡泡的显示

	// 图片上传2009-11-13
	private File upload;
	/*
	 * 其实，<s:file/>标志不仅仅是绑定到myFile，还有myFileContentType （上传文件的MIME类型） 和
	 * myFileFileName（上传文件的文件名，该文件名不包括文件的路径）。
	 */
	private String uploadFileName;
	private String uploadContentType;

	// 读取用户的配置信息
	private HashMap userInfo;

	// 入口函数
	@SuppressWarnings("unchecked")
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
	public String index() {
		// 实力,口碑,人气与商家信息相关
		Pages pages = new Pages();
		// 生成前台菜单的结构
		menustr = getmenu("0");
		pages.setPage(this.getPage());// 默认第一页
		pages.setPerPageNum(10); // 设置每页大小
		pages.setFileName("mapcard.do?action=index");
		this.setPageList(this.mapcardFacade.findMapcardPage(search, pages));
		return "index";
	}

	// 2.商家信息（得到单行记录）
	@SuppressWarnings("unchecked")
	public String detail() {
		this.setMapcard((HashMap) this.mapcardFacade.findMapcard(search).get(0));// 商家信息
		
		//2009-11-15前6位商品信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		search.put("START", 0);
		search.put("END", 6);
		this.setProductList(this.mapcardFacade.findSpecialintroex(search)); // 商品信息列表
		
		
		// 地图泡泡
		search.clear();
		search.put("plng", mapcard.get("LNG").toString());
		search.put("plat", mapcard.get("LAT").toString());
		search.put("END", 10);
		search.put("START", 0);
		this.setProductListex(mapcardFacade.findSpecialintro500m(search)); // 周边商品信息
		json500m = ListParseToJson.parseToJson(this.productListex);
		return "detail";
	}

	// 3.特色商品信息（得到商品信息）(商品详细信息)
	public String detailitem() {
		this.setProduct((HashMap) this.mapcardFacade.findSpecialintro(search).get(0)); // 商品信息
		String mid = search.get("ppid").toString();
		search.clear();
		search.put("pid", mid);
		this.setMapcard((HashMap) this.mapcardFacade.findMapcard(search)
						.get(0)); // 商家信息
		return "detailitem";
	}

	// 后台 struts.ui.theme=simple
	// 1.地图名片列表(得到列表)
	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());// 默认第一页
		pages.setPerPageNum(10); // 设置每页大小
		pages.setFileName("mapcard.do?action=list");
		search.put("orderby", "1");
		//用户过滤,根据采编人员
		search.put("puserid", userInfo.get("USERID").toString());
		try{
		this.setPageList(this.mapcardFacade.findMapcardPage(search, pages));
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return "list";
	}

	// 2.编辑时取后台数据信息（得到单行记录）
	public String edit() {
		//用户过滤,根据采编人员2009-11-24
		search.put("puserid", userInfo.get("USERID").toString());
		mapcard = (HashMap) this.mapcardFacade.findMapcard(search).get(0); // 商家信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		this.setProductList(this.mapcardFacade.findSpecialintro(search)); // 商品信息列表

		this.setAction("update");
		return "edit";
	}

	// 3.新增时增加的页面信息（得到单行记录）
	public String add() {
		this.setAction("insert");
		return "edit";
	}

	// 4.在新增加的页面保存时的处理（得到单行记录）
	public String insert() {
		// 保存图片
		String result = this.mapcardFacade.saveimg(this.getUpload(), this
				.getUploadFileName());
		// 生成的文件名,保存到数据库
		search.put("pmerchanticon", result);
		//用户过滤,根据采编人员2009-11-24
		search.put("puserid", userInfo.get("USERID").toString());
		int i = this.mapcardFacade.insertMapcardSpecial(search, product);
		if (i > 0) {
			this.addActionMessage(this.getText("新增成功!"));
		} else {
			this.addActionError(this.getText("新增失败!"));
		}
		// 增加数据
		// i=i+this.mapcardFacade.insertSpecialintro(search);

		// 转到列表页面
		search.clear();
		return list();
	}

	// 5.数据更新的处理（得到单行记录）
	public String update() {

		File fl = this.getUpload();
		String flname = this.getUploadFileName();
		// 保存图片
		try {

			String result = this.mapcardFacade.saveimg(this.getUpload(), this
					.getUploadFileName());// ()
			if (result.equals("")) {
				result = search.get("pmerchanticonex").toString();
			}
			// 生成的文件名,保存到数据库
			search.put("pmerchanticon", result);
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		
		int i = this.mapcardFacade.updateMapcardSpecialintroById(search,product);
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
	public String view() {
		this.setMapcard((HashMap) this.mapcardFacade.findMapcard(search).get(0)); // 商家信息
		String mid = search.get("pid").toString();
		search.clear();
		search.put("pmerchantid", mid);
		this.setProductList(this.mapcardFacade.findSpecialintro(search)); // 商品信息列表
		return "view";
	}

	// 7.删除商家信息（得到商品信息）(商品详细信息)
	public String delete() {
		// 主表
		int i = this.mapcardFacade.deleteMapcardById(search);
		// 子表
		HashMap hs = new HashMap();
		hs.put("pmerchantid", search.get("pid").toString());
		i = i + this.mapcardFacade.deleteSpecialintroById(hs);

		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";

	}

	// 生成菜单
	private String getmenu(String id) {
		StringBuffer sb = new StringBuffer();
		HashMap hs = new HashMap();
		List ls = this.mapcardFacade.getmenu(hs);// 传入空值得到菜单的结构
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
								+ "<a href='/selfconfig/mapcard.do?action=index&search.pmerchanttype="
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
						.append("<li>><a href='/selfconfig/mapcard.do?action=index&search.pmerchanttype="
								+ hs.get("id")
								+ "'>"
								+ hs.get("title")
								+ "</a></li>");
			}
		}
		String strmenu = "";
		strmenu = sb.toString();
		if (strmenu.length() > 16) {
			strmenu = strmenu.substring(16, strmenu.length()).concat(
					"</ul></div></li>");
		}
		return strmenu;
	}

	// 商家类别的增加,删除,修改,查,等动作
	public String mapcardsort() {

		if (this.getAction().equals("")) {

		} else if (this.getAction().equals("insert")) {
			int i = this.mapcardFacade.insertmenu(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			// return this.SUCCESS;
		} else if (this.getAction().equals("delete")) {
			int i = this.mapcardFacade.deletemenu(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if (this.getAction().equals("update")) {
			int i = this.mapcardFacade.updatemenu(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}else if (this.getAction().equals("combomenu")) {
			
			List ls = this.mapcardFacade.findmenu(search);
			xml =ListParseToXML.parseToXML(ls);
			return "xml";
		}
		return "index";
	}

	// 生成类别维护树
	public String getTree() {
		HashMap hs = new HashMap();
		List ls = this.mapcardFacade.getmenu(hs);// 传入空值得到菜单的结构
		NodesUtil nu = new NodesUtil();
		xml = nu.getTreeNodes(ls);
		return "xml";
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the mapcardFacade
	 */
	public MapcardFacade getMapcardFacade() {
		return mapcardFacade;
	}

	/**
	 * @param mapcardFacade
	 *            the mapcardFacade to set
	 */
	public void setMapcardFacade(MapcardFacade mapcardFacade) {
		this.mapcardFacade = mapcardFacade;
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
	 * @return the mapcard
	 */
	public HashMap getMapcard() {
		return mapcard;
	}

	/**
	 * @param mapcard
	 *            the mapcard to set
	 */
	public void setMapcard(HashMap mapcard) {
		this.mapcard = mapcard;
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
	 * @return the menustr
	 */
	public String getMenustr() {
		return menustr;
	}

	/**
	 * @param menustr
	 *            the menustr to set
	 */
	public void setMenustr(String menustr) {
		this.menustr = menustr;
	}

	/**
	 * @return the productListex
	 */
	public List getProductListex() {
		return productListex;
	}

	/**
	 * @param productListex
	 *            the productListex to set
	 */
	public void setProductListex(List productListex) {
		this.productListex = productListex;
	}

	/**
	 * @return the json500m
	 */
	public String getJson500m() {
		return json500m;
	}

	/**
	 * @param json500m
	 *            the json500m to set
	 */
	public void setJson500m(String json500m) {
		this.json500m = json500m;
	}

	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * @param uploadContentType
	 *            the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
