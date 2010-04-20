package com.qzgf.application.archives.address.domain;
 

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 网上下单接口
 *
 */
public interface AddressFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertAddress(HashMap map);
	
	public List findUser(HashMap map);

	public  int updateUserAddressById(HashMap map);
	
	public abstract int countAddress(HashMap map);
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteAddressById(HashMap map);

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateAddressById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findAddress(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findAddressPage(HashMap map,Pages pages);
	

	//保证默认地址唯一性
	public int addressCheck(HashMap map);
	
}