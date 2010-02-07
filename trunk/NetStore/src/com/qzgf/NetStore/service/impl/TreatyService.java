package com.qzgf.NetStore.service.impl;

import com.qzgf.NetStore.dao.ITreatyDAO;
import com.qzgf.NetStore.service.ITreatyService;

public class TreatyService implements ITreatyService {
    private ITreatyDAO treatyDAO;
	@Override
	public boolean addTreaty(String treatyContent) {
		return this.treatyDAO.addTreaty(treatyContent);
	}

	@Override
	public String getTreaty() {
		return this.treatyDAO.getTreaty();
	}

	@Override
	public boolean updateTreaty(String treatyContent) {
	    return this.treatyDAO.updateTreaty(treatyContent);	
	}

	public ITreatyDAO getTreatyDAO() {
		return treatyDAO;
	}

	public void setTreatyDAO(ITreatyDAO treatyDAO) {
		this.treatyDAO = treatyDAO;
	}

	public int getTreatyNumById(){
		return this.treatyDAO.getTreatyNumById();
	}
}
