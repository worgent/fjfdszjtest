/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public class newEntity {
    private cmsClass cls;
    public newEntity(cmsClass cls)
    {
     this.cls=cls;
    }
    public newEntity(String path)
    {
     search sh=new search();
     this.cls=sh.searchClassByName(path);
    }
    /**
     * 返回表单域的html
     * @param attributeName
     * @return
     */
    public String getFieldHtml(String attributeName)
    {
     addEntity ade=new addEntity();
     return ade.getFormHTML(cls, attributeName);
    }
    /**
     * 返回表单域的ID
     * @param attributeName
     * @return
     */
    public int getFieldId(String attributeName)
    {
     return cls.getAttributeByName(attributeName).getId();
    }
    /**
     * 返回表单域的默认值
     * @param attributeName
     * @return
     */
    public String getFieldValue(String attributeName)
    {
     return cls.getAttributeByName(attributeName).getDefaultValue();
    }
/**
 * 富文本类型表单域
 * @param attributeName 属性名
 * @param width 编辑器宽度
 * @param height 编辑器高度
 * @param ToolbarSet 工具栏类别
 * @param ToolbarStartExpanded 工具栏是否展开
 * @return
 */
    public String getTextFieldHtml(String attributeName,String width,String height,String ToolbarSet,String ToolbarStartExpanded)
    {
    return "<textarea id=\""+getFieldId(attributeName)+"\" name=\""+getFieldId(attributeName)+"\" style=\"WIDTH:"+width+";HEIGHT:"+height+"px\">"+getFieldValue(attributeName)+"</textarea>" +
                "<script type=\"text/javascript\">" +
                "var oFCKeditor = new FCKeditor(\""+getFieldId(attributeName)+"\");" +
                //"oFCKeditor.BasePath=\""+vpath+"FCKeditor/\";" +
                ((width.endsWith("%"))?"":("oFCKeditor.Width="+width+";"))+
                "oFCKeditor.Height="+height+";" +
                "oFCKeditor.ToolbarSet=\""+ToolbarSet+"\";" +
                "oFCKeditor.Config['ToolbarStartExpanded']='"+ToolbarStartExpanded+"';"+
                "oFCKeditor.ReplaceTextarea();" +
                "</script>";
    }
/**
 * 富文本类型表单域
 * @param attributeName
 * @return
 */
 public String getTextFieldHtml(String attributeName)
 {
  return getTextFieldHtml(attributeName,"100%","400","Default","true");
 }
/**
 * 获得属性值键对:name-attribute
 * @return
 */
    public Map attributeMap()
    {
      return cls.allAttributesMap();
    }
/**
 * 排除相应名称的属性，返回剩余属性键对
 * String filterName[]={"name1","name2","name3"};
 * @param filterName
 * @return
 */
    public Map attributeMap(String filterName[])
    {
      Map map=cls.allAttributesMap();
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
      return cls.allAttributes();
    }
}
