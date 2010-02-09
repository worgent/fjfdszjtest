<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>目录</title>
   
  </head>
  
  <body>
    <div align="center">
        <a href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/register.jsp">用户注册</a>
        <hr>
    	<h2>订单管理</h2>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listPayType" >订单查询</a><br>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus&orderStatus=2">
    	等待付款订单</a><br>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus&orderStatus=3">
    	正在配货订单</a><br>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus&orderStatus=4">
    	等待发货订单</a><br>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus&orderStatus=5">
    	已经发货订单</a><br>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus&orderStatus=6">
    	已经收货订单</a><br>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/order.do?status=listOrderByStatus&orderStatus=7">
    	已取消订单</a><br>
    	<hr>
    	<h2>我的账户</h2>
    	<a href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">订单信息</a>
    	
    	<hr>
    	<h2>维护</h2>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/sendMode.do?status=querySendMode">配送方式</a><br>
    	<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/payType.do?status=queryPayType">支付方式</a>
    	<hr>
        <h2>首页新闻管理</h2>
        <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=queryGroupByApplicationType&applicationType=1">新闻类别</a>
        <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do?status=queryAllNews">首页新闻管理</a>
        <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/addNews.jsp">添加新闻</a>
        <h2>注册条约管理</h2>
        <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/treaty.do?status=queryTreaty">注册条约维护</a>
        <h2>生产厂商管理</h2>
        <a href="http://localhost:8080/NetStore/JspForm/BackfuncModual/manufacturer.do?status=queryAllManufacturers">生产厂商管理</a>
        <h2>管理人员</h2>
        <a href="http://localhost:8080/NetStore/JspForm/BackfuncModual/manager.do?status=queryManagers">管理员列表</a>
        <a></a>
        <h2>公告管理</h2>
        <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/bulletin.do?status=queryBulletin">公告管理</a>
    </div>
  </body>
</html>
