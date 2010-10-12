<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,util.HibernateUtil,java.util.*" errorPage="" %>

<%
request.setCharacterEncoding("utf-8");
String idStr=request.getParameter("id");
int id=Integer.parseInt(idStr);
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmscs=(cmsClass)s.load(cmsClass.class,new Integer(id));
int thesort=cmscs.getSort();
cmscs.setSort(-1);
s.flush();
List _tt=s.createQuery("select id from cmsClass where sort="+(thesort+1)+" and id<>"+id+" and pid="+cmscs.getPid()+"").setFirstResult(0).setMaxResults(1).list();
String tt$=_tt.toString();
tt$=tt$.substring(1,tt$.length()-1);
if(!_tt.isEmpty())s.createQuery("update cmsClass set sort=sort-1 where id in("+tt$+")").executeUpdate();
s.getTransaction().commit();

s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmscs=(cmsClass)s.load(cmsClass.class,new Integer(id));
cmscs.setSort(thesort+1);
s.flush();
s.getTransaction().commit();
response.sendRedirect("classList.jsp");
%>
