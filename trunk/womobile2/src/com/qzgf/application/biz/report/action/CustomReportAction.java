package com.qzgf.application.biz.report.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseMainAction;
import com.qzgf.application.biz.report.domain.CustomReportFacade;
import com.qzgf.comm.EMSUtil;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.webutils.interceptor.SessionAware;

@SuppressWarnings("serial")
public class CustomReportAction extends BaseMainAction implements SessionAware{
	Log log = LogFactory.getLog(CustomReportAction.class);
	private String basePath;
	private PageList pageList;
	private AjaxMessagesJson ajaxMessagesJson;
	private CustomReportFacade customReportFacade;
	private List<Integer> uploadIDs = new ArrayList<Integer>();
	private String curMenu;
	private String reportId;
	private String menuTree;// 报表树
	@SuppressWarnings("unchecked")
	private List fieldList;
	@SuppressWarnings("unchecked")
	private List reportList;//查询结果
	private UserSession userSession;
	@SuppressWarnings("unchecked")
	private HashMap query = new HashMap();
	@SuppressWarnings("unchecked")
	private HashMap dayMap1 = new HashMap();//封装原始日期字段
	@SuppressWarnings("unchecked")
	private HashMap dayMap2 = new HashMap();//封装包含开始与结束的日期字段
	private String  parentType;
	@SuppressWarnings("unchecked")
	private List resultFieldList=new ArrayList();
	private String table;//封装的表信息
	private String exportUrl;//EXCEL导出URL
	private String reportName;//报表名称

	/** **********************用户信息*************************** */
	private String id;

	@SuppressWarnings("unchecked")
	public String getCustomReport() {
		// 报表树
		@SuppressWarnings("unused")
		List menuList = customReportFacade.findCustomReport();
		StringBuffer menustr = new StringBuffer();
		menustr.append("<script type='text/javascript'>");
		menustr.append("var d1 = new dTree('d1','images/system/dept/');");
		menustr.append("d1.config.target = 'user_admin_mainFrame';");
		menustr.append("d1.add('1','-1','日常工作');");
		menustr.append("d1.add('2','-1','督办工作');");
		menustr.append("d1.add('3','-1','事项申报');");
		Map menuitem = new HashMap();
		java.util.Iterator it = menuList.iterator();
		while (it.hasNext()) {
			menuitem = (HashMap) it.next();
			menustr.append("d1.add(" + menuitem.get("ID") + ","+menuitem.get("patternType")+",'"
				+ menuitem.get("ReportName")+ "','/customReport.do?action=toQueryMark&reportId="+ menuitem.get("ID") + "&parentType="+menuitem.get("patternType")+"');");
		}

		menustr.append("document.write(d1);");
		menustr.append("</script>");

		menuTree = menustr.toString();

		return "left";
	}

	@SuppressWarnings("unchecked")
	public String index() {
		return SUCCESS;
	}

	public String toQueryMark() {
		return "queryMark";
	}
	
	/**
	 * 查询报表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryReport(){
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(10); //设置每页大小
		String parm="";
		if(reportId==null||reportId.equals("")){
			return "list";
		}else{
			search.put("reportId", reportId);
			reportName=this.customReportFacade.getReportName(reportId);
			fieldList = this.customReportFacade.findQueryField(search);
			//取各参数的值
			Map item = new HashMap();
			
			java.util.Iterator it = fieldList.iterator();
			while (it.hasNext()) {
				item = (HashMap) it.next();   
				String type=(String)item.get("fieldtype");//字段类型
				if(type!=null&&type.equals("date")){
					if(dayMap1.containsKey(item.get("fieldname"))){
						parm=parm+"&dayMap1."+item.get("fieldname")+"=";
						if(dayMap2.get(item.get("fieldname")+"start")!=null&&!dayMap2.get(item.get("fieldname")+"start").equals("")){
							parm=parm+"&dayMap2."+item.get("fieldname")+"start="+dayMap2.get(item.get("fieldname")+"start");
						}
						if(dayMap2.get(item.get("fieldname")+"end")!=null&&!dayMap2.get(item.get("fieldname")+"end").equals("")){
							parm=parm+"&dayMap2."+item.get("fieldname")+"end="+dayMap2.get(item.get("fieldname")+"end");
						}
						
					}
				}else if(type!=null){
					String val=(String)query.get(item.get("fieldname"));
					if(val!=null&&!"".equals(val)){
						parm=parm+"&query."+item.get("fieldname")+"="+query.get(item.get("fieldname"));
					}
				}
			}
			parm=parm+"&parentType="+parentType+"&reportId="+reportId;
		}
		pages.setFileName(EMSUtil.getActionMappingURLWithoutPrefix("customReport?action=queryReport")+parm); 
		this.pageList=this.customReportFacade.queryReportList(query,dayMap1,dayMap2,parentType,reportId,pages);
		exportUrl=EMSUtil.getActionMappingURLWithoutPrefix("customReport?action=getReportExcel")+parm;
		//列出所有的输出报表字段
		resultFieldList=this.customReportFacade.findReportResultFieldList(reportId);
		return "list";
	}
	
	public String getReportExcel(){

		table=this.customReportFacade.execExport(query,dayMap1,dayMap2,parentType,reportId);
		return "export";
	}


	/**
	 * 查询某一报表的查询字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list(){
		
		if(reportId==null||reportId.equals("")){
			return "list";
		}else{
			search.put("reportId", reportId);
			reportName=this.customReportFacade.getReportName(reportId);
			fieldList = this.customReportFacade.findQueryField(search);
			return "list";
		}
	}

	public String getCurMenu() {
		return curMenu;
	}

	public void setCurMenu(String curMenu) {
		this.curMenu = curMenu;
	}


	public String getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(String menuTree) {
		this.menuTree = menuTree;
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

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<Integer> getUploadIDs() {
		return uploadIDs;
	}

	public void setUploadIDs(List<Integer> uploadIDs) {
		this.uploadIDs = uploadIDs;
	}

	public CustomReportFacade getCustomReportFacade() {
		return customReportFacade;
	}

	public void setCustomReportFacade(CustomReportFacade customReportFacade) {
		this.customReportFacade = customReportFacade;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public List getFieldList() {
		return fieldList;
	}

	public void setFieldList(List fieldList) {
		this.fieldList = fieldList;
	}

	public HashMap getQuery() {
		return query;
	}

	public void setQuery(HashMap query) {
		this.query = query;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public HashMap getDayMap1() {
		return dayMap1;
	}

	public void setDayMap1(HashMap dayMap1) {
		this.dayMap1 = dayMap1;
	}

	public HashMap getDayMap2() {
		return dayMap2;
	}

	public void setDayMap2(HashMap dayMap2) {
		this.dayMap2 = dayMap2;
	}

	public List getReportList() {
		return reportList;
	}

	public void setReportList(List reportList) {
		this.reportList = reportList;
	}

	public List getResultFieldList() {
		return resultFieldList;
	}

	public void setResultFieldList(List resultFieldList) {
		this.resultFieldList = resultFieldList;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}
