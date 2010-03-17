package com.qzgf.application.systemManage.taglib.domain;

import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

public class ConfigFacadeImpl implements ConfigFacade {
	BaseSqlMapDAO baseSqlMapDAO;

	@SuppressWarnings("unchecked")
	public List findConfigs(){
		System.out.println(baseSqlMapDAO.toString());
		List testList = baseSqlMapDAO.queryForList("ConfigManage.findConfigs", null);
		if (!testList.isEmpty() && testList != null) {
			return testList;
		} else
			return null;
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

}
