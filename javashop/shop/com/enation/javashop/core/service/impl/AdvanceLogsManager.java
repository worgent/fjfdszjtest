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
package com.enation.javashop.core.service.impl;

import java.util.List;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.AdvanceLogs;
import com.enation.javashop.core.service.IAdvanceLogsManager;

/**
 * 预存款日志
 * 
 * @author lzf<br/>
 *         2010-3-25 下午01:36:37<br/>
 *         version 1.0<br/>
 */
public class AdvanceLogsManager extends BaseSupport implements
		IAdvanceLogsManager {

	
	public Page pageAdvanceLogs(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		Page page = this.baseDaoSupport.queryForPage("select * from advance_logs where member_id=? order by mtime DESC", pageNo, pageSize, member.getMember_id());
		return page;
	}

	
	public void add(AdvanceLogs advanceLogs) {
		this.baseDaoSupport.insert("advance_logs", advanceLogs);
		
	}

	
	public List listAdvanceLogsByMemberId(int member_id) {
		return this.baseDaoSupport.queryForList("select * from advance_logs where member_id=? order by mtime desc", AdvanceLogs.class, member_id);
	}

}
