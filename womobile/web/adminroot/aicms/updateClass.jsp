<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,java.util.Date,util.HibernateUtil,HibernateBeans.cms.attribute,HibernateBeans.cms.updateAllClass" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
request.setCharacterEncoding("utf-8");
updateAllClass uac=new updateAllClass();

String idStr=request.getParameter("id");
int id=Integer.parseInt(idStr);
String pidStr=request.getParameter("pid");
if(pidStr==null||pidStr.equals(""))pidStr="0";
int pid=Integer.parseInt(pidStr);
String name=request.getParameter("name");

if(id==pid){out.println("不能把类别本身做为父类别！");out.println("<a href=\"javascript:window.history.back()\" target=\"_self\">返回</a>");out.close();return;}

org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();

cmsClass cmscs=(cmsClass)s.load(cmsClass.class,new Integer(id));
int oldpid=cmscs.getPid();
int oldsort=cmscs.getSort();
cmscs.setPid(pid);
if(!cmscs.getLockme())cmscs.setName(name);
if(oldpid!=pid)cmscs.setSort(1);
cmscs.setLastModify(new Date());
s.getTransaction().commit();
uac.updateClassPathInfo(cmscs);
//s.getTransaction().commit();
if(oldpid!=pid){//当类别变动时
    s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
int updatesorts1=s.createQuery("update cmsClass set sort=sort-1 where pid="+oldpid+" and sort>"+oldsort+"").executeUpdate();
int updatesorts2=s.createQuery("update cmsClass set sort=sort+1 where pid="+pid+" and id<>"+id+"").executeUpdate();
s.getTransaction().commit();
}

int dzsort=0;
//////修改的属性////////////////////////////
String attridM[]=request.getParameterValues("attridM");
String attrnameM[]=request.getParameterValues("attrnameM");
String attrtypeM[]=request.getParameterValues("attrtypeM");
String attrvsM[]=request.getParameterValues("attrvsM");
String attrsvM[]=request.getParameterValues("attrsvM");
String listViewM[]=request.getParameterValues("listViewM");
String addViewM[]=request.getParameterValues("addViewM");
String modViewM[]=request.getParameterValues("modViewM");
if(attridM!=null)
for(int i=0;i<attridM.length;i++)
    {
    s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
    attribute attr=(attribute)s.load(attribute.class,new Integer(Integer.parseInt(attridM[i])));
    attr.setCmsclass(cmscs);
    if(!attr.getLockme())attr.setName(attrnameM[i]);
    attr.setType(attrtypeM[i]);
    attr.setLegth(255);
    attr.setListColumn(Boolean.valueOf(listViewM[i]).booleanValue());
    attr.setNewEdit(Boolean.valueOf(addViewM[i]).booleanValue());
    attr.setUpdateEdit(Boolean.valueOf(modViewM[i]).booleanValue());
    attr.setValueList(Integer.parseInt(attrvsM[i]));
    attr.setDefaultValue(attrsvM[i]);
    dzsort++;
    attr.setSort(dzsort);
    ///////
    String position="VARCHAR_V";
    if(attrtypeM[i].equals("string"))position="varcharV";
    else if(attrtypeM[i].equals("password"))position="varcharV";
    else if(attrtypeM[i].equals("int"))position="intV";
    else if(attrtypeM[i].equals("float"))position="floatV";
    else if(attrtypeM[i].equals("radio"))position="intV";
    else if(attrtypeM[i].equals("checkbox"))position="intV";
    else if(attrtypeM[i].equals("list"))position="intV";
    else if(attrtypeM[i].equals("text"))position="textV";
    else if(attrtypeM[i].equals("datetime"))position="dateV";
    else if(attrtypeM[i].equals("image"))position="varcharV";
    else if(attrtypeM[i].equals("file"))position="varcharV";
    ///////
    attr.setPosition(position);
    s.flush();
    s.getTransaction().commit();
    }
///////////////新增的属性////////////////////
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
    attr.setCmsclass(cmscs);
    attr.setName(attrname[i]);
    attr.setType(attrtype[i]);
    attr.setLegth(255);
    attr.setListColumn(Boolean.valueOf(listView[i]).booleanValue());
    attr.setNewEdit(Boolean.valueOf(addView[i]).booleanValue());
    attr.setUpdateEdit(Boolean.valueOf(modView[i]).booleanValue());
    attr.setValueList(Integer.parseInt(attrvs[i]));
    attr.setDefaultValue(attrsv[i]);
    dzsort++;
    attr.setSort(dzsort);
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
    else if(attrtype[i].equals("image"))position="dateV";
    else if(attrtype[i].equals("file"))position="dateV";
    ///////
    attr.setPosition(position);
    s.save(attr);
    s.getTransaction().commit();
    }    
response.sendRedirect("classList.jsp");
%>