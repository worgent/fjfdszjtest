package com.qzgf.NetStore.service.impl;

import com.qzgf.NetStore.dao.IProductDisplayDAO;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.IProductDisplayService;

public class ProductDisplayService implements IProductDisplayService {

	IProductDisplayDAO productDisplayDAO;
	public IProductDisplayDAO getProductDisplayDAO() {
		return productDisplayDAO;
	}
	public void setProductDisplayDAO(IProductDisplayDAO productDisplayDAO) {
		this.productDisplayDAO = productDisplayDAO;
	}
	public Page queryProductByNews(int npage) {
		return this.productDisplayDAO.queryProductByNews(npage);
	}

}
