<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:directive.page import="com.qzgf.NetStore.dao.IIndexFirstDAO"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="com.qzgf.NetStore.dao.impl.IndexFirstDAO"/>


<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>网页底部</title>
</head>
<body>
<center>
	
  <table width="98%" height="100" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td><table width="100%" cellpadding="0" cellspacing="1" bgcolor="#F1F1F1">
        <tr bgcolor="#f1f1f1">
          <td align="middle" width="20%" height="22"><strong>关于我们</strong></td>
          <td align="middle" width="20%" height="22"><strong>购物指南</strong></td>
          <td align="middle" width="20%" height="22"><strong>服务保证</strong></td>
          <td align="middle" width="20%" height="22"><strong>客户服务</strong></td>
          <td align="middle" width="20%" height="22"><strong>其它说明</strong></td>
        </tr>
        <tr bgcolor="#ffffff">
          <td align="middle" height="23">关于我们</td>
          <td align="middle" height="23">新手上路</td>
          <td align="middle" height="23">隐私保护</td>
          <td align="middle" height="23">会员中心</td>
          <td align="middle" height="23">缺货登记</td>
        </tr>
        <tr bgcolor="#ffffff">
          <td align="middle" height="23">网站动态</td>
          <td align="middle" height="23">定购方式</td>
          <td align="middle" height="23">服务保证</td>
          <td align="middle" height="23">订单查询</td>
          <td align="middle" height="23">投诉建议</td>
        </tr>
        <tr bgcolor="#ffffff">
          <td align="middle" height="23"><a href="page.aspid=13"></a>常见问题<a href="page.aspid=13"></a></td>
          <td align="middle" height="23">如何付款</td>
          <td align="middle" height="23">售后服务</td>
          <td align="middle" height="23">汇款确认</td>
          <td align="middle" height="23"><a href="<%=request.getContextPath()%>/JspForm/login.jsp">管理登录</a></td>
        </tr>
      </table></td>
    </tr>
  </table>
  <table width="950" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td height="1" colspan="2" bgcolor="#CCCCCC"></td>
    </tr>
    <tr>
      <td width="300" height="80">LOGO</td>
      <td valign="middle"><table width="100%" border="0" cellpadding="2" cellspacing="0">
        <tr>
          <td><div align="left">商城首页  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;关于我们  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;在线支付  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;版权声明  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;付款方式  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;意见反馈  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;在线帮助&nbsp;<a href="<%=request.getContextPath() %>/JspForm/login.jsp">管理员登录</a></div></td>
        </tr>
        <tr>
          <td><p align="left">公司地址：</p>            </td>
        </tr>
        <tr>
          <td><div align="left">客服信箱：</div></td>
        </tr>
        <tr>
          <td><div align="left">Copyright &copy; 2008</div></td>
        </tr>
      </table></td>
    </tr>
  </table>
</center>
</body>
</html>