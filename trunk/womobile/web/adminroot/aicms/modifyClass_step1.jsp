<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,util.HibernateUtil,java.util.List" errorPage="" %>
<%
String idStr=request.getParameter("id");
int id=Integer.parseInt(idStr);
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmscs=(cmsClass)s.load(cmsClass.class,new Integer(id));
int selectedId=cmscs.getPid();
String name=cmscs.getName();
String des=cmscs.getDes();
boolean isLock=cmscs.getLockme();
s.close();
if(isLock)response.sendRedirect("modifyClass_step2.jsp?id="+id+"&pid="+selectedId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" href="dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="dtree/dtree.js"></script>
<title>修改类别</title>
<link href="../styles/table.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form action="modifyClass_step2.jsp" method="post">
<input type="hidden" name="id" id="id" value="<%=id%>" />
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <tr class="title">
    <td colspan="2" align="center">修改数据类别(步骤1：选定上级类别)</td>
  </tr>
  <tr>
    <td width="9%" height="30">父类别：</td>
    <td width="91%" height="30">
<div class="dtree">
<script type="text/javascript">
<!--
d = new dTree('d');
//mytree.add(1, 0, 'My node', 'node.html', 'node title', 'mainframe', 'img/musicfolder.gif');
var ix=-1;
var rootId=0;
var pId=0;
d.add(0,-1,'请选择分类&nbsp;&nbsp;<span style="text-align:right; height:15px; background-color:#F0F0F0; vertical-align:middle"><a href="javascript:d.openAll();"><img src="dtree/img/icon-expandall.gif" border="0" /></a>-<a href="javascript:d.closeAll();"><img src="dtree/img/icon-closeall.gif" border="0" /></a></span>');
<%
s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List list=s.createQuery("from cmsClass order by pid ASC,sort ASC").list();
s.getTransaction().commit();
for(int i=0;i<list.size();i++)
{
    cmsClass cs=(cmsClass)list.get(i);
%>
    d.add(<%=cs.getId()%>,<%=cs.getPid()%>,'<%=cs.getName()%>&nbsp;<input type="radio"<%if(selectedId==cs.getId()){%> checked="checked"<%}%> id="pid" name="pid" value="<%=cs.getId()%>"/>');
<%
}
%>
document.write(d);
//-->
</script>
</div>    </td>
  </tr>
  
  
  <tr>
    <td height="30">&nbsp;</td>
    <td height="30"><input name="" value="撤销修改" type="reset" />&nbsp;&nbsp;<input type="submit" name="button" id="button" value="下一步" /></td>
  </tr>
</table>
</form>
</body>
</html>
