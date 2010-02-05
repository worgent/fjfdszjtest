package com.qzgf.application.baseArchives.OrganManage.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
import com.qzgf.PaginactionAction;
import com.qzgf.application.baseArchives.OrganManage.domain.OrganManageFacade;
import com.qzgf.application.systemManage.manager.dto.UserInfo;

/**
 * 机构部门管理
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class OrganManageAction extends PaginactionAction {
    //用于调试或打印信息的Log
	
	Log log = LogFactory.getLog(OrganManageAction.class);
	private OrganManageFacade organManageFacade;
	@SuppressWarnings({ "unchecked", "unused" })
	private List organManageList;
	@SuppressWarnings("unchecked")
	private HashMap search=new HashMap();
	private String actionType;
	private String action;
	//private String node;
	private String sjjg_Id="";//父结点信息
	private String json="";   //json信息
	private String sjjg="";   //上级机构名称
	private String xml;       //返回xml信息


	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		String JGID= userInfo.getDeptId();
		String JG_MC=userInfo.getDeptName();
		search.put("JGID",JGID);	//取得机构id
		search.put("JG_MC", JG_MC); //取得机构名称
		if ("".equals(actionType) || null == actionType){
			return "search";
		}
		//通过json取得数据信息
		else if ("organTreeJson".equals(actionType)){
			search.put("pparentid", sjjg_Id);//当前用户的机构id作为根结点,前台传入
			json="[";
			//=========================================
			organManageList = organManageFacade.findOrganTree(search);//查询顶级的信息,采用递归算法
			//调用叠代方法
			json+=GetSubDeptJson(organManageList);
			//=========================================
			json += "]";
			//如遇到不规则的数据,用该方法可以得到规则的数据 例:[{1},{2},]===[{1},{2}]
			JSONArray jsonObject = JSONArray.fromObject(json);
			json = jsonObject.toString();
			return "json";
		}else if("new".equals(actionType)){
			action = "insert";
			return "new";
		}else if ("insert".equals(actionType)){
			int i = organManageFacade.insertOrganManage(search);
			if (i==1){
				super.addActionMessage("添加机构部门信息,操作成功");
				addActionScript("parent.document.ifrm_OrganManage.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加机构部门信息,操作失败");
				return ERROR;
			}
		}
		else if ("edit".equals(actionType)){
			search = (HashMap)(organManageFacade.findOrganManage(search).get(0));
			search.put("JGID", JGID);
			search.put("JG_MC", JG_MC);
		    action = "update";
		    return "edit";
		}else if ("update".equals(actionType)){		
			//search.put("editorMan", userInfo.getStaffId());//编辑人
			if(search.get("pparentid")==search.get("pid")){
				super.addActionMessage("修改机构部门信息,操作失败,可能是选择上级机构有误");
				return ERROR; 
			}else{
				int i = organManageFacade.updateOrganManage(search); 
				if (i==1){				
					super.addActionMessage("修改机构部门信息,操作成功");
					addActionScript("parent.document.ifrm_OrganManage.window.location.reload();");					
					return SUCCESS;
				}else {
					super.addActionMessage("修改机构部门信息,操作失败");
					return ERROR;
				}
			}
		}else if("del".equals(actionType)){
			//判断是否有关联的合同及是否有下级机构
			int subNum=organManageFacade.findOrganXgCount(search);//该方法仅返回6,7,0三种情况如下
			if(subNum==6){
				//super.addActionMessage("本机构下有操作员,不能删除");
			    //return ERROR;
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<delete>");
				sb.append("<value>").append("6").append("</value>");
				sb.append("</delete>");
				xml = sb.toString();
				return "xml";
			}
			else if(subNum==7){
				//super.addActionMessage("本机构有下级机构,不能删除");
			    //return ERROR;
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<delete>");
				sb.append("<value>").append("7").append("</value>");
				sb.append("</delete>");
				xml = sb.toString();
				return "xml";
			}
			else if(subNum==0){
				int i = organManageFacade.deleteOrganManage(search);
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<delete>");
				sb.append("<value>").append(i).append("</value>");
				sb.append("</delete>");
				xml = sb.toString();
				return "xml";
			}
		}
		return ERROR;
	}

	/**
	 * 通过第一级数据,叠代方式取得所有结点信息(暂时不用该方法,因为ext数据的取得是每次叠代而成,即每次都会解发该事件,不需要直接叠代信息)
	 * @param ls
	 * @return
	 */
	/*
	private String GetAllDeptJson(List ls)
	{
		String result="";
		HashMap tmp = null;
		if(ls.size()!=0)
		{
			Iterator it = ls.iterator();//取得数据集
			while (it.hasNext()){//取得每一行记录
				//1.处理一行记录
				tmp = (HashMap)it.next();
				result+="{";											//每行记录的开头
				result += "\"id\":\"" + tmp.get("id") + "\"";			//记录部门编号
				result += ",\"text\":\"" + tmp.get("deptname") + "\"";	//记录部门名称
				search.put("pparentid", tmp.get("id"));				 	//查询是否有子结点
				organManageList= organManageFacade.findOrganTree(search);
				Iterator it1 = organManageList.iterator();//判断是否是叶子结点
				if(it1.hasNext()){
					result += ",\"leaf\":false";
				}
				else{
					result += ",\"leaf\":true";
				}
				result +="},";
				//2.处理叠代
				result+=GetAllDeptJson(organManageList);//叠代取得数据
			}
			//result=result.substring(0, result.length()-1);			
		}
		return result;
	}
	*/
	/**
	 * 通过第一级数据,仅需一级的数据就OK.
	 * @param ls
	 * @return
	 */
	private String GetSubDeptJson(List ls)
	{
		String result="";
		HashMap tmp = null;
		if(ls.size()!=0)
		{
			Iterator it = ls.iterator();//取得数据集
			while (it.hasNext()){//取得每一行记录
				//1.处理一行记录
				tmp = (HashMap)it.next();
				result+="{";											//每行记录的开头
				result += "\"id\":\"" + tmp.get("id") + "\"";			//记录部门编号
				result += ",\"text\":\"" + tmp.get("deptname") + "\"";	//记录部门名称
				search.put("pparentid", tmp.get("id"));				 	//查询是否有子结点
				organManageList= organManageFacade.findOrganTree(search);
				Iterator it1 = organManageList.iterator();//判断是否是叶子结点
				if(it1.hasNext()){
					result += ",\"leaf\":false";
				}
				else{
					result += ",\"leaf\":true";
				}
				result +="},";
				//2.处理叠代
				//result+=GetAllDeptJson(organManageList);//叠代取得数据
			}	
		}
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
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

	public OrganManageFacade getOrganManageFacade() {
		return organManageFacade;
	}

	public void setOrganManageFacade(OrganManageFacade organManageFacade) {
		this.organManageFacade = organManageFacade;
	}

	public String getSjjg_Id() {
		return sjjg_Id;
	}

	public void setSjjg_Id(String sjjg_Id) {
		this.sjjg_Id = sjjg_Id;
	}
	
	
	
	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getSjjg() {
		return sjjg;
	}

	public void setSjjg(String sjjg) {
		this.sjjg = sjjg;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
