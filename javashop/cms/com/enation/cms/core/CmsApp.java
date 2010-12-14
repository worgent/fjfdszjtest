/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.cms.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.enation.app.saler.service.impl.SqlExportService;
import com.enation.cms.core.model.DataModel;
import com.enation.cms.core.service.IDataModelManager;
import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.sdk.App;
import com.enation.framework.database.IDBRouter;

public class CmsApp implements App {
	private IDBRouter baseDBRouter;
	private static List<String> tables;
	private IDataModelManager dataModelManager ;
	private SqlExportService sqlExportService;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	static{
		tables  = new ArrayList<String>();
		tables.add("data_cat");
		tables.add("data_model");
		tables.add("data_field");
	}
	
	public void install() {
		if("2".equals(EopSetting.RUNMODE)){ //只有saas版安 装数据库
			this.createDatabase();
		}
	}

	/**
	 * 创建数据库
	 */
	private void createDatabase(){
		for(String tbName : tables){
			this.baseDBRouter.createTable(tbName);
		}
	}

	public String dumpSql() {
		List<String > newTbs = new ArrayList<String>();
		newTbs.addAll(tables);
		String querySql;
		
		if("2".equals(EopSetting.RUNMODE)){
			EopSite site  = EopContext.getContext().getCurrentSite();
			querySql="show tables like 'es_data_model"+site.getUserid()+"_"+site.getId()+"'";
		}else{
			querySql="show tables like 'es_data_model'";
		}
		
		List tblist  =this.simpleJdbcTemplate.queryForList(querySql);
		if(tblist!=null && !tblist.isEmpty()){
			List<DataModel> modelList  =  this.dataModelManager.list();
			
			for(DataModel model :modelList){
					
				newTbs.add(	model.getEnglish_name() );
			}
		}
		

		return this.sqlExportService.dumpSql(newTbs);
	}
	
	
	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}


	public IDataModelManager getDataModelManager() {
		return dataModelManager;
	}

	public void setDataModelManager(IDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public SqlExportService getSqlExportService() {
		return sqlExportService;
	}

	public void setSqlExportService(SqlExportService sqlExportService) {
		this.sqlExportService = sqlExportService;
	}

	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
}
