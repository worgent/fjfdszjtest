<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html:html lang="true">
<head>
	<html:base />
	<title>结算中心:填写收货地址</title>
	<link href="<%=request.getContextPath()%>/css/header01.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/index.css"
		type="text/css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/css/catalog.css"
		type="text/css" rel="stylesheet" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
	<script type="text/javascript">
	    function pay(){
	        
	    }
	</script>
</head>

<body>
	<center>
		<jsp:include page="top.jsp" />
		<form
			action="https://payment.chinapay.com/pay/TransGet"
			method="get">
			<table width="98%" align="center" cellspacing="1" cellpadding="2"
				bgcolor="#e1e1e1" border="0">
				<tr>
					<td valign="middle" bgcolor="#ffffff" align="left">
						<h3>
							感谢您的订购
						</h3>
					</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff" align="left">
						您的订单编号为${payTypeMap.orderId},已经送交处理中心，很快你就可以收到货物了。
					</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff" align="left">
						您需要支付的金额为${allSum}元，祝您购物愉快！
						<input type="hidden" name="MerId" value="${MerId }" />
						<input type="hidden" name="OrdId" value="${OrdId }" />
						<input type="hidden" name="TransAmt" value="${TransAmt}" />
						<input type="hidden" name="CuryId" value="156" />
						<input type="hidden" name="TransDate" value="${TransDate }" />
						<input type="hidden" name="TransType" value="0001" />
						<input type="hidden" name="Version" value="20040916" />
						<input type="hidden" name="BgRetUrl"
							value="http://www.eeu8.com/pay.do?status=test" />
						<input type="hidden" name="PageRetUrl"
							value="localhost:8088/pay.do" />
						<input type="hidden" name="ChkValue"
							value="${ChkValue }" />
						<input type="hidden" name="GateId" value=""/>
						<input type="hidden" name="Priv1" value="Memo"/> 
						<input type="submit" value="网上支付" />
					</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff" align="left">
						如果您在付款过程中遇到问题，您可以拨打客服热线15905094440寻求帮助。如果7天没有支付成功，您的订单将被取消。
					</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff" align="left">
						我想进行如下操作
					</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a
							href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=getOrderDetails&orderId=${payTypeMap.orderId}">查看或编辑我的订单</a>
						<br />
						在我的帐户中可以查询订单、取消订单，还有更多功能，不如现在就去
						<a
							href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">我的账户</a>看看吧
					</td>
				</tr>
			</table>
		</form>
		<jsp:include flush="true" page="bottom.jsp" />
	</center>
</body>
</html:html>



