<%@page language="java" isErrorPage="true" pageEncoding="GB2312" contentType="text/html;charset=gb2312" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<APPLET CODE = "PrinterApplet.class" CODEBASE = "applets" ARCHIVE = "jasperreports-3.5.3-applet.jar,commons-logging-1.0.2.jar,commons-collections-2.1.jar" WIDTH = "300" HEIGHT = "40">
<PARAM NAME = "REPORT_URL" VALUE ="<ww:property value="search.path"/>" />
</APPLET>
</html>