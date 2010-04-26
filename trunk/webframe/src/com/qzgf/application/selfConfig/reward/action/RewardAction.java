package com.qzgf.application.selfConfig.reward.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.reward.domain.RewardFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.ListParseToJson;

/**
 * 悬赏揭榜
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class RewardAction extends BaseAction {
	Log log = LogFactory.getLog(RewardAction.class);

	private HashMap reward = new HashMap();//悬赏
	private HashMap solve = new HashMap();//揭榜
	private List rewardList;				//悬赏列表
	private List solveList;				//悬赏列表
	
	private String json;  					//Ajax专用返回值
	private RewardFacade rewardFacade;		
	private PageList pageList;
	private String xml;		//页面返回
	
	private String ptitlehide;//用户提交的提示
	
	// 读取用户的配置信息2009-11-24
	private HashMap userInfo;
	
	 //入口函数
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
	//前台程序
    //悬赏列表(提问)
	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		pages.setFileName("reward.do?action=index");
		this.setPageList(this.rewardFacade.findRewardPage(search, pages));
		return "index";
	}
    //揭榜提示(相关回答)
	public String solvehide() {
		//通过评论模糊查询数据信息
		ptitlehide=search.get("ptitle").toString();
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		pages.setFileName("reward.do?action=solvehide&search.ptitle="+ptitlehide);
		this.setPageList(this.rewardFacade.findRewardSolvePage(search, pages));		
		return "solvehide";
	}
	
	//继续提问,写入数据库2009-10-14
	public String insertreward() {
		search.put("puserid", userInfo.get("USERID").toString());
		int i=this.rewardFacade.insertReward(search);
		search.clear();
		return index();
	}	
	
    //揭榜回答(),通过相关内容查询后关连的数据信息
	public String solvequestion() {
		this.setSolve((HashMap)this.rewardFacade.findRewardSolve(search).get(0));
		return "solvequestion";
	}
	
    //揭榜回答(),悬赏的相关信息,包括揭榜的,与悬赏的具体内容
	public String rewarddetail() {
		//首先,取得悬赏的相关信息
		List ls=this.rewardFacade.findReward(search);
		if(ls.size()>0){
		    HashMap hs=(HashMap)ls.get(0);
			this.setReward(hs);
			String mid=hs.get("ID").toString();
			search.clear();
			//相关的揭榜信息
			search.put("prewardid", mid);
			Pages pages = new Pages();
			pages.setPage(this.getPage());//默认第一页
			pages.setPerPageNum(10); //设置每页大小
			pages.setFileName("reward.do?action=rewarddetail&search.pid="+mid);//指定条目的详细信息
			this.setPageList(this.rewardFacade.findSolvePage(search, pages));
		}
		return "rewarddetail";
	}
	
    //揭榜回答()
	public String insertsolvequestion() {
		//问题描述
		search.put("puserid", userInfo.get("USERID").toString());
		int i=this.rewardFacade.insertSolve(search);
		search.clear();
		return index();
	}
    //揭榜回答(json回复的提示数据),因为分页无法处理,改用常规方式如下
	public String ajaxsolvejson() {
		//问题描述
		List ls=this.rewardFacade.findRewardSolve(search);
		//if(ls.size()>0)
			json=ListParseToJson.parseToJson(ls);
		return "json";
	}
	
	
	//后台 struts.ui.theme=simple
    //1.悬赏列表(得到列表)
	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		pages.setFileName("reward.do?action=list");
		this.setPageList(this.rewardFacade.findRewardPage(search, pages));
		return "list";
	}
	//2.编辑时取后台数据信息（得到单行记录）
	public String edit() {
		//首先,取得悬赏的相关信息
		List ls=this.rewardFacade.findReward(search);
		if(ls.size()>0){
		    HashMap hs=(HashMap)ls.get(0);
			this.setReward(hs);
			String mid=hs.get("ID").toString();
			search.clear();
			//相关的揭榜信息
			search.put("prewardid", mid);	
			Pages pages = new Pages();
			pages.setPage(this.getPage());//默认第一页
			pages.setPerPageNum(10); //设置每页大小
			pages.setFileName("reward.do?action=rewarddetail");
			this.setPageList(this.rewardFacade.findSolvePage(search, pages));
		}
		this.setAction("update");
    	return "edit";
	}

	//数据更新的处理（得到单行记录）
	public String update() {
		int i=this.rewardFacade.updateRewardById(search);
		if(i==1)
		{
			this.addActionMessage(this.getText("更新成功!"));
		}else
		{
			this.addActionError(this.getText("更新失败!"));
		}
		//转到列表页面
		search.clear();
		return list();
	}
	
	
	//删除悬赏信息同时删除揭榜信息
	public String delete() {
		//主表
		int i=this.rewardFacade.deleteRewardById(search);
		//子表
		HashMap hs=new HashMap();
		hs.put("prewardid", search.get("pid").toString());
		i=i+this.rewardFacade.deleteSolveById(hs);
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<delete>");
		sb.append("<value>").append(i).append("</value>");
		sb.append("</delete>");
		xml = sb.toString();
		return "xml";
	}
	
	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
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
	public RewardFacade getRewardFacade() {
		return rewardFacade;
	}

	/**
	 * @param rewardFacade the rewardFacade to set
	 */
	public void setRewardFacade(RewardFacade rewardFacade) {
		this.rewardFacade = rewardFacade;
	}
	/**
	 * @return the reward
	 */
	public HashMap getReward() {
		return reward;
	}
	/**
	 * @param reward the reward to set
	 */
	public void setReward(HashMap reward) {
		this.reward = reward;
	}
	/**
	 * @return the solve
	 */
	public HashMap getSolve() {
		return solve;
	}
	/**
	 * @param solve the solve to set
	 */
	public void setSolve(HashMap solve) {
		this.solve = solve;
	}
	/**
	 * @return the rewardList
	 */
	public List getRewardList() {
		return rewardList;
	}
	/**
	 * @param rewardList the rewardList to set
	 */
	public void setRewardList(List rewardList) {
		this.rewardList = rewardList;
	}
	/**
	 * @return the solveList
	 */
	public List getSolveList() {
		return solveList;
	}
	/**
	 * @param solveList the solveList to set
	 */
	public void setSolveList(List solveList) {
		this.solveList = solveList;
	}

	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}
	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
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
	 * @return the ptitlehide
	 */
	public String getPtitlehide() {
		return ptitlehide;
	}
	/**
	 * @param ptitlehide the ptitlehide to set
	 */
	public void setPtitlehide(String ptitlehide) {
		this.ptitlehide = ptitlehide;
	}
}
