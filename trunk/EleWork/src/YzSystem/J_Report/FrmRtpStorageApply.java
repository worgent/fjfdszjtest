package YzSystem.J_Report;

import YzSystem.JMain.*;
import YzSystem.J_System.BeanLogin;

import java.util.ArrayList;
import java.util.Properties;

public class FrmRtpStorageApply extends FrmFunctionBase{
    public FrmRtpStorageApply() throws wlglException {
    	   super();
    	   setQueryComp();
           // 设置查询SQL
    	   setQueryInfo();
           
    }
    
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
    
    private void setQueryInfo() {
    	
    	qry_selectSQL ="select a.TheSeqn, a.BillNo, a.TheDate, a.ApplyDepCode, c.TheName ApplyDepName,a.ApplyerCode,b.TheName ApplyerName, " +
   		"\n"+"a.ApplyerDate, a.Reason, a.HandleCode,d.TheName HandleMark, a.MarkerCode,j.TheName MarkerName,a.BillSortCode, " +
   		"\n"+"e.RowNo, e.ProductCode,k.TheName ProductName,f.TheName SpecName,h.TheName UnitName,e.Number, e.Price, e.TotalMoney,e.RemarkItem" +
   		"\n"+"from tbstorageapply a left outer join tbdatumemployee b on a.ApplyerCode=b.TheCode" +
   		"\n"+"left outer join tbdatumdepartment c on  a.ApplyDepCode=c.TheCode" +
   		"\n"+"left outer join tbdatumemployee d on a.HandleCode=d.TheCode " +
   		"\n"+"left outer join tbdatumemployee j on a.MarkerCode=j.TheCode" +
   		"\n"+"left outer join tbstorageapplyitem e on a.BillNo=e.BillNo" +
   		"\n"+"left outer join tbdatumproduct k on e.ProductCode=k.TheCode " +
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='SpecCode') f on f.RowNo=k.SpecCode" +
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='UnitCode') h on h.RowNo=k.UnitCode";

        qry_orderSQL = "order by a.TheSeqn desc,e.RowNo ";

        // 设置列属性(类型，标题，宽度，数据列)
        columnsInfo.clear();
        Properties prop2 = new Properties();
        prop2.setProperty("title", "单据号");
        prop2.setProperty("data", "1");
        columnsInfo.add(prop2);

        Properties prop3 = new Properties();
        prop3.setProperty("title", "单据日期");
        prop3.setProperty("data", "2");
        columnsInfo.add(prop3);

        Properties prop4 = new Properties();
        prop4.setProperty("title", "申请部门");
        prop4.setProperty("data", "4");
        columnsInfo.add(prop4);

        Properties prop5 = new Properties();
        prop5.setProperty("title", "申请人");
        prop5.setProperty("data", "6");
        columnsInfo.add(prop5);
       

        Properties prop7 = new Properties();
        prop7.setProperty("title", "申请日期");
        prop7.setProperty("data", "7");
        columnsInfo.add(prop7);
        
        Properties prop6 = new Properties();
        prop6.setProperty("title", "原由");
        prop6.setProperty("data", "8");
        columnsInfo.add(prop6);
        
        Properties prop8 = new Properties();
        prop8.setProperty("title", "经办人");
        prop8.setProperty("data", "10");
        columnsInfo.add(prop8);
        
        Properties prop9 = new Properties();
        prop9.setProperty("title", "制单人");
        prop9.setProperty("data", "12");
        
        Properties prop10 = new Properties();
        prop10.setProperty("title", "商品编号");
        prop10.setProperty("data", "15");
        
        Properties prop11 = new Properties();
        prop11.setProperty("title", "商品名称");
        prop11.setProperty("data", "16");
        columnsInfo.add(prop11);
        
        Properties prop12 = new Properties();
        prop12.setProperty("title", "数量");
        prop12.setProperty("data", "19");
        columnsInfo.add(prop12);
        
        Properties prop13 = new Properties();
        prop13.setProperty("title", "金额");
        prop13.setProperty("data", "21");
        columnsInfo.add(prop13);
        
        /*
        Properties prop14 = new Properties();
        prop14.setProperty("title", "商品编号");
        prop14.setProperty("data", "22");
        */
    }
    
    
    public String genHtmlTotalResult() throws wlglException {
       UtilDB utildb = new UtilDB();
       String  tmp= "select sum(IFNULL(TotalMoney,0)), sum(IFNULL(Number,0))  from ("+Query_SQL+") a" ;
       ArrayList a=utildb.exeQueryOneRow(tmp,Query_SQLParam);
       String ototalMoney=(String)a.get(0);
       String ototalNumber=(String)a.get(1);
       utildb.closeCon();
       HttpBase base = new HttpBase();
       HttpBase table11 = HttpBase.genHttpTable("btd", "100%", "1", "5",
                                                "0");
       base.getElements().add(table11);
       HttpBase tr111 = HttpBase.genHttpTR("btd");
       table11.getElements().add(tr111);
       HttpBase td1111 = new HttpBase();
       td1111.setTag("td");
       td1111.getProperties().setProperty("height", "36");
       td1111.getProperties().setProperty("align", "right");
       tr111.getElements().add(td1111);

        HttpBase hb=HttpBase.genHttpValue("合计：申请总金额:"+ototalMoney+"；总数量:"+ototalNumber+";");

       td1111.getElements().add(hb);

        return base.GenHtml();
    }
    
    public String genHtmlMain() throws wlglException {
        String result = "";
        result = genHtmlPos();
        seqn = UtilCommon.NVL(UtilWebTools.getRequestParameter("seqn"));
        if (mode.equals("query")) {
            // 条件录入栏
            result += genHtmlQueryComp();
            // 查询结果栏
            result += genHtmlQueryResult();
            // 增删改查按钮
            result += genHtmlTotalResult();
        }

        return result;
    }
}
