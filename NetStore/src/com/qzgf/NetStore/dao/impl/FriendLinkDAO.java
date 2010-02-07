package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IFriendLinkDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class FriendLinkDAO   extends BaseDao implements IFriendLinkDAO{
	//UtilDB du = new UtilDB();
	
	@SuppressWarnings("unchecked")
	public Page friendLinkList(int nPage)
	{
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String sql="Select * from t_friendlink order by no ";

		Page page=utilDB.executeQueryByPageForMySql(sql,nPage,Page.DEFAULT_PAGESIZE);
		
		ResultSet rs=page.getRowset();
	    List results = new ArrayList();	

		try {
			
			while(rs.next()){
				
				Map map=new HashMap();
				map.put("urlId", rs.getString("urlId"));
				map.put("urlTitle", rs.getString("urlTitle"));
				map.put("url", rs.getString("url"));
				map.put("no", rs.getString("no"));
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
	
	
	// Ìí¼Ó
	public void addFriendLink(String urlID,String urlTitle,String url,String no)
	{
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		 String insertStr="insert into t_friendlink(urlID,urlTitle,url,no)  values('"+urlID+"','"+urlTitle+"','"+url+"',"+no+")";	
		 	//try {
				//du.stmt.executeUpdate(insertStr);
		 		utilDB.executeUpdate(insertStr);
		 		
				
		/*	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		 		
		 		try {
					utilDB.closeCon();
				} catch (wlglException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	//É¾³ý
	public void delete(String urlID)
	{
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		 String deleteStr="delete from t_friendlink where urlId='"+urlID+"'";	
		 	//try {
				utilDB.executeUpdate(deleteStr);
			//} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
			
				try {
					utilDB.closeCon();
				} catch (wlglException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			
	}
	
	public void update(String urlId,String urlTitle,String url,String no){
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String updateStr="update t_friendlink set urlTitle='"+urlTitle+"',url='"+url+"',no="+no+" where urlId='"+urlId+"'";
		/* try {
				du.stmt.executeUpdate(updateStr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		utilDB.executeUpdate(updateStr);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
	}
		
}
