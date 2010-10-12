
package HibernateBeans.cms ;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.HibernateUtil;
/**
 * 
 *
 * @hibernate.class
 *     table="ENTITY"
 *
 */
public class entity {

  // <editor-fold defaultstate="collapsed" desc=" Property:   Date wtime ">
  private Date wtime;
/**
  *   @hibernate.property
  */
   public Date getWtime () {
      return wtime;
   } 
   public void setWtime (Date wtime) {
      this.wtime = wtime;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   Date lastModify ">
  private Date lastModify;
/**
  *   @hibernate.property
  */
   public Date getLastModify () {
      return lastModify;
   } 
   public void setLastModify (Date lastModify) {
      this.lastModify = lastModify;
   }
   // </editor-fold>
   
// <editor-fold defaultstate="collapsed" desc=" Property:   cmsClass cmsclass ">
  private cmsClass cmsclass;
/**
  *   @hibernate.property
  */
   public cmsClass getCmsclass () {
      return cmsclass;
   } 
   public void setCmsclass (cmsClass cmsclass) {
      this.cmsclass = cmsclass;
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
//////////////////////////////////属性操作/////////////////////////////////////////////////////////////////////////
private MD5 md5=new MD5();

// <editor-fold defaultstate="collapsed" desc="setAttribute(String idstr,String value):设置属性值,自动判别类型">
/**
 *设置属性值,自动判别类型
 */
public void setAttribute(String idstr,String value) throws ParseException
{
    int id=0;
    try{
        id=Integer.parseInt(idstr);
    }catch(Exception ex) {
      //System.out.println("参数错误!!");
      return;  
    }finally{
        
    }
 org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
 s.beginTransaction();
 //List attributeList=s.createQuery("from attribute where id = ? and cid=?").setInteger(0,id).setInteger(1,this.getCmsclass().getId()).list();
 attribute attri=(attribute)s.load(attribute.class,new Integer(id));
 s.getTransaction().commit();
  if(attri!=null){
     if(attri.getType().equals("string")){
         setVarcharAttribute(attri,value);
     }
     else if(attri.getType().equals("password")){
        setPwdAttribute(attri,value);
     }
     else if(attri.getType().equals("int")){
         int intvalue=Integer.parseInt(value);
         setIntAttribute(attri,intvalue);
     }
     else if(attri.getType().equals("float")){
        float fvalue=Float.parseFloat(value);
        setFloatAttribute(attri,fvalue);
     }
     else if(attri.getType().equals("radio")){
        int intvalue=Integer.parseInt(value);
        setIntAttribute(attri,intvalue);
     }
     else if(attri.getType().equals("checkbox")){
          setVarcharAttribute(attri,value);
     }
     else if(attri.getType().equals("list")){
         int intvalue=Integer.parseInt(value);
         setIntAttribute(attri,intvalue);
     }
     else if(attri.getType().equals("text")){
         setTextAttribute(attri,value);
     }
     else if(attri.getType().equals("datetime")){
        SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        if(!(value.equals("")||value.equals("now")||value.equals("现在")))date1=localTime.parse(value);
        setDateAttribute(attri,date1); 
     }
     else if(attri.getType().equals("image")){
        setVarcharAttribute(attri,value);
     }
     else if(attri.getType().equals("file")){
        setVarcharAttribute(attri,value);
     }
  }
}
//</editor-fold>

// <editor-fold defaultstate="collapsed" desc="setAttributeValue(String attributeName,String value):设置属性值,自动判别类型">
public synchronized void setAttributeValue(String attributeName,String value) throws ParseException
{
   org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
   s.beginTransaction();
   List list=s.createQuery("from attribute where cmsclass=? and name=?").setEntity(0,getCmsclass()).setString(1,attributeName).setFirstResult(0).setMaxResults(1).list();
   s.getTransaction().commit();
   if(!list.isEmpty())
   {
    attribute attri=(attribute)list.get(0);
    //-------------
    if(attri!=null){
     if(attri.getType().equals("string")){
         setVarcharAttribute(attri,value);
     }
     else if(attri.getType().equals("password")){
        setPwdAttribute(attri,value);
     }
     else if(attri.getType().equals("int")){
         int intvalue=Integer.parseInt(value);
         setIntAttribute(attri,intvalue);
     }
     else if(attri.getType().equals("float")){
        float fvalue=Float.parseFloat(value);
        setFloatAttribute(attri,fvalue);
     }
     else if(attri.getType().equals("radio")){
        int intvalue=Integer.parseInt(value);
        setIntAttribute(attri,intvalue);
     }
     else if(attri.getType().equals("checkbox")){
          setVarcharAttribute(attri,value);
     }
     else if(attri.getType().equals("list")){
         int intvalue=Integer.parseInt(value);
         setIntAttribute(attri,intvalue);
     }
     else if(attri.getType().equals("text")){
         setTextAttribute(attri,value);
     }
     else if(attri.getType().equals("datetime")){
        SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        if(!(value.equals("")||value.equals("now")||value.equals("现在")))date1=localTime.parse(value);
        setDateAttribute(attri,date1); 
     }
     else if(attri.getType().equals("image")){
        setVarcharAttribute(attri,value);
     }
     else if(attri.getType().equals("file")){
        setVarcharAttribute(attri,value);
     }
    }
    //-------------
   }
}
//</editor-fold>

// <editor-fold defaultstate="collapsed" desc="getAllAttributeValues():返回所有属性值Map(属性名->属性值)">
/**
 *返回所有属性值Map(属性名->属性值)
 */
public Map getAllAttributeValues()throws Exception
{
cmsClass cls$=getCmsclass();
if(!org.hibernate.Hibernate.isInitialized(cls$))org.hibernate.Hibernate.initialize(cls$);
Set attris=cls$.getAttributes();
Iterator it=attris.iterator();
Map map = new LinkedHashMap();
while(it.hasNext())
 {
   attribute attri=(attribute)it.next();
   if(attri.getType().equals("string")){
         map.put(attri.getName(),getVarcharAttribute(attri));
     }
     else if(attri.getType().equals("password")){
         map.put(attri.getName(),getPwdAttribute(attri));
     }
     else if(attri.getType().equals("int")){
         map.put(attri.getName(),getIntAttribute(attri));
     }
     else if(attri.getType().equals("float")){
        map.put(attri.getName(),getFloatAttribute(attri));
     }
     else if(attri.getType().equals("radio")){
        String vas="";
        Map mapp=getRadioValue(attri);
        if(mapp!=null)vas=(String)mapp.get("name");
        map.put(attri.getName(),vas);
     }
     else if(attri.getType().equals("checkbox")){
          List vaslist=getCheckBoxValues(attri);
          String vas="";
          if(vaslist!=null)
          for(int i=0;i<vaslist.size();i++)
          {
            Map mpp=(Map)vaslist.get(i);
            if(i>0)vas+=",";
            vas+=mpp.get("name").toString();
          }
        map.put(attri.getName(),vas);
     }
     else if(attri.getType().equals("list")){
        String vas="";
        Map mapp=getRadioValue(attri); 
        if(mapp!=null) vas=(String)mapp.get("name");
        map.put(attri.getName(),vas);
     }
     else if(attri.getType().equals("text")){
         map.put(attri.getName(),getTextAttribute(attri));
     }
     else if(attri.getType().equals("datetime")){
        Date dtvas=getDateAttribute(attri);
        String vas="";
        if(dtvas!=null){
        SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vas=localTime.format(dtvas);
        }
        map.put(attri.getName(),vas);
     }
     else if(attri.getType().equals("image")){
        map.put(attri.getName(),getVarcharAttribute(attri));
     }
     else if(attri.getType().equals("file")){
        map.put(attri.getName(),getVarcharAttribute(attri));
     }
 }
map.put("创建时间",this.getWtime());
map.put("最后修改时间",this.getLastModify());
return map;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getIntAttribute(attribute attri):返回整数值">
/**
 *返回整数值
*/
private int getIntAttribute(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from intV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return 0;
     intV fobj=(intV)vals.get(0);
     return fobj.getIntV();
 }else return 0;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setIntAttribute(attribute attri,int value):设置整数属性值">
/**
 *设置整数属性值
 */
private void setIntAttribute(attribute attri,int value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from intV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()>0){
         intV lsobj=(intV)vals.get(0);
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         intV iobj=(intV) s.load(intV.class,new Integer(lsobj.getId()));
         iobj.setIntV(value);
         s.getTransaction().commit();
     }else{
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         intV iobj=new intV();
         iobj.setA_id(attri.getId());
         iobj.setE_id(this.getId());
         iobj.setIntV(value);
         s.save(iobj);
         s.getTransaction().commit();
     }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getFloatAttribute(attribute attri):返回浮点数值">
/**
 *返回浮点数值
 */
private float getFloatAttribute(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from floatV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return 0;
     floatV fobj=(floatV)vals.get(0);
     return fobj.getFloatV();
 }else return 0;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setFloatAttribute(attribute attri,float value):设置浮点数属性值">
/**
 *设置浮点数属性值
 */
private void setFloatAttribute(attribute attri,float value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from floatV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()>0){
         floatV lsobj=(floatV)vals.get(0);
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         floatV iobj=(floatV) s.load(floatV.class,new Integer(lsobj.getId()));
         iobj.setFloatV(value);
         s.getTransaction().commit();
     }else{
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         floatV iobj=new floatV();
         iobj.setA_id(attri.getId());
         iobj.setE_id(this.getId());
         iobj.setFloatV(value);
         s.save(iobj);
         s.getTransaction().commit();
     }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getVarcharAttribute(attribute attri):返回字符串值">
/**
 *返回字符串值
 */
private String getVarcharAttribute(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from varcharV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return "";
     varcharV fobj=(varcharV)vals.get(0);
     return fobj.getVarcharV();
 }else return "";
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setVarcharAttribute(attribute attri,String value):设置字符串值">
/**
 *设置字符串值
 */
private void setVarcharAttribute(attribute attri,String value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from varcharV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()>0){
         varcharV lsobj=(varcharV)vals.get(0);
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         varcharV iobj=(varcharV) s.load(varcharV.class,new Integer(lsobj.getId()));
         iobj.setVarcharV(value);
         s.getTransaction().commit();
     }else{
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         varcharV iobj=new varcharV();
         iobj.setA_id(attri.getId());
         iobj.setE_id(this.getId());
         iobj.setVarcharV(value);
         s.save(iobj);
         s.getTransaction().commit();
     }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getPwdAttribute(attribute attri):返回密码串值">
/**
 *返回密码串值
 */
private String getPwdAttribute(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from varcharV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return "";
     varcharV fobj=(varcharV)vals.get(0);
     return fobj.getVarcharV();
 }else return "";
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setPwdAttribute(attribute attri,String value):设置密码串值">
/**
 *设置密码串值
 */
private void setPwdAttribute(attribute attri,String value)
{
     String ecodeStr=md5.getMD5ofStr(value);
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from varcharV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()>0){
         varcharV lsobj=(varcharV)vals.get(0);
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         varcharV iobj=(varcharV) s.load(varcharV.class,new Integer(lsobj.getId()));
         if(!iobj.getVarcharV().equals(value))iobj.setVarcharV(ecodeStr);
         s.getTransaction().commit();
     }else{
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         varcharV iobj=new varcharV();
         iobj.setA_id(attri.getId());
         iobj.setE_id(this.getId());
         iobj.setVarcharV(ecodeStr);
         s.save(iobj);
         s.getTransaction().commit();
     }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getTextAttribute(attribute attri):返回长文本值">
/**
 *返回长文本值
 */
private String getTextAttribute(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from textV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return "";
     textV fobj=(textV)vals.get(0);
     return fobj.getTextV();
 }else return "";
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setTextAttribute(attribute attri,String value):设置长文本值">
/**
 *设置长文本值
 */
private void setTextAttribute(attribute attri,String value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from textV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()>0){
         textV lsobj=(textV)vals.get(0);
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         textV iobj=(textV) s.load(textV.class,new Integer(lsobj.getId()));
         iobj.setTextV(value);
         s.getTransaction().commit();
     }else{
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         textV iobj=new textV();
         iobj.setA_id(attri.getId());
         iobj.setE_id(this.getId());
         iobj.setTextV(value);
         s.save(iobj);
         s.getTransaction().commit();
     }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getDateAttribute(attribute attri):返回日期值">
/**
 *返回日期值
 */
private Date getDateAttribute(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from dateV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return null;
     dateV fobj=(dateV)vals.get(0);
     return fobj.getDateV();
 }else return null;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setDateAttribute(attribute attri,Date value):设置日期值">
/**
 *设置日期值
 */
private void setDateAttribute(attribute attri,Date value)
{
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from dateV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()>0){
         dateV lsobj=(dateV)vals.get(0);
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         dateV iobj=(dateV) s.load(dateV.class,new Integer(lsobj.getId()));
         iobj.setDateV(value);
         s.getTransaction().commit();
     }else{
         s=HibernateUtil.getSessionFactory().getCurrentSession();
         s.beginTransaction();
         dateV iobj=new dateV();
         iobj.setA_id(attri.getId());
         iobj.setE_id(this.getId());
         iobj.setDateV(value);
         s.save(iobj);
         s.getTransaction().commit();
     }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getRadioValue(attribute attri):获取单选形式的选定值Map(id=*,name=*)">
/**
 *获取单选形式的选定值Map(id=*,name=*)
 */
private Map getRadioValue(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from intV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return null;
     intV fobj=(intV)vals.get(0);
     return getCmsClassMap(fobj.getIntV());
 }else return null;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getCmsClassMap(int id):根据ID构造Map(id=*,name=*)">
/**
 *根据ID构造Map(id=*,name=*)
 */
private Map getCmsClassMap(int id)
{
 org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
 s.beginTransaction();
 cmsClass cls=null; 
 if(id==0)return null;
 try{
  cls=(cmsClass)s.load(cmsClass.class,new Integer(id));   
 }catch(Exception ex) {
     return null;
 }finally{
     
 }
 if(cls==null)return null;
 Map map = new HashMap(); 
 map.put("id",cls.getId());
 map.put("name",cls.getName());
 s.getTransaction().commit();
 return map;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getCheckBoxValues(attribute attri):获取多选形式的选定值List<map(id=*,name=*)>">
/**
 *获取多选形式的选定值List<map(id=*,name=*)>
 */
private List getCheckBoxValues(attribute attri)
{
 if(attri!=null){
     org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
     s.beginTransaction();
     List vals=s.createQuery("from varcharV where e_id = ? and a_id=?").setInteger(0,this.getId()).setInteger(1,attri.getId()).list();
     s.getTransaction().commit();
     if(vals.size()<1)return null;
     varcharV fobj=(varcharV)vals.get(0);
     String svl=fobj.getVarcharV();
     if(svl==null||svl.equals(""))return null;
     List list = new ArrayList();
     String svls[]=svl.split(",");
     for(int i=0;i<svls.length;i++)
     {
      list.add(getCmsClassMap(Integer.parseInt(svls[i])));
     }
     return list;
 }else return null;
}
//</editor-fold>
}