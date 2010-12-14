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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 会员中心-修改密码挂件
 * 
 * @author lzf<br/>
 *         2010-3-19 下午12:12:06<br
 *         / version 1.0<br/>
 */
public class MemberSecurityWidget extends AbstractMemberWidget {

	private IRegionsManager regionsManager;
	private IMemberManager memberManager;

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("member_security");

		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String action = request.getParameter("action");
		action = action == null ? "" : action;
		if (action.equals("save")) {
			String oldPassword = request.getParameter("oldpassword");
			oldPassword = oldPassword == null ? "" : StringUtil
					.md5(oldPassword);
			//System.out.println("oldpassword:" + oldPassword + " password:"
			//		+ member.getPassword());
			if (oldPassword.equals(member.getPassword())) {
				String password = request.getParameter("password");
				String passwd_re = request.getParameter("passwd_re");
		
				if (passwd_re.equals(password)) {
					try {
						memberManager.updatePassword(password);
						this.showSuccess("修改密码成功", "修改密码",
								"member_security.html");
					} catch (Exception e) {
						if (this.logger.isDebugEnabled()) {
							logger.error(e.getStackTrace());
						}
						this
								.showError("修改密码失败", "修改密码",
										"member_security.html");
					}
				} else {
					this.showError("修改失败！两次输入的密码不一致", "修改密码",
							"member_security.html");
				}
			} else {
				this.showError("修改失败！原始密码不符", "修改密码", "member_security.html");
			}
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
