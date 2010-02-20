
//巡检
package YzSystem.J_Elework;

import java.util.ArrayList;
import java.util.Properties;

import YzSystem.JMain.EditComponent;
import YzSystem.JMain.FrmFunctionBase;
import YzSystem.JMain.HttpBase;
import YzSystem.JMain.UtilDB;
import YzSystem.JMain.wlglException;

public class Frmpatrol extends FrmFunctionBase {
    public String billType = "1004";
    public String pre = "XJ";
	
	   public Frmpatrol() throws wlglException {
	        super();
	        Table = "tbeleworkpatrol";
	        seqnField = "billno";
	        DetailTable = "tbeleworkpatrolitem";
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
	        qry_selectSQL ="select a.TheSeqn, a.BillNo, a.TheDate, a.ClientCode,b.TheName ClientName," +
	        		"\n"+" a.CopartnerType, a.PatrolDate, a.CheckSign, c.TheName CheckSignName,a.EngineSign," +
	        		"\n"+"d.TheName EngineSignName, a.ClientSign, a.ClientAttitud, a.BillSortCode " +
	        		"\n"+ "from tbeleworkpatrol a left outer join tbdatumcopartner b on a.ClientCode=b.TheCode and a.CopartnerType=b.CopartnerType" +
	        		"\n"+" left outer join tbdatumemployee c on a.CheckSign=c.TheCode" +
	        		"\n"+"left outer join tbdatumemployee d on a.EngineSign=d.Thecode";

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

	        
	        Properties prop4 = new Properties();
	        prop4.setProperty("title", "客户");
	        prop4.setProperty("data", "4");
	        columnsInfo.add(prop4);
	        
	        Properties prop5 = new Properties();
	        prop5.setProperty("title", "巡检日期");
	        prop5.setProperty("data", "6");
	        columnsInfo.add(prop5);
	        
	        
	        Properties prop6 = new Properties();
	        prop6.setProperty("title", "巡检人员");
	        prop6.setProperty("data", "10");
	        columnsInfo.add(prop6);
	        
	        Properties prop7 = new Properties();
	        prop7.setProperty("title", "支局签名");
	        prop7.setProperty("data", "11");
	        columnsInfo.add(prop7);
	        
	        Properties prop8 = new Properties();
	        prop8.setProperty("title", "支局建议");
	        prop8.setProperty("data", "12");
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
	        ec2.setEdtType("Client");
	        ec2.setField("ClientCode");
	        ec2.setCaption("客户名称");
	        ec2.setDisID("ClientName");
	        ec2.setDefDisValue("＝请选择＝");
	        ec2.setMode("readonly");
	        ec2.setMustFill(true);
	        ec2.setFillCompArray("['CopartnerType','CopartnerTypeName']");
	        ec2.setFillIndexArray("[1,4]");      
        
	        EditComponent ec3 = new EditComponent();
	        ec3.setEdtType("CopartnerType");
	        ec3.setField("CopartnerType");
	        ec3.setCaption("客户类别");
	        ec3.setDisID("CopartnerTypeName");
	        ec3.setDefDisValue("＝请选择＝");
	        ec3.setMode("readonly");
	        
	        
	        EditComponent ec4 = new EditComponent();
	        ec4.setEdtType("date");
	        ec4.setCaption("巡检日期");
	        ec4.setField("PatrolDate");
	        ec4.setMode("readonly");
	        ec4.setMustFill(true);

	        


	        EditComponent ec5 = new EditComponent();
	        ec5.setEdtType("Employee");
	        ec5.setCaption("巡检人员");
	        ec5.setField("EngineSign");
	        ec5.setDisID("EngineSignName");
	        ec5.setDefDisValue("＝请选择＝");
	        ec5.setMode("readonly");
	        
	        
	        EditComponent ec6 = new EditComponent();
	        ec6.setCaption("支局签名");
	        ec6.setField("ClientSign");

	        EditComponent ec7= new EditComponent();
	        ec7.setEdtType("textarea");
	        ec7.setCaption("建议");
	        ec7.setField("ClientAttitud");
	        ec7.setColspan("5");

	        
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


	        // 增加到编辑控件列表
	        editComps.clear();
	        editComps.add(ec1);
	        editComps.add(ec2);
	        editComps.add(ec3);
	        editComps.add(ec4);
	        editComps.add(ec5);
	        editComps.add(ec6);
	        editComps.add(ec7);

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
	    private void setUpdateInfo() {
	        ArrayList ret = new ArrayList();
	        edt_selectSQL ="select a.TheSeqn, a.BillNo, a.TheDate, a.ClientCode,b.TheName ClientName," +
    		"\n"+" a.CopartnerType, a.PatrolDate, a.CheckSign, c.TheName CheckSignName,a.EngineSign," +
    		"\n"+"d.TheName EngineSignName, a.ClientSign, a.ClientAttitud, a.BillSortCode " +
    		"\n"+ "from tbeleworkpatrol a left outer join tbdatumcopartner b on a.ClientCode=b.TheCode and a.CopartnerType=b.CopartnerType" +
    		"\n"+" left outer join tbdatumemployee c on a.CheckSign=c.TheCode" +
    		"\n"+"left outer join tbdatumemployee d on a.EngineSign=d.Thecode";


	        // 设置编辑框对应的列
	        editColumns.setProperty("0", "1");
	        editColumns.setProperty("1", "3");
	        editColumns.setProperty("1d", "4");
	        editColumns.setProperty("2", "5");
	        editColumns.setProperty("3", "6");
	        editColumns.setProperty("4", "9");
	        editColumns.setProperty("4d", "10");
	        editColumns.setProperty("5", "11");
	        editColumns.setProperty("6", "12");

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
	    
	    //明细信息
	    private void setDetailQueryInfo() {
	    	detailSelectSQL = "select a.BillNo, a.RowNo, a.ProjectCode,c.theName ProjectName ,a.PatrolCode," +
	    			"\n"+"b.PatrolContent, a.IsNormal,case a.IsNormal when '1' then '是' else '否' end IsNormalName, a.Abnordesc from tbeleworkpatrolitem a" +
	    			"\n"+"left outer join tbpatrolreportitem b on a.ProjectCode=b.thecode and a.PatrolCode=b.Rowno" +
	    			"\n"+"left outer join tbpatrolreport c on a.ProjectCode=c.thecode ";

		detailOrderSQL = "order by a.RowNo ";

		// 设置列属性(类型，标题，宽度，数据列)
		detailColumnsInfo.clear();
		Properties prop1 = new Properties();
		detailColumnsInfo.add(prop1);
		prop1.setProperty("type", "radio");
		prop1.setProperty("title", "选择");
		prop1.setProperty("width", "5%");
		prop1.setProperty("data", "1");

		Properties prop2 = new Properties();
		prop2.setProperty("title", "项目名称");
		prop2.setProperty("data", "3");
		detailColumnsInfo.add(prop2);

		Properties prop3 = new Properties();
		prop3.setProperty("title", "检查内容");
		prop3.setProperty("data", "5");
		detailColumnsInfo.add(prop3);

		Properties prop4 = new Properties();
		prop4.setProperty("title", "是否正常");
		prop4.setProperty("data", "7");
		detailColumnsInfo.add(prop4);

		Properties prop5 = new Properties();
		prop5.setProperty("title", "异常描述");
		prop5.setProperty("data", "8");
		detailColumnsInfo.add(prop5);

	    }
	    private void setDetailEditComp() throws wlglException {
	        EditComponent ec1 = new EditComponent();
	        ec1.setEdtType("bool");
	        ec1.setCaption("正常");
	        ec1.setField("IsNormal");
	        ec1.setDisID("IsNormalName");


	        EditComponent ec2 = new EditComponent();
	        ec2.setEdtType("textarea");
	        ec2.setCaption("异常描述");
	        ec2.setField("Abnordesc");


	        // 设置界面每行内容
	        HttpBase tre1 = HttpBase.genHttpTR("btd");
	        tre1.getElements().add(ec1.genHttpBase());
	        
	        HttpBase tre2 = HttpBase.genHttpTR("btd");
	        tre2.getElements().add(ec2.genHttpBase());

	        // 增加到编辑控件列表
	        editComps.clear();
	        editComps.add(ec1);
	        editComps.add(ec2);

	        // 增加到编辑栏列表
	        editPanels.clear();
	        editPanels.add(tre1);
	        editPanels.add(tre2);
	    }
 
	    private void setDetailUpdateInfo() {
	    	
	        ArrayList ret = new ArrayList();
	        detailSelectSQL = "select a.BillNo, a.RowNo, a.ProjectCode,c.theName ProjectName ,a.PatrolCode," +
			"\n"+"b.PatrolContent, a.IsNormal,case a.IsNormal when '1' then '是' else '否' end IsNormalName, a.Abnordesc from tbeleworkpatrolitem a" +
			"\n"+"left outer join tbpatrolreportitem b on a.ProjectCode=b.thecode and a.PatrolCode=b.Rowno" +
			"\n"+"left outer join tbpatrolreport c on a.ProjectCode=c.thecode ";
	        
	        detailOrderSQL = "order by a.RowNo";

	        // 设置编辑框对应的列

	        editColumns.setProperty("0", "6");
	        editColumns.setProperty("0d", "7");
	        editColumns.setProperty("1", "8");
	        
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
	           if (!inDetailMode) {//主表保存时生成子表信息.
		           	String tmpSQL = "insert Into  tbeleworkpatrolitem (BillNo,RowNo,ProjectCode,PatrolCode) values(?,?,?,?)";
					ArrayList paramsvalue = new ArrayList();
					int RowNo=1;
					String RowNoStr="";
					try {
						utildb.exeQuery("select  TheCode,RowNo from tbpatrolreportitem where RowTheState=1");
						while (utildb.myRs.next()) {
							paramsvalue.add(seqn);
							RowNoStr= Integer.toString(RowNo);
							paramsvalue.add(RowNoStr);
							RowNo++;
							paramsvalue.add(utildb.myRs.getString(1));
							paramsvalue.add(utildb.myRs.getString(2));
							utildb.exeUpdate(tmpSQL, paramsvalue);
							paramsvalue.clear();
						}
					} catch (Exception ex) {
						throw new wlglException("自动生成巡检项目");
					}
		           }
	    }

	    private void jbInit() throws Exception {
	    }

}
