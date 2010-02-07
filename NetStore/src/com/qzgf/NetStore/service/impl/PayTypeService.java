package com.qzgf.NetStore.service.impl;

import java.util.List;

import com.qzgf.NetStore.dao.IPayTypeDAO;
import com.qzgf.NetStore.persistence.PayType;
import com.qzgf.NetStore.service.IPayTypeService;

public class PayTypeService implements IPayTypeService {
    @SuppressWarnings("unused")
	private IPayTypeDAO payTypeDAO;
	@Override
	public boolean addPayType(PayType payType) {
		return this.payTypeDAO.addPayType(payType);
	}

	@Override
	public boolean deletePayType(String payTypeId) {
		return this.payTypeDAO.deletePayType(payTypeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryPayType() {
		return this.payTypeDAO.queryPayType();
	}

	@Override
	public boolean updatePayType(PayType payType) {
		return this.payTypeDAO.updatePayType(payType);
	}

	public IPayTypeDAO getPayTypeDAO() {
		return payTypeDAO;
	}

	public void setPayTypeDAO(IPayTypeDAO payTypeDAO) {
		this.payTypeDAO = payTypeDAO;
	}

}
