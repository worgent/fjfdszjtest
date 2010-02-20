
<!--
  m_sys/logManage.jsp
  功能:日志管理-公用模板
  作者:qsy
  生成日期:2005-04-28
  修改日期:2005-04-28
-->
<%@include file="../main/common.jsp"%>
<%@page contentType="text/html; charset=GB2312" import="YzSystem.J_System.*,YzSystem.JMain.*"  errorPage="../main/error.faces"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	FrmFunctionBase frmFunctionBase = (FrmRptManage) UtilWebTools.getValueBinding("frmRptManage");
%>
<%@include file="../main/functionBase.jsp"%>
