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
package com.enation.javashop.core.model;

import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 规格实体
 * @author kingapex
 *2010-3-6上午12:30:54
 */
public class Specification {
	private Integer spec_id;
	private String spec_name;
	private Integer spec_show_type;
	private Integer spec_type;
	private String spec_memo;
 
	private List<SpecValue> valueList;
	
	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer specId) {
		spec_id = specId;
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String specName) {
		spec_name = specName;
	}
	public Integer getSpec_show_type() {
		return spec_show_type;
	}
	public void setSpec_show_type(Integer specShowType) {
		spec_show_type = specShowType;
	}
	public Integer getSpec_type() {
		return spec_type;
	}
	public void setSpec_type(Integer specType) {
		spec_type = specType;
	}
	public String getSpec_memo() {
		return spec_memo;
	}
	public void setSpec_memo(String specMemo) {
		spec_memo = specMemo;
	}
	
	@NotDbField
	public List<SpecValue> getValueList() {
		return valueList;
	}
	public void setValueList(List<SpecValue> valueList) {
		this.valueList = valueList;
	}
	
 
 
	
	
}
