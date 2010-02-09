<%@ page language="java"  pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
    <title>订单明细</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css" />
    <script type="text/javascript">
    	function check(orderId,nextOrderStatus){
    	    var orderStatus=nextOrderStatus-1;
    	    var info;
    	    if(orderStatus==2){
    	       info="您确定该定单已全部付款了吗?";
    	       toConfirm(orderId,nextOrderStatus,info);
    	    }
    	    else if(orderStatus==3){
    	       info="您确定已完成配货了吗?";
    	       toConfirm(orderId,nextOrderStatus,info);
    	    }
    	    else if(orderStatus==4){
    	       info="您确定该订单已发货了吗?";
    	       toConfirm(orderId,nextOrderStatus,info);
    	    }
    	    else if(orderStatus==5){
    	       info="您确定买家已收到货物了吗?";
    	       toConfirm(orderId,nextOrderStatus,info);
    	    }
    	}
    	
    	function toConfirm(orderId,nextOrderStatus,info){
    	  if(confirm(info)){
    	  var url="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=changeOrderStatus&orderId="+
    	      orderId+"&nextOrderStatus="+nextOrderStatus;
		      window.location.href=url;
		  }
		  else 
		    return false;
    	}
    	
    	function toOperateHistory(orderId){
    	    var url="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=queryOperateHistory&orderId="+orderId;
    	    window.location.href=url;
    	}
    </script>
  </head>
  
  <body background="<%=request.getContextPath()%>/images/bg.gif"> 
  <center>
    	<table width="80%" class="tbl">
		   	<tr>
			    <td align="left" width="40%" class="main">订单号:${mapAllUserInfo.orderId }<font color="red">(${mapAllUserInfo.orderStatus })</font></td>
			    <td align="right" width="60%" class="main">订购时间:${mapAllUserInfo.downOrderTime }</td>   
			</tr>
			<tr>
			    <td colspan="3" >
			        <table class="tbl" width="100%"  cellspacing="0" cellpadding="0" >
			            <tr>
			                <td colspan="4" class="main"  align="left" >订购者信息</td><td class="main"  align="center">支付方式</td><td class="main"  align="left" >${mapAllUserInfo.payTypeName}&nbsp;
			            </tr>
			            <tr>
			                <td class="main"  align="center">姓名</td><td class="main" align="left">${mapAllUserInfo.sendUserName }&nbsp;</td><td class="main"  align="center">联系电话</td>
			                <td align="left" class="main" >${mapAllUserInfo.sendPhone }&nbsp;</td><td class="main" align="center">联系手机</td>
			                <td align="left" class="main"  >${mapAllUserInfo.sendCellPhone }&nbsp;</td>
			            </tr>
			            <tr>
			                <td align="center" class="main" >地址</td><td colspan="3" class="main"  align="left">${mapAllUserInfo.sendAddress }&nbsp;</td><td class="main"  align="center">邮编</td><td class="main" align="left">${mapAllUserInfo.sendPostCode }</td>
			            </tr>
			            <tr>
			                <td align="center" class="main" >其他要求</td><td colspan="5" class="main" align="left" >鞋子要40码的,颜色白色等等</td>
			            </tr>
			            <tr>
			                <td colspan="4" class="main"  align="left">收货人信息</td><td align="center" class="main" >送货方式</td><td class="main" align="left">${mapAllUserInfo.sendModeName}</td>
			            </tr>
			            <tr>
			                <td align="center" class="main" >姓名</td><td align="left" class="main" >${mapAllUserInfo.receiveName }&nbsp;</td><td align="center" class="main" >联系电话</td>
			                <td align="left" class="main" >${mapAllUserInfo.receivePhone }&nbsp;</td><td align="center" class="main" >联系手机</td>
			                <td align="left" class="main" >${mapAllUserInfo.receiveCellPhone }&nbsp;</td>   
			            </tr>
			            <tr>
			                <td align="center" class="main" >地址</td><td colspan="3" class="main" align="left">${mapAllUserInfo.receiveAddress }&nbsp;</td><td align="center" class="main" >邮编</td>
			                <td class="main" align="left">${mapAllUserInfo.receivePostCode }&nbsp;</td>
			            </tr>
			            <tr>
			                <td align="center" class="main" >时间要求</td><td colspan="5" class="main" align="left">只双休日、假日送货</td>
			            </tr>
			            <tr>
			                <td colspan="4" class="main" >订购的商品</td><td align="center" class="main" >发票</td><td class="main" >${mapAllUserInfo.ifInvoice }&nbsp;</td>
			            </tr>
			            <tr>
			                <th align="center" class="main" >商品编号</th><th colspan="3" class="main"  align="center">商品名称</th><th align="center" class="main" >单价</th><th align="center" class="main" >数量</th>
			            </tr>
			            <logic:present name="listOrderDetail">
		                    <logic:iterate id="item" name="listOrderDetail">
		                         <tr>
			                        <td align="center" class="main">${item.productId }&nbsp;</td><td colspan="3" class="main" align="center">${item.productName }&nbsp;</td>
			                        <td align="center" class="main">￥${item.memberPrice }/${item.unitName}&nbsp;</td><td align="center" class="main" align="center" >${item.quantity}&nbsp;</td>
			                     </tr>
		                    </logic:iterate>
		                </logic:present>
		                <tr>
		                    <td colspan="6" class="main" align="right">合计:￥${mapAllUserInfo.allSum }元</td>
		                </tr>
			        </table>
			    </td>
			</tr>
			<tr>
			    <td colspan="7" nowrap="nowrap" class="main" align="left">
			        <logic:equal value="1" name="orderStatus" >
			            <input type="button" value="审核通过" class="button0000"  />&nbsp;&nbsp;
			        </logic:equal>
			        <logic:equal value="2" name="orderStatus">
			            <input type="button" value="财务确认已付款" class="button0000" onclick="check('${mapAllUserInfo.orderId }','3' )"/>&nbsp;&nbsp;
			            <input type="button" value="打印订单"  class="button0000"/>&nbsp;&nbsp;
			        </logic:equal>
			        <logic:equal value="3" name="orderStatus">
			            <input type="button" value="完成配货" class="button" onclick="check('${mapAllUserInfo.orderId }','4' )"/>&nbsp;&nbsp;
			            <input type="button" value="打印订单" class="button0000"/>&nbsp;&nbsp;
			        </logic:equal>
			        <logic:equal value="4" name="orderStatus">
			            <input type="button" value="确认已发货"  class="button0000" onclick="check('${mapAllUserInfo.orderId }','5' )"/>&nbsp;&nbsp;
			            <input type="button" value="打印配送单" class="button0000"/>&nbsp;&nbsp;
			        </logic:equal>
			        <logic:equal value="5" name="orderStatus">
			            <input type="button" value="确认用户已收货" class="button0000" onclick="check('${mapAllUserInfo.orderId }','6' )"/>&nbsp;&nbsp;
			        </logic:equal>
			        <input type="button" value="操作历史" onclick="toOperateHistory('${mapAllUserInfo.orderId}')" class="button"/>
                        &nbsp;&nbsp;
			        <input type="button" value="返回" onClick="history.back();" class="button"/>
			       
			    </td>
			</tr>
    	</table>
    </center>
  </body>
</html:html>
