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
package com.enation.cms.core.model;

import java.io.Serializable;
import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 数据分类
 * @author kingapex
 * 2010-7-5上午07:17:43
 */
public class DataCat implements Serializable{

 
	private static final long serialVersionUID = 3764718745312895356L;


	protected Integer cat_id;
	protected String name;
	protected Integer parent_id;
	protected String cat_path;
	protected Integer cat_order;
	protected Integer model_id;
	protected Integer if_audit;
    
	//子类别，非数据库字段
	protected List<DataCat> children;
	private boolean hasChildren ;
	
    public Integer getIf_audit() {
		return if_audit;
	}
	public void setIf_audit(Integer if_audit) {
		this.if_audit = if_audit;
	}
 
	public Integer getCat_id() {
		return cat_id;
	}
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}

	public Integer getCat_order() {
		return cat_order;
	}
	public void setCat_order(Integer cat_order) {
		this.cat_order = cat_order;
	}
	public String getCat_path() {
		return cat_path;
	}
	public void setCat_path(String cat_path) {
		this.cat_path = cat_path;
	}
	public Integer getModel_id() {
		return model_id;
	}
	public void setModel_id(Integer model_id) {
		this.model_id = model_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	@NotDbField
	public List<DataCat> getChildren() {
		return children;
	}
	public void setChildren(List<DataCat> children) {
		this.children = children;
	}

	@NotDbField
	public boolean getHasChildren() {
		hasChildren = this.children==null|| this.children.isEmpty() ?false:true;
		return hasChildren;
	}


	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}	
}
