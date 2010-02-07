package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;



import com.qzgf.NetStore.dao.IIndexFirstDAO;

import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;


public class IndexFirstDAO extends BaseDao implements IIndexFirstDAO   {
	//UtilDB du = new UtilDB();
	  
	 
	// 新品专区
	@SuppressWarnings("unchecked")
	public List newsGoods() throws wlglException{
		
		
/*
		List results = new ArrayList();
	     try {
		     results = utilDB.exeQueryRow("select  productId,displayDate, productName,marketPrice,memberPrice,SmallPath " +
		     		" from  t_product where IsNewGoods=1 order by displayDate desc");
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      utilDB.closeCon();
		
      return results;
		*/
      
      
		UtilDB utilDB = new UtilDB();
		ResultSet rs = null;
		String sql = "select  productId,displayDate, productName,marketPrice,memberPrice,SmallPath  from  t_product where IsNewGoods=1 order by displayDate desc";
		List results = new ArrayList();
		try {
			rs =  utilDB.exeQueryRow(sql);
			
			int i=0;
			while (rs.next()) {
				if(i<4)
				{
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("marketPrice", rs.getString("marketPrice"));
				map.put("memberPrice", rs.getString("memberPrice"));
				map.put("smallPath", rs.getString("smallPath"));
				map.put("productName", rs.getString("productName"));
				results.add(map);
				}
				else
				{
					break;
				}
				i=i+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  utilDB.closeCon();
		return results;
		
		
		
		
		

	}

	// 推荐
	@SuppressWarnings("unchecked")
	public List releseGoods()  throws wlglException {
//		ResultSet rs = null;
//		String sql = "select  productId,displayDate, productName,marketPrice,memberPrice,SmallPath  from  t_product where IFCommend=1 order by displayDate desc";
//		System.out.println(sql);
//		List results = new ArrayList();
//		try {
//			rs = du.stmt.executeQuery(sql);
//			
//			
			
			
			
		
		   UtilDB  utilDB = new UtilDB();
			
			ResultSet rs = null;
			String sql = "select  productId,displayDate, productName,marketPrice,memberPrice,SmallPath  from  t_product where IFCommend=1 order by displayDate desc";
			List results = new ArrayList();
			try {
					rs =  utilDB.exeQueryRow(sql);

			int i=0;
			while (rs.next()) {
				if(i<4)
				{
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("marketPrice", rs.getString("marketPrice"));
				map.put("memberPrice", rs.getString("memberPrice"));
				map.put("smallPath", rs.getString("smallPath"));
				map.put("productName", rs.getString("productName"));
				results.add(map);
				}
				else
				{
					break;
				}
				i=i+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	// 特价专区
	@SuppressWarnings("unchecked")
	public List specialPriceGoods() throws wlglException{
		
		
		
		
		/*ResultSet rs = null;
		String sql = "select  productId,displayDate, productName,marketPrice,memberPrice,SmallPath  from  t_product where IsSpecialPrice=1 order by displayDate desc";
		System.out.println(sql);
		List results = new ArrayList();
		try {
			
			rs = du.stmt.executeQuery(sql);*/
			
			
			
			
			
			  UtilDB  utilDB = new UtilDB();
				
				ResultSet rs = null;
				String sql = "select  productId,displayDate, productName,marketPrice,memberPrice,SmallPath  from  t_product where IsSpecialPrice=1 order by displayDate desc";
				List results = new ArrayList();
				try {
						rs =  utilDB.exeQueryRow(sql);
			
			
			int i=0;
			while (rs.next()) {
				if(i<4)
				{
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("marketPrice", rs.getString("marketPrice"));
				map.put("memberPrice", rs.getString("memberPrice"));
				map.put("smallPath", rs.getString("smallPath"));
				map.put("productName", rs.getString("productName"));
				results.add(map);
				}
				else
				{
					break;
				}
				i=i+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		utilDB.closeCon();
		
		return results;
	}

	// 显示大图片
	@SuppressWarnings("unchecked")
	public List onlyGoods(String productId) throws wlglException{
		/*ResultSet rs = null;
		String sql = "select  productId,productName,stock,marketPrice,memberPrice,productIntro,bigPath  from  t_product where productId='"
				+ productId + "'";
		System.out.println(sql);
		List results = new ArrayList();
		try {
			rs = du.stmt.executeQuery(sql);*/
			
			
		UtilDB  utilDB = new UtilDB();
		
		ResultSet rs = null;
		String sql = "select  productId,productName,stock,marketPrice,memberPrice,productIntro,bigPath  from  t_product where productId='"
			+ productId + "'";
		List results = new ArrayList();
		try {
				rs =  utilDB.exeQueryRow(sql);
			
			while (rs.next()) {
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("stock", rs.getString("stock"));
				map.put("marketPrice", rs.getString("marketPrice"));
				map.put("memberPrice", rs.getString("memberPrice"));
				map.put("productIntro", rs.getString("productIntro"));
				map.put("bigPath", rs.getString("bigPath"));
				results.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		utilDB.closeCon();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List showReview(String productId) throws wlglException {
		/*ResultSet rs = null;
		String sql = "select  productId,userId,title,content,releaseTime,replyContent,ip  from  t_review where productId='"
				+ productId + "' and isAudit=1 order by releaseTime desc";
		System.out.println(sql);
		List results = new ArrayList();
		try {
			rs = du.stmt.executeQuery(sql);*/
			
			
       UtilDB  utilDB = new UtilDB();
		
		ResultSet rs = null;
		String sql = "select  productId,userId,title,content,releaseTime,replyContent,ip  from  t_review where productId='"
			+ productId + "' and isAudit=1 order by releaseTime desc";
	     List results = new ArrayList();
		try { 
				rs =  utilDB.exeQueryRow(sql);
			
			
			
			
			while (rs.next()) {
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("userId", rs.getString("userId"));
				map.put("title", rs.getString("title"));
				map.put("content", rs.getString("content"));
				map.put("releaseTime", rs.getString("releaseTime"));
				map.put("replyContent", rs.getString("replyContent"));
				map.put("ip", rs.getString("ip"));
				results.add(map);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		utilDB.closeCon();
		return results;
	}

	// 显示该商品所有评论
	@SuppressWarnings("unchecked")
	public Page showAllReview(int nPage, String productId) {
		String sql = "select  a.productId,b.productName,userId,title,content,releaseTime,replyContent,ip   from  t_review a ,t_product  b "
				+ " where  a.productId=b.productId and  a.productId='"
				+ productId + "' and isAudit=1 order by  releaseTime desc ";
		
		 UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Page page = utilDB.executeQueryByPageForMySql(sql, nPage,
				Page.DEFAULT_PAGESIZE);
		ResultSet rs = page.getRowset();
		List results = new ArrayList();
		try {
			while (rs.next()) {
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("userId", rs.getString("userId"));
				map.put("title", rs.getString("title"));
				map.put("content", rs.getString("content"));
				map.put("releaseTime", rs.getString("releaseTime"));
				map.put("replyContent", rs.getString("replyContent"));
				map.put("ip", rs.getString("ip"));
				results.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(results);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return page;
	}

	public void insertReview(String reviewId, String productId,
			String userName, String ip, String contentH, String ReviewGrade,
			String isAudit, String titleName, String isReply) {
		UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String str = "insert into t_review(ReviewId,ProductId,UserId,Ip,Content,ReviewGrade,isAudit,title,isReply) "
				+ " values('"
				+ reviewId
				+ "','"
				+ productId
				+ "','"
				+ userName
				+ "','"
				+ ip
				+ "',"
				+ "'"
				+ contentH
				+ "',"
				+ ReviewGrade
				+ ","
				+ isAudit + ",'" + titleName + "'," + isReply + ")";
		System.out.println(str);
		
		
		//try {
			//du.stmt.executeUpdate(str);
			utilDB.executeUpdate(str);
			try {
				utilDB.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	/*	} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
	}

	// ///////////////////////////////特价促销
	@SuppressWarnings("unchecked")
	public Page specialPrice(int nPage) {
		UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "select productId,productName,ProductIntro,smallPath,marketPrice,memberPrice,displayDate from t_product where IsSpecialPrice=1 and IfCommend=1 order by  DisplayDate desc";// 是特价
																																																// 且
																																																// 是发布的
		Page page = utilDB.executeQueryByPageForMySql(sql, nPage,
				Page.DEFAULT_PAGESIZE);
		ResultSet rs = page.getRowset();
		List results = new ArrayList();
		try {
			while (rs.next()) {
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("productIntro", rs.getString("productIntro"));
				map.put("smallPath", rs.getString("smallPath"));
				map.put("marketPrice", rs.getString("marketPrice"));
				map.put("memberPrice", rs.getString("memberPrice"));
				map.put("displayDate", rs.getString("displayDate"));
				results.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(results);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	// ///////////////////////////////特价促销

	// //////首页商品分类
	@SuppressWarnings("unchecked")
	public List pdtCatalog() {
		
		UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		

		ResultSet rs = null;
		String sql = "select * from  t_productcatalog where parentId='0'";
		System.out.println(sql);
		List results = new ArrayList();
		try {
			//rs = du.stmt.executeQuery(sql);
			rs = utilDB.executeQuery(sql);
			
			while (rs.next()) {
				Map map = new HashMap();
				map.put("id", rs.getString("id"));
				map.put("catalogName", rs.getString("catalogName"));
				map.put("parentId", rs.getString("parentId"));
				results.add(map);

				String sqlChild = "select * from t_productcatalog where parentId='"
						+ rs.getString("id") + "'";
				System.out.println(sqlChild);

				//ResultSet rs1 = du.stmtTwo.executeQuery(sqlChild);
				ResultSet rs1 = utilDB.executeQuery(sqlChild);
				
				
				while (rs1.next()) {
					System.out.println(rs1.getString("catalogName"));
					Map map1 = new HashMap();
					map1.put("id", rs1.getString("id"));
					map1.put("catalogName", rs1.getString("catalogName"));
					map1.put("parentId", rs1.getString("parentId"));
					results.add(map1);
				}
				// results.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*try {
			)
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		return results;
	}

	// /首页 someGoodsShow
	@SuppressWarnings("unchecked")
	public Page someProductShow(String npage, String catalogId) {
		UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String sql = "select * from  t_product where catalogId='" + catalogId
				+ "' and IsRelease=1 order by  DisplayDate desc";// 已经发布且按上架时间来排顺序
		
		Page page = utilDB.executeQueryByPageForMySql(sql, Integer.parseInt(npage),
				Page.DEFAULT_PAGESIZE);
		
		ResultSet rs = page.getRowset();
		List results = new ArrayList();
		try {
			while (rs.next()) {
				Map map = new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("productIntro", rs.getString("productIntro"));
				map.put("smallPath", rs.getString("smallPath"));
				map.put("marketPrice", rs.getString("marketPrice"));
				map.put("memberPrice", rs.getString("memberPrice"));
				map.put("displayDate", rs.getString("displayDate"));
				results.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(results);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	// ////////////////商品搜索
	@SuppressWarnings("unchecked")
	public List topProduct() {
		
		UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		ResultSet rs = null;
		String sql = "select * from t_productCatalog where parentId='0'";
		System.out.println(sql);
		List results = new ArrayList();
		try {
			rs = utilDB.executeQuery(sql);
			Map map = new HashMap();
			map.put("id", "0");
			map.put("catalogName", "所有商品");
			results.add(map);

			while (rs.next()) {
				map = new HashMap();
				map.put("id", rs.getString("id"));
				map.put("catalogName", rs.getString("catalogName"));
				results.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	
	//更多的新品展示

	@SuppressWarnings("unchecked")
	public   Page  NewsProduct(int nPage){
		
		UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "select * from t_product where  IsNewGoods=1 and IfCommend=1 order by  DisplayDate desc";
		System.out.println(sql);
		
		Page page = utilDB.executeQueryByPageForMySql(sql, nPage,Page.DEFAULT_PAGESIZE);
		ResultSet rs1 = page.getRowset();
		List results = new ArrayList();
		try {
			while (rs1.next()) {
				Map map = new HashMap();
				map.put("productId", rs1.getString("productId"));
				map.put("productName", rs1.getString("productName"));
				map.put("productIntro", rs1.getString("productIntro"));
				map.put("smallPath", rs1.getString("smallPath"));
				map.put("marketPrice", rs1.getString("marketPrice"));
				map.put("memberPrice", rs1.getString("memberPrice"));
				map.put("displayDate", rs1.getString("displayDate"));
				results.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(results);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
		
	}
	
	  @SuppressWarnings("unchecked")
	public   Page  ReleaseProduct(int nPage){
		  UtilDB utilDB=null;
			try {
				utilDB = new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
		  
		  
		  String sql = "select * from t_product where  IsRelease=1 and IfCommend=1 order by  DisplayDate desc";
			System.out.println(sql);
			
			Page page = utilDB.executeQueryByPageForMySql(sql, nPage,Page.DEFAULT_PAGESIZE);
			ResultSet rs1 = page.getRowset();
			List results = new ArrayList();
			try {
				while (rs1.next()) {
					Map map = new HashMap();
					map.put("productId", rs1.getString("productId"));
					map.put("productName", rs1.getString("productName"));
					map.put("productIntro", rs1.getString("productIntro"));
					map.put("smallPath", rs1.getString("smallPath"));
					map.put("marketPrice", rs1.getString("marketPrice"));
					map.put("memberPrice", rs1.getString("memberPrice"));
					map.put("displayDate", rs1.getString("displayDate"));
					results.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			page.setResultList(results);
			return page;
	  }
	  
	  
	  //首页公告	
	  @SuppressWarnings("unchecked")
	public   List bulletinList()
	  {
		  UtilDB utilDB=null;
			try {
				utilDB = new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		  ResultSet rs = null;
			String sql = "select * from t_bulletin";
			System.out.println(sql);
			List results = new ArrayList();
			try {
				rs = utilDB.executeQuery(sql);
				while (rs.next()) {
					Map map = new HashMap();
				
					map.put("bulletinId", rs.getString("bulletinId"));
					map.put("bulletinContent", rs.getString("bulletinContent"));
					results.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				utilDB.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return results;
		  
	  }
	
	
	  //商品新闻
	  @SuppressWarnings("unchecked")
	public   Page  prdNews(int nPage){
		  UtilDB utilDB=null;
			try {
				utilDB = new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
		  
		  
		  String sql = "select *  FROM  t_news order by releaseTime desc";//where ifcommend=1 
			System.out.println(sql);
			
			Page page = utilDB.executeQueryByPageForMySql(sql, nPage,Page.DEFAULT_PAGESIZE);
			ResultSet rs1 = page.getRowset();
			List results = new ArrayList();
			try {
				while (rs1.next()) {
					Map map = new HashMap();
					map.put("newsId", rs1.getString("newsId"));
					map.put("newsTitle", rs1.getString("newsTitle"));
					map.put("releaseMan", rs1.getString("releaseMan"));
					map.put("releaseTime", rs1.getString("releaseTime"));
					results.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			page.setResultList(results);
			return page;		  
	  }

	  //新闻详细信息
	  @SuppressWarnings("unchecked")
public   List newsDetailList(String newsId)
{
		  UtilDB utilDB=null;
			try {
				utilDB = new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		  ResultSet rs = null;
			String sql = "select * from t_news where newsId='"+newsId+"'";
			System.out.println(sql);
			List results = new ArrayList();
			try {
				rs = utilDB.executeQuery(sql);
				while (rs.next()) {
					Map map = new HashMap();
					map.put("newsId", rs.getString("newsId"));
					map.put("newsTitle", rs.getString("newsTitle"));
					map.put("newsContent", rs.getString("newsContent"));
					map.put("releaseMan", rs.getString("releaseMan"));
					map.put("releaseTime", rs.getString("releaseTime"));
					map.put("newsAuthor", rs.getString("newsAuthor"));
					results.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return results;
}
	  
	
	  
//友情链接
@SuppressWarnings("unchecked")
public   List  friendLinkist()
{
	  UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    	 ResultSet rs = null;
				String sql = "select * from t_friendlink ";
				System.out.println(sql);
				List results = new ArrayList();
				try {
					rs = utilDB.executeQuery(sql);
					while (rs.next()) {
						Map map = new HashMap();
						map.put("url", rs.getString("url"));
						map.put("urlTitle", rs.getString("urlTitle"));
						results.add(map);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return results;
}


//列出所有的树
@SuppressWarnings("unchecked")
public List getPrdCata(String parentId) {
	
	  UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	    ResultSet rs = null;
		String sql = "select id,CatalogName,ParentId from t_productcatalog where  parentId='"+parentId+"'";
		System.out.println(sql);
		List results = new ArrayList();
		try {
			rs = utilDB.executeQuery(sql);
			while (rs.next()) {
				Map map = new HashMap();
				map.put("id", rs.getString("id"));
				map.put("CatalogName", rs.getString("CatalogName"));
				results.add(map);
				
				
				 String sql1 = "select id,CatalogName,ParentId from t_productcatalog where  parentId='"+ rs.getString("id")+"'";
				 ResultSet rs1 = null;
				 rs1 = utilDB.executeQuery(sql1);
				 while (rs1.next()) {
					    map = new HashMap();
						map.put("id", rs1.getString("id"));
						map.put("CatalogName", rs1.getString("CatalogName"));
						results.add(map);
						
						results.add(zyjgTree(rs1.getString("id")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(results);
		return results;	
}	

@SuppressWarnings("unchecked")
private  List zyjgTree(String tempId) {
	  UtilDB utilDB=null;
		try {
			utilDB = new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    ResultSet rs = null;
	    List results = new ArrayList();
	    String sql="select id,CatalogName,ParentId from t_productcatalog where  parentId='"+ tempId+"'";
	    
	    try {
			rs = utilDB.executeQuery(sql);
	
			while (rs.next()) {
				Map map = new HashMap();
				map.put("id", rs.getString("id"));
				map.put("CatalogName", rs.getString("CatalogName"));
				results.add(map);
				
				
				
				 String sql1 = "select id,CatalogName,ParentId from t_productcatalog where  parentId='"+ rs.getString("id")+"'";
				 ResultSet rs1 = null;
				 rs1 = utilDB.executeQuery(sql1);
				
				while (rs1.next()) {
					results.add(zyjgTree(rs1.getString("id"))) ;
				}

		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 return results;
}


	  
	
}
