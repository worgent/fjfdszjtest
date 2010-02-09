package com.qzgf.NetStore.service;

import java.util.List;

public interface ISpecificationService {
	@SuppressWarnings("unchecked")
	public List specList();
	
	// Ìí¼Ó
	public void addSpec(String specName);
	
	//É¾³ý
	public void deleteSpec(String specId);
	
	//¸üÐÂ
	public void updateSpec(String specId,String specName);
	
	

}
