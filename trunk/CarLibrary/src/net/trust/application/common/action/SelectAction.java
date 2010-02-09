package net.trust.application.common.action;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.trust.PaginactionAction;
import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SelectAction extends PaginactionAction{
	private Log log = LogFactory.getLog(SelectAction.class);
	private BaseSqlMapDAO baseSqlMapDAO;
	
	private String dynamicSql;
	private String sqlMap;
	
	private List selectList;
	private String jsonData;
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String getJsonData() {
		selectList = baseSqlMapDAO.queryForList("dynamicSql", dynamicSql);
		JSONArray jsonObject = JSONArray.fromObject(selectList);
		jsonData = jsonObject.toString();
		
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	public List getSelectList() {
		this.selectList = baseSqlMapDAO.queryForList("dynamicSql", dynamicSql);
		return selectList;
	}

	public void setSelectList(List selectList) {
		this.selectList = selectList;
	}

	public String getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(String sqlMap) {
		this.sqlMap = sqlMap;
	}
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
	public String getDynamicSql() {
		return dynamicSql;
	}
	public void setDynamicSql(String dynamicSql) {
		this.dynamicSql = dynamicSql;
	}
}
