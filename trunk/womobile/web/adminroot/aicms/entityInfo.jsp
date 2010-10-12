<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*,util.HibernateUtil,HibernateBeans.cms.entity" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
int eid=Integer.parseInt(request.getParameter("id"));
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
entity ent=(entity)s.load(entity.class,new Integer(eid));
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据查看</title>
<link href="../styles/table.css" rel="stylesheet" type="text/css" />
<style>
tr.cgird {
 background-color: expression(
  this.sectionRowIndex%2==0?"#FFF0F0":"#F0F0FF"
 );
 }
</style>
</head>

<body>
<table width="100%" border="1" cellspacing="5" cellpadding="0">
  <tr class="title">
    <td colspan="2">数据查看</td>
  </tr>
<%
Map map=ent.getAllAttributeValues(); 
Iterator it=map.entrySet().iterator();
while(it.hasNext()){
    Map.Entry entry=(Map.Entry)it.next();
    Object key=entry.getKey();
    Object value=entry.getValue();
%>
  <tr class="cgird">
    <td width="9%"><strong><%=key.toString()%>：</strong></td>
    <td width="91%"><%=value.toString()%></td>
  </tr>
<%}%>
  <tr>
    <td colspan="2" align="center"><input type="submit" name="button" id="button" value="返回上一页" onclick="javascript:window.history.back()" /></td>
  </tr>
</table>
</body>
</html>
