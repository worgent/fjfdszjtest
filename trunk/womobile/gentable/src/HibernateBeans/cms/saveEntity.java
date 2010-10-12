/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.text.ParseException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author James
 */
public class saveEntity {
private HttpServletRequest request;
private entity ent;
public saveEntity(HttpServletRequest request)
{
 this.request=request;
}
public saveEntity(HttpServletRequest request,int eid)
{
 this.request=request;
 this.setEntity(eid);
}
public saveEntity(HttpServletRequest request,String path)
{
 this.request=request;
 this.newEntity(path);
}
/**
 * 设置entity
 * @param id
 */
public void setEntity(int eid)
{
 org.hibernate.Session s=util.HibernateUtil.getSessionFactory().getCurrentSession();
 s.beginTransaction();
 entity enti=(entity)s.load(entity.class, new Integer(eid));
 this.ent=enti;
 s.getTransaction().commit();
}
/**
 * 新建entity
 * @param cid
 */
public void newEntity(int cid)
{
 org.hibernate.Session s=util.HibernateUtil.getSessionFactory().getCurrentSession();
 s.beginTransaction();
 cmsClass clsx=(cmsClass)s.load(cmsClass.class, new Integer(cid));
 if(clsx!=null){
 entity enti=new entity();
 enti.setCmsclass(clsx);
 enti.setWtime(new Date());
 enti.setLastModify(new Date());
 s.save(enti);
 this.ent=enti;
 }
 s.getTransaction().commit();
}
/**
 * 根据path设置父类，创建entity
 * @param path
 */
public void newEntity(String  path)
{
 search sh=new search();
 cmsClass clsx=sh.searchClassByName(path);
 if(clsx!=null){
 org.hibernate.Session s=util.HibernateUtil.getSessionFactory().getCurrentSession();
 s.beginTransaction();
 entity enti=new entity();
 enti.setCmsclass(clsx);
 enti.setWtime(new Date());
 enti.setLastModify(new Date());
 s.save(enti);
 s.getTransaction().commit();
 this.ent=enti;
 }
}
/**
 * 自动保存所有属性
 * @return
 */
public boolean autoSave()
{
 entityExtra ext=new entityExtra(this.request,this.ent);
        try {
            ext.attributeAutoSave();
        } catch (ParseException ex) {
            //Logger.getLogger(saveEntity.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
 return true;
}
/**
 * 为属性赋值
 * @param attributeName
 * @param value
 * @return
 */
public boolean setValue(String attributeName,String value)
{
        try {
            this.ent.setAttributeValue(attributeName, value);
        } catch (ParseException ex) {
            //Logger.getLogger(saveEntity.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
}
/**
 * 返回E
 * @return
 */
public E getE()
{
 return new E(ent.getId());
}
}
