package net.trust.application.baseArchives.recunitInfo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;

import net.trust.PaginactionAction;
import net.trust.application.baseArchives.recunitInfo.domain.RecUnitFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

/**
 * 来往单位信息管理
 * @author zhengmh
 *
 */
public class RecUnitInfoAction extends PaginactionAction{
//	用于调试或打印信息的Log
	Log log = LogFactory.getLog(RecUnitInfoAction.class);
	private RecUnitFacade recUnitFacade;	
	private List recunitList;
	private HashMap search=new HashMap();
	private String actionType;		
	private String action;
	private String xml;
	private String json;
	private String serviceName;
	
	public String execute() throws Exception {
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");		
		search.put("staffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(recUnitFacade.findRecUnitInfoCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			recunitList = recUnitFacade.findRecUnitInfo(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";	
			
		}else if ("insert".equals(actionType)){
			search.put("createMan", userInfo.getStaffId());//创建人
			int i = recUnitFacade.insertRecUnitInfo(search);
			if (i>0){
				super.addActionMessage("添加往来单位信息,操作成功");
				addActionScript("parent.document.ifrm_ComeandgoUnitInfo.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加往来单位信息,操作失败");
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search.put("editorMan", userInfo.getStaffId());//编辑人
			search = (HashMap)(recUnitFacade.findRecUnitInfo(search).get(0));			
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){			
			int i = recUnitFacade.updateRecUnitInfo(search);
			if (i>=0){				
				super.addActionMessage("修改往来单位信息,操作成功");
				addActionScript("parent.document.ifrm_ComeandgoUnitInfo.window.location.reload();");					
				return SUCCESS;
			}else {
				super.addActionMessage("修改往来单位信息,操作失败");
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){		
			search.put("editorMan", userInfo.getStaffId());//编辑人
			int i = recUnitFacade.deleteRecUnitInfo(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			
		}else if ("view".equals(actionType)){
			search = (HashMap)(recUnitFacade.findRecUnitInfo(search).get(0));			
			return "view";
			
		}
		return ERROR;
	}
	
	public String ajaxJson(){
		recunitList = recUnitFacade.findRecUnitInfo(search);
		Iterator it = recunitList.iterator();
		StringBuffer sb = new StringBuffer();
		HashMap tmp = null;
		while (it.hasNext()){
			tmp = (HashMap)it.next();
			if (null != sb && !"".equals(sb.toString())){
				sb.append(",");
    		}
			sb.append("{");
			sb.append("\"text\":\"").append(tmp.get("coop_unit_name")).append("\"");
			sb.append(",\"id\":\"").append(tmp.get("coop_unit_id")).append("\"");
			sb.append(",\"leaf\":true");
			sb.append("}");
		}
		json = "[" +sb.toString()+ "]";
		return "json";
	}
	
	public RecUnitFacade getRecUnitFacade() {
		return recUnitFacade;
	}
	public void setRecUnitFacade(RecUnitFacade recUnitFacade) {
		this.recUnitFacade = recUnitFacade;
	}
	public List getRecunitList() {
		return recunitList;
	}
	public void setRecunitList(List recunitList) {
		this.recunitList = recunitList;
	}
	public HashMap getSearch() {
		return search;
	}
	public void setSearch(HashMap search) {
		this.search = search;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
}
