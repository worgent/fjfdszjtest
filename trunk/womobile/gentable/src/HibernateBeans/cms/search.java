/*
 * search.java
 *
 * Created on 2008年4月26日, 上午11:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class search {
    
    private Set searchResult=new HashSet();
    /** Creates a new instance of search */
    public search() {
    }
//<editor-fold defaultstate="collapsed" desc="searchClassByName(String name):根据自然语言字符串查询对应的cmsClass">
/**
 *根据自然语言字符串查询对应的cmsClass。
 *查询字符串格式：对象>对象>对象
 */
public cmsClass searchClassByName(String name)
{
  String names[]=name.split(">");
  cmsClass cmscls=null;
  int pid=0;
  for(int i=0;i<names.length;i++)
  {
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from cmsClass where pid = ? and name=?").setInteger(0,pid).setString(1,names[i]).setFirstResult(0).setMaxResults(1).list();
     //s.getTransaction().commit();
     if(vals.size()<1)break;
     cmscls=(cmsClass)vals.get(0);
     pid=cmscls.getId();
     //s.getTransaction().commit();
  } 
  return cmscls;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="attribute searchAttributeByName(cmsClass cls,String attributeName):根据类和属性名查询属性">
/**
 *根据类和属性名查询属性
 */
public attribute searchAttributeByName(cmsClass cls,String attributeName)
{
   org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
   s.beginTransaction();
   List list=s.createQuery("from attribute where cmsclass=? and name=?").setEntity(0,cls).setString(1,attributeName).setFirstResult(0).setMaxResults(1).list();
   s.getTransaction().commit();
   if(list.isEmpty())return null;
   else return (attribute)list.get(0);  
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="searchEntityByValue(int aid,int value):根据值查询entity">
/**
 *根据值查询entity
 */
public List searchEntityByValue(int aid,int value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List list=s.createQuery("select e from entity as e , intV as i where e.id=i.e_id and i.intV=? and i.a_id=? order by e.wtime desc").setInteger(0,value).setInteger(1,aid).list();
     //s.getTransaction().commit();
     return list;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="searchEntityByValue(int aid,float value):根据值查询entity">
/**
 *根据值查询entity
 */
public List searchEntityByValue(int aid,float value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List list=s.createQuery("select e from entity as e , floatV as i where e.id=i.e_id and i.floatV=? and i.a_id=? order by e.wtime desc").setFloat(0,value).setInteger(1,aid).list();
     //s.getTransaction().commit();
     return list;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="searchEntityByValue(int aid,String value):根据值查询entity">
/**
 *根据值查询entity
 */
public List searchEntityByValue(int aid,String value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List list=s.createQuery("select e from entity as e , varcharV as i where e.id=i.e_id and i.varcharV=? and i.a_id=? order by e.wtime desc").setString(0,value).setInteger(1,aid).list();
     //s.getTransaction().commit();
     return list;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="searchEntityByWord(int aid,String value):根据关键字查询entity">
/**
 *根据关键字查询entity
 */
public List searchEntityByWord(int aid,String value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List list=s.createQuery("select e from entity as e , textV as i where e.id=i.e_id and i.textV like ? and i.a_id=? order by e.wtime desc").setString(0,"%"+value+"%").setInteger(1,aid).list();
     //s.getTransaction().commit();
     return list;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getEntitiesByCls(cmsClass cmsclass):返回class的所有entity">
/**
 *返回class的所有entity
 */
public List getEntitiesByCls(cmsClass cmsclass)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List list=s.createQuery("select e from entity as e where e.cmsclass.id=? order by e.wtime desc").setInteger(0,cmsclass.getId()).list();
     //s.getTransaction().commit();
     return list;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="recurrde(cmsClass cmscls,String attributeName,String value):递归有条件查询实现函数">
/**
 *递归有条件查询实现函数
 */
private void recurrde(cmsClass cmscls,String attributeName,String value)
{
  List list=cmscls.getSubs();
  if(list.size()>0){
  for(int i=0;i<list.size();i++){
  cmsClass $cmscls=(cmsClass)list.get(i);
  recurrde($cmscls,attributeName,value);
  }
  }
  else {
  attribute attri=(attribute) cmscls.allAttributesMap().get(attributeName);
  if(attri!=null){
      if(attri.getType().equals("string"))searchResult.addAll(searchEntityByValue(attri.getId(),value));
      else if (attri.getType().equals("int")||attri.getType().equals("radio")){
          int v=Integer.parseInt(value);
          List $list=searchEntityByValue(attri.getId(),v);
          try{
          if($list!=null&&$list.size()>0)searchResult.addAll($list);
          }catch(Exception ex){}
      }
      else if (attri.getType().equals("float")){
          float v=Float.parseFloat(value);
          List $list=searchEntityByValue(attri.getId(),v);
          if($list!=null&&$list.size()>0)searchResult.addAll($list);
      }
  }
  }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="recurrdeAll(cmsClass cmscls):递归无条件查询实现函数">
/**
 *递归无条件查询实现函数
 */
private void recurrdeAll(cmsClass cmscls)
{
  List list=cmscls.getSubs();
  if(list.size()>0){
  for(int i=0;i<list.size();i++){
  cmsClass $cmscls=(cmsClass)list.get(i);
  recurrdeAll($cmscls);
  }
  }
  else {
  List $set=getEntitiesByCls(cmscls);
  if($set!=null&&$set.size()>0)searchResult.addAll($set);
  }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="selectAll(cmsClass cmscls):非递归方式无条件查询">
/**
 *非递归方式无条件查询
 */
private void selectAll(cmsClass cmscls)
{
org.hibernate.Session sx=HibernateUtil.getSessionFactory().getCurrentSession();
sx.beginTransaction();
List cmsIdList=sx.createQuery("select id from cmsClass where pathinfo like '%|"+cmscls.getId()+"|%'").list();
String cmsIdstr=cmsIdList.toString();
cmsIdstr=cmsIdstr.substring(1,cmsIdstr.length()-1);
List list=sx.createQuery("from entity where cmsclass.id in("+cmsIdstr+") order by wtime desc").list();
searchResult.addAll(list);
sx.getTransaction().commit();
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="recurrdeSearch(cmsClass cmscls,String attributeName,String value):递归有条件查询">
/**
 *递归有条件查询
 *参数：cmsClass cmscls,String attributeName,String value
 *特别注意参数值value,必须是string(float,int须转为string)
 */
public Set recurrdeSearch(cmsClass cmscls,String attributeName,String value)
{
 searchResult=new HashSet();
 recurrde(cmscls,attributeName,value);
 return searchResult;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="recurrdeSearch(cmsClass cmscls):无条件查询">
/**
 *无条件查询
 *参数：cmsClass cmscls
 */
public Set recurrdeSearch(cmsClass cmscls)
{
 searchResult=new HashSet();
 //recurrdeAll(cmscls);
 selectAll(cmscls);
 return searchResult;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setMinContains(Set set1,Set set2):最小相容方式删选Set">
/**
 *最小相容方式删选Set
 */
public Set setMinContains(Set set1,Set set2)
{
 if(set1==null)return set2;
 else if(set2==null)return set1;
 if(set1.size()>=set2.size())
 {
  set1.retainAll(set2);
  return set1;
 }else{
  set2.retainAll(set1);
  return set2;
 }
// if(set1.size()>=set2.size())
// {
//   Iterator it=set2.iterator();
//   while(it.hasNext()){
//       Object o=it.next();
//   if(set1.contains(o))result.add(o);
//   }
// }else{
//   Iterator it=set1.iterator();
//   while(it.hasNext()){
//    Object o=it.next();
//   if(set2.contains(o))result.add(o);
//   }
// }
// return result;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setMaxContains(Set set1,Set set2):最大相容方式删选Set">
/**
 *最大相容方式删选Set
 */
public Set setMaxContains(Set set1,Set set2)
{
 Set result=new HashSet();
 result.addAll(set1);
 result.addAll(set2);
 return result;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setMaxContains(List list1,List list2):最大相容方式删选List">
/**
 *最大相容方式删选List
 */
public Set setMaxContains(List list1,List list2)
{
 Set result=new HashSet();
 result.addAll(list1);
 result.addAll(list2);
 return result;
}
//</editor-fold>

public static void main(String[] args) throws Exception
{
  search sh=new search();
  ////////////////////////////////////////
  long l1=System.currentTimeMillis();
  cmsClass cls=sh.searchClassByName("会员系统>会员资料");
  attribute attr=sh.searchAttributeByName(cls,"姓名");
  System.out.println(attr.getId()+"-->"+attr.getName());
  System.out.println("本次运行查询耗费时间: "+(System.currentTimeMillis()-l1)+" ms");
}
}
