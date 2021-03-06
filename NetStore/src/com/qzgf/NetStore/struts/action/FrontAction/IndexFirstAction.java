/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.qzgf.NetStore.struts.action.FrontAction;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;



import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.wlglException;
import com.qzgf.NetStore.service.IIndexFirstService;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import   java.net.*;  

/** 
 * MyEclipse Struts
 * Creation date: 09-08-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="failure" path="/JspForm/errors.jsp"
 * @struts.action-forward name="indexSuccess" path="/JspForm/FrontfuncModual/index.jsp"
 */
public class IndexFirstAction extends DispatchAction {
	private IIndexFirstService indexFirstService;

	
	
	public IIndexFirstService getIndexFirstService() {
		return indexFirstService;
	}
	
	public void setIndexFirstService(IIndexFirstService indexFirstService) {
		this.indexFirstService = indexFirstService;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward exec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws wlglException {
		
		//新品
		List newsGoodsList;
		try {
			newsGoodsList = this.indexFirstService.newsGoods();
			request.setAttribute("newsGoodsList", newsGoodsList);
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		//推荐
		List releseList=this.indexFirstService.releseGoods();
		request.setAttribute("releseList", releseList);
		
		//特价
		List specialPriceList=this.indexFirstService.specialPriceGoods();
		request.setAttribute("specialPriceList", specialPriceList);
		
		
		//商品分类
		List productCatalogList=this.indexFirstService.pdtCatalog();
		System.out.println(productCatalogList);
		request.setAttribute("productCatalogList", productCatalogList);
		
		
		//首页搜索顶级
		//List topProduct=this.indexFirstService.topProduct();
		//request.setAttribute("productList", topProduct);
		
		
		//首页公告菜单
		//List bulletinList=this.indexFirstService.bulletinList();
		//request.setAttribute("bulletinList", bulletinList);
		
		//友情链接
		//List friendLinkist=this.indexFirstService.friendLinkist();
		//request.setAttribute("friendLinkist", friendLinkist);

		return mapping.findForward("indexSuccess");
	}
	

	public ActionForward getXML(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//menu = new MenuService();
		//String jgbh = (String) request.getParameter("jgbh");
		//String jsbh = (String) request.getParameter("jsbh");

		//menu.setJgbh(jgbh);
		//menu.setJsbh(jsbh);

		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(indexFirstService.getRoleMoudleTree());
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	
	
	//唯一显示一条商品
	@SuppressWarnings("unchecked")
	public ActionForward onlyGoodsShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws wlglException {
		String productId=request.getParameter("productId");
		request.setAttribute("productId", productId);
		
		//String productName=request.getParameter("productName");
		//request.setAttribute("productName", productName);
		
		//产品
		List onlyProductList=this.indexFirstService.onlyGoods(productId);
		request.setAttribute("onlyProductList", onlyProductList);
		
		
		//该产品的评论
		List reviewList=this.indexFirstService.showReview(productId);
		request.setAttribute("reviewList", reviewList);
		
		//首页搜索顶级
		List topProduct=this.indexFirstService.topProduct();
		request.setAttribute("productList", topProduct);
		return mapping.findForward("onlyOneSuccess");	
	}
	
	
	//显示所有评论
	@SuppressWarnings("unchecked")
	public ActionForward showAllReview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String page =request.getParameter("page");
		if ("".equals(page) || page==null)	{
			page =request.getParameter("targetPage");
		}
		
		if ("".equals(page) || page==null)	{
			page="1";
		}
		//判断一下输入的页值是不是大于总的页数
		String totalPages= request.getParameter("totalPages");
		if  (!"".equals(totalPages) && totalPages!=null)
		if ((Integer.parseInt(page))>(Integer.parseInt(totalPages)))//输入的页数不能超过总页数
			page=totalPages;
		
		
	       String productId=request.getParameter("productId");
		   request.setAttribute("productId", productId);
		   
		  // String productName=request.getParameter("productName");
		  // request.setAttribute("productName", productName);
		
		
		  Page ppage=this.indexFirstService.showAllReview(Integer.parseInt(page), productId);
		  request.setAttribute("ppage", ppage);
		  List pageList=ppage.getResultList();
		  
		  Map  StrVal=(Map)(pageList.get(0));
		  String productName=StrVal.get("productName").toString();
		  request.setAttribute("productName", productName);
		 
		  //String [] strs=StrVal.split(",");
		// String productName=strs[5].toString();
		
		    //首页搜索顶级
			List topProduct=this.indexFirstService.topProduct();
			request.setAttribute("productList", topProduct);
			
		   
		  
		  return mapping.findForward("allReviewSuccess");
	}

	
	@SuppressWarnings("unchecked")
	public ActionForward someGoodsShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) { 
		 
		String page =request.getParameter("page");
		if ("".equals(page) || page==null)	{
			page =request.getParameter("targetPage");
		}
		
		if ("".equals(page) || page==null)	{
			page="1";
		}
		
		//判断一下输入的页值是不是大于总的页数
		String totalPages= request.getParameter("totalPages");
		if  (!"".equals(totalPages) && totalPages!=null)
		if ((Integer.parseInt(page))>(Integer.parseInt(totalPages)))//输入的页数不能超过总页数
			page=totalPages;
		
		
		
		  String  catalogId=request.getParameter("catalogId");
		  request.setAttribute("catalogId", catalogId);
		
		  Page ppage=this.indexFirstService.someProductShow(page, catalogId);
		  request.setAttribute("ppage", ppage);
		
		  
		  //首页搜索顶级
			List topProduct=this.indexFirstService.topProduct();
			request.setAttribute("productList", topProduct);
		  
		  
		return mapping.findForward("someProduct");
	}
	
	
	//提交保存　回复review
	public ActionForward review(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) { 
		
		Page pg = new Page();
		String tag = "RE";// 标志位
		int len = 12;// 位数
		String tableStr = "t_lsh";// 表名
		String dateField = "reviewDate";// 日期字段名
		String initField = "reviewInitValue";// 初始化值
		String reviewId= pg.lshNO(tag, len, tableStr, dateField, initField);// 自动生成
							

		 String productId=request.getParameter("productId");
		 request.setAttribute("productId", productId);
		 
		 String productName=request.getParameter("productName");
			request.setAttribute("productName", productName);
			
			
			
		 String userName=request.getParameter("userNameReview");//评论者名称
		 String userNameSave="";
		try {
			userNameSave = new String(userName.getBytes("GB2312"),"GB2312");
			if(!userNameSave.equals(userName))
			{	
				userName=new String(userName.getBytes("ISO-8859-1"),"GB2312"); 
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
			
			
		 
		 
		 
		 
		  
		  /////////////ip
		  String ip ="";
		  try {
			 ip =InetAddress.getLocalHost().toString(); //取得服务器ip地址
			 String [] strIp=ip.split("/");
			 ip=strIp[1].toString();
		   } catch (UnknownHostException e) {
			e.printStackTrace();
		   }
		   
		   
		   
		   
		  String contentH=request.getParameter("contentH");
		  String contentHSave="";
			try {
				contentHSave = new String(contentH.getBytes("GB2312"),"GB2312");
				if(!contentHSave.equals(contentH))
				{	
					contentH=new String(contentH.getBytes("ISO-8859-1"),"GB2312"); 
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
			
		  
		  String ReviewGrade="";
		  String   [] ReviewGradeValues=request.getParameterValues("pingji");  //评论等级
		  for (int i=0;i<ReviewGradeValues.length;i++)
		  {
			  ReviewGrade=ReviewGradeValues[i];
		  }
		  
		  String isAudit="1";//默认已审核吧
		  
		  String titleName=request.getParameter("titleName");
		  String titleNameSave="";
			try {
				titleNameSave = new String(titleName.getBytes("GB2312"),"GB2312");
				if(!titleNameSave.equals(titleName))
				{	
					titleName=new String(titleName.getBytes("ISO-8859-1"),"GB2312"); 
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		  
		  
		  
		  
		  
		  
		  
		  String isReply="0";//未回复
		
		  
		  this.indexFirstService.insertReview(reviewId, productId, userName, ip,
				  contentH, ReviewGrade, isAudit, titleName, isReply);
		  
		return mapping.findForward("onlyReviewSuccess");//转到	　唯一显示一条商品
	}
	
	
	//在 所有回复中  新增加 评论
	public ActionForward allReviewAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) { 
		
		Page pg = new Page();
		String tag = "RE";// 标志位
		int len = 12;// 位数
		String tableStr = "t_lsh";// 表名
		String dateField = "reviewDate";// 日期字段名
		String initField = "reviewInitValue";// 初始化值
		String reviewId= pg.lshNO(tag, len, tableStr, dateField, initField);// 自动生成
							

		 String productId=request.getParameter("productId");
		 request.setAttribute("productId", productId);
		 
		 
		 
		 
		 String productName=request.getParameter("productName");
			request.setAttribute("productName", productName);
			
		 String userName=request.getParameter("userNameReview");//评论者名称
		 String userNameSave="";
		try {
			userNameSave = new String(userName.getBytes("GB2312"),"GB2312");
			if(!userNameSave.equals(userName))
			{	
				userName=new String(userName.getBytes("ISO-8859-1"),"GB2312"); 
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
			
		 
		 
		 
		  
		  /////////////ip
		  String ip ="";
		  try {
			 ip =InetAddress.getLocalHost().toString(); //取得服务器ip地址
			 String [] strIp=ip.split("/");
			 ip=strIp[1].toString();
		   } catch (UnknownHostException e) {
			e.printStackTrace();
		   }
		   
		   
		   
		   
		   
		
		  String contentH=request.getParameter("contentH");
		  String contentHSave="";
			try {
				contentHSave = new String(contentH.getBytes("GB2312"),"GB2312");
				if(!contentHSave.equals(contentH))
				{	
					contentH=new String(contentH.getBytes("ISO-8859-1"),"GB2312"); 
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
		  
		  
		  String ReviewGrade="";
		  String   [] ReviewGradeValues=request.getParameterValues("pingji");  //评论等级
		  for (int i=0;i<ReviewGradeValues.length;i++)
		  {
			  ReviewGrade=ReviewGradeValues[i];
		  }
		  
		  String isAudit="1";//默认已审核吧
		  

		  String titleName=request.getParameter("titleName");
		  String titleNameSave="";
			try {
				titleNameSave = new String(titleName.getBytes("GB2312"),"GB2312");
				if(!titleNameSave.equals(titleName))
				{	
					titleName=new String(titleName.getBytes("ISO-8859-1"),"GB2312"); 
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		  
		  
		  
		  
		  
		  String isReply="0";//未回复
		
		  if((!"".equals("titleName"))||(!"".equals("userName")))
		  {
		    this.indexFirstService.insertReview(reviewId, productId, userName, ip,
				  contentH, ReviewGrade, isAudit, titleName, isReply);
		  }
		  
		  request.setAttribute("titleName","");
		  request.setAttribute("userName","");

		return mapping.findForward("allReviewAddsuc");
	}
	/////////////////////////////特价促销
	
	@SuppressWarnings("unchecked")
	public ActionForward specialPrice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String page =request.getParameter("page");
		if ("".equals(page) || page==null)	{
			page =request.getParameter("targetPage");
		}
		
		if ("".equals(page) || page==null)	{
			page="1";
		}
		//判断一下输入的页值是不是大于总的页数
		String totalPages= request.getParameter("totalPages");
		if  (!"".equals(totalPages) && totalPages!=null)
		if ((Integer.parseInt(page))>(Integer.parseInt(totalPages)))//输入的页数不能超过总页数
			page=totalPages;
		
		Page ppage=this.indexFirstService.specialPrice(Integer.parseInt(page));
		request.setAttribute("ppage", ppage);
		
		
		
		//首页搜索顶级
		List topProduct=this.indexFirstService.topProduct();
		request.setAttribute("productList", topProduct);
		
		
		return mapping.findForward("specialPrice");
	}

	//更多新品
	@SuppressWarnings("unchecked")
	public ActionForward moreNewProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String page =request.getParameter("page");
		if ("".equals(page) || page==null)	{
			page =request.getParameter("targetPage");
		}
		 
		if ("".equals(page) || page==null)	{
			page="1";
		}
		
		//判断一下输入的页值是不是大于总的页数
		String totalPages= request.getParameter("totalPages");
		if  (!"".equals(totalPages) && totalPages!=null)
		if ((Integer.parseInt(page))>(Integer.parseInt(totalPages)))//输入的页数不能超过总页数
			page=totalPages;
	
		Page ppage=this.indexFirstService.NewsProduct(Integer.parseInt(page));
		request.setAttribute("ppage", ppage);
		
		
		//首页搜索顶级
		List topProduct=this.indexFirstService.topProduct();
		request.setAttribute("productList", topProduct);
		
		
		
		return mapping.findForward("moreNewProduct");	
		
	}
	
	
	//更多推荐
	@SuppressWarnings("unchecked")
	public ActionForward moreReleaseProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String page =request.getParameter("page");
		if ("".equals(page) || page==null)	{
			page =request.getParameter("targetPage");
		}
		
		if ("".equals(page) || page==null)	{
			page="1";
		}
		
		//判断一下输入的页值是不是大于总的页数
		String totalPages= request.getParameter("totalPages");
		if  (!"".equals(totalPages) && totalPages!=null)
		if ((Integer.parseInt(page))>(Integer.parseInt(totalPages)))//输入的页数不能超过总页数
			page=totalPages;
	
		Page ppage=this.indexFirstService.ReleaseProduct(Integer.parseInt(page));
		request.setAttribute("ppage", ppage);
		
		
		//首页搜索顶级
		List topProduct=this.indexFirstService.topProduct();
		request.setAttribute("productList", topProduct);
		
		
		return mapping.findForward("moreReleaseProduct");	
		
	}
	
	///////////////商品新闻
	@SuppressWarnings("unchecked")
	public ActionForward productNewsShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    	
		String page =request.getParameter("page");
		if ("".equals(page) || page==null)	{
			page =request.getParameter("targetPage");
		}
		
		if ("".equals(page) || page==null)	{
			page="1";
		}
		
		//判断一下输入的页值是不是大于总的页数
		String totalPages= request.getParameter("totalPages");
		if  (!"".equals(totalPages) && totalPages!=null)
		if ((Integer.parseInt(page))>(Integer.parseInt(totalPages)))//输入的页数不能超过总页数
			page=totalPages;
	
       Page ppage=this.indexFirstService.prdNews(Integer.parseInt(page));
       request.setAttribute("ppage", ppage);
       
       
        //首页搜索顶级
		List topProduct=this.indexFirstService.topProduct();
		request.setAttribute("productList", topProduct);
       
	
       return mapping.findForward("productNewsShow");	
	   }
	

	//单一新闻明细
	@SuppressWarnings("unchecked")
	public ActionForward newsDetailList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String newsId=request.getParameter("newsId");
		List newsDetailList=this.indexFirstService.newsDetailList(newsId);
		request.setAttribute("newsDetailList", newsDetailList);
		
		 return mapping.findForward("newsDetailList");	
	}
	
	
	
	
	
	
	
///////////////////////////////////上传文件
	private ServletConfig config;

	 final public void init(ServletConfig config) throws ServletException {
	  this.config = config;
	 }
		
		public ActionForward upload(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, SmartUploadException {
			  
			
			SmartUpload mySmartUpload = new SmartUpload();
			
			 try {
				mySmartUpload.initialize(config, request, response);
				mySmartUpload.upload();
				mySmartUpload.save("/images/smallPic");
				
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			   request.setAttribute("errmsg", "文件已成功上传！");
			   return mapping.findForward("upSuccess");	
		}
	
	
}