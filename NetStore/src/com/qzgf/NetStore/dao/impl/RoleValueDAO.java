package com.qzgf.NetStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.qzgf.NetStore.dao.IRoleValueDAO;
import com.qzgf.NetStore.persistence.RoleValue;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
import com.qzgf.NetStore.util.node.Node;


public class RoleValueDAO  extends BaseDao  implements IRoleValueDAO {	
	/** 
	 * 返回所有的菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryRoleValues(String roleid){
		List list=new ArrayList();
		String strhql="  from RoleValue where roleId='"+roleid+"'";
		List result=this.find(strhql);
		Iterator it=result.iterator();  
		while(it.hasNext())
		{
			list.add((RoleValue)it.next());
		}
		return list;
    }
	/**
	 * 菜单值
	 */
	@SuppressWarnings("unchecked")
	public List queryChileRoleValues(String roleid,String root)
	{
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		List list=new ArrayList();
		String strsql="select a.thecode id,a.theName title,a.theParentCode superId,b.FunisShow checked " +"\n"+
				"  from t_menu a left outer join t_RoleValue b on a.thecode=b.menucode " +"\n"+
				" where b.roleId='"+roleid+"' order by a.theParentCode,a.thecode,a.theOrderId" ;// and theParentCode='"+root+"'

		try {
			ResultSet rs=utilDB.executeQuery(strsql);
			while(rs.next()){
				Node nodes=new Node();
				nodes.setId(rs.getString("id"));
				nodes.setSuperId(rs.getString("superId"));
				nodes.setTitle(rs.getString("title"));
				String t1=rs.getString("checked");
				nodes.setChecked(Integer.valueOf(t1));
				nodes.setChecked(Integer.valueOf(rs.getString("checked")));
				list.add(nodes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
	
	/**
	 * 根据厂家ID号查询这个菜单的信息,可用做修改用
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map queryRoleValueById(Integer id){
		Map result = new HashMap();
		RoleValue roleValue=(RoleValue) this.getByPk(RoleValue.class, id);
		result.put("id", roleValue.getId());
		result.put("roleId", roleValue.getRoleId());	
		result.put("menuCode", roleValue.getMenuCode());
		result.put("funIsShow", roleValue.getFunisShow());
		result.put("powerValue",roleValue.getPowerValue());
		result.put("remark", roleValue.getRemark());
		return result;
	}
	
	/**
	 * 更新某个菜单的信息
	 * @param mfr
	 * @return
	 */
	public boolean updateRoleValue(RoleValue mfr){
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
	public boolean addRoleValue(RoleValue mfr){
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
	public boolean deleteRoleValue(Integer id){
		boolean result=false;
		try
		{
			this.delete(RoleValue.class, id);
			result=true;
		}
		catch(Exception e)
		{
			
		}
		return result;	
	}
	
	/**
	 * 根据某一角色的权限值
	 * @param id
	 * @return
	 */
	public boolean deleteAllRoleValue(String rname,String rid){
		boolean result=false;
		try
		{
			this.deleteAll(RoleValue.class,rname, rid);
			result=true;
		}
		catch(Exception e)
		{
			
		}
		return result;	
	}
}
