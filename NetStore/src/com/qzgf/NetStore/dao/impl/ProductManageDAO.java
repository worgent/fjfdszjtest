package com.qzgf.NetStore.dao.impl;

import java.util.List;

import com.qzgf.NetStore.dao.IProductManageDAO;


public class ProductManageDAO  extends BaseDao  implements IProductManageDAO {
	@SuppressWarnings("unchecked")
	public List showProductBig() {
		String strhql="from ProductCatalog where ParentId='0'";
		return this.find(strhql);
	}
}
