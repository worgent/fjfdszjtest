<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,java.util.List,util.HibernateUtil" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>New Entity(step 1)</title>
<link rel="StyleSheet" href="dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="dtree/dtree.js"></script>
<link href="../styles/table.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="Entities.jsp" method="post">
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <tr class="title">
    <td colspan="2" align="center">数据查看(步骤 1: 请选择类别)</td>
  </tr>
  <tr class="second">
    <td height="30" colspan="2" align="left">请选择类别：</td>
    </tr>
  <tr>
    <td height="30" colspan="2" align="left"><div class="dtree">
  <script type="text/javascript">
<!--
d = new dTree('d');
//mytree.add(1, 0, 'My node', 'node.html', 'node title', 'mainframe', 'img/musicfolder.gif');
var ix=-1;
var rootId=0;
var pId=0;
d.add(0,-1,'请选择分类&nbsp;&nbsp;<span style="text-align:right; height:15px; background-color:#F0F0F0; vertical-align:middle"><a href="javascript:d.openAll();"><img src="dtree/img/icon-expandall.gif" border="0" /></a>-<a href="javascript:d.closeAll();"><img src="dtree/img/icon-closeall.gif" border="0" /></a></span>');
<%
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List list=s.createQuery("from cmsClass order by pid ASC,sort ASC").list();
s.getTransaction().commit();
for(int i=0;i<list.size();i++)
{
    cmsClass cs=(cmsClass)list.get(i);
%>
    d.add(<%=cs.getId()%>,<%=cs.getPid()%>,'<%=cs.getName()%>&nbsp;<input type="radio" id="cid" name="cid" value="<%=cs.getId()%>"/>');
<%
}
%>
document.write(d);
//-->
</script>
      </div>      </td>
    </tr>
  <tr>
    <td height="30" colspan="2" align="center"><input type="submit" name="button" id="button" value="下一步" /></td>
  </tr>
</table>
</form>
</body>
</html>