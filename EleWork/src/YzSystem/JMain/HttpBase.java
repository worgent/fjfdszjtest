package YzSystem.JMain;

import java.util.*;

/**
 * <p>Title:对http封装 </p>
 *
 * <p>Description: 对http封装</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-14  生成代码
 */
public class HttpBase {
    Properties properties = new Properties(); // 属性
    private ArrayList elements = new ArrayList(); // 元素
    private String tag = ""; // 标签
    private String Value = ""; // 内容

    public static HttpBase genHttpValue(String avalue) {
        HttpBase val = new HttpBase();
        val.setValue(avalue);
        return val;
    }

    public static HttpBase genHttpFont(String color, String value) {
        HttpBase font = new HttpBase();
        font.setTag("font");
        if (!color.equals("")) {
            font.getProperties().setProperty("color", color);
        }
        font.setValue(value);
        return font;
    }

    /**
     * genHttpInput
     */
    public static HttpBase genHttpInput(String type, String name, String value,
                                        String onclick) {
        HttpBase val = new HttpBase();
        val.setTag("input");
        if (!name.equals("")) {
            val.getProperties().setProperty("name", name);
        }
        if (!type.equals("")) {
            val.getProperties().setProperty("type", type);
        }
        if (!value.equals("")) {
            val.getProperties().setProperty("value", value);
        }
        if (!onclick.equals("")) {
            val.getProperties().setProperty("onclick", onclick);
        }
        return val;
    }

    public static HttpBase genHttpInputRadio(String name, String value,
                                             String onclick) {
        return genHttpInput("radio", name, value, onclick);
    }

    public static HttpBase genHttpA(String style, String onclick) {
        HttpBase val = new HttpBase();
        val.setTag("a");
        if (!style.equals("")) {
            val.getProperties().setProperty("style", style);
        }
        if (!onclick.equals("")) {
            val.getProperties().setProperty("onclick", onclick);
        }
        return val;
    }

    public static HttpBase genHttpTR(String aclass, String align) {
        HttpBase tr = new HttpBase();
        tr.setTag("tr");
        if (!aclass.equals("")) {
            tr.getProperties().setProperty("class", aclass);
        }
        if (!align.equals("")) {
            tr.getProperties().setProperty("align", align);
        }
        return tr;
    }

    public static HttpBase genHttpTR() {
        return genHttpTR("", "");
    }

    public static HttpBase genHttpTR(String aclass) {
        return genHttpTR(aclass, "");
    }

    public static HttpBase genHttpTD(String aclass, String align,
                                     String colspan, String value) {
        HttpBase td = new HttpBase();
        td.setTag("td");
        if (!aclass.equals("")) {
            td.getProperties().setProperty("class", aclass);
        }
        if (!align.equals("")) {
            td.getProperties().setProperty("align", align);
        }
        if (!colspan.equals("")) {
            td.getProperties().setProperty("colspan", colspan);
        }
        if (!value.equals("")) {
            td.getProperties().setProperty("value", value);
        }
        return td;
    }

    public static HttpBase genHttpTH(String aclass, String align,
                                     String colspan, String value) {
        HttpBase td = new HttpBase();
        td.setTag("th");
        if (!aclass.equals("")) {
            td.getProperties().setProperty("class", aclass);
        }
        if (!align.equals("")) {
            td.getProperties().setProperty("align", align);
        }
        if (!colspan.equals("")) {
            td.getProperties().setProperty("colspan", colspan);
        }
        if (!value.equals("")) {
            td.getProperties().setProperty("value", value);
        }
        return td;
    }

    public static HttpBase genHttpScript(String program) {
        HttpBase val = new HttpBase();
        val.setTag("script");
        val.getProperties().setProperty("language", "JavaScript");
        val.getProperties().setProperty("type", "text/JavaScript");
        if (!program.equals("")) {
            HttpBase value = HttpBase.genHttpValue(program);
            val.getElements().add(value);
        }
        return val;
    }

