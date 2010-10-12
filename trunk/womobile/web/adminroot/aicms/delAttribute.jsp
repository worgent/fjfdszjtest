<%@ page contentType="text/html; charset=utf-8" language="java" import="util.HibernateUtil,HibernateBeans.cms.attribute" errorPage="" %><%
String idstr=request.getParameter("id");
int id=Integer.parseInt(idstr);
String author=request.getParameter("author");
if(author==null||!author.equals("james")){out.close();return;}
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
attribute attr=(attribute)s.load(attribute.class,new Integer(id));
if(!attr.getLockme()){
    attr.getCmsclass().getAttributes().remove(attr);
    s.delete(attr);
    out.print("YES");
    }
else out.print("NO");
s.getTransaction().commit();
%>