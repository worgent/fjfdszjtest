package com.qzgf.application.archives.space.action;



import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.space.domain.SpaceFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * �ռ�����
 * @author swq
 *com.qzgf.webutils.interceptor.MapParametersInterceptor
 */
@SuppressWarnings("serial")
public class SpaceAction extends BaseAction {
	Log log = LogFactory.getLog(SpaceAction.class);

	@SuppressWarnings("unchecked")

	private SpaceFacade spaceFacade;
	@SuppressWarnings("unchecked")
	private List spaceList;
	private PageList pageList;
	@SuppressWarnings("unchecked")
	private PageList pageOnlyList;
	
	private String spaceName;//�ռ����
	private String spaceBulletin;//����
	private String showArlticleNum;//��ʾ������
	
	private String spaceStyle;//�ռ���
	private String anonReversion;//��ʾ�ظ�
	private String showCaller;//��ʾ�ÿ�
	private String showFriend;//��ʾ����
	private String showBulletin;//��ʾ����
	
	
	private String xml;
	
	

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			//log.error(e.printStackTrace());
			e.printStackTrace();
			return "index";
		}
	}

	@SuppressWarnings("unchecked")
	public String index() {

	/*	Pages pages = new Pages();
		pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
		pages.setPerPageNum(10); //����ÿҳ��С
		//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("space.do?action=" + this.getAction());
		try{
		this.setPageList(this.spaceFacade.findSpace(search, pages));
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		    HashMap userInfo=new HashMap();
		    userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	        String pusername=userInfo.get("USERNAME").toString();
			search.put("pusername", pusername);
			
	    
		   List ExistSpaceList=this.spaceFacade.isExistSpaceList(search);
	 		 if (!ExistSpaceList.isEmpty()){//不等于空，说明已经存在空间配置,更新一下空间
	 			  search=(HashMap)ExistSpaceList.get(0);
	 			  }
		
		return "index";
	}
	public String  spaceSet() {
		
		    HashMap userInfo=new HashMap();
		    userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
	        String pusername=userInfo.get("USERNAME").toString();
			search.put("pusername", pusername);
		
		
    
	 			   try {
	 				 this.spaceFacade.deleteSpace(search);  //先删除后插入
	 				  spaceFacade.insertSpace(search);
	 			    }catch (Exception e) {
	 					e.printStackTrace();
	 				} 
	 		
	 			    
	 			   List ExistSpaceList=this.spaceFacade.isExistSpaceList(search);
	 		 		 if (!ExistSpaceList.isEmpty()){//不等于空，说明已经存在空间配置,更新一下空间
	 		 			  search=(HashMap)ExistSpaceList.get(0);
	 		 			  }
	 		 		 

	    /*	///////////////////////////
	    Pages pages = new Pages();
		pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
		pages.setPerPageNum(10); //����ÿҳ��С
		//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("space.do?action=" + this.getAction());
		this.setPageList(this.spaceFacade.findSpace(search, pages));*/
	    
	    
		return "index";	
	    ///////////////////////////
	
	}
	
	public String  list() {
		
		Pages pages = new Pages();
		pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
		pages.setPerPageNum(10); //����ÿҳ��С
		//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("space.do?action=" + this.getAction());
		this.setPageList(this.spaceFacade.findSpace(search, pages));
		return "indexList";	
		
		}
	
	public String  delete() {
		int i=0;
		try {
			i=spaceFacade.deleteSpaceById(search);
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
		
		
		//this.setAction("list");
		//return list();
	/*	
		
		Pages pages = new Pages();
		pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
		pages.setPerPageNum(10); //����ÿҳ��С
		//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("space.do?action=list");
		this.setPageList(this.spaceFacade.findSpace(search, pages));
	
		return "indexList";	*/
		
	}
	@SuppressWarnings("unchecked")
	public String  edit() {
		
		System.out.println(search.get("SPACEID"));

		search.put("SPACEID", search.get("SPACEID"));
		this.pageOnlyList=this.spaceFacade.pageOnlyOneList(this.getSearch(), null);
		
		return "editPage";
	}
	
	@SuppressWarnings("unchecked")
	public String  editSave() {
		
		System.out.println(search.get("SPACEID"));
		System.out.println(search.get("pspaceName"));
		
		System.out.println(search.get("panonReversion"));
		
		if(search.get("panonReversion")==null){
			search.put("panonReversion", "0");
		}
		
		if(search.get("pshowCaller")==null){
			search.put("pshowCaller", "0");
		}
		
		if(search.get("pshowFriend")==null){
			search.put("pshowFriend", "0");
		}
		
		if(search.get("pshowBulletin")==null){
			search.put("pshowBulletin", "0");
		}
		

		try
		{
		this.spaceFacade.updateSpaceById(search);
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		try
		{
			
			Pages pages = new Pages();
			pages.setPage(this.getPage());//Ĭ�ϵ�һҳ
			pages.setPerPageNum(5); //����ÿ����ʾ�������Ϊ���
			//����fileName��Ϊ�˷��ص�ǰ̨���ҳ����ת
			//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
			pages.setFileName("space.do?action=list");
			this.pageList=this.spaceFacade.findSpace(search, pages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		   
		return "indexList"; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String  add() {
		
		return "index";
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the rewardFacade
	 */

	public SpaceFacade getSpaceFacade() {
		return spaceFacade;
	}

	public void setSpaceFacade(SpaceFacade spaceFacade) {
		this.spaceFacade = spaceFacade;
	}

	@SuppressWarnings("unchecked")
	public List getSpaceList() {
		return spaceList;
	}

	@SuppressWarnings("unchecked")
	public void setSpaceList(List spaceList) {
		this.spaceList = spaceList;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getSpaceBulletin() {
		return spaceBulletin;
	}

	public void setSpaceBulletin(String spaceBulletin) {
		this.spaceBulletin = spaceBulletin;
	}

	public String getShowArlticleNum() {
		return showArlticleNum;
	}

	public void setShowArlticleNum(String showArlticleNum) {
		this.showArlticleNum = showArlticleNum;
	}

	public String getSpaceStyle() {
		return spaceStyle;
	}

	public void setSpaceStyle(String spaceStyle) {
		this.spaceStyle = spaceStyle;
	}

	public String getAnonReversion() {
		return anonReversion;
	}

	public void setAnonReversion(String anonReversion) {
		this.anonReversion = anonReversion;
	}

	public String getShowCaller() {
		return showCaller;
	}

	public void setShowCaller(String showCaller) {
		this.showCaller = showCaller;
	}

	public String getShowFriend() {
		return showFriend;
	}

	public void setShowFriend(String showFriend) {
		this.showFriend = showFriend;
	}

	public String getShowBulletin() {
		return showBulletin;
	}

	public void setShowBulletin(String showBulletin) {
		this.showBulletin = showBulletin;
	}


	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public void setPageOnlyList(PageList pageOnlyList) {
		this.pageOnlyList = pageOnlyList;
	}

	public PageList getPageOnlyList() {
		return pageOnlyList;
	}


}