    public static HttpBase genHttpBaseCombobox(String name, ArrayList dis,
                                               ArrayList value) {
        HttpBase select21 = new HttpBase();
        select21.setTag("select");
        select21.getProperties().setProperty("name", name);

        HttpBase option1 = new HttpBase();
        option1.setTag("option");
        option1.getProperties().setProperty("value", "");
        option1.setValue("＝请选择＝");
        select21.getElements().add(option1);

        Iterator itx1 = value.iterator();
        Iterator itx2 = dis.iterator();
        while (itx1.hasNext() && itx2.hasNext()) {
            HttpBase option = new HttpBase();
            option.setTag("option");
            option.getProperties().setProperty("value", (String) itx1.next());
            String curval = (String) itx2.next();
            option.setValue(curval);
            select21.getElements().add(option);
        }
        return select21;
    }


    public static HttpBase genHttpTD(String colspan, String value) {
        return genHttpTD("", "", colspan, value);
    }

    public static HttpBase genHttpTH(String colspan, String value) {
        return genHttpTH("", "", colspan, value);
    }

    public static HttpBase genHttpTD() {
        return genHttpTD("", "", "", "");
    }

    public static HttpBase genHttpTH() {
        return genHttpTH("", "", "", "");
    }

    public static HttpBase genHttpTable() {
        return genHttpTable("");
    }

    public static HttpBase genHttpTable(String aclass) {
        return genHttpTable(aclass, "", "", "", "");
    }

    public static HttpBase genHttpTable(String aclass, String width,
                                        String cellspacing, String cellpadding,
                                        String border) {
        HttpBase val = new HttpBase();
        val.setTag("table");
        if (!aclass.equals("")) {
            val.getProperties().setProperty("class", aclass);
        }
        if (!width.equals("")) {
            val.getProperties().setProperty("width", width);
        }
        if (!cellspacing.equals("")) {
            val.getProperties().setProperty("cellspacing", cellspacing);
        }
        if (!cellpadding.equals("")) {
            val.getProperties().setProperty("cellpadding", cellpadding);
        }
        if (!border.equals("")) {
            val.getProperties().setProperty("border", border);
        }
        return val;
    }

    public static HttpBase genHttpSpan(String aclass, String value) {
        HttpBase span = new HttpBase();
        span.setTag("span");
        if (!aclass.equals("")) {
            span.getProperties().setProperty("class", aclass);
        }
        if (!value.equals("")) {
            span.setValue(value);
        }
        return span;
    }

    /**
     * GenHtml
     * 产生html
     * @return String    //产生的html;
     */
    public String GenHtml() {
        String val = "";
        if (tag.equals("")) {
            val = Value;
            if (elements != null) {
                Iterator itx = elements.iterator();
                while (itx.hasNext()) {
                    HttpBase tmp = (HttpBase) itx.next();
                    if (tmp != null) {
                        val += tmp.GenHtml();
                    }
                }
            }
        } else {
            val = "\n<" + tag + " ";
            if (properties != null) {
                Enumeration e = properties.propertyNames();
                while (e.hasMoreElements()) {
                    String key = UtilCommon.NVL((String) e.nextElement());
                    String aVal = UtilCommon.NVL(properties.getProperty(key));
                    val += key + "=\"" + aVal + "\" ";
                }
            }
            val += ">\n";

            val += Value;
            if (elements != null) {
                Iterator itx = elements.iterator();
                while (itx.hasNext()) {
                    val += ((HttpBase) itx.next()).GenHtml();
                }
            }
            val += "\n</" + tag + ">";
        }
        return val;
    }

    /**
     * setCompValue
     * 设置comp的value属性
     * @return boolean    //是否成功;
     */
    public static boolean setCompValue(HttpBase mybase, String ID,
                                       String val) {
        return setCompProperty(mybase, ID, "value", val);
    }

