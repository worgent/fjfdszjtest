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

import com.enation.cms.core.model.DataModel;
import com.enation.cms.core.service.IDataFieldManager;
import com.enation.cms.core.service.IDataModelManager;
import com.enation.framework.action.WWAction;

/**
 * 模型管理action
 * 
 * @author kingapex 2010-7-2下午04:57:51
 */
public class DataModelAction extends WWAction {

	private IDataModelManager dataModelManager;
	private IDataFieldManager dataFieldManager;
	private Integer modelid;
	private DataModel dataModel;
	private List modelList;
	private List fieldList;
	
	public String list() {
		try{
		this.modelList  = this.dataModelManager.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "list";
	}
	
	public String add(){
		
		return "add";
	}
	
	public String edit(){
		dataModel =this.dataModelManager.get(modelid);
		fieldList = this.dataFieldManager.list(modelid);
		return "edit";
	}
	
	
	public String saveAdd(){
		try{
		this.dataModelManager.add(dataModel);
		this.msgs.add("模型添加成功");
		this.urls.put("模型列表", "model!list.do");
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.msgs.add("模型添加出现错误");
		}
		return this.MESSAGE;
	}
	
	public String saveEdit(){
		try{
			this.dataModelManager.edit(dataModel);
			this.msgs.add("模型修改成功");
			this.urls.put("模型列表", "model!list.do");
		}catch(RuntimeException e){
				this.logger.error(e.getMessage(), e);
				this.msgs.add("模型修改出现错误");
		}
			return this.MESSAGE;
	}
	
	public String delete(){
		try{
			this.dataModelManager.delete(modelid);
			this.json= "{result:1,message:'模型删除成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json= "{result:0,message:'模型删除失败'}";
		}
		return this.JSON_MESSAGE;
		
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

	public Integer getModelid() {
		return modelid;
	}

	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}

	public DataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public List getModelList() {
		return modelList;
	}

	public void setModelList(List modelList) {
		this.modelList = modelList;
	}

	public List getFieldList() {
		return fieldList;
	}

	public void setFieldList(List fieldList) {
		this.fieldList = fieldList;
	}

}
