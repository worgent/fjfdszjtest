package YzSystem.JMain;

import YzSystem.J_System.BeanSystemGroupPower;
import YzSystem.J_System.BeanSystemFunctionCode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>Title:Web页面控件工厂</p>
 *
 * <p>Description: 产生各类页面控件</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-07  生成代码
 */
public class UtilWebFactory {
    /**
     * genHtmlPos
     * 功能：创立位置http内容
     * @param tradeseqn String      名字
     * @return Object   返回的valueBinding
     */
    public static String genHtmlPos(BeanSystemFunctionCode atrade) {
        return genHtmlPos(atrade, "");
    }

    public static String genHtmlPos(BeanSystemFunctionCode atrade, String otherInfo) {
        String val = "";
        if (atrade == null) {
            return val;
        }

        HttpBase table3 = new HttpBase();
        table3.setTag("table");
        table3.properties.setProperty("width", "100%");
        table3.properties.setProperty("border", "0");
        table3.properties.setProperty("cellspacing", "0");
        table3.properties.setProperty("cellpadding", "0");
        table3.properties.setProperty("class", "bborder");

        HttpBase tr4 = new HttpBase();
        tr4.setTag("tr");
        table3.getElements().add(tr4);

        HttpBase td51 = new HttpBase();
        td51.setTag("td");
        td51.getProperties().setProperty("height", "22");
        tr4.getElements().add(td51);

        HttpBase img61 = new HttpBase();
        img61.setTag("img");
        img61.getProperties().setProperty("src", "../images/loca.gif");
        img61.getProperties().setProperty("width", "16");
        img61.getProperties().setProperty("height", "16");
        img61.getProperties().setProperty("hspace", "3");
        img61.getProperties().setProperty("align", "absmiddle");
        td51.getElements().add(img61);

        String curVal = "";
        BeanSystemFunctionCode curTrade = atrade.getFather();
        while (curTrade != null) {
            curVal = curTrade.getTheName() + "&gt" + curVal;
            curTrade = curTrade.getFather();
        }

        HttpBase val62 = new HttpBase();
        String posInfo = "位置:&nbsp;" + curVal + atrade.getTheName();
        if (!otherInfo.equals("")) {
            posInfo += "-" + otherInfo;
        }
        val62.setValue(posInfo);
        td51.getElements().add(val62);

        HttpBase td52 = new HttpBase();
        td52.setTag("td");
        td52.getProperties().setProperty("align", "right");
        td52.getProperties().setProperty("width", "30");
        tr4.getElements().add(td52);

        HttpBase a63 = new HttpBase();
        a63.setTag("a");
        a63.getProperties().setProperty("heref", "#");
        a63.getProperties().setProperty("onClick", "self.history.back();");
        td52.getElements().add(a63);

        HttpBase img7 = new HttpBase();
        img7.setTag("img");
        img7.getProperties().setProperty("src", "../images/back2.gif");
        img7.getProperties().setProperty("hspace", "3");
        img7.getProperties().setProperty("border", "0");
        img7.getProperties().setProperty("width", "16");
        img7.getProperties().setProperty("height", "16");
        a63.getElements().add(img7);
        val = table3.GenHtml();
        return val;

    }

    
    public static HttpBase genHttpBaseTextArea(EditComponent comp, String value) {
        String name=comp.getCaption();
        String ID=comp.getID();
        String width = comp.getWidth();
        String mode = comp.getMode();
        String colspan= comp.getColspan();
        boolean mustFill= comp.isMustFill();
        HttpBase val = new HttpBase();

        HttpBase td1 = new HttpBase();
        td1.setTag("td");
        val.getElements().add(td1);

        HttpBase div11 = new HttpBase();
        div11.setTag("div");
        div11.getProperties().setProperty("align", "right");
        td1.getElements().add(div11);

        HttpBase val111 = new HttpBase();
        val111.setValue(name);
        div11.getElements().add(val111);

        HttpBase td2 = new HttpBase();
        td2.setTag("td");
        val.getElements().add(td2);

        HttpBase textarea = new HttpBase();
        textarea.setTag("textarea");
        textarea.getProperties().setProperty("name", ID);
        textarea.getProperties().setProperty("rows", "12");
        textarea.getProperties().setProperty("cols", "80");
        td2.getElements().add(textarea);

        if (mode.equals("disabled")) {
            textarea.getProperties().setProperty("disabled", "true");
        }
        if (mode.equals("readonly")) {
            textarea.getProperties().setProperty("readonly", "true");
        }
        if (!colspan.equals("")) {
            td2.getProperties().setProperty("colspan", colspan);
        }
        if (mustFill){
            HttpBase must=HttpBase.genHttpValue("*");
            td2.getElements().add(must);
        }

        return val;
    }
    /**
     * genCombobox
     * 功能：产生下拉列表.
     * @return HttpBase            返回搜索按钮
     */

