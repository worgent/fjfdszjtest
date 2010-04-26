package com.qzgf.application.selfConfig.bulletin.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.bulletin.domain.BulletinFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
		2009-11-07信息公告 
		控制面板｜信息公告(优惠劵的发布，狩猎贴，领主招纳信息)
 */
@SuppressWarnings("serial")
public class BulletinAction extends BaseAction {
	Log log = LogFactory.getLog(BulletinAction.class);
	private BulletinFacade bulletinFacade;		//实现类接口
	@SuppressWarnings("unchecked")
	private List bulletinList;					//信息公告
	private HashMap bulletin =new HashMap();
//	private List repbulletinList; 				//信息反馈(暂时不用明细信息)
//	private HashMap repbulletin =new HashMap();
	private PageList pageList;
	private String xml;

	// 读取用户的配置信息2009-11-24
	private HashMap userInfo;
	
	public String execute() {
		try {
			// 从session中读取用户信息
			userInfo=(HashMap)(ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
			//当匿名登录时
			if(userInfo==null)
			{
				HashMap tmp=new HashMap();
				tmp.put("USERID","0");
				userInfo=tmp;
			}
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}
	//前台
	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//设置fileName是为了返回到前台后的页面跳转
		pages.setFileName("bulletin.do?action=index&search.ptype="+search.get("ptype").toString());
		this.setPageList(this.bulletinFacade.findBulletinPage(search, pages));
		return "index"+search.get("ptype").toString();
		//index1:优惠劵,index2:狩猎贴,index3:领主招纳 
	}
	//关注数的处理(留下操作痕迹)
	public String countReceve()
	{
		//先取得当前关注数
		bulletin=(HashMap)this.bulletinFacade.findBulletin(search).get(0);		//商家信息
		//三种不同的方式处理不同(达到优惠劵总数，不能下载，招纳关注仍可以)
		
		
		
		//关注数加１
		HashMap map=new HashMap();
		map.put("pid", bulletin.get("ID").toString());
		try{
		map.put("pcountreceve", ((Number)bulletin.get("COUNTRECEVE")).intValue()+1);
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}

		int i=this.bulletinFacade.updateBulletinById(map);
		
		
		//记录参与者信息(取用户信息)2009-11-09
		map.clear();
		map.put("pbulletinid", bulletin.get("ID").toString());
		map.put("pbulletincontent", bulletin.get("BULLETINCONTENT").toString());
		//参与者编号
		map.put("puserid", userInfo.get("USERID").toString());
		i=i+this.bulletinFacade.insertRepBulletin(map);

		//转到列表页面
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
	}
	
	
	//后台
    //列表(得到列表)
	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		pages.setFileName("bulletin.do?action=list&search.ptype="+search.get("ptype").toString());
		//参与者编号,过滤发布者信息 
		search.put("puserid", userInfo.get("USERID").toString());
		
		this.setPageList(this.bulletinFacade.findBulletinPage(search, pages));
		return "list"+search.get("ptype").toString();
	}
	
	//参与明细
	public String replist() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		pages.setFileName("bulletin.do?action=replist");
		this.setPageList(this.bulletinFacade.findRepBulletinPage(search, pages));
		return "replist";
	}
	
	//新增页面
	public String add() {
		this.setAction("insert");
		return "edit"+search.get("ptype").toString();
	}

	//增加数据
	public String insert() {
		String type=search.get("ptype").toString();
		//参与者编号,发布者信息
		search.put("puserid", userInfo.get("USERID").toString());
		int i=this.bulletinFacade.insertBulletin(search);
		search.clear();
		search.put("ptype", type);
		return list();
	}
	
	
	//编辑时取后台数据信息（得到单行记录）
	public String edit() {
		bulletin=(HashMap)this.bulletinFacade.findBulletin(search).get(0);		//商家信息
		this.setAction("update");
    	return "edit"+search.get("ptype").toString();
	}
	
	//数据更新的处理（得到单行记录）
	public String update() {
		String type=search.get("ptype").toString();
		int i=this.bulletinFacade.updateBulletinById(search);
		if(i==1)
		{
			this.addActionMessage(this.getText("更新成功!"));
		}else
		{
			this.addActionError(this.getText("更新失败!"));
		}
		//转到列表页面
		search.clear();
		search.put("ptype", type);
		return list();
	}
	//删除记录
	public String delete() {
		//主表
		int i=this.bulletinFacade.deleteBulletinById(search);
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
	}
	
	
	//查看信息
	public String view() {
		bulletinList=this.bulletinFacade.findBulletin(search);
		bulletin=(HashMap)bulletinList.get(0);
		return "view"+search.get("ptype").toString();
	}
	
	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the messageFacade
	 */
	
	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}
	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}
	/**
	 * @return the bulletinFacade
	 */
	public BulletinFacade getBulletinFacade() {
		return bulletinFacade;
	}
	/**
	 * @param bulletinFacade the bulletinFacade to set
	 */
	public void setBulletinFacade(BulletinFacade bulletinFacade) {
		this.bulletinFacade = bulletinFacade;
	}
	/**
	 * @return the bulletinList
	 */
	public List getBulletinList() {
		return bulletinList;
	}
	/**
	 * @param bulletinList the bulletinList to set
	 */
	public void setBulletinList(List bulletinList) {
		this.bulletinList = bulletinList;
	}
	/**
	 * @return the bulletin
	 */
	public HashMap getBulletin() {
		return bulletin;
	}
	/**
	 * @param bulletin the bulletin to set
	 */
	public void setBulletin(HashMap bulletin) {
		this.bulletin = bulletin;
	}
}
