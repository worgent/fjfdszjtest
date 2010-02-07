package com.qzgf.NetStore.service;

import java.util.List;



public interface IProductModifyService {
	@SuppressWarnings("unchecked")
	public List showOnlyProduct(String pId);
	
	
	@SuppressWarnings("unchecked")
	public List showProductBig();//产品大类列表
	
	
	@SuppressWarnings("unchecked")
	public List showUnit();//单位列表
	
	@SuppressWarnings("unchecked")
	public List showProductList();
	
	public String getGljgSimpleTree(String id);
	
	public String getProductName(String id);
	
	//更新商品信息
	public void updateProduct(String ProductId,String Productname,String CatalogId,
 			String ProductDate,String ManufacturerID,
 			String IfCommend,String MarketPrice,String MemberPrice,String Brand,
 			String ProductIntro,String SmallPath,String BigPath,
 			String Specification,String UnitId,String Stock,String isRelease,
 			String isNewGoods,String isSpecialPrice);
	
	@SuppressWarnings("unchecked")
	public List showManufacturer(); 
	
	
	//添加保存
 	public void addSave(String ProductId,String Productname,String CatalogId,
 			String ProductDate,String ManufacturerID,
 			String IfCommend,String MarketPrice,String MemberPrice,String Brand,
 			String ProductIntro,String SmallPath,String BigPath,
 			String Specification,String UnitId,String Stock,String isRelease,
 			String isNewGoods,String isSpecialPrice);
 	
 	@SuppressWarnings("unchecked")
	public List  showBrand();
 	
	
}
