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
package com.enation.eop.sdk.user;

import java.io.Serializable;

/**
 * 用户上下文
 * @author kingapex
 *
 */
public class UserContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 752513002L;
	public static final String CONTEXT_KEY= "usercontext";
	private Integer userid;
	private Integer siteid;
	private Integer managerid; 
	
	public  UserContext(){}
	
	public  UserContext(Integer userid,Integer siteid,Integer managerid){
		this.userid = userid;
		this.siteid = siteid;
		this.managerid = managerid;
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

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

 
	
}
