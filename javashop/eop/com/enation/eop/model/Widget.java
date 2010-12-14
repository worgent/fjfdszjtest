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
package com.enation.eop.model;

import java.util.HashMap;
import java.util.Map;

import com.enation.eop.core.resource.model.EopApp;
import com.enation.eop.core.resource.model.EopSite;

/**
 * 挂件
 * 
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 16:31:34
 */
public class Widget {

	private Map<String,String> attrMap;
	private String widgetType;
	private EopApp app;
	private EopSite site;
	
	
	public Map<String, String> getAttrMap() {
		return attrMap;
	}


	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}


	public EopSite getSite() {
		return site;
	}


	public void setSite(EopSite site) {
		this.site = site;
	}


	public Widget() {
		attrMap = new HashMap<String, String>();
	}

	
	public Widget(EopApp app) {
		this.app = app;
		attrMap = new HashMap<String, String>();
	}
	
	
	/**
	 * 设置挂件属性
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name,String value){
		
		this.attrMap.put(name, value);
	}
	
	public void setAttributes(Map<String,String> attrs){
		this.attrMap = attrs;
	}
	
	
	/**
	 * 读取挂件的某个属性
	 * @param name
	 * @return
	 */
	public String getAttribute(String name){
		name=name==null ? null :name.toLowerCase();
		return this.attrMap.get(name);
	}
	
	/**
	 * 读取挂件的所有属性
	 * @return
	 */
	public Map<String,String> getAtributes(){
		
		return this.attrMap;
	}


	public String getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}

	public EopApp getApp() {
		return app;
	}

	public void setApp(EopApp app) {
		this.app = app;
	}

}