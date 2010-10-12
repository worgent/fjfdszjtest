/*
 * modifyEntity.java
 *
 * Created on 2008年4月25日, 下午6:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import util.HibernateUtil;
/**
 *
 * @author Administrator
 */
public class modifyEntity {
    
    /** Creates a new instance of modifyEntity */
    private entity thisEntity;
    private final String vpath="/";
    public modifyEntity(int eid) {
        org.hibernate.Session s=util.HibernateUtil.getSession();
        util.HibernateUtil.beginTransaction();
        entity ent=(entity)s.load(entity.class, new Integer(eid));
        util.HibernateUtil.commitTransaction();
        setThisEntity(ent);
    }
    public modifyEntity(entity thisEntity) {
        this.thisEntity=thisEntity;
    }
    public void setThisEntity(entity thisEntity)
    {
       this.thisEntity=thisEntity;
    }
    public entity getThisEntity()
    {
       return thisEntity;
    }
/**
 * 获得表单域的html代码
 * @param attributeName
 * @return
 */    
public String getFieldHtml(String attributeName)
{
    String result="";
        try {
             result=getFormHTML(attributeName);
        } catch (Exception ex) {
            //Logger.getLogger(modifyEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    return result;
}
/**
 * 获得表单域的ID
 * @param attributeName
 * @return
 */
public int getFieldId(String attributeName)
{
 return this.thisEntity.getCmsclass().getAttributeByName(attributeName).getId();
}
/**
 * 获得表单域的值
 * @param attributeName
 * @return
 */
public String getFieldValue(String attributeName)
{
 E e=new E(this.thisEntity.getId());
 return e.V(attributeName);
}

/**
 * 获得属性值键对:name-attribute
 * @return
 */
    public Map attributeMap()
    {
      return this.thisEntity.getCmsclass().allAttributesMap();
    }
/**
 * 排除相应名称的属性，返回剩余属性键对
 * String filterName[]={"name1","name2","name3"};
 * @param filterName
 * @return
 */
    public Map attributeMap(String filterName[])
    {
      Map map=this.thisEntity.getCmsclass().allAttributesMap();
      for(int i=0;i<filterName.length;i++)
      {
       if(map.containsKey(filterName[i]))map.remove(filterName[i]);
      }
      return map;
    }
/**
 * 所有属性list
 * @return
 */
    public List attributeList()
    {
      return this.thisEntity.getCmsclass().allAttributes();
    }
    
//<editor-fold defaultstate="collapsed" desc="getFormHTML(attribute attr):根据属性名，返回表单HTML代码">
    /**
     *根据属性名，返回表单HTML代码
     */
    public String getFormHTML(attribute attr) throws Exception
    {
        String html="";
        if(attr==null)return "<!--找不到该属性-->";
        if(attr.getType().equals("string"))html=getStringFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("password"))html=getPwdFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("int"))html=getIntFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("float"))html=getFloatFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("radio"))html=getRadioFormHtml(attr.getId(),attr.getValueList(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("checkbox"))html=getCheckboxFormHtml(attr.getId(),attr.getValueList(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("list"))html=getListFormHtml(attr.getId(),attr.getValueList(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("text"))html=getTextFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("datetime"))html=getDateFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("image"))html=getImageFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else if(attr.getType().equals("file"))html=getFileFormHtml(attr.getId(),new E(thisEntity.getId()).V(attr.getName()));
        else html="";
        return html;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="String getFormHTML(cmsClass cls,String attributeName)，返回表单HTML代码">
    /**
     *根据类名和属性名，返回表单HTML代码
     */
public String getFormHTML(cmsClass cls,String attributeName) throws Exception
{
  search sh=new search();
  return getFormHTML(sh.searchAttributeByName(cls,attributeName));
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getFormHTML(String attributeName)，返回表单HTML代码">
    /**
     *根据属性名，返回表单HTML代码,已知类别
     */
public String getFormHTML(String attributeName) throws Exception
{
  search sh=new search();
  return getFormHTML(sh.searchAttributeByName(thisEntity.getCmsclass(),attributeName));
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getStringFormHtml(int id,String defaultValue):返回string类型的表单html">
    /**
     *返回string类型的表单html
     */
    private String getStringFormHtml(int id,String defaultValue)
    {
      return "<input type=\"text\" id=\""+id+"\" name=\""+id+"\" value=\""+defaultValue+"\"/>";
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getPwdFormHtml(int id,String defaultValue):返回password类型的表单html">
    /**
     *返回password类型的表单html
     */
    private String getPwdFormHtml(int id,String defaultValue)
    {
      return "<input type=\"password\" id=\""+id+"\" name=\""+id+"\" value=\""+defaultValue+"\"/>";
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getIntFormHtml(int id, String defaultValue):返回int类型的表单html">
    /**
     *返回int类型的表单html
     */
    private String getIntFormHtml(int id, String defaultValue) {
        int dv=0;
        try{
            dv=Integer.parseInt(defaultValue);
        }catch(Exception ex) {
            
        }finally{
            
        }
        return "<input type=\"text\" id=\""+id+"\" name=\""+id+"\" value=\""+dv+"\"/>";
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getFloatFormHtml(int id, String defaultValue):返回float类型的表单html">
    /**
     *返回float类型的表单html
     */
    private String getFloatFormHtml(int id, String defaultValue) {
        float dv=0;
        try{
            dv=Float.parseFloat(defaultValue);
        }catch(Exception ex) {
            
        }finally{
            
        }
        return "<input type=\"text\" id=\""+id+"\" name=\""+id+"\" value=\""+dv+"\"/>";
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getRadioFormHtml(int id, int listid,String selectedValue):返回单选类型表单html">
/**
 *返回单选类型表单html
 */
    private String getRadioFormHtml(int id, int listid,String selectedValue) {
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String result="";
        cmsClass cmscls=(cmsClass)s.load(cmsClass.class,new Integer(listid));
        List list=cmscls.getSubs();
        for(int i=0;i<list.size();i++)
        {
        String chdstr="";
        cmsClass cmlsdd=(cmsClass)list.get(i);
        if(cmlsdd.getName().equals(selectedValue))chdstr="checked=\"checked\"";
        result+=cmlsdd.getName()+"<input type=\"radio\" id=\""+id+"\" name=\""+id+"\" value=\""+cmlsdd.getId()+"\" "+chdstr+"/>";
        }
        //s.close();
        return result;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getCheckboxFormHtml(int id, int listid,String selectedValue):返回复选类型表单html">
/**
 *返回复选类型表单html
 */
    private String getCheckboxFormHtml(int id, int listid,String selectedValue) {
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String result="";
        String spres[]=null;
        if(!selectedValue.equals(""))spres=selectedValue.split(",");
        cmsClass cmscls=(cmsClass)s.load(cmsClass.class,new Integer(listid));
        List list=cmscls.getSubs();
        for(int i=0;i<list.size();i++)
        {
        cmsClass cmlsdd=(cmsClass)list.get(i);
        String ckdstr="";
        if(spres!=null){
            for(int x=0;x<spres.length;x++)
            {
              if(cmlsdd.getName().equals(spres[x])){ckdstr="checked=\"checked\"";break;}
            }
        }
        result+=cmlsdd.getName()+"<input type=\"checkbox\" id=\""+id+"\" name=\""+id+"\" value=\""+cmlsdd.getId()+"\" "+ckdstr+"/>";
        }
        //s.close();
        return result;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getListFormHtml(int id, int listid,String selectedValue):返回下拉列表类型表单html">
/**
 *返回下拉列表类型表单html
 */
    private String getListFormHtml(int id, int listid,String selectedValue) {
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String result="";
        result+="<select id=\""+id+"\" name=\""+id+"\" size=\"1\">";
        cmsClass cmscls=(cmsClass)s.load(cmsClass.class,new Integer(listid));
        List list=cmscls.getSubs();
        for(int i=0;i<list.size();i++)
        {
        cmsClass cmlsdd=(cmsClass)list.get(i);
        String scdstr="";
        if(cmlsdd.getName().equals(selectedValue))scdstr="selected=\"selected\"";
        result+="<option value=\""+cmlsdd.getId()+"\" "+scdstr+">"+cmlsdd.getName()+"</option>";
        }
        result+="</seclet>";
        //s.close();
        return result;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getTextFormHtml(int id, String defaultValue):返回长文本类型表单HTML">
/**
 *返回长文本类型表单HTML
 */
    private String getTextFormHtml(int id, String defaultValue) {
        /*return "<FCK:editor id=\""+id+"\" basePath=\"/FCKeditor/\" width=\"100%\" height=\"350\" " +
                "imageBrowserURL=\"/FCKeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector\" " +
                "linkBrowserURL=\"/FCKeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector\" " +
                "flashBrowserURL=\"/FCKeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector\" " +
                "imageUploadURL=\"/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Image\" " +
                "linkUploadURL=\"/FCKeditor/editor/filemanager/upload/simpleuploader?Type=File\" " +
                "flashUploadURL=\"/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Flash\">" +
                "</FCK:editor>";
         */
        return "<textarea id=\""+id+"\" name=\""+id+"\" style=\"WIDTH:100%;HEIGHT:400px\">"+defaultValue+"</textarea>" +
                "<script type=\"text/javascript\">" +
                "var oFCKeditor = new FCKeditor(\""+id+"\");" +
                "oFCKeditor.BasePath=\""+vpath+"FCKeditor/\";" +
                "oFCKeditor.Height=400;" +
                "oFCKeditor.ToolbarSet=\"Default\";" +
                "oFCKeditor.ReplaceTextarea();" +
                "</script>";
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getDateFormHtml(int id, String dateStr):返回时间类型表单HTML">
/**
 *返回时间类型表单HTML
 */
    private String getDateFormHtml(int id, String dateStr) {
        String timeresult="";
        SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1;
        try {
            date1 = localTime.parse(dateStr);
            timeresult=localTime.format(date1);
        } catch (ParseException ex) {
            //ex.printStackTrace();
            java.util.Date xx=new java.util.Date();   //当前时间   
            java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
            timeresult=df.format(xx);
        }
        return "<input type=\"text\" id=\""+id+"\" name=\""+id+"\" value=\""+timeresult+"\"/>";
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getFileFormHtml(int i, String string):返回文件路径">
    private String getFileFormHtml(int i, String string) {
        String result="<input type=\"hidden\" id=\""+i+"\" name=\""+i+"\" value=\""+string+"\"/>";
        result+="<iframe scrolling=\"no\" frameborder=\"0\" src=\""+vpath+"upload/uploadFile.jsp?id="+i+"\" width=\"350\" height=\"25\"></iframe>";
        if(!string.equals(""))result+="<a href=\""+vpath+"editorUploadfiles/"+string+"\" target=\"_blank\">查看当前文件</a>";
        return result;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getImageFormHtml(int i, String string):返回图像路径">
    private String getImageFormHtml(int i, String string) {
        String result="<input type=\"hidden\" id=\""+i+"\" name=\""+i+"\" value=\""+string+"\"/>";
        result+="<iframe scrolling=\"no\" frameborder=\"0\" src=\""+vpath+"upload/uploadImage.jsp?id="+i+"\" width=\"350\" height=\"25\"></iframe>";
        if(!string.equals(""))result+="<a href=\""+vpath+"editorUploadfiles/"+string+"\" target=\"_blank\">查看当前图像</a>";
        return result;
    }
//</editor-fold>

}
