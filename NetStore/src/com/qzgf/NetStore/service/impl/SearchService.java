package com.qzgf.NetStore.service.impl;


import java.util.List;

import com.qzgf.NetStore.dao.ISearchDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.ISearchService;

public class SearchService  implements ISearchService{	
	private ISearchDAO searchDAO;
	public Page searchProductList(String npage, String searchStr,String catalogId) {
		return this.searchDAO.searchProductList(npage, searchStr, catalogId);
	}

	public ISearchDAO getSearchDAO() {
		return searchDAO;
	}

	public void setSearchDAO(ISearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}

	@SuppressWarnings("unchecked")
	public List topProduct(){
		return this.searchDAO.topProduct();
	}
	
	@SuppressWarnings("unchecked")
	public List pdtCatalog() {
		return this.searchDAO.pdtCatalog();	
	}
}
