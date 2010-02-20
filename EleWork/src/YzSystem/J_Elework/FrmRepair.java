package YzSystem.J_Elework;

import java.util.ArrayList;
import java.util.Properties;

import YzSystem.JMain.EditComponent;
import YzSystem.JMain.FrmFunctionBase;
import YzSystem.JMain.HttpBase;
import YzSystem.JMain.UtilDB;
import YzSystem.JMain.wlglException;

public class FrmRepair  extends FrmFunctionBase {
	  public String billType = "1003";
	    public String pre = "WX";
		
		   public FrmRepair() throws wlglException {
		        super();
		        Table = "tbeleworkrepair";
		        seqnField = "billno";
		        DetailTable = "tbeleworkrepairitem";
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
		        qry_selectSQL ="select 	a.TheSeqn, a.BillNo, a.TheDate, a.ClientCode,b.theName ClientName,a.RelTelephone, a.RelCode,  a.CopartnerType,a.ClientAdd," +
		        		"\n"+"a.ListNo, a.ProductCode,h.TheName ProductName,a.ProductSort,i.TheName ProductSortName,j.TheName ModelName,k.TheName SpecName,l.TheName UnitName," +
		        		"\n"+"a.IsKeep,case a.IsKeep when '1' then '是' else '否' end IsKeepName,a.ServiceSortCode,g.TheName ServiceSortName,a.Quedesc, a.InHandOver, a.InSelfSign,e.TheName InSelfSignName," +
		        		"\n"+"a.InDate,a.Configdesc, a.Appeardesc, a.Operdesc, a.Taskdesc, a.IsReject,case a.IsReject when '1' then '是' else '否' end IsRejectName,a.FinDate, a.CheckSign,c.TheName CheckName, a.EngineSign,d.TheName EngingName,a.ClientSign,a.ClientAttitud," +
		        		"\n"+"a.OutHandOver, a.OutSelfSign,f.TheName OutSelfSignName, a.TransferSort"+
		        		"\n"+ "from tbeleworkrepair a left outer join tbdatumcopartner b on a.ClientCode=b.TheCode and a.CopartnerType=b.CopartnerType" +
		        		"\n"+" left outer join tbdatumemployee c on c.TheCode=a.CheckSign left outer join tbdatumemployee d on d.TheCode=a.EngineSign"+
		        		"\n"+" left outer join tbdatumemployee e on e.TheCode=a.InSelfSign left outer join tbdatumemployee f on f.TheCode=a.OutSelfSign"+ 
		        		"\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"+ 
		        		"\n" + "where b.Flag='ServiceSortCode') g on g.RowNo=a.ServiceSortCode"
						+ "\n"+ "left outer join tbdatumproduct h on a.ProductCode=h.TheCode and a.Productsort=h.ProductTypecode"
						+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
						+ "\n"+ "where b.Flag='ProductTypeCode') i on i.RowNo=h.ProductTypeCode"
						+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
						+ "\n"+ "where b.Flag='ModelCode') j on j.RowNo=h.ModelCode"
						+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
						+ "\n"+ "where b.Flag='SpecCode') k on k.RowNo=h.SpecCode"
						+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
						+ "\n" + "where b.Flag='UnitCode') l on l.RowNo=h.UnitCode";

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
		        
		        Properties prop9 = new Properties();
		        prop9.setProperty("title", "序列号");
		        prop9.setProperty("data", "9");
		        columnsInfo.add(prop9);
		        
		        Properties prop10 = new Properties();
		        prop10.setProperty("title", "送修时间");
		        prop10.setProperty("data", "25");
		        columnsInfo.add(prop10);
		        
		        Properties prop11 = new Properties();
		        prop11.setProperty("title", "商品");
		        prop11.setProperty("data", "11");
		        columnsInfo.add(prop11);
		        
		        
		        Properties prop7 = new Properties();
		        prop7.setProperty("title", "工程师");
		        prop7.setProperty("data", "36");
		        columnsInfo.add(prop7);
		        
		        Properties prop8 = new Properties();
		        prop8.setProperty("title", "主管确认");
		        prop8.setProperty("data", "34");
		        columnsInfo.add(prop8);
		    } 
		    /**
		     * setAddInfo
		     * 设置需要的编辑组件
		     */
		    private void setAddInfo() {
		        ArrayList ret = new ArrayList();

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
		        ec7.setCaption("通信地址");
		        ec7.setField("ClientAdd");
		        
		        EditComponent ec8 = new EditComponent();
		        ec8.setField("ListNo");
		        ec8.setCaption("序列号");
		        
		        EditComponent ec9 = new EditComponent();
		        ec9.setEdtType("Product");
		        ec9.setField("ProductCode");
		        ec9.setCaption("设备名称");
		        ec9.setDisID("ProductName");
		        ec9.setDefDisValue("＝请选择＝");
		        ec9.setMode("readonly");
		        ec9.setMustFill(true);
		        ec9.setFillCompArray("['ProductSort','ModelName','SpecName','UnitName']");
		        ec9.setFillIndexArray("[1,9,5,7]");

		        EditComponent ec10 = new EditComponent();
		        ec10.setEdtType("ProductTypeCode ");
		        ec10.setField("ProductSort");
		        ec10.setDisID("ProductSortName");
		        ec10.setCaption("设备类别");
		        ec10.setMode("readonly");
		        
		        
		        EditComponent ec11 = new EditComponent();
		        ec11.setID("ModelName");
		        ec11.setCaption("型号");
		        ec11.setMode("readonly");
		        
		        EditComponent ec12 = new EditComponent();
		        ec12.setID("SpecName");
		        ec12.setCaption("规格");
		        ec12.setMode("readonly");

		        EditComponent ec13 = new EditComponent();
		        ec13.setID("UnitName");
		        ec13.setCaption("单位");
		        ec13.setMode("readonly");
		        
		        
		        EditComponent ec14 = new EditComponent();
		        ec14.setEdtType("bool");
		        ec14.setCaption("是否保修");
		        ec14.setMode("readonly");
		        ec14.setField("IsKeep");
		        ec14.setDisID("IsKeepName");
		        
		        EditComponent ec15 = new EditComponent();
		        ec15.setEdtType("ServiceSortCode");
		        ec15.setCaption("服务方式");
		        ec15.setMode("readonly");
		        ec15.setField("ServiceSortCode");
		        ec15.setDisID("ServiceSortName");
		        
		        
		        EditComponent ec16 = new EditComponent();
		        ec16.setEdtType("textarea");
		        ec16.setCaption("故障现象及故障判断");
		        ec16.setField("Quedesc");
		        ec16.setColspan("5");
		        
		        
		        EditComponent ec17 = new EditComponent();
		        ec17.setCaption("管理部门签名(入)");
		        ec17.setField("InHandOver");
		        
		        EditComponent ec18 = new EditComponent();
		        ec18.setEdtType("Employee");
		        ec18.setCaption("冠发签名(入)");
		        ec18.setMode("readonly");
		        ec18.setField("InSelfSign");
		        ec18.setDisID("InSelfSignName");
		        
		        
		        EditComponent ec19 = new EditComponent();
		        ec19.setEdtType("date");
		        ec19.setCaption("用户送修时间");
		        ec19.setMode("readonly");
		        ec19.setField("InDate");
		        
		        EditComponent ec20 = new EditComponent();
		        ec20.setEdtType("textarea");
		        ec20.setCaption("特殊配置描述");
		        ec20.setField("Configdesc");
		        ec20.setColspan("5");
		        
		        
		        EditComponent ec21 = new EditComponent();
		        ec21.setEdtType("textarea");
		        ec21.setCaption("损坏情况描述");
		        ec21.setField("Appeardesc");
		        ec21.setColspan("5");
		        
		        
		        EditComponent ec22 = new EditComponent();
		        ec22.setEdtType("textarea");
		        ec22.setCaption("客户要求");
		        ec22.setField("Operdesc");
		        ec22.setColspan("5");
		        
		        EditComponent ec23 = new EditComponent();
		        ec23.setEdtType("textarea");
		        ec23.setCaption("故障排除和处理方法");
		        ec23.setField("Taskdesc");
		        ec23.setColspan("5");      
		        
		        
		        EditComponent ec24 = new EditComponent();
		        ec24.setEdtType("bool");
		        ec24.setCaption("是否报废");
		        ec24.setField("IsReject");
		        ec24.setDisID("IsRejectName");
		        
		        EditComponent ec25 = new EditComponent();
		        ec25.setEdtType("date");
		        ec25.setCaption("完成日期");
		        ec25.setField("FinDate");

		        EditComponent ec26 = new EditComponent();
		        ec26.setEdtType("Employee");
		        ec26.setCaption("工程师");
		        ec26.setField("EngineSign");
		        ec26.setDisID("EngineSignName");
		        ec26.setDefDisValue("＝请选择＝");
		        ec26.setMode("readonly");
		        
		        EditComponent ec27= new EditComponent();
		        ec27.setEdtType("textarea");
		        ec27.setCaption("用户建议");
		        ec27.setField("ClientAttitud");
		        ec27.setColspan("5");		        
		        
		        EditComponent ec28 = new EditComponent();
		        ec28.setCaption("用户签名");
		        ec28.setField("ClientSign"); 
		        
		        EditComponent ec29 = new EditComponent();
		        ec29.setCaption("管理部门签名(出)");
		        ec29.setField("OutHandOver");
		        
		        EditComponent ec30 = new EditComponent();
		        ec30.setEdtType("Employee");
		        ec30.setCaption("冠发签名(出)");
		        ec30.setMode("readonly");
		        ec30.setField("OutSelfSign");
		        ec30.setDisID("OutSelfSignName");

		        EditComponent ec31 = new EditComponent();
		        ec31.setEdtType("bool");
		        ec31.setCaption("单据转移");
		        ec31.setField("TransferSort");
                ec31.setColspan("5");
                
                
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
		        
		        HttpBase tre8 = HttpBase.genHttpTR("btd");
		        tre8.getElements().add(ec20.genHttpBase());
		        
		        HttpBase tre9 = HttpBase.genHttpTR("btd");
		        tre9.getElements().add(ec21.genHttpBase());
		        
		        HttpBase tre10 = HttpBase.genHttpTR("btd");
		        tre10.getElements().add(ec22.genHttpBase());
		        
		        HttpBase tre11 = HttpBase.genHttpTR("btd");
		        tre11.getElements().add(ec23.genHttpBase());
		        
		        HttpBase tre12 = HttpBase.genHttpTR("btd");
		        tre12.getElements().add(ec24.genHttpBase());
		        tre12.getElements().add(ec25.genHttpBase());
		        tre12.getElements().add(ec26.genHttpBase());
		        
		        HttpBase tre13 = HttpBase.genHttpTR("btd");
		        tre13.getElements().add(ec27.genHttpBase());
		        
		        HttpBase tre14 = HttpBase.genHttpTR("btd");
		        tre14.getElements().add(ec28.genHttpBase());
		        tre14.getElements().add(ec29.genHttpBase());
		        tre14.getElements().add(ec30.genHttpBase());
		        
		        HttpBase tre15 = HttpBase.genHttpTR("btd");
		        tre15.getElements().add(ec31.genHttpBase());

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
		        editComps.add(ec20);
		        editComps.add(ec21);
		        editComps.add(ec22);
		        editComps.add(ec23);
		        editComps.add(ec24);
		        editComps.add(ec25);
		        editComps.add(ec26);
		        editComps.add(ec27);
		        editComps.add(ec28);
		        editComps.add(ec29);
		        editComps.add(ec30);
		        editComps.add(ec31);

		        // 增加到编辑栏列表
		        editPanels.clear();
		        editPanels.add(tre1);
		        editPanels.add(tre2);
		        editPanels.add(tre3);
		        editPanels.add(tre4);
		        editPanels.add(tre5);
		        editPanels.add(tre6);
		        editPanels.add(tre7);
		        editPanels.add(tre8);
		        editPanels.add(tre9);
		        editPanels.add(tre10);
		        editPanels.add(tre11);
		        editPanels.add(tre12);
		        editPanels.add(tre13);
		        editPanels.add(tre14);
		        editPanels.add(tre15);
		    }

