<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,util.HibernateUtil,java.util.List" errorPage="" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
request.setCharacterEncoding("utf-8");
String idStr=request.getParameter("id");
int id=Integer.parseInt(idStr);

org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List list=s.createQuery("select count(css) from cmsClass css where pid="+id+"").list();
s.getTransaction().commit();
Long subCount=(Long)list.get(0);
if(subCount<1){
s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmscs=(cmsClass)s.load(cmsClass.class,new Integer(id));
int theSort=cmscs.getSort();
if(cmscs.getLockme()){out.println("类被超级管理员锁定，无法删除!");out.close();return;}
else {s.delete(cmscs);
int updateEnties=s.createQuery("update cmsClass set sort=sort-1 where sort>"+theSort+"").executeUpdate();//更新顺序
int updateEnties2=s.createQuery("delete from attribute where cid="+id+"").executeUpdate();//删除属性
}
s.getTransaction().commit();
//s.close();
response.sendRedirect("classList.jsp");
}else
    {
    //out.println(subCount);
    response.sendRedirect("alert.jsp?url=classList.jsp&msg="+java.net.URLEncoder.encode("请先删除子类别")+"");
    }
%>
