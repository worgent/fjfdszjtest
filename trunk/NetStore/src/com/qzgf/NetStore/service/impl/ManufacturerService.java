package com.qzgf.NetStore.service.impl;

import java.util.Map;

import com.qzgf.NetStore.dao.IManufacturerDAO;
import com.qzgf.NetStore.persistence.Manufacturer;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.IManufacturerService;

public class ManufacturerService implements IManufacturerService {
    private IManufacturerDAO manufacturerDAO;
	public IManufacturerDAO getManufacturerDAO() {
		return manufacturerDAO;
	}
	public void setManufacturerDAO(IManufacturerDAO manufacturerDAO) {
		this.manufacturerDAO = manufacturerDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page queryManufacturers(int npage) {
		return this.manufacturerDAO.queryManufacturers(npage);
	}

	@SuppressWarnings("unchecked")
	public Map queryManufacturersById(String id){
		return this.manufacturerDAO.queryManufacturersById(id);
	}
	
	public boolean updateManufacturer(Manufacturer mfr){
		return this.manufacturerDAO.updateManufacturer(mfr);
	}
	
	public boolean addManufacturer(Manufacturer mfr){
		return this.manufacturerDAO.addManufacturer(mfr);
	}
	
	public boolean deleteManufacturer(String id){
		return this.manufacturerDAO.deleteManufacturer(id);
	}
}