    public static HttpBase genHttpBaseCombobox(EditComponent comp, ArrayList dis,ArrayList value) {
        String name=comp.getCaption();
        String ID=comp.getID();
        String width = comp.getWidth();
        String mode = comp.getMode();
        String colspan= comp.getColspan();
        boolean mustFill= comp.isMustFill();
        HttpBase val = new HttpBase();

        HttpBase td1 = new HttpBase();
        td1.setTag("td");
        val.getElements().add(td1);

        HttpBase div11 = new HttpBase();
        div11.setTag("div");
        div11.getProperties().setProperty("align", "right");
        td1.getElements().add(div11);

        HttpBase val111 = new HttpBase();
        val111.setValue(name);
        div11.getElements().add(val111);

        HttpBase td2 = new HttpBase();
        td2.setTag("td");
        val.getElements().add(td2);

        HttpBase select21 = new HttpBase();
        select21.setTag("select");
        select21.getProperties().setProperty("name", ID);
        td2.getElements().add(select21);

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
        if (mode.equals("disabled")) {
            select21.getProperties().setProperty("disabled", "true");
        }
        if (mode.equals("readonly")) {
            select21.getProperties().setProperty("readonly", "true");
        }
        if (!colspan.equals("")) {
            td2.getProperties().setProperty("colspan", colspan);
        }
        if (mustFill){
            HttpBase must=HttpBase.genHttpValue("*");
            td2.getElements().add(must);
        }

        return val;
    }

    /**
     * genEdit
     * 功能：产生编辑框按钮.
     * @param comp EditComponent 控件
     * @return HttpBase            返回控件httpBase内容
     */
    public static HttpBase genHttpBaseEdit(EditComponent comp) {

        String edtType=comp.getEdtType();
        String name=comp.getCaption();
        String ID= comp.getID();
        String width=comp.getWidth();
        String oper=comp.getOper();
        String mode=comp.getMode();
        String colspan=comp.getColspan();
        boolean mustFill=comp.isMustFill();
        HttpBase val = new HttpBase();

        HttpBase td1 = new HttpBase();
        td1.setTag("td");
        val.getElements().add(td1);

        HttpBase div11 = new HttpBase();
        div11.setTag("div");
        div11.getProperties().setProperty("align", "right");
        td1.getElements().add(div11);

        HttpBase val111 = new HttpBase();
        val111.setValue(name);
        div11.getElements().add(val111);

        HttpBase td2 = new HttpBase();
        td2.setTag("td");
        val.getElements().add(td2);

        HttpBase input21 = new HttpBase();
        input21.setTag("input");
        input21.getProperties().setProperty("type", "text");
        input21.getProperties().setProperty("name", ID);
        input21.getProperties().setProperty("size", "12");
        input21.getProperties().setProperty("value", "");
        if (mode.equals("readonly")) {
            input21.getProperties().setProperty("readonly", "true");
        }
        if (mode.equals("disabled")) {
            input21.getProperties().setProperty("disabled", "disabled");
        }
        if (!width.equals("")) {
            input21.getProperties().setProperty("size", width);
        }

        if (!colspan.equals("")) {
            td2.getProperties().setProperty("colspan", colspan);
        }
        td2.getElements().add(input21);
        if (mustFill){
            HttpBase must=HttpBase.genHttpValue("*");
            td2.getElements().add(must);
        }

        return val;
    }

    /**
     * genBtnFind
     * 功能：产生查找按钮.
     * @return HttpBase            返回搜索按钮
     */
    public static HttpBase genHttpBaseBtnFind(String ID) {
        return genHttpBaseBtn(ID,"查找","doFind()");
    }

    public static HttpBase genHttpBaseBtnSave(String ID) {
        return genHttpBaseBtn(ID,"保存","doSave()");
    }
    public static HttpBase genHttpBaseBtnView(String ID) {
        return genHttpBaseBtn(ID,"查看","doView()");
    }

