package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.ISearchDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class SearchDAO extends BaseDao implements ISearchDAO {
	//UtilDB du = new UtilDB();

	// ////////////////商品搜索
	@SuppressWarnings("unchecked")
	public Page searchProductList(String npage, String searchStr,
			String catalogId) {
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

		String sql = "select  *  from  t_product where ";
		if (!"".equals(searchStr)) {
			sql = sql + "    productName like '%" + searchStr
					+ "%'  and IsRelease=1  order by  DisplayDate desc ";
		}
		else
		{
			sql = sql + " 1=0 ";
		}

	

		Page page = utilDB.executeQueryByPageForMySql(sql, Integer.parseInt(npage),
				Page.DEFAULT_PAGESIZE);

		System.out.println(sql);

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
			 utilDB= new UtilDB();
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

	@SuppressWarnings("unchecked")
	public List pdtCatalog() {
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		

		ResultSet rs = null;
		String sql = "select * from  t_productcatalog where parentId='0'";
		System.out.println(sql);
		List results = new ArrayList();
		try {
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
		
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return results;
	}

}
