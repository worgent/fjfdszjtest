<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,util.HibernateUtil" errorPage="" %><%
int id=Integer.parseInt(request.getParameter("id"));
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmls=(cmsClass)s.load(cmsClass.class,new Integer(id));
if(cmls.getLockme()){cmls.setLockme(false);out.print("unlock");}
else {cmls.setLockme(true);out.print("lock");}
s.getTransaction().commit();
//s.flush();
//s.close();
%>