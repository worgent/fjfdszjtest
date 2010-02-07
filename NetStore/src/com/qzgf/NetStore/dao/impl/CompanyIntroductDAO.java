package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qzgf.NetStore.dao.ICompanyIntroductDAO;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class CompanyIntroductDAO extends BaseDao implements ICompanyIntroductDAO {
	
	//UtilDB du=new UtilDB();
	
	@Override
	public String getCompanyIntrContent() {
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		String sql="select * from t_companyIntroduct ";
    	System.out.println(sql);
    	try {
			//ResultSet rs=du.stmt.executeQuery(sql);
    		
    		ResultSet rs=utilDB.executeQuery(sql);
    		
			while(rs.next()){
				String companyContent=rs.getString("companyContent");
				System.out.println("treatyContent:"+companyContent);
				return companyContent;
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

	@Override
	public boolean updateCompanyIntrContent(String CpyContent) {
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(null==CpyContent){
			CpyContent="";
    	}
    	StringBuffer sql=new StringBuffer("update t_companyintroduct set companyContent='"+CpyContent+"'");
    	System.out.println("Updatecompany_SQL:"+sql.toString());
    	
    	
    /*	int num=0;
    	try {
			num=du.stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	return utilDB.executeUpdate(sql.toString());

	}

}
