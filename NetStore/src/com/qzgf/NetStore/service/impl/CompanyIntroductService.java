package com.qzgf.NetStore.service.impl;

import com.qzgf.NetStore.dao.ICompanyIntroductDAO;
import com.qzgf.NetStore.service.ICompanyIntroductService;


public class CompanyIntroductService implements ICompanyIntroductService{
	 private ICompanyIntroductDAO companyIntroductDAO;
	 
	@Override
	public String getCompanyIntrContent() {
		
		return this.companyIntroductDAO.getCompanyIntrContent();
	}

	@Override
	public boolean updateCompanyIntrContent(String CpyContent) {
		// TODO Auto-generated method stub
		return this.companyIntroductDAO.updateCompanyIntrContent(CpyContent);
	}

	public ICompanyIntroductDAO getCompanyIntroductDAO() {
		return companyIntroductDAO;
	}

	public void setCompanyIntroductDAO(ICompanyIntroductDAO companyIntroductDAO) {
		this.companyIntroductDAO = companyIntroductDAO;
	}

}
