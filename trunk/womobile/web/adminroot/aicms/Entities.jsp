<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*,util.HibernateUtil,HibernateBeans.cms.*" errorPage="" %>
<%
long l1=System.currentTimeMillis();
request.setCharacterEncoding("utf-8");
int cid=Integer.parseInt(request.getParameter("cid"));

org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmscls=(cmsClass)s.load(cmsClass.class,new Integer(cid));

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Entities</title>
<link href="../styles/table.css" rel="stylesheet" type="text/css" />
<style>
div.lcontent{
width:80px;
overflow:hidden;
height:30px;
line-height:30px;
vertical-align:middle;
cursor:pointer;
}
div.lcontent:hover{
overflow:visible;
cursor:text;
vertical-align:middle;
color:#FF0000;
}
</style>
</head>

<body>
<%
entitiesSearch esh=new entitiesSearch();
 String cupagestr=request.getParameter("page");
 if(cupagestr==null||cupagestr.equals(""))cupagestr="1";
 int currentPage=Integer.parseInt(cupagestr);
 String tialstr="&cid="+cid;
 
 esh.setPageSize(13);
 esh.setCurrentPage(currentPage);
 esh.addCondition(cid);
 esh.build();
 List list=esh.getList();
%>   
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <tr class="title">
    <td><%=cmscls.getName()%></td>
  </tr>
  <tr>
    <td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="19%"><input type="button" name="button" id="button" onclick="javascript:window.location.href='newEntity_step2.jsp?cid=<%=cid%>'" value="添加<%=cmscls.getName()%>" /></td>
          <td width="81%">
              &nbsp;&nbsp;页码：<%=esh.getCurrentPage()%>/<%=esh.getPageCount()%>&nbsp;&nbsp;
              <%if(esh.getCurrentPage()>1&&esh.getCurrentPage()<=esh.getPageCount()){%><a href="?page=1<%=tialstr%>">首页</a><%}%>&nbsp;
              <%if(esh.getCurrentPage()>1){%><a href="?page=<%=esh.getCurrentPage()-1%><%=tialstr%>">上一页</a><%}%>&nbsp;
              <%if(esh.getCurrentPage()<esh.getPageCount()){%><a href="?page=<%=esh.getCurrentPage()+1%><%=tialstr%>">下一页</a><%}%>&nbsp;
          <%if(esh.getCurrentPage()>=1&&esh.getCurrentPage()<esh.getPageCount()){%><a href="?page=<%=esh.getPageCount()%><%=tialstr%>">尾页</a><%}%>
          </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="1" cellspacing="0" cellpadding="0" frame="void">
      <tr class="second">
<%
List st=cmscls.getListViewAttributes();
List attributesArr=new ArrayList();
for(int i=0;i<st.size();i++){
    attribute att=(attribute)st.get(i);
    attributesArr.add(att.getName());
%>
        <td><%=att.getName()%></td>
<%}%>
<td>--</td>
<td>--</td>
<td>--</td>
      </tr>
<%
 for(int i=0;i<list.size();i++){
 entity ent=(entity)list.get(i);
 Map map=ent.getAllAttributeValues();
%>
      <tr class="gird">
<%
for(int x=0;x<attributesArr.size();x++){
%>
        <td align="center" valign="middle"><div class="lcontent"><pre><%=map.get(attributesArr.get(x).toString()).toString()%></pre></div></td>
<%}%>
<td align="center" valign="middle"><a href="entityInfo.jsp?id=<%=ent.getId()%>" target="_self">查看</a></td>
<td align="center" valign="middle"><a href="modifyEntity.jsp?id=<%=ent.getId()%>" target="_self">修改</a></td>
<td align="center" valign="middle"><a href="confirm.jsp?url=delEntity.jsp?id=<%=ent.getId()%>&msg=删除记录" target="_self">删除</a></td>
      </tr>
<%}%>
    </table></td>
  </tr>
</table>
</body>
</html>
<!--运行该页面花费: <%=System.currentTimeMillis()-l1%> ms-->