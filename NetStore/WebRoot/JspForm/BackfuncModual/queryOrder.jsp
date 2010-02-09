<%@ page language="java" pageEncoding="gbk"%>
<jsp:directive.page import="com.qzgf.NetStore.pub.*" />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
    <title>订单查询</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css" />
	<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/jslib/setday.js"></SCRIPT>
    <script type="text/javascript">
         function back(){
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/AdminIndex.jsp";
        }
        function setAction(){
            var page=document.getElementById("page").value;
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=queryOrders&page="+page;
        }
    </script>
  </head>
  
  <body background="<%=request.getContextPath()%>/images/bg.gif">
  <center>
      <form action="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do" method="get">
      <table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
          <tr>
              <td colspan="2"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">  
                  订单查询
              </td>
          </tr>
          <tr>
              <td class="main" align="right" width="50%">
                订单号
              </td>
              <td class="main" align="left">
                  <input type="text" name="orderId" id="orderId" value="${queryInfo.orderId }"/>
              </td>
          </tr>
          <tr>
              <td class="main" align="right">
                  订单状态
              </td> 
              <td class="main" align="left">
                  <select id="orderStatus" name="orderStatus" style="width:150px">
                      <option value="0" >--请选择--</option>
                      <option value="2" >等待付款</option>
                      <option value="3" >正在配货</option>
                      <option value="4" >等待发货</option>
                      <option value="5" >已经发货</option>
                      <option value="6" >已经收货</option>
                      <option value="7" >已取消定单</option>
                  </select>
              </td>
          </tr>
          <tr>
              <td class="main" align="right">
                  用户帐号
              </td>
              <td class="main" align="left">
                <input type="text" name="userId" value="${queryInfo.userId}"/>
              </td>
          </tr>
          <tr>
              <td class="main" align="right">
                 总金额大于等于
              </td>
              <td class="main" align="left">
                <input type="text" name="allSum" value="${queryInfo.allSum }"/>元
              </td>
          </tr>
          <tr>
              <td class="main" align="right">
                 支付方式
              </td>
              <td class="main" align="left">
                  <select name="payTypeId" style="width:150px">
                      <option value="0">--请选择--</option>
                      <logic:present name="listPayType">
                         <logic:iterate id="item" name="listPayType">
                            <option value="${item.PayTypeId }" ${queryInfo.payTypeId==item.PayTypeId?"selected":'' }>${item.PayTypeName }</option>
                         </logic:iterate> 
                      </logic:present>
                  </select> 
              </td>
           </tr>
           <tr>
              <td class="main" align="right" rowspan="2">
                 下单日期范围
              </td>
              <td class="main" align="left">
                  开始日期(含)<input type="text" name="beginDay" onclick="setday(this)" size="8" value="${queryInfo.beginDay}"/><br>
                  结束日期(含)<input type="text" name="endDay" onclick="setday(this)" size="8" value="${queryInfo.endDay}"/>
              </td>
                 
           </tr>
           <tr>
          </tr>
          <tr>
              <td colspan="2" class="main" align="center">
                  <input type="hidden" name="status" value="queryOrders" />
                  <input type="submit" value="查询" class="button" /> <input type="reset" value="重置" class="button" />
              </td>
          </tr>
      </table>
      </form>
  	<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
			
    		<tbody>
    		<logic:present name="ppage" scope="request"> 
			<logic:present name="ppage" property="resultList" scope="request">
				
    		    <thead>
    		<tr>
    		    <td colspan="10"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
    		        订单列表
    		    </td>
    		</tr>
    		<tr>
    			<th>订单号</th><th>用户</th><th>总金额</th><th>配送费</th>
    			<th>订单状态</th><th>支付方式</th><th>配送方式</th><th>支付状态</th>
    			<th>下单日期</th><th>&nbsp;&nbsp;&nbsp;</th>
    		</tr>
    		</thead>
    			<logic:iterate id="item" name="ppage" property="resultList"
					scope="request">
    				<tr>
    					<td class="main">
    					${item.OrderId }
    					</td>
    					<td class="main">
    					${item.UserId }
    					</td>
    					<td class="main">
    					￥${item.AllSum }
    					</td>
    					<td class="main">
    					￥${item.SendFee }
    					</td>
    					<td class="main">
    					${item.OrderStatusName }
    					</td>
    					<td class="main">
    					${item.PayTypeName }
    					</td>
    					<td class="main">
    					${item.SendModeName }
    					</td>
    					<td class="main">
    					${item.PayStatus }
    					</td>
    					<td class="main">
    					${item.DownOrderTime }
    					</td >
    					<td class="main">
    					<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=queryAndLoadOrder&orderId=${item.OrderId}">载入</a>
    					</td> 
    				</tr>  
    			</logic:iterate>
			</logic:present>
		</logic:present>
		<logic:present name="ppage">
		<tr>
			<td class="main" align="left" colspan="8">
				<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=queryOrders&targetPage=1"
					class="pagenav">首页</a>
				<%
								if (p.isHasPrevious()) {
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=queryOrders&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
					class="pagenav">上一页</a>
				<%
								} else {
								%>
				上一页
				<%
									}
									if (p.isHasNext()) {
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=queryOrders&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
					class="pagenav">下一页</a>
				<%
								} else {
								%>
				下一页
				<%
								}
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=queryOrders&targetPage=<%=p.getTotalPages()%>"
					class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页
				&nbsp;&nbsp;共
				<bean:write name="ppage" property="totalRecords" />
				条记录

			</td>
			<td class="main" align="right" colspan="2">
				转
				<input type="text" name="page" class="inp_page" value="" size="4"
					onkeyup="value=value.replace(/[^\d]/g,'')" />
				页
				<input type="button" value="Go" onclick="setAction()" class="button" />
				<input type="hidden" id="totalPages" name="totalPages"
					value="<bean:write name="ppage" property="totalPages"/>" />
			</td>
		</tr>
		<tr>
    		    <td class="main" colspan="10"> 
    		        &nbsp;&nbsp;<input type="button" value="返回" class="button" onclick="back()"/>
    		    </td>
    		</tr>
		</logic:present>
    		</tbody>
    		
    	</table>
  </center>
  </body>
</html:html>
