package com.qzgf.NetStore.service.impl;

import java.util.List;

import com.qzgf.NetStore.dao.ISendModeDAO;
import com.qzgf.NetStore.persistence.SendMode;
import com.qzgf.NetStore.service.ISendModeService;

public class SendModeService implements ISendModeService {
    private ISendModeDAO sendModeDAO;
	public ISendModeDAO getSendModeDAO() {
		return sendModeDAO;
	}
	public void setSendModeDAO(ISendModeDAO sendModeDAO) {
		this.sendModeDAO = sendModeDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List querySendMode() {
		// TODO Auto-generated method stub
		return this.sendModeDAO.querySendMode();
	}

	public boolean updateSendMode(SendMode sendMode){
		return this.sendModeDAO.updateSendMode(sendMode);
	}
	
	public boolean deleteSendMode(String sendModeId){
		return this.sendModeDAO.deleteSendMode(sendModeId);
	}
	public boolean addSendMode(SendMode sendMode){
		return this.sendModeDAO.addSendMode(sendMode);
	}
}
