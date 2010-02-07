package com.qzgf.NetStore.service.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.qzgf.NetStore.dao.ILoginDAO;


import com.qzgf.NetStore.persistence.Administrator;
import com.qzgf.NetStore.persistence.Cdmlb;
import com.qzgf.NetStore.persistence.Gnmkb;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
import com.qzgf.NetStore.service.ILoginService;
import com.qzgf.NetStore.util.context.LoginContextUtil;

import javax.servlet.http.HttpSession;



public class LoginService implements ILoginService {
	private ILoginDAO loginDAO;
	
	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	@SuppressWarnings("unchecked")
	public List LoginIsSuccess(String mm, String userId) {
		return this.loginDAO.LoginIsSuccess(mm, userId);
	}
	/////////////////sssssssssssssssss
	//UtilDB du = new UtilDB();

	private String adminId;

	private String jsbh;

	@SuppressWarnings("unchecked")
	private List menulist;

	@SuppressWarnings("unchecked")
	private List modulelist;

	@SuppressWarnings("unchecked")
	//顶极菜单生成
	public List getTopMenu(String root,Administrator ad) {
		List FinResult=null;
		FinResult=this.loginDAO.LoginTopMenu(root, ad);
		return FinResult;
	}  
    //设置功能菜单，模块菜单。  
	public void getLeftMenuList(String mlbh,Administrator ad) {
		setModuleList(mlbh,ad);
		setLeftMenuList(mlbh,ad);
	}

	@SuppressWarnings("unchecked")
	public void setLeftMenuList(String mlbh,Administrator ad) {
		this.menulist=this.loginDAO.FunctionMenu(mlbh, ad);
	}
 
	@SuppressWarnings("unchecked")
	public void setModuleList(String mlbh,Administrator ad) {
		this.modulelist=this.loginDAO.ModelMenu(mlbh, ad);
	}

	public String getModulebyID(String mkbh) {
		String FinValue=this.loginDAO.GetModelName(mkbh);
		return FinValue;
	}

	@SuppressWarnings("unchecked")
	public List getMenulist() {
		return menulist;
	}

	@SuppressWarnings("unchecked")
	public List getModulelist() {	
		return modulelist;
	}
	
	public String getRoleMoudleTree() {
		String xml="[" + getTree("0         ","") + "]";
		return xml;
	}

	private String getTree(String tempId, String tempStr) {
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String sql = "";
		try {
			sql = "select * from v_mltree where parentid = '" + tempId
					+ "' order by seq";
			ResultSet rs = utilDB.executeQuery(sql);
			
			while (rs.next()) {
				tempId = rs.getString("id");
				tempStr += "{";
				tempStr += "\"id\":\"" + tempId + "\"";
				tempStr += ",\"text\":\"" + rs.getString("text") + "\"";
				if(rs.getString("leaf").equals("N"))  {
				   tempStr += ",\"leaf\":false,\"children\":[";
				   tempStr = getTree(tempId, tempStr);
				   tempStr += "]";
				} else {
					tempStr += ",\"leaf\":true";
					sql="select * from T_JSMKQXB where adminId='" + adminId + "' and c_jsbh='" + jsbh + "' and c_mkbh='" + tempId + "'";
					//ResultSet rs1 = du.stmtTwo.executeQuery(sql);
					ResultSet rs1 = utilDB.executeQuery(sql);
					
					if(rs1.next()) 
						tempStr += ",\"checked\":true";
					else
						tempStr += ",\"checked\":false";
					rs1.close();
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
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tempStr;
	}
	

	///////////权限
	private String qx = "0";
	public int GetUserQx(String adminId, String jsbh, String mkbh) {
		
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 

		 int status = 0;

		 String error = "";


		String sql = "select * from T_JSMKQXB where adminId='" + adminId
				+ "' and C_JSBH='" + jsbh + "' and C_MKBH='" + mkbh + "'";

		try {
			//ResultSet rs = du.stmt.executeQuery(sql);
			ResultSet rs = utilDB.executeQuery(sql);
			
			if (rs.next()) {
				qx = rs.getString("C_CZQX");
				rs.close();
				status = 0;
			} else {
				rs.close();
				qx = "0";
				status = 1;
				error = "用户没有权限";
			}
		} catch (SQLException e) {
			qx = "0";
			status = 1;
			error = e.getMessage();
			System.out.println(e.getMessage());
		}
	
	  try {
		utilDB.closeCon();
	} catch (wlglException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		
		
	return status;
	}

	public String getQx() {
		return qx;
	}

	public void setQx(String qx) {
		this.qx = qx;
	}




	

}
