<!--
main/main.jsp
功能:主页面
作者:qsy
生成日期:2005-04-28
修改日期:2005-04-28
-->
<%@include file="../main/common.jsp"%>
<%@page contentType="text/html; charset=GB2312" import="YzSystem.J_Elework.*,YzSystem.JMain.*" errorPage="../main/error.faces"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%FrmFunctionBase frmFunctionBase = (FrmTel) UtilWebTools.getValueBinding("frmTel");%>
<%@include file="../main/functionBase.jsp"%>