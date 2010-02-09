<%@ page language="java" %>
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>我的账户</title>
		<link href="<%=request.getContextPath()%>/css/header01.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/index.css"
			type="text/css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/css/catalog.css"
			type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
		<script type="text/javascript">
	        function pay(orderId,allSum){
				
				  var TransDate="";       // 声明变量。
				  var d = new Date();    
				   TransDate+=d.getYear();
				   TransDate+=d.getMonth()+1;
				  var day1=""+d.getDate(); 
				  if(day1.length==1){
				      day1="0"+day1;
				  }     
				  TransDate+=day1;                 // 创建 Date 对象。
				alert(TransDate);
				var TransAmt = allSum * 100;
				alert(TransAmt);
				TransAmt=""+TransAmt;
				alert(TransAmt.length);
				var len = 12 - TransAmt.length;
				for (var i = 0; i < len; i++) {
					TransAmt = "0" + TransAmt;
				}
			    var MerId=document.getElementById("MerId").value;
			    alert(MerId);
	            var OrderId=orderId;
	            var CuryId=document.getElementById("CuryId").value;
	            var TransType=document.getElementById("TransType").value;
	            var Version="20040916";
	            var BgRetUrl="http://www.eeu8.com/pay.do?status=test";
	            var PageRetUrl="localhost:8088/pay.do";
	            var ChkValue="";
	            alert("111");
	            alert("status=getChkValue&MerId="+MerId+"&OrderId="+OrderId+"&TransAmt="+TransAmt+"&CuryId="+CuryId+
					     "&TransDate="+TransDate+"&TransType="+TransType);
			  
	            $.ajax({
					 type: "POST",
					 url: "<%=request.getContextPath()%>/JspForm/FrontfuncModual/cart.do",
					 async:false,
					 data:"status=getChkValue&MerId="+MerId+"&OrderId="+OrderId+"&TransAmt="+TransAmt+"&CuryId="+CuryId+
					     "&TransDate="+TransDate+"&TransType="+TransType,
					 success: function(msg){ 
					     ChkValue=msg; 
					 } 
				}); 
				alert("abc");
	            alert("ChkValue:"+ChkValue);
	            var GateId="";
	            var Priv1="Memo";
                alert("该功能未实现");
                document.forms[0].action="https://payment.chinapay.com/pay/TransGet?MerId="+MerId+"&TransAmt="+TransAmt+"&OrdId="+OrderId+"&CuryId="+CuryId+
                    "&TransDate="+TransDate+"&TransType="+TransType+"&Version="+Version+"&BgRetUrl="+BgRetUrl+"&PageRetUrl="+PageRetUrl+
                    "&ChkValue="+ChkValue+"&GateId="+GateId+"&Priv1=Memo";
                alert("https://payment.chinapay.com/pay/TransGet?MerId="+MerId+"&TransAmt="+TransAmt+"&OrdId="+OrderId+"&CuryId="+CuryId+
                    "&TransDate="+TransDate+"&TransType="+TransType+"&Version="+Version+"&BgRetUrl="+BgRetUrl+"&PageRetUrl="+PageRetUrl+
                    "&ChkValue="+ChkValue+"&GateId="+GateId+"&Priv1=Memo");
                document.forms[0].method="post";
                document.forms[0].submit();
            }
	    </script>
	</head>
	<body>
		<center>
			<jsp:include page="top.jsp" />
			<form action="" method="post">
			<table width="98%">
				<tr>
					<td align="left">
						我的账户
					</td>
				</tr>
				<tr>
					<td width="100%">
						<table width="100%">
							<tr>
								<td align="left">
									订单信息
								</td>
							</tr>
							<tr>
								<td width="100%">
									<table width="100%" align="center" cellspacing="1"
										cellpadding="2" bgcolor="#e1e1e1" border="0">
										<tr>
											<td colspan="2" align="left" bgcolor="#ffffff">
												<ul>
													<li>
														<a
															href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition&condition=1">查看一个月内下的定单</a>
													</li>
													<li>
														<a
															href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">查看所有的定单</a>
													</li>
												</ul>
											</td>
											<td colspan="2" align="left" bgcolor="#ffffff">
												<ul>
													<li>
														<a
															href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition&condition=2">查看已发货的定单</a>
													</li>
													<li>
														<a
															href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition&condition=3">查看已取消的定单</a>
													</li>
												</ul>
											</td>
											<td colspan="5" bgcolor="#ffffff">
												&nbsp;
											</td>
										</tr>
										<logic:present name="orderList">
											<tr>
												<th align="center" bgcolor="#ffffff">
													订单号
												</th>
												<th align="center" bgcolor="#ffffff">
													总金额
												</th>
												<th align="center" bgcolor="#ffffff">
													配送费
												</th>
												<th align="center" bgcolor="#ffffff">
													订单状态
												</th>
												<th align="center" bgcolor="#ffffff">
													配送方式
												</th>
												<th align="center" bgcolor="#ffffff">
													付款状态
												</th>
												<th align="center" bgcolor="#ffffff">
													下单日期
												</th>
												<th bgcolor="#ffffff">
													&nbsp;&nbsp;&nbsp;
												</th>
											</tr>
											<logic:iterate id="item" name="orderList">
												<tr>
													<td align="center" bgcolor="#ffffff">
														${item.OrderId }
													</td>
													<td align="center" bgcolor="#ffffff">
														￥${item.AllSum }
													</td>
													<td align="center" bgcolor="#ffffff">
														￥${item.SendFee }
													</td>
													<td align="center" bgcolor="#ffffff">
														${item.OrderStatusName }
													</td>
													<td align="center" bgcolor="#ffffff">
														${item.SendModeName }
													</td>
													<td align="center" bgcolor="#ffffff">
														<logic:equal value="2" name="item"
															property="payStatusValue">${item.PayStatus }</logic:equal>
														<logic:notEqual value="2" name="item"
															property="payStatusValue">
															<input type="button" value="立即支付" onclick="pay(${item.OrderId },${item.AllSum})" />
														</logic:notEqual>
													</td>

													<td align="center" bgcolor="#ffffff">
														${item.DownOrderTime }
													</td>
													<td align="center" bgcolor="#ffffff">
														<a
															href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=getOrderDetails&orderId=${item.OrderId}">查看订单</a>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>

									</table>
									<input type="hidden" name="MerId" value="${MerId }" />
									<input type="hidden" name="OrdId" value="${OrdId }" />
									<input type="hidden" name="TransAmt" value="${TransAmt}" />
									<input type="hidden" name="CuryId" value="156" />
									<input type="hidden" name="TransDate" value="${TransDate }" />
									<%
									    
									 %>
									<input type="hidden" name="TransType" value="0001" />
									<input type="hidden" name="Version" value="20040916" />
									<input type="hidden" name="ChkValue" value="${ChkValue }" />
									<input type="hidden" name="Priv1" value="Memo" />
								</td>
							</tr>
						</table>
						<table width="100%" align="center" cellspacing="1" cellpadding="2"
							bgcolor="#e1e1e1" border="0">
							<tr>
								<td bgcolor="#ffffff" align="left">
									账户设置
								</td>
							</tr>
							<tr>
								<td bgcolor="#ffffff">
									<table width="100%">
										<tr>
											<td bgcolor="#ffffff" align="left">
												个人信息
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%">
													<tr>
														<td colspan="2" align="left">
															<ul>
																<li>
																	<a
																		href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryMyInfo">修改我的联系信息</a>
																</li>
																<li>
																	<a
																		href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=to_modifyPwd">修改我的密码</a>
																</li>
															</ul>
														</td>
														<td colspan="2" align="left" valign="top">
															<ul>
																<li>
																	<a
																		href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryUserEmail">修改我的Email地址</a>
																</li>
															</ul>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</form>
			<jsp:include flush="true" page="bottom.jsp" />
		</center>
	</body>
</html>