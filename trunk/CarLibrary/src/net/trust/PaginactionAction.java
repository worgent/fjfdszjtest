package net.trust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.trust.utils.pagination.PaginationImplt;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * 分页的action类，如果模块需要分页的功能继承该类
 * */
public class PaginactionAction extends ActionSupport{
	protected StringBuffer reload = new StringBuffer();//反回要刷新的窗口
	protected String back;//成功页面上展示的返回连接的URL
	protected List button = new java.util.ArrayList();//成功页面展示的按钮
	
	protected String startNum ="0"; //记录开始数
	protected String endNum="15";    //记录结果数
	protected int queryFlag = 0;    //标f志，0代表该查询动作是新的,1则是点击上下页或跳转查询
	protected String turnPage = null; // 翻页,first,previous,next,last
	protected PaginationImplt  pagination = null;
	protected int pageRecord=15;
	
	protected String exportFlag = null;
	
	protected List actionScripts = new ArrayList(); //记录开始数
	
	private final Log log = LogFactory.getLog(PaginactionAction.class);
	public String execute() throws Exception {
		return SUCCESS;
	}
	protected Object getParameter(){
		pagination = (PaginationImplt)ActionContext.getContext().getSession().get("Qpagination");
		pagination.turnPage(turnPage);
		turnPage=String.valueOf(pagination.getCurrentPage());
		startNum = String.valueOf(pagination.getPageStart() - 1);
//		endNum =  String.valueOf( pagination.getPageEnd());
		endNum = "15";
		
		if( ActionContext.getContext().getSession().get("Qparameter") instanceof java.util.Map){
			if(log.isDebugEnabled()){
				log.debug("parameter is Map");
			}
			((java.util.HashMap)ActionContext.getContext().getSession().get("Qparameter")).put("START",startNum);
			((java.util.HashMap)ActionContext.getContext().getSession().get("Qparameter")).put("END",endNum);
		}
		if(log.isDebugEnabled()){
			log.debug(ActionContext.getContext().getSession().get("Qparameter"));
		}
		return ActionContext.getContext().getSession().get("Qparameter");
	}
	protected void setParameter(Object para){
		ActionContext.getContext().getSession().put("Qparameter",para);
	}
	/**
	 * 设置总记录数，并初始化pagination对象保存在会话里
	 * */
	protected void setCount(int c){
		pagination= new PaginationImplt();
		pagination.setTotalRecord(c);
		pagination.setPageRecord(pageRecord);
		startNum = String.valueOf(pagination.getPageStart());
//		endNum = String.valueOf( pagination.getPageEnd());
		endNum = "15";
		
		if( ActionContext.getContext().getSession().get("Qparameter") instanceof java.util.Map){
			if(log.isDebugEnabled()){
				log.debug("parameter is Map");
			}
			((java.util.HashMap)ActionContext.getContext().getSession().get("Qparameter")).put("START",startNum);
			((java.util.HashMap)ActionContext.getContext().getSession().get("Qparameter")).put("END",endNum);
		}
		ActionContext.getContext().getSession().put("Qpagination",pagination);
	}
	
	/**
	 * 解释request中的参数，组成字符串
	 * @param param
	 * @return
	 */
	private String paramToString(HashMap param){
		String paramStr = "";
		Iterator paramIt = param.keySet().iterator();
		while (paramIt.hasNext()){
			String paramName = (String)paramIt.next();
			String v[] = (String[])param.get(paramName);
			if (null != v[0])
				paramStr += "&" + paramName + "=" + v[0];
			else
				paramStr += "&" + paramName + "=";
			
		}
		return paramStr;
	}
	
	public PaginationImplt getPagination() {
		return pagination;
	}
	public int getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(int queryFlag) {
		this.queryFlag = queryFlag;
	}

	public String getTurnPage() {
		return turnPage;
	}

	public void setTurnPage(String turnPage) {
		this.turnPage = turnPage;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public StringBuffer getReload() {
		return reload;
	}
	public void setReload(StringBuffer reload) {
		this.reload = reload;
	}

	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public List getButton() {
		return button;
	}
	public void setButton(List button) {
		this.button = button;
	}
	
	public List getActionScripts() {
		return actionScripts;
	}
	public String getExportFlag() {
		return exportFlag;
	}
	public void setExportFlag(String exportFlag) {
		this.exportFlag = exportFlag;
	}
	public void setActionScripts(List actionScripts) {
		this.actionScripts = actionScripts;
	}
	
	public void addActionScript(String script){
		actionScripts.add(script);
	}
}
