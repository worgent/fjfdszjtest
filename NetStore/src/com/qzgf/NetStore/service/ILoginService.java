package com.qzgf.NetStore.service;

import java.util.List;

import com.qzgf.NetStore.persistence.Administrator;

public interface ILoginService {
	

@SuppressWarnings("unchecked")
public List LoginIsSuccess(String mm,String userId);

@SuppressWarnings("unchecked")
public List getTopMenu(String root,Administrator ad);

@SuppressWarnings("unchecked")
public List getMenulist();

@SuppressWarnings("unchecked")
public List getModulelist();

public void getLeftMenuList(String mlbh,Administrator ad);

public void setLeftMenuList(String mlbh,Administrator ad);

public void setModuleList(String mlbh,Administrator ad) ;
	
public String getModulebyID(String mkbh) ;

public int GetUserQx(String jgbh, String jsbh, String mkbh);
public String getQx();
}
