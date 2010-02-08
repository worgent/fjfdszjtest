package com.qzgf.application.systemManage.frontlogin.domain;

import java.security.MessageDigest;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 *用户登录与注册
 *
 */
public class FrontLoginFacadeImpl implements FrontLoginFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(FrontLoginFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	/**
	 * 验证用户,同时提用户信息
	 * @param map
	 * @return
	 */
	public  HashMap checkLogin(HashMap map){
		String pwd=map.get("ppasswd").toString();
		HashMap user=(HashMap) baseSqlMapDAO.queryForList("User.findSessionUserById", map).get(0);
		if (user != null) {
			try {
				//对用户提交的密码进行md5加密处理
				MessageDigest md = MessageDigest.getInstance("MD5");
				//pwd=new String(md.digest(pwd.getBytes("GBK")),"GBK");
				BASE64Encoder encoder=new BASE64Encoder();
				pwd=new String (encoder.encode(md.digest(pwd.getBytes())));
				//与数据库中的密码进行比较
				if(pwd.equalsIgnoreCase(user.get("PASSWD").toString().replaceAll(" ","")))
				{
					//后期角色信息加载
					//密码验证成功
					return user;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 用户信息注册
	 * @param map
	 * @return
	 */
	public  boolean regedit(HashMap map){
		boolean result=false;
		//对用户提交的密码进行md5加密处理
		String pwd=map.get("ppasswd").toString();
		try {
			//对用户提交的密码加密
			MessageDigest md= MessageDigest.getInstance("MD5");
			//pwd=new String(md.digest(pwd.getBytes("GBK")),"GBK");
			BASE64Encoder encoder=new BASE64Encoder();
			pwd=new String (encoder.encode(md.digest(pwd.getBytes())));
			map.put("ppasswd", pwd);
			//最后增加数据
			int i=baseSqlMapDAO.update("User.insertUser", map);
			if(i>0)
			{
				result=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 用户是否存在
	 * @param map
	 * @return
	 */
	public  int isExist(HashMap map){
		return  ((Integer)baseSqlMapDAO.queryForObject("User.findUserCount", map)).intValue();
		
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
