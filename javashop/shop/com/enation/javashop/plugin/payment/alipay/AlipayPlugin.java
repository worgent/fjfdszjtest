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
package com.enation.javashop.plugin.payment.alipay;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.util.CheckURL;
import com.alipay.util.Payment;
import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.IPaymentEvent;

public class AlipayPlugin extends AbstractPaymentPlugin  implements IPaymentEvent {
	
	private static String paygateway = "https://www.alipay.com/cooperate/gateway.do?";
	
	
	public String onCallBack() {
		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		Map<String,String> configparams = this.getConfigParams();
		String partner = configparams.get("partner");  //partner合作伙伴id（必须填写）
		String privateKey =  configparams.get("key"); //partner 的对应交易安全校验码（必须填写）
		//**********************************************************************************
		//如果您服务器不支持https交互，可以使用http的验证查询地址
		//***注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接
		//String alipayNotifyURL = "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"
		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
		+ "partner="
		+ partner
		+ "&notify_id="
		+ request.getParameter("notify_id");
		//**********************************************************************************
		String sign = request.getParameter("sign");
		//获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
		String responseTxt = CheckURL.check(alipayNotifyURL);

		Map params = new HashMap();
		//获得POST 过来参数设置到新的params中
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
				: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化（现在已经使用）
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}

		String mysign = com.alipay.util.SignatureHelper_return.sign(params,
				privateKey);
		
		StringBuffer outString = new StringBuffer();
		if (mysign.equals(request.getParameter("sign"))
				&& responseTxt.equals("true")) {
			
			
			//以下输出测试时候用，可以删除
			String get_order = request.getParameter("out_trade_no");
			String get_total_fee = request.getParameter("total_fee");
			
			
			String get_subject ="";
			String get_body ="";
			
			try{
			get_subject = new String(request.getParameter("subject")
			.getBytes("ISO-8859-1"), "UTF-8");
			
			get_body= new String(request.getParameter("body")
			.getBytes("ISO-8859-1"), "UTF-8");
			
			}catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			outString.append("显示订单信息如下："+ "<br>");
			outString.append("订单号:"+get_order + "<br>");
			outString.append("订单总价:"+get_total_fee + "<br>");
			outString.append("商品名称:"+get_subject + "<br>");
			outString.append("商品描述:"+get_body + "<br>");
			outString.append("交易状态:" + request.getParameter("trade_status")
			+ "<br>");
			
			outString.append("responseTxt=" + responseTxt + "<br>");
			if ((request.getParameter("trade_status"))
			.equals("WAIT_SELLER_SEND_GOODS")) {
				outString.append("买家已经付款，等待卖家发货"); // 买家已经付款，等待卖家发货，请更改订单状态
				//此返回方式， 只有再客户付款成功之后返回。
				this.paySuccess(Integer.valueOf( get_order) );
			}
			if ((request.getParameter("trade_status"))
			.equals("WAIT_BUYER_CONFIRM_GOODS")) {
				outString.append("卖家已发货， 等待买家确认"); // 卖家已发货， 等待买家确认，请更改订单状态
				//此返回方式， 只有再客户付款成功之后返回。
			}
			if ((request.getParameter("trade_status"))
			.equals("TRADE_FINISHED")) {
				outString.append("交易成功"); // 买家已经付款，交易完成，请更改订单状态
			//   this.paySuccess(orderId);
			}
			
			
			
		} else {
			//打印，收到消息比对sign的计算结果和传递来的sign是否匹配
			outString.append(mysign + "----------------" + sign + "<br>");
			outString.append("支付失败" + "<br>");
		}
		
		return outString.toString();
	}

	
	public String onPay(PayCfg payCfg, Order order) {
		
		Map<String, String> data = new HashMap();
		String service =  "create_partner_trade_by_buyer";  //"create_direct_pay_by_user";//
		Map<String,String> params = this.getConfigParams();
		
		String sign_type = "MD5";
		String out_trade_no = "" + order.getOrder_id(); // 商户网站订单
		String input_charset = "utf-8";
		String partner =params.get("partner");  // 支付宝合作伙伴id (账户内提取)
		String key =  params.get("key");  // 支付宝安全校验码(账户内提取)
		String seller_email = params.get("seller_email"); // 卖家支付宝帐户
		
		
		// ******以上是账户信息，以下是商品信息**************************
		String body = "网店订单";//order.get("goods").toString().replaceAll("\"", "'"); // 商品描述，推荐格式：商品名称（订单编号：订单编号）
		String subject = "订单:" + order.getSn(); // 商品名称
		
		String  price = String.valueOf(order.getOrder_amount()); //"0.01";
		// //System.out.println("price is " + price);
		String quantity = "1";
		String show_url = this.getShowUrl();
		String payment_type = "1";
		String discount = "0";
		// ******物流信息和支付宝通知，一般商城不需要通知，请删除此参数，并且在payment.java里面相应删除参数********
		String logistics_type = "EXPRESS";
		String logistics_fee = "0.01";
		String logistics_payment = "SELLER_PAY";
		// String notify_url =
		// "http://10.2.17.136:8081/jsp_xuni/alipay_notify.jsp"; //通知接收URL
		String return_url = this.getCallBackUrl(); // 支付完成后跳转返回的网址URL

		String ItemUrl = Payment.CreateUrl(paygateway, service, sign_type,
				out_trade_no, input_charset, partner, key, seller_email, body, 
				subject, price, quantity, show_url, payment_type, discount,
				logistics_type, logistics_fee, logistics_payment, return_url);

		data.put("service", service);
		data.put("sign_type", sign_type);
		data.put("out_trade_no", out_trade_no);
		data.put("input_charset", input_charset);
		data.put("partner", partner);
		data.put("key", key);
		data.put("seller_email", seller_email);
		data.put("body", body);
		data.put("subject", subject);
		data.put("price", price);
		data.put("quantity", quantity);
		data.put("show_url", show_url);
		data.put("payment_type", payment_type);
		data.put("discount", discount);
		data.put("logistics_type", logistics_type);
		data.put("logistics_fee", logistics_fee);
		data.put("logistics_payment", logistics_payment);
		data.put("return_url", return_url);
		data.put("sign", ItemUrl);
		
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(this.getClass());
		freeMarkerPaser.setPageName("redict");
		freeMarkerPaser.putData("pay", data);
		return freeMarkerPaser.proessPageContent();
	}
	
	
	
	
	
	
	public void register() {

	}

	
	public String getAuthor() {
		return "kingapex";
	}

	
	public String getId() {
		return "alipay";
	}

	
	public String getName() {
		return "支付宝";
	}

	
	public String getType() {
		return "payment";
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {

	}

}
