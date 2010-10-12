/*
 * updateAllClass.java
 *
 * Created on 2008年5月19日, 下午2:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class updateAllClass {
    
    /** Creates a new instance of updateAllClass */
    public updateAllClass() {
    }
    private void recurrde(cmsClass cls)
    {
         updateClassPathInfo(cls);
         List list=cls.getSubs();
         if(!list.isEmpty())
         {
          for(int i=0;i<list.size();i++)
           {
              cmsClass clst=(cmsClass)list.get(i);
              setCLSsort(clst.getId(),i+1);
              recurrde(clst);
           }
         }
     }
    
    public static void main(String agrs[])
    {
        updateAllClass ua=new updateAllClass();
        ua.flush();
    }
    public void flush()
    {
        flush(0);
    }
    public void flush(int ipid)
    {
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List list=s.createQuery("from cmsClass where pid=?").setInteger(0,ipid).list();
        s.getTransaction().commit();
        if(!list.isEmpty())
            {
            for(int i=0;i<list.size();i++)
                {
                 cmsClass cls=(cmsClass)list.get(i);
                 setCLSsort(cls.getId(),i+1);
                 recurrde(cls);
                }
            }
       //s.close();
    }
    public void updateClassPathInfo(cmsClass cls){
         String path="";
         if(cls.getPid()>0){
         String ppathinfo=getCLSPath(cls.getPid());
         if(ppathinfo!=null)path=ppathinfo+cls.getId()+"|";
         }
         else path="|"+cls.getId()+"|";
         setCLSPath(cls.getId(),path);
    }
  private void setCLSsort(int clsID,int sort)
  {
    org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
    s.beginTransaction();
    cmsClass cls=(cmsClass)s.load(cmsClass.class,new Integer(clsID));
    cls.setSort(sort);
    s.getTransaction().commit();
  }
  private void setCLSPath(int clsID,String path)
  {
    org.hibernate.Session s=HibernateUtil.getSession();
    HibernateUtil.beginTransaction();
    cmsClass cls=(cmsClass)s.load(cmsClass.class,new Integer(clsID));
    cls.setPathinfo(path);
    HibernateUtil.commitTransaction();
  }
  
  private String getCLSPath(int clsID)
  {
    org.hibernate.Session s=HibernateUtil.getSession();
    HibernateUtil.beginTransaction();
    cmsClass cls=(cmsClass)s.load(cmsClass.class,new Integer(clsID));
    String res=cls.getPathinfo();
    HibernateUtil.commitTransaction();
    return res;
  }
}