    /**
     * setCompProperty
     * 设置comp属性prop的value
     * @return boolean    //是否成功;
     */
    public static boolean setCompProperty(HttpBase mybase, String ID,
                                          String prop, String val) {
        boolean result = false;
        if (prop == null || val == null || ID == null || mybase == null) {
            return result;
        }
        if (mybase.getProperties() != null) {
            if (mybase.getProperties().getProperty("name") != null) {
                if (mybase.getProperties().getProperty("name").equals(ID)) {
                    mybase.getProperties().setProperty(prop, val);
                    if (mybase.tag.equals("select") && (prop.equals("value"))) {
                        for (int i = 0; i < mybase.getElements().size(); i++) {
                            HttpBase option = (HttpBase) mybase.getElements().
                                              get(i);
                            if (option.getProperties().getProperty("value").
                                equals(val)) {
                                option.getProperties().setProperty("selected",
                                        "true");
                            }

                        }
                    }
                    if (mybase.tag.equals("textarea") && (prop.equals("value"))) {
                        HttpBase value = HttpBase.genHttpValue(val);
                        mybase.getElements().add(value);
                    }

                    return true;
                }
            }
        }
        if (mybase.getElements() != null) {
            Iterator itx = mybase.getElements().iterator();
            while (itx.hasNext()) {
                result = setCompProperty((HttpBase) itx.next(), ID, prop,
                                         val);
                if (result == true) {
                    return result;
                }
            }
        }
        return result;
    }

    public static HttpBase getCompByName(HttpBase mybase, String ID) {
        HttpBase result = null;
        if (mybase.getProperties() != null) {
            if (mybase.getProperties().getProperty("name") != null) {
                if (mybase.getProperties().getProperty("name").equals(ID)) {
                    return mybase;
                }
            }
        }
        if (mybase.getElements() != null) {
            Iterator itx = mybase.getElements().iterator();
            while (itx.hasNext()) {
                result = getCompByName((HttpBase) itx.next(), ID);
                if (result != null) {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * setCompProperty
     * 设置comp属性prop的value
     * @return boolean    //是否成功;
     */
    public static String getCompProperty(HttpBase mybase, String ID,
                                         String prop) {
        String ret = "";
        if (mybase.getProperties() != null) {
            if (mybase.getProperties().getProperty("name") != null) {
                if (mybase.getProperties().getProperty("name").equals(ID)) {
                    ret = UtilCommon.NVL(mybase.getProperties().getProperty(
                            prop));
                    return ret;
                }
            }
        }
        if (mybase.getElements() != null) {
            Iterator itx = mybase.getElements().iterator();
            while (itx.hasNext()) {
                ret = getCompProperty((HttpBase) itx.next(), ID, prop);
                if (!ret.equals("")) {
                    return ret;
                }
            }
        }
        return ret;
    }

    public static String getCompValue(HttpBase mybase, String ID) {
        return getCompProperty(mybase, ID, "value");
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setElements(ArrayList elements) {
        this.elements = elements;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getTag() {
        return tag;
    }

    public ArrayList getElements() {
        return elements;
    }

    public String getValue() {
        return Value;
    }

    public HttpBase() {
    }

    public HttpBase(HttpBase ahttpBase) {
        for (int i = 0; i < ahttpBase.getElements().size(); i++) {
            HttpBase ele = new HttpBase((HttpBase) ahttpBase.getElements().get(
                    i));
            this.getElements().add(ele);
        }
        this.setProperties((Properties) ahttpBase.getProperties().clone());
        this.setTag(ahttpBase.getTag());
        this.setValue(ahttpBase.getValue());
    }

    public void ReplacePropVal(String oldstr, String newstr) {
        if (this.getProperties() != null) {
            while (this.getProperties().propertyNames().hasMoreElements()) {

                this.getProperties().getProperty(
                        this.getProperties().propertyNames().nextElement().
                        toString()).
                        replaceAll(oldstr, newstr);
            }
        }
        if (this.getElements() != null) {
            for (int i = 0; i < this.getElements().size(); i++) {
                HttpBase ahttpbase = (HttpBase) (this.getElements().get(i));
                ahttpbase.ReplacePropVal(oldstr, newstr);
            }

        }
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
