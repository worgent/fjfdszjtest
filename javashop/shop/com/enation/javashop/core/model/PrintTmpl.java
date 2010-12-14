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
 * 打印模板
 * 
 * @author lzf<br/>
 *         2010-5-4上午09:58:30<br/>
 *         version 1.0
 */
public class PrintTmpl implements java.io.Serializable {
	private Integer prt_tmpl_id;
	private String prt_tmpl_title;
	private String shortcut;
	private String disabled;
	private Integer prt_tmpl_width;
	private Integer prt_tmpl_height;
	private String prt_tmpl_data;
	private String bgimage;

	public Integer getPrt_tmpl_id() {
		return prt_tmpl_id;
	}

	public void setPrt_tmpl_id(Integer prtTmplId) {
		prt_tmpl_id = prtTmplId;
	}

	public String getPrt_tmpl_title() {
		return prt_tmpl_title;
	}

	public void setPrt_tmpl_title(String prtTmplTitle) {
		prt_tmpl_title = prtTmplTitle;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Integer getPrt_tmpl_width() {
		return prt_tmpl_width;
	}

	public void setPrt_tmpl_width(Integer prtTmplWidth) {
		prt_tmpl_width = prtTmplWidth;
	}

	public Integer getPrt_tmpl_height() {
		return prt_tmpl_height;
	}

	public void setPrt_tmpl_height(Integer prtTmplHeight) {
		prt_tmpl_height = prtTmplHeight;
	}

	public String getPrt_tmpl_data() {
		return prt_tmpl_data;
	}

	public void setPrt_tmpl_data(String prtTmplData) {
		prt_tmpl_data = prtTmplData;
	}

	public String getBgimage() {
		return bgimage;
	}

	public void setBgimage(String bgimage) {
		this.bgimage = bgimage;
	}

}
