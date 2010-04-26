package com.qzgf.application.archives.user.action;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.user.domain.UserFacade;
import com.qzgf.application.systemManage.login.domain.LoginFacade;
import com.qzgf.application.systemManage.manager.dto.UserInfo;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;






@SuppressWarnings("serial")
public class UserAction extends BaseAction {
	Log log = LogFactory.getLog(UserAction.class);

	@SuppressWarnings("unchecked")

	private UserFacade userFacade;
	@SuppressWarnings("unchecked")
	private List testList;
	private PageList pageList;
	
	private PageList pageUserList;
	private PageList userOnlyList;
	private PageList userOnly_ExList;
	private PageList isExistList;
	private PageList isExistloginCorrect; //用户名跟密码确认
	
	private PageList provinceList;
	private PageList cityList;
	private PageList districtList;
	
	private PageList isExistPwdCorrect;//判断旧密码是否正确
	
	
	//会员
	private PageList  isExistMerchant;
	private PageList merchantMemberList; 
	
	private PageList  pageMemberList;
	
	//会员
	
	//创建部落
	private PageList   tribalList;
	private PageList chatRecordList;//聊天记录
	private PageList searchUserList;
	
	private PageList ifExistCreate; //部落是否已经存在
	
	private PageList searchGroupList;
	
	//创建部落
	
	
	
	//商品信息
	private PageList pageProductList;
	//商品信息
	
	//优惠券
	private PageList pageFavourableList;
	
	//优惠券 end
	
	//会员申诉
	private PageList searchAppealList;

	
	//会员申诉 end
	
    //好友设置
	private PageList    pageFriendList;
	private PageList  	pageFriBlackList;

	
	//好友设置
	
	
	//商业分类
	private PageList   categoriesList;
	
	//商业分类

	//private String provinceId;
	//private List<OptionsString> userTypeList = new ArrayList<OptionsString>();
	
	private String xml;
	BaseSqlMapDAO baseSqlMapDAO;

	
    private LoginFacade loginFacade;
	