    public static HttpBase genHttpBaseBtnPrint(String ID) {
        return genHttpBaseBtn(ID,"打印","doPrint()");
    }
    public static HttpBase genHttpBaseBtnEdit(String ID) {
        return genHttpBaseBtn(ID,"修改","doEdit()");
    }
    public static HttpBase genHttpBaseBtnAdd(String ID) {
        return genHttpBaseBtn(ID,"增加","doAdd()");
    }
    public static HttpBase genHttpBaseBtnDel(String ID) {
        return genHttpBaseBtn(ID,"删除","doDel()");
    }
    public static HttpBase genHttpBaseBtnCancel(String ID) {
        return genHttpBaseBtn(ID,"取消","history.go(-1)");
    }
    
    
    public static HttpBase genHttpBaseBtnClear(String ID) {
        return genHttpBaseBtn(ID,"清除","doClear()");
    }
    
    

    public static HttpBase  genHttpBaseBtn(String ID,String caption,String function){
        HttpBase val = new HttpBase();

        HttpBase input21 = new HttpBase();
        input21.setTag("input");
        input21.getProperties().setProperty("type", "button");
        input21.getProperties().setProperty("name", ID);
        input21.getProperties().setProperty("value", caption);
        input21.getProperties().setProperty("onClick",
                                            "javascript:return "+function+";");

        val.getElements().add(input21);
        return val;
    }

    /**
     * genAhrefEdit
     * 功能：产生有超级连接的选择按钮.]
     * @param  comp   EditComponent 控件
     * @param  fName  String       js函数名
     * @return HttpBase            返回搜索按钮
     */
    public static HttpBase genHttpBaseAhrefEdit(EditComponent comp,String fName) {
        String ID=comp.getID();
        String DisID=comp.getDisID();
        String name=comp.getCaption();
        String width=comp.getWidth();
        String oper=comp.getOper();
        String mode=comp.getMode();
        String colspan=comp.getColspan();
        boolean mustFill=comp.isMustFill();
        HttpBase val = new HttpBase();

        HttpBase td1 = new HttpBase();
        td1.setTag("td");
        val.getElements().add(td1);

        HttpBase div11 = new HttpBase();
        div11.setTag("div");
        div11.getProperties().setProperty("align", "right");
        td1.getElements().add(div11);

        HttpBase a111 = new HttpBase();
        a111.setTag("a");

            a111.getProperties().setProperty("href",
                                             "Javascript:" + fName +";");
        div11.getElements().add(a111);

        HttpBase val1111 = new HttpBase();
        val1111.setValue(name);
        a111.getElements().add(val1111);

        HttpBase td2 = new HttpBase();
        td2.setTag("td");
        val.getElements().add(td2);

        HttpBase input21 = new HttpBase();
        input21.setTag("input");
        if (!DisID.equals(ID)) {
            input21.getProperties().setProperty("type", "hidden");
        }
        else{
            input21.getProperties().setProperty("type", "text");
        }
        input21.getProperties().setProperty("name", ID);
        input21.getProperties().setProperty("size", width);
        input21.getProperties().setProperty("value", "");
        td2.getElements().add(input21);
        if (!DisID.equals(ID)) {
            HttpBase input22 = new HttpBase();
            input22.setTag("input");
            input22.getProperties().setProperty("type", "text");
            input22.getProperties().setProperty("name", DisID);
            input22.getProperties().setProperty("size", width);
            input22.getProperties().setProperty("value", "");
            td2.getElements().add(input22);
            if (mode.equals("disabled")) {
                if (!DisID.equals(ID)) {
                    input22.getProperties().setProperty("disabled", "disabled");
                }
            }
            if (mode.equals("readonly")) {
                if (!DisID.equals(ID)) {
                    input22.getProperties().setProperty("readonly", "true");
                    input22.getProperties().setProperty("value", "＝请选择＝");
                }
            }
        }
        if (mode.equals("disabled")) {
            input21.getProperties().setProperty("disabled", "disabled");
        }
        if (mode.equals("readonly")) {
            input21.getProperties().setProperty("readonly", "true");
        }
        if (!colspan.equals("")) {
            td2.getProperties().setProperty("colspan", colspan);
        }
        if (mustFill){
            HttpBase must=HttpBase.genHttpValue("*");
            td2.getElements().add(must);
        }
        return val;
    }

