<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
 
<%@ page import="com.tenpay.util.TenpayUtil" %>
<%@ page import="com.tenpay.MediPayResponseHandler"%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

//---------------------------------------------------------
//财付通中介担保支付应答（处理回调）示例，商户按照此文档进行开发即可
//---------------------------------------------------------

//平台商密钥
String key = "123456";

//创建MediPayResponseHandler实例
MediPayResponseHandler resHandler = new MediPayResponseHandler(request, response);

resHandler.setKey(key);

//判断签名
if(resHandler.isTenpaySign()) {
	//交易单号
	String cft_tid = resHandler.getParameter("cft_tid");
	
	//金额金额,以分为单位
	String total_fee = resHandler.getParameter("total_fee");
	
	//支付结果
	String retcode = resHandler.getParameter("retcode");
	
	//状态码
	String status = resHandler.getParameter("status");
	
	if( "0".equals(retcode) ) {
		//------------------------------
		//处理业务开始
		//------------------------------ 
		
		//注意交易单不要重复处理
		//注意判断返回金额
		
		int iStatus = TenpayUtil.toInt(status);
		switch(iStatus) {
			case 1:		//交易创建
				
				break;
			case 2:		//收获地址填写完毕
			
				break;
			case 3:		//买家付款成功，注意判断订单是否重复的逻辑
			
				break;
			case 4:		//卖家发货成功
			
				break;
			case 5:		//买家收货确认，交易成功
				
				break;
			case 6:		//交易关闭，未完成超时关闭
			
				break;
			case 7:		//修改交易价格成功
			
				break;
			case 8:		//买家发起退款
			
				break;
			case 9:		//退款成功
			
				break;
			case 10:	//退款关闭
			
				break;
			default:
				//nothing to do
			
		}
		
		//------------------------------
		//处理业务完毕
		//------------------------------
			
		//调用doShow, 打印meta值,告诉财付通处理成功.
		resHandler.doShow();
	} else {
		//当做不成功处理
		out.println("支付失败");
	}
	
} else {
	out.println("认证签名失败");
	//String debugInfo = resHandler.getDebugInfo();
	//System.out.println("debugInfo:" + debugInfo);
}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>财付通支付回调处理</title>
</head>
<body>

</body>
</html>