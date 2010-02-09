package com.qzgf.NetStore.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.qzgf.NetStore.dao.ISpecDAO;
import com.qzgf.NetStore.persistence.Specification;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
import com.qzgf.NetStore.service.ISpecificationService;

public class SpecificationService implements ISpecificationService {
	private ISpecDAO specDAO;

	@SuppressWarnings("unchecked")
	public List specList() {
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
				while(rs.next()){
					Specification spec=new Specification();
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
	
	
	// Ìí¼Ó
	public void addSpec(String specName)
	{
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		 String insertStr="insert into t_specification(specificationName)  values('"+specName+"')";	
		 	//try {
			/*	utilDB.executeUpdate(insertStr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		 		utilDB.executeUpdate(insertStr);
		 		
		 		try {
					utilDB.closeCon();
				} catch (wlglException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 		
	}
	
	//É¾³ý
	public void deleteSpec(String specId)
	{
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String delStr="delete from  t_specification where specificationId='"+specId+"'";
		/* try {
			utilDB.executeUpdate(delStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		utilDB.executeUpdate(delStr);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//¸üÐÂ
	public void updateSpec(String specId,String specName){
		
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		String updateStr="update t_specification set specificationName='"+specName+"' where specificationId="+specId;
		/* try {
				utilDB.executeUpdate(updateStr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	*/
		
		utilDB.executeUpdate(updateStr);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ISpecDAO getSpecDAO() {
		return specDAO;
	}
	public void setSpecDAO(ISpecDAO specDAO) {
		this.specDAO = specDAO;
	}


}
