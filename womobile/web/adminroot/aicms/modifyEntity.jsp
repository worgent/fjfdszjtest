<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.*,java.util.List,util.HibernateUtil,java.util.*" errorPage="" %>
<%
request.setCharacterEncoding("utf-8");
String idStr=request.getParameter("id");
if(idStr==null||idStr.equals("")){out.println("Error!");out.close();return;}
int id=Integer.parseInt(idStr);

org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
entity ent=(entity)s.load(entity.class,new Integer(id));
//ent.setLastModify(new Date());
modifyEntity modyEn=new modifyEntity(ent);
List attributes=ent.getCmsclass().getModifydataViewAttributes();
//s.save(ent);
//s.flush();
//s.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../styles/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/FCKeditor/fckeditor.js"></script>
<title>Modify Entity</title>
</head>
<body>
<form id="form1" name="form1" method="post" action="updateEntity.jsp">
 <input type="hidden" id="id" name="id" value="<%=id%>"/> 
  <table width="100%" border="1" cellspacing="0" cellpadding="0">
    <tr class="title">
      <td colspan="2" align="center">修改数据 - <%=ent.getCmsclass().getName()%></td>
    </tr>
<%
for(int i=0;i<attributes.size();i++){
    attribute attri=(attribute)attributes.get(i);
%>
    <tr>
      <td height="30"><%=attri.getName()%>：</td>
      <td height="30"><%=modyEn.getFormHTML(attri)%></td>
    </tr>
<%
}
%>
    <tr>
      <td height="30">&nbsp;</td>
      <td height="30"><input type="button" name="button1" id="button1" value="返回" onclick="javascript:window.history.back()" />
        &nbsp;&nbsp;
        <input type="submit" name="button" id="button" value="完成" /></td>
    </tr>
  </table>
</form>
</body>
</html>
