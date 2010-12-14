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
package com.enation.javashop.plugin.payment.deposit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.AdvanceLogs;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.IPaymentEvent;
import com.enation.javashop.core.service.IAdvanceLogsManager;
import com.enation.javashop.core.service.IMemberManager;
import com.sun.jmx.snmp.ThreadContext;

/**
 * 预存款支付
 * @author kingapex
 * 2010-5-26下午02:29:09
 */
public class DepositPlugin extends AbstractPaymentPlugin implements
		IPaymentEvent {
	
	private IMemberManager memberManager;
	private IAdvanceLogsManager advanceLogsManager;
	
	
	public String onCallBack() {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String orderid = request.getParameter("orderid");
		
		try{
			this.paySuccess(Integer.valueOf(orderid));
		}catch(RuntimeException e){
			if(this.logger.isDebugEnabled()){
				this.logger.debug(e.getMessage(),e);
			}
			return e.getMessage();
		}
		
		return "支付成功";
	}

	
	public String onPay(PayCfg payCfg, Order order) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member  = userService.getCurrentMember();
		if(member== null ){
			 
			String url="";
			try {
				url = URLEncoder.encode("widget?type=paywidget&orderid="+order.getOrder_id()+"&paymentid="+payCfg.getId(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			return "<script>location.href='member_login.html?forward="+url+"'</script>";
		}
		
		return "<script>location.href='"+this.getCallBackUrl()+"&orderid="+order.getOrder_id()+"';</script>正在支付...";
	
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "deposit";
	}

	
	public String getName() {
		
		return "预存款支付";
	}

	
	public String getType() {
		
		return "payment";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

	
	public void perform(Object... params) {
		

	}

	
	public void register() {

	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public IAdvanceLogsManager getAdvanceLogsManager() {
		return advanceLogsManager;
	}

	public void setAdvanceLogsManager(IAdvanceLogsManager advanceLogsManager) {
		this.advanceLogsManager = advanceLogsManager;
	}


}
