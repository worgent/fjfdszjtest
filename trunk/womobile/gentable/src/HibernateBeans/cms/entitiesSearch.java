/*
 * entitiesSearch.java
 *
 * Created on 2008年5月20日, 下午1:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.HibernateUtil;

/**
 * 数据查询，已绑定分页功能
 */
public class entitiesSearch extends pageResult {
    
    /** Creates a new instance of entitiesSearch */
    protected Set eids=new HashSet();
    protected int searchCount=0;
    public entitiesSearch() {
    }
    
//<editor-fold defaultstate="collapsed" desc="addCondition(...)附加条件查询">
/**
 *附加条件查询,针对整数类型
 */
public void addCondition(int cmsClassId,String attributeName,String Operators,int value)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , intV as i where e.id=i.e_id and i.intV "+Operators+" "+value+" and i.a_id in("+aidStr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMinContains(eids,new HashSet(list));
else if(searchCount==0) eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *附加条件查询,增加快速排序功能
 */
//public void addCondition(int cmsClassId,String attributeName,String Operators,int value,String sortRule)
//{
//search sh=new search();
//org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
//s.beginTransaction();
//List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
//String cmsIdstr=cmsIdList.toString();
//cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
//List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
//String aidStr=aidList.toString();
//aidStr=aidStr.substring(1,aidStr.length()-1);
//List list=s.createQuery("select e.id from entity as e , intV as i where e.id=i.e_id and i.intV "+Operators+" "+value+" and i.a_id in("+aidStr+") order by i.intV "+sortRule+"").list();
//if(!eids.isEmpty())eids=sh.setMinContains(eids,new HashSet(list));
//else if(searchCount==0) eids=new HashSet(list);
//s.getTransaction().commit();
//searchCount++;//查询次数递增
//}
/**
 *附加条件查询,针对小数类型
 */
public void addCondition(int cmsClassId,String attributeName,String Operators,float value)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , floatV as i where e.id=i.e_id and i.floatV "+Operators+" "+value+" and i.a_id in("+aidStr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMinContains(eids,new HashSet(list));
else if(searchCount==0) eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *附加条件查询，针对字符串类型进行查询，可用'like'操作符进行模糊查询
 */
public void addCondition(int cmsClassId,String attributeName,String Operators,String value)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
if(Operators.equals("like"))value="%"+value+"%";
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , varcharV as i where e.id=i.e_id and i.varcharV "+Operators+" '"+value+"' and i.a_id in("+aidStr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMinContains(eids,new HashSet(list));
else if(searchCount==0) eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *附加条件查询,针对长文本类型数据进行'like'查询
 */
public void addCondition(int cmsClassId,String attributeName,String value)
{
String Operators="like";
value="%"+value+"%";
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , textV as i where e.id=i.e_id and i.textV "+Operators+" '"+value+"' and i.a_id in("+aidStr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMinContains(eids,new HashSet(list));
else if(searchCount==0) eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *无条件查询
 */
public void addCondition(int cmsClassId)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List list=s.createQuery("select e.id from entity as e where e.cmsclass.id in("+cmsIdstr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMinContains(eids,new HashSet(list));
else if(searchCount==0) eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="orCondition(...)相容条件查询">
/**
 *相容条件查询
 */
public void orCondition(int cmsClassId,String attributeName,String Operators,int value)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , intV as i where e.id=i.e_id and i.intV "+Operators+" ? and i.a_id in(?) order by e.wtime desc").setInteger(0,value).setString(1,aidStr).list();
if(!eids.isEmpty())eids=sh.setMaxContains(eids,new HashSet(list));
else eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *相容条件查询
 */
public void orCondition(int cmsClassId,String attributeName,String Operators,float value)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , floatV as i where e.id=i.e_id and i.floatV "+Operators+" ? and i.a_id in(?) order by e.wtime desc").setFloat(0,value).setString(1,aidStr).list();
if(!eids.isEmpty())eids=sh.setMaxContains(eids,new HashSet(list));
else eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *相容条件查询
 */
public void orCondition(int cmsClassId,String attributeName,String Operators,String value)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
if(Operators.equals("like"))value="%"+value+"%";
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , varcharV as i where e.id=i.e_id and i.varcharV "+Operators+" '"+value+"' and i.a_id in("+aidStr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMaxContains(eids,new HashSet(list));
else eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *相容条件查询
 */
public void orCondition(int cmsClassId,String attributeName,String value)
{
String Operators="like";
value="%"+value+"%";
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List aidList=s.createQuery("select id from attribute where cmsclass.id in("+cmsIdstr+") and name=:attributeName").setString("attributeName",attributeName).list();
String aidStr=aidList.toString();
aidStr=aidStr.substring(1,aidStr.length()-1);
List list=s.createQuery("select e.id from entity as e , textV as i where e.id=i.e_id and i.textV "+Operators+" '"+value+"' and i.a_id in("+aidStr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMaxContains(eids,new HashSet(list));
else eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
/**
 *无条件查询
 */
public void orCondition(int cmsClassId)
{
search sh=new search();
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List cmsIdList=s.createQuery("select id from cmsClass where pathinfo like '%|"+cmsClassId+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List list=s.createQuery("select e.id from entity as e where e.cmsclass.id in("+cmsIdstr+") order by e.wtime desc").list();
if(!eids.isEmpty())eids=sh.setMaxContains(eids,new HashSet(list));
else eids=new HashSet(list);
s.getTransaction().commit();
searchCount++;//查询次数递增
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="filterId(Set set):在查询结果中过滤数据，必须作为最后的附加查询条件">
/**
 * 在查询结果中过滤数据，必须作为最后的附加查询条件
 * 
 */
public void filterId(Set set)
{
 eids.removeAll(set);
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="build():查询并构造数据">
/**
 *查询并构造数据
 */
public void build()
{
setList(new ArrayList());
if(getSortKey()!=null&&!getSortKey().equals(""))//有排序规则设定情况下
{
    if(!eids.isEmpty()&&eids.size()>0){
    org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
    String eidsStr=eids.toString();
    eidsStr=eidsStr.substring(1,eidsStr.length()-1);
    List list=new ArrayList();
    //属性类别-----
    List oneAttribute=s.createQuery("select type from attribute where cmsclass.id in(select cmsclass.id from entity where id in("+eidsStr+")) and name='"+getSortKey()+"'").setFirstResult(0).setMaxResults(1).list();
    if(oneAttribute.isEmpty())return;
    String theSortRule="desc";
    if(this.getSortRules()==1)theSortRule="asc";
    String theType=(String)oneAttribute.get(0);
    List<Integer> listP=new ArrayList<Integer>();
    if(theType.equals("int")||theType.equals("radio")||theType.equals("list")){
        List listC=s.createQuery("select count(e) from entity as e,intV as i where e.id in("+eidsStr+") and e.id=i.e_id and i.a_id in(select id from attribute where name ='"+getSortKey()+"')").list();
        setRecordCount(Integer.parseInt(listC.get(0).toString()));
        listP=s.createQuery("select i.e_id from intV as i where i.e_id in("+eidsStr+") and i.a_id in(select id from attribute where name ='"+getSortKey()+"') order by i.intV "+theSortRule+"").setFirstResult(getFirstResult()).setMaxResults(getLastResult()-getFirstResult()).list();
    }else if(theType.equals("float")){
        List listC=s.createQuery("select count(e) from entity as e,floatV as i where e.id in("+eidsStr+") and e.id=i.e_id and i.a_id in(select id from attribute where name ='"+getSortKey()+"')").list();
        setRecordCount(Integer.parseInt(listC.get(0).toString()));
        listP=s.createQuery("select i.e_id from floatV as i where i.e_id in("+eidsStr+") and i.a_id in(select id from attribute where name ='"+getSortKey()+"') order by i.floatV "+theSortRule+"").setFirstResult(getFirstResult()).setMaxResults(getLastResult()-getFirstResult()).list();
    }else if(theType.equals("checkbox")||theType.equals("string")){
        List listC=s.createQuery("select count(e) from entity as e,varcharV as i where e.id in("+eidsStr+") and e.id=i.e_id and i.a_id in(select id from attribute where name ='"+getSortKey()+"')").list();
        setRecordCount(Integer.parseInt(listC.get(0).toString()));
        listP=s.createQuery("select i.e_id from varcharV as i where i.e_id in("+eidsStr+") and i.a_id in(select id from attribute where name ='"+getSortKey()+"') order by i.varcharV "+theSortRule+"").setFirstResult(getFirstResult()).setMaxResults(getLastResult()-getFirstResult()).list();
    }else if(theType.equals("datetime")){
        List listC=s.createQuery("select count(e) from entity as e,dateV as i where e.id in("+eidsStr+") and e.id=i.e_id and i.a_id in(select id from attribute where name ='"+getSortKey()+"')").list();
        setRecordCount(Integer.parseInt(listC.get(0).toString()));
        listP=s.createQuery("select i.e_id from dateV as i where i.e_id in("+eidsStr+") and i.a_id in(select id from attribute where name ='"+getSortKey()+"') order by i.dateV "+theSortRule+"").setFirstResult(getFirstResult()).setMaxResults(getLastResult()-getFirstResult()).list();
    }else if(theType.equals("text")){
        List listC=s.createQuery("select count(e) from entity as e,textV as i where e.id in("+eidsStr+") and e.id=i.e_id and i.a_id in(select id from attribute where name ='"+getSortKey()+"')").list();
        setRecordCount(Integer.parseInt(listC.get(0).toString()));
        listP=s.createQuery("select i.e_id from textV as i where i.e_id in("+eidsStr+") and i.a_id in(select id from attribute where name ='"+getSortKey()+"') order by i.textV "+theSortRule+"").setFirstResult(getFirstResult()).setMaxResults(getLastResult()-getFirstResult()).list();
    }
    for(Integer inte:listP){
        entity entp = (entity)s.load(entity.class,inte);
        list.add(entp);
    }
    setList(list);
    s.getTransaction().commit();
    }
}else{//无排序规则设定，默认以ID倒序排列
    if(!eids.isEmpty()&&eids.size()>0){
    org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
    String eidsStr=eids.toString();
    eidsStr=eidsStr.substring(1,eidsStr.length()-1);
    List listC=s.createQuery("select count(e) from entity as e where e.id in("+eidsStr+")").list();
    setRecordCount(Integer.parseInt(listC.get(0).toString()));
    List list=s.createQuery("select e from entity as e where e.id in("+eidsStr+") order by e.wtime desc").setFirstResult(getFirstResult()).setMaxResults(getLastResult()-getFirstResult()).list();
    setList(list);
    s.getTransaction().commit();
    }
}
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getAllList():返回所有结果集">
/**
 *返回所有结果集
 */
public List getAllList()
{
if(!eids.isEmpty()){
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
String eidsStr=eids.toString();
eidsStr=eidsStr.substring(1,eidsStr.length()-1);
List list=s.createQuery("select e from entity as e where e.id in("+eidsStr+") order by e.wtime desc").list();
s.getTransaction().commit();
return list;
}else return new ArrayList();
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="clean():清除结果">
/**
 *清除结果
 */
public void clean()
{
searchCount=0;
eids.clear();
setList(new ArrayList());
setSortKey("");
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="searchClassByName(String name):根据自然语言字符串查询对应的cmsClass">
/**
 *根据自然语言字符串查询对应的cmsClass。
 *查询字符串格式：对象>对象>对象
 */
public cmsClass searchClassByName(String name)
{
 search sh=new search();
 return sh.searchClassByName(name);
}
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="searchEntityValue(int eid,String attributeName):根据entity ID和属性名查询值">
/**
 *根据entity ID和属性名查询值
 * 通常用于二重查询
 */
public String searchEntityValue(int eid,String attributeName) throws Exception
{
  if(eid<1)return null;
  org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
  s.beginTransaction();
  entity tent=(entity)s.load(entity.class,new Integer(eid));
  s.getTransaction().commit();
  Map tvalue=tent.getAllAttributeValues();
  if(tvalue.containsKey(attributeName))return tvalue.get(attributeName).toString();
  else return null;
}
/**
 *根据entity ID和属性名查询值
 *通常用于二重查询
 */
public String searchEntityValue(String eidstr,String attributeName) throws Exception
{
  if(eidstr==null||eidstr.equals(""))return null;
  int eid=Integer.parseInt(eidstr);
  return searchEntityValue(eid,attributeName);
}
//</editor-fold>

//public static void main(String[] args) throws Exception
//{
// entitiesSearch esh=new entitiesSearch();
// esh.clean();
//int currentId=0;
//cmsClass gzcls=esh.searchClassByName("互动>互访记录");
//esh.addCondition(gzcls.getId(),"页面地址","=","http://www.yntrip.com/yanyu/detail.jsp?id=159");
//for(int i=0;i<4;i++){
//esh.addCondition(gzcls.getId(),"访问者ID","<>",currentId);
//esh.setPageSize(1);
//esh.setCurrentPage(1);
//esh.build();
//List gzlist=esh.getList();
//if(gzlist.size()<1)break;
//entity ent=(entity)gzlist.get(0);
//Map map=ent.getAllAttributeValues();
//currentId=Integer.parseInt(map.get("访问者ID").toString());
//    System.out.println("访问者: "+map.get("访问者").toString());
//
//}
//}
}
