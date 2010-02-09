package com.qzgf.NetStore.dao.impl;

import java.util.List;

import com.qzgf.NetStore.dao.IUnitDAO;

public class UnitDAO extends BaseDao implements IUnitDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List unitList() {
		String strhql="from Unit";
		List allList=this.find(strhql);
		return allList;
	}

}
