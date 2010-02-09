package com.qzgf.NetStore.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.qzgf.NetStore.dao.IUnitDAO;

import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
import com.qzgf.NetStore.service.IUnitService;

public class UnitService  implements IUnitService{
	
	
	//UtilDB du = new UtilDB();
	
	private IUnitDAO unitDAO;

	@SuppressWarnings("unchecked")
	public List unitList()
	{
		return  this.unitDAO.unitList();
	}
	
	//Ìí¼Ó
	public void addUnit(String unitName)
	{
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
 	 String insertStr="insert into t_unit(UnitName)  values('"+unitName+"')";	
 	/*try {
		utilDB.executeUpdate(insertStr);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	*/
 	 
 	utilDB.executeUpdate(insertStr);
 	try {
		utilDB.closeCon();
	} catch (wlglException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 	
	}

	
	//É¾³ý
	public void delete(String uId)
	{
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		 String delStr="delete from t_unit  where unitId='"+uId+"'";
		// try {
			 utilDB.executeUpdate(delStr);
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
	
	//¸üÐÂ
	public void update(String unitId,String unitName)
	{
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		String updateStr="update t_unit set unitName='"+unitName+"' where unitId="+unitId;
		/* try {
				utilDB.executeUpdate(updateStr);
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
	
	
	
	
	
	public IUnitDAO getUnitDAO() {
		return unitDAO;
	}

	public void setUnitDAO(IUnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}
}
