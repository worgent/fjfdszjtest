/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateBeans.cms;

/**
 *
 * @author James
 */
public class C {
private cmsClass cls;
public C(int cid)
{
  org.hibernate.Session s=util.HibernateUtil.getSession();
  util.HibernateUtil.beginTransaction();
  cls=(cmsClass)s.load(cmsClass.class, new Integer(cid));
  util.HibernateUtil.commitTransaction();
}
public C(String path)
{
 search sh=new search();
 cls=sh.searchClassByName(path);
}
/**
 * 获得cmsClass对象
 * @return
 */
public cmsClass getC()
{
 return cls;
}
/**
 * 获得cmsClass Id
 * @return
 */
public int getId()
{
 return cls.getId();
}
/**
 * 获得cmsClass Name
 * @return
 */
public String getName()
{
 return cls.getName();
}
}
