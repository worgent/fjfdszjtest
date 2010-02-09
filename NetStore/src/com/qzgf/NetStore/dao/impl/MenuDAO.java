package com.qzgf.NetStore.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IMenuDAO;
import com.qzgf.NetStore.persistence.Menu;

public class MenuDAO  extends BaseDao implements IMenuDAO {	
	/** 
	 * 返回所有的菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryMenus(){
			List list=new ArrayList();
			String strhql="  from Menu ";
			List result=this.find(strhql);
			Iterator it=result.iterator();  
			while(it.hasNext())
			{
				list.add((Menu)it.next());
			}
			return list;
    }
	
	/**
	 * 根据厂家ID号查询这个菜单的信息,可用做修改用
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map queryMenuById(String id) {
		Map result = new HashMap();;
		Menu menu=(Menu) this.getByPk(Menu.class, id);
		result.put("theCode", menu.getTheCode());	
		result.put("theName", menu.getTheName());	
		result.put("theOrderId", menu.getTheOrderId());	
		result.put("theParentCode", menu.getTheParentCode());	
		result.put("theRemark", menu.getTheRemark());	
		result.put("theUrl", menu.getTheUrl());	
		return result;
	}
	/**
	 * 更新某个菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean updateMenu(Menu mfr){
		boolean result=false;
		try
		{
			this.update(mfr);
			result=true;
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	
	/**
	 * 添加某一菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean addMenu(Menu mfr){
		boolean result=false;
		try
		{
			this.create(mfr);
			result=true;
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	
	/**
	 * 根据编号删除某一菜单
	 * @param id
	 * @return
	 */
	public boolean deleteMenu(String id){
		boolean result=false;
		try
		{
			this.delete(Menu.class, id);
			result=true;
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
}
