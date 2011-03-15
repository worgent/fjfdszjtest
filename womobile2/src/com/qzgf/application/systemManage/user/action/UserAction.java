package com.qzgf.application.systemManage.user.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseMainAction;
import com.qzgf.application.systemManage.role.domain.RoleFacade;
import com.qzgf.application.systemManage.user.domain.UserFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.Util;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.utils.ui.OptionsString;
import com.qzgf.webutils.interceptor.SessionAware;

@SuppressWarnings("serial")
public class UserAction extends BaseMainAction implements SessionAware{
	Log log = LogFactory.getLog(UserAction.class);
	private String basePath;
	private PageList pageList;
	private AjaxMessagesJson ajaxMessagesJson;
	private UserFacade userFacade;
	private List<Integer> uploadIDs = new ArrayList<Integer>();
	private String curMenu;
	private String branchId;
	private String branchName;
	@SuppressWarnings("unchecked")
	private Map sexMap = new HashMap();
	@SuppressWarnings("unchecked")
	private Map uploadMap=new HashMap();
	private String perentBranchId;
	private String menuTree;// 机构树
	private RoleFacade roleFacade;
	private UserSession userSession;
	private List<OptionsString> groupValues = new ArrayList<OptionsString>();
	
	//机构列表，用于修改
	private List<OptionsString> branchValues=new ArrayList<OptionsString>();
	// 职称
	private List<OptionsString> zhichengValues = new ArrayList<OptionsString>();
	// 职务
	private List<OptionsString> dutyValues = new ArrayList<OptionsString>();

	/** **********************用户信息*************************** */
	private String id;
	private String userId;// 用户编号
	private String groupId;// 用户组编号
	private String userName;// 用户名
	private String passwd;// 密码
	private String repasswd;// 重复密码
	private String zhicheng;// 职称
	private String duty;// 职务
	private String sex;// 性别
	private String realName;// 真实姓名
	private String mobilephone;// 移动电话
	private String workphone;// 工作电话
	private String email;// 邮箱
	private String birthday;// 生日
	private String address;// 联系地址
	private String postId;// 邮编
	private String accountBank;// 开户银行
	private String accountId;// 银行账号
	private String remark;// 备注
	

	/** **********************用户信息*************************** */

