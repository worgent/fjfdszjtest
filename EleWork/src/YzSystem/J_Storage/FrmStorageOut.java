package YzSystem.J_Storage;

import java.util.ArrayList;
import java.util.Properties;

import YzSystem.JMain.EditComponent;
import YzSystem.JMain.FrmFunctionBase;
import YzSystem.JMain.HttpBase;
import YzSystem.JMain.wlglException;

public class FrmStorageOut  extends FrmFunctionBase{
    public String billType = "2004";
    public String pre = "BJCK";
    public void init() {
    }

    //生成的页面流程控制
    public FrmStorageOut() throws wlglException {
        super();
        init();
        // 设置页面标题
        Table = "tbstorageout";
        seqnField = "BillNo";
        DetailTable = "tbstorageoutitem";
        DetailSeqnField = "RowNo";
        pageType = "masterDetail";
        if (!inDetailMode) {
//=========================查询设置==============================================
            if (mode.equals("query")) {
                // 设置查询组件
                setQueryComp();
                // 设置查询信息
                setQueryInfo();
            }
//=========================添加设置==============================================
            // 设置添加组件
            if (mode.equals("add")) {
                setUpdateComp();
                setAddInfo();
            }
//=========================修改设置==============================================
            // 设置修改组件
            if (mode.equals("edit")) {
                setUpdateComp();
                setUpdateInfo();
                setDetailQueryInfo();
            }
        } else {
//=========================添加设置==============================================
            //Table = "tbstorageapplyitem";
            //seqnField = "RowNo";

            setDetailEditComp();
            // 设置添加组件
            if (mode.equals("add")) {
                setDetailAddInfo();
            }
//=========================修改设置==============================================
            // 设置修改组件
            if (mode.equals("edit")) {
                setDetailUpdateInfo();
            }

        }
    }
    
    /**
     * setQueryComp
     * 设置需要的主表查询组件
     */
    private void setQueryComp() throws wlglException {
        EditComponent e1 = new EditComponent("",
                                             "单据号",
                                             "a.BillNo",
                                             "like",
                                             "QBillNo",
                                             "",
                                             "",
                                             "",
                                             "",
                                             "");
        //e1.setMode("readonly");
        EditComponent e2 = new EditComponent("date",
                                             "录入日期",
                                             "a.TheDate",
                                             ">=",
                                             "Qbegindate",
                                             "",
                                             "",
                                             "",
                                             "",
                                             "");
        EditComponent e3 = new EditComponent("date",
                                             "至",
                                             "a.TheDate",
                                             "<=",
                                             "Qenddate",
                                             "",
                                             "",
                                             "",
                                             "",
                                             "");

        // 设置界面第1行内容

        HttpBase tr1 = HttpBase.genHttpTR("btd");
        tr1.getElements().add(e1.genHttpBase());
        tr1.getElements().add(e2.genHttpBase());
        tr1.getElements().add(e3.genHttpBase());
        qryComps.clear();
        qryComps.add(e1);
        qryComps.add(e2);
        qryComps.add(e3);

        qryPanels.clear();
        qryPanels.add(tr1);
    }

