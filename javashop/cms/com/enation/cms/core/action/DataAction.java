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
package com.enation.cms.core.action;

import java.util.List;
import java.util.Map;

import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.ArticlePluginBundle;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.cms.core.service.IDataFieldManager;
import com.enation.framework.action.WWAction;

/**
 * 文章管理action
 * @author kingapex
 * 2010-7-5上午11:22:57
 */
public class DataAction extends WWAction {
	
	private IDataFieldManager dataFieldManager;
	private IDataCatManager dataCatManager;
	private IDataManager dataManager;
	private ArticlePluginBundle articlePluginBundle;
	private Integer dataid;
	private Integer catid;
	private Integer modelid;
	private List<DataField> fieldList ;
	private DataCat cat;
	private Map article;
	private boolean isEdit;
	public String add(){
		this.isEdit = false;
		this.cat = this.dataCatManager.get(catid);
		this.modelid = cat.getModel_id();
		this.fieldList = dataFieldManager.listByCatId(catid);
		for(DataField field: fieldList){
			field.setInputHtml(articlePluginBundle.onDisplay(field, null));
		}
		return "input";
	}
	
	public String edit(){
		this.isEdit =true;
		this.article = this.dataManager.get(dataid, catid,false);
		this.cat = this.dataCatManager.get(catid);
		this.modelid = cat.getModel_id();
		this.fieldList = dataFieldManager.listByCatId(catid);
		for(DataField field: fieldList){
			field.setInputHtml(articlePluginBundle.onDisplay(field, article.get(field.getEnglish_name())));
		}		
		return "input";
	}

	public String saveAdd(){
		this.dataManager.add(modelid,catid);
		this.msgs.add("文章添加成功");
		this.urls.put("文章列表", "data!list.do?catid="+catid);
		return this.MESSAGE;
	}
	
	public String saveEdit(){
		this.dataManager.edit(modelid, catid, dataid);
		this.msgs.add("文章修改成功");
		this.urls.put("文章列表", "data!list.do?catid="+catid);
		return this.MESSAGE;
	}
	
	public String list(){
		this.webpage = this.dataManager.listAll(catid, this.getPage(), this.getPageSize());
		cat  = this.dataCatManager.get(catid);
		fieldList =  this.dataFieldManager.listIsShow(cat.getModel_id());
		return "list";
	}

	public String delete(){
		this.dataManager.delete(catid, dataid);
		this.msgs.add("文章删除成功");
		this.urls.put("文章列表", "data!list.do?catid="+catid);
		return this.MESSAGE;
	}
	public Integer getCatid() {
		return catid;
	}
	public void setCatid(Integer catid) {
		this.catid = catid;
	}

	public IDataFieldManager getDataFieldManager() {
		return dataFieldManager;
	}

	public void setDataFieldManager(IDataFieldManager dataFieldManager) {
		this.dataFieldManager = dataFieldManager;
	}

	public List getFieldList() {
		return fieldList;
	}

 

	public Integer getModelid() {
		return modelid;
	}

	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}

	public DataCat getCat() {
		return cat;
	}

	public void setCat(DataCat cat) {
		this.cat = cat;
	}

	public IDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public ArticlePluginBundle getArticlePluginBundle() {
		return articlePluginBundle;
	}

	public void setArticlePluginBundle(ArticlePluginBundle articlePluginBundle) {
		this.articlePluginBundle = articlePluginBundle;
	}

 

	public void setFieldList(List<DataField> fieldList) {
		this.fieldList = fieldList;
	}

 
	public Map getArticle() {
		return article;
	}

	public void setArticle(Map article) {
		this.article = article;
	}

	public boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Integer getDataid() {
		return dataid;
	}

	public void setDataid(Integer dataid) {
		this.dataid = dataid;
	}

 
}