	@SuppressWarnings("unchecked")
	public String getBranch() {
		// 机构树
		Map map = new HashMap();
		map.put("branchId",this.getUserSession().getBranchId() );
		@SuppressWarnings("unused")
		List menuList = userFacade.findSubBranchById(map);
		StringBuffer menustr = new StringBuffer();
		menustr.append("<script type='text/javascript'>");
		menustr.append("var d1 = new dTree('d1','images/system/dept/');");
		menustr.append("d1.config.target = 'user_admin_mainFrame';");
		Map menuitem = new HashMap();
		java.util.Iterator it = menuList.iterator();
		int num = 0;
		while (it.hasNext()) {
			menuitem = (HashMap) it.next();
			if (num == 0) {
				menustr.append("d1.add(" + menuitem.get("branchId") + ",-1,'"
						+ menuitem.get("branchName")
						+ "','user.do?action=toUserMark&branchId="
						+ menuitem.get("branchId") + "');");
			} else {
				menustr.append("d1.add(" + menuitem.get("branchId") + ","
						+ menuitem.get("parentBranchId") + ",'"
						+ menuitem.get("branchName")
						+ "','user.do?action=toUserMark&branchId="
						+ menuitem.get("branchId") + "');");
			}
			num = 1;
		}

		menustr.append("document.write(d1);");
		menustr.append("</script>");

		menuTree = menustr.toString();

		return "left";
	}

	
	@SuppressWarnings("unchecked")
	public String getUser() {
		// 机构树
		Map map = new HashMap();
		map.put("branchId",this.getUserSession().getBranchId() );
		@SuppressWarnings("unused")
		List menuList = userFacade.findSubBranchUserById(map);
		StringBuffer menustr = new StringBuffer();//菜单
		StringBuffer checkstr = new StringBuffer();//是否选中
		menustr.append("<script type='text/javascript'>");
		menustr.append("var d1 = new dTree('d1','images/system/dept/');");
		menustr.append("d1.config.folderLinks=true;");
		menustr.append("d1.config.useCookies=false;");
		menustr.append("d1.config.check=true;");
		Map menuitem = new HashMap();
		java.util.Iterator it = menuList.iterator();
		boolean flag = true;
		//查看树型,全部
		while (it.hasNext()) {
			menuitem = (HashMap) it.next();
			if (flag) {
					menustr.append("d1.add(\'" + menuitem.get("branchId") + "\',-1,'"
							+ menuitem.get("branchName")
							+ "');");
				flag=false;
			} else {
					menustr.append("d1.add(\'" + menuitem.get("branchId") + "\',\'"
							+ menuitem.get("parentBranchId") + "\','"
							+ menuitem.get("branchName")
							+ "');");
			}
		}
		//测试
		//search.put("type","1");
		//search.put("pid","2011011700000040");
		
		List nowlist=null;
		if(search.get("type").equals("1")){
			nowlist=userFacade.findworkexcute(search);
		}else if(search.get("type").equals("2")){
			nowlist=userFacade.findtomonitorexcute(search);
		}else if(search.get("type").equals("3")){
			nowlist=userFacade.finddeclareexcute(search);
		}
		int len=nowlist.size();
		String brand="";
		for(int i=0;i<len;i++){
			HashMap nowhs= (HashMap) nowlist.get(i);
			//员工的编号加上 前缀0
			checkstr.append(",{menudm:'" + nowhs.get("id")+ "_ex'}");
			
			if(nowhs.get("brand").toString().equals(brand)){
				continue;
			}
			brand=nowhs.get("brand").toString();
			String superbranch=userFacade.findSuperBranch(nowhs.get("brand").toString());
			String[] superbrancharr=superbranch.split(",");
			for(String now:superbrancharr){
				checkstr.append(",{menudm:'" + now+ "'}");
			}
		}
		//处理数据
		String data=checkstr.toString();
		if(data.length()>0){
			data=data.substring(1, data.length());
		}
		menustr.append("var funcs = eval(({funcs:[" + data + "]}));");
		menustr.append("document.write(d1);");
		menustr.append("</script>");
		menuTree = menustr.toString();
		return "user";
	}
	
	
	@SuppressWarnings("unchecked")
	public String index() {
		return SUCCESS;
	}

	// 查询用户列表
	@SuppressWarnings("unchecked")
	public String list() {
		Pages pages = new Pages();
		if(branchId!=null&&!branchId.equals("")){
			search.put("branchId", branchId);
		}else{
			search.put("branchId", this.getUserSession().getBranchId());//需要根据创建者的所属机构
			branchId="0";
		}
		pages.setPage(this.getPage());// 默认第一页
		pages.setPerPageNum(10); // 设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		pages.setFileName("user.do");
		this.pageList = this.userFacade.findUser(search, pages);
		return "list";
	}

	public String toUserMark() {
		return "userMark";
	}

	@SuppressWarnings("unchecked")
	public String listBranch() {
		return "treemenu";
	}

	// 加载添加用户页面
	@SuppressWarnings("unchecked")
	public String add() {
		this.setAction("addsave");

		// 性别
		sexMap.put(0, "未知");
		sexMap.put(1, "男");
		sexMap.put(2, "女");

		// 机构树
		Map map = new HashMap();
		map.put("branchId",this.getUserSession().getBranchId() );
		@SuppressWarnings("unused")
		List menuList = userFacade.findSubBranchForChangeById(map);
		for (int i = 0; i < menuList.size(); i++) {
			Map p = (Map) menuList.get(i);
			branchValues.add(new OptionsString("" + p.get("branchId"), (String) p
					.get("branchName")));
		}
		
		// 只能查看或修改自己创建的组
		List groupList = this.getUserFacade().findGroupsAll(this.getUserSession().getUserId());
		for (int i = 0; i < groupList.size(); i++) {
			Map p = (Map) groupList.get(i);
			groupValues.add(new OptionsString("" + p.get("groupId"), (String) p
					.get("groupName")));
		}

		// 查询职称
		List zhichengList = this.getUserFacade().findDictionaryBySysId("2002");
		for (int i = 0; i < zhichengList.size(); i++) {
			Map p = (Map) zhichengList.get(i);
			zhichengValues.add(new OptionsString("" + p.get("id"), (String) p
					.get("dictvalue")));
		}

		// 查询职务
		List dutyList = this.getUserFacade().findDictionaryBySysId("2003");
		for (int i = 0; i < dutyList.size(); i++) {
			Map p = (Map) dutyList.get(i);
			dutyValues.add(new OptionsString("" + p.get("id"), (String) p
					.get("dictvalue")));
		}
		return "add";
	}

