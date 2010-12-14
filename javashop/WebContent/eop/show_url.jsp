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
		
		if("3".equals(status)) {
			
			out.println("支付成功");
		} else {
			//支付失败
			out.println("支付失败");
		}
		
		//------------------------------
		//处理业务完毕
		//------------------------------
		
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