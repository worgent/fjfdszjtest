package com.qzgf.NetStore.service;

import java.util.List;

import com.qzgf.NetStore.pub.Page;

public interface ISearchService {
	
	
@SuppressWarnings("unchecked")
public  Page searchProductList(String npage,String searchStr,String catalogId);
	
@SuppressWarnings("unchecked")
public List topProduct();
@SuppressWarnings("unchecked")
public List pdtCatalog() ;


}