	/**
	 * 添加用户
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String addsave() {
		try {
			search.put("repasswd", Util.hash((String)search.get("passwd")));
			search.put("creator", this.getUserSession().getUserId());
			try {
				search.put("userName", URLDecoder.decode((String)search.get("userName"), "UTF-8"));
				search.put("realName", URLDecoder.decode((String)search.get("realName"), "UTF-8"));
				search.put("address", URLDecoder.decode((String)search.get("address"), "UTF-8"));
				search.put("accountBank", URLDecoder.decode((String)search.get("accountBank"), "UTF-8"));
				search.put("remark", URLDecoder.decode((String)search.get("remark"), "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
		} catch (Exception e1) {
			log.info(e1);
		}
		try {
			this.userFacade.insertUser(search);
			this.getAjaxMessagesJson().setMessage("0", "用户添加成功");
		} catch (Exception e) {
			log.error(e);
			this.getAjaxMessagesJson().setMessage("E_NOTE_ADDFAILED", "用户添加失败");
		}
		return RESULT_AJAXJSON;
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String del(){
		
		search.put("id",id);
		
		try {
			int i=this.userFacade.deleteUserById(search);
			if(i==1){
			this.getAjaxMessagesJson().setMessage("0", "用户删除成功");
			}else{
				this.getAjaxMessagesJson().setMessage("1", "用户删除失败");
			}
		} catch (Exception e) {
			log.error(e);
			this.getAjaxMessagesJson().setMessage("1", "用户删除失败");
		}
		return RESULT_AJAXJSON;
	}
	
	/**
	 * 进入用户编辑页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String edit(){
		this.setAction("editdo");
		search.put("id",id);
		search=this.userFacade.findUserById(id);
		
		// 机构树
		Map map = new HashMap();
		map.put("branchId",this.getUserSession().getBranchId() );
		@SuppressWarnings("unused")
		List menuList = userFacade.findSubBranchForChangeById(map);
		for (int i = 0; i < menuList.size(); i++) {
			Map p = (Map) menuList.get(i);
			branchValues.add(new OptionsString("" + p.get("branchId"), (String) p
					.get("branchName")));
		}
		
		
		// 性别
		sexMap.put(0, "未知");
		sexMap.put(1, "男");
		sexMap.put(2, "女");

		// 只能查看或修改自己创建的组
		List groupList = this.getUserFacade().findGroupsAll(this.getUserSession().getUserId());
		for (int i = 0; i < groupList.size(); i++) {
			Map p = (Map) groupList.get(i);
			groupValues.add(new OptionsString("" + p.get("groupId"), (String) p
					.get("groupName")));
		}

		// 查询职称
		List zhichengList = this.getUserFacade().findDictionaryBySysId("2002");
		for (int i = 0; i < zhichengList.size(); i++) {
			Map p = (Map) zhichengList.get(i);
			zhichengValues.add(new OptionsString("" + p.get("id"), (String) p
					.get("dictvalue")));
		}

		// 查询职务
		List dutyList = this.getUserFacade().findDictionaryBySysId("2003");
		for (int i = 0; i < dutyList.size(); i++) {
			Map p = (Map) dutyList.get(i);
			dutyValues.add(new OptionsString("" + p.get("id"), (String) p
					.get("dictvalue")));
		}
		
		return "edit";
	}
	
	/**
	 * 进入考核设置页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editCheckWork(){
		search.put("id",id);
		search=this.userFacade.findUserById(id);
		String uploadIds=(String)search.get("uploadIds");
		if(uploadIds!=null&&uploadIds!=""){
			String[] uploadId=uploadIds.split(",");
			for(int i=0;i<uploadId.length;i++){
				uploadIDs.add(Integer.parseInt((String)uploadId[i])); 
			}
			search.put("uploadIDS", uploadIDs);
		}
		
		//上传设置
		uploadMap.put(1, "周一");
		uploadMap.put(2, "周二");
		uploadMap.put(3, "周三");
		uploadMap.put(4, "周四");
		uploadMap.put(5, "周五");
		uploadMap.put(6, "周六");
		uploadMap.put(7, "周日");
		return "checkwork";
	}
	
	/**
	 * 考核设置保存
	 * @return
	 */           
	public String saveCheckWork(){
		try {
			this.userFacade.updateCheckWorkById(search);
			this.getAjaxMessagesJson().setMessage("0", "考勤设置成功");
		} catch (Exception e) {
			log.error(e);
			this.getAjaxMessagesJson().setMessage("1", "考勤设置失败");
		}
		return RESULT_AJAXJSON;
		
	}
	
