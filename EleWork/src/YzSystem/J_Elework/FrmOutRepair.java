
//外邮维护
package YzSystem.J_Elework;

import java.util.ArrayList;
import java.util.Properties;

import YzSystem.JMain.EditComponent;
import YzSystem.JMain.FrmFunctionBase;
import YzSystem.JMain.HttpBase;
import YzSystem.JMain.UtilDB;
import YzSystem.JMain.wlglException;

public class FrmOutRepair extends FrmFunctionBase {
	  public String billType = "1002";
	    public String pre = "WH";
		
		   public FrmOutRepair() throws wlglException {
		        super();
		        Table = "tbEleworkOutRepair";
		        seqnField = "billno";
		        DetailTable = "tbEleworkOutRepairItem";
		        DetailSeqnField = "rowno";
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
		        	            	setEditComp();
		        	                setAddInfo();
		        	            }
		        	//=========================修改设置==============================================
		        	            // 设置修改组件
		        	            if (mode.equals("edit")) {
		        	            	setEditComp();
		        	                setUpdateInfo();
		        	                setDetailQueryInfo();
		        	            }
		        	        } else {
		        	//=========================添加设置==============================================

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
		     * 设置需要的查询组件
		     */
		    private void setQueryComp() throws wlglException {
		    EditComponent e1 = new EditComponent("", "编号", "a.BillNo", "like",
					"QBillNo", "", "", "", "", "");
			EditComponent e2 = new EditComponent("date", "录入日期", "a.TheDate", ">=",
					"Qbegindate", "", "", "", "", "");
			EditComponent e3 = new EditComponent("date", "至", "a.TheDate", "<=",
					"Qenddate", "", "", "", "", "");

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
			 * setQueryInfo 设置需要的查询信息
			 */
		    private void setQueryInfo() {
		        qry_selectSQL ="select 	a.TheSeqn, a.BillNo, a.TheDate, a.ClientCode,b.theName ClientName,a.RelTelephone, a.RelCode,  a.CopartnerType," +
		        		"\n"+"a.FaxCode, a.WorkArray, a.IsFinally, a.NoticeDate, a.TrafficeDate, a.AskDate, a.TrafficeFee, a.StartDate, a.FinDate, " +
		        		"\n"+"a.ClientSign, a.CheckSign,c.TheName CheckName, a.EngineSign,d.TheName EngingName,a.ClientAttitud, a.TransferSort " +
		        		"\n"+ "from tbeleworkoutrepair a left outer join tbdatumcopartner b on a.ClientCode=b.TheCode and a.CopartnerType=b.CopartnerType" +
		        		"\n"+" left outer join tbdatumemployee c on c.TheCode=a.CheckSign left outer join tbdatumemployee d on d.TheCode=a.EngineSign";
		        qry_whereSQL="a.BillSortCode='"+billType+"'";
		        qry_orderSQL = "order by a.BillNo desc";

		        // 设置列属性(类型，标题，宽度，数据列)
		        columnsInfo.clear();
		        Properties prop1 = new Properties();
		        columnsInfo.add(prop1);
		        prop1.setProperty("type", "radio");
		        prop1.setProperty("title", "选择");
		        prop1.setProperty("width", "5%");
		        prop1.setProperty("data", "1");
		        
		        Properties prop2 = new Properties();
		        prop2.setProperty("title", "单据号");
		        prop2.setProperty("data", "1");
		        columnsInfo.add(prop2);
		        
		        Properties prop3 = new Properties();
		        prop3.setProperty("title", "单据日期");
		        prop3.setProperty("data", "2");
		        columnsInfo.add(prop3);

		        Properties prop4 = new Properties();
		        prop4.setProperty("title", "客户");
		        prop4.setProperty("data", "4");
		        columnsInfo.add(prop4);
		        
		        Properties prop5 = new Properties();
		        prop5.setProperty("title", "联系人");
		        prop5.setProperty("data", "6");
		        columnsInfo.add(prop5);
		        
		        
		        Properties prop6 = new Properties();
		        prop6.setProperty("title", "联系电话");
		        prop6.setProperty("data", "5");
		        columnsInfo.add(prop6);
		        
		        Properties prop7 = new Properties();
		        prop7.setProperty("title", "工程师");
		        prop7.setProperty("data", "21");
		        columnsInfo.add(prop7);
		        
		        Properties prop8 = new Properties();
		        prop8.setProperty("title", "主管确认");
		        prop8.setProperty("data", "19");
		        columnsInfo.add(prop8);
		    } 
		    /**
		     * setAddInfo
		     * 设置需要的编辑组件
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
		    private void setEditComp() throws wlglException {
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
		        ec2.setCaption("单据日期");
		        ec2.setField("TheDate");
		        ec2.setMode("readonly");
		        ec2.setMustFill(true);
		        
		        EditComponent ec3 = new EditComponent();
		        ec3.setEdtType("Client");
		        ec3.setField("ClientCode");
		        ec3.setCaption("客户名称");
		        ec3.setDisID("ClientName");
		        ec3.setDefDisValue("＝请选择＝");
		        ec3.setMode("readonly");
		        ec3.setMustFill(true);
		        ec3.setFillCompArray("['CopartnerType','CopartnerTypeName']");
		        ec3.setFillIndexArray("[1,4]");      
	        
		        EditComponent ec4 = new EditComponent();
		        ec4.setEdtType("CopartnerType");
		        ec4.setField("CopartnerType");
		        ec4.setCaption("客户类别");
		        ec4.setDisID("CopartnerTypeName");
		        ec4.setDefDisValue("＝请选择＝");
		        ec4.setMode("readonly");
		        
		        EditComponent ec5 = new EditComponent();
		        ec5.setCaption("电话");
		        ec5.setField("RelTelephone");
		        
		        EditComponent ec6 = new EditComponent();
		        ec6.setCaption("联系人");
		        ec6.setField("RelCode");

		        EditComponent ec7 = new EditComponent();
		        ec7.setCaption("传真");
		        ec7.setField("FaxCode");
		        
		        EditComponent ec8 = new EditComponent();
		        ec8.setCaption("人员安排");
		        ec8.setField("WorkArray");
		        
		        
		        EditComponent ec9 = new EditComponent();
		        ec9.setEdtType("date");
		        ec9.setCaption("通知时间");
		        ec9.setMode("readonly");
		        ec9.setField("NoticeDate");
		        
		        EditComponent ec10 = new EditComponent();
		        ec10.setEdtType("date");
		        ec10.setCaption("交通时间");
		        ec10.setMode("readonly");
		        ec10.setField("TrafficeDate");
		        
		        
		        EditComponent ec11 = new EditComponent();
		        ec11.setEdtType("date");
		        ec11.setCaption("要求时间");
		        ec11.setMode("readonly");
		        ec11.setField("AskDate");
		        
		        
		        EditComponent ec12 = new EditComponent();
		        ec12.setCaption("交通费用");
		        ec12.setField("TrafficeFee");
		        
		        EditComponent ec13 = new EditComponent();
		        ec13.setEdtType("date");
		        ec13.setCaption("开始时间");
		        ec13.setMode("readonly");
		        ec13.setField("StartDate");
		        
		        
		        EditComponent ec14 = new EditComponent();
		        ec14.setEdtType("date");
		        ec14.setCaption("完成时间");
		        ec14.setMode("readonly");
		        ec14.setField("FinDate");
	        
		        
		        EditComponent ec15 = new EditComponent();
		        ec15.setEdtType("Employee");
		        ec15.setCaption("工程师");
		        ec15.setField("EngineSign");
		        ec15.setDisID("EngineSignName");
		        ec15.setDefDisValue("＝请选择＝");
		        ec15.setMode("readonly");
		        
		        EditComponent ec16= new EditComponent();
		        ec16.setEdtType("textarea");
		        ec16.setCaption("用户建议");
		        ec16.setField("ClientAttitud");
		        ec16.setColspan("5");		        
		        
		        EditComponent ec17 = new EditComponent();
		        ec17.setCaption("用户签名");
		        ec17.setField("ClientSign"); 
		        
		        EditComponent ec18 = new EditComponent();
		        ec18.setEdtType("bool");
		        ec18.setCaption("是否完成");
		        ec18.setField("IsFinally");
		        
		        
		        EditComponent ec19 = new EditComponent();
		        ec19.setEdtType("bool");
		        ec19.setCaption("单据转移");
		        ec19.setField("TransferSort");

		        // 设置界面每行内容
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

		        HttpBase tre4 = HttpBase.genHttpTR("btd");
		        tre4.getElements().add(ec10.genHttpBase());
		        tre4.getElements().add(ec11.genHttpBase());
		        tre4.getElements().add(ec12.genHttpBase());
		        
		        HttpBase tre5 = HttpBase.genHttpTR("btd");
		        tre5.getElements().add(ec13.genHttpBase());
		        tre5.getElements().add(ec14.genHttpBase());
		        tre5.getElements().add(ec15.genHttpBase());

		        HttpBase tre6 = HttpBase.genHttpTR("btd");
		        tre6.getElements().add(ec16.genHttpBase());
		        
		        HttpBase tre7 = HttpBase.genHttpTR("btd");
		        tre7.getElements().add(ec17.genHttpBase());
		        tre7.getElements().add(ec18.genHttpBase());
		        tre7.getElements().add(ec19.genHttpBase());

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
		        editComps.add(ec10);
		        editComps.add(ec11);
		        editComps.add(ec12);
		        editComps.add(ec13);
		        editComps.add(ec14);
		        editComps.add(ec15);
		        editComps.add(ec16);
		        editComps.add(ec17);
		        editComps.add(ec18);
		        editComps.add(ec19);

		        // 增加到编辑栏列表
		        editPanels.clear();
		        editPanels.add(tre1);
		        editPanels.add(tre2);
		        editPanels.add(tre3);
		        editPanels.add(tre4);
		        editPanels.add(tre5);
		        editPanels.add(tre6);
		        editPanels.add(tre7);
		    }

		    /**
		     * setAddInfo
		     * 设置需要的编辑组件
		     */
		    private void setUpdateInfo() {
		        ArrayList ret = new ArrayList();
		        edt_selectSQL ="select 	a.TheSeqn, a.BillNo, a.TheDate, a.ClientCode,b.theName ClientName, a.CopartnerType,a.RelTelephone, a.RelCode, " +
      		"\n"+"a.FaxCode, a.WorkArray, a.NoticeDate, a.TrafficeDate, a.AskDate, a.TrafficeFee, a.StartDate, a.FinDate, " +
      		"\n"+"a.EngineSign, d.TheName EngingName,a.ClientAttitud,a.ClientSign,a.IsFinally, case a.IsFinally when '1' then '是' else '否' end IsFinallyName,a.TransferSort,a.CheckSign,c.TheName CheckName" +
      		"\n"+ "from tbeleworkoutrepair a left outer join tbdatumcopartner b on a.ClientCode=b.TheCode and a.CopartnerType=b.CopartnerType" +
      		"\n"+" left outer join tbdatumemployee c on c.TheCode=a.CheckSign left outer join tbdatumemployee d on d.TheCode=a.EngineSign";
		        
		        edt_whereSQL="a.BillSortCode='"+billType+"'";


		        // 设置编辑框对应的列
		        editColumns.setProperty("0", "1");
		        editColumns.setProperty("1", "2");
		        editColumns.setProperty("2", "3");
		        editColumns.setProperty("2d", "4");
		        editColumns.setProperty("3", "5");
		        editColumns.setProperty("4", "6");
		        editColumns.setProperty("5", "7");
		        editColumns.setProperty("6", "8");
		        editColumns.setProperty("7", "9");
		        
		        editColumns.setProperty("8", "10");
		        editColumns.setProperty("9", "11");
		        editColumns.setProperty("10", "12");
		        editColumns.setProperty("11", "13");
		        editColumns.setProperty("12", "14");
		        editColumns.setProperty("13", "15");
		        
		        editColumns.setProperty("14", "16");
		        editColumns.setProperty("14d", "17");
		        editColumns.setProperty("15", "18");
		        
		        editColumns.setProperty("16", "19");
		        editColumns.setProperty("17", "20");
		        editColumns.setProperty("17d", "21");
		        editColumns.setProperty("18", "22");
		        editColumns.setProperty("18d", "23");
		        

		        // 设置附加的修改列属性(类型，标题，宽度，数据列)
		        ret.clear();
		        
		        //执行人
		        Properties prop1 = new Properties();
		        ret.add(prop1);
		        prop1.setProperty("type", "employeecode");
		        prop1.setProperty("field", "Stater");

		  
		        // 创建时间
		        Properties prop2 = new Properties();
		        ret.add(prop2);
		        prop2.setProperty("type", "time");
		        prop2.setProperty("field", "StateTime");
		        
		        //编辑人
		        Properties prop3 = new Properties();
		        ret.add(prop3);
		        prop3.setProperty("type", "employeecode");
		        prop3.setProperty("field", "Editer");

		  
		        //编辑时间
		        Properties prop4 = new Properties();
		        ret.add(prop4);
		        prop4.setProperty("type", "time");
		        prop4.setProperty("field", "EditeTime");

		        addExtraColumns.addAll(ret);
		    }
		    
		    // 明细信息
	private void setDetailQueryInfo() {
		detailSelectSQL = "select 	TheSeqn, a.BillNo, a.RowNo, a.ListNo, a.ProductCode,b.TheName ProductName, a.ProductSort,c.TheName ProductSortName,d.TheName ModelName,e.TheName SpecName,f.TheName UnitName,"
				+ "\n"+ "a.Quedesc, a.Taskdesc, a.Remark from tbeleworkoutrepairitem a"
				+ "\n"+ "left outer join tbdatumproduct b on a.ProductCode=b.TheCode and a.Productsort=b.ProductTypecode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n"+ "where b.Flag='ProductTypeCode') c on c.RowNo=b.ProductTypeCode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n"+ "where b.Flag='ModelCode') d on d.RowNo=b.ModelCode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n"+ "where b.Flag='SpecCode') e on e.RowNo=b.SpecCode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n" + "where b.Flag='UnitCode') f on f.RowNo=b.UnitCode";

		detailOrderSQL = "order by a.RowNo ";

		// 设置列属性(类型，标题，宽度，数据列)
		detailColumnsInfo.clear();
		Properties prop1 = new Properties();
		detailColumnsInfo.add(prop1);
		prop1.setProperty("type", "radio");
		prop1.setProperty("title", "选择");
		prop1.setProperty("width", "5%");
		prop1.setProperty("data", "2");

		Properties prop2 = new Properties();
		prop2.setProperty("title", "序列号");
		prop2.setProperty("data", "3");
		detailColumnsInfo.add(prop2);

		Properties prop3 = new Properties();
		prop3.setProperty("title", "商品");
		prop3.setProperty("data", "5");
		detailColumnsInfo.add(prop3);

		Properties prop4 = new Properties();
		prop4.setProperty("title", "型号");
		prop4.setProperty("data", "7");
		detailColumnsInfo.add(prop4);

		Properties prop5 = new Properties();
		prop5.setProperty("title", "规格");
		prop5.setProperty("data", "8");
		detailColumnsInfo.add(prop5);

	}
		    private void setDetailEditComp() throws wlglException {
		    	
		        EditComponent ec1 = new EditComponent();
		        ec1.setField("ListNo");
		        ec1.setCaption("序列号");
		        
		        EditComponent ec2 = new EditComponent();
		        ec2.setEdtType("Product");
		        ec2.setField("ProductCode");
		        ec2.setCaption("设备名称");
		        ec2.setDisID("ProductName");
		        ec2.setDefDisValue("＝请选择＝");
		        ec2.setMode("readonly");
		        ec2.setMustFill(true);
		        ec2.setFillCompArray("['ProductSort','ModelName','SpecName','UnitName']");
		        ec2.setFillIndexArray("[1,9,5,7]");

		        EditComponent ec3 = new EditComponent();
		        ec3.setEdtType("ProductTypeCode ");
		        ec3.setField("ProductSort");
		        ec3.setDisID("ProductSortName");
		        ec3.setCaption("设备类别");
		        ec3.setMode("readonly");
		        
		        
		        EditComponent ec4 = new EditComponent();
		        ec4.setID("ModelName");
		        ec4.setCaption("型号");
		        ec4.setMode("readonly");
		        
		        EditComponent ec5 = new EditComponent();
		        ec5.setID("SpecName");
		        ec5.setCaption("规格");
		        ec5.setMode("readonly");

		        EditComponent ec6 = new EditComponent();
		        ec6.setID("UnitName");
		        ec6.setCaption("单位");
		        ec6.setMode("readonly");


		        EditComponent ec7 = new EditComponent();
		        ec7.setEdtType("textarea");
		        ec7.setCaption("任务阐述");
		        ec7.setField("Quedesc");
		        ec7.setColspan("5");
		        
		        EditComponent ec8 = new EditComponent();
		        ec8.setEdtType("textarea");
		        ec8.setCaption("工作详情");
		        ec8.setField("Taskdesc");
		        ec8.setColspan("5");


		        // 设置界面每行内容
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
		        
		        HttpBase tre4 = HttpBase.genHttpTR("btd");
		        tre4.getElements().add(ec8.genHttpBase());

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

		        // 增加到编辑栏列表
		        editPanels.clear();
		        editPanels.add(tre1);
		        editPanels.add(tre2);
		        editPanels.add(tre3);
		        editPanels.add(tre4);
		    }
	 
		    private void setDetailUpdateInfo() {
		    	
		        ArrayList ret = new ArrayList();
		        detailSelectSQL = "select 	a.BillNo, a.RowNo, a.ListNo, a.ProductCode,b.TheName ProductName, a.ProductSort,c.TheName ProductSortName,d.TheName ModelName,e.TheName SpecName,f.TheName UnitName,"
					+ "\n"+ "a.Quedesc, a.Taskdesc, a.Remark from tbeleworkoutrepairitem a"
					+ "\n"+ "left outer join tbdatumproduct b on a.ProductCode=b.TheCode and a.Productsort=b.ProductTypecode"
					+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
					+ "\n"+ "where b.Flag='ProductTypeCode') c on c.RowNo=b.ProductTypeCode"
					+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
					+ "\n"+ "where b.Flag='ModelCode') d on d.RowNo=b.ModelCode"
					+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
					+ "\n"+ "where b.Flag='SpecCode') e on e.RowNo=b.SpecCode"
					+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
					+ "\n" + "where b.Flag='UnitCode') f on f.RowNo=b.UnitCode";
		        
		        detailOrderSQL = "order by a.RowNo";

		        // 设置编辑框对应的列

		        editColumns.setProperty("0", "2");
		        editColumns.setProperty("1", "3");
		        editColumns.setProperty("1d", "4");
		        editColumns.setProperty("2", "5");
		        editColumns.setProperty("2d", "6");
		        editColumns.setProperty("3", "7");
		        editColumns.setProperty("4", "8");
		        editColumns.setProperty("5", "9");
		        editColumns.setProperty("6", "10");
		        editColumns.setProperty("7", "11");
		        
		        // 设置附加的修改列属性(类型，标题，宽度，数据列)
		        ret.clear();

		        addExtraColumns.addAll(ret);
		    }
		    
		    private void setDetailAddInfo() {
		        ArrayList ret = new ArrayList();
		        //Table = "tssystemsubjectrecord";
		        // 设置列属性(类型，标题，宽度，数据列)
		        ret.clear();
		        
		        Properties prop1 = new Properties();
		        ret.add(prop1);
		        prop1.setProperty("type", "DetailSeqn");
		        prop1.setProperty("mainkey",seqnField);
		        prop1.setProperty("field", "RowNo");
		        
		        Properties prop7 = new Properties();
		        ret.add(prop7);
		        prop7.setProperty("type", "seqn");
		        prop7.setProperty("field", "BillNo");

		        addExtraColumns.addAll(ret);
		    }
		    
		    
		    
		    //增加默认的项目，检查内容。
		    protected void afterAddSave(UtilDB utildb) throws wlglException {
		    }

		    private void jbInit() throws Exception {
		    }


}
