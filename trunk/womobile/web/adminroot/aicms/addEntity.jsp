<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*,util.HibernateUtil,HibernateBeans.cms.*" errorPage="" %>
 <%
request.setCharacterEncoding("utf-8");
int cid=Integer.parseInt(request.getParameter("cid"));
 org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
 s.beginTransaction();
 entity ent=new entity();
 ent.setWtime(new Date());
 ent.setLastModify(new Date());
 cmsClass cmcls=(cmsClass)s.load(cmsClass.class,new Integer(cid));
 ent.setCmsclass(cmcls);
 s.save(ent);
 s.getTransaction().commit();
Map map=request.getParameterMap();
if(map!=null){
Iterator it=map.entrySet().iterator();
while(it.hasNext()){
Map.Entry entry=(Map.Entry)it.next();
Object ok=entry.getKey();
Object ov=entry.getValue();
String key=ok.toString();
String keyval="";
String[] value=new String[1];
        if(ov instanceof String[]){
            value=(String[])ov;
        }else{
            value[0]=ov.toString();
        }
        keyval+=value[0];
        for(int k=1;k<value.length;k++){
            keyval+=","+value[k];
        }
ent.setAttribute(key,keyval);
}
}
response.sendRedirect("Entities.jsp?cid="+cid);
%>
