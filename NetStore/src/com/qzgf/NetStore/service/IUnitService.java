package com.qzgf.NetStore.service;

import java.util.List;

public interface IUnitService {
	@SuppressWarnings("unchecked")
	public List unitList();
	
	// Ìí¼Ó
	public void addUnit(String unitName);
	
	//É¾³ý
	public void delete(String uId);
	
	public void update(String unitId,String unitName);
	
}
