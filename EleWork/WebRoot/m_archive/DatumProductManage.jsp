<!--
  m_storage/inStorage.jsp
  功能:入库处理-公用模板
  作者:qsy
  生成日期:2005-04-28
  修改日期:2005-04-28
-->
<%@include file="../main/common.jsp"%>
<%@page contentType="text/html; charset=GB2312" import="YzSystem.J_Archive.*,YzSystem.JMain.*" errorPage="../main/error.faces"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%FrmFunctionBase frmFunctionBase = (FrmProductManage) UtilWebTools.getValueBinding("frmProductManage");%>
<%@include file="../main/functionBase.jsp"%>