    /**
     * setQueryInfo
     * 设置查询信息
     */
    private void setQueryInfo() {
        qry_selectSQL ="select 	a.BillNo, a.TheDate, a.StorageCode, b.theCode StorageName,a.ApplyerCode,c.theName ApplyerName," +
        		"\n"+" a.BillSortCode,d.BillName BillSortName,a.Remark" +
        		"\n"+"from tbstorageout a left outer join tbdatumstorage b on b.TheCode=a.StorageCode" +
        		"\n"+"left outer join tbdatumemployee c on a.ApplyerCode=c.TheCode" +
        		"\n"+"left outer join  vbillsort d on a.BillSortCode=d.BillSort";
        
        qry_whereSQL="a.BillSortCode='"+billType+"'";

        qry_orderSQL = "order by a.BillNo desc ";

        // 设置列属性(类型，标题，宽度，数据列)
        columnsInfo.clear();
        Properties prop1 = new Properties();
        columnsInfo.add(prop1);
        prop1.setProperty("type", "radio");
        prop1.setProperty("title", "选择");
        prop1.setProperty("width", "5%");
        prop1.setProperty("data", "0");

        Properties prop2 = new Properties();
        prop2.setProperty("title", "单据号");
        prop2.setProperty("data", "0");
        columnsInfo.add(prop2);

        Properties prop3 = new Properties();
        prop3.setProperty("title", "单据日期");
        prop3.setProperty("data", "1");
        columnsInfo.add(prop3);

        Properties prop4 = new Properties();
        prop4.setProperty("title", "仓库");
        prop4.setProperty("data", "3");
        columnsInfo.add(prop4);

        Properties prop5 = new Properties();
        prop5.setProperty("title", "领料人");
        prop5.setProperty("data", "5");
        columnsInfo.add(prop5);
        
        Properties prop8 = new Properties();
        prop8.setProperty("title", "备注");
        prop8.setProperty("data", "8");
        columnsInfo.add(prop8);
    }
    
    /**
     * setAddInfo
     * 设置需要的编辑默认值
     */
    private void setAddInfo() {
        ArrayList ret = new ArrayList();
        //Table = "tssystemsubjectrecord";
        // 设置列属性(类型，标题，宽度，数据列)
        ret.clear();
        
        
        //创建人
        Properties prop1 = new Properties();
        ret.add(prop1);
        prop1.setProperty("type", "employeecode");
        prop1.setProperty("field", "Creater");
        //执行人
        Properties prop2 = new Properties();
        ret.add(prop2);
        prop2.setProperty("type", "employeecode");
        prop2.setProperty("field", "Stater");
        //编辑人
        Properties prop3 = new Properties();
        ret.add(prop3);
        prop3.setProperty("type", "employeecode");
        prop3.setProperty("field", "Editer");

  
        // 创建时间
        Properties prop4 = new Properties();
        ret.add(prop4);
        prop4.setProperty("type", "time");
        prop4.setProperty("field", "CreateTime");
        // 执行时间
        Properties prop5 = new Properties();
        ret.add(prop5);
        prop5.setProperty("type", "time");
        prop5.setProperty("field", "StateTime");
        // 编辑时间
        Properties prop6 = new Properties();
        ret.add(prop6);
        prop6.setProperty("type", "time");
        prop6.setProperty("field", "EditeTime");
        
        // 单据号
        Properties prop7 = new Properties();
        ret.add(prop7);
        prop7.setProperty("type", "billno");
        prop7.setProperty("field", "billno");
        prop7.setProperty("pre", pre);
        
        //单据类别
        Properties prop8 = new Properties();
        ret.add(prop8);
        prop8.setProperty("type", "data");
        prop8.setProperty("field", "BillSortCode");
        prop8.setProperty("value", billType);

        addExtraColumns.addAll(ret);
    }

