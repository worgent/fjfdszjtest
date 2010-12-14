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
package com.enation.eop.sdk.webapp.taglib.html;

import com.enation.eop.sdk.webapp.taglib.HtmlTaglib;
import com.enation.eop.sdk.webapp.taglib.html.support.GridCellProvider;

public class GridCellTaglib extends HtmlTaglib {
	private String sort;

	private String sortDefault;

	private String order;

	private int isSort;

	private int isAjax;

	private String style;

	private String width;

	private String height;

	private String align;

	private String plugin_type;

	private GridCellProvider cellProvider;

	public GridCellTaglib(){
		cellProvider = new GridCellProvider();
	}
	/**
	 * 这个html标签标记的开始段
	 */
	
	protected String postStart() {
		
		cellProvider.initCellProvider(this);
		
		return cellProvider.getStartHtml(); 
	} 

	/**
	 * 这个标记的结束串
	 */
	
	protected String postEnd() {

		return this.cellProvider.getEndHtml();
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortDefault() {
		return sortDefault;
	}

	public void setSortDefault(String sortDefault) {
		this.sortDefault = sortDefault;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getPlugin_type() {
		return plugin_type;
	}

	public void setPlugin_type(String plugin_type) {
		this.plugin_type = plugin_type;
	}

	public int getIsAjax() {
		return isAjax;
	}

	public void setIsAjax(int isAjax) {
		this.isAjax = isAjax;
	}

	public int getIsSort() {
		return isSort;
	}

	public void setIsSort(int isSort) {
		this.isSort = isSort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}