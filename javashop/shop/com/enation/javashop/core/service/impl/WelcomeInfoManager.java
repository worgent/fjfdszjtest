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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.eop.context.EopContext;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.DoubleMapper;
import com.enation.javashop.core.service.IWelcomeInfoManager;

public class WelcomeInfoManager extends BaseSupport implements
		IWelcomeInfoManager {

	
	public Map mapWelcomInfo() {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		Map map = this.baseDaoSupport.queryForMap("SELECT count(*) as oNum  FROM order where pay_status=0 and member_id=? and status != 4", member.getMember_id());
		int totalOrder = this.baseDaoSupport.queryForInt( "SELECT count(*) as totalOrder  FROM order where member_id=?", member.getMember_id() );
		map.put("totalOrder", totalOrder);
		int mNum = this.baseDaoSupport.queryForInt( "SELECT count(*) as mNum FROM message where to_id=? and unread='0' and to_type=0 and disabled='false'", member.getMember_id() );
		map.put("mNum", mNum);
		int pNum = this.baseDaoSupport.queryForInt( "SELECT sum(point) as pNum FROM member where member_id=?", member.getMember_id() );
		map.put("pNum", pNum);
		Object oaNum = this.baseDaoSupport.queryForObject( "SELECT advance FROM member where member_id=?",new DoubleMapper(), member.getMember_id() );
		Double aNum = oaNum == null ? 0L : (Double)oaNum;
		map.put("aNum", aNum);
		int couponNum = this.baseDaoSupport.queryForInt( "SELECT count(*) as couponNum FROM member_coupon where member_id=?", member.getMember_id() );
		map.put("couponNum", couponNum);
		Long curTime = (new Date()).getTime();
		String sql = "SELECT count(*) as cNum FROM " + this.getTableName("member_coupon") + " as mc left join " + this.getTableName("coupons") + " as c on c.cpns_id=mc.cpns_id left join " + this.getTableName("promotion") + " as p on c.pmt_id=p.pmt_id where member_id=?";
		sql += " and p.pmt_time_end - " + curTime + " < 1296000";
		int cNum = this.daoSupport.queryForInt(sql, member.getMember_id()  );
		map.put("cNum", cNum);
		int commentRNum = this.baseDaoSupport.queryForInt( "SELECT count(*) as commentRNum FROM comments where author_id=? and display='true' and lastreply>0", member.getMember_id() );
		map.put("commentRNum", commentRNum);
		List pa = this.baseDaoSupport.queryForList( "SELECT name FROM promotion_activity where enable='1' and end_time>=? and begin_time<=?", Object.class, curTime, curTime );
		pa = pa == null ? new ArrayList() : pa;
		map.put("pa", pa);
		return map;
	}

}
