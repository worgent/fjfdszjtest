/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateBeans.cms;

/**
 *
 * @author James
 */
public class A{
private attribute attri;
public A(int aid)
{
  org.hibernate.Session s=util.HibernateUtil.getSession();
  util.HibernateUtil.beginTransaction();
  attri=(attribute)s.load(attribute.class, new Integer(aid));
  util.HibernateUtil.commitTransaction();
}

public attribute getA()
{
 return attri;
}

public int getId()
{
 return attri.getId();
}

public String getName()
{
 return attri.getName();
}

public String getType()
{
 return attri.getType();
}
}