		    /**
		     * setAddInfo
		     * 设置需要的编辑组件
		     */
		    private void setUpdateInfo() {
		        ArrayList ret = new ArrayList();
		        edt_selectSQL ="select 	a.TheSeqn, a.BillNo, a.TheDate, a.ClientCode,b.theName ClientName,a.CopartnerType,m.TheName CopartnerTypeName,a.RelTelephone, a.RelCode,  a.ClientAdd," +
        		"\n"+"a.ListNo, a.ProductCode,h.TheName ProductName,a.ProductSort,i.TheName ProductSortName,j.TheName ModelName,k.TheName SpecName,l.TheName UnitName," +
        		"\n"+"a.IsKeep,case a.IsKeep when '1' then '是' else '否' end IsKeepName,a.ServiceSortCode,g.TheName ServiceSortName,a.Quedesc, a.InHandOver, a.InSelfSign,e.TheName InSelfSignName," +
        		"\n"+"a.InDate,a.Configdesc, a.Appeardesc, a.Operdesc, a.Taskdesc, a.IsReject,case a.IsReject when '1' then '是' else '否' end IsRejectName,a.FinDate, a.CheckSign,c.TheName CheckName, a.EngineSign,d.TheName EngingName,a.ClientAttitud, a.ClientSign," +
        		"\n"+"a.OutHandOver, a.OutSelfSign,f.TheName OutSelfSignName,a.TransferSort"+
        		"\n"+ "from tbeleworkrepair a left outer join tbdatumcopartner b on a.ClientCode=b.TheCode and a.CopartnerType=b.CopartnerType" +
        		"\n"+" left outer join tbdatumemployee c on c.TheCode=a.CheckSign left outer join tbdatumemployee d on d.TheCode=a.EngineSign"+
        		"\n"+" left outer join tbdatumemployee e on e.TheCode=a.InSelfSign left outer join tbdatumemployee f on f.TheCode=a.OutSelfSign"+ 
        		
        		"\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"+ 
        		"\n" + "where b.Flag='ServiceSortCode') g on g.RowNo=a.ServiceSortCode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n"+ "where b.Flag='CopartnerType') m on m.RowNo=a.CopartnerType"
				+ "\n"+ "left outer join tbdatumproduct h on a.ProductCode=h.TheCode and a.Productsort=h.ProductTypecode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n"+ "where b.Flag='ProductTypeCode') i on i.RowNo=h.ProductTypeCode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n"+ "where b.Flag='ModelCode') j on j.RowNo=h.ModelCode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n"+ "where b.Flag='SpecCode') k on k.RowNo=h.SpecCode"
				+ "\n"+ "left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort"
				+ "\n" + "where b.Flag='UnitCode') l on l.RowNo=h.UnitCode";


		        // 设置编辑框对应的列
		        editColumns.setProperty("0", "1");//billno
		        editColumns.setProperty("1", "2");
		        editColumns.setProperty("2", "3");
		        editColumns.setProperty("2d", "4");
		        editColumns.setProperty("3", "5");
		        editColumns.setProperty("3d", "6");
		        editColumns.setProperty("4", "7");
		        editColumns.setProperty("5", "8");
		        editColumns.setProperty("6", "9");
		        editColumns.setProperty("7", "10");//序列号
		        
		        editColumns.setProperty("8", "11");
		        editColumns.setProperty("8d", "12");
		        editColumns.setProperty("9", "13");
		        editColumns.setProperty("9d", "14");
		        editColumns.setProperty("10", "15");
		        editColumns.setProperty("11", "16");
		        editColumns.setProperty("12", "17");
		        
		        editColumns.setProperty("13", "18");//是否保修
		        editColumns.setProperty("13d", "19");
		        editColumns.setProperty("14", "20");		        
		        editColumns.setProperty("14d", "21");//ServiceSortName
		        
		        editColumns.setProperty("15", "22");
		        editColumns.setProperty("16", "23");
		        editColumns.setProperty("17", "24");
		        editColumns.setProperty("17d", "25");
		        editColumns.setProperty("18", "26"); //送修时间
		        
		        editColumns.setProperty("19", "27");
		        editColumns.setProperty("20", "28");
		        editColumns.setProperty("21", "29");
		        editColumns.setProperty("22", "30");
		        
		        editColumns.setProperty("23", "31");//是否报废
		        editColumns.setProperty("23d", "32");
		        editColumns.setProperty("24", "33");
		        
		        
		        editColumns.setProperty("25", "36");
		        editColumns.setProperty("25d", "37");
		        editColumns.setProperty("26", "38");
		        editColumns.setProperty("27", "39");
		        editColumns.setProperty("28", "40");
		        editColumns.setProperty("29", "41");	
		        editColumns.setProperty("29d", "42");	
		        
		        editColumns.setProperty("30", "43");
		        
		        

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
		detailSelectSQL = "select 	TheSeqn, a.BillNo, a.RowNo,a.FitCode,b.TheName ProductName, a.FitSort,c.TheName ProductSortName,d.TheName ModelName,e.TheName SpecName,f.TheName UnitName,"
				+ "\n"+ "a.Number, a.Price, a.TotalMoney, a.Remark from tbeleworkrepairitem a"
				+ "\n"+ "left outer join tbdatumproduct b on a.FitCode=b.TheCode and a.FitSort=b.ProductTypecode"
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

		Properties prop3 = new Properties();
		prop3.setProperty("title", "备件");
		prop3.setProperty("data", "4");
		detailColumnsInfo.add(prop3);

		Properties prop4 = new Properties();
		prop4.setProperty("title", "型号");
		prop4.setProperty("data", "7");
		detailColumnsInfo.add(prop4);

		Properties prop5 = new Properties();
		prop5.setProperty("title", "规格");
		prop5.setProperty("data", "8");
		detailColumnsInfo.add(prop5);
		
		Properties prop6 = new Properties();
		prop6.setProperty("title", "数量");
		prop6.setProperty("data", "10");
		detailColumnsInfo.add(prop6);
		
		Properties prop7 = new Properties();
		prop7.setProperty("title", "金额");
		prop7.setProperty("data", "11");
		detailColumnsInfo.add(prop7);
		


	}
		    private void setDetailEditComp() throws wlglException {
		    			        
		        EditComponent ec2 = new EditComponent();
		        ec2.setEdtType("Product");
		        ec2.setField("FitCode");
		        ec2.setCaption("备件名称");
		        ec2.setDisID("ProductName");
		        ec2.setDefDisValue("＝请选择＝");
		        ec2.setMode("readonly");
		        ec2.setMustFill(true);
		        ec2.setFillCompArray("['FitSort','ModelName','SpecName','UnitName']");
		        ec2.setFillIndexArray("[1,9,5,7]");

		        EditComponent ec3 = new EditComponent();
		        ec3.setEdtType("ProductTypeCode ");
		        ec3.setField("FitSort");
		        ec3.setDisID("FitSortName");
		        ec3.setCaption("备件类别");
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
		        ec7.setField("Number");
		        ec7.setCaption("数量");
		        ec7.setMustFill(true);
		        ec7.setMustInt(true);
		        ec7.setDefValue("0");
		        
		        EditComponent ec8 = new EditComponent();
		        ec8.setField("TotalMoney");
		        ec8.setCaption("总额");
		        ec8.setMustFill(true);
		        ec8.setMustFloat(true);
		        ec8.setDefValue("0.00");
		        ec8.setColspan("5");


		        // 设置界面每行内容
		        HttpBase tre1 = HttpBase.genHttpTR("btd");
		        tre1.getElements().add(ec2.genHttpBase());
		        tre1.getElements().add(ec3.genHttpBase());
		        tre1.getElements().add(ec4.genHttpBase());
		        
		        HttpBase tre2 = HttpBase.genHttpTR("btd");
		        tre2.getElements().add(ec5.genHttpBase());
		        tre2.getElements().add(ec6.genHttpBase());
		        tre2.getElements().add(ec7.genHttpBase());
		        
		        HttpBase tre3 = HttpBase.genHttpTR("btd");
		        tre3.getElements().add(ec8.genHttpBase());
		        

		        // 增加到编辑控件列表
		        editComps.clear();
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
		    }
	 
		    private void setDetailUpdateInfo() {
		    	
		        ArrayList ret = new ArrayList();
		        detailSelectSQL =  "select 	TheSeqn, a.BillNo, a.RowNo,a.FitCode,b.TheName ProductName, a.FitSort,c.TheName ProductSortName,d.TheName ModelName,e.TheName SpecName,f.TheName UnitName,"
					+ "\n"+ "a.Number, a.Price, a.TotalMoney, a.Remark from tbeleworkrepairitem a"
					+ "\n"+ "left outer join tbdatumproduct b on a.FitCode=b.TheCode and a.FitSort=b.ProductTypecode"
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
