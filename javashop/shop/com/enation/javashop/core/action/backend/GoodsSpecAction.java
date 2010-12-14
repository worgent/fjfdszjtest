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
package com.enation.javashop.core.action.backend;

import java.util.List;
import java.util.Map;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.service.ISpecManager;
import com.enation.javashop.core.service.ISpecValueManager;

/**
 * 商品规格操作action
 * 
 * @author kingapex 2010-3-8下午04:22:31
 */
public class GoodsSpecAction extends WWAction {
	private ISpecManager specManager;
	private ISpecValueManager specValueManager;
	private List specList;
	private Integer spec_id;
	private Integer value_id;
	private Map spec;
	private List valueList;

	public String execute() {
		specList = specManager.list();
		return "select";
	}

	public String getValues() {
		this.spec = this.specManager.get(spec_id);
		this.valueList = this.specValueManager.list(spec_id);
		return "values";
	}

	public String addOne() {
		spec = this.specValueManager.get(value_id);
		return "add_one";
	}

	public String addAll() {

		return "add_all";
	}

	public ISpecManager getSpecManager() {
		return specManager;
	}

	public void setSpecManager(ISpecManager specManager) {
		this.specManager = specManager;
	}

	public List getSpecList() {
		return specList;
	}

	public void setSpecList(List specList) {
		this.specList = specList;
	}

	public ISpecValueManager getSpecValueManager() {
		return specValueManager;
	}

	public void setSpecValueManager(ISpecValueManager specValueManager) {
		this.specValueManager = specValueManager;
	}

	public Map getSpec() {
		return spec;
	}

	public void setSpec(Map spec) {
		this.spec = spec;
	}

	public List getValueList() {
		return valueList;
	}

	public void setValueList(List valueList) {
		this.valueList = valueList;
	}

	public Integer getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(Integer specId) {
		spec_id = specId;
	}

	public Integer getValue_id() {
		return value_id;
	}

	public void setValue_id(Integer valueId) {
		value_id = valueId;
	}
	
 
 
}
