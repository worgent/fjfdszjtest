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
import com.enation.javashop.core.model.PointHistory;
import com.enation.javashop.core.service.IPointHistoryManager;

/**
 * 会员积分日志
 * 
 * @author lzf<br/>
 *         2010-3-22 上午11:27:23<br/>
 *         version 1.0<br/>
 */
public class PointHistoryManager extends BaseSupport implements
		IPointHistoryManager {

	
	public Page pagePointHistory(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select * from point_history where member_id = ? order by time desc";
		Page webpage = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize,
				member.getMember_id());
		return webpage;
	}

	
	public Long getConsumePoint() {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		Long result = this.baseDaoSupport
				.queryForLong(
						"select SUM(point) from point_history where point < 0 and member_id = ?",
						member.getMember_id());
		return result;
	}

	
	public Long getGainedPoint() {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		Long result = this.baseDaoSupport
				.queryForLong(
						"select SUM(point) from point_history where point > 0 and member_id = ?",
						member.getMember_id());
		return result;
	}

	
	public void addPointHistory(PointHistory pointHistory) {
		this.baseDaoSupport.insert("point_history", pointHistory);
	}

	
	public List listPointHistory(int member_id) {
		String sql = "select * from point_history where member_id = ? order by time desc";
		List list = this.baseDaoSupport.queryForList(sql,PointHistory.class, member_id);
		return list;
	}

}
