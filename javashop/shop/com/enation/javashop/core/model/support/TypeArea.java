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
package com.enation.javashop.core.model.support;

import com.enation.framework.database.NotDbField;

/**
 * 配送地区费用表
 * @author kingapex
 *2010-3-28下午11:32:01
 */
public class TypeArea {
 		
	private Integer type_id	;
	private String area_id_group;	
	private String area_name_group;
	private String expressions;		
	private Integer  has_cod;
	private String config;
	private TypeAreaConfig typeAreaConfig;
	
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer typeId) {
		type_id = typeId;
	}
	public String getArea_id_group() {
		return area_id_group;
	}
	public void setArea_id_group(String areaIdGroup) {
		area_id_group = areaIdGroup;
	}
	public String getArea_name_group() {
		return area_name_group;
	}
	public void setArea_name_group(String areaNameGroup) {
		area_name_group = areaNameGroup;
	}
	public String getExpressions() {
		return expressions;
	}
	public void setExpressions(String expressions) {
		this.expressions = expressions;
	}
	public Integer getHas_cod() {
		return has_cod;
	}
	public void setHas_cod(Integer hasCod) {
		has_cod = hasCod;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	
	@NotDbField
	public TypeAreaConfig getTypeAreaConfig() {
		return typeAreaConfig;
	}
	public void setTypeAreaConfig(TypeAreaConfig typeAreaConfig) {
		this.typeAreaConfig = typeAreaConfig;
	}
	
}
