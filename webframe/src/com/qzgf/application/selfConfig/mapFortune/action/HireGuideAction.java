package com.qzgf.application.selfConfig.mapFortune.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.mapFortune.domain.HireGuideFacade;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.ListParseToJson;
import com.qzgf.utils.OptionsString;

/**
 * 雇请向导
 * @author lsr
 *
 */  
@SuppressWarnings("serial")
public class HireGuideAction extends BaseAction {
	Log log = LogFactory.getLog(HireGuideAction.class);

	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();

	private String ml;			//缩放级别
	private String mt;			//地图类型
	private String sw;			//西南经纬度
	private String ne;          //东南经纬度
	private String selectType;  //地图查询类型(0:地图周边查询 1:框选查询)
	private String json500m=null;//json供前台显法调用,地图泡泡的显示
	
	private List<OptionsString> specialtySortList = new ArrayList<OptionsString>();
	private List<OptionsString> mapConditionList = new ArrayList<OptionsString>();
	private List<OptionsString> sortList = new ArrayList<OptionsString>();
	private List<OptionsString> guideLevelList = new ArrayList<OptionsString>();
	private HireGuideFacade hireGuideFacade;
	private PageList pageList=null;
	private PageList pageList1=null;
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
		//把页面需要的属性查询出来并显示
		
		//专长分类
		this.specialtySortList.add(new OptionsString("0", ""));
		this.specialtySortList.add(new OptionsString("1", "专长一"));
		this.specialtySortList.add(new OptionsString("2", "专长二"));
		this.specialtySortList.add(new OptionsString("3", "专长三"));
		this.specialtySortList.add(new OptionsString("4", "专长四"));
		
		//区域控制
		this.mapConditionList.add(new OptionsString("0",""));
		this.mapConditionList.add(new OptionsString("1","地图框选"));
		this.mapConditionList.add(new OptionsString("2","热点区域周边"));
		
		//结果排序
		this.sortList.add(new OptionsString("0",""));
		this.sortList.add(new OptionsString("1","收入"));
		this.sortList.add(new OptionsString("2","经验"));
		this.sortList.add(new OptionsString("3","名望"));
		
		//向导级别
		this.guideLevelList.add(new OptionsString("0",""));
		this.guideLevelList.add(new OptionsString("1","新向导"));
		this.guideLevelList.add(new OptionsString("2","向导"));
		this.guideLevelList.add(new OptionsString("3","老向导"));
		this.setAction("query");
		List list=new ArrayList();
		Map map=new HashMap();
		map.put("1", "aaa");
		list.add(map);
		json500m=ListParseToJson.parseToJson(null);
		return "index";
	}
	
	@SuppressWarnings("unchecked")
	public String query() {

		//专长分类
		this.specialtySortList.add(new OptionsString("0", ""));
		this.specialtySortList.add(new OptionsString("1", "专长一"));
		this.specialtySortList.add(new OptionsString("2", "专长二"));
		this.specialtySortList.add(new OptionsString("3", "专长三"));
		this.specialtySortList.add(new OptionsString("4", "专长四"));
		
		//区域控制
		this.mapConditionList.add(new OptionsString("0",""));
		this.mapConditionList.add(new OptionsString("1","地图框选"));
		this.mapConditionList.add(new OptionsString("2","热点区域周边"));
		
		//结果排序
		this.sortList.add(new OptionsString("0",""));
		this.sortList.add(new OptionsString("1","收入"));
		this.sortList.add(new OptionsString("2","经验"));
		this.sortList.add(new OptionsString("3","名望"));
		
		//向导级别
		this.guideLevelList.add(new OptionsString("0",""));
		this.guideLevelList.add(new OptionsString("1","新向导"));
		this.guideLevelList.add(new OptionsString("2","向导"));
		this.guideLevelList.add(new OptionsString("3","老向导"));
		search.put("selectType", selectType);
		if((selectType.equals("1"))&&!(null==selectType)){
			//当selectType为框选时
			search.put("swLat", sw.split(",")[0]);//切割西南经续度
			search.put("swLng", sw.split(",")[1]);
			search.put("neLat", ne.split(",")[0]);//切割东北经续度
			search.put("neLng", ne.split(",")[1]);
		}
		
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		//search.put("UserId", "1");
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("hireGuide.do?action=" + this.getAction());
		pageList1=this.hireGuideFacade.findGuideByHire(search, pages);
		this.setPageList(pageList1);
		//json500m=ListParseToJson.parseToJson(this.pageList1.getObjectList());
		json500m=ListParseToJson.parseToJson(pageList1.getObjectList());
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

	public List<OptionsString> getSpecialtySortList() {
		return specialtySortList;
	}

	public void setSpecialtySortList(List<OptionsString> specialtySortList) {
		this.specialtySortList = specialtySortList;
	}

	public List<OptionsString> getMapConditionList() {
		return mapConditionList;
	}

	public void setMapConditionList(List<OptionsString> mapConditionList) {
		this.mapConditionList = mapConditionList;
	}

	public List<OptionsString> getSortList() {
		return sortList;
	}

	public void setSortList(List<OptionsString> sortList) {
		this.sortList = sortList;
	}

	public List<OptionsString> getGuideLevelList() {
		return guideLevelList;
	}

	public void setGuideLevelList(List<OptionsString> guideLevelList) {
		this.guideLevelList = guideLevelList;
	}

	public HireGuideFacade getHireGuideFacade() {
		return hireGuideFacade;
	}

	public void setHireGuideFacade(HireGuideFacade hireGuideFacade) {
		this.hireGuideFacade = hireGuideFacade;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public String getMl() {
		return ml;
	}

	public void setMl(String ml) {
		this.ml = ml;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public String getSw() {
		return sw;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}

	public String getNe() {
		return ne;
	}

	public void setNe(String ne) {
		this.ne = ne;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getJson500m() {
		return json500m;
	}

	public void setJson500m(String json500m) {
		this.json500m = json500m;
	}

	public PageList getPageList1() {
		return pageList1;
	}

	public void setPageList1(PageList pageList1) {
		this.pageList1 = pageList1;
	}

	
}
