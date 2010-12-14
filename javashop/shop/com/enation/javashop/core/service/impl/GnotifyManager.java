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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.eop.context.EopContext;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IGnotifyManager;

public class GnotifyManager extends BaseSupport implements IGnotifyManager {

	
	public Page pageGnotify(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select a.*, b.image_default image,b.store store, b.name name, b.price price, b.mktprice mktprice from "+ this.getTableName("gnotify")+" a left join "+ this.getTableName("goods")+" b on b.goods_id = a.goods_id";
		sql += " and a.member_id = " + member.getMember_id();
 		Page webpage = this.daoSupport.queryForPage(sql, pageNo, pageSize);
		List<Map> list = (List<Map>) (webpage.getResult());
//		for (Map gnotify : list) {
//			Long create_time = (Long)gnotify.get("create_time");
//			gnotify.put("create_date", (new Date(create_time)));
//			//Long send_time = (Long)gnotify.get("send_time");
//			//gnotify.put("send_date", (new Date(send_time)));
//		}
		return webpage;
	}

	
	public void deleteGnotify(int gnotify_id) {
		this.baseDaoSupport.execute("delete from gnotify where gnotify_id = ?", gnotify_id);
	}

}
