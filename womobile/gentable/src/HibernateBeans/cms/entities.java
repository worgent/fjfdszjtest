/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.ArrayList;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author James
 */
public class entities extends entitiesSearch {
    private cmsClass cls;
    public entities(String path)
    {
     search sh=new search();
     this.cls=sh.searchClassByName(path);
    }
    
    public void reset()
    {
     clean();
    }

//<editor-fold defaultstate="collapsed" desc="addCondition(...)附加条件查询">
    public void addCondition(String attributeName,String Operators,int value)
    {
     addCondition(cls.getId(),attributeName,Operators,value);
    }
    
    public void addCondition(String attributeName,String Operators,float value)
    {
     addCondition(cls.getId(),attributeName,Operators,value);
    }
    
    public void addCondition(String attributeName,String Operators,String value)
    {
     addCondition(cls.getId(),attributeName,Operators,value);
    }
    
    public void addCondition(String attributeName,String value)
    {
     addCondition(cls.getId(),attributeName,value);
    }
    
    public void addCondition()
    {
     addCondition(cls.getId());
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="orCondition(...)相容条件查询">
public void orCondition(String attributeName,String Operators,int value)
{
 orCondition(cls.getId(),attributeName,Operators,value);
}

public void orCondition(String attributeName,String Operators,float value)
{
 orCondition(cls.getId(),attributeName,Operators,value);
}

public void orCondition(String attributeName,String Operators,String value)
{
 orCondition(cls.getId(),attributeName,Operators,value);
}

public void orCondition(String attributeName,String value)
{
 orCondition(cls.getId(),attributeName,value);
}

public void orCondition()
{
 orCondition(cls.getId());
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="search():查询数据">
/**
 *查询数据，和build不同，getList()返回的是integer类型的list
 */
public void search()
{
setList(new ArrayList());
if(getSortKey()!=null&&!getSortKey().equals(""))//有排序规则设定情况下
{
    if(!eids.isEmpty()&&eids.size()>0){
    org.hibernate.Session s=util.HibernateUtil.getSessionFactory().getCurrentSession();
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
//    for(Integer inte:listP){
//        entity entp = (entity)s.load(entity.class,inte);
//        list.add(entp);
//    }
    setList(listP);
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
    String sortR="desc";
    if(getSortRules()==1)sortR="asc";
    List list=s.createQuery("select e.id from entity as e where e.id in("+eidsStr+") order by e.wtime "+sortR).setFirstResult(getFirstResult()).setMaxResults(getLastResult()-getFirstResult()).list();
    s.getTransaction().commit();
    setList(list);
    }
}
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getAllResult():返回所有结果id">
/**
 *返回所有结果Id
 */
public List getAllSearchResult()
{
if(!eids.isEmpty()){
org.hibernate.Session s=util.HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
String eidsStr=eids.toString();
eidsStr=eidsStr.substring(1,eidsStr.length()-1);
List list=s.createQuery("select e.id from entity as e where e.id in("+eidsStr+") order by e.wtime desc").list();
s.getTransaction().commit();
return list;
}else return new ArrayList();
}
//</editor-fold>

public List getAllResult()
{
 addCondition();
 search();
 return getAllSearchResult();
}

public List getResult()
{
 search();
 return getList();
}

}
