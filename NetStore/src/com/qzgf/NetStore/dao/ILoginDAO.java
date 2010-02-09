package com.qzgf.NetStore.dao;

import java.util.List;

import com.qzgf.NetStore.persistence.Administrator;
/**
 * 
 * @author fjfdszj
 * 用户登录处理
 * 
 */

public interface ILoginDAO {
	/**
	 * @param userPsw
	 * @param userId
	 * @return List 用户对应的信息
	 */
	public List LoginIsSuccess(String userPsw, String userId);
	/**
	 * 功能：菜单明细获得
	 * @param ad
	 * @param ParentCode
	 * @return
	 */
	public List GetMenu(Administrator ad,String ParentCode);
	/**
	 * 功能：处理顶级菜单
	 * @param mlbh
	 * @param ad
	 * @return
	 */
	public List LoginTopMenu(String mlbh,Administrator ad);
	/**
	 * 
	 * 功能：处理模块菜单
	 * @param mlbh
	 * @param ad
	 * @return
	 */
	public List ModelMenu(String mlbh,Administrator ad);
	/**
	 * 
	 * 功能：处理功能菜单
	 * @param mlbh
	 * @param ad
	 * @return
	 */
	public List FunctionMenu(String mlbh,Administrator ad);
	/**
	 * 
	 * 功能根据模块编号得到名称
	 * @param thecode
	 * @return
	 */
	public String GetModelName(String thecode);
	
}
