package com.qzgf.NetStore.dao.impl;

import java.util.List;

import com.qzgf.NetStore.dao.IProductModifyDAO;
import com.qzgf.NetStore.persistence.*;



public class ProductModifyDAO  extends BaseDao implements IProductModifyDAO{
	@SuppressWarnings("unchecked")
	public List showOnlyProduct(String pId) {
		String strhql="from Product where  ProductId='"+pId+"'";
	    
		return this.find(strhql);
	}

	@SuppressWarnings("unchecked")
	@Override
	//商品大类
	
	
	public List showProductBig() {
		String strhql="from ProductCatalog where ParentId='0' order by id";
		//List prdTree=this.find(strhql);
		
		
		
		return this.find(strhql);
	}
	
	@SuppressWarnings("unchecked")
	public List showUnit()
	{
		String strhql="from Unit";
		return this.find(strhql);
	}
	public String getGljgSimpleTree(String id)
	{
		return "";
	}
	
	
	//获得　商品分类名称
	@SuppressWarnings("unchecked")
	public String getProductName(String id)
	{
		String strhql="from ProductCatalog where id='"+id+"'";
	    List prdList= this.find(strhql);
	    ProductCatalog prdCatalog=(ProductCatalog)prdList.get(0);
		return prdCatalog.getCatalogName();
	}
	
	@SuppressWarnings("unchecked")
	public List showManufacturer()
	{
		String strhql="from  Manufacturer";
		return this.find(strhql);
	}
	

/*	public void updateProduct(Product  product)
	{
	    this.update(product);
	}*/
	
}
