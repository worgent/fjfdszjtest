/*
 * removeEntity.java
 *
 * Created on 2008年5月23日, 下午11:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.List;
import util.HibernateUtil;

/**
 *删除entity
 *必须初始化eid或者entity
 */
public class removeEntity {
    
    /** Creates a new instance of removeEntity */
    entity ent;
    int eid;
    public removeEntity() {
    }
    public removeEntity(int eid) {
        setEid(eid);
    }
   public removeEntity(entity ent) {
         this.ent=ent;
         this.eid=ent.getId();
    }
//<editor-fold defaultstate="collapsed" desc="void setEid(int eid)">
   public void setEid(int eid)
   {
        this.eid=eid;
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        ent=(entity)s.load(entity.class,new Integer(eid));
        s.getTransaction().commit();
   }
//</editor-fold>
   
//<editor-fold defaultstate="collapsed" desc="setEntity(entity ent)">
   public void setEntity(entity ent)
   {
         this.ent=ent;
         this.eid=ent.getId();
   }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="void removeEnt()">
   private void removeEnt()
   {
    org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
    ent=(entity)s.load(entity.class,new Integer(eid));
    try{
        ent.getCmsclass().getEntities().remove(ent);
    }catch(Exception ex) {
        //nothing
    }finally{
       
    }
    s.delete(ent); 
    s.getTransaction().commit();
   }
//</editor-fold>
   
//<editor-fold defaultstate="collapsed" desc="void removeValues()">
   private void removeValues()
   {
    org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
    s.createQuery("delete from dateV where e_id="+eid+"").executeUpdate();
    s.createQuery("delete from floatV where e_id="+eid+"").executeUpdate();
    s.createQuery("delete from intV where e_id="+eid+"").executeUpdate();
    s.createQuery("delete from textV where e_id="+eid+"").executeUpdate();
    s.createQuery("delete from varcharV where e_id="+eid+"").executeUpdate();
    s.getTransaction().commit();
   }
//</editor-fold>
   
//<editor-fold defaultstate="collapsed" desc="void remove()">
   /**
    * 删除entity及存储值
    */
   public void remove()
   {
    removeEnt();
    removeValues();
   }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="void remove()">
   /**
    * entity关联删除
    * @param path
    * @param attributeName
    */
   public void remove(String path,String attributeName)
   {
    entities ets=new entities(path);
    ets.addCondition(attributeName, "=",eid);
    List list=ets.getAllSearchResult();
    for(int i=0;i<list.size();i++)
    {
     removeEntity rex=new removeEntity(Integer.parseInt(list.get(i).toString()));
     rex.remove();
    }
   }
//</editor-fold>
}
