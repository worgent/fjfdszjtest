<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*,util.HibernateUtil,HibernateBeans.cms.*" errorPage="" %>
 <%
request.setCharacterEncoding("utf-8");
String idStr=request.getParameter("id");
if(idStr==null||idStr.equals("")){out.println("Error!");out.close();return;}
int id=Integer.parseInt(idStr);

 org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
 s.beginTransaction();
entity ent=(entity)s.load(entity.class,new Integer(id));
 ent.setLastModify(new Date());
 cmsClass cmcls=ent.getCmsclass();
 s.flush();
 s.close();
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
response.sendRedirect("Entities.jsp?cid="+cmcls.getId());
%>
