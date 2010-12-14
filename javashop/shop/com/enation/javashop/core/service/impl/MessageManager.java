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

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.model.Message;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IMessageManager;

public class MessageManager extends BaseSupport<Message> implements IMessageManager {

	
	public Page pageMessage(int pageNo, int pageSize, String folder) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select * from message where folder = ? ";
		if(folder.equals("inbox")){//收件箱
			sql += " and to_id = ? and del_status != '1'";
		}else{//发件箱
			sql += " and from_id = ? and del_status != '2'";
		}
		sql += " order by date_line desc";
		Page webpage = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, folder, member.getMember_id());
		List<Map> list = (List<Map>) (webpage.getResult());
//		for (Map order : list) {
//			Long time = (Long)order.get("date_line");
//			order.put("date", (new Date(time)));
//		}
		return webpage;
	}

	
	public void addMessage(Message message) {
		this.baseDaoSupport.insert("message", message);
		
	}

	
	public void delinbox(String ids) {
		this.baseDaoSupport.execute("delete from message where msg_id in (" + ids + ") and del_status = '2'");
		this.baseDaoSupport.execute("update message set del_status = '1' where msg_id in (" + ids + ")");
	}

	
	public void deloutbox(String ids) {
		this.baseDaoSupport.execute("delete from message where msg_id in (" + ids + ") and del_status = '1'");
		this.baseDaoSupport.execute("update message set del_status = '2' where msg_id in (" + ids + ")");
	}

	
	public void setMessage_read(int msg_id) {
		this.daoSupport.execute("update " + this.getTableName("message") + " set unread = '1' where msg_id = ?", msg_id);
	}

}
