<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
    <title>操作历史</title> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css" />
  </head>
  
  <body background="<%=request.getContextPath()%>/images/bg.gif">
  <center>
  	    <table width="80%" class="tbl">
  	        <tr>
  	            <td align="left" class="main">
  	             订单号:${orderId }
  	            </td>
  	        </tr>
  	     <tr>
  	     <td>
    	<table class="tbl" width="100%"  cellspacing="0" cellpadding="0">

			<thead>
    		<tr>
    			<th align="center">流水号</th><th align="center">发生时间</th><th align="center">订单状态</th><th align="center">操作对象</th>
    			<th align="center">操作者用户名</th><th align="center">注释</th>
    		</tr>
    		</thead>
    		<tbody>
    		<logic:present name="operateHistoryList">
    			<logic:iterate id="item" name="operateHistoryList">
    				<tr>
    					<td align="center" class="main">
    					${item.sequenceNum }
    					</td>
    					<td align="center" class="main">
    					${item.occurTime }
    					</td>
    					<td align="center" class="main">
    					${item.orderStatusName}
    					</td>
    					<td align="center" class="main">
    					${item.operateObject }
    					</td>
    					<td align="center" class="main">
    					${item.operatePerson }
    					</td>
    					<td align="center" class="main">
    					${item.remark }
    					</td>
    				</tr>  
    			</logic:iterate>
    		</logic:present>
    		</tbody>
    		<tr>
    		    <td colspan="6" align="left" class="main">
    		        &nbsp;&nbsp;<input type="button" value="返回" onClick="history.back();" class="button"/>
    		    </td>
    		</tr>
    	</table> 
    	   
    	</td>
    	</tr>
        </table>
         </center>
  </body>
</html:html>
