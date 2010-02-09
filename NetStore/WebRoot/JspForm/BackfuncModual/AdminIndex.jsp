<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>目录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  <form action="/NetStore/uploadTest.do"  enctype="multipart/form-data">
    <div align="center">
    	<h2>订单管理</h2>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus">待审核订单</a>
    	<br>
    	<a href="<%=request.getContextPath()%>/product.do">商品管理</a>
    	<br>
    	<a href="<%=request.getContextPath()%>/unit.do?status=select">单位设置</a>
    	<br>
    	<a href="<%=request.getContextPath()%>/specification.do?status=select">品牌设置</a>
    	<br>
    	<a href="<%=request.getContextPath()%>/productManage.do?status=select">商品大类管理</a>
    	<br>
    	<a href="<%=request.getContextPath()%>/productManage.do?status=selectChild">商品小类管理</a>
    	<br/>
    	<a href="<%=request.getContextPath()%>/review.do?status=select">商品评论管理</a>
    	<hr>
    	用户管理
    	<hr>
        <a href="<%=request.getContextPath()%>/user.do?status=select">商城会员管理</a>	
        <hr>
        <a href="<%=request.getContextPath()%>/friendLink.do?status=exec">合作伙伴管理</a>	
        
    </div>
    
    
    
    
    
   <table>
<tr>
<td>
 导入文件: 
			
			<input type="file" name="wjm" />
			
			<input type="submit" value="确 认" onclick="setAction('upload')" />
			
			<input type="button" value="返 回" onclick="setAction('cancel')" />
			<%=request.getAttribute("errmsg") %>
</td>
</tr>
</table>

</form>
  </body>
</html>
