<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.attribute,util.HibernateUtil" errorPage="" %><%
int id=Integer.parseInt(request.getParameter("id"));
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
attribute attri=(attribute)s.load(attribute.class,new Integer(id));
if(attri.getLockme()){attri.setLockme(false);out.print("unlock");}
else {attri.setLockme(true);out.print("lock");}
s.getTransaction().commit();
//s.flush();
//s.close();
%>