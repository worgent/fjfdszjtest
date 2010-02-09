<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:directive.page import="com.qzgf.NetStore.pub.*"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
    <title>商品管理</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"/>
  

    <script type="text/javascript">
	function AddAction(object){
	   
		document.forms[0].action='../../productModify.do?status=add';
		document.forms[0].submit();
	}
	function delete(object){
	   
		document.forms[0].action='../../productModify.do?status=deletePic';
		document.forms[0].submit();
	}
	
    </script>
    
<script type="text/javascript">
function number()
{
var char = String.fromCharCode(event.keyCode)
var re = /[0-9]/g
event.returnValue = char.match(re) != null ? true : false
}

function setAction(action){

document.forms[0].action="../../product.do?GoQry="+action;

document.forms[0].submit();
}
</script>
  </head>
  
  <body background="<%=request.getContextPath()%>/images/bg.gif">
  <form action="../../product.do" method="post">
  <center>
        
    
         <table width="80%">
         <tr>
         <td  align="right">
          <a href="<%=request.getContextPath()%>/productModify.do?status=add&selTypeUrl=<%=request.getAttribute("selType") %>&page=<%=request.getAttribute("page") %>">添加</a>
         </td>
         </tr>
         </table>

    	<table class="tbl" width="80%"  cellspacing="0" cellpadding="0" >
    	   <tr><td colspan="5"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">商品查看与修改</td></tr>
    		<tr>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			序号
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			产品名称
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			加入时间
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			是否发布
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			操作
    			</td>
    			
    		
    		</tr>
    		<logic:present name="ppage" scope="request">
				<logic:present   name="ppage" property="resultList" scope="request">
				 <logic:iterate id="item" name="ppage" property="resultList" scope="request">
    				<tr>
    					<td class="main">
    					${item.productId}&nbsp;
    					</td>
    					<td class="main">
    					${item.productName}&nbsp;
    					</td>
    					<td class="main">
    					${item.displayDate}&nbsp;
    					</td>
    					<td class="main">
    					<logic:equal name="item" property="isRelease" value="true">发布</logic:equal>
    					<logic:equal name="item" property="isRelease" value="false">不发布</logic:equal>
    					&nbsp;
    					</td>
    					<td class="main">
    					<div class="txt_td_normal">
    					<a href="<%=request.getContextPath()%>/productModify.do?status=onlyShow&pId=${item.productId}&selTypeUrl=<%=request.getAttribute("selType") %>&page=<%=request.getAttribute("page") %>">修改</a>
    				    |<a onclick='return confirm("确定要删除当前记录吗?")' href="<%=request.getContextPath()%>/product.do?action=del&selTypeUrl=<%=request.getAttribute("selType") %>&pId=${item.productId}&sPic=${item.smallPath}&bPic=${item.bigPath}">删除</a>
    				    </div>
    					</td>
    				</tr>  
    	</logic:iterate>
    	</logic:present>
    	</logic:present>
    	</table>
    	
    	    	
   <table class="tbl" width="80%"  cellspacing="0" cellpadding="0"  >
	<tr>
	<td class="main" align="left">
								<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
								<a
									href="../../product.do?targetPage=1&selTypeUrl=<%=request.getAttribute("selType") %>"
									class="pagenav">首页</a>
								<%
								if (p.isHasPrevious()) {
								%>
								<a
									href="../../product.do?targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>&selTypeUrl=<%=request.getAttribute("selType") %>"
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
									href="../../product.do?targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>&selTypeUrl=<%=request.getAttribute("selType") %>"
									class="pagenav">下一页</a>
								<%
								} else {
								%>
								下一页
								<%
								}
								%>
								<a
									href="../../product.do?targetPage=<%=p.getTotalPages()%>&selTypeUrl=<%=request.getAttribute("selType") %>"
									class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页 &nbsp;&nbsp;共<bean:write name="ppage" property="totalRecords"/>条记录
							</td>
    
    <td align="right" class="main">
    转<input type="text" name="page" class="inp_page" value="" size="2"  onkeypress="number()"/>页 
    <input type="button" value="Go" onclick="setAction('<%=request.getAttribute("selType") %>')" class="button"/>
    </td>
	</tr>
	</table>
     <input type="hidden" id="totalPages" name="totalPages" value="<bean:write name="ppage" property="totalPages"/>"/>
     <input type="hidden" id="tt" name="tt" value="<%=request.getAttribute("t") %>"/>
     <input type="hidden" id="selTypeGo" name="selTypeGo" value="<%=request.getAttribute("selType") %>"/>
    
    <br/>
    <table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
    	<tr>
    	<td colspan="3" bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">查询某一产品</td>
    	</tr>
    	<tr>
    	<td class="main" colspan="3" align="center">
    	<input name="searchContent" type="text"  value="输入查询关键字... " 
    	onfocus="if(this.value==this.defaultValue){this.value='';}" 
    	onblur="if(this.value==''){this.value=this.defaultValue;}" 
    	onKeyDown="if (event.keyCode==13)return PageBar.submitSearchForm();"/>
    	
    	<select name="selType">
    	<option value="1">按商品名称</option>
    	<option value="2">按商品说明</option>
    	<option value="3">按商品序号</option>
    	<option value="4">全部商品</option>
    	<option value="5">按新品</option>
    	<option value="6">按特价</option>
    	<option value="7">按推荐</option>
    	</select>
    	<input type="submit" name="sel" value="查 询"  class="button"/>
    	</td>
    	</tr>
    	</table>

    	<input type="hidden" name="selss" value="查 询"  onclick="delete(this)" class="button"/>
    </center>
    </form>
  </body>
</html:html>
