package com.qzgf.NetStore.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzgf.NetStore.dao.IUserDAO;
import com.qzgf.NetStore.persistence.User;
import com.qzgf.NetStore.pub.Page;
import com.qzgf.NetStore.pub.UtilDB;
import com.qzgf.NetStore.pub.wlglException;
public class UserDAO extends BaseDao implements IUserDAO{
	//UtilDB du = new UtilDB();
	
	//根据userId获得该用户信息
	public User login(String userId) {
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql="select * from t_User where userId='"+userId+"'";
		
		
		User user=null;
		try {
			ResultSet rs=utilDB.executeQuery(sql);
			while(rs.next()){
				user=new User();
				//把一些用户的基本信息保存起来,用于session信息
				user.setUserPwd(rs.getString("userPwd"));
				user.setIfUsable(rs.getString("ifUsable"));
				user.setUserId(userId);
				user.setRealName(rs.getString("realName"));
                return user;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	//沈伟庆０８.０８.２７
	//显示所有列表
	@SuppressWarnings("unchecked")
	@Override
	public Page userList(int npage) {
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		
		
		
		
		
		
		
		String sql="select  *  from  t_user";

		Page page=utilDB.executeQueryByPageForMySql(sql,npage,Page.DEFAULT_PAGESIZE);
		
		ResultSet rs=page.getRowset();
	    List results = new ArrayList();	

		try {
			
			while(rs.next()){
				//Review review=new Review();
				Map map=new HashMap();
				map.put("userId", rs.getString("userId"));
				map.put("realName", rs.getString("realName"));
				map.put("registerTime", rs.getTimestamp("registerTime"));
				map.put("ifUsable", rs.getString("ifUsable"));
				results.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		page.setResultList(results);	
		
		
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	public void updateUsable(String state,String id){
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		
		
		
		
		
		String sql="update t_user set IfUsable="+state+"  where UserId='"+id+"'";
		//try {
			utilDB.executeUpdate(sql);
	//	} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	///	}
			
			try {
				utilDB.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	//点击查看明细
	@SuppressWarnings("unchecked")
	public  List  onlyUserList(String uId)
	{
		
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		
		
		
		
		String sql="select  *  from  t_user where userId='"+uId+"'";
		ResultSet rs=null;
	    List results = new ArrayList();	
		try {
			rs = utilDB.executeQuery(sql);
			while(rs.next()){
				User user=new User();
			
				user.setUserId(rs.getString("UserId"));
				//user.setUserName(rs.getString("userName"));
				//
				user.setSex(rs.getString("sex"));
				user.setRealName(rs.getString("realName"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));//固定电话
				user.setCellPhone(rs.getString("cellPhone"));//手机
				user.setAddress(rs.getString("address"));//详细地址
				
				user.setRegisterTime(rs.getTimestamp("registerTime"));	
				user.setIdCard(rs.getString("IdCard"));
				user.setPostCode(rs.getString("postCode"));
				user.setIntroduce(rs.getString("introduce"));
				user.setAge(rs.getString("age"));
				results.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;	
	}
	
	/**
	 * 用户注册
	 * @param user 用户持久化类
	 * @return
	 */
	public boolean userRegister(User user){
		UtilDB utilDB=null;
    	try {
			 utilDB= new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		
		
		
		StringBuffer sql=new StringBuffer("insert into t_user(userId,userPwd,ifUsable,email)values('"+user.getUserId()+"','");
		sql.append(user.getUserPwd()+"',"+user.getIfUsable()+",'"+user.getEmail()+"')");
		
	
		boolean tf=utilDB.executeUpdate((sql.toString()));
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tf;
		
		
		
	}
	
	//判断用户ID是否存在
	@SuppressWarnings("unchecked")
	public boolean checkUserId(String userid) {
		String strsql="select * from t_User where userid='"+userid+"'";
		List userList=this.findBySql(strsql);
		if(userList.size()>0){
			//已经存在该用户ID 
			return true;
		}
		else{
			//不存在该用户ID
			return false;
		}
	}

}
