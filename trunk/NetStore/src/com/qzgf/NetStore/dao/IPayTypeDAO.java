package com.qzgf.NetStore.dao;

import java.util.List;
import com.qzgf.NetStore.persistence.PayType;

public interface IPayTypeDAO {
	@SuppressWarnings("unchecked")
	public List queryPayType();
	public boolean updatePayType(PayType payType);
	public boolean deletePayType(String payTypeId);
	public boolean addPayType(PayType payType);
}
