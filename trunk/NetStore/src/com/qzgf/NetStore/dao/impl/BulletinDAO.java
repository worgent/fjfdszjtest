package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qzgf.NetStore.dao.IBulletinDAO;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class BulletinDAO implements IBulletinDAO{
	
	//UtilDB du=new UtilDB();
	
	
	
	
	
	/**
	 * 添加公告
	 * @param bulletinContent
	 * @return
	 */
	public boolean addBulletin(String bulletinContent){
		 UtilDB utilDB=null;
			try {
				utilDB = new UtilDB();
			} catch (wlglException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
		
		String sql="insert into t_bulletin(bulletinId,bulletinContent)values(1,'"+bulletinContent+"')";
		boolean flag=utilDB.executeUpdate(sql);
		
		
		return flag; 
	}
	
	/**
	 * 更新公告
	 * @param treatyContent
	 * @return
	 */
    public boolean updateBulletin(String bulletinContent){
    	
    	UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    	if(null==bulletinContent){
    		bulletinContent="";
    	}
    	StringBuffer sql=new StringBuffer("update t_bulletin set bulletinContent='"+bulletinContent+"' where bulletinId=1");
    	System.out.println("UpdateBulletin_SQL:"+sql.toString());
    	

		
    	return utilDB.executeUpdate(sql.toString());
    }
    
    /**
     * 取得公告的信息
     * @return
     */
    public String getBulletin(){
    	
    	UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    	String sql="select * from t_bulletin where bulletinId=1";
    	System.out.println(sql);
    	try {
			
    		
    		//ResultSet rs=du.stmt.executeQuery(sql);
    		
    		ResultSet rs=utilDB.executeQuery(sql);
			
			
			while(rs.next()){
				String bulletinContent=rs.getString("bulletinContent");
				System.out.println("bulletinContent:"+bulletinContent);
				return bulletinContent;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	return "";
    }
    
    /**
     * 返回拥有此Id的数量
     * @return
     */
    public int getBulletinNumById(){
    	
    	UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    	String sql="select count(*) as num from t_bulletin where bulletinId=1";
    	System.out.println(sql);
    	try {
			//ResultSet rs=du.stmt.executeQuery(sql);
			
    		ResultSet rs=utilDB.executeQuery(sql);
			
			while(rs.next()){
				int num=Integer.parseInt(rs.getString("num"));
				return num;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
    	return 0;
    }
}
