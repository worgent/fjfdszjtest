package com.qzgf.NetStore.service;

import java.util.List;

import com.qzgf.NetStore.persistence.PayType;

public interface IPayTypeService {
	@SuppressWarnings("unchecked")
	public List queryPayType();
	public boolean updatePayType(PayType payType);
	public boolean deletePayType(String payTypeId);
	public boolean addPayType(PayType payType);
}
