package com.qzgf.NetStore.service;

import java.util.List;

public interface IProductManageService {
	@SuppressWarnings("unchecked")
	public List showProductBig();//产品大类列表
	
	// 添加
	public void addProductBig(String productName);
	
	//删除 大类与小类　公用
	public void deleteProductBig(String id);
	
	//更新　大类与小类　公用
	public void updateProductBig(String id,String productName);
	
	//是否有子类
	public String isUseProduct(String id);
	
	
	///////商品小类管理
	@SuppressWarnings("unchecked")
	public List showSmallCatalog(String pid );

	//增加小类
	public void addProductSmall(String pid, String  catalogName);
	
	
	public String catalogNameValue(String id);
	
	
	
	
}
