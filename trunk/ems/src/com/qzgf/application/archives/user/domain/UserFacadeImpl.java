package com.qzgf.application.archives.user.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 用户管理实现类
 *
 */
public class UserFacadeImpl implements UserFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(UserFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertUser(HashMap map){
		return baseSqlMapDAO.update("User.insertUser", map);
	}
	
	@SuppressWarnings("unchecked")
	public int deleteUserById(HashMap map) {
		return baseSqlMapDAO.update("User.deleteUserById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateUserById(HashMap map) {
		return baseSqlMapDAO.update("User.updateUserById", map);
	}
	@SuppressWarnings("unchecked")
	public List findUser(HashMap map) {
		return baseSqlMapDAO.queryForList("User.findUser", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findUserPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("User.findUserCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("User.findUserPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	/**
	 * 修改密码
	 * @param map
	 * @return -1:原密码有误码,1:更新成功,-2:更新异常
	 */
	public  int changePwd(HashMap map){
		int result=0;
		String ppasswd=map.get("ppasswd").toString();
		String curpasswd=map.get("curpasswd").toString().replaceAll(" ", "");
		
		try {
			MessageDigest md= MessageDigest.getInstance("MD5");
			BASE64Encoder encoder=new BASE64Encoder();
			ppasswd=new String (encoder.encode(md.digest(ppasswd.getBytes())));
			if(ppasswd.equalsIgnoreCase(curpasswd)){//原密码正错
				String newpasswd= map.get("pnewpasswd").toString();
				//密码md5加密后,base64处理得到的数据
				newpasswd=new String (encoder.encode(md.digest(newpasswd.getBytes())));
				map.put("ppasswd", newpasswd);
				//更新密码
				result=baseSqlMapDAO.update("User.updateUserById", map);
			}else
			{
				result=-1;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=-2;
		}
		return result;
		
	}	
	
	//================================相关参数==============================
	/**
	 * 参数值
	 * 传入参数 map.put("psort",1);
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List parameterValue(HashMap map){
		return baseSqlMapDAO.parameterValue(map);
	}
	
	//====================================区域信息========================================
	
	/**
	 * 省份
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List ajaxProvince(HashMap map){
		return baseSqlMapDAO.queryForList("User.findProvince", map);
	}
	
	/**
	 * 地市
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List ajaxCity(HashMap map){
		return baseSqlMapDAO.queryForList("User.findCity", map);
	}
	
	/**
	 * 县区
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List ajaxCounty(HashMap map){
		return baseSqlMapDAO.queryForList("User.findCounty", map);
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
