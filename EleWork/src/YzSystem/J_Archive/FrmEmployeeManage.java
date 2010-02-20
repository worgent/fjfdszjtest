package YzSystem.J_Archive;

import java.util.ArrayList;
import java.util.Properties;

import YzSystem.JMain.EditComponent;
import YzSystem.JMain.FrmFunctionBase;
import YzSystem.JMain.HttpBase;
import YzSystem.JMain.wlglException;

public class FrmEmployeeManage extends FrmFunctionBase {
	   public FrmEmployeeManage() throws wlglException {
	        Table = "tbdatumemployee";
	        seqnField = "thecode";
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
	        }
	        try {
	            jbInit();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    /**
	     * setQueryComp
	     * 设置需要的查询组件
	     */
	    private void setQueryComp() throws wlglException {
	        EditComponent e1 = new EditComponent("",
	                                             "工号",
	                                             "a.TheCode",
	                                             "like",
	                                             "QTheCode",
	                                             "",
	                                             "",
	                                             "",
	                                             "",
	                                             "");
	        EditComponent e2 = new EditComponent("",
	                                             "姓名",
	                                             "a.TheName",
	                                             "like",
	                                             "QTheName",
	                                             "",
	                                             "",
	                                             "",
	                                             "",
	                                             "");

	        // 设置界面第1行内容

	        HttpBase tr1 = HttpBase.genHttpTR("btd");
	        tr1.getElements().add(e1.genHttpBase());
	        tr1.getElements().add(e2.genHttpBase());
	        qryComps.clear();
	        qryComps.add(e1);
	        qryComps.add(e2);
	        qryPanels.clear();
	        qryPanels.add(tr1);
	    }

	    /**
	     * setQueryInfo
	     * 设置需要的查询信息
	     */
	    private void setQueryInfo() {
	        qry_selectSQL = "select a.TheCode, a.TheName, a.Sex,c.TheName SexName, a.Address, a.EmployeTypeCode,e.TheName EmployeTypeName, a.CertifyTypeCode, " +
	        		"\n"+" g.theName CertifyTypeName, a.CertifyCode, a.OtherRel, a.RelTelphone,a.InDate, a.OutDate, a.DepCode,i.TheName DepName," +
	        		"\n"+ "a.SerialCode from tbdatumemployee a  " +
	        		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
	           		"\n"+"where b.Flag='Sex') c on c.RowNo=a.Sex" +
	        		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
	           		"\n"+"where b.Flag='EmployeTypeCode') e on e.RowNo=a.EmployeTypeCode" +
	        		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
	           		"\n"+"where b.Flag='CertifyTypeCode') g on g.RowNo=a.CertifyTypeCode" + 
	        		"\n"+"left outer join tbdatumdepartment i on a.DepCode=i.Thecode";
	        qry_orderSQL = "order by a.TheCode ";

	        // 设置列属性(类型，标题，宽度，数据列)
	        columnsInfo.clear();
	        Properties prop1 = new Properties();
	        columnsInfo.add(prop1);
	        prop1.setProperty("type", "radio");
	        prop1.setProperty("title", "选择");
	        prop1.setProperty("width", "5%");
	        prop1.setProperty("data", "0");
	        
	        Properties prop2 = new Properties();
	        prop2.setProperty("title", "工号");
	        prop2.setProperty("data", "0");
	        columnsInfo.add(prop2);

	        Properties prop3 = new Properties();
	        prop3.setProperty("title", "姓名");
	        prop3.setProperty("data", "1");
	        columnsInfo.add(prop3);
	        
	        Properties prop4 = new Properties();
	        prop4.setProperty("title", "性别");
	        prop4.setProperty("data", "3");
	        columnsInfo.add(prop4);

	        Properties prop5 = new Properties();
	        prop5.setProperty("title", "工种");
	        prop5.setProperty("data", "6");
	        columnsInfo.add(prop5);

	        Properties prop6 = new Properties();
	        prop6.setProperty("title", "证件类型");
	        prop6.setProperty("data", "8");
	        columnsInfo.add(prop6);

	        Properties prop7 = new Properties();
	        prop7.setProperty("title", "证件号");
	        prop7.setProperty("data", "9");
	        columnsInfo.add(prop7);
	        
	        Properties prop8 = new Properties();
	        prop8.setProperty("title", "其他联系方式");
	        prop8.setProperty("data", "10");
	        columnsInfo.add(prop8);

	        Properties prop9 = new Properties();
	        prop9.setProperty("title", "联系电话");
	        prop9.setProperty("data", "11");
	        columnsInfo.add(prop9);

	        Properties prop10 = new Properties();
	        prop10.setProperty("title", "部门");
	        prop10.setProperty("data", "15");
	        columnsInfo.add(prop10);
	    }

	    /**
	     * setAddComp
	     * 设置需要的编辑组件
	     */
	    private void setEditComp() throws wlglException {
	        EditComponent ec1 = new EditComponent();
	        ec1.setCaption("工号");
	        ec1.setField("thecode");
	        ec1.setUnique(true);
	        ec1.setMustFill(true);
	        if(!mode.equals("add"))
	        {
	        	ec1.setMode("readonly");
	        }


	        EditComponent ec2 = new EditComponent();
	        ec2.setCaption("姓名");
	        ec2.setField("theName");
	        ec2.setMustFill(true);
	        

	        EditComponent ec3 = new EditComponent();
	        ec3.setEdtType("Sex");
	        ec3.setCaption("性别");
	        ec3.setField("Sex");
	        ec3.setDisID("SexName");
	        
	   
	        
	        EditComponent ec4 = new EditComponent();
	        ec4.setEdtType("CertifyTypeCode");
	        ec4.setCaption("证件类型");
	        ec4.setField("CertifyTypeCode");
	        ec4.setDisID("CertifyTypeName");

	        EditComponent ec5 = new EditComponent();
	        ec5.setCaption("证件号");
	        ec5.setField("CertifyCode");
	        ec5.setWidth("30");     

	        EditComponent ec6 = new EditComponent();
	        ec6.setCaption("联系电话");
	        ec6.setField("RelTelphone");
	        ec6.setWidth("30");
	        

	        EditComponent ec7 = new EditComponent();
	        ec7.setEdtType("EmployeTypeCode");
	        ec7.setCaption("工种");
	        ec7.setField("EmployeTypeCode");
	        ec7.setDisID("EmployeTypeName");
	        
	        EditComponent ec8 = new EditComponent();
	        ec8.setCaption("地址");
	        ec8.setField("Address");
	        ec8.setWidth("30");
	       

	        EditComponent ec9 = new EditComponent();
	        ec9.setCaption("其他联系方式");
	        ec9.setField("OtherRel");
	        ec9.setWidth("30");
           
	        

	        EditComponent ec10 = new EditComponent();
	        ec10.setEdtType("Department");
	        ec10.setCaption("部门");
	        ec10.setField("DepCode");
	        ec10.setDisID("DepName");
	        
	        EditComponent ec11 = new EditComponent();
	        ec11.setEdtType("date");
	        ec11.setCaption("入职时间");
	        ec11.setField("InDate");

	        EditComponent ec12 = new EditComponent();
	        ec12.setEdtType("date");
	        ec12.setCaption("离职时间");
	        ec12.setField("OutDate");
	        

	        

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

	        // 增加到编辑栏列表
	        editPanels.clear();
	        editPanels.add(tre1);
	        editPanels.add(tre2);
	        editPanels.add(tre3);
	        editPanels.add(tre4);
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
	        
	        

	        addExtraColumns.addAll(ret);
	    }



	    /**
	     * setAddInfo
	     * 设置需要的编辑组件
	     */
	    private void setUpdateInfo() {
	        ArrayList ret = new ArrayList();
	        edt_selectSQL ="select a.TheCode, a.TheName, a.Sex,c.TheName SexName,  a.CertifyTypeCode, g.theName CertifyTypeName, a.CertifyCode, a.RelTelphone, " +
    		"\n"+"a.EmployeTypeCode,e.TheName EmployeTypeName,a.Address,a.OtherRel,  a.DepCode,i.TheName DepName, a.InDate, a.OutDate," +
    		"\n"+ "a.SerialCode from tbdatumemployee a  " +
       		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
       		"\n"+"where b.Flag='Sex') c on c.RowNo=a.Sex" +
       		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
       		"\n"+"where b.Flag='EmployeTypeCode') e on e.RowNo=a.EmployeTypeCode" + 		
       		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
       		"\n"+"where b.Flag='CertifyTypeCode') g on g.RowNo=a.CertifyTypeCode" + 
    		"\n"+"left outer join tbdatumdepartment i on a.DepCode=i.Thecode";
	        
	        //edt_whereSQL="d.flag='Sex' and  f.flag='EmployeTypeCode'and  h.flag='CertifyTypeCode' ";
	        

	        edt_orderSQL = "order by a.TheCode ";


	        // 设置编辑框对应的列
	        editColumns.setProperty("0", "0");
	        editColumns.setProperty("1", "1");
	        editColumns.setProperty("2", "2");
	        editColumns.setProperty("2d", "3");
	        editColumns.setProperty("3", "4");
	        editColumns.setProperty("3d", "5");
	        editColumns.setProperty("4", "6");
	        editColumns.setProperty("5", "7");
	        editColumns.setProperty("6", "8");
	        editColumns.setProperty("6d", "9");
	        editColumns.setProperty("7", "10");
	        editColumns.setProperty("8", "11");
	        editColumns.setProperty("9", "12");
	        editColumns.setProperty("9d", "13");
	        editColumns.setProperty("10", "14");
	        editColumns.setProperty("11", "15");
	        

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

	    private void jbInit() throws Exception {
	    }


}
