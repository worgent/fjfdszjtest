/**
 * 说明：网关短信处理主类。
 * 		 接收 
 * 			ProcessRecvDeliverMsg（CMPPMessage msg）接收短信
 * 		 发送 
 * 			SendMessage（SmsBean sms）		仅处理短信的发送
 * 			CarLibrarySendSms（SmsBean sms）车辆管理专用
 * 时间：2008-12-16
 * 
 */
package net.trust.utils.sms;

//基本类的方法导入

public interface SMProxySendFacade {

	/**
	 * 华为短信发送类处理（公用类）
	 * 
	 * @param 短信类，包括手机号，内容，序号
	 */
	public int SendMessage(SmsBean sms);

	
}
