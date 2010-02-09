package com.qzgf.NetStore.service.impl;

import com.qzgf.NetStore.dao.IBulletinDAO;
import com.qzgf.NetStore.service.IBulletinService;

public class BulletinService implements IBulletinService {
    private IBulletinDAO bulletinDAO;
	public IBulletinDAO getBulletinDAO() {
		return bulletinDAO;
	}

	public void setBulletinDAO(IBulletinDAO bulletinDAO) {
		this.bulletinDAO = bulletinDAO;
	}

	@Override
	public boolean addBulletin(String bulletinContent) {
		return this.bulletinDAO.addBulletin(bulletinContent);
	}

	@Override
	public String getBulletin() {
		return this.bulletinDAO.getBulletin();
	}

	@Override
	public int getBulletinNumById() {
		return this.bulletinDAO.getBulletinNumById();
	}

	@Override
	public boolean updateBulletin(String bulletinContent) {
		return this.bulletinDAO.updateBulletin(bulletinContent);
	}


}
