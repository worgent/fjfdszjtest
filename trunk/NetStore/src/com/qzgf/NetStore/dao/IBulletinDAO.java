package com.qzgf.NetStore.dao;

public interface IBulletinDAO {
	public boolean addBulletin(String bulletinContent);
	public boolean updateBulletin(String bulletinContent);
	public String getBulletin();
	public int getBulletinNumById();
}
