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
package com.enation.eop.core.resource.model;

import java.io.Serializable;


/**
 * @author lzf
 *         <p>
 *         created_time 2009-11-27 下午01:40:20
 *         </p>
 * @version 1.0
 */
public class EopSite implements Serializable {
 
	private static final long serialVersionUID = 7525130003L;
	private Integer id;
	private Integer userid;
	private String sitename;
	private String productid;
	private String descript;
	private Integer themeid;
	private String themepath;
	
	private Integer adminthemeid;
	private String icofile;
	private String logofile;
	private String keywords;
	
	private int point ; //站点积分
	
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Integer getThemeid() {
		return themeid;
	}

	public void setThemeid(Integer themeid) {
		this.themeid = themeid;
	}

	public Integer getAdminthemeid() {
		return adminthemeid;
	}

	public void setAdminthemeid(Integer adminthemeid) {
		this.adminthemeid = adminthemeid;
	}

	public String getIcofile() {
		return icofile;
	}

	public void setIcofile(String icofile) {
		this.icofile = icofile;
	}

	public String getLogofile() {
		return logofile;
	}

	public void setLogofile(String logofile) {
		this.logofile = logofile;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}


	public String getThemepath() {
		return themepath;
	}

	public void setThemepath(String themepath) {
		this.themepath = themepath;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	

}