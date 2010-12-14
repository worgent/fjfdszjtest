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
package com.enation.cms.core.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.RowMapper;

import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.model.DataField;
import com.enation.cms.core.model.DataModel;
import com.enation.cms.core.plugin.ArticlePluginBundle;
import com.enation.cms.core.plugin.IFieldValueShowEvent;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataFieldManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.cms.core.service.IDataModelManager;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.plugin.IPlugin;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;

/**
 * 数据管理
 * @author kingapex
 * 2010-7-5下午03:55:14
 */
public class DataManager extends BaseSupport implements IDataManager {

	private IDataModelManager dataModelManager;
	private IDataFieldManager dataFieldManager ;
	private ArticlePluginBundle articlePluginBundle;
	private IDataCatManager dataCatManager;
	
	
	public void add(Integer modelid,Integer catid) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		DataModel dataModel = this.dataModelManager.get(modelid);
		List<DataField> fieldList = dataFieldManager.list(modelid);
		Map article = new HashMap(); 
		for(DataField field :fieldList){
			articlePluginBundle.onSave(article, field);
		}
		article.put("cat_id", catid);
		article.put("add_time", DateUtil.toString(  new Date() , "yyyy-MM-dd hh:mm:ss "));
	  this.baseDaoSupport.insert(dataModel.getEnglish_name(), article);
	}

	
	public void delete(Integer catid,Integer articleid) {
		DataModel dataModel = this.getModelByCatid(catid);
		String sql  ="delete from "+ dataModel.getEnglish_name() +" where id=?";
		this.baseDaoSupport.execute(sql, articleid);
	}

	
	public void edit(Integer modelid,Integer catid,Integer articleid) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		DataModel dataModel = this.dataModelManager.get(modelid);
		List<DataField> fieldList = dataFieldManager.list(modelid);
		Map article = new HashMap(); 
		for(DataField field :fieldList){
			articlePluginBundle.onSave(article, field);
		}
 
	  this.baseDaoSupport.update(dataModel.getEnglish_name(), article,"id="+articleid);		

	}

	
	public Page list(Integer catid,int page,int pageSize) {
		DataModel model = this.getModelByCatid(catid);
		String 	sql  ="select "+buildFieldStr(model.getModel_id())+" from "+ model.getEnglish_name() +" where cat_id=? order by add_time desc";
		Page webpage  = this.baseDaoSupport.queryForPage(sql, page, pageSize, catid);
		return webpage;
	}
	
	
	public Page listAll(Integer catid, int page, int pageSize) {
		DataModel model = this.getModelByCatid(catid);
		DataCat cat  =this.dataCatManager.get(catid);
		StringBuffer sql  = new StringBuffer("select * from ");
		sql.append( this.getTableName(model.getEnglish_name()) );
		sql.append(" where cat_id in (select cat_id from ");
		sql.append(this.getTableName("data_cat"));
		sql.append(" where cat_path like '");
		sql.append(cat.getCat_path());
		sql.append("%'");
		sql.append(")  order by add_time desc");
		final List<DataField> fieldList  = this.dataFieldManager.list(model.getModel_id());
		return this.daoSupport.queryForPage(sql.toString(), page, pageSize, new RowMapper(){

			
			public Object mapRow(ResultSet rs, int c) throws SQLException {
				Map data  = new HashMap();
				 for(DataField field:fieldList){
					 Object value = null;
					 String name = field.getEnglish_name();
					 int data_type = field.getData_type();
				 
					 value=  rs.getString(name) ;
				 
					 
					IPlugin plugin = articlePluginBundle.findPlugin(field.getShow_form());
					if(plugin!=null){
						if(plugin instanceof IFieldValueShowEvent ){
							value = ((IFieldValueShowEvent)plugin).onShow(field, value);
						}
					}
					data.put(name, value);
				 }
				 data.put("id", rs.getInt("id"));
				 data.put("cat_id", rs.getInt("cat_id"));
				 data.put("add_time", rs.getTimestamp("add_time"));
				 data.put("hit", rs.getLong("hit"));
				return data;
			}
			
		});
	}

	
	
	
	public Map get(Integer articleid,Integer catid,boolean filter) {
		DataModel model = this.getModelByCatid(catid);
		String sql  ="select * from " + model.getEnglish_name() +" where id=?" ;
		Map data = this.baseDaoSupport.queryForMap(sql, articleid);
		
		if(filter){
			 List<DataField> fieldList  = this.dataFieldManager.list(model.getModel_id());
			 
			 for(DataField field:fieldList){
				 String name = field.getEnglish_name();
				 Object value = data.get(name);
				 IPlugin plugin = articlePluginBundle.findPlugin(field.getShow_form());
					if(plugin!=null){
						if(plugin instanceof IFieldValueShowEvent ){
							value = ((IFieldValueShowEvent)plugin).onShow(field, value);
							 data.put(name, value);	
						}
					}
					 
			 } 
		}
		return data;
	}
	
	private DataModel getModelByCatid(Integer catid){
		String sql ="select dm.* from " + this.getTableName("data_model") +" dm ,"
		+this.getTableName("data_cat" ) +" c where dm.model_id=c.model_id and c.cat_id=?";
		List modelList = this.daoSupport.queryForList(sql,DataModel.class, catid);
		if(modelList == null || modelList.isEmpty()){
			throw new RuntimeException("此类别不存在模型");
		}
		DataModel model  =(DataModel) modelList.get(0);
		
		return model;
	}
	
	
	private String buildFieldStr(Integer modelid){
		StringBuffer sql  = new StringBuffer("id");
		List<DataField> fieldList =  this.dataFieldManager.listIsShow(modelid);
		for(DataField field:fieldList){
			if(field.getIs_show()==1){
				sql.append(",");
				sql.append(field.getEnglish_name());
			}
		}
		return sql.toString();
	}
	
	
	
	public Page search(int pageNo, int pageSize, int modelid) {
		HttpServletRequest  request  = ThreadContextHolder.getHttpRequest();
		final  List<DataField> fieldList  =  this.dataFieldManager.list(modelid);
		DataModel model = this.dataModelManager.get(modelid);
		StringBuffer sql  = new StringBuffer();
		sql.append("select * from ");
		sql.append( model.getEnglish_name() );
		sql.append(" where ");
		int i=0;
		for(DataField field :fieldList){
			
			String showform = field.getShow_form();			
			if("image".equals(showform)) continue;
			
			String value  =request.getParameter(field.getEnglish_name());
			if(value!=null) value = StringUtil.toUTF8(value);
			if(StringUtil.isEmpty(value)) continue;
			
			String name= field.getEnglish_name();
		
			if(i!=0)sql.append(" and ");
			
			sql.append( name );			
			if("radio".equals(showform) || "select".equals(showform)){
				sql.append(" ='");
				sql.append(value);
				sql.append("'");
			} else{
				sql.append(" like '%");
				sql.append(value);
				sql.append("%'");
			}
			i++;
		}
		
		return this.baseDaoSupport.queryForPage(sql.toString(), pageNo, pageSize, new RowMapper(){

			
			public Object mapRow(ResultSet rs, int c) throws SQLException {
				Map data  = new HashMap();
				 for(DataField field:fieldList){
					 Object value = null;
					 String name = field.getEnglish_name();
					 int data_type = field.getData_type();
				 
					 value=  rs.getString(name) ;
				 
					 
					IPlugin plugin = articlePluginBundle.findPlugin(field.getShow_form());
					if(plugin!=null){
						if(plugin instanceof IFieldValueShowEvent ){
							value = ((IFieldValueShowEvent)plugin).onShow(field, value);
						}
					}
					data.put(name, value);
				 }
				 data.put("id", rs.getInt("id"));
				 data.put("cat_id", rs.getInt("cat_id"));
				 data.put("add_time", rs.getTimestamp("add_time"));
				 data.put("hit", rs.getLong("hit"));
				return data;
			}
			
		});
	}
 

	public IDataModelManager getDataModelManager() {
		return dataModelManager;
	}

	public void setDataModelManager(IDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public IDataFieldManager getDataFieldManager() {
		return dataFieldManager;
	}

	public void setDataFieldManager(IDataFieldManager dataFieldManager) {
		this.dataFieldManager = dataFieldManager;
	}

	public ArticlePluginBundle getArticlePluginBundle() {
		return articlePluginBundle;
	}

	public void setArticlePluginBundle(ArticlePluginBundle articlePluginBundle) {
		this.articlePluginBundle = articlePluginBundle;
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}




}
