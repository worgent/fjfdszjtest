<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.*,java.util.List,util.HibernateUtil,java.util.*" errorPage="" %>
<%
request.setCharacterEncoding("utf-8");
String pidStr=request.getParameter("cid");
if(pidStr==null||pidStr.equals("")){out.println("Error!");out.close();return;}
int pid=Integer.parseInt(pidStr);

addEntity addEn=new addEntity();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmscs=(cmsClass)s.load(cmsClass.class,new Integer(pid));
String pname=cmscs.getName();
List attributes=cmscs.getNewdataViewAttributes();
//s.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../styles/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/FCKeditor/fckeditor.js"></script>
<title>New Entity (step 2)</title>
</head>
<body>
<form id="form1" name="form1" method="post" action="addEntity.jsp">
 <input type="hidden" id="cid" name="cid" value="<%=pid%>"/> 
  <table width="100%" border="1" cellspacing="0" cellpadding="0">
    <tr class="title">
      <td colspan="2" align="center">添加<%=pname%></td>
    </tr>
    <tr>
      <td width="9%" height="30">类别：</td>
      <td width="91%" height="30"><%=pname%></td>
    </tr>
<%
for(int i=0;i<attributes.size();i++){
    attribute attri=(attribute)attributes.get(i);
%>
    <tr>
      <td height="30"><%=attri.getName()%>：</td>
      <td height="30"><%=addEn.getFormHTML(attri)%></td>
    </tr>
<%
}
%>
    <tr>
      <td height="30">&nbsp;</td>
      <td height="30"><input type="button" name="button1" id="button1" value="上一步" onclick="javascript:window.history.back()" />
        &nbsp;&nbsp;
        <input type="submit" name="button" id="button" value="完成" /></td>
    </tr>
  </table>
</form>
</body>
</html>
