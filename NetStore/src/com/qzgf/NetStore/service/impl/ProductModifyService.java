package com.qzgf.NetStore.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;


import com.qzgf.NetStore.pub.*;
import com.qzgf.NetStore.persistence.*;
import com.qzgf.NetStore.dao.IProductModifyDAO;

import com.qzgf.NetStore.service.IProductModifyService;

public class ProductModifyService implements IProductModifyService {
	//UtilDB du = new UtilDB();
	
	private IProductModifyDAO productModifyDAO;

	public IProductModifyDAO getProductModifyDAO() {
		return productModifyDAO;
	}

	public void setProductModifyDAO(IProductModifyDAO productModifyDAO) {
		this.productModifyDAO = productModifyDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List showOnlyProduct(String pId)//唯一链接
	{
		
	/*	private String productname;
		private String brand;
		private String specification;
		
		
		
		
		private String marketPrice;
		private String memberPrice;
		private String stock;
		private String smallPath;
		private String bigPath;
		
		private String displayDate;
		private String productDate;*/
	//return this.productModifyDAO.showOnlyProduct(pId);
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		ResultSet rs = null;
		 List results = new ArrayList();	

			try {
				rs = utilDB.executeQuery("select * from t_product where  ProductId='"+pId+"'"); 
				
	
				while(rs.next()){
					Map map=new HashMap();
					
					map.put("catalogId", rs.getString("catalogId"));
					map.put("productname", rs.getString("productname"));
					map.put("brand", rs.getString("brand"));
					map.put("specification", rs.getString("specification"));
					
			 
					map.put("marketPrice", rs.getString("marketPrice"));
					map.put("memberPrice", rs.getString("memberPrice"));
					map.put("stock", rs.getString("stock"));
					map.put("smallPath", rs.getString("smallPath"));
					map.put("bigPath", rs.getString("bigPath"));
					
					map.put("productIntro", rs.getString("productIntro"));
					map.put("unitId", rs.getString("unitId"));
					map.put("manufacturerID", rs.getString("manufacturerID"));
					
					
					
					
					map.put("displayDate", rs.getTimestamp("displayDate"));
					map.put("productDate", rs.getTimestamp("productDate"));
					
					map.put("isRelease", rs.getString("isRelease"));
					map.put("isNewGoods", rs.getString("isNewGoods"));
					map.put("ifCommend", rs.getString("ifCommend"));
					map.put("isSpecialPrice", rs.getString("isSpecialPrice"));
					
					
					
					/*review.setProductId(rs.getString("productId"));
					review.setReviewId(rs.getString("reviewId"));
					review.setProductName(rs.getString("productName"));
					review.setIsAudit(rs.getByte("isAudit"));
					review.setTitle(rs.getString("title"));
					review.setReleaseTime(rs.getTimestamp("releaseTime"));*/
					
					
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
	
	@SuppressWarnings("unchecked")
	public List showProductBig()//商品大类
	{
		return this.productModifyDAO.showProductBig();
	}
	
	@SuppressWarnings("unchecked")
	public List showUnit()//单位列表
	{
		return this.productModifyDAO.showUnit();
	}

	@SuppressWarnings("unchecked")
	public List  showBrand()
	{
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		 ResultSet rs = null;
		 List results = new ArrayList();	

			try {
				rs = utilDB.executeQuery("select * from  t_specification"); 
				
			    Specification spec=new Specification();
				spec.setSpecificationId(0);
				spec.setSpecificationName("请选择品牌");
				results.add(spec);
				
				while(rs.next()){
					spec=new Specification();
					spec.setSpecificationId(rs.getInt("specificationId"));
					spec.setSpecificationName(rs.getString("specificationName"));
					results.add(spec);
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
	
	
	 @SuppressWarnings("unchecked")
	 
	 //获得商品分类
	 public String getProductName(String id)
	 {
		 return this.productModifyDAO.getProductName(id);
	 }
	 
	 
	 
	 //商品分类树
	 @SuppressWarnings("unchecked")
	List results = new ArrayList();	
	 	@SuppressWarnings("unchecked")
		public List showProductList() {
	 		
	 		
	 		
			UtilDB utilDB=null;
	    	try {
				 utilDB= new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
		ResultSet rs = null;
		try {
			rs = utilDB.executeQuery("select  *  from t_ProductCatalog where ParentId='0' order by id"); 
			while(rs.next()){
				//System.out.println(rs.getString("cont"));
				/*Map map=new HashMap();
				map.put("id", rs.getString("Id"));
				map.put("catalo;gName", rs.getString("CatalogName"));
				results.add(map);*/
				System.out.println(rs.getString("CatalogName"));
				tree(rs.getString("id"));
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

//添加保存
	 	public void addSave(String ProductId,String Productname,String CatalogId,
	 		    String ProductDate,String ManufacturerID,
	 			String IfCommend,String MarketPrice,String MemberPrice,String Brand,
	 			String ProductIntro,String SmallPath,String BigPath,
	 			String Specification,String UnitId,String Stock,String isRelease,
	 			String isNewGoods,String isSpecialPrice)
	 	{
			
			UtilDB utilDB=null;
	    	try {
				 utilDB= new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	 		
	 		
	 		
	 		
	 		
	 		
	 	String sql="insert into   t_product(ProductId,Productname,CatalogId,ProductDate," +
	 			"ManufacturerID,IfCommend,MarketPrice,MemberPrice,Brand," +
	 			"ProductIntro,SmallPath,BigPath,Specification,UnitId," +
	 			"Stock,isRelease,isNewGoods,isSpecialPrice)   values(" +
	 			"'"+ProductId+"','"+Productname+"','"+CatalogId+"','"+ProductDate+"'," +
	 			"'"+ManufacturerID+"',"+IfCommend+",'"+MarketPrice+"','"+MemberPrice+"','"+Brand+"'," +
	 		    "'"+ProductIntro+"','"+SmallPath+"','"+BigPath+"','"+Specification+"','"+UnitId+"'," +
	 		    "'"+Stock+"',"+isRelease+","+isNewGoods+","+isSpecialPrice+")";
	 	
	 /*	System.out.println(sql);
	 	try {
			utilDB.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	 	utilDB.executeUpdate(sql);
	 	
	 	try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	 	}
	 	
	 	
	 	
	 	
	 	
//更新保存	 
public void updateProduct(String ProductId,String Productname,String CatalogId,
	 			String ProductDate,String ManufacturerID,
	 			String IfCommend,String MarketPrice,String MemberPrice,String Brand,
	 			String ProductIntro,String SmallPath,String BigPath,
	 			String Specification,String UnitId,String Stock,String isRelease,
	 			String isNewGoods,String isSpecialPrice)
	 	 {
	
	UtilDB utilDB=null;
	try {
		 utilDB= new UtilDB();
	} catch (wlglException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
	
	
	
	
	
	
             String str="update t_product set Productname='"+Productname+"',CatalogId='"+CatalogId+"',"+
             " ProductDate='"+ProductDate+"',ManufacturerID='"+ManufacturerID+"',"+
             " IfCommend="+IfCommend+",MarketPrice='"+MarketPrice+"',MemberPrice='"+MemberPrice+"',"+
             " MemberPrice='"+MemberPrice+"',Brand='"+Brand+"',ProductIntro='"+ProductIntro+"',"+
             " SmallPath='"+SmallPath+"',BigPath='"+BigPath+"',Specification='"+Specification+"',"+
             " UnitId='"+UnitId+"',Stock='"+Stock+"',isRelease="+isRelease+",isNewGoods="+isNewGoods+","+
             " isSpecialPrice="+isSpecialPrice+" where productId='"+ProductId+"'";
             
             
             System.out.println(str);
	      /*   try {
				du.stmt.executeUpdate(str);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
          	utilDB.executeUpdate(str);
    	 	
    	 	try {
    			utilDB.closeCon();
    		} catch (wlglException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    			
			
			
	 	 }
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	@SuppressWarnings("unchecked")
	private void tree(String id) {
		
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		ResultSet rs = null;
		//StringBuffer strPre = new StringBuffer("");
	/*	for(int i=0; i<level; i++) {
			strPre.append("    ");
		}*/
		
		try {
			String sql = "select  *  from t_ProductCatalog where ParentId='"+id+"' order by id";
			rs = utilDB.executeQuery(sql);
			while(rs.next()) {
				//Map map=new HashMap();
				//map.put("id", rs.getString("Id"));
				//map.put("catalogName", rs.getString("CatalogName"));
				System.out.println(rs.getString("CatalogName"));
				//results.add(map);
				
				//String strChild="select  *  from t_ProductCatalog where ParentId='"+rs.getString("id")+"' order by id";
				//if()
				//{
			    //tree(rs.getString("id"));
				//}
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
		
		
	}
	 
	 
	 
	 
	 
	@SuppressWarnings("unchecked")
	public  List  getUnit(String id) throws SQLException
	 {
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		 String sql="";
		 if(id.equals("0"))
		 {
		  sql = "select  *  from t_ProductCatalog where ParentId='0' order by id";
		 }
		 else
		 {
		  sql = "select  *  from t_ProductCatalog where ParentId='"+id+"' order by id" ;
		 }
		 
			ResultSet rs=utilDB.executeQuery(sql);
			List results = new ArrayList();		
			try {
				while (rs.next()) {
					ProductCatalog Idx = new ProductCatalog();
					Idx.setId(rs.getString("id"));
					Idx.setCatalogName(rs.getString("CatalogName"));
					Idx.setParentId(rs.getString("parentId"));
					results.add(Idx);
					//String sqlChild="select * from ProductCatalog where ParentId='"+rs.getString("parentId")+"'";
					
					getUnit(rs.getString("id"));
					
					
					results.add(Idx);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			
			
			try {
				utilDB.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return	results;
	 }

///////////////树
	
	public String getGljgSimpleTree(String id) {
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		
		String tempStr="[";
		String sql = "";
		try {					
			sql = "select  *  from t_ProductCatalog where ParentId='"+id+"' order by id";
			ResultSet rs= utilDB.executeQuery(sql);
			while (rs.next()) {
				tempStr += "{";
				tempStr += "\"id\":\"" + rs.getString("id") + "\"";
				tempStr += ",\"text\":\"" + rs.getString("CatalogName") + "\"";
				//int i=1;
				
				/*//{
				//	i=1;
				}
				else
				{
					i=0;
				}*/
				
				
				
				sql="select  *  from t_ProductCatalog where ParentId='"+rs.getString("id")+"' order by id";
				//ResultSet rs1= du.stmtTwo.executeQuery(sql);
				ResultSet rs1= utilDB.executeQuery(sql);
				
				if(rs1.next())  {
				   rs1.close();
				   tempStr += ",\"leaf\":false";
				} else {
					rs1.close();
					tempStr += ",\"leaf\":true";
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
		tempStr += "]";
		
		
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempStr;
	}
	
	//供应商
	@SuppressWarnings("unchecked")
	public List showManufacturer()
	{
		
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String str="select * from   t_Manufacturer";
		ResultSet rs = null;
	    // try {
			rs = utilDB.executeQuery(str);
		//} catch (SQLException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
	//}
	     
			List results = new ArrayList();		
			try {
				while (rs.next()) {
					Manufacturer mfr = new Manufacturer();
					mfr.setManufacturerId(new Integer(rs.getString("manufacturerId")));
					mfr.setManufacturerName(rs.getString("manufacturerName"));
					results.add(mfr);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			
			try {
				utilDB.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		return	results;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


	
	
	
	
	
}
