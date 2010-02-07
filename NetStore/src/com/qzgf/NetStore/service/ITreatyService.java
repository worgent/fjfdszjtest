package com.qzgf.NetStore.service;

public interface ITreatyService {
	public boolean addTreaty(String treatyContent);
	public boolean updateTreaty(String treatyContent);
	public String getTreaty();
	public int getTreatyNumById();
}
