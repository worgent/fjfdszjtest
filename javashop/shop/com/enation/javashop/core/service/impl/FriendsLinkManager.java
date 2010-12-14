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

import com.enation.eop.EopSetting;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.utils.UploadUtil;
import com.enation.javashop.core.model.FriendsLink;
import com.enation.javashop.core.model.mapper.FriendsLinkMapper;
import com.enation.javashop.core.service.IFriendsLinkManager;

/**
 * 友情链接
 * 
 * @author lzf<br/>
 *         2010-4-8 下午06:20:29<br/>
 *         version 1.0<br/>
 */
public class FriendsLinkManager extends BaseSupport<FriendsLink> implements
		IFriendsLinkManager {

	
	public void add(FriendsLink friendsLink) {
		this.baseDaoSupport.insert("friends_link", friendsLink);

	}

	
	public void delete(String id) {
		this.baseDaoSupport.execute("delete from friends_link where link_id in (" + id + ")");

	}

	
	public List listLink() {
		
		return this.baseDaoSupport.queryForList("select * from friends_link order by sort",new FriendsLinkMapper());
	}

	
	public void update(FriendsLink friendsLink) {
		this.baseDaoSupport.update("friends_link", friendsLink, "link_id = " + friendsLink.getLink_id());

	}

	
	public FriendsLink get(int link_id) {
		FriendsLink friendsLink = this.baseDaoSupport.queryForObject("select * from friends_link where link_id = ?", FriendsLink.class, link_id);
		String logo  = friendsLink.getLogo();
		if(logo!=null) {
			logo  =UploadUtil.replacePath(logo);
			friendsLink.setLogo(logo);
		}
		return  friendsLink;
	}

}
