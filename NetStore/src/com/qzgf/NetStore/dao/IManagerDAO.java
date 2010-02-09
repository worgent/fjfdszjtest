package com.qzgf.NetStore.dao;

import java.util.Map;
import com.qzgf.NetStore.persistence.Administrator;
import com.qzgf.NetStore.pub.Page;

public interface IManagerDAO {
	@SuppressWarnings("unchecked")
	public Page queryAdministrators(int npage);
	@SuppressWarnings("unchecked")
	public Map queryAdministratorById(String id);
	public boolean updateManager(Administrator admin,boolean flag);
	public boolean deleteManagerByID(String adminId);
	public boolean addAdmin(Administrator admin);
	@SuppressWarnings("unchecked")
	public Map queryAdminById(String id);
	public boolean updateAdmin(Administrator admin,boolean flag);
	public boolean ifPwdEnterRight(String adminId,String adminPwd);
}
