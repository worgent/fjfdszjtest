package com.qzgf.application.selfConfig.mapFortune.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.mapFortune.domain.GuideDynamicFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.ListParseToJson;
import com.qzgf.utils.OptionsString;

/**
 * 周边向导动态
 * @author lsr
 *
 */  
@SuppressWarnings("serial")
public class GuideDynamicAction extends BaseAction {
	Log log = LogFactory.getLog(GuideDynamicAction.class);

	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	
	private String spcialtySort;    //专长分类
	private List guideListex;  //特色商品信息(地图显示)
	private List guideList;
	private List<OptionsString> sortList = new ArrayList<OptionsString>();
	private String json500m=null;//json供前台显法调用,地图泡泡的显示
	private GuideDynamicFacade guideDynamicFacade;
	private PageList pageList=null;

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
		//放置当前的用户进查询条件里,查得当前用户的位置
		search.put("UserID", "2009100900000141");
		search.put("plat","24.904450211196607");
		search.put("plng","118.597412109375" );
		json500m=ListParseToJson.parseToJson(null);
		return "index";
	}
	
	@SuppressWarnings("unchecked")
	public String query() {
		System.out.println("经度值:"+search.get("plat"));
		
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("guideDynamic.do?action=" + this.getAction());
		
		System.out.println("纬度值:"+search.get("plng"));
		guideList=this.guideDynamicFacade.findGuideDynamicList(search);
		json500m=ListParseToJson.parseToJson(this.guideList);
		return "index";

	}
	
	

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getSpcialtySort() {
		return spcialtySort;
	}

	public void setSpcialtySort(String spcialtySort) {
		this.spcialtySort = spcialtySort;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public List<OptionsString> getSortList() {
		return sortList;
	}

	public void setSortList(List<OptionsString> sortList) {
		this.sortList = sortList;
	}

	public GuideDynamicFacade getGuideDynamicFacade() {
		return guideDynamicFacade;
	}

	public void setGuideDynamicFacade(GuideDynamicFacade guideDynamicFacade) {
		this.guideDynamicFacade = guideDynamicFacade;
	}

	@SuppressWarnings("unchecked")
	public List getGuideListex() {
		return guideListex;
	}

	@SuppressWarnings("unchecked")
	public void setGuideListex(List guideListex) {
		this.guideListex = guideListex;
	}

	public String getJson500m() {
		return json500m;
	}

	public void setJson500m(String json500m) {
		this.json500m = json500m;
	}

	public List getGuideList() {
		return guideList;
	}

	public void setGuideList(List guideList) {
		this.guideList = guideList;
	}

	
}