    /**
     * setAddComp
     * 设置需要的编辑组件
     */
    protected void setUpdateComp() throws wlglException {
        editPanels.clear();
        EditComponent ec1 = new EditComponent();
        ec1.setCaption("单据号");
        ec1.setField("billno");
        ec1.setUnique(true);
        ec1.setMode("readonly");
        ec1.setDefValue("系统生成");
        ec1.setMustFill(true); 


        EditComponent ec2 = new EditComponent();
        ec2.setEdtType("date");
        ec2.setCaption("日期");
        ec2.setField("TheDate");
        
        EditComponent ec3 = new EditComponent();
        ec3.setEdtType("Storage");
        ec3.setField("StorageCode");
        ec3.setCaption("仓库");
        ec3.setDisID("StorageName");
        ec3.setDefDisValue("＝请选择＝");
        ec3.setMode("readonly");
        ec3.setMustFill(true);
        
        
        EditComponent ec4 = new EditComponent();
        ec4.setEdtType("Employee");
        ec4.setField("ApplyerCode");
        ec4.setCaption("领料人");
        ec4.setDisID("ApplyerName");
        ec4.setDefDisValue("＝请选择＝");
        ec4.setMode("readonly");
        
   
        EditComponent ec5 = new EditComponent();
        ec5.setCaption("备注");
        ec5.setField("Remark");
        ec5.setColspan("3");
        

        
        // 设置界面每行内容
        HttpBase tre1 = HttpBase.genHttpTR("btd");
        tre1.getElements().add(ec1.genHttpBase());
        tre1.getElements().add(ec2.genHttpBase());
        tre1.getElements().add(ec3.genHttpBase());

        
        HttpBase tre2 = HttpBase.genHttpTR("btd");
        tre2.getElements().add(ec4.genHttpBase());
        tre2.getElements().add(ec5.genHttpBase());

        // 增加到编辑控件列表
        editComps.clear();
        editComps.add(ec1);
        editComps.add(ec2);
        editComps.add(ec3);
        editComps.add(ec4);
        editComps.add(ec5);


        // 增加到编辑栏列表
        editPanels.clear();
        editPanels.add(tre1);
        editPanels.add(tre2);
    }
    
