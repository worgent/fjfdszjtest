package YzSystem.JMain;

/**
 * <p>Title:控件基础类 </p>
 *
 * <p>Description: 控件基础类</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 描述:
 * 供查询用的控件，具有以下属性
 * 1.用来显示给用户编辑查询内容的编辑框,id为disID,caption为caption
 * 2.用来存储查询内容的编辑框,控件id为ID,普通情况和1为同个控件.
 *   特殊情况为hiddenInput控件，保存所对应的数据库seqn,或者所对应的具体的flag.
 * 历史:
 * 2005-04-25  生成代码
 */

public class EditComponent {
    private String field = ""; // 对应的数据库字段
    private String ID = ""; // field对应的界面控件id
    private String defValue = ""; // 缺省值
    private String disID = ""; // 显示给用户的界面控件id
    private String defDisValue = ""; // 显示的缺省值
    private String oper = "="; // 查询方法:=,>,<等等
    private String mode = ""; // 编辑框类型
    private String colspan = ""; // field对应的表格宽度
    private String width = ""; // field对应的表格宽度
    private boolean mustFill = false; //必填标志
    private boolean unique = false; //唯一标志
    private boolean mustInt = false; // 必须整数
    private String edtType = ""; //编辑框类型:normal:普通;date:日期选择;project:项目选择...
    private String caption = ""; //编辑框的label显示字符串
    private String fillCompArray = "[]"; // 和本字段关联的字段
    private String fillIndexArray = "[]"; // 和本字段关联的字段对应的选择结果索引


    /**
     * UtilEditComponent
     * 功能: 产生控件
     * @param atype   String  编辑框类型:normal:普通;date:日期选择;project:项目选择...
     * @param caption String  控件标题
     * @param field   String  数据库对应的field
     * @param oper    String  此控件对应的查询操作符:like,=,<=...     *
     * @param id      String  控件ID
     * @param disid   String  显示的控件ID
     * @param defvalue      String  默认值
     * @param defDisValue   String  默认显示值
     */
    public EditComponent(String atype, String caption, String field,
                         String oper, String id,
                         String disid, String defvalue, String defDisValue,
                         String width, String colspan) {
        setEdtType(atype);
        setCaption(caption);
        setOper(oper);
        setDisID(disid);
        setID(id);
        setField(field);
        setDefValue(defvalue);
        setDefDisValue(defDisValue);
        setWidth(width);
        setColspan(colspan);
    }
    public EditComponent(EditComponent acomp) {
        setEdtType(acomp.getEdtType());
        setCaption(acomp.getCaption());
        setOper(acomp.getOper());
        setDisID(acomp.getDisID());
        setID(acomp.getID());
        setField(acomp.getField());
        setDefValue(acomp.getDefValue());
        setDefDisValue(acomp.getDefDisValue());
        setWidth(acomp.getWidth());
        setColspan(acomp.getColspan());
        this.setFillCompArray(acomp.getFillCompArray());
        this.setFillIndexArray(acomp.getFillIndexArray());
        this.setMustFill(acomp.isMustFill());
        this.setMustInt(acomp.isMustInt());
        this.setDbDisField(acomp.getDbDisField());
        this.setDbField(acomp.getDbField());
        this.setMode(acomp.getMode());
        this.setSelParams(acomp.getSelParams());
        this.setUnique(acomp.isUnique());
        this.setMustFloat(acomp.isMustFloat());
    }

    public EditComponent(String caption, String field) {
        this("", caption, field, "", "", "", "", "", "", "");
    }

    public EditComponent() {
        this("", "", "", "", "", "", "", "", "", "");
    }

    public EditComponent(String atype, String caption, String field) {
        this(atype, caption, field, "", "", "", "", "", "", "");
    }

    /**
     * getValue
     * 功能: 取得控件value
     * @return   String  控件值
     */
    public String getValue() {
        String val = UtilCommon.NVL(UtilWebTools.getRequestParameter(ID));
        return val;
    }

    /**
     * getDisValue
     * 功能: 取得显示控件的value
     * @return   String  控件值
     */
    public String getDisValue() {
        return UtilCommon.NVL(UtilWebTools.getRequestParameter(disID));
    }

    /**
     * getQryValue
     * 功能: 取得产生查询用的value
     * @return   String  控件值
     */
    public String getQryValue() {
        String val = getValue();
        val = getQryValue(val);
        return val;
    }

