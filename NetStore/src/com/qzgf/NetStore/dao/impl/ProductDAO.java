package com.qzgf.NetStore.dao.impl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IProductDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class ProductDAO extends BaseDao implements IProductDAO {
	//UtilDB du = new UtilDB();
	@SuppressWarnings("unchecked")

public Page showProducts(int nPage){
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		String sql="select productId,productName,displayDate,isRelease,smallPath,bigPath  from  t_product order by displayDate desc";

		Page page=utilDB.executeQueryByPageForMySql(sql,nPage,Page.DEFAULT_PAGESIZE);
		
		ResultSet rs=page.getRowset();
	    List results = new ArrayList();	

		try {
			
			while(rs.next()){
				Map map=new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("displayDate", rs.getTimestamp("displayDate"));
				map.put("isRelease", rs.getString("isRelease"));
				
				map.put("smallPath", rs.getString("smallPath"));
				map.put("bigPath", rs.getString("bigPath"));
				
				results.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public Page searchProducts(int nPage,String searchStr,String SearchType){
		
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		String sql="";
		
		if(SearchType.equals("1"))//按商品名称
		{
			sql="select productId,productName,displayDate,isRelease  from  t_product " +
					" where productName like '%" +searchStr+"%'"+
			        " order by displayDate desc";
		}
		else if(SearchType.equals("2"))//按商品说明
		{
			sql="select productId,productName,displayDate,isRelease  from  t_product " +
			" where ProductIntro like '%" +searchStr+"%'"+
	        " order by displayDate desc";
		}
		else if(SearchType.equals("3"))//按商品序号
		{
			sql="select productId,productName,displayDate,isRelease  from  t_product " +
			" where ProductId like '%" +searchStr+"%'"+
	        " order by displayDate desc";
		}
		else if(SearchType.equals("5"))//按新品
		{
			sql="select productId,productName,displayDate,isRelease  from  t_product " +
			" where IsNewGoods=1 "+
	        " order by displayDate desc";
		}
		else if(SearchType.equals("6"))//按特价
		{
			sql="select productId,productName,displayDate,isRelease  from  t_product " +
			" where IsSpecialPrice=1 "+
	        " order by displayDate desc";
		}
		else if(SearchType.equals("7"))//按推荐
		{
			sql="select productId,productName,displayDate,isRelease  from  t_product " +
			" where IfCommend=1 "+
	        " order by displayDate desc";
		}
		else //全部商品 4
		{
			sql="select productId,productName,displayDate,isRelease  from  t_product order by displayDate desc";
		}
		

		Page page=utilDB.executeQueryByPageForMySql(sql,nPage,Page.DEFAULT_PAGESIZE);
		
		ResultSet rs=page.getRowset();
	    List results = new ArrayList();	

		try {
			
			while(rs.next()){
				Map map=new HashMap();
				map.put("productId", rs.getString("productId"));
				map.put("productName", rs.getString("productName"));
				map.put("displayDate", rs.getTimestamp("displayDate"));
				map.put("isRelease", rs.getString("isRelease"));
				results.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
	

}
