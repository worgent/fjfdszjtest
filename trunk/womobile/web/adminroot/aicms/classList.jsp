<%@ page contentType="text/html; charset=utf-8" language="java" import="util.HibernateUtil,HibernateBeans.cms.cmsClass,java.util.List" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AICMS类别管理</title>
<link rel="StyleSheet" href="dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="dtree/dtree.js"></script>
</head>

<body>
<div class="dtree">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#F0F0F0">
    <td width="53%" bgcolor="#F0F0F0"><a href="newClass_step1.jsp">【添加新类别】</a></td>
    <td width="47%" align="right"><span style="text-align:right; height:15px; background-color:#F0F0F0; vertical-align:middle"><a href="javascript:d.openAll();"><img src="dtree/img/icon-expandall.gif" border="0" /></a>-<a href="javascript:d.closeAll();"><img src="dtree/img/icon-closeall.gif" border="0" /></a></span></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">&nbsp;</td>
  </tr>
</table>
<script type="text/javascript">
<!--
d = new dTree('d');
//mytree.add(1, 0, 'My node', 'node.html', 'node title', 'mainframe', 'img/musicfolder.gif');
var ix=-1;
var rootId=0;
var pId=0;
d.add(0,-1,'分类目录');
<%
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List list=s.createQuery("from cmsClass order by pid ASC,sort ASC").list();
s.getTransaction().commit();
for(int i=0;i<list.size();i++)
{
    cmsClass cs=(cmsClass)list.get(i);
%>
    d.add(<%=cs.getId()%>,<%=cs.getPid()%>,'<%=cs.getName()%>&nbsp;<a href="modifyClass_step1.jsp?id=<%=cs.getId()%>" class="cont">修改</a>&nbsp;<a href="confirm.jsp?url=deleteClass.jsp?id=<%=cs.getId()%>&msg=即将删除类别" class="cont">删除</a>&nbsp;<a href="up.jsp?id=<%=cs.getId()%>" class="cont">升↑</a>&nbsp;<a href="down.jsp?id=<%=cs.getId()%>" class="cont">降↓</a>');
<%
}
%>
document.write(d);
//-->
</script>
</div>
</body>
</html>
