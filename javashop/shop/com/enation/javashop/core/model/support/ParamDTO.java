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

public class ParamDTO {
	private String[] paramnums; // 参数组中拥有参数的个数
	private String[] groupnames;// 参数组的名称
	private String[] paramnames;// 参数名称
	private String[] paramvalues;// 参数值
	public String[] getGroupnames() {
		return groupnames;
	}
	public void setGroupnames(String[] groupnames) {
		this.groupnames = groupnames;
	}
	public String[] getParamnames() {
		return paramnames;
	}
	public void setParamnames(String[] paramnames) {
		this.paramnames = paramnames;
	}
	public String[] getParamnums() {
		return paramnums;
	}
	public void setParamnums(String[] paramnums) {
		this.paramnums = paramnums;
	}
	public String[] getParamvalues() {
		return paramvalues;
	}
	public void setParamvalues(String[] paramvalues) {
		this.paramvalues = paramvalues;
	}
	
	
}
