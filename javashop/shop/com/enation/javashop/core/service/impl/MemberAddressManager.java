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
import java.util.List;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.model.MemberAddress;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.javashop.core.service.IMemberAddressManager;

/**
 * 会员中心-收货地址
 * 
 * @author lzf<br/>
 *         2010-3-17 下午03:03:56<br/>
 *         version 1.0<br/>
 */
public class MemberAddressManager extends BaseSupport<MemberAddress> implements
		IMemberAddressManager {

	
	public void addAddress(MemberAddress address) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		address.setMember_id(member.getMember_id());
		this.baseDaoSupport.insert("member_address", address);
	}

	
	public void deleteAddress(int addr_id) {
		this.baseDaoSupport.execute(
				"delete from member_address where addr_id = ?", addr_id);
	}

	
	public MemberAddress getAddress(int addr_id) {
		MemberAddress address = this.baseDaoSupport.queryForObject(
				"select * from member_address where addr_id = ?",
				MemberAddress.class, addr_id);
		return address;
	}

	
	public List listAddress() {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		List list = this.baseDaoSupport.queryForList(
				"select * from member_address where member_id = ?", member
						.getMember_id());
		return list;
	}

	
	public void updateAddress(MemberAddress address) {
		this.baseDaoSupport.update("member_address", address, "addr_id="
				+ address.getAddr_id());
	}

}
