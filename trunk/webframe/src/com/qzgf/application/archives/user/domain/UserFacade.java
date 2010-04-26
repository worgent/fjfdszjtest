package com.qzgf.application.archives.user.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

public interface UserFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/** 
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertT_ARCHIVES_USER(HashMap map);


	/**
	 * ɾ
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteUserById(HashMap map);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateUserById(HashMap map);
	
	@SuppressWarnings("unchecked")
	public abstract int updatePassword(HashMap map);

	/**
	 * ��
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findUser(HashMap map,Pages pages);
	
	@SuppressWarnings("unchecked")
	public abstract PageList findMemberList(HashMap map,Pages pages);
	
	

	
	@SuppressWarnings("unchecked")
	public abstract PageList findOnlyUserList(HashMap map,Pages pages);
	
	@SuppressWarnings("unchecked")
	//public abstract PageList findOnlyUser_ExList(HashMap map,Pages pages);
	
	public abstract PageList isExist(HashMap map,Pages pages);
	
	@SuppressWarnings("unchecked")
	public abstract PageList loginCorrect(HashMap map,Pages pages);
	
	public abstract PageList pwdCorrect(HashMap map,Pages pages);
	
	public abstract PageList selectProvinceList(HashMap map,Pages pages);

	
	
	public abstract PageList selectCityList(HashMap map,Pages pages);//��õ������
	
	public abstract PageList selectDistrictList(HashMap map,Pages pages);//�������
	
	
	

	public  boolean updatePersonal(HashMap map);
	
	public abstract List safeList(HashMap map);
	
	
	//会员
	public PageList merchantExist(HashMap map, Pages pages);
	public  abstract int insertT_Member(HashMap map);
	public   abstract int updateT_Member(HashMap map);
	public  abstract List merchantMemberList(HashMap map);
	public  abstract List editOnlyRecordList(HashMap map);
	public  boolean updateAudit(HashMap map);
	public abstract int deleteMemberOnly(HashMap map);
	
	//会员
	
	//部落创建
	public PageList findTribalList(HashMap map, Pages pages);
	public abstract int insertT_CHAT_RECORD(HashMap map);
	public  abstract List findT_chat_relateList(HashMap map);
	
	public PageList findChatRecordList(HashMap map, Pages pages);
	public PageList findSearchUserList(HashMap map, Pages pages);
	
	public abstract int insertT_CHAT_RELATE(HashMap map);
	public  abstract List findT_chat_relate_ExistMemList(HashMap map);
	public  boolean updateT_CHAT_RELATE(HashMap map);
	
	public abstract PageList isExistCreate(HashMap map,Pages pages);
	
	public abstract int insertT_chat_relate_admin(HashMap map);
	
	public PageList findGroupList(HashMap map, Pages pages);
	
	public  abstract List findChatRelateMainList(HashMap map);
	
	
	//部落创建
	
	
	
	//商品信息
	public PageList findProductList(HashMap map, Pages pages);
	public abstract int insertT_PRODUCT(HashMap map);
	public  abstract List onlyProductList(HashMap map);
	
	public  boolean updateT_PRODUCT(HashMap map);
	public abstract int deleteProduct(HashMap map);
	
	
	//优惠券
	public PageList findFavourableList(HashMap map, Pages pages);
	public abstract int insertT_SELFCONFIG_FAVOURABLE(HashMap map);
	public  abstract List onlyFavourableList(HashMap map);
	public  boolean updateT_SELFCONFIG_FAVOURABLE(HashMap map);
	public abstract int deleteFavourab(HashMap map);
	//优惠券 end
	
	
	//会员申诉
	public abstract int insertT_ARCHIVES_APPEAL(HashMap map);
	public PageList findAppealList(HashMap map, Pages pages);
	public  abstract List onlyAppealRecordList(HashMap map);
	public  boolean updateT_ARCHIVES_APPEAL(HashMap map);
	public abstract int deleteAppeal(HashMap map);
	
	//会员申诉
	
	
	//好友设置
	//public PageList findIsExistUserList(HashMap map, Pages pages);
	public  abstract List findIsExistUserList(HashMap map);
	public  abstract int insertT_ARCHIVES_FRIENDZero(HashMap map);
	public  abstract int insertT_ARCHIVES_FRIENDOne(HashMap map);
	public  abstract List findUserInviteList(HashMap map);
	public  boolean  updateUserInvitetype(HashMap map);
	
	public PageList findFriendList(HashMap map, Pages pages);
	public abstract int deleteFriend(HashMap map);
    public  abstract List onlyFriendRecordList(HashMap map);
	public  boolean  updateAcceptState(HashMap map);
	
	public  PageList findFriendBlackList(HashMap map, Pages pages);
	public  abstract List findFriendUserList(HashMap map);
	
	public  boolean  updateBlackState(HashMap map);
	
    public List checkLogin(HashMap map);
    
    public List findfriBlaList(HashMap map);
    
    
   //好友设置
	
    //分类
    public  PageList findCategoriesList(HashMap map, Pages pages);
    public  abstract List onlyCategoriesList(HashMap map);
	public  abstract int insertT_ARCHIVES_CATEGORIES(HashMap map);
	public  boolean  updateT_ARCHIVES_CATEGORIES(HashMap map);
	public  abstract List findExistCategoriesList(HashMap map);
	public abstract int deleteCate(HashMap map);
	
	
    //分类   
	
	
	
	
	//用户信息存储session 2009-11-15
	public List findUserSession(HashMap map);
	
	public  abstract int insertMobileT_ARCHIVES_USER(HashMap map);
	
	//更新通过手机新注册的地图位置标注
	public  boolean  updateNewUserPosition(HashMap map);
	
	
	
	
	
	

}