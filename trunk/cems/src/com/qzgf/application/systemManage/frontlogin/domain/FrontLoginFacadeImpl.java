package com.qzgf.application.systemManage.frontlogin.domain;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

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
	
	//得到主键id
	public String getpid(){
		return  baseSqlMapDAO.sequences("T_Archives_User");
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
			String id=baseSqlMapDAO.sequences("T_Archives_User");
			map.put("pidex", id);
			//增加用户信息
			int i=baseSqlMapDAO.update("User.insertUser", map);
			//新增加默认地址信息
			map.put("pischeck", "1");//是否默认
			map.put("pbill_type", 0);//是否审核pcreate_code peditor_code
			map.put("pcreate_code",id);
			map.put("peditor_code",id);
			i=i+baseSqlMapDAO.update("Address.insertAddress", map);
			
			
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
	 * 2010-02-04
	 * 动态菜单生成方式 
	 */
	
	
	public String menu(HashMap map) {
		//取得数据集
		List ls=baseSqlMapDAO.queryForList("User.menu", map);
		
		StringBuffer sb = new StringBuffer();
		//图片路径参数设置
		String imgurl="SlideMenu/Icon/";
		
		int length=ls.size();
		HashMap search = (HashMap) ls.get(0);//每行记录
		sb.append("{text:\""+search.get("NAME").toString()+"\", items:[");
		for(int i=1 ; i<length; i++){
			search = (HashMap) ls.get(i);//每行记录
			if(search.get("LAYERID").toString().trim().equals("1")){
				sb=sb.replace(sb.length()-1, sb.length(), "");
				sb.append("]},{text:\""+search.get("NAME").toString()+"\",items:[");
			}else{
				sb.append("{text:\""+search.get("NAME").toString()+"\", icon:'"+search.get("IMG").toString()+"', url:'"+search.get("URL").toString()+"'},");
			}
		}
		sb=sb.replace(sb.length()-1, sb.length(), "");
		sb.append("]}");
		return sb.toString();
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
