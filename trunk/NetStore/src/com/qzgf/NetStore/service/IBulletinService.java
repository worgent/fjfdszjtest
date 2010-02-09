package com.qzgf.NetStore.service;

public interface IBulletinService {
	public boolean addBulletin(String bulletinContent);
	public boolean updateBulletin(String bulletinContent);
	public String getBulletin();
	public int getBulletinNumById();
}
