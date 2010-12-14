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

/**
 * SEO实体
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-19 上午10:11:48<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class Seo {
	private Integer id;
	private String title;
	private String meta_keywords;
	private String meta_description;
	private Integer use_static;
	private Integer noindex_catalog;
	private Integer userid;
	private Integer siteid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMeta_keywords() {
		return meta_keywords;
	}

	public void setMeta_keywords(String metaKeywords) {
		meta_keywords = metaKeywords;
	}

	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String metaDescription) {
		meta_description = metaDescription;
	}

	public Integer getUse_static() {
		return use_static;
	}

	public void setUse_static(Integer useStatic) {
		use_static = useStatic;
	}

	public Integer getNoindex_catalog() {
		return noindex_catalog;
	}

	public void setNoindex_catalog(Integer noindexCatalog) {
		noindex_catalog = noindexCatalog;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

}
