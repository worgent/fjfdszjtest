<!--
config.jsp
功能:配置文件
作者:qsy
生成日期:2005-04-28
修改日期:2005-04-28
--------------------------
修改日期:2008-05-28 szj
-->
<%@page contentType="text/html; charset=GB2312" %>
<%
	/* 全局session 包含内容如下，（其它用途必须及时清除）:
	 *----------------------------------------------------------------------------
	 * SystemURL        web系统URL
	 * UseRemoteEJB     是否使用远程EJB(EJB服务器和web服务器不在同一台机器)
	 * EJBURL           使用远程EJB时候，必须指定此URL
	 * utilEJB2Local    EJB调用到本地调用的接口对象
	 * context          使用的EJB naming空间上下文
	 * userTrades       用户有权限的树形交易列表
	 * userInfo         用户信息
	 */
	String SystemURL = "http://" + request.getHeader("host")
			+ "/EleWork/";
	String SystemTitle = "冠发电子工单系统";
	String RowCount = "10";
	String DetailEditRow = "3";
%>

