package com.qzgf.NetStore.dao;

import java.util.List;


public interface IProductModifyDAO {
	@SuppressWarnings("unchecked")
	public List showOnlyProduct(String pId);//产品列表点击到修改文件
	
	@SuppressWarnings("unchecked")
	public List showProductBig();//产品大类列表
	
	
	@SuppressWarnings("unchecked")
	public List showUnit();//单位列表
	
	
	//@SuppressWarnings("unchecked")
	//public void updateProduct(Product  product);//单位列表
	
	public String getGljgSimpleTree(String id);
	
	public String getProductName(String id);
	
	@SuppressWarnings("unchecked")
	public List showManufacturer();  //供应商
	

	
	

	
}