	@SuppressWarnings("unchecked")
	public  HashMap userInfo;
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return "index";
		}
	}

	
	
	
	
	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
		pages.setPerPageNum(10); //����ÿҳ��С
		//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("user.do?action=" + this.getAction());
		this.setPageList(this.userFacade.findUser(search, pages));
		return "index";
	}
	

	
	
	//密码修改-判断旧密码正确不
	  @SuppressWarnings("unchecked")
	public String passwordCorrect(){
		  
		   HashMap userInfo=new HashMap();
		   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	        String pusername=userInfo.get("USERNAME").toString();
			search.put("pusername", pusername);
		    
 
	        isExistPwdCorrect=this.userFacade.pwdCorrect(search,null);
	    	if(isExistPwdCorrect.getObjectList().isEmpty()){
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<check>");
			sb.append("<value>").append("旧密码输入有误!").append("</value>");
			sb.append("</check>");
			xml = sb.toString();
			return "xml";
			
		}
	else{
	    		
	    		java.lang.StringBuffer sb = new StringBuffer();
	    		sb.append("<check>");
	    		sb.append("<value>").append("0").append("</value>");
	    		sb.append("</check>");
	    		xml = sb.toString();
	    		return "xml";
	    	}
	}
	
	@SuppressWarnings("unchecked")
	public String passwordSet() {
		
		
		   HashMap userInfo=new HashMap();
		   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
		
		
		
		try
 		{
			
 		 List safeAnsQusList=this.userFacade.safeList(search);
 		 
 		 if (!safeAnsQusList.isEmpty()){
 	     search=(HashMap)safeAnsQusList.get(0);
 		 }
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}

 		
		return "pwdSet";
	}

	
	//�޸�
    @SuppressWarnings("unchecked")
	public String modify() {
    	
    int i=0;	
    	
    	try
    	{
     i=   userFacade.updatePassword(search);
    	
	}catch(Exception e){
		e.getStackTrace();
	}
        
	
	
	if(i==1){
		this.addActionMessage(this.getText("提示：修改成功."));
	}else{
		this.addActionError(this.getText("提示：修改失败"));
	}
	
	
	
        
        
		return passwordSet();
		
	}	
  //密码修改
    
    public String enter(){
    	
    	return "enterLogin";
    }
    
    public String loginCorrect(){
        isExistloginCorrect=this.userFacade.loginCorrect(search,null);//判断登录的用户名跟密码是否正确
    	if(isExistloginCorrect.getObjectList().isEmpty()){
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("用户名跟密码不匹配!").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		return "xml";
	}
else{
    		
    		java.lang.StringBuffer sb = new StringBuffer();
    		sb.append("<check>");
    		sb.append("<value>").append("0").append("</value>");
    		sb.append("</check>");
    		xml = sb.toString();
    		
    		return "xml";
    	}
 
}
    
    @SuppressWarnings("unchecked")
	public String login(){

    	//pusername
    	//ppassword

    	/*
    	if(!isExistList.getObjectList().isEmpty()){i=1;}
    	if(i==1)
    	{
    	java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("此用户已经被注册了").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		return "xml";
    	}
    	*/
    	
    	
    	//ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME,search.get("pusername"));

		
    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	         String pusername=userInfo.get("USERNAME").toString();
			 search.put("pusername", pusername);
    	

    	provinceList=this.userFacade.selectProvinceList(this.getSearch(),null);
    	//cityList=this.userFacade.selectCityList(this.getSearch(),null);
    
	
    	this.userOnlyList=this.userFacade.findOnlyUserList(this.getSearch(), null);
    	
    
    	String provinceID="";
    	String cityID="";
    	String districtID="";
    	
    //	search=  (HashMap) ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME);
    	
    	
    	
    	//System.out.println(employId1);
    	

    	
    	List userList=userOnlyList.getObjectList();//this.userFacade.findUserById(search.get("pusername"));
		Iterator i = userList.iterator();
		if(i.hasNext()){
			Map map=(HashMap)i.next();
			provinceID=(String)map.get("PROVINCEID");
			cityID=(String)map.get("CITYID");
			districtID=(String)map.get("DISTRICTID");
            }
    	
    	if (provinceID!=null){
		search.put("fid", provinceID);
		cityList=this.userFacade.selectCityList(this.getSearch(),null);
    	}
    	
    	if (cityID!=null){
		search.put("fid", cityID);//获得区/县
		districtList=this.userFacade.selectDistrictList(this.getSearch(),null); 
    	}
   
        
    	
    	return "enterSuc";	
    	
    	
    	///////////////////////////////////////////
    	/*List userList=userOnlyList.getObjectList();//this.userFacade.findUserById(search.get("pusername"));
		Iterator i = userList.iterator();
		if(i.hasNext()){
			Map map=(HashMap)i.next();
		
			this.provinceId=(String)map.get("PROVINCEID");
		}
		
		List typeList=new ArrayList();
		try{
			typeList=provinceList.getObjectList();//this.userFacade.findTwitterTypeByUserId(search);
		}catch(Exception e){
			e.printStackTrace();
		}
		for (int j = 0; j < typeList.size(); j++) {
			Map map=(HashMap)typeList.get(j);
			this.userTypeList.add(new OptionsString(String.valueOf(map.get("ID")), String.valueOf(map.get("NAME"))));
		}*/
    	}
    
    public String listAllCity(){
    	
    	
    	cityList=this.userFacade.selectCityList(this.getSearch(),null);
    	
    	//�����
	List all = null;
	try {
		java.lang.StringBuffer sb = new StringBuffer();
	
		all =this.userFacade.selectCityList(this.getSearch(),null).getObjectList();
		
		int i=0;
		sb.append("<root>");
		Iterator it = all.iterator();
		while (it.hasNext()) {

			search=(HashMap)(all.get(i));
			 i=i+1;
			it.next();// ���
			
			sb.append("<item>");
			sb.append("<id>" + search.get("ID") + "</id>");
			sb.append("<name>" + search.get("NAME")+ "</name>");
			sb.append("</item>");
		}
		sb.append("</root>");
		xml = sb.toString();
	} catch (Exception e) {
	}
	
	return "xml";
}
    
   
    public String listAllDistrict(){
    	//�������
	List all = null;
	try {
		java.lang.StringBuffer sb = new StringBuffer();
	
		all =this.userFacade.selectDistrictList(this.getSearch(),null).getObjectList();
		
		int i=0;
		sb.append("<root>");
		Iterator it = all.iterator();
		while (it.hasNext()) {

			search=(HashMap)(all.get(i));
			 i=i+1;
			it.next();// ���
			
			sb.append("<item>");
			sb.append("<id>" + search.get("ID") + "</id>");
			sb.append("<name>" + search.get("NAME")+ "</name>");
			sb.append("</item>");
		}
		sb.append("</root>");
		xml = sb.toString();
	} catch (Exception e) {
	}
	
	return "xml";
}
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
	public String personal(){
    	
    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
    	
    	
    	    try{
        	boolean i=this.userFacade.updatePersonal(search);
        	 System.out.println(i);
   	 
        	return login();
        	}catch(Exception e){
        		e.getStackTrace();
        	}
        	
        	return "enterFail";	
        	
    }
    public String editOnlyList(){
    	// System.out.println(search.get("psex"));
    	 //System.out.println(search.get("pusername"));
    	 
    	 
    		
    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
    	
    	
    	 
    	 
    	 
    	 
    	   try{
        	boolean i=this.userFacade.updatePersonal(search);
        	 System.out.println(i);
        	}catch(Exception e){
        		e.getStackTrace();
        	}

        	
        //////////////////////////���»��list�б�
        	  try
        		{
        			Pages pages = new Pages();
        			pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
        			pages.setPerPageNum(10); //����ÿ����ʾ�������Ϊ���
        			//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
        			//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
        			pages.setFileName("user.do?action=list");
        			this.pageUserList=this.userFacade.findUser(search, pages);
        		}
        		catch(Exception e)
        		{
        			e.printStackTrace();
        		}
       ////////////////////////////���»��list�б�  		 	
        		
     return "regeditList";
    }
    

    public String  delete() {
    	int i=0;
		try {
			i=this.userFacade.deleteUserById(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		

		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
		

       /* //////////////////////////���»��list�б�
        	  try
        		{
        			Pages pages = new Pages();
        			pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
        			pages.setPerPageNum(10); //����ÿ����ʾ�������Ϊ���
        			//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
        			//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
        			pages.setFileName("user.do?action=list");
        			this.pageUserList=this.userFacade.findUser(search, pages);
        		}
        		catch(Exception e)
        		{
        			e.printStackTrace();
        		}
        		
        		
        		
        		
       ////////////////////////////���»��list�б�  		 		
     return "regeditList";*/
	}
    
    
    
    
    public String regedit(){
    	return "regedit";
    }
   
    @SuppressWarnings("unchecked")
	public String insert(){ //������û� 

              int i=0;
    	      try{
    		   i= this.userFacade.insertT_ARCHIVES_USER(search);
    		    }catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    	
    	
    	    if(i==1){
    	    	return "regeditSuccess"; 	
    	    }else
    	    {
    	    return "regeditFail";	
    	    }
    	    
    	    
    	    
    }
    
    
    
    
    
    
    
   /* public String edit(){ //����༭����
    
    	this.userOnlyList=this.userFacade.findOnlyUserList(this.getSearch(), null);

			
    	if(userOnlyList!=null)
    	{
    		search=(HashMap)(userOnlyList.getObjectList().get(0));
    		System.out.println(search.get("USERID"));
    		this.userOnly_ExList=this.userFacade.findOnlyUser_ExList(this.getSearch(), null);
    		
    		return "enterSuc";
    	}
    	
    	return "enterFail";
    }
    */
    @SuppressWarnings("unchecked")
	public String editList(){ 
    	
    	
    	/* HashMap userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
    	*/
    	//针对某单一用户
    	
    	
    	this.userOnlyList=this.userFacade.findOnlyUserList(this.getSearch(), null);

    	if(!userOnlyList.getObjectList().isEmpty())
    	{
    		search=(HashMap)(userOnlyList.getObjectList().get(0));
    	//	System.out.println(search.get("USERID"));
    	//	this.userOnly_ExList=this.userFacade.findOnlyUser_ExList(this.getSearch(), null);
    		
    	}
    	
    	
    		// return "";
    	return "enterListSuc";
    }
    
    
    
    
    
    public String isExist(){ 
    	
    	
    	String rand = (String) ActionContext.getContext().getSession().get("rand");
    	System.out.print(rand);
    	
    	System.out.print(search.get("checkcode"));
    	
    	if(!rand.equals(search.get("checkcode")))
    	{
    	java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("验证码输入有误").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		return "xml";
    	}
    	
    	
    	
    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
           String pusername=userInfo.get("USERNAME").toString();
		   search.put("pusername", pusername);
   	
   	
    	
    	int i=0;
    	isExistList=this.userFacade.isExist(search,null);
    	if(!isExistList.getObjectList().isEmpty()){i=1;}
    	if(i==1)
    	{
    	java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("此用户已经被注册了").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		return "xml";
    	}
    	else{
    		
    		java.lang.StringBuffer sb = new StringBuffer();
    		sb.append("<check>");
    		sb.append("<value>").append("0").append("</value>");
    		sb.append("</check>");
    		xml = sb.toString();
    		
    		return "xml";
    	}
    	
    	
    }
	

     
    public String list(){
    try
	{
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(10);
		pages.setFileName("user.do?action=list");
		this.pageUserList=this.userFacade.findUser(search, pages);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	
	 
	return "regeditList";
    }
    
    //��ת��Э����
    public String article(){
    	return "article";
    }
    
    
    //商家会员
     @SuppressWarnings("unchecked")
	public String  merchantEnter() { 

    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
		 
		 
    	 try
    		{
    		 List mercMenList=this.userFacade.merchantMemberList(search);
    		 
    		 if (!mercMenList.isEmpty()){
    	     search=(HashMap)mercMenList.get(0);
    		 }
    		 
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		
		return "merchantEnter"; 
		
		
	 }
     public String  deleteMember() {
    	 
    		int i=0;
    		try {
    			i=this.userFacade.deleteMemberOnly(search);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.getMessage();
    		}
    		
    	
    	java.lang.StringBuffer sb = new StringBuffer();
 		sb.append("<delete>");
 		sb.append("<value>").append(i).append("</value>");
 		sb.append("</delete>");
 		xml = sb.toString();
 		return "xml"; 
    	 
     }
     
     
     
     
     @SuppressWarnings("unchecked")
	public String  applMerchantSave() {
    	 
    	 
    	 

         
         
         
    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
    	 
    	 
    	    isExistMerchant=this.userFacade.merchantExist(search,null);
    	    
    	    int i=0;
    		if(isExistMerchant.getObjectList().isEmpty()){ //如果没有记录执行插入
    			        try{
    				   i=  this.userFacade.insertT_Member(search);
    	    		    }catch (Exception e) {
    	    				e.printStackTrace();
    	    			}
    	    		    
    			}
    		else //存在就直接更新
    		   {
    			
    			
    			  try{
    				  i= this.userFacade.updateT_Member(search);	
    			
    			   }catch (Exception e) {
	    				e.printStackTrace();
	    			}
    			   
    		
    		   }
    		
    		
    		if(i==1){
				this.addActionMessage(this.getText("提示：保存成功."));
			}else{
				this.addActionError(this.getText("提示：保存失败"));
			}
    		
    		
    		
    	

    		 try
     		{
     		 List mercMenList=this.userFacade.merchantMemberList(search);
     		 
     		 if (!mercMenList.isEmpty()){
     	     search=(HashMap)mercMenList.get(0);
     		 }
     		 
     		}
     		catch(Exception e)
     		{
     			e.printStackTrace();
     		}
     	
     		
     		
     		
 
    		  return "applMerchantSave";
 	 }
     
     
     public String  MemberPageList() {  

     try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
			pages.setPerPageNum(10); //����ÿ����ʾ�������Ϊ���
			//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
			//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
			pages.setFileName("user.do?action=MemberPageList");//分页功能使用
			this.pageMemberList=this.userFacade.findMemberList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "MemberPageList";
     }

     
     
     @SuppressWarnings("unchecked")
	public String  editOnlyRecord() {
       try
  		{
  		 List editOnlyList=this.userFacade.editOnlyRecordList(search);
  		 
  		 if (!editOnlyList.isEmpty()){
  	     search=(HashMap)editOnlyList.get(0);
  		 }
  		 
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}

       return "editOnlyRecord";
 	}
     
     public String  applicationAudit() {
    	   try{
          	boolean i=this.userFacade.updateAudit(search);
          	 System.out.println(i);
          	}catch(Exception e){
          		e.getStackTrace();
          	}
          	
    	    return MemberPageList(); 
     }
     
    //商家会员
	
	
    //部落创建
     @SuppressWarnings("unchecked")
     public String  createTribal() {  
    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
    	 
		 String membertype=userInfo.get("MEMBERTYPE").toString();
		 search.put("pmembertype", membertype);
		 
		 
		 
			
		 try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			//pages.setPerPageNum(10);
			pages.setFileName("user.do?action=categories");
			this.categoriesList=this.userFacade.findCategoriesList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		 
    	 
        return "createTribal";
    	 
     }
    
     
     
   //判断是否已经存在一个群主
     public String ifexistTribal()
     {
	       
	  

	        System.out.println(search.get("pgroupname"));
	        
	       
	        ifExistCreate=this.userFacade.isExistCreate(search,null);//判断登录的用户名跟密码是否正确

	        
	        String pmembertype=search.get("pmembertype").toString();
	        
	 
	        if(!pmembertype.equals("2")){
	        	java.lang.StringBuffer sb = new StringBuffer();
		 		sb.append("<check>");
		 		sb.append("<value>").append("创建部落必须是向导身份，你目前不是!").append("</value>");
		 		sb.append("</check>");
		 		xml = sb.toString();
		 		return "xml";
	        }else if(search.get("pgroupname").equals("")){
	        	java.lang.StringBuffer sb = new StringBuffer();
		 		sb.append("<check>");
		 		sb.append("<value>").append("部落名称不能为空,请填写!").append("</value>");
		 		sb.append("</check>");
		 		xml = sb.toString();
		 		return "xml";
	        }
	        else if(search.get("plat").equals(""))
	        {
	        	java.lang.StringBuffer sb = new StringBuffer();
		 		sb.append("<check>");
		 		sb.append("<value>").append("请标注活动地点,你还没标注!").append("</value>");
		 		sb.append("</check>");
		 		xml = sb.toString();
		 		return "xml";
	        }
	        else if(!ifExistCreate.getObjectList().isEmpty()){
	 		java.lang.StringBuffer sb = new StringBuffer();
	 		sb.append("<check>");
	 		sb.append("<value>").append("此用户已创建一个部落群,不能重复创建!").append("</value>");
	 		sb.append("</check>");
	 		xml = sb.toString();
	 		return "xml";
	 	}
	 else{
	     		
	     		java.lang.StringBuffer sb = new StringBuffer();
	     		sb.append("<check>");
	     		sb.append("<value>").append("0").append("</value>");
	     		sb.append("</check>");
	     		xml = sb.toString();
	     		
	     		return "xml";
	     	}
 }
     
     
     
     @SuppressWarnings("unchecked")
	public String  newCreateTribal() { 
    	 

	 
	       
    	   HashMap userInfo=new HashMap();
    	   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	         String pusername=userInfo.get("USERNAME").toString();
			 search.put("pusername", pusername);
			 
	     //  System.out.println(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	      // System.out.println(search.get("pusername"));
	       
	     //  System.out.println(search.get("pgroupname"));
	       
	         int i=0;
		      try{
			   i= this.userFacade.insertT_chat_relate_admin(search);
			    }catch (Exception e) {
					e.printStackTrace();
				}

			    
				
	    		if(i==1){
					this.addActionMessage(this.getText("提示：已创建成功,请在部落群查看."));
				}else{
					this.addActionError(this.getText("提示：创建失败"));
				}
			    
			    
	        
	        
             /*   try
    			  {
    				Pages pages = new Pages();
    				pages.setPage(this.getPage());
    				pages.setPerPageNum(10);    		
    				//pages.setFileName("user.do?action=MemberPageList");//分页功能使用
    				 tribalList=this.userFacade.findTribalList(search,pages);
    				
    			 }
    			catch(Exception e)
    			{
    				e.printStackTrace();
    			}*/
    		
            
    	 
    	 return "createTribal"; 
    	 
    	 
     }
     
     
 	public String  Release() {   
 		  
 		  
 	      int i=0;
	      try{
		   i= this.userFacade.insertT_CHAT_RECORD(search);
		    }catch (Exception e) {
				e.printStackTrace();
			}

	/*    if(i==1){
	    	return "regeditSuccess"; 	
	    }else
	    {
	    return "regeditFail";	
	    }
 		*/
 		
		    if(i==1){
 		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("1").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		    }
		    else{
		    	java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<check>");
				sb.append("<value>").append("0").append("</value>");
				sb.append("</check>");
				xml = sb.toString();	
		    	
		    }
		return "xml";
		

 		
 	}
 	
 	public String  tribalGroup() { 
 		
 		     HashMap userInfo=new HashMap();
 		     userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	         String pusername=userInfo.get("USERNAME").toString();
			 search.put("pusername", pusername);

 		 try
 		 {
 			Pages pages = new Pages();
 			pages.setPage(this.getPage());
 			//pages.setPerPageNum(10);    		
 			//pages.setFileName("user.do?action=MemberPageList");//分页功能使用
 			searchGroupList=this.userFacade.findGroupList(search,pages);
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		
 		
 	return "tribalGroup";	
 	}
 	
 	
 	
 	
 	@SuppressWarnings("unchecked")
	public String  loginTribal() { 
 	
     //search.put("pusername", ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
     
 		// HashMap userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
        // String pusername=userInfo.get("USERNAME").toString();
		// search.put("pusername", pusername);

 	 List chatRelateList=this.userFacade.findT_chat_relateList(search);
	 if (!chatRelateList.isEmpty()){
     search=(HashMap)chatRelateList.get(0);
	 }
	 
	 
	
	
	    try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			//pages.setPerPageNum(10);    		
			//pages.setFileName("user.do?action=MemberPageList");//分页功能使用
			 tribalList=this.userFacade.findTribalList(search,pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		  try
			{
				Pages pages = new Pages();
				pages.setPage(this.getPage());
				//pages.setPerPageNum(10);    		
				//pages.setFileName("user.do?action=MemberPageList");//分页功能使用
				 chatRecordList=this.userFacade.findChatRecordList(search,pages);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			try
			{
				   HashMap userInfo=new HashMap();
				   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	         String pusername=userInfo.get("USERNAME").toString();
			 search.put("MEMBERUSER", pusername);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
			 
	
			
			
	return "loginTribal"; 
	
 	}
 	
	 
 	public String  searchUser() { 

 		 try
			{
				Pages pages = new Pages();
				pages.setPage(this.getPage());
				pages.setPerPageNum(10);    		
				//pages.setFileName("user.do?action=MemberPageList");//分页功能使用
				searchUserList=this.userFacade.findSearchUserList(search,pages);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			
 		
 	    
 		return "searchUser";
 	}
 	
 	   ////查找好友
	@SuppressWarnings("unchecked")
	public String  addFriend() { 
		
	 //群主才有权限发布要求，所以这里记的pusername是群主的mainUser
	 //search.put("pusername", ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	 
	/* try
	 {
	 HashMap userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
     String pusername=userInfo.get("USERNAME").toString();
	 search.put("pusername", pusername);
	 }catch(Exception e) {
			e.printStackTrace();
		}	 */
	 
	 
 	 List memberExistList=this.userFacade.findT_chat_relate_ExistMemList(search);
	 if (!memberExistList.isEmpty()){ //不等于空，说明这个成员已经是这个群里面的成员
		 this.userFacade.updateT_CHAT_RELATE(search); 
	 }
	 else
	 {
		 String MemberUser=search.get("MemberUser").toString();
		 List mainList=this.userFacade.findChatRelateMainList(search);
		 if (!mainList.isEmpty()){
	     search=(HashMap)mainList.get(0);
		 }
		 
		 search.put("MemberUser", MemberUser);
		 
		   try{
			     this.userFacade.insertT_CHAT_RELATE(search);//添加普通成员
			    }catch (Exception e) {
					e.printStackTrace();
				}	 
		 
	 }
	 
	 System.out.println(search.get("chatid"));
	 
	  return "addFriend";
	  
	  
	}
       ////查找好友
     //部落创建 end
    
	
	//商品信息
	
	public String  productMessage() { 

		
		 HashMap userInfo=new HashMap();
		 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);

		
		
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			pages.setPerPageNum(10);    		
			pages.setFileName("user.do?action=productMessage");//分页功能使用
			pageProductList=this.userFacade.findProductList(search,pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	 return "productMessage";	
	}
	
	public String  productAdd() { 
		
		
		 return "productAdd"; 
	}
	
	
	public String  productAddSave() {

		 HashMap userInfo=new HashMap();
		 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
		
		
	        int i=0;
 	         try{
 		      i= this.userFacade.insertT_PRODUCT(search);
 		    }catch (Exception e) {
 				e.printStackTrace();
 			}

		 return productMessage(); 
	}
	
	
	public String  productModify() { //取得当前行的值
   	 try
   		{
   		 List productList=this.userFacade.onlyProductList(search);
   		 
   		 if (!productList.isEmpty()){
   	     search=(HashMap)productList.get(0);
   		 }
   		 
   		}
   		catch(Exception e)
   		{
   			e.printStackTrace();
   		}
		 return "productModify"; 
	}
	
	
	public String  productModifySave() { 
		   try{
	          	boolean i=this.userFacade.updateT_PRODUCT(search);
	          	 System.out.println(i);
	          	}catch(Exception e){
	          		e.getStackTrace();
	          	}
		 return productMessage();
	}
	
	
	public String  productDelete() {
		int i=0;
		try {
		i=this.userFacade.deleteProduct(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
		
		
		
		//return productMessage();
	}

	//商品信息 end 
	
	
	// 优惠券
	public String  favourableList() {
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			pages.setPerPageNum(10);    		
			pages.setFileName("user.do?action=favourableList");//分页功能使用
			pageFavourableList=this.userFacade.findFavourableList(search,pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return  "favourableList";		
	}
	
	public String  favourableAdd() {
		
		
		return  "favourableAdd";			
	}
	
	public String  favourableAddSave() {
            try{
	         this.userFacade.insertT_SELFCONFIG_FAVOURABLE(search);
	       }catch (Exception e) {
			e.printStackTrace();
	      	}
		
		return  favourableList();		
	}
	
	
	
    public String  favourableModify() {
    	 try
    		{
    		 List favourableList=this.userFacade.onlyFavourableList(search);
    		 
    		 if (!favourableList.isEmpty()){
    	     search=(HashMap)favourableList.get(0);
    		 }
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
 	      return  "favourableModify" ;		
	}
	
	public String  favourableModifySave() {
		
		 try{
	          	boolean i=this.userFacade.updateT_SELFCONFIG_FAVOURABLE(search);
	          	 System.out.println(i);
	          	}catch(Exception e){
	          		e.getStackTrace();
	          	}
	return favourableList();
	}
	
	public String  favourableDelete() {
		
		int i=0;
		try {
			i=this.userFacade.deleteFavourab(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
	
	}
	// 优惠券 end
	
	
	
	//会员申诉
	public String  insertAppeal() {
		

		return "insertAppeal";
	}
	
	public String  insertAppealSave() {

	     int i=0;
	      try{
		   i= this.userFacade.insertT_ARCHIVES_APPEAL(search);
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    
		    
		    
			
    		if(i==1){
				this.addActionMessage(this.getText("提示：申诉信息已经提交,请等待回复."));
			}else{
				this.addActionError(this.getText("提示：申诉失败"));
			}  
		    
		    
		    
		return "insertAppealSave";
	}
	public String  appealList() {
		
		 try
			{
				Pages pages = new Pages();
				pages.setPage(this.getPage());
				pages.setPerPageNum(10);    		
				pages.setFileName("user.do?action="+ this.getAction());//分页功能使用
				searchAppealList=this.userFacade.findAppealList(search,pages);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
	return	"appealList";
		
	}
	
	
	public String  editOnlyAppeal() {
		
		 try
	  		{
	  		 List onlyAppealList=this.userFacade.onlyAppealRecordList(search);
	  		 
	  		 if (!onlyAppealList.isEmpty()){
	  	     search=(HashMap)onlyAppealList.get(0);
	  		 }
	  		 
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	
		return	"editOnlyAppeal";
		
	}
	
	public String  editOnlyAppealSave() {
		
		        try{
	          	boolean i=this.userFacade.updateT_ARCHIVES_APPEAL(search);
	          	 System.out.println(i);
	          	}catch(Exception e){
	          		e.getStackTrace();
	          	}
	          	
	          	
	         this.setAction("appealList"); 	
		
		return appealList();
	}
	
	public String  selectAllAppeal() {
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			//pages.setPerPageNum(10);    		
			pages.setFileName("user.do?action=selectAllAppeal");//分页功能使用
			searchAppealList=this.userFacade.findAppealList(search,pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		return "selectAllAppeal";	
	}
	
	public String  deleteAppeal() {

	 
	    int i=this.userFacade.deleteAppeal(search);
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";

	}
	//会员申诉
	
	
	
	//好友设置  
	public void  pagefriendAll() {   //显示在下面的列表
		   HashMap userInfo=new HashMap();
		   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	     String pusername=userInfo.get("USERNAME").toString();
	     search.put("pusername", pusername);
		 try
			{
				Pages pages = new Pages();
				pages.setPage(this.getPage());
				pages.setPerPageNum(10);
				pages.setFileName("user.do?action=pagefriendAll");
				this.pageFriendList=this.userFacade.findFriendList(search, pages);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public String  friendSet() {
		
		   HashMap userInfo=new HashMap();
		   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		  search.put("pusername",userInfo.get("USERNAME").toString());
		 
		  HashMap hm= (HashMap)userFacade.checkLogin(search).get(0); 
		  String uinvitetype=hm.get("INVITETYPE").toString();
		  search.put("uinvitetype", uinvitetype);


		 pagefriendAll();
     return	 "friendSet";
		
	}
	
	@SuppressWarnings("unchecked")
	public String  isExistUser() {
	
		
		   HashMap userInfo=new HashMap();
		   userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	     String sessionUser=userInfo.get("USERNAME").toString();
	     
		 String frienduser=search.get("frienduser").toString();
		 
		 if(sessionUser.equals(frienduser)){
			
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<check>");
				sb.append("<value>").append("要添加的好友不能为自己本身！").append("</value>");
				sb.append("</check>");
				xml = sb.toString();
				return "xml";
				
		 }
		 
		 
		 //判断一下是不是已在黑名单中。
		 
		 search.put("mainuser", sessionUser);
		 search.put("frienduser", frienduser);
		 List friBlaList=this.userFacade.findfriBlaList(search);
		 if(!friBlaList.isEmpty()){
			  HashMap  hmp= (HashMap)friBlaList.get(0);
			  String state=hmp.get("STATE").toString();
			  
			  if (state.equals("2")){//是黑名单
				    java.lang.StringBuffer sb = new StringBuffer();
				  sb.append("<check>");
				  sb.append("<value>").append("你已经把此用户列入黑名单!").append("</value>");
				  sb.append("</check>");
				  xml = sb.toString();
				  
				    return "xml";
				  }
			  }
 
		 //判断一下是不是已在黑名单中,在黑名单中就不能在继续添加为好友了
		 
		 
		 
		 
		 
		 
		 
		 
		
		
        search.put("pusername", search.get("frienduser"));
        
        List isExistUserList=this.userFacade.findIsExistUserList(search);

    	if(isExistUserList.isEmpty()){
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("不存在此用户!").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		return "xml";
		
	} else if(!isExistUserList.isEmpty()){
		
		  HashMap  hmap= (HashMap)isExistUserList.get(0);
		  String invStr=hmap.get("INVITETYPE").toString();
		  if (invStr.equals("2")){
		    java.lang.StringBuffer sb = new StringBuffer();
		  sb.append("<check>");
		  sb.append("<value>").append("此用户拒绝被添加为好友!").append("</value>");
		  sb.append("</check>");
		  xml = sb.toString();
		  
		    return "xml";
		  }
		  else{
	
	    		java.lang.StringBuffer sb = new StringBuffer();
	    		sb.append("<check>");
	    		sb.append("<value>").append("0").append("</value>");
	    		sb.append("</check>");
	    		xml = sb.toString();
	    		return "xml"; 
			  
		  }
	
		
	}
else{
    		
    		java.lang.StringBuffer sb = new StringBuffer();
    		sb.append("<check>");
    		sb.append("<value>").append("0").append("</value>");
    		sb.append("</check>");
    		xml = sb.toString();
    		return "xml";
    	}
}
	
	
	
	
	@SuppressWarnings("unchecked")
	public String  addFriendSet() {
		   HashMap userInfo=new HashMap();
		 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	     String pusername=userInfo.get("USERNAME").toString();
		 search.put("mainuser", pusername);
		 
    
		 String uinvitetype=userInfo.get("INVITETYPE").toString();
		 search.put("uinvitetype", uinvitetype); 
		 
		  search.put("frienduser", search.get("frienduser"));
		
		  	
		   List InviteTypeList=this.userFacade.findUserInviteList(search); 
           HashMap Invite= (HashMap) InviteTypeList.get(0);
		    String InvStr= Invite.get("INVITETYPE").toString();
			  int i=0;
			  
			  
			  this.userFacade.deleteFriend(search);//不管有没有存在好友,先做删除后插入
			  
			  
			  
			  
			  
		    if (InvStr.equals("0")){ //0不需要 验证，直接被添加为好友 mainuser，frienduser
		     
			      try{
				   i= this.userFacade.insertT_ARCHIVES_FRIENDZero(search);
				    }catch (Exception e) {
						e.printStackTrace();
					}
              }
		     else if (InvStr.equals("1")){ //1需要 验证，才能被添加为好友mainuser，frienduser
		    	  try{
				   i= this.userFacade.insertT_ARCHIVES_FRIENDOne(search);
				    }catch (Exception e) {
						e.printStackTrace();
					}
                }
	
    		if(i==2){
				this.addActionMessage(this.getText("提示：已经发送邀请."));
			}else{
				this.addActionError(this.getText("提示：邀请失败"));
			}  
    		
    		
    		
    		
    		
    		pagefriendAll();
    		
	       return	 "friendSet";
	 }
		
	
	public String  checkSet() {
		
		   HashMap userInfo=new HashMap(); 
		 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	     String pusername=userInfo.get("USERNAME").toString();
		 search.put("pusername", pusername);
		 
         search.put("uinvitetype", search.get("pinvitetype"));  	
		   try{
	          	 boolean i=this.userFacade.updateUserInvitetype(search);
	          	 System.out.println(i);
	          	}catch(Exception e){
	          		e.getStackTrace();
	          	}
	          	pagefriendAll();
       	
		 return	 "friendSet";	
		
	}
	
	
	public String  deleteFriend() {
		
		    int i=this.userFacade.deleteFriend(search);
		     
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
       }
	
	
	@SuppressWarnings("unchecked")
	public String  updateAccept() {
		
		
		 try
	  		{
	  		 List onlyFriendList=this.userFacade.onlyFriendRecordList(search);
	  		 
	  		 if (!onlyFriendList.isEmpty()){
	  	     search=(HashMap)onlyFriendList.get(0);
	  		 }
	  		 
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	
		
		
		return "updateAccept";
		
	}
	public String  updateAcceptSave() {
		
		     try{
	          	 boolean i=this.userFacade.updateAcceptState(search);
	          	 System.out.println(i);
	          	}catch(Exception e){
	          		e.getStackTrace();
	          	}
	          	
	          	
		return friendSet();
	}
	
	
	//黑名单
	public void  pagefriendBlackAll() {   //显示在下面的列表
		   HashMap userInfo=new HashMap(); 
		 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	     String pusername=userInfo.get("USERNAME").toString();
	     search.put("pusername", pusername);
		 try
			{
				Pages pages = new Pages();
				pages.setPage(this.getPage());
				pages.setPerPageNum(10);
				pages.setFileName("user.do?action=pagefriendBlackAll");
				this.pageFriBlackList=this.userFacade.findFriendBlackList(search, pages);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
        }
	
	public String  friendBlack() {

		   HashMap userInfo=new HashMap(); 
		 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
         String uinvitetype=userInfo.get("INVITETYPE").toString();
		 search.put("uinvitetype", uinvitetype);

		 pagefriendBlackAll();
		 
         return  "friendBlack";
     }
	
	
	@SuppressWarnings("unchecked")
	public String  isExistBlackUser() {
		
		   HashMap userInfo=new HashMap(); 
		 userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	     String mainuser=userInfo.get("USERNAME").toString();
	     
	     search.put("mainuser", mainuser);
	     System.out.print(search.get("frienduser"));
	     
	     
	        List isExistFriendList=this.userFacade.findFriendUserList(search);
	    	if(isExistFriendList.isEmpty()){
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<check>");
			sb.append("<value>").append("此用户不是你的好友,不能添加黑名单!").append("</value>");
			sb.append("</check>");
			xml = sb.toString();
			return "xml";
		   }
	    	else{
	    		
	    		java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<check>");
				sb.append("<value>").append("0").append("</value>");
				sb.append("</check>");
				xml = sb.toString();	
                 return "xml";	
	    	}
	    	}
	
	@SuppressWarnings("unchecked")//点击添加的时候更新state状态
	public String  setFriendBlack() {
		   HashMap userInfo=new HashMap();
		  userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	      String mainuser=userInfo.get("USERNAME").toString();
	     
	     search.put("mainuser", mainuser);
	     System.out.print(search.get("frienduser"));
	     
	    
	      try{
          	 boolean i=this.userFacade.updateBlackState(search);
          	 System.out.println(i);
          	}catch(Exception e){
          		e.getStackTrace();
          	}
	     
     	pagefriendBlackAll() ;
		return  "friendBlack";
	}
	
	//删除
	public String  deleteFriendBlack() {
		
	    int i=this.userFacade.deleteFriend(search);
	     
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
   }

	
	
	
	
	
	
	//好友设置
	
	
	//手机注册
	public String  mobileReg() {	
		return "mobileRegedit";
	}
	
	//手机验证是否重复用户
	public String mobileValu(){
		
		
        if (search.get("pusername").toString().trim()==""){
        	java.lang.StringBuffer sb = new StringBuffer();
    		sb.append("<check>");
    		sb.append("<value>").append("用户名不能为空,请填写").append("</value>");
    		sb.append("</check>");
    		xml = sb.toString();
    		return "xml";
		}
		
    	int i=0;
    	isExistList=this.userFacade.isExist(search,null);
    	if(!isExistList.getObjectList().isEmpty()){i=1;}
    	if(i==1)
    	{
    	java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("此用户已经被注册了").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		return "xml";
    	}
    	else{
    		java.lang.StringBuffer sb = new StringBuffer();
    		sb.append("<check>");
    		sb.append("<value>").append("0").append("</value>");
    		sb.append("</check>");
    		xml = sb.toString();
    		return "xml";
    	}
    	
	}
	
	
	@SuppressWarnings("unchecked")
	public String  mobileRegInsert() {	
		
		
		
		 java.util.Random r=new java.util.Random();  
         search.put("pwd", Math.abs(r.nextInt()));
         
		       int i=0;
		      try{
			   i= this.userFacade.insertMobileT_ARCHIVES_USER(search);//手机插入用户
			    }catch (Exception e) {
					e.printStackTrace();
				}
			    
			        
			 if (i==1){
				 search.put("message", "你已经成功注册用户为:"+search.get("pusername")+"<br/>初始密码为:"+search.get("pwd"));
			 }
			 else{
				 search.put("message", "可能网络原因引起注册失败,请重试。");
				 }
			    
			    
		return "mobileReg";
        }
	
	public String  position() {	
		return "position";
	}
	
	public String  positionSave() {	
		 
		    try{
	          	 boolean i=this.userFacade.updateNewUserPosition(search);
	          	 System.out.println(i);
	          	}catch(Exception e){
	          		e.getStackTrace();
	          	}
	          	
	          	
		
		return "position";
		
	}
	
	
	
	
	
	
	
	
    //手机注册
	
	
	
	///////////////
	
	public String  categories() {	
		
		
		
		try
		{
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			//pages.setPerPageNum(10);
			pages.setFileName("user.do?action=categories");
			this.categoriesList=this.userFacade.findCategoriesList(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	
		  if (search.get("CID")!=null){
		  try
	  		{
	  		 List onlyCateList=this.userFacade.onlyCategoriesList(search);
	  		 
	  		 if (!onlyCateList.isEmpty()){
	  	     search=(HashMap)onlyCateList.get(0);
	  		 }
	  		 }
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
		  }
	  		
	  		
     return "categories";	
	}
	
	public String  addCate() {	
		
		     try{
			   this.userFacade.insertT_ARCHIVES_CATEGORIES(search);
			    }catch (Exception e) {
					e.printStackTrace();
				}
		
		
		return categories();
	}
	
	
	public String  modifyCate() {	
		
		   try{
	          	 boolean i=this.userFacade.updateT_ARCHIVES_CATEGORIES(search);
	          	 System.out.println(i);
	          	}catch(Exception e){
	          		e.getStackTrace();
	          	}
	          	
		
	
		return categories();
	}
	
	@SuppressWarnings("unchecked")
	public String  deleteCateExist() {	
		
		
	    List isCategoriesList=this.userFacade.findExistCategoriesList(search);
    	if(!isCategoriesList.isEmpty()){
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<check>");
		sb.append("<value>").append("当前选中节点有子节点存在,不能删除!").append("</value>");
		sb.append("</check>");
		xml = sb.toString();
		return "xml";
	   }
    	else{
    		
    		java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<check>");
			sb.append("<value>").append("0").append("</value>");
			sb.append("</check>");
			xml = sb.toString();	
             return "xml";	
    	}
		
		
		
		//return categories();
	}
	
	public String  deleteCate() {	
		
		  this.userFacade.deleteCate(search);
		return categories();	
	}
	
	@SuppressWarnings("unchecked")
	public String  addCateTop() {	
		

		search.put("CATEGORIESNAME", search.get("CATEGORIESNAMETop"));
		search.put("FID", "0");
		
		   try{
			   this.userFacade.insertT_ARCHIVES_CATEGORIES(search);
			    }catch (Exception e) {
					e.printStackTrace();
				}

	return categories();
	}
	
	//分类
	
    
	@SuppressWarnings("unchecked")
	public List getTestList() {
		return testList;
	}

	@SuppressWarnings("unchecked")
	public void setTestList(List testList) {
		this.testList = testList;
	}

	public PageList getPageList() {
		return pageList;
	}



	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}


	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}



	public PageList getUserOnlyList() {
		return userOnlyList;
	}

	public void setUserOnlyList(PageList userOnlyList) {
		this.userOnlyList = userOnlyList;
	}

	public PageList getUserOnly_ExList() {
		return userOnly_ExList;
	}

	public void setUserOnly_ExList(PageList userOnly_ExList) {
		this.userOnly_ExList = userOnly_ExList;
	}


	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public PageList getPageUserList() {
		return pageUserList;
	}

	public void setPageUserList(PageList pageUserList) {
		this.pageUserList = pageUserList;
	}

	public PageList getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(PageList provinceList) {
		this.provinceList = provinceList;
	}

	public PageList getCityList() {
		return cityList;
	}

	public void setCityList(PageList cityList) {
		this.cityList = cityList;
	}

	public PageList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(PageList districtList) {
		this.districtList = districtList;
	}

	public PageList getIsExistloginCorrect() {
		return isExistloginCorrect;
	}

	public void setIsExistloginCorrect(PageList isExistloginCorrect) {
		this.isExistloginCorrect = isExistloginCorrect;
	}

	public PageList getIsExistMerchant() {
		return isExistMerchant;
	}

	public void setIsExistMerchant(PageList isExistMerchant) {
		this.isExistMerchant = isExistMerchant;
	}

	public PageList getMerchantMemberList() {
		return merchantMemberList;
	}

	public void setMerchantMemberList(PageList merchantMemberList) {
		this.merchantMemberList = merchantMemberList;
	}

	public PageList getIsExistPwdCorrect() {
		return isExistPwdCorrect;
	}

	public void setIsExistPwdCorrect(PageList isExistPwdCorrect) {
		this.isExistPwdCorrect = isExistPwdCorrect;
	}

	public PageList getPageMemberList() {
		return pageMemberList;
	}

	public void setPageMemberList(PageList pageMemberList) {
		this.pageMemberList = pageMemberList;
	}

	

	public PageList getTribalList() {
		return tribalList;
	}

	public void setTribalList(PageList tribalList) {
		this.tribalList = tribalList;
	}

	public PageList getChatRecordList() {
		return chatRecordList;
	}

	public void setChatRecordList(PageList chatRecordList) {
		this.chatRecordList = chatRecordList;
	}

	public PageList getSearchUserList() {
		return searchUserList;
	}

	public void setSearchUserList(PageList searchUserList) {
		this.searchUserList = searchUserList;
	}

	public PageList getPageProductList() {
		return pageProductList;
	}

	public void setPageProductList(PageList pageProductList) {
		this.pageProductList = pageProductList;
	}

	public PageList getPageFavourableList() {
		return pageFavourableList;
	}

	public void setPageFavourableList(PageList pageFavourableList) {
		this.pageFavourableList = pageFavourableList;
	}

	public PageList getSearchAppealList() {
		return searchAppealList;
	}

	public void setSearchAppealList(PageList searchAppealList) {
		this.searchAppealList = searchAppealList;
	}

	public PageList getIfExistCreate() {
		return ifExistCreate;
	}

	public void setIfExistCreate(PageList ifExistCreate) {
		this.ifExistCreate = ifExistCreate;
	}

	public PageList getPageFriendList() {
		return pageFriendList;
	}

	public void setPageFriendList(PageList pageFriendList) {
		this.pageFriendList = pageFriendList;
	}

	public PageList getPageFriBlackList() {
		return pageFriBlackList;
	}

	public void setPageFriBlackList(PageList pageFriBlackList) {
		this.pageFriBlackList = pageFriBlackList;
	}

	public PageList getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(PageList categoriesList) {
		this.categoriesList = categoriesList;
	}

	public PageList getSearchGroupList() {
		return searchGroupList;
	}

	public void setSearchGroupList(PageList searchGroupList) {
		this.searchGroupList = searchGroupList;
	}


	public LoginFacade getLoginFacade() {
		return loginFacade;
	}


	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}


}
