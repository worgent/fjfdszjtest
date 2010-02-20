<%
/**
* 演示文件上传的处理
* @author <a href=“mailto:winter.lau@163.com”>Winter Lau</a>
* @version $Id: save.jsp,v 1.00 2003/03/01 10:10:15
*/
%>
<%@page language="java" contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<html>
<head>
<title>保存上传文件</title>
<link rel="stylesheet" href="../css/mm.css" type="text/css">
</head>
<%
String msg = "";
DiskFileUpload fu = new DiskFileUpload();
// 设置允许用户上传文件大小,单位:字节
fu.setSizeMax(10000000);
// maximum size that will be stored in memory?
//开始读取上传信息
List fileItems = fu.parseRequest(request);
%>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<font size="6">文件列表：</font>
<center>
<table class="btd" cellpadding=0 cellspacing=1 border=1 width="100%">
<tr class="btd1">
<td class="btd">文件名</td>
<td class="btd">大小</td>
</tr>
<%
// 依次处理每个上传的文件
Iterator iter = fileItems.iterator();
while (iter.hasNext()) {
  FileItem item = (FileItem) iter.next();
  //忽略其他不是文件域的所有表单信息
  if (!item.isFormField()) {
   String name = item.getName();
   long size = item.getSize();
   if((name==null||name.equals("")) && size==0)
   continue;
%>
<tr class="btd1">
<td class="btd2"><%=item.getName()%></td>
<td class="btd2"><%=item.getSize()%></td>
</tr>
<%
   //保存上传的文件到指定的目录
   name=name.substring(name.lastIndexOf('\\')+1);
   name = name.replace(':','_');
   name = name.replace('\\','_');

   File f=new File("EleWork"+File.separator+"rpt"+File.separator+name);
   item.write(f);
  }
}
%>
</table>

<br/><br/>
<a href="rptManage.faces?tseqn=001999020">返回报表管理页面</a>
</center>
</body>
</html>
