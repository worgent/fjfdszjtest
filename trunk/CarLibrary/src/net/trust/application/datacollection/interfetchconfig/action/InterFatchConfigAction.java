package net.trust.application.datacollection.interfetchconfig.action;

import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

import net.trust.PaginactionAction;
import net.trust.application.datacollection.interfetchconfig.domain.InterFatchConfigFacade;
import net.trust.utils.PubFunction;
/**
 * GPS接口数据采集规则配置
 *
 */
public class InterFatchConfigAction extends PaginactionAction{
	private Log log = LogFactory.getLog(InterFatchConfigAction.class);
	
	private InterFatchConfigFacade interFatchConfigFacade;
	
	private List interFatchConfigList;
	private HashMap search=new HashMap();
	
	private String actionType;		
	private String action;
	private String xml;

	public String execute() throws Exception {
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(interFatchConfigFacade.findInterFatchConfigCount(search));//获取查询结果总记录数
			}
			search = (HashMap)super.getParameter();
			interFatchConfigList = interFatchConfigFacade.findInterFatchConfig(search);
			return "search";
			
		}else if ("new".equals(actionType)){
			action = "insert";
			return "new";		
			
		}else if ("insert".equals(actionType)){
			search.put("interSelectSql", PubFunction.getNulltoStr(search.get("interSelectSql")).replaceAll("'", "''"));
			search.put("localInsertSql", PubFunction.getNulltoStr(search.get("localInsertSql")).replaceAll("'", "''"));
			search.put("localUpdateSql", PubFunction.getNulltoStr(search.get("localUpdateSql")).replaceAll("'", "''"));
			int i = interFatchConfigFacade.insertInterFatchConfig(search);
			if (i>0){
				super.addActionMessage("创建GPS接口数据采集规则配置,操作成功");
				addActionScript("parent.document.ifrm_InterFetchConfig.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("创建GPS接口数据采集规则配置,操作失败");				
				return ERROR;
			}
			
		}else if ("edit".equals(actionType)){;
			search = (HashMap)(interFatchConfigFacade.findInterFatchConfig(search).get(0));
			search.put("inter_select_sql", PubFunction.getNulltoStr(search.get("inter_select_sql")).replaceAll("''", "'"));
			search.put("local_insert_sql", PubFunction.getNulltoStr(search.get("local_insert_sql")).replaceAll("''", "'"));
			search.put("local_update_sql", PubFunction.getNulltoStr(search.get("local_update_sql")).replaceAll("''", "'"));
			
			action = "update";
			return "edit";
			
		}else if ("update".equals(actionType)){	
			search.put("interSelectSql", PubFunction.getNulltoStr(search.get("interSelectSql")).replaceAll("'", "''"));
			search.put("localInsertSql", PubFunction.getNulltoStr(search.get("localInsertSql")).replaceAll("'", "''"));
			search.put("localUpdateSql", PubFunction.getNulltoStr(search.get("localUpdateSql")).replaceAll("'", "''"));
			int i = interFatchConfigFacade.updateInterFatchConfig(search);
			if (i>=0){				
				super.addActionMessage("修改GPS接口数据采集规则配置,操作成功");
				addActionScript("parent.document.ifrm_InterFetchConfig.window.location.reload();");			
				return SUCCESS;
			}else {
				super.addActionMessage("修改GPS接口数据采集规则配置,操作失败");				
				return ERROR;
			}
			
		}else if ("delete".equals(actionType)){		
			int i = interFatchConfigFacade.deleteInterFatchConfig(search);							
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(""+i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";	
				
		}else if ("view".equals(actionType)){
			search = (HashMap)(interFatchConfigFacade.findInterFatchConfig(search).get(0));
			return "view";

		}
		return ERROR;
	}

	
	public InterFatchConfigFacade getInterFatchConfigFacade() {
		return interFatchConfigFacade;
	}

	public void setInterFatchConfigFacade(
			InterFatchConfigFacade interFatchConfigFacade) {
		this.interFatchConfigFacade = interFatchConfigFacade;
	}

	public List getInterFatchConfigList() {
		return interFatchConfigList;
	}

	public void setInterFatchConfigList(List interFatchConfigList) {
		this.interFatchConfigList = interFatchConfigList;
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
	
	
}
