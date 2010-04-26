package com.qzgf.application.archives.user.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;



public class UserFacadeImpl implements UserFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(UserFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertT_ARCHIVES_USER(HashMap map){
		String pb_seq="";

    	 pb_seq=baseSqlMapDAO.sequences("T_Archives_User");

    	map.put("USERID", pb_seq);

    	int st=0;
		st= baseSqlMapDAO.update("User.insertT_ARCHIVES_USER", map);
        return st;
	}

	
	
	@SuppressWarnings("unchecked")
	public int deleteUserById(HashMap map) {
		int num=0;
		int num1 = baseSqlMapDAO.update("User.deleteUserById", map);
		int num2 = baseSqlMapDAO.update("User.deleteUserByEx", map);
		
		num=num1+num2;
		
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public int updateUserById(HashMap map) {
		int num = baseSqlMapDAO.update("User.updateUserById", map);
		if (num == 1) {
			// �޸ĳɹ�
			return 1;
		}
		// �޸�ʧ��
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public int updatePassword(HashMap map) {
		int num = baseSqlMapDAO.update("User.updatePassword", map);
		if (num == 1) {
		    
			return 1;
		}
	 return 0;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public PageList findUser(HashMap map,Pages pages) {
		//�ӷֲ�����
		PageList pl = new PageList();
		//�������
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("User.findUserCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//�����ҳ��
		pages.executeCount();
		//��ʼ��¼
		map.put("START", pages.getSpage());
		//�����ʾ��¼��
		map.put("END", pages.getEpage());
		//�б�����
		List testList = baseSqlMapDAO.queryForList("User.findUserPage", map);
		pl.setObjectList(testList);
		//��ҳ��Ϣ
		pl.setPages(pages);
		return pl;
	}

	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}


	
	
	public PageList findOnlyUserList(HashMap map,Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("User.findOnlyUserList", map);
		pl.setObjectList(testList);
		//��ҳ��Ϣ
		pl.setPages(pages);
		return pl;
		
	}

/*	@Override
	public PageList findOnlyUser_ExList(HashMap map, Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("User.findOnlyUser_ExList", map);
		pl.setObjectList(testList);
		//��ҳ��Ϣ
		pl.setPages(pages);
		return pl;
	}*/
	
	
	@SuppressWarnings("unchecked")
	public PageList isExist(HashMap map, Pages pages) {
		PageList pl = new PageList();
		try
		{
		List testList = baseSqlMapDAO.queryForList("User.isExist", map);
		pl.setObjectList(testList);
		pl.setPages(pages);
		}catch(Exception e)
		{	
		e.printStackTrace();	
		}
		return pl;
	}
	public PageList loginCorrect(HashMap map, Pages pages) {
		PageList pl = new PageList();
		try
		{
		List testList = baseSqlMapDAO.queryForList("User.loginCorrect", map);
		pl.setObjectList(testList);
		pl.setPages(pages);
		}catch(Exception e)
		{	
		e.printStackTrace();	
		}
		return pl;
	}
	
	


	
	public PageList selectProvinceList(HashMap map, Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("User.selectProvinceList", map);
		pl.setObjectList(testList);
		pl.setPages(pages);
		return pl;
	}
	
	public PageList selectCitList(HashMap map, Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("User.selectCitList", map);
		pl.setObjectList(testList);
		pl.setPages(pages);
		return pl;
	}
	
	
	
	
	
	public PageList selectCityList(HashMap map, Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("User.selectCityList", map);
		pl.setObjectList(testList);
		//��ҳ��Ϣ
		pl.setPages(pages);
		return pl;
	}
	
	public PageList selectDistrictList(HashMap map, Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("User.selectDistrictList", map);
		pl.setObjectList(testList);
		//��ҳ��Ϣ
		pl.setPages(pages);
		return pl;
	}
	
	
	
	
	
	
	
	public boolean updatePersonal(HashMap map) {
		int num =0;
	
		try
		{
		  num = baseSqlMapDAO.update("User.updatePersonal", map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(num==1)
		{
		return true;
		}
		else{
		return false;
		}
		
		/*try
		{
		 
		 num2 = baseSqlMapDAO.update("User.updatePersonalUser", map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		int num=num1+num2;
		if (num == 2) {
			// �޸ĳɹ�
			return true;
		}
		// �޸�ʧ��
*/		
		
	}
	
	
	public PageList pwdCorrect(HashMap map, Pages pages) {
		
		PageList pl = new PageList();
		try
		{
		List testList = baseSqlMapDAO.queryForList("User.loginCorrect", map);//跟登录的一样的验证
		pl.setObjectList(testList);
		pl.setPages(pages);
		}catch(Exception e)
		{	
		e.printStackTrace();	
		}
		return pl;
	}
	
	
	
	
	
	
	//会员
	public PageList merchantExist(HashMap map, Pages pages) {
		PageList pl = new PageList();
		try
		{
		List testList = baseSqlMapDAO.queryForList("User.merchantExist", map);
		pl.setObjectList(testList);
		pl.setPages(pages);
		}catch(Exception e)
		{	
		e.printStackTrace();	
		}
		return pl;
	}
	
	
	public int insertT_Member(HashMap map){
		String pb_seq="";

    	 pb_seq=baseSqlMapDAO.sequences("T_MEMBER");

    	map.put("memberid", pb_seq);

    	int st=0;
		st= baseSqlMapDAO.update("User.insertT_Member", map);
        return st;
	}
	
	
	
	public  int updateT_Member(HashMap map){
		int num =0;
		
		try
		{
		  num = baseSqlMapDAO.update("User.updateT_Member", map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return num;
	
	}
	
	

	public List merchantMemberList(HashMap map) {
		return  baseSqlMapDAO.queryForList("User.merchantMemberList", map);
		
	}
	
	public List safeList(HashMap map) {
		return  baseSqlMapDAO.queryForList("User.safeList", map);
		
	}
	
	
	public PageList findMemberList(HashMap map,Pages pages) {
		PageList pl = new PageList();
            if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("User.findMemberCount", map)).intValue();
			pages.setTotalNum(total);}
            pages.executeCount();
            map.put("START", pages.getSpage());
    		map.put("END", pages.getEpage());
            List testList = baseSqlMapDAO.queryForList("User.findMemberList", map);
		    pl.setObjectList(testList);
		    pl.setPages(pages);
		    return pl;
	}

	
	public  boolean updateAudit(HashMap map){
		
	   int num =0;
		
		try
		{
		  num = baseSqlMapDAO.update("User.updateAudit", map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		if(num==1)
		{
		return true;
		}
		else{
		return false;
		}
	}
	
	public List editOnlyRecordList(HashMap map) {
		return  baseSqlMapDAO.queryForList("User.editOnlyRecordList", map);
		
	}
	
    public int deleteMemberOnly(HashMap map) {
		int num=0;
		num = baseSqlMapDAO.update("User.deleteMemberOnly", map);
		return num;
	}	
    
	
	
	
	
	//会员
	
	//部落
	
    
      public PageList isExistCreate(HashMap map, Pages pages) {
		
		PageList pl = new PageList();
		try
		{
		List testList = baseSqlMapDAO.queryForList("User.isExistCreate", map);//跟登录的一样的验证
		pl.setObjectList(testList);
		pl.setPages(pages);
		}catch(Exception e)
		{	
		e.printStackTrace();	
		}
		return pl;
	   }
	
	
    
    
	public PageList findTribalList(HashMap map,Pages pages) {
	     	PageList pl = new PageList();
            if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("User.findTribalCount", map)).intValue();
			pages.setTotalNum(total);}
            pages.executeCount();
            map.put("START", pages.getSpage());
    		map.put("END", pages.getEpage());
            List testList = baseSqlMapDAO.queryForList("User.findTribalList", map);
		    pl.setObjectList(testList);
		    pl.setPages(pages);
		    return pl;
	}

	public PageList findChatRecordList(HashMap map,Pages pages) {
     	PageList pl = new PageList();
        if (pages.getTotalNum() == -1) {
		int total= ((Integer)baseSqlMapDAO.queryForObject("User.findChatRecordCount", map)).intValue();
		pages.setTotalNum(total);}
        pages.executeCount();
        map.put("START", pages.getSpage());
		map.put("END", pages.getEpage());
        List testList = baseSqlMapDAO.queryForList("User.findChatRecordList", map);
	    pl.setObjectList(testList);
	    pl.setPages(pages);
	    return pl;
}
	
	public PageList findSearchUserList(HashMap map,Pages pages) {
     	PageList pl = new PageList();
        if (pages.getTotalNum() == -1) {
		int total= ((Integer)baseSqlMapDAO.queryForObject("User.findSearchUserCount", map)).intValue();
		pages.setTotalNum(total);}
        pages.executeCount();
        map.put("START", pages.getSpage());
		map.put("END", pages.getEpage());
        List testList = baseSqlMapDAO.queryForList("User.findSearchUserList", map);
	    pl.setObjectList(testList);
	    pl.setPages(pages);
	    return pl;
}
	
	
	public PageList findGroupList(HashMap map,Pages pages) {
     	PageList pl = new PageList();
        if (pages.getTotalNum() == -1) {
		int total= ((Integer)baseSqlMapDAO.queryForObject("User.findGroupCount", map)).intValue();
		pages.setTotalNum(total);}
        pages.executeCount();
        map.put("START", pages.getSpage());
		map.put("END", pages.getEpage());
        List testList = baseSqlMapDAO.queryForList("User.findGroupList", map);
	    pl.setObjectList(testList);
	    pl.setPages(pages);
	    return pl;
}
	
	
	
	public int insertT_CHAT_RECORD(HashMap map){
    	int st=0;
		st= baseSqlMapDAO.update("User.insertT_CHAT_RECORD", map);
        return st;
	}

	public int insertT_chat_relate_admin(HashMap map){
    	int st=0;
		st= baseSqlMapDAO.update("User.insertT_chat_relate_admin", map);
        return st;
	}

	
	
	
	public List findT_chat_relateList(HashMap map) {
		return  baseSqlMapDAO.queryForList("User.findT_chat_relateList", map);
		
	}
	
	
	public int insertT_CHAT_RELATE(HashMap map){
    	int st=0;
		st= baseSqlMapDAO.update("User.insertT_CHAT_RELATE", map);
        return st;
	}

	public List findT_chat_relate_ExistMemList(HashMap map) {
		return  baseSqlMapDAO.queryForList("User.findT_chat_relate_ExistMemList", map);
		
	}
	
	
	

	 public  boolean updateT_CHAT_RELATE(HashMap map){
		int num =0;
		try
		{
		  num = baseSqlMapDAO.update("User.updateT_CHAT_RELATE", map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		if(num==1)
		{
		return true;
		}
		else{
		return false;
		}
	}
	 
	 
		@SuppressWarnings("unchecked")
		public List isExistCreate(HashMap map) {
			return  baseSqlMapDAO.queryForList("User.isExistCreate", map);
			
		}
		
	
		
		public List findChatRelateMainList(HashMap map) {
			return  baseSqlMapDAO.queryForList("User.findChatRelateMainList", map);
			
		}
		
	
	//部落
	
	
	 
	 
	 //商品信息
		public PageList findProductList(HashMap map,Pages pages) {
	     	PageList pl = new PageList();
	        if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("User.findProductCount", map)).intValue();
			pages.setTotalNum(total);}
	        pages.executeCount();
	        map.put("START", pages.getSpage());
			map.put("END", pages.getEpage());
	        List testList = baseSqlMapDAO.queryForList("User.findProductList", map);
		    pl.setObjectList(testList);
		    pl.setPages(pages);
		    return pl;
	}
		
		public int insertT_PRODUCT(HashMap map){
	    	int st=0;
			st= baseSqlMapDAO.update("User.insertT_PRODUCT", map);
	        return st;
		}
	


		public List onlyProductList(HashMap map) {
			return  baseSqlMapDAO.queryForList("User.onlyProductList", map);
		}
		
		
		 public  boolean updateT_PRODUCT(HashMap map){
				int num =0;
				try
				{
				  num = baseSqlMapDAO.update("User.updateT_PRODUCT", map);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				if(num==1)
				{
				return true;
				}
				else{
				return false;
				}
			}	

			public int deleteProduct(HashMap map) {
				int num=0;
				num = baseSqlMapDAO.update("User.deleteProduct", map);
				return num;
			}	
	 //商品信息
	 
			
    //优惠券
	 public PageList findFavourableList(HashMap map,Pages pages) {
		     	PageList pl = new PageList();
		        if (pages.getTotalNum() == -1) {
				int total= ((Integer)baseSqlMapDAO.queryForObject("User.findFavourableCount", map)).intValue();
				pages.setTotalNum(total);}
		        pages.executeCount();
		        map.put("START", pages.getSpage());
				map.put("END", pages.getEpage());
		        List testList = baseSqlMapDAO.queryForList("User.findFavourableList", map);
			    pl.setObjectList(testList);
			    pl.setPages(pages);
			    return pl;
		}
	 
	 
	  public int insertT_SELFCONFIG_FAVOURABLE(HashMap map){
	    	int st=0;
			st= baseSqlMapDAO.update("User.insertT_SELFCONFIG_FAVOURABLE", map);
	        return st;
		}
	 
		public List onlyFavourableList(HashMap map) {
			return  baseSqlMapDAO.queryForList("User.onlyFavourableList", map);
		}
	   public  boolean updateT_SELFCONFIG_FAVOURABLE(HashMap map){
				int num =0;
				try
				{
				  num = baseSqlMapDAO.update("User.updateT_SELFCONFIG_FAVOURABLE", map);
				}catch(Exception e)
				{
					e.printStackTrace();
				}

				if(num==1)
				{
				return true;
				}
				else{
				return false;
				}
			}	
		
		public int deleteFavourab(HashMap map) {
			int num=0;
			num = baseSqlMapDAO.update("User.deleteFavourab", map);
			return num;
		}	
	  //优惠券
	
	  //会员申诉
		  public int insertT_ARCHIVES_APPEAL(HashMap map){
		    	int st=0;
				st= baseSqlMapDAO.update("User.insertT_ARCHIVES_APPEAL", map);
		        return st;
			}	
		  public PageList findAppealList(HashMap map,Pages pages) {
		     	PageList pl = new PageList();
		        if (pages.getTotalNum() == -1) {
				int total= ((Integer)baseSqlMapDAO.queryForObject("User.findAppealCount", map)).intValue();
				pages.setTotalNum(total);}
		        pages.executeCount();
		        map.put("START", pages.getSpage());
				map.put("END", pages.getEpage());
		        List testList = baseSqlMapDAO.queryForList("User.findAppealList", map);
			    pl.setObjectList(testList);
			    pl.setPages(pages);
			    return pl;
		}
		  
		  
		  
		  
	 
			public List onlyAppealRecordList(HashMap map) {
				return  baseSqlMapDAO.queryForList("User.onlyAppealRecordList", map);
			}  
		  
		  
			  public  boolean updateT_ARCHIVES_APPEAL(HashMap map){
					int num =0;
					try
					{
					  num = baseSqlMapDAO.update("User.updateT_ARCHIVES_APPEAL", map);
					}catch(Exception e)
					{
						e.printStackTrace();
					}

					if(num==1)
					{
					return true;
					}
					else{
					return false;
					}
				}	
			  
			  
			  
                public int deleteAppeal(HashMap map) {
					int num=0;
					num = baseSqlMapDAO.update("User.deleteAppeal", map);
					return num;
				}		    
			
	  //会员申诉
               
              //好友设置    

          	public List findIsExistUserList(HashMap map) {
				return  baseSqlMapDAO.queryForList("User.findIsExistUserList", map);
			}  
          	
          	
          	public List findUserInviteList(HashMap map) {
				return  baseSqlMapDAO.queryForList("User.findUserInviteList", map);
			}  
          	
          	
            public int insertT_ARCHIVES_FRIENDZero(HashMap map){
		    	int st1=0;int st2=0;
				st1= baseSqlMapDAO.update("User.insertT_ARCHIVES_FRIENDZero1", map);
				st2= baseSqlMapDAO.update("User.insertT_ARCHIVES_FRIENDZero2", map);
				
		        return st1+st2;
			}	
          	
            public int insertT_ARCHIVES_FRIENDOne(HashMap map){
            	int st1=0;int st2=0;
				st1= baseSqlMapDAO.update("User.insertT_ARCHIVES_FRIENDOne1", map);
				st2= baseSqlMapDAO.update("User.insertT_ARCHIVES_FRIENDOne2", map);
				
				 return st1+st2;
			}	
          	
            
      	  public  boolean updateUserInvitetype(HashMap map){
				int num =0;
				try
				{
				  num = baseSqlMapDAO.update("User.updateUserInvitetype", map);
				}catch(Exception e)
				{
					e.printStackTrace();
				}

				if(num==1)
				{
				return true;
				}
				else{
				return false;
				}
			}	
      	  
          	
      	  
      	  public PageList findFriendList(HashMap map,Pages pages) {
		     	PageList pl = new PageList();
		        if (pages.getTotalNum() == -1) {
				int total= ((Integer)baseSqlMapDAO.queryForObject("User.findFriendCount", map)).intValue();
				pages.setTotalNum(total);}
		        pages.executeCount();
		        map.put("START", pages.getSpage());
				map.put("END", pages.getEpage());
		        List testList = baseSqlMapDAO.queryForList("User.findFriendList", map);
			    pl.setObjectList(testList);
			    pl.setPages(pages);
			    return pl;
		}
		  
      	  
			public int deleteFriend(HashMap map) {
				int num1=0,num2=0;
				num1 = this.baseSqlMapDAO.update("User.deleteFriend", map);
				num2= this.baseSqlMapDAO.update("User.deleteFriendTwo", map);
				return num1+num2;
			}	
			
      	  
			public List onlyFriendRecordList(HashMap map) {
				return  baseSqlMapDAO.queryForList("User.onlyFriendRecordList", map);
			}  
		  
			
			  public  boolean updateAcceptState(HashMap map){
					int num1=0,num2=0;
					try
					{
					  num1 = baseSqlMapDAO.update("User.updateAcceptStateOne", map);
					}catch(Exception e)
					{
						e.printStackTrace();
					}

					
					try
					{
					  num2 = baseSqlMapDAO.update("User.updateAcceptStateTwo", map);
					}catch(Exception e)
					{
						e.printStackTrace();
					}

					int num=num1+num2;
					
					
					if(num==2)
					{
					return true;
					}
					else{
					return false;
					}
				}	
			  
			  
			  
			  public PageList findFriendBlackList(HashMap map,Pages pages) {
			     	PageList pl = new PageList();
			        if (pages.getTotalNum() == -1) {
					int total= ((Integer)baseSqlMapDAO.queryForObject("User.findFriendBlackCount", map)).intValue();
					pages.setTotalNum(total);}
			        pages.executeCount();
			        map.put("START", pages.getSpage());
					map.put("END", pages.getEpage());
			        List testList = baseSqlMapDAO.queryForList("User.findFriendBlackList", map);
				    pl.setObjectList(testList);
				    pl.setPages(pages);
				    return pl;
			}
			   
			  
				public List findFriendUserList(HashMap map) {
					return  baseSqlMapDAO.queryForList("User.findFriendUserList", map);
				}  
			  
			  
			  
				  public  boolean updateBlackState(HashMap map){
						int num1=0,num2=0;
						try
						{
						  num1 = baseSqlMapDAO.update("User.updateBlackStateOne", map);
						}catch(Exception e)
						{
							e.printStackTrace();
						}

						
						try
						{
						  num2 = baseSqlMapDAO.update("User.updateBlackStateTwo", map);
						}catch(Exception e)
						{
							e.printStackTrace();
						}

						int num=num1+num2;
						
						
						if(num==2)
						{
						return true;
						}
						else{
						return false;
						}
					}	
				  
				
				
					public List checkLogin(HashMap map) {
						return  baseSqlMapDAO.queryForList("User.checkLogin", map);
                     }
					
					public List findfriBlaList(HashMap map) {
						return  baseSqlMapDAO.queryForList("User.findfriBlaList", map);
						
					}	
					

          	 //好友设置        
                
                
          //分类
					  
					  public PageList findCategoriesList(HashMap map,Pages pages) {
					     	PageList pl = new PageList();
					        if (pages.getTotalNum() == -1) {
							int total= ((Integer)baseSqlMapDAO.queryForObject("User.findCategoriesCount", map)).intValue();
							pages.setTotalNum(total);}
					        pages.executeCount();
					        map.put("START", pages.getSpage());
							map.put("END", pages.getEpage());
							
					        List testList = baseSqlMapDAO.queryForList("User.findCategoriesList", map);
						    pl.setObjectList(testList);
						    pl.setPages(pages);
						    return pl;
					}
					  
					  
						public List onlyCategoriesList(HashMap map) {
							return  baseSqlMapDAO.queryForList("User.onlyCategoriesList", map);
						}  
						
						
						
						   public int insertT_ARCHIVES_CATEGORIES(HashMap map){
				            	int st=0;
								st= baseSqlMapDAO.update("User.insertT_ARCHIVES_CATEGORIES", map);
								
								
								 return st;
							}	
						   
						   
							  public  boolean updateT_ARCHIVES_CATEGORIES(HashMap map){
									int num=0;
									try
									{
									  num = baseSqlMapDAO.update("User.updateT_ARCHIVES_CATEGORIES", map);
									}catch(Exception e)
									{
										e.printStackTrace();
									}

                         		if(num==1)
									{
									return true;
									}
									else{
									return false;
									}
								}	
							  		   
						   
						   
				  public List findExistCategoriesList(HashMap map) {
									return  baseSqlMapDAO.queryForList("User.findExistCategoriesList", map);
					}  
										   
					public int deleteCate(HashMap map) {
						int num=0;
						num = this.baseSqlMapDAO.update("User.deleteCate", map);
					
						return num;
					}	
							   
						   
	    //分类		   
					      
	
            	//用户信息存储session 2009-11-15
            	public List findUserSession(HashMap map){
            		return baseSqlMapDAO.queryForList("Mapcard.findMapcard", map);
            	}
            	
            	//手机用户插入
         	   public int insertMobileT_ARCHIVES_USER(HashMap map){
	            	int st=0;
					st= baseSqlMapDAO.update("User.insertMobileT_ARCHIVES_USER", map);
					return st;
				}	   	
         	   
         	   
         	   
         	  public  boolean updateNewUserPosition(HashMap map){
					int num=0;
					try
					{
					  num = baseSqlMapDAO.update("User.updateNewUserPosition", map);
					}catch(Exception e)
					{
						e.printStackTrace();
					}

       		if(num==1)
					{
					return true;
					}
					else{
					return false;
					}
				}	
            	
            	
	
	
}