    /**
     * genDateSelect
     * 功能：产生日期选择框.
     * @param name String          显示名字
     * @param ID   String          ID
     * @return HttpBase            返回选择框
     */
    public static HttpBase genHttpBaseDateSelect(String name, String ID,boolean mustFill) {
        return genHttpBaseDateSelect(name,ID,mustFill,"");
    }
    public static HttpBase genHttpBaseDateSelect(String name, String ID,boolean mustFill,String colwidth) {
        HttpBase val = new HttpBase();

        HttpBase td1 = new HttpBase();
        td1.setTag("td");
        val.getElements().add(td1);

        HttpBase div11 = new HttpBase();
        div11.setTag("div");
        div11.getProperties().setProperty("align", "right");
        td1.getElements().add(div11);

        HttpBase a111 = new HttpBase();
        a111.setTag("a");
        a111.getProperties().setProperty("href",
                                         "Javascript:selectDate('" + ID + "');");
        div11.getElements().add(a111);

        HttpBase val1111 = new HttpBase();
        val1111.setValue(name);
        a111.getElements().add(val1111);

        HttpBase td2 = new HttpBase();
        td2.setTag("td");
        val.getElements().add(td2);

        HttpBase input21 = new HttpBase();
        input21.setTag("input");
        input21.getProperties().setProperty("type", "text");
        input21.getProperties().setProperty("name", ID);
        input21.getProperties().setProperty("size", "12");
        input21.getProperties().setProperty("value", "");
        td2.getElements().add(input21);
        if (!colwidth.equals("")) {
            td2.getProperties().setProperty("colspan", colwidth);
        }

        if (mustFill){
            HttpBase must=HttpBase.genHttpValue("*");
            td2.getElements().add(must);
        }

        return val;
    }

    /**
     * genDate2Select
     * 功能：产生日期期间选择框.
     * @param name String          显示名字
     * @param ID1   String          ID1
     * @param ID2   String          ID2
     * @return HttpBase            返回选择框
     */
    public static HttpBase genHttpBaseDateSelect(String name, String ID1, String ID2) {
        HttpBase val = new HttpBase();

        HttpBase td1 = new HttpBase();
        td1.setTag("td");
        val.getElements().add(td1);

        HttpBase div11 = new HttpBase();
        div11.setTag("div");
        div11.getProperties().setProperty("align", "right");
        td1.getElements().add(div11);

        HttpBase a111 = new HttpBase();
        a111.setTag("a");
        a111.getProperties().setProperty("href",
                                         "Javascript:selectDate('" + ID1 +
                                         "');");
        div11.getElements().add(a111);

        HttpBase val1111 = new HttpBase();
        val1111.setValue(name);
        a111.getElements().add(val1111);

        HttpBase td2 = new HttpBase();
        td2.setTag("td");
        val.getElements().add(td2);

        HttpBase input21 = new HttpBase();
        input21.setTag("input");
        input21.getProperties().setProperty("type", "text");
        input21.getProperties().setProperty("name", ID1);
        input21.getProperties().setProperty("size", "12");
        input21.getProperties().setProperty("value", "");
        td2.getElements().add(input21);

        HttpBase td3 = new HttpBase();
        td3.setTag("td");
        val.getElements().add(td3);

        HttpBase div31 = new HttpBase();
        div31.setTag("div");
        div31.getProperties().setProperty("align", "right");
        td3.getElements().add(div31);

        HttpBase a311 = new HttpBase();
        a311.setTag("a");
        a311.getProperties().setProperty("href",
                                         "Javascript:selectDate('" + ID2 +
                                         "');");
        div31.getElements().add(a311);

        HttpBase val3111 = new HttpBase();
        val3111.setValue("至");
        a311.getElements().add(val3111);

        HttpBase td4 = new HttpBase();
        td4.setTag("td");
        val.getElements().add(td4);

        HttpBase input41 = new HttpBase();
        input41.setTag("input");
        input41.getProperties().setProperty("type", "text");
        input41.getProperties().setProperty("name", ID2);
        input41.getProperties().setProperty("size", "12");
        input41.getProperties().setProperty("value", "");
        td4.getElements().add(input41);

        return val;
    }

    public HttpBase genHttpBaseTable() {
        HttpBase val = new HttpBase();
        val.setTag("table");
        val.properties.setProperty("type", "button");
        val.properties.setProperty("name", "btnSearch");
        val.properties.setProperty("value", "查询");
        val.properties.setProperty("onclick", "Javascript:doSearch();");
        return val;
    }

    public UtilWebFactory() {
    }
}
