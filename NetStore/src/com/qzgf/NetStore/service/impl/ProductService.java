package com.qzgf.NetStore.service.impl;

import java.sql.SQLException;

import java.io.*;



import com.qzgf.NetStore.dao.IProductDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
import com.qzgf.NetStore.service.IProductService;

public class ProductService implements IProductService {
	//UtilDB du = new UtilDB();

	private IProductDAO productDAO;

	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@SuppressWarnings("unchecked")
	public Page showProducts(int nPage) {
		return this.productDAO.showProducts(nPage);
	}

	public Page searchProducts(int nPage, String searchStr, String SearchType) {
		return this.productDAO.searchProducts(nPage, searchStr, SearchType);
	}

	public void delete(String pId, String smallPicture, String bigPicture,String pathStr) {
		
		UtilDB utilDB=null;
		try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		String str = "delete from t_product where  ProductId='" + pId + "'";
		//try {
			utilDB.executeUpdate(str);

			 // ServletContext   servletContext   =   pageContext.getServletContext();   
			 // String   realPath   =servletContext.getRealPath("/");
			  String smallName=pathStr+"\\smallPic\\"; 
			  
			String path = new String(smallName + smallPicture);
			
			
			boolean flag = (new File(path)).delete();
			System.out.println(flag);
			String bigName=pathStr+"\\bigPic\\"; 
			String pathBig = new String(bigName + bigPicture);
			
			
			boolean flagBig = (new File(pathBig)).delete();
			System.out.println(flagBig);

		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
