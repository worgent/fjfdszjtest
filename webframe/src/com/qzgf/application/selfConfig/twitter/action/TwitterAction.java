package com.qzgf.application.selfConfig.twitter.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.twitter.domain.TwitterFacade;
import com.qzgf.application.systemManage.taglib.config.SysConfig;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.OptionsString;
import com.qzgf.utils.comm.WebFrameUtil;

/**
 * 地图日志表现层
 * @author lsr
 *
 */  
@SuppressWarnings("serial")
public class TwitterAction extends BaseAction {
	Log log = LogFactory.getLog(TwitterAction.class);

	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private TwitterFacade twitterFacade;
	private PageList pageList;
	private String twitterTitle;
	private String twitterContent;
	private String twitterId;
	private String twitterTypeId;
	private SysConfig sysConfig;
	private String twitterTypeName;
	private String content;
	private String xml;
	private List<OptionsString> twitterTypeList = new ArrayList<OptionsString>();

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}

	@SuppressWarnings("unchecked")
	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("twitter.do?action=" + this.getAction());
		this.setPageList(this.twitterFacade.findTwitter(search, pages));
		return "index";
	}
	
	@SuppressWarnings("unchecked")
	public String toAdd() {
		//列出本人所有的日志分类,包括默认分类
		String userId="1";//////////////////////////////
		search.put("UserId", userId);
		List typeList=new ArrayList();
		try{
			typeList=this.twitterFacade.findTwitterTypeByUserId(search);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int j = 0; j < typeList.size(); j++) {
			Map map=(HashMap)typeList.get(j);
			this.twitterTypeList.add(new OptionsString(String.valueOf(map.get("TWITTERTYPEID")), String.valueOf(map.get("TWITTERTYPENAME"))));
		}
		this.setAction("add");
		return "add";
	}
	
	@SuppressWarnings("unchecked")
	public String toEdit() {
		String userId="1";//////////////////////////////
		search.put("UserId", userId);
		@SuppressWarnings("unused")
		List twitterList=this.twitterFacade.findTwitterById(twitterId);
		Iterator i = twitterList.iterator();
		if(i.hasNext()){
			Map map=(HashMap)i.next();
			this.twitterId=(String)map.get("TWITTERID");
			this.twitterTitle=(String)map.get("TWITTERTITLE");
			this.twitterContent=(String)map.get("TWITTERCONTENT");
			this.twitterTypeId=(String)map.get("TWITTERTYPEID");
		}
		List typeList=new ArrayList();
		try{
			typeList=this.twitterFacade.findTwitterTypeByUserId(search);
		}catch(Exception e){
			e.printStackTrace();
		}
		for (int j = 0; j < typeList.size(); j++) {
			Map map=(HashMap)typeList.get(j);
			this.twitterTypeList.add(new OptionsString(String.valueOf(map.get("TWITTERTYPEID")), String.valueOf(map.get("TWITTERTYPENAME"))));
		}
		this.setAction("edit");
		return "add";
	}
	
	@SuppressWarnings("unchecked")
	public String edit() {
		HashMap map=new HashMap();
		map.put("TwitterId", twitterId);
		map.put("TwitterTitle", twitterTitle);
		map.put("TwitterTypeId", twitterTypeId);
		try{
			if(this.twitterFacade.updateTwitterById(map)){
				this.addActionMessage(this.getText("webframe.dataupdate.succeed"));
			}else{
				this.addActionError(this.getText("error.dataupdate.failed"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return toEdit();
	}
	
	@SuppressWarnings("unchecked")
	public String add() {
		twitterTitle=this.sysConfig.bestrowScreen(twitterTitle);
		twitterContent=this.sysConfig.bestrowScreen(twitterContent);
		search.put("TwitterTitle", twitterTitle);
		search.put("TwitterContent", twitterContent);
		search.put("TwitterTypeId", twitterTypeId);
		search.put("TwitterSayId", "1");
		try{
			int num=this.twitterFacade.insertTwitter(search);
			if(num==1){
				this.addActionMessage(this.getText("webframe.addtwitter.succeed"));//添加日志成功
			}else{
				this.addActionError(this.getText("webframe.addtwitter.failed"));
				return toAdd();
			}
		}catch(Exception e){
			e.getMessage();
			this.addActionError(this.getText("webframe.addtwitter.failed"));
			return toAdd();
		}
		this.setAction("index");
		return index();
	}
	
	/**
	 * 删除某一日志
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String del(){
		search.put("TwitterId", twitterId);
		search.put("twitterTypeId", twitterTypeId);
		
		try {
			int i=this.twitterFacade.deleteTwitterById(search);
			if(i==1){
				this.addActionMessage(this.getText("webframe.datadelete.succeed"));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.addActionError(this.getText("error.datadelete.failed"));
		}
		this.setAction("index");
		return index();
	}
	
	/**
	 * 查看某一日志
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String view(){
		
		List twitterList=this.twitterFacade.findTwitterById(twitterId);
		Iterator i = twitterList.iterator();
		if(i.hasNext()){
			Map map=(HashMap)i.next();
			this.twitterId=(String)map.get("TWITTERID");
			this.twitterTitle=(String)map.get("TWITTERTITLE");
			this.twitterContent=(String)map.get("TWITTERCONTENT");
			this.twitterTypeName=(String)map.get("TWITTERTYPENAME");
		}
		return "view";
	}
	
	
	/**
	 * 查询某一类型的日志
	 * @return
	 */
	public String viewTwitterByTwitterTypeId(){
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		search.put("UserID", "1");
		//设置fileName是为了返回到前台后的页面跳转
		pages.setFileName("twitter.do?action=viewTwitterByTwitterTypeId");
		this.setPageList(this.twitterFacade.findTwittersByTwitterType(search, pages));
		return "index";
	}
	
	public String editdo(){
		System.out.println(content);
		try {
			content=URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		content=this.sysConfig.bestrowScreen(content);
		content = WebFrameUtil.filterText(content, false, false, true);
		System.out.println(content);
		search.put("PW_CONTENT", content);
		search.put("P_ID", "2009082300000011");
		search.put("USERID", "1");
		try {
			this.twitterFacade.insertTwitterWord(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<check>");
			sb.append("<value>").append("成功发表评论").append("</value>");
			sb.append("</check>");
			xml = sb.toString();
			
			return "xml"; 
		} catch (Exception e) {
			e.printStackTrace();
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<check>");
			sb.append("<value>").append("出错了,请重试").append("</value>");
			sb.append("</check>");
			xml = sb.toString();
		}
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

	public TwitterFacade getTwitterFacade() {
		return twitterFacade;
	}

	public void setTwitterFacade(TwitterFacade twitterFacade) {
		this.twitterFacade = twitterFacade;
	}

	public String getTwitterTitle() {
		return twitterTitle;
	}

	public void setTwitterTitle(String twitterTitle) {
		this.twitterTitle = twitterTitle;
	}

	public String getTwitterContent() {
		return twitterContent;
	}

	public void setTwitterContent(String twitterContent) {
		this.twitterContent = twitterContent;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public List<OptionsString> getTwitterTypeList() {
		return twitterTypeList;
	}

	public void setTwitterTypeList(List<OptionsString> twitterTypeList) {
		this.twitterTypeList = twitterTypeList;
	}

	public String getTwitterTypeId() {
		return twitterTypeId;
	}

	public void setTwitterTypeId(String twitterTypeId) {
		this.twitterTypeId = twitterTypeId;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public String getTwitterTypeName() {
		return twitterTypeName;
	}

	public void setTwitterTypeName(String twitterTypeName) {
		this.twitterTypeName = twitterTypeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	
	
	
}
