package com.qzgf.NetStore.dao.impl;
import java.util.List;

import com.qzgf.NetStore.dao.ISpecDAO;


public class SpecDAO  extends BaseDao implements ISpecDAO{
	@SuppressWarnings("unchecked")
	public List specList()
	{
		String strhql="from Specification";
		List specList=this.find(strhql);
		return specList;
	}
	
}
