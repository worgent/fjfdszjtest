package com.qzgf.application.common.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.PaginactionAction;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

@SuppressWarnings("serial")
public class SelectAction extends PaginactionAction {
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(SelectAction.class);
	// 通过ibatis访问数据库
	private BaseSqlMapDAO baseSqlMapDAO;

	private String dynamicSql;
	private String sqlMap;

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public List getSelectList() {
		this.selectList = baseSqlMapDAO.queryForList("dynamicSql", dynamicSql);
		return selectList;
	}

	@SuppressWarnings("unchecked")
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