    public String getQryValue(String avalue) {
        if (oper.equals("like")) {
            if (!avalue.equals("")) {
                avalue = "%" + avalue + "%";
            }
        }
        return avalue;
    }

    /**
     * getHttpBase()
     * 产生httpBase;
     * @return HttpBase
     */
    public HttpBase genHttpBase() throws wlglException {
        HttpBase httpBase = UtilWLGLFactory.genEdit(this);
        if (!UtilWebTools.
            getRequestParameter(ID).equals("")) {
            HttpBase.setCompValue(httpBase, disID,
                                  UtilWebTools.
                                  getRequestParameter(disID));
            HttpBase.setCompValue(httpBase, ID,
                                  UtilWebTools.
                                  getRequestParameter(ID));

        }
        return httpBase;
    }

    public void setCaption(String caption) {
        if (caption != null && !caption.equals("")) {
            this.caption = caption;
        }
    }

    public String getCaption() {
        return caption;
    }

    public void setColspan(String colspan) {
        if (colspan != null && !colspan.equals("")) {
            this.colspan = colspan;
        }
    }

    public String getColspan() {
        return colspan;
    }

    public void setDefDisValue(String defDisValue) {
        if (defDisValue != null && !defDisValue.equals("")) {
            this.defDisValue = defDisValue;
        }
    }

    public String getDefDisValue() {
        return defDisValue;
    }

    public void setDefValue(String defValue) {
        if (defValue != null && !defValue.equals("")) {
            this.defValue = defValue;
            if (defDisValue.equals("")) {
                setDefDisValue(defValue);
            }
        }
    }

    public String getDefValue() {
        return defValue;
    }

    public void setDisID(String disID) {
        if (disID != null && !disID.equals("")) {
            this.disID = disID;
        }
    }

    public String getDisID() {
        return disID;
    }

    public void setEdtType(String edtType) {
        if (edtType != null && !edtType.equals("")) {
            this.edtType = edtType;
        }
    }

    public String getEdtType() {
        return edtType;
    }

    public void setField(String field) {
        if (field != null && !field.equals("")) {
            this.field = field;
            if (this.ID.equals("")) {
                setID(field);
            }
        }
    }

    public String getField() {
        return field;
    }

    public void setID(String ID) {
        if (ID != null && !ID.equals("")) {
            this.ID = ID;
            if (disID.equals("")) {
                setDisID(ID);
            }
        }
    }

    public String getID() {
        return ID;
    }

    public void setMode(String mode) {
        if (mode != null && !mode.equals("")) {
            this.mode = mode;
        }
    }

    public String getMode() {
        return mode;
    }

    public void setMustFill(boolean mustFill) {
        this.mustFill = mustFill;
    }

    public boolean isMustFill() {
        return mustFill;
    }

    public void setOper(String oper) {
        if (caption != null && oper.equals("")) {
            this.oper = "=";
        } else {
            this.oper = oper;
        }
    }

    public String getOper() {
        return oper;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setWidth(String width) {
        if (width != null && !width.equals("")) {
            this.width = width;
        }
    }

    public void setFillCompArray(String fillCompArray) {
        this.fillCompArray = fillCompArray;
    }

    public void setFillIndexArray(String fillIndexArray) {
        this.fillIndexArray = fillIndexArray;
    }

    public void setMustInt(boolean mustInt) {
        this.mustInt = mustInt;
    }

    public void setSelParams(String selParams) {
        this.selParams = selParams;
    }

    public void setDbField(String dbField) {
        this.dbField = dbField;
    }

    public void setDbDisField(String dbDisField) {
        this.dbDisField = dbDisField;
    }

    public void setMustFloat(boolean MustFloat) {
        this.MustFloat = MustFloat;
    }

    public String getWidth() {
        return width;
    }

    public String getFillCompArray() {
        return fillCompArray;
    }

    public String getFillIndexArray() {
        return fillIndexArray;
    }

    public boolean isMustInt() {
        return mustInt;
    }

    public String getSelParams() {
        return selParams;
    }

    public String getDbField() {
        return dbField;
    }

    public String getDbDisField() {
        return dbDisField;
    }

    public boolean isMustFloat() {
        return MustFloat;
    }

    private String selParams;
    private String dbField;
    private String dbDisField;
    private boolean MustFloat;

}
