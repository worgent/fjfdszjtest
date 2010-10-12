/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class E {
 private Map values;
 private entity ent;
 public E(int eid)
 {
  setEntity(eid);
 }
  public E(Object entx)
 {
  int eid=Integer.parseInt(entx.toString());
  setEntity(eid);
 }
  
 private void setEntity(int eid)
 {
  org.hibernate.Session s=util.HibernateUtil.getSession();
  util.HibernateUtil.beginTransaction();
  ent=(entity)s.load(entity.class, new Integer(eid));
  util.HibernateUtil.commitTransaction();
 }
 /**
  * 获得entity对象
  * @return
  */
 public entity getE()
 {
  return ent;
 }
 /**
  * getId()
  * @return
  */
 public int getId()
 {
  return ent.getId();
 }
/**
 * 过滤结果
 * @param fstr
 * @return
 */
 public Map filterValue(String[] fstr)
 {
    if(values==null)try {
            values = ent.getAllAttributeValues();
        } catch (Exception ex) {
            //Logger.getLogger(E.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    for(int i=0;i<fstr.length;i++)
    {
     if(values.containsKey(fstr[i]))values.remove(fstr[i]);
    }
    return values;
 }
/**
 * 获得属性值
 * @param attributeName
 * @return
 */
 public String getValue(String attributeName)
 {
  if(values==null)try {
            values = ent.getAllAttributeValues();
        } catch (Exception ex) {
            //Logger.getLogger(E.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
  if(values.containsKey(attributeName))
  {
   return values.get(attributeName).toString();
  }else return "";
 }
 
/**
 * 获得属性值
 * @param attributeName
 * @return
 */
 public String V(String attributeName)
 {
  return getValue(attributeName);
 }
 
 /**
 * 设置属性值
 * @param attributeName
 * @return
 */
 public void V(String attributeName,String value)
 {
        try {
            getE().setAttributeValue(attributeName, value);
        } catch (ParseException ex) {
            Logger.getLogger(E.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 public void V(String attributeName,int value)
 {
  V(attributeName,String.valueOf(value));
 }
 public void V(String attributeName,float value)
 {
  V(attributeName,String.valueOf(value));
 }
 /**
  * 设置值（增长）
  * @param attributeName
  * @param value
  */
 public void increase(String attributeName,int value)
 {
  int nv=Integer.parseInt(V(attributeName));
  V(attributeName,nv+value);
 }
 
 public String IMG(String attributeName,int width,int height,boolean needZoom)
 {
  //if(!needZoom)
  return V(attributeName).equals("")!=true?"<img src=\"/editorUploadfiles/"+V(attributeName)+"\" border=\"0\" width=\""+width+"\" height=\""+height+"\"/>":"<img src=\"/editorUploadfiles/nopic.jpg\" width=\""+width+"\" border=\"0\" height=\""+height+"\"/>";
 }
 
 public String IMG(String attributeName)
 {
  //if(!needZoom)
  return V(attributeName).equals("")!=true?"/editorUploadfiles/"+V(attributeName):"/editorUploadfiles/nopic.jpg";
 }
}
