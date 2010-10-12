
package HibernateBeans.cms ;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.HibernateUtil;
/**
 * 
 *
 * @hibernate.class
 *     table="CMS_CLASS"
 *
 */
public class cmsClass {
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   int pid ">
  private int pid;
/**
  *   @hibernate.property
  */
   public int getPid () {
      return pid;
   } 
   public void setPid (int pid) {
      this.pid = pid;
   }
   // </editor-fold>
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   String pathinfo ">
  private String pathinfo;
/**
  *   @hibernate.property
  */
   public String getPathinfo () {
      return pathinfo;
   } 
   public void setPathinfo(String pathinfo) {
      this.pathinfo = pathinfo;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String name ">
  private String name;
/**
  *   @hibernate.property
  */
   public String getName () {
      return name;
   } 
   public void setName (String name) {
      this.name = name;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   int sort ">
  private int sort;
/**
  *   @hibernate.property
  */
   public int getSort () {
      return sort;
   } 
   public void setSort (int sort) {
      this.sort = sort;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String des ">
  private String des;
/**
  *   @hibernate.property
  */
   public String getDes () {
      return des;
   } 
   public void setDes (String des) {
      this.des = des;
   }
   // </editor-fold>
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   Date wtime ">
  private Date wtime;
/**
  *   @hibernate.property
  */
   public Date getWtime() {
      return wtime;
   } 
   public void setWtime(Date wtime) {
      this.wtime = wtime;
   }
   // </editor-fold> 
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   boolean lockme ">
  private boolean lockme;
/**
  *   @hibernate.property
  */
   public boolean getLockme() {
      return lockme;
   } 
   public void setLockme(boolean lockme) {
      this.lockme = lockme;
   }
   // </editor-fold> 
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   Date lastModify ">
  private Date lastModify;
/**
  *   @hibernate.property
  */
   public Date getLastModify() {
      return lastModify;
   } 
   public void setLastModify(Date lastModify) {
      this.lastModify = lastModify;
   }
   // </editor-fold>
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   Set attributes ">
  private Set attributes;
/**
  *   @hibernate.property
  */
   public Set getAttributes() {
      return attributes;
   } 
   public void setAttributes(Set attributes) {
      this.attributes = attributes;
   }
   // </editor-fold> 
   
 // <editor-fold defaultstate="collapsed" desc=" Property:   Set entities ">
  private Set entities;
/**
  *   @hibernate.property
  */
   public Set getEntities() {
      return entities;
   } 
   public void setEntities(Set entities) {
      this.entities = entities;
   }
   // </editor-fold> 

   // <editor-fold defaultstate="collapsed" desc=" PrimaryKey:   int id ">
   private int id;
/**
  *   @hibernate.id
  *     generator-class="increment"
  */
   public int getId () {
      return id;
   } 
   public void setId (int id) {
      this.id = id;
   }
   //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getSubs():获取子类别List">
/**
 *获取子类别List
 */  
 public List getSubs()
 {
    org.hibernate.Session se_sqqdb=HibernateUtil.getSessionFactory().getCurrentSession();
    se_sqqdb.beginTransaction();
    List list=se_sqqdb.createQuery("from cmsClass where pid=:cid order by sort ASC").setInteger("cid",this.getId()).list();
    se_sqqdb.getTransaction().commit();
    return list;
 }
 //</editor-fold>
 
//<editor-fold defaultstate="collapsed" desc="attribute getAttributeByName(String attributeName):根据名称查询属性">
/**
*根据名称该类下的查询属性
 */  
 public attribute getAttributeByName(String attributeName)
 {
    org.hibernate.Session se_sqqdb=HibernateUtil.getSessionFactory().getCurrentSession();
    se_sqqdb.beginTransaction();
    List list=se_sqqdb.createQuery("from attribute where cmsclass.id=? and name=? order by sort ASC").setInteger(0,this.getId()).setString(1,attributeName).setFirstResult(0).setMaxResults(1).list();
    se_sqqdb.getTransaction().commit();
    if(!list.isEmpty()){
    attribute attr=(attribute)list.get(0);
    return attr;
    }else return null;
 }
 //</editor-fold>
 
//<editor-fold defaultstate="collapsed" desc="List getListViewAttributes():获得所有允许在后台管理数据列表中显示的属性">
/**
*获得所有允许在后台管理数据列表中显示的属性
 */  
 public List getListViewAttributes()
 {
    org.hibernate.Session se_sqqdb=HibernateUtil.getSessionFactory().getCurrentSession();
    se_sqqdb.beginTransaction();
    List list=se_sqqdb.createQuery("from attribute where cmsclass.id=? and listColumn=? order by sort ASC").setInteger(0,this.getId()).setBoolean(1,true).list();
    se_sqqdb.getTransaction().commit();
   return list;
 }
 //</editor-fold>
 
//<editor-fold defaultstate="collapsed" desc="List getNewdataViewAttributes():获得所有允许在后台管理创建新数据时显示的属性">
/**
*获得所有允许在后台管理创建新数据时显示的属性
 */  
 public List getNewdataViewAttributes()
 {
    org.hibernate.Session se_sqqdb=HibernateUtil.getSessionFactory().getCurrentSession();
    se_sqqdb.beginTransaction();
    List list=se_sqqdb.createQuery("from attribute where cmsclass.id=? and newEdit=? order by sort ASC").setInteger(0,this.getId()).setBoolean(1,true).list();
    se_sqqdb.getTransaction().commit();
   return list;
 }
 //</editor-fold>
 
 //<editor-fold defaultstate="collapsed" desc="List getModifydataViewAttributes():获得所有允许在后台管理修改数据时显示的属性">
/**
*获得所有允许在后台管理修改数据时显示的属性
 */  
 public List getModifydataViewAttributes()
 {
    org.hibernate.Session se_sqqdb=HibernateUtil.getSessionFactory().getCurrentSession();
    se_sqqdb.beginTransaction();
    List list=se_sqqdb.createQuery("from attribute where cmsclass.id=? and updateEdit=? order by sort ASC").setInteger(0,this.getId()).setBoolean(1,true).list();
    se_sqqdb.getTransaction().commit();
   return list;
 }
 //</editor-fold>
 
//<editor-fold defaultstate="collapsed" desc="allAttributes():所有属性,List方式">
/**
 *所有属性,List方式.
 *建议使用getAttributes，再转List
 *除非有特殊需要，否则不推荐使用该方法
 */
public List allAttributes()
{
    org.hibernate.Session se_sqqdb=HibernateUtil.getSessionFactory().getCurrentSession();
    se_sqqdb.beginTransaction();
    List list=se_sqqdb.createQuery("from attribute where cid=:cid order by sort ASC").setInteger("cid",this.getId()).list();
    se_sqqdb.getTransaction().commit();
    return list;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="allEntities():所有实体,List方式">
/**
 *所有实体,List方式
 *建议使用getEntities
 *除非有特殊需要，否则不推荐使用该方法
 */
public List allEntities()
{
    org.hibernate.Session se_sqqdb=HibernateUtil.getSessionFactory().getCurrentSession();
    se_sqqdb.beginTransaction();
    List list=se_sqqdb.createQuery("from entity where cid=:cid order by wtime DESC").setInteger("cid",this.getId()).list();
    se_sqqdb.getTransaction().commit();
    return list;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" allAttributesMap():所有属性,Map(属性名->属性)">
/**
 *所有属性,Map(属性名->属性)
 */
public Map allAttributesMap()
{
  Set set=this.getAttributes();
  Iterator it=set.iterator();
  Map map=new HashMap();
  while(it.hasNext())
  {
   attribute attri=(attribute)it.next();
   map.put(attri.getName(),attri);
  }
 return map;
}
//</editor-fold>
}