package com.qzgf.application.archives.serTime.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.archives.serTime.domain.SerTimeFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;

/**
 * 网上下单
 * 
 * 
 */
@SuppressWarnings("serial")
public class SerTimeAction extends BaseAction {
	Log log = LogFactory.getLog(SerTimeAction.class);

	private SerTimeFacade serTimeFacade;
	private HashMap serTime = new HashMap(); // 商品信息
	private List serTimeList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回


	// 入口函数
	public String execute() {

		// 从session中读取用户信息
		HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		// 当匿名登录时
		if (userInfo == null) {
			HashMap tmp = new HashMap();
			tmp.put("USERID", "0");
			userInfo = tmp;
		}
		
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			int i=serTimeFacade.countSerTime(search);
			if(i==0){
				this.setAction("insert");
				return "edit";
			}else{
				return "list";
			}
		} 
		if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("serTime.do?action=listdetail");
			setPageList(serTimeFacade.findSerTimePage(search, pages));
			//数据为空时,跳到新增页面
			if(pageList.getObjectList()==null||pageList.getObjectList().size()==0){
				this.setAction("insert");
				return "edit";
			}else{
				return "listdetail";
			}
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			this.setAction("insert");
			return "edit";
		} else if ("insert".equals(super.getAction())) {
			//初始化基本信息
			search.put("pcreate_code", userInfo.get("ID").toString());//创建人
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = serTimeFacade.insertSerTime(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			
			serTime = (HashMap) serTimeFacade.findSerTime(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = serTimeFacade.updateSerTimeById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
		} else if ("view".equals(super.getAction())) {
			serTime=((HashMap) serTimeFacade.findSerTime(search).get(0)); 
			return "view";
		} else if ("delete".equals(super.getAction())) {
			// 主表
			int i = serTimeFacade.deleteSerTimeById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("serTimeVerify".equals(super.getAction())) {
			// 验证服务时间
			int i=0;
			String stime="";
			String etime="";
			i=serTimeVerify(search);//验证时间是否符合
			List ls=serTimeGetVerify(search);//取得地址对应的服务时间
			if(i>0)
			{
				i=1;
			}else{
				i=0;
				if(ls.size()>0){
					stime=((HashMap)ls.get(0)).get("SERVERSTIME").toString();
					etime=((HashMap)ls.get(0)).get("SERVERETIME").toString();
				}
			}
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("<stime>").append(stime).append("</stime>");
			sb.append("<etime>").append(etime).append("</etime>");
			sb.append("</result>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
	}

	
	private List serTimeGetVerify(HashMap hs){
		List result=null;
		if (hs.containsKey("pbookingtime")) {
			String pservertime = hs.get("pbookingtime").toString();
			String pprovince = hs.get("pprovince").toString();
			String pcity = hs.get("pcity").toString();
			String pcounty = hs.get("pcounty").toString();
			try {
				pservertime = java.net.URLDecoder.decode(pservertime, "UTF-8");
				pprovince = java.net.URLDecoder.decode(pprovince, "UTF-8");
				pcity = java.net.URLDecoder.decode(pcity, "UTF-8");
				pcounty = java.net.URLDecoder.decode(pcounty, "UTF-8");
				hs.put("pservertime", pservertime);
				hs.put("pprovince", pprovince);
				hs.put("pcity", pcity);
				hs.put("pcounty", pcounty);
				result = serTimeFacade.serTimeGetVerify(hs);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			result=null;
		}
		return result;
		
	}

	
	private int serTimeVerify(HashMap hs){
		int result=0;
		if (hs.containsKey("pbookingtime")) {
			String pservertime = hs.get("pbookingtime").toString();
			String pprovince = hs.get("pprovince").toString();
			String pcity = hs.get("pcity").toString();
			String pcounty = hs.get("pcounty").toString();
			try {
				pservertime = java.net.URLDecoder.decode(pservertime, "UTF-8");
				pprovince = java.net.URLDecoder.decode(pprovince, "UTF-8");
				pcity = java.net.URLDecoder.decode(pcity, "UTF-8");
				pcounty = java.net.URLDecoder.decode(pcounty, "UTF-8");
				hs.put("pservertime", pservertime);
				hs.put("pprovince", pprovince);
				hs.put("pcity", pcity);
				hs.put("pcounty", pcounty);
				result = serTimeFacade.serTimeVerify(hs);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			result=0;
		}
		return result;
		
	}

	/**
	 * @return the pageList
	 */
	public PageList getPageList() {
		return pageList;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

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
	 * @return the serTimeFacade
	 */
	public SerTimeFacade getSerTimeFacade() {
		return serTimeFacade;
	}



	/**
	 * @param serTimeFacade the serTimeFacade to set
	 */
	public void setSerTimeFacade(SerTimeFacade serTimeFacade) {
		this.serTimeFacade = serTimeFacade;
	}



	/**
	 * @return the serTime
	 */
	public HashMap getSerTime() {
		return serTime;
	}



	/**
	 * @param serTime the serTime to set
	 */
	public void setSerTime(HashMap serTime) {
		this.serTime = serTime;
	}



	/**
	 * @return the serTimeList
	 */
	public List getSerTimeList() {
		return serTimeList;
	}



	/**
	 * @param serTimeList the serTimeList to set
	 */
	public void setSerTimeList(List serTimeList) {
		this.serTimeList = serTimeList;
	}


}
