package com.qzgf.NetStore.service;

import java.util.List;

import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.wlglException;

public interface IIndexFirstService {
	//新品专区
	@SuppressWarnings("unchecked")
	public List newsGoods() throws wlglException;
	
	
	//推荐专区
	@SuppressWarnings("unchecked")
	public List releseGoods() throws wlglException;
	
	//特价专区
	@SuppressWarnings("unchecked")
	public List specialPriceGoods() throws wlglException;
	
	
	//显示大图片
	@SuppressWarnings("unchecked")
	public List onlyGoods(String productId) throws wlglException;
	
	
	//显示回复
	@SuppressWarnings("unchecked")
	public List showReview(String productId) throws wlglException;
	
	//显示所有评论
	public Page showAllReview(int nPage,String productId);
	
	public void insertReview(String reviewId,String productId,String userName,String ip,
			   String contentH,String ReviewGrade,String isAudit,String titleName, String isReply);
	
	
	////////////////////特价促销
	public Page specialPrice(int nPage);
	
	//首页商品分类
	@SuppressWarnings("unchecked")
	public  List pdtCatalog();
	
	
	public  Page someProductShow(String npage,String catalogId);
	
	@SuppressWarnings("unchecked")
	public  List topProduct();


	//更多新品
	public   Page  NewsProduct(int nPage);
	
	//更多推荐区域
	public   Page  ReleaseProduct(int nPage);
	
	//首页公告
	@SuppressWarnings("unchecked")
	public   List bulletinList();
	
	
	//商品新闻
	public   Page  prdNews(int nPage);
	
	
	@SuppressWarnings("unchecked")
	public   List newsDetailList(String newsId);
	
	//友情链接
	@SuppressWarnings("unchecked")
	public   List  friendLinkist();
	
	
	//=============树
	public String getRoleMoudleTree();
	
	//全部樹
	@SuppressWarnings("unchecked")
	public List getPrdCata(String parentId);
	
	
	
}
