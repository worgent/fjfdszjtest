package com.qzgf.NetStore.service.impl;


import java.util.Map;
import com.qzgf.NetStore.dao.IManagerDAO;
import com.qzgf.NetStore.persistence.Administrator;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.service.IManagerService;

public class ManagerService implements IManagerService {
    IManagerDAO managerDAO;
	@SuppressWarnings("unchecked")
	@Override
	public Page queryAdministrators(int npage){
		return this.managerDAO.queryAdministrators(npage);
	}
	public IManagerDAO getManagerDAO() {
		return managerDAO;
	}
	public void setManagerDAO(IManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}

	@SuppressWarnings("unchecked")
	public Map queryAdministratorById(String id){
		return this.managerDAO.queryAdministratorById(id);
	}
	public boolean updateManager(Administrator admin,boolean flag){
		return this.managerDAO.updateManager(admin, flag);
	}
	
	public boolean deleteManagerByID(String adminId){
		return this.managerDAO.deleteManagerByID(adminId);
	}
	public boolean addAdmin(Administrator admin){
		return this.managerDAO.addAdmin(admin);
	}
	 
	@SuppressWarnings("unchecked") 
	public Map queryAdminById(String id){
		return this.managerDAO.queryAdminById(id);
	}
	
	public boolean updateAdmin(Administrator admin,boolean flag){
		return this.managerDAO.updateAdmin(admin, flag);
	}
	
	public boolean ifPwdEnterRight(String adminId,String adminPwd){
		return this.managerDAO.ifPwdEnterRight(adminId, adminPwd);
	}
}
