<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.action;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import ${basepackage}.domain.${className}Facade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ajax.AjaxMessagesJson;


public class ${className}Action  extends BaseAction{

	Log log = LogFactory.getLog(${className}Action.class);

	private ${className}Facade ${classNameLower}Facade;
	private HashMap ${classNameLower} = new HashMap(); // 商品信息
	private List ${classNameLower}List; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回
	private String json; //页面返回
	private AjaxMessagesJson ajaxMessagesJson;

	// 入口函数
	@SuppressWarnings("unchecked")
	public String execute() {
		// 从session中读取用户信息
		HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		// 当匿名登录时
		if (userInfo == null) {
			HashMap tmp = new HashMap();
			tmp.put("USERID", "0");
			userInfo = tmp;
		}
		//初始化基本信息
		search.put("maker", userInfo.get("ID").toString());//创建人
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			int i=${classNameLower}Facade.count${className}(search);
			if(i==0){
				this.setAction("insert");
				return "edit";
			}else{
				return "list";
			}
		} 
		else if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("${classNameLower}.do?action=listdetail");
			setPageList(${classNameLower}Facade.find${className}Page(search, pages));
			return "listdetail";
		} else if ("new".equals(super.getAction())) {
			//order = (HashMap) orderFacade.findOrder(search).get(0); // 用户信息
			this.setAction("insert");
			return "edit";
			//新增
		}else if ("insert".equals(super.getAction())) {
			//增加数据
			int i = ${classNameLower}Facade.insert${className}(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
			//编辑
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			${classNameLower} = (HashMap) ${classNameLower}Facade.find${className}(search).get(0); // 用户信息
			this.setAction("update");
			return "edit";
			//更新
		} else if ("update".equals(super.getAction())) {
			//用户信息更新,包括管理人员的审核,用户的修改
			//初始化基本信息
			search.put("maker", userInfo.get("ID").toString());//创建人
			int i = ${classNameLower}Facade.update${className}ById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
			//查看
		} else if ("view".equals(super.getAction())) {
			${classNameLower}=((HashMap) ${classNameLower}Facade.find${className}(search).get(0)); 
			return "view";
			//删除
		}else if ("delete".equals(super.getAction())) {
			int i = ${classNameLower}Facade.delete${className}ById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			//多选删除
		}else if ("alldel".equals(super.getAction())) {
			search.put("pstate", 0);//删除数据
			search.put("pexstate", "新任务");//删除数据
			int i = ${classNameLower}Facade.allpro${className}ById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
			//审批
		}else if ("auth".equals(super.getAction())) {
			search.put("pstate", "已审核");//修改状态
			int i = ${classNameLower}Facade.allpro${className}ById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//中止
		}else if ("stop".equals(super.getAction())) {
			search.put("pstate", "已中止");//修改状态
			int i =  ${classNameLower}Facade.allpro${className}ById(search);
			json = "[{value:"+i+"}]";
    		return "json";
			//归档
		}else if ("archive".equals(super.getAction())) {
			search.put("pstate", "已归档");//修改状态
			int i = ${classNameLower}Facade.allpro${className}ById(search);
			json = "[{value:"+i+"}]";
    		return "json";
		}
		return ERROR;
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
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * @return the ${classNameLower}Facade
	 */
	public ${className}Facade get${className}Facade() {
		return ${classNameLower}Facade;
	}



	/**
	 * @param ${classNameLower}Facade the ${classNameLower}Facade to set
	 */
	public void set${className}Facade(${className}Facade ${classNameLower}Facade) {
		this.${classNameLower}Facade = ${classNameLower}Facade;
	}



	/**
	 * @return the ${classNameLower}
	 */
	public HashMap get${className}() {
		return ${classNameLower};
	}



	/**
	 * @param ${classNameLower} the ${classNameLower} to set
	 */
	public void set${className}(HashMap ${classNameLower}) {
		this.${classNameLower} = ${classNameLower};
	}



	/**
	 * @return the ${classNameLower}List
	 */
	public List get${className}List() {
		return ${classNameLower}List;
	}



	/**
	 * @param ${classNameLower}List the ${classNameLower}List to set
	 */
	public void set${className}List(List ${classNameLower}List) {
		this.${classNameLower}List = ${classNameLower}List;
	}



	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}



	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}
		
	
}
