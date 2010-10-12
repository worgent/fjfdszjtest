/*
 * wash.java
 *
 * Created on 2008年6月16日, 下午7:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans;
import HibernateBeans.cms.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import util.HibernateUtil;
/**
 *
 * @author Administrator
 */
public class wash {
    
    /** Creates a new instance of wash */
    public wash() {
    }
/**
 *清除无主商品
 */    
   public void cleanLoneProducts() throws Exception
   {
     entitiesSearch esh=new entitiesSearch();
     cmsClass cls=esh.searchClassByName("购物商城>商品");
     esh.addCondition(cls.getId());
     List products=esh.getAllList();
     for(int i=0;i<products.size();i++)
     {
      entity product=(entity)products.get(i);
      Map productInfo=product.getAllAttributeValues();
      int owerId=Integer.parseInt(productInfo.get("发布者").toString());
      entity ower=null;
      org.hibernate.Session session=HibernateUtil.getSessionFactory().getCurrentSession();
      session.beginTransaction();
      if(owerId==0){product.getCmsclass().getEntities().remove(product);}
      try{
          ower=(entity)session.load(entity.class,new Integer(owerId));
      }catch(Exception ex) {
          //removeEntity re=new removeEntity(product);
          //re.remove();
          product.getCmsclass().getEntities().remove(product);
          //product.setAttributeValue("发布者","315");
      }finally{
          
      }
      if(ower==null){
          removeEntity re=new removeEntity(product);
          re.remove();
          //product.setAttributeValue("发布者","315");
      }
     }
   }
/**
 *清除无主店铺
 */
public void cleanLoneShop() throws Exception
{
     entitiesSearch esh=new entitiesSearch();
     cmsClass cls=esh.searchClassByName("购物商城>店铺");
     esh.addCondition(cls.getId());
     List shops=esh.getAllList();
     for(int i=0;i<shops.size();i++)
     {
      entity shop=(entity)shops.get(i);
      Map shopInfo=new LinkedHashMap();
      try{
          shopInfo=shop.getAllAttributeValues();
      }catch(Exception ex) {
          System.out.println("err");
      }finally{
          
      } 
      int owerId=Integer.parseInt(shopInfo.get("店主").toString());
      org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
      s.beginTransaction();
      entity ower=null;
      try{
          ower=(entity)s.load(entity.class,new Integer(owerId));
      }catch(org.hibernate.ObjectNotFoundException ex) {
          //System.out.println("errmsg: "+ex.getMessage());
          //removeEntity re=new removeEntity(shop);
          //re.remove();
      }finally{
         if(ower==null){removeEntity re=new removeEntity(shop);re.remove();} 
      }
     
      }
   //session.flush();  
}

public static void main(String args[]) throws Exception
{
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cs=(cmsClass)s.load(cmsClass.class, new Integer(1));
attribute attr=new attribute();
attr.setCmsclass(cs);
    attr.setName("属性1");
    attr.setType("string");
    attr.setLegth(255);
    attr.setLockme(false);
    attr.setListColumn(true);
    attr.setNewEdit(true);
    attr.setUpdateEdit(true);
    attr.setValueList(1);
    attr.setDefaultValue("无");
    attr.setSort(1);
    attr.setPosition("varcharV");
    s.save(attr);
    s.getTransaction().commit();
}
}
