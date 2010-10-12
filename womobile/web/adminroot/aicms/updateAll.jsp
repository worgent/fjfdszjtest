<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.updateAllClass" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Update all</title>
</head>

<body>
    <h1>Update All</h1>
    <hr/>
<%
updateAllClass ua=new updateAllClass();
ua.flush();
%>
update finished.
<%
org.hibernate.Session s=util.HibernateUtil.getSession();
util.HibernateUtil.beginTransaction();
s.createQuery("delete from entity where cmsclass.id not in (select id from cmsClass)").executeUpdate();
s.createQuery("delete from attribute where cmsclass.id not in (select id from cmsClass)").executeUpdate();

s.createQuery("delete from intV where e_id not in (select id from entity)").executeUpdate();
s.createQuery("delete from floatV where e_id not in (select id from entity)").executeUpdate();
s.createQuery("delete from dateV where e_id not in (select id from entity)").executeUpdate();
s.createQuery("delete from varcharV where e_id not in (select id from entity)").executeUpdate();
s.createQuery("delete from textV where e_id not in (select id from entity)").executeUpdate();

s.createQuery("delete from intV where a_id not in (select id from attribute)").executeUpdate();
s.createQuery("delete from floatV where a_id not in (select id from attribute)").executeUpdate();
s.createQuery("delete from dateV where a_id not in (select id from attribute)").executeUpdate();
s.createQuery("delete from varcharV where a_id not in (select id from attribute)").executeUpdate();
s.createQuery("delete from textV where a_id not in (select id from attribute)").executeUpdate();
util.HibernateUtil.commitTransaction();
out.println("data clean!");
%>
</body>
</html>