	/**
	 * 保存编辑用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String editdo(){
		String passwd=(String)search.get("passwd");
		if(passwd!=null&&!passwd.equals("")){
		search.put("repasswd", Util.hash((String)search.get("passwd")));
		}
		search.put("creator", this.getUserSession().getUserId());
		
		try {
			search.put("userName", URLDecoder.decode((String)search.get("userName"), "UTF-8"));
			search.put("realName", URLDecoder.decode((String)search.get("realName"), "UTF-8"));
			search.put("address", URLDecoder.decode((String)search.get("address"), "UTF-8"));
			search.put("accountBank", URLDecoder.decode((String)search.get("accountBank"), "UTF-8"));
			search.put("remark", URLDecoder.decode((String)search.get("remark"), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			this.userFacade.updateUserById(search);
			this.getAjaxMessagesJson().setMessage("0", "用户更新成功");
		} catch (Exception e) {
			log.error(e);
			this.getAjaxMessagesJson().setMessage("1", "用户更新失败");
		}
		return RESULT_AJAXJSON;
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	@SuppressWarnings("unchecked")
	public Map getSexMap() {
		return sexMap;
	}

	@SuppressWarnings("unchecked")
	public void setSexMap(Map sexMap) {
		this.sexMap = sexMap;
	}

	public String getPerentBranchId() {
		return perentBranchId;
	}

	public void setPerentBranchId(String perentBranchId) {
		this.perentBranchId = perentBranchId;
	}

	public String getCurMenu() {
		return curMenu;
	}

	public void setCurMenu(String curMenu) {
		this.curMenu = curMenu;
	}

	public List<OptionsString> getGroupValues() {
		return groupValues;
	}

	public void setGroupValues(List<OptionsString> groupValues) {
		this.groupValues = groupValues;
	}

	public String getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(String menuTree) {
		this.menuTree = menuTree;
	}

	public RoleFacade getRoleFacade() {
		return roleFacade;
	}

	public void setRoleFacade(RoleFacade roleFacade) {
		this.roleFacade = roleFacade;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public List<OptionsString> getZhichengValues() {
		return zhichengValues;
	}

	public void setZhichengValues(List<OptionsString> zhichengValues) {
		this.zhichengValues = zhichengValues;
	}

	public List<OptionsString> getDutyValues() {
		return dutyValues;
	}

	public void setDutyValues(List<OptionsString> dutyValues) {
		this.dutyValues = dutyValues;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRepasswd() {
		return repasswd;
	}

	public void setRepasswd(String repasswd) {
		this.repasswd = repasswd;
	}

	public String getZhicheng() {
		return zhicheng;
	}

	public void setZhicheng(String zhicheng) {
		this.zhicheng = zhicheng;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public Map getUploadMap() {
		return uploadMap;
	}

	public void setUploadMap(Map uploadMap) {
		this.uploadMap = uploadMap;
	}

	public List<Integer> getUploadIDs() {
		return uploadIDs;
	}

	public void setUploadIDs(List<Integer> uploadIDs) {
		this.uploadIDs = uploadIDs;
	}

	public List<OptionsString> getBranchValues() {
		return branchValues;
	}

	public void setBranchValues(List<OptionsString> branchValues) {
		this.branchValues = branchValues;
	}

}
