package com.qzgf.NetStore.dao;

public interface ITreatyDAO {
	public boolean addTreaty(String treatyContent);
	public boolean updateTreaty(String treatyContent);
	public String getTreaty();
	public int getTreatyNumById();
}
