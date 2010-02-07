package com.qzgf.NetStore.dao;

import java.util.List;

import com.qzgf.NetStore.persistence.SendMode;

public interface ISendModeDAO {
	@SuppressWarnings("unchecked")
	public List querySendMode();
	public boolean updateSendMode(SendMode sendMode);
	public boolean deleteSendMode(String sendModeId);
	public boolean addSendMode(SendMode sendMode);
}
