/*
 * addEntity.java
 *
 * Created on 2008年4月23日, 上午2:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import util.HibernateUtil;
/**
 *
 * @author Administrator
 */
public class addEntity {
    
    /** Creates a new instance of addEntity */
    private final String vpath="/";
    public addEntity() {
    }
    
//<editor-fold defaultstate="collapsed" desc="getFormHTML(attribute attr):根据属性名，返回表单HTML代码">
    /**
     *根据属性名，返回表单HTML代码
     */
    public String getFormHTML(attribute attr)
    {
        String html="";
        if(attr.getType().equals("string"))html=getStringFormHtml(attr.getId(),attr.getDefaultValue());
        else if(attr.getType().equals("password"))html=getPwdFormHtml(attr.getId(),attr.getDefaultValue());
        else if(attr.getType().equals("int"))html=getIntFormHtml(attr.getId(),attr.getDefaultValue());
        else if(attr.getType().equals("float"))html=getFloatFormHtml(attr.getId(),attr.getDefaultValue());
        else if(attr.getType().equals("radio"))html=getRadioFormHtml(attr.getId(),attr.getValueList());
        else if(attr.getType().equals("checkbox"))html=getCheckboxFormHtml(attr.getId(),attr.getValueList());
        else if(attr.getType().equals("list"))html=getListFormHtml(attr.getId(),attr.getValueList());
        else if(attr.getType().equals("text"))html=getTextFormHtml(attr.getId(),attr.getDefaultValue());
        else if(attr.getType().equals("datetime"))html=getDateFormHtml(attr.getId(),attr.getDefaultValue());
        else if(attr.getType().equals("image"))html=getImageFormHtml(attr.getId(),attr.getDefaultValue());
        else if(attr.getType().equals("file"))html=getFileFormHtml(attr.getId(),attr.getDefaultValue());
        else html="";
        return html;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="String getFormHTML(cmsClass cls,String attributeName)，返回表单HTML代码">
    /**
     *根据类名和属性名，返回表单HTML代码
     */
public String getFormHTML(cmsClass cls,String attributeName)
{
  search sh=new search();
  return getFormHTML(sh.searchAttributeByName(cls,attributeName));
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

//<editor-fold defaultstate="collapsed" desc="getRadioFormHtml(int id, int listid):返回单选类型表单html">
/**
 *返回单选类型表单html
 */
    private String getRadioFormHtml(int id, int listid) {
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String result="";
        cmsClass cmscls=(cmsClass)s.load(cmsClass.class,new Integer(listid));
        s.getTransaction().commit();
        List list=cmscls.getSubs();
        for(int i=0;i<list.size();i++)
        {
        cmsClass cmlsdd=(cmsClass)list.get(i);
        result+=cmlsdd.getName()+"<input type=\"radio\" id=\""+id+"\" name=\""+id+"\" value=\""+cmlsdd.getId()+"\"/>";
        }
        return result;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getCheckboxFormHtml(int id, int listid):返回复选类型表单html">
/**
 *返回复选类型表单html
 */
    private String getCheckboxFormHtml(int id, int listid) {
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String result="";
        cmsClass cmscls=(cmsClass)s.load(cmsClass.class,new Integer(listid));
        s.getTransaction().commit();
        List list=cmscls.getSubs();
        for(int i=0;i<list.size();i++)
        {
        cmsClass cmlsdd=(cmsClass)list.get(i);
        result+=cmlsdd.getName()+"<input type=\"checkbox\" id=\""+id+"\" name=\""+id+"\" value=\""+cmlsdd.getId()+"\"/>";
        }
        return result;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getListFormHtml(int id, int listid):返回下拉列表类型表单html">
/**
 *返回下拉列表类型表单html
 */
    private String getListFormHtml(int id, int listid) {
        org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String result="";
        result+="<select id=\""+id+"\" name=\""+id+"\" size=\"1\">";
        cmsClass cmscls=(cmsClass)s.load(cmsClass.class,new Integer(listid));
        s.getTransaction().commit();
        List list=cmscls.getSubs();
        for(int i=0;i<list.size();i++)
        {
        cmsClass cmlsdd=(cmsClass)list.get(i);
        result+="<option value=\""+cmlsdd.getId()+"\">"+cmlsdd.getName()+"</option>";
        }
        result+="</seclet>";
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
    
//<editor-fold defaultstate="collapsed" desc="getFileFormHtml(int i, String string):返回文件地址">
    private String getFileFormHtml(int i, String string) {
        String result="<input type=\"hidden\" id=\""+i+"\" name=\""+i+"\" value=\""+string+"\"/>";
        result+="<iframe scrolling=\"no\" frameborder=\"0\" src=\""+vpath+"upload/uploadFile.jsp?id="+i+"\" width=\"350\" height=\"25\"></iframe>";
        return result;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getImageFormHtml(int i, String string):返回图像地址">
    private String getImageFormHtml(int i, String string) {
        String result="<input type=\"hidden\" id=\""+i+"\" name=\""+i+"\" value=\""+string+"\"/>";
        result+="<iframe scrolling=\"no\" frameborder=\"0\" src=\""+vpath+"upload/uploadImage.jsp?id="+i+"\" width=\"350\" height=\"25\"></iframe>";
        return result;
    }
//</editor-fold>

}
