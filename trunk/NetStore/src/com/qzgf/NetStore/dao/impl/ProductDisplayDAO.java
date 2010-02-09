package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IProductDisplayDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
 
public class ProductDisplayDAO implements IProductDisplayDAO{
  
	/**
	 * 根据某个条件查询该条件下按最高上架的时间排序
	 */
	@SuppressWarnings("unchecked")
	public Page queryProductByNews(int npage) {
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		
		System.out.println("abcd");
		String sql="select * from t_Product where IfCommend=1 order by DisplayDate desc";
		Page page=utilDB.executeQueryByPageForMySql(sql, npage, 20);
		ResultSet rs=page.getRowset();
		List managersList=new ArrayList();
		try {
			while(rs.next()){
				Map map=new HashMap();
				map.put("ProductId", rs.getString("ProductId"));
				map.put("ProductName", rs.getString("ProductName"));
				map.put("Stock", rs.getString("Stock"));
				System.out.println("abababa");
				map.put("MarketPrice", rs.getString("MarketPrice"));
				map.put("MemberPrice", rs.getString("MemberPrice"));
				map.put("Brand", rs.getString("Brand"));
				map.put("SmallPath", rs.getString("SmallPath"));
				managersList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(managersList);
		
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	
}
