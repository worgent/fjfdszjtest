package com.qzgf.NetStore.service.impl;

import java.sql.ResultSet;
import java.util.List;

import com.qzgf.NetStore.dao.IIndexFirstDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
import com.qzgf.NetStore.service.IIndexFirstService;



public class IndexFirstService  implements IIndexFirstService{
	//UtilDB du = new UtilDB();
	
	private IIndexFirstDAO indexFirstDAO;
	
	@SuppressWarnings("unchecked")
	public List newsGoods() throws wlglException
	{
		return this.indexFirstDAO.newsGoods();
	}

	
	@SuppressWarnings("unchecked")
	public List  releseGoods() throws wlglException
	{
		return this.indexFirstDAO.releseGoods();
	}
	
	
	//特价专区
	@SuppressWarnings("unchecked")
	public List specialPriceGoods() throws wlglException{
		return this.indexFirstDAO.specialPriceGoods();	
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List onlyGoods(String productId) throws wlglException{
		return this.indexFirstDAO.onlyGoods(productId);
	}
	
	
	public IIndexFirstDAO getIndexFirstDAO() {
		return indexFirstDAO;
	}

	public void setIndexFirstDAO(IIndexFirstDAO indexFirstDAO) {
		this.indexFirstDAO = indexFirstDAO;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List showReview(String productId)  throws wlglException{
		
		return this.indexFirstDAO.showReview(productId);
	}
	
	//显示所有评论
	public Page showAllReview(int nPage,String productId){
		
	   return this.indexFirstDAO.showAllReview(nPage, productId);	
	}
	
	public void insertReview(String reviewId,String productId,String userName,String ip,
			   String contentH,String ReviewGrade,String isAudit,String titleName, String isReply)
	{
	  this.indexFirstDAO.insertReview(reviewId, productId, userName, ip, 
				 contentH, ReviewGrade, isAudit, titleName, isReply);
	}
	
	////////////////////特价促销
	public Page specialPrice(int nPage){
		
	return this.indexFirstDAO.specialPrice(nPage);	
	}
	
	
	@SuppressWarnings("unchecked")
	public  List pdtCatalog()
	{
		return this.indexFirstDAO.pdtCatalog();	
	}
	
	public  Page someProductShow(String npage,String catalogId)
	{
		return this.indexFirstDAO.someProductShow(npage, catalogId);
	}
	
	@SuppressWarnings("unchecked")
	public  List topProduct()
	{
		return this.indexFirstDAO.topProduct();
	}

	public   Page  NewsProduct(int nPage){
		
		return this.indexFirstDAO.NewsProduct(nPage);
		
	}
	
	public   Page  ReleaseProduct(int nPage){
		
		return this.indexFirstDAO.ReleaseProduct(nPage);
		
	}
	
	@SuppressWarnings("unchecked")
	public   List bulletinList(){
		return this.indexFirstDAO.bulletinList();
	}
	
	public   Page  prdNews(int nPage){
		
		return this.indexFirstDAO.prdNews(nPage);
	}
	
	@SuppressWarnings("unchecked")
	public   List newsDetailList(String newsId){
		return this.indexFirstDAO.newsDetailList(newsId);
	}
	
	//友情链接
	@SuppressWarnings("unchecked")
	public   List  friendLinkist(){
		
		return this.indexFirstDAO.friendLinkist();
	}
	
	
////////////////////////////树问题
	public String getRoleMoudleTree() {
		String xml="[" + getTree("0         ","") + "]";
		return xml;
	}
	

	private String getTree(String tempId, String tempStr) {
		
		 UtilDB utilDB=null;
			try {
				utilDB = new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select * from  t_productcatalog where parentId='" + tempId+ "'";
			rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				tempId = rs.getString("id");
				tempStr += "{";
				tempStr += "\"id\":\"" + tempId + "\"";
				tempStr += ",\"text\":\"" + rs.getString("text") + "\"";
				if(rs.getString("leaf").equals("N"))  {
				   tempStr += ",\"leaf\":false,\"children\":[";
				   tempStr = getTree(tempId, tempStr);
				   tempStr += "]";
				} else {
					tempStr += ",\"leaf\":true";
					sql="select * from t_productcatalog where parentId='"+ rs.getString("id") + "'";;
					//ResultSet rs1 = du.stmtTwo.executeQuery(sql);
					ResultSet rs1 = utilDB.executeQuery(sql);
					
					
					
					if(rs1.next()) 
						tempStr += ",\"checked\":true";
					else
						tempStr += ",\"checked\":false";
					
					rs1.close();
				}
				if(rs.isLast())
					tempStr += "}";
				else
					tempStr += "},";
			}
			rs.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		}
		return tempStr;
	}

	
	@SuppressWarnings("unchecked")
	public List getPrdCata(String parentId) {
		return this.indexFirstDAO.getPrdCata(parentId);
	}
	
	
	
	
	
	
	

	
}
