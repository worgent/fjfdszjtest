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
 * @author lzf
 * version 1.0<br/>
 * 2010-6-17&nbsp;下午02:31:00
 */
public class Tag implements java.io.Serializable{
	
	private Integer tag_id;
	private String tag_name;
	private Integer rel_count;
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tagId) {
		tag_id = tagId;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tagName) {
		tag_name = tagName;
	}
	public Integer getRel_count() {
		return rel_count;
	}
	public void setRel_count(Integer relCount) {
		rel_count = relCount;
	}
	
}
