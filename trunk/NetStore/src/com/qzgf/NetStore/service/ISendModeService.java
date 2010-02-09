package com.qzgf.NetStore.service;

import java.util.List;

import com.qzgf.NetStore.persistence.SendMode;

public interface ISendModeService {
    @SuppressWarnings("unchecked")
	public List querySendMode();
    public boolean updateSendMode(SendMode sendMode);
    public boolean deleteSendMode(String sendModeId);
    public boolean addSendMode(SendMode sendMode);
}
