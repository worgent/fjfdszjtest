package com.qzgf.NetStore.dao;

import java.util.Map;

import com.qzgf.NetStore.persistence.Manufacturer;
import com.qzgf.NetStore.pub.Page;

public interface IManufacturerDAO {
	@SuppressWarnings("unchecked")
	public Page queryManufacturers(int npage);
	@SuppressWarnings("unchecked")
	public Map queryManufacturersById(String id);
	public boolean updateManufacturer(Manufacturer mfr);
	public boolean addManufacturer(Manufacturer mfr);
	public boolean deleteManufacturer(String id);
}
