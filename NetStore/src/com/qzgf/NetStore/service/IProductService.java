package com.qzgf.NetStore.service;




import com.qzgf.NetStore.pub.Page;

public interface IProductService {

	public Page showProducts(int nPage);
	public Page searchProducts(int nPage,String searchStr,String SearchType);
	
	public void delete(String pId,String smallPicture,String bigPicture,String pathStr);
	
	
}
