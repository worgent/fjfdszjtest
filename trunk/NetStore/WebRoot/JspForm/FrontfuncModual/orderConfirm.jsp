<%@ page language="java"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>结算中心:订单确认</title>
		<link href="<%=request.getContextPath()%>/css/header01.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/index.css"
			type="text/css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/css/catalog.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript">
		     function doConfirm(){
                document.forms[0].action='<%=request.getContextPath()%>/JspForm/FrontfuncModual/cart.do?status=downOrder';
                document.forms[0].submit();
            }
            
            function toCheck(){
               document.forms[0].action='<%=request.getContextPath()%>/JspForm/FrontfuncModual/cart.do?status=goModifyCart';
                document.forms[0].submit();
            }
            
            function toModifyReceiver(){
                document.forms[0].action='<%=request.getContextPath()%>/JspForm/FrontfuncModual/cart.do?status=goModifyReceiver';
                document.forms[0].submit();
            }
            
            function goModifyPayAndSend(){
                document.forms[0].action='<%=request.getContextPath()%>/JspForm/FrontfuncModual/cart.do?status=goModifySendPayType';
                document.forms[0].submit();
            }
		</script>
	</head>
	<body>

		<form action="" method="post">
			<center>
				a<jsp:include page="top.jsp" />
				<table width="98%" align="center" cellspacing="1" cellpadding="2"
					bgcolor="#e1e1e1" border="0">
					<tr>
						<td align="left" bgcolor="#ffffff">
							查看以下订单信息,然后点击"订单确认"
						</td>
						<td align="right" bgcolor="#ffffff">
							<input type="button" value="订单确认" onclick="doConfirm()" />
						</td>
					</tr>
					<tr>
						<td colspan="2" bgcolor="#ffffff">
							<table cellspacing="1" cellpadding="2" bgcolor="#e1e1e1"
								border="0" width="100%">
								<tr>
									<td align="center" colspan="4" bgcolor="#ffffff">
										配送详情
									</td>
								</tr>
								<tr>
									<td align="left" colspan="4" bgcolor="#ffffff">
										商品信息:
										<input type="button" value="修改" onclick="toCheck()" />
									</td>
								</tr>
								<logic:present name="cart">
									<logic:iterate id="item" name="cart" property="items">
										<tr>
											<td width="50%" bgcolor="#ffffff">
												<a href="<%=request.getContextPath()%>/indexFirst.do?status=onlyGoodsShow&productId=${item.value.product.productId}" target="_blank">${item.value.product.productname}</a>
											</td>
											<td width="15%" bgcolor="#ffffff">
												单位:${item.value.product.unitName}
											</td>
											<td width="15%" bgcolor="#ffffff">
												数量:${item.value.number}
											</td>
											<td width="20%" bgcolor="#ffffff">
												单价:￥${item.value.product.memberPrice}元
												<fmt:formatNumber var="abc" value="${abc+item.value.product.memberPrice*item.value.number }" type="currency"></fmt:formatNumber>	
												<bean:define id="total1" value="${total1+item.value.product.memberPrice*item.value.number+sendFee}"></bean:define>
												<fmt:formatNumber var="totalFee" value="${total1}" type="currency"></fmt:formatNumber>
											</td>
										</tr>
									</logic:iterate>
									<tr> 
                                        <fmt:formatNumber var="fee" value="${sendFee}" type="currency"></fmt:formatNumber>	
                                        
										<td align="right" colspan="4" bgcolor="#ffffff">
											商品总价:${abc }元&nbsp;&nbsp;配送费:${fee}元&nbsp;&nbsp;订单金额:${totalFee}元
											<br />
											<font color="red">应付金额:${totalFee}元</font>

										</td>
									</tr>
								</logic:present>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" bgcolor="#ffffff">
							<table cellspacing="1" cellpadding="2" bgcolor="#e1e1e1"
								border="0" width="100%">
								<tr>
									<td align="left" colspan="3" bgcolor="#ffffff">
										送货地址:
										<input type="button" value="修改" onclick="toModifyReceiver()" />
									</td>
								</tr>
								<tr>
									<td align="left" bgcolor="#ffffff">
										收件人姓名:${sendInfoMap.receiveName }
										<br />
										地址:${sendInfoMap.receiveAddress }
										<br />
										邮编:${sendInfoMap.receivePostCode }
										<br />
										电话:${sendInfoMap.receivePhone}&nbsp;&nbsp;&nbsp;${sendInfoMap.receiveCellPhone
										}
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" bgcolor="#ffffff">
							<table cellspacing="1" cellpadding="2" bgcolor="#e1e1e1"
								border="0" width="100%">
								<tr>
									<td align="left" colspan="3" bgcolor="#ffffff">
										送货与付款方式:
										<input type="button" value="修改" onclick="goModifyPayAndSend()" />
									</td>
								</tr>
								<tr>
									<td align="left" bgcolor="#ffffff">
										送货方式:${sendInfoMap.sendModeName }
										<br />
										付款方式:${sendInfoMap.payTypeName }
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="right" colspan="2" bgcolor="#ffffff">
							<input type="hidden" name="OrderId" value="${OrderId }" />
							<input type="button" value="订单确认" onclick="doConfirm()" />
						</td>
					</tr>
				</table>
			</center>
		</form>
		<jsp:include flush="true" page="bottom.jsp" />
	</body>
</html>
<script type="text/javascript">
   	<logic:present name="xgResult">
   	    alert("${xgResult}");
   	</logic:present>
</script>