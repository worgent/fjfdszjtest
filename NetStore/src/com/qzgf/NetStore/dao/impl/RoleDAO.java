package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.qzgf.NetStore.dao.IRoleDAO;
import com.qzgf.NetStore.persistence.Menu;
import com.qzgf.NetStore.persistence.Role;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;

public class RoleDAO  extends BaseDao  implements IRoleDAO {
	
	//UtilDB du = new UtilDB();
	/** 
	 * 返回所有的菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List queryRoles()
	{
		List list=new ArrayList();
		String strhql="  from Role ";
		List result=this.find(strhql);
		Iterator it=result.iterator();  
		while(it.hasNext())
		{
			list.add((Role)it.next());
		}
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Page queryRoles(int npage){
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String sql = "select * from t_role";
		List allRoles = new ArrayList();
		Page page=null;

		try {
			page=utilDB.executeQueryByPageForMySql(sql.toString(), npage, Page.DEFAULT_PAGESIZE);
			ResultSet rs=page.getRowset();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("roleId", rs.getString("roleId"));
				map.put("roleName", rs.getString("roleName"));
				allRoles.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(allRoles);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return page;
    }
	
	@SuppressWarnings("unchecked")
	public Page queryAllNews(int npage) {
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		String sql = "select * from t_news";
		List allNews = new ArrayList();
		Page page=null;

		try {
			page=utilDB.executeQueryByPageForMySql(sql.toString(), npage, Page.DEFAULT_PAGESIZE);
			ResultSet rs=page.getRowset();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("newsId", rs.getString("NewsId"));
				map.put("newsTitle", rs.getString("NewsTitle"));
				map.put("releaseMan", rs.getString("ReleaseMan"));
				map.put("releaseTime", rs.getString("ReleaseTime"));
				allNews.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setResultList(allNews);
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return page;
	}
	
	/**
	 * 根据厂家ID号查询这个菜单的信息,可用做修改用
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map queryRoleById(String id){
		Map result = new HashMap();
		int sid=Integer.valueOf(id);
		Role role=(Role) this.getByPk(Role.class, sid);
		result.put("roleId", role.getRoleId());	
		result.put("roleName", role.getRoleName());	
		return result;
	}
	
	/**
	 * 更新某个菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean updateRole(Role mfr){
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
	public boolean addRole(Role mfr){
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
	public boolean deleteRole(String id){
		boolean result=false;
		try
		{
			Integer sid=Integer.valueOf(id);
			this.delete(Role.class, sid);
			result=true;
		}
		catch(Exception e)
		{
			
		}
		return result;	
	}
}
