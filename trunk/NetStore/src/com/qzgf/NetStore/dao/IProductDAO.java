package com.qzgf.NetStore.dao;


import com.qzgf.NetStore.pub.Page;

public interface IProductDAO {
	
	public Page showProducts(int nPage);
	public Page searchProducts(int nPage,String searchStr,String SearchType);
	
}
