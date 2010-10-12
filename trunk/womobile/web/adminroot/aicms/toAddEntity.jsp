<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.search,HibernateBeans.cms.cmsClass" errorPage="" %>
<%
request.setCharacterEncoding("utf-8");
String path=request.getParameter("path");
path=new String(path.getBytes("iso8859-1"),"utf-8");
search sh=new search();
cmsClass cmscls=sh.searchClassByName(path);
if(cmscls!=null){
    response.sendRedirect("newEntity_step2.jsp?cid="+cmscls.getId());
}else{%>
对不起，路径错误！
<%}%>