    /**
     * setAddInfo
     * 设置需要的编辑组件
     */
    protected void setUpdateInfo() {
        ArrayList ret = new ArrayList();
        
        edt_selectSQL ="select 	a.BillNo, a.TheDate, a.StorageCode, b.theCode StorageName,a.ApplyerCode,c.theName ApplyerName," +
		"\n"+" a.BillSortCode,d.BillName BillSortName,a.Remark" +
		"\n"+"from tbstorageout a left outer join tbdatumstorage b on b.TheCode=a.StorageCode" +
		"\n"+"left outer join tbdatumemployee c on a.ApplyerCode=c.TheCode" +
		"\n"+"left outer join  vbillsort d on a.BillSortCode=d.BillSort";
        
        edt_whereSQL="a.BillSortCode='"+billType+"'";
        
        editColumns.setProperty("0", "0");
        editColumns.setProperty("1", "1");
        editColumns.setProperty("2", "2");
        editColumns.setProperty("2d", "3");
        editColumns.setProperty("3", "4");
        editColumns.setProperty("3d", "5");
        editColumns.setProperty("4", "8");
     
        // 设置附加的修改列属性(类型，标题，宽度，数据列)
        ret.clear();


        //执行人
        Properties prop1 = new Properties();
        ret.add(prop1);
        prop1.setProperty("type", "employeecode");
        prop1.setProperty("field", "Stater");
        //编辑人
        Properties prop2 = new Properties();
        ret.add(prop2);
        prop2.setProperty("type", "employeecode");
        prop2.setProperty("field", "Editer");

        // 执行时间
        Properties prop3 = new Properties();
        ret.add(prop3);
        prop3.setProperty("type", "time");
        prop3.setProperty("field", "StateTime");
        // 编辑时间
        Properties prop4 = new Properties();
        ret.add(prop4);
        prop4.setProperty("type", "time");
        prop4.setProperty("field", "EditeTime");

        addExtraColumns.addAll(ret);
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------
    //明细表信息处理
    //-----------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * setDetailInfo
     * 设置详表信息
     */
    private void setDetailQueryInfo() {
        // 详表参数
        detailSelectSQL = "select a.BillNo, a.RowNo, a.ProductCode, b.TheName ProductName,a.ProductSort,c.TheName ProductTypeName," +
        		"\n"+" d.TheName ModelName,e.TheName SpecName,f.TheName UnitName, a.Number, a.Price, a.TotalMoney, a.remarkItem " +
        		"\n"+"from tbstorageoutitem a  left outer join tbdatumproduct b on a.ProductCode=b.TheCode and a.ProductSort=b.ProductTypeCode"+
           		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
           		"\n"+"where b.Flag='ProductTypecode') c on c.RowNo=a.ProductSort"+
           		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
           		"\n"+"where b.Flag='ModelCode') d on d.RowNo=b.ModelCode" +
           		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
           		"\n"+"where b.Flag='SpecCode') e on e.RowNo=b.SpecCode" +
           		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
           		"\n"+"where b.Flag='UnitCode') f on f.RowNo=b.UnitCode";
        

        detailOrderSQL = " order by a.RowNo"; // 详表排序

        // 设置列属性(类型，标题，宽度，数据列)
        detailColumnsInfo.clear();
        Properties prop1 = new Properties();
        prop1.setProperty("title", "选择");
        prop1.setProperty("type", "radio");
        prop1.setProperty("data", "1");
        detailColumnsInfo.add(prop1);
        
        Properties prop2 = new Properties();
        prop2.setProperty("title", "备件编号");
        prop2.setProperty("data", "2");
        detailColumnsInfo.add(prop2);    
        
        Properties prop3 = new Properties();
        prop3.setProperty("title", "备件");
        prop3.setProperty("data", "3");
        detailColumnsInfo.add(prop3);

        Properties prop4 = new Properties();
        prop4.setProperty("title", "备件类别");
        prop4.setProperty("data", "5");
        detailColumnsInfo.add(prop4);

        Properties prop5 = new Properties();
        prop5.setProperty("title", "型号");
        prop5.setProperty("data", "6");
        detailColumnsInfo.add(prop5);

        Properties prop6 = new Properties();
        prop6.setProperty("title", "规格");
        prop6.setProperty("data", "7");
        detailColumnsInfo.add(prop6);
        
        Properties prop7 = new Properties();
        prop7.setProperty("title", "单位");
        prop7.setProperty("data", "8");
        detailColumnsInfo.add(prop7);
        
        Properties prop8 = new Properties();
        prop8.setProperty("title", "数量");
        prop8.setProperty("data", "9");
        detailColumnsInfo.add(prop8);
      
        Properties prop9 = new Properties();
        prop9.setProperty("title", "总额");
        prop9.setProperty("data", "11");
        detailColumnsInfo.add(prop9);

        Properties prop10 = new Properties();
        prop10.setProperty("title", "备注");
        prop10.setProperty("data", "12");
        detailColumnsInfo.add(prop10);
    }


    /**
     * setDetailAddComp
     * 设置需要的编辑组件
     */
    private void setDetailEditComp() throws wlglException {
    	
        EditComponent ec1 = new EditComponent();
        ec1.setEdtType("Product");
        ec1.setField("ProductCode");
        ec1.setCaption("设备名称");
        ec1.setDisID("ProductName");
        ec1.setDefDisValue("＝请选择＝");
        ec1.setMode("readonly");
        ec1.setMustFill(true);
        ec1.setFillCompArray("['ProductSort','ModelName','SpecName','UnitName']");
        ec1.setFillIndexArray("[1,9,5,7]");

        EditComponent ec2 = new EditComponent();
        ec2.setEdtType("ProductTypeCode ");
        ec2.setField("ProductSort");
        ec2.setDisID("ProductTypeName");
        ec2.setCaption("设备类别");
        ec2.setMode("readonly");
        
        
        EditComponent ec3 = new EditComponent();
        ec3.setID("ModelName");
        ec3.setCaption("型号");
        ec3.setMode("readonly");
        
        EditComponent ec4 = new EditComponent();
        ec4.setID("SpecName");
        ec4.setCaption("规格");
        ec4.setMode("readonly");

        EditComponent ec5 = new EditComponent();
        ec5.setID("UnitName");
        ec5.setCaption("单位");
        ec5.setMode("readonly");
        
        EditComponent ec6 = new EditComponent();
        ec6.setField("Number");
        ec6.setCaption("数量");
        ec6.setMustFill(true);
        ec6.setMustInt(true);
        ec6.setDefValue("0");
        
        EditComponent ec7 = new EditComponent();
        ec7.setField("Price");
        ec7.setCaption("单价");
        ec7.setMustFill(true);
        ec7.setMustFloat(true);
        ec7.setDefValue("0.00");
        
        EditComponent ec8 = new EditComponent();
        ec8.setField("TotalMoney");
        ec8.setCaption("总额");
        ec8.setMustFill(true);
        ec8.setMustFloat(true);
        ec8.setDefValue("0.00");

        EditComponent ec9 = new EditComponent();
        ec9.setField("remarkItem");
        ec9.setCaption("备注");

        HttpBase tre1 = HttpBase.genHttpTR("btd");
        tre1.getElements().add(ec1.genHttpBase());
        tre1.getElements().add(ec2.genHttpBase());
        tre1.getElements().add(ec3.genHttpBase());

        HttpBase tre2 = HttpBase.genHttpTR("btd");
        tre2.getElements().add(ec4.genHttpBase());
        tre2.getElements().add(ec5.genHttpBase());
        tre2.getElements().add(ec6.genHttpBase());
        
        HttpBase tre3 = HttpBase.genHttpTR("btd");
        tre3.getElements().add(ec7.genHttpBase());
        tre3.getElements().add(ec8.genHttpBase());     
        tre3.getElements().add(ec9.genHttpBase());   
        

        // 增加到编辑控件列表
        editComps.clear();
        editComps.add(ec1);
        editComps.add(ec2);
        editComps.add(ec3);
        editComps.add(ec4);
        editComps.add(ec5);
        editComps.add(ec6);
        editComps.add(ec7);
        editComps.add(ec8);
        editComps.add(ec9);

        // 增加到编辑栏列表
        editPanels.clear();
        editPanels.add(tre1);
        editPanels.add(tre2);
        editPanels.add(tre3);
    }

    /**
     * setAddInfo
     * 设置需要的编辑组件
     */
    private void setDetailUpdateInfo() {
        ArrayList ret = new ArrayList();
        detailSelectSQL =  "select a.BillNo, a.RowNo, a.ProductCode, b.TheName ProductName,a.ProductSort,c.TheName ProductTypeName," +
		"\n"+" d.TheName ModelName,e.TheName SpecName,f.TheName UnitName, a.Number, a.Price, a.TotalMoney, a.remarkItem " +
		"\n"+"from tbstorageoutItem a  left outer join tbdatumproduct b on a.ProductCode=b.TheCode and a.ProductSort=b.ProductTypeCode"+
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='ProductTypecode') c on c.RowNo=a.ProductSort"+
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='ModelCode') d on d.RowNo=b.ModelCode" +
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='SpecCode') e on e.RowNo=b.SpecCode" +
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='UnitCode') f on f.RowNo=b.UnitCode";
        
        // 设置编辑框对应的列
        editColumns.setProperty("0", "2");
        editColumns.setProperty("0d", "3");
        editColumns.setProperty("1", "4");
        editColumns.setProperty("1d", "5");
        editColumns.setProperty("2", "6");
        editColumns.setProperty("3", "7");
        editColumns.setProperty("4", "8");
        editColumns.setProperty("5", "9");
        editColumns.setProperty("6", "10");
        editColumns.setProperty("7", "11");
        editColumns.setProperty("8", "12");
        // 设置附加的修改列属性(类型，标题，宽度，数据列)
        ret.clear();
        addExtraColumns.addAll(ret);
    }
    /**
     * setAddInfo
     * 设置需要的编辑组件
     */
    private void setDetailAddInfo() {
        ArrayList ret = new ArrayList();
        // 设置列属性(类型，标题，宽度，数据列)
        ret.clear();
        Properties prop1 = new Properties();
        ret.add(prop1);
        prop1.setProperty("type", "DetailSeqn");
        prop1.setProperty("mainkey",seqnField);
        prop1.setProperty("field", "RowNo");
        
        Properties prop2 = new Properties();
        ret.add(prop2);
        prop2.setProperty("type", "seqn");
        prop2.setProperty("field", "BillNo");
        
        addExtraColumns.addAll(ret);
    }


}
