/*
 * entityExtra.java
 *
 * Created on 2008年4月29日, 下午3:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;
import java.text.ParseException;
import java.util.Iterator;
import javax.servlet.http.*;
import java.util.Map;
/**
 * 辅助entity对象，简化操作
 * 
 */
public class entityExtra {
    private entity ent; 
    private HttpServletRequest request;
    /** Creates a new instance of entityExtra */
    public entityExtra() {
    }
    public entityExtra(HttpServletRequest request,entity ent) {
        this.request=request;
        this.ent=ent;
    }
//<editor-fold defaultstate="collapsed" desc="setRequest(HttpServletRequest request)">
    public void setRequest(HttpServletRequest request)
    {
    this.request=request;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="setEntity(entity ent)">
    public void setEntity(entity ent)
    {
    this.ent=ent;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="attributeAutoSave():自动保存属性,需要初始化request和entity">
/**
 *自动保存属性,需要初始化request和entity
 */    
public void attributeAutoSave() throws ParseException
{
    Map map=request.getParameterMap();
    if(map!=null){
    Iterator it=map.entrySet().iterator();
    while(it.hasNext()){
    Map.Entry entry=(Map.Entry)it.next();
    Object ok=entry.getKey();
    Object ov=entry.getValue();
    String key=ok.toString();
    String keyval="";
    String[] value=new String[1];
            if(ov instanceof String[]){
                value=(String[])ov;
            }else{
                value[0]=ov.toString();
            }
            keyval+=value[0];
            for(int k=1;k<value.length;k++){
                keyval+=","+value[k];
            }
    ent.setAttribute(key,keyval);
    }
    }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="attributeAutoSave(HttpServletRequest request,entity ent):自动保存属性,带参数request和entity">
/**
 *自动保存属性,带参数request和entity
 */    
public void attributeAutoSave(HttpServletRequest request,entity ent) throws ParseException
{
 setRequest(request);
 setEntity(ent);
 attributeAutoSave();
}
//</editor-fold>
}
