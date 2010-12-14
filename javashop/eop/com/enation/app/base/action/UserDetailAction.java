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
package com.enation.app.base.action;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.IUserDetailManager;
import com.enation.eop.core.resource.model.EopUserDetail;
import com.enation.framework.action.WWAction;

/**
 * @author lzf
 * <p>created_time 2009-12-4 下午03:55:33</p>
 * @version 1.0
 */
public class UserDetailAction extends WWAction {
	
	private EopUserDetail eopUserDetail;
	private IUserDetailManager userDetailManager;
	private Integer userid;
	public String execute() throws Exception {
		this.userid =EopContext.getContext().getCurrentSite().getUserid();
		eopUserDetail =userDetailManager.get(userid);
		return "input";
	} 
	
	public String save() throws Exception {
		try{
		userDetailManager.edit(eopUserDetail);
		this.msgs.add("修改成功");
		} catch (RuntimeException e) {
			this.msgs.add(e.getMessage());

		}
		this.urls.put("用户信息页面", "userDetail.do");
 
		return this.MESSAGE;
	}

	public EopUserDetail getEopUserDetail() {
		return eopUserDetail;
	}

	public void setEopUserDetail(EopUserDetail eopUserDetail) {
		this.eopUserDetail = eopUserDetail;
	}

	public IUserDetailManager getUserDetailManager() {
		return userDetailManager;
	}

	public void setUserDetailManager(IUserDetailManager userDetailManager) {
		this.userDetailManager = userDetailManager;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
}
