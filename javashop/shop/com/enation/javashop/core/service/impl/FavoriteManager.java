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

import com.enation.eop.context.EopContext;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Favorite;
import com.enation.javashop.core.service.IFavoriteManager;

/**
 * 我的收藏
 * 
 * @author lzf<br/>
 *         2010-3-24 下午02:54:26<br/>
 *         version 1.0<br/>
 */
public class FavoriteManager extends BaseSupport implements IFavoriteManager {

	
	public Page list(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select g.*, f.favorite_id from " + this.getTableName("favorite")
				+ " f left join " + this.getTableName("goods")
				+ " g on g.goods_id = f.goods_id";
		sql += " where f.member_id = ?";
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize, member
				.getMember_id());
		return page;
	}

	
	public void delete(int favorite_id) {
		this.baseDaoSupport.execute("delete from favorite where favorite_id = ?", favorite_id);
	}

	
	public void add(Integer goodsid) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		Favorite favorite = new Favorite();
		favorite.setGoods_id(goodsid);
		favorite.setMember_id(member.getMember_id());
		this.baseDaoSupport.insert("favorite", favorite);
	}

	
	public List list( ) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		
		return this.baseDaoSupport.queryForList("select * from favorite where member_id=?", Favorite.class, member.getMember_id());
	}

}
