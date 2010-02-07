package com.qzgf.NetStore.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.qzgf.NetStore.dao.ILoginDAO;


import com.qzgf.NetStore.persistence.Administrator;
import com.qzgf.NetStore.persistence.Menu;
import com.qzgf.NetStore.util.MD5Code;

public class LoginDAO extends BaseDao  implements ILoginDAO {
	@SuppressWarnings("unchecked")
	@Override
	//登录用户组信息
	public List LoginIsSuccess(String userPsw, String userId) {
		MD5Code md5 = new MD5Code() ;
		String encodestr =md5.getMD5ofStr(userPsw);
		String strhql="from Administrator where adminId=? and password=? and ifuse=1";
		String strarry[]={userId,encodestr};
		List FinAdmin=this.find(strhql,strarry);
		return FinAdmin;
	}
	
	@SuppressWarnings("unchecked")
	public List GetMenu(Administrator ad,String ParentCode)
	{
		String strhql="select a.*  from t_Menu a " +"\n"+
				"left outer join t_RoleValue b on a.theCode=b.menucode " +"\n"+
				"left outer join t_Role c on b.roleid=c.roleId " +"\n"+
				"left outer join t_Administrators d on d.roleId=c.roleId " +"\n"+
				"where d.adminId=? and a.theParentCode=? and b.FunisShow=1 order by a.theOrderId";
		
		String strarry[]={ad.getAdminId().toString(),ParentCode};
		//List HqlFinMenu=this.findBySql(strhql,strarry);
		List HqlFinMenu=this.findBySql(strhql,strarry,Menu.class);
		List FinMenu= new ArrayList();
		Iterator<Menu> it = HqlFinMenu.iterator();
		while (it.hasNext()) {
			Menu menu = new Menu();
			Object tmp=it.next();
			menu=(Menu)tmp;
			FinMenu.add(menu);
		}
		//List FinMenu=this.find(strhql,strarry);
		return FinMenu;	
	}
	// 顶极菜单
	public List LoginTopMenu(String mlbh, Administrator ad) {
		List FinMenu= new ArrayList();
		FinMenu=GetMenu(ad,mlbh);	
		return FinMenu;
	}
	//模块菜单
	public List ModelMenu(String mlbh,Administrator ad) {
		List FinMenu= new ArrayList();//=GetMenu(ad,mlbh);
		FinMenu=LoginTopMenu(mlbh,ad);
		return FinMenu;
	}
	//功能菜单
	public List FunctionMenu(String mlbh,Administrator ad) {
		List FinMenu= new ArrayList();
		List topmenu=ModelMenu(mlbh,ad);
		Iterator it=topmenu.iterator();  
		while(it.hasNext())
		{
			//Iterator ititem= GetMenu(ad,((Menu)it).get(0).toString()).iterator();
			Iterator ititem= GetMenu(ad,((Menu)it.next()).getTheCode()).iterator();
			while(ititem.hasNext())
				FinMenu.add((Menu)ititem.next());
		}
		return FinMenu;
	}
	
	public String GetModelName(String thecode)
	{
		String FinStr="";
		String strhql="from Menu a where a.theCode=?";
		
		String strarry[]={thecode};
		//List HqlFinMenu=this.findBySql(strhql,strarry);
		List HqlFinMenu=this.find(strhql,strarry);
		Iterator it = HqlFinMenu.iterator();
		if (it.hasNext()) {
			Menu menu = new Menu();
			menu=(Menu)it.next();
			if(menu.getTheUrl()!=null)
			{
				FinStr=menu.getTheUrl();
			}
		}
		return FinStr;	
	}

}
