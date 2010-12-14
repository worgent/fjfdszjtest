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
package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 会员中心-用户信息挂件
 * @author lzf<br/>
 * 2010-3-16 上午10:11:49<br/>
 * version 1.0<br/>
 */
public class MemberInfoWidget extends AbstractMemberWidget {
	
	private IRegionsManager regionsManager;
	private IMemberManager memberManager;

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("myinfo");
		
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String page  = request.getParameter("action");
		page = page == null ? "" : page;
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if(page.equals("save")){//是保存动作
			String name = request.getParameter("member.name");
			member.setName(name);
			String sex = request.getParameter("member.sex");
			member.setSex(Integer.valueOf(sex));
			String birthday = request.getParameter("mybirthday");
			member.setBirthday(DateUtil.toDate(birthday, "yyyy-MM-dd").getTime());
			String province_id = request.getParameter("member.province_id");
			member.setProvince_id(Integer.valueOf(province_id));
			String city_id = request.getParameter("member.city_id");
			member.setCity_id(Integer.valueOf(city_id));
			String region_id = request.getParameter("member.region_id");
			member.setRegion_id(Integer.valueOf(region_id));
			String province = request.getParameter("member.province");
			member.setProvince(province);
			String city = request.getParameter("member.city");
			member.setCity(city);
			String region = request.getParameter("member.region");
			member.setRegion(region);
			String address = request.getParameter("member.address");
			member.setAddress(address);
			String zip = request.getParameter("member.zip");
			member.setZip(zip);
			String mobile = request.getParameter("member.mobile");
			member.setMobile(mobile);
			String tel = request.getParameter("member.tel");
			member.setTel(tel);
			String pw_question = request.getParameter("member.pw_question");
			member.setPw_question(pw_question);
			String pw_answer = request.getParameter("member.pw_answer");
			member.setPw_answer(pw_answer);
			try{
				memberManager.edit(member);
				this.showSuccess("修改成功","个人信息", "member_info.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("修改失败", "个人信息", "member_info.html");
			}
		}else{
			Long mybirthday = (Long)member.getBirthday();
			List provinceList = this.regionsManager.listProvince();
			List cityList = this.regionsManager.listCity(member.getProvince_id());
			cityList = cityList == null ? new ArrayList() : cityList;
			List regionList = this.regionsManager.listRegion(member.getCity_id());
			regionList = regionList == null ? new ArrayList() : regionList;
			this.putData("member", member);
			this.putData("provinceList", provinceList);
			this.putData("cityList", cityList);
			this.putData("regionList", regionList);
			if(mybirthday==null){
				this.putData("mybirthday", DateUtil.toDate("1970-01-01", "yyyy-MM-dd"));
			}else
				this.putData("mybirthday", (new Date(mybirthday)));
		}

	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
}
