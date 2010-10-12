<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.*,util.HibernateUtil" errorPage="" %>
<%
request.setCharacterEncoding("utf-8");
String idStr=request.getParameter("id");
if(idStr==null||idStr.equals("")){out.println("Error!");out.close();return;}
int id=Integer.parseInt(idStr);
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
entity ent=(entity)s.load(entity.class,new Integer(id));
int e_id=ent.getId();
int cid=ent.getCmsclass().getId();
ent.getCmsclass().getEntities().remove(ent);
s.delete(ent);
s.getTransaction().commit();
/////////////删除存储值////////
s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
s.createQuery("delete from dateV where e_id="+e_id+"").executeUpdate();
s.createQuery("delete from floatV where e_id="+e_id+"").executeUpdate();
s.createQuery("delete from intV where e_id="+e_id+"").executeUpdate();
s.createQuery("delete from textV where e_id="+e_id+"").executeUpdate();
s.createQuery("delete from varcharV where e_id="+e_id+"").executeUpdate();
s.getTransaction().commit();
//////////////////////////////
response.sendRedirect("Entities.jsp?cid="+cid+"");
%>
