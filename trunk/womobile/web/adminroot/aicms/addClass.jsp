<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,java.util.Date,util.HibernateUtil,HibernateBeans.cms.attribute,HibernateBeans.cms.updateAllClass" errorPage="" %>
<%
request.setCharacterEncoding("utf-8");
updateAllClass uac=new updateAllClass();

String pidStr=request.getParameter("pid");
if(pidStr==null||pidStr.equals(""))pidStr="0";
int pid=Integer.parseInt(pidStr);
String name=request.getParameter("name");
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cs=new cmsClass();
cs.setPid(pid);
cs.setName(name);
cs.setWtime(new Date());
cs.setLastModify(new Date());
cs.setLockme(false);
cs.setSort(1);
s.save(cs);
int theId=cs.getId();
s.getTransaction().commit();
uac.updateClassPathInfo(cs);
//s.getTransaction().commit();
s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
int updateEnties=s.createQuery("update cmsClass set sort=sort+1 where pid="+pid+" and id<>"+theId+"").executeUpdate();
s.getTransaction().commit();
///////////////////////////////////
String attrname[]=request.getParameterValues("attrname");
String attrtype[]=request.getParameterValues("attrtype");
String attrvs[]=request.getParameterValues("attrvs");
String attrsv[]=request.getParameterValues("attrsv");
String listView[]=request.getParameterValues("listView");
String addView[]=request.getParameterValues("addView");
String modView[]=request.getParameterValues("modView");

if(attrname!=null)
for(int i=0;i<attrname.length;i++)
    {
  
    s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
    attribute attr=new attribute();
    attr.setCmsclass(cs);
    attr.setName(attrname[i]);
    attr.setType(attrtype[i]);
    attr.setLegth(255);
    attr.setLockme(false);
    attr.setListColumn(Boolean.valueOf(listView[i]).booleanValue());
    attr.setNewEdit(Boolean.valueOf(addView[i]).booleanValue());
    attr.setUpdateEdit(Boolean.valueOf(modView[i]).booleanValue());
    attr.setValueList(Integer.parseInt(attrvs[i]));
    attr.setDefaultValue(attrsv[i]);
    attr.setSort(i+1);
    ///////
    String position="VARCHAR_V";
    if(attrtype[i].equals("string"))position="varcharV";
    else if(attrtype[i].equals("password"))position="varcharV";
    else if(attrtype[i].equals("int"))position="intV";
    else if(attrtype[i].equals("float"))position="floatV";
    else if(attrtype[i].equals("radio"))position="intV";
    else if(attrtype[i].equals("checkbox"))position="intV";
    else if(attrtype[i].equals("list"))position="intV";
    else if(attrtype[i].equals("text"))position="textV";
    else if(attrtype[i].equals("datetime"))position="dateV";
    else if(attrtype[i].equals("image"))position="varcharV";
    else if(attrtype[i].equals("file"))position="varcharV";
    ///////
    attr.setPosition(position);
    s.save(attr);
    s.getTransaction().commit();
 
    }
//////////////////////////////////
response.sendRedirect("classList.jsp");
%>
