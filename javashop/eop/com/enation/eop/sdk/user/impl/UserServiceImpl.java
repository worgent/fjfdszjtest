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
package com.enation.eop.sdk.user.impl;

import org.apache.log4j.Logger;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserContext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;

public final class UserServiceImpl implements IUserService {
	private UserContext userContext;
	
	protected static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	public UserServiceImpl() {
		if(logger.isDebugEnabled()){
			logger.debug("create userservice impl ");
		}
		WebSessionContext<UserContext> webSessionContext = ThreadContextHolder
				.getSessionContext();
		userContext = webSessionContext.getAttribute(UserContext.CONTEXT_KEY);
		
		if(logger.isDebugEnabled()){
			logger.debug("get  userContext is "+ userContext);
		}
	}

	
	public Integer getCurrentSiteId() {
		if (isUserLoggedIn()) {
			return userContext.getSiteid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public Integer getCurrentUserId() {
		if (isUserLoggedIn()) {
			return userContext.getUserid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public boolean isUserLoggedIn() {
		if (userContext == null)
			return false;
		else
			return true;
	}

	
	public Integer getCurrentManagerId() {
		if (isUserLoggedIn()) {
			return userContext.getManagerid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public Member getCurrentMember() {
		Member member = (Member)ThreadContextHolder.getSessionContext().getAttribute(IUserService.CURRENT_MEMBER_KEY);
/*	Member member = new Member();
		member.setMember_id(1);
		member.setName("bbb");
		member.setProvince_id(104);
		member.setCity_id(105);
		member.setRegion_id(106);
		member.setRegtime(1267572800000L);
		member.setBirthday(1267572800000L);
		member.setSex(1);
		member.setUname("测试");
		member.setEmail("1@2.com");
		member.setAddress("aaa");
		member.setZip("202020");
		member.setMobile("1391111234");
		member.setTel("119");
		member.setPw_question("Why?");
		member.setPw_answer("Oh!");
		member.setPassword("e4789cf2281e1d05a5685438d2cfad");//StringUtil.md5(752513)
		member.setPoint(1440);
		member.setLv_id(1);
		member.setAdvance(new Double(250));*/
		return member;
	}

}
