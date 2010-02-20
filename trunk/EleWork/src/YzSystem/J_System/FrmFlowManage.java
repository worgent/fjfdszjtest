package YzSystem.J_System;

import java.util.ArrayList;
import java.util.Properties;

import YzSystem.JMain.EditComponent;
import YzSystem.JMain.FrmFunctionBase;
import YzSystem.JMain.HttpBase;
import YzSystem.JMain.UtilDB;
import YzSystem.JMain.UtilWLGLFactory;
import YzSystem.JMain.UtilWebFactory;
import YzSystem.JMain.UtilWebTools;
import YzSystem.JMain.wlglException;

public class FrmFlowManage extends FrmFunctionBase {

	    public FrmFlowManage() throws wlglException {
	        super();

	        // 设置页面标题
	        Table = "tbstorageflow";
	        seqnField = "BillNo";
	        DetailTable = "tbStorageFlowItem";
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
	        EditComponent e1 = new EditComponent("",
	                                             "流程编号",
	                                             "a.BillNo",
	                                             "like",
	                                             "QBillNo",
	                                             "",
	                                             "",
	                                             "",
	                                             "",
	                                             "");
	        //e1.setMode("readonly");
	        EditComponent e2 = new EditComponent("",
	                                             "流程名称",
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
	        qry_selectSQL = "select a.BillNo, a.TheName, a.Remark from tbstorageflow a";
	        
	       // qry_whereSQL = " TheCode='" +
	       //            billType + "' ";

	        qry_orderSQL = "order by a.BillNo ";

	        // 设置列属性(类型，标题，宽度，数据列)
	        columnsInfo.clear();
	        Properties prop1 = new Properties();
	        columnsInfo.add(prop1);
	        prop1.setProperty("type", "radio");
	        prop1.setProperty("title", "选择");
	        prop1.setProperty("width", "5%");
	        prop1.setProperty("data", "0");

	        Properties prop2 = new Properties();
	        prop2.setProperty("title", "流程编号");
	        prop2.setProperty("data", "0");
	        columnsInfo.add(prop2);

	        Properties prop3 = new Properties();
	        prop3.setProperty("title", "流程名称");
	        prop3.setProperty("data", "1");
	        columnsInfo.add(prop3);

	        Properties prop4 = new Properties();
	        prop4.setProperty("title", "备注");
	        prop4.setProperty("data", "2");
	        columnsInfo.add(prop4); 

	    }

	    /**
	     * setAddComp
	     * 设置需要的编辑组件
	     */
	    protected void setUpdateComp() throws wlglException {
	        editPanels.clear();
	     
	        EditComponent ec1 = new EditComponent();
	        ec1.setCaption("编号");
	        ec1.setField("billno");
	        ec1.setUnique(true);
	        ec1.setMustFill(true);
	        if(!mode.equals("add"))
	        {
	        	ec1.setMode("readonly");
	        }


	        EditComponent ec2 = new EditComponent();
	        ec2.setCaption("名称");
	        ec2.setField("TheName");
	        ec2.setMustFill(true);
	        
	        EditComponent ec3 = new EditComponent();
	        ec3.setCaption("备注");
	        ec3.setField("Remark");
	        ec3.setWidth("180");
	        ec3.setColspan("5");

	        // 设置界面每行内容
	        HttpBase tre1 = HttpBase.genHttpTR("btd");
	        tre1.getElements().add(ec1.genHttpBase());
	        tre1.getElements().add(ec2.genHttpBase());

	        
	        HttpBase tre2 = HttpBase.genHttpTR("btd");
	        tre2.getElements().add(ec3.genHttpBase());

	        // 增加到编辑控件列表
	        editComps.clear();
	        editComps.add(ec1);
	        editComps.add(ec2);
	        editComps.add(ec3);
	        
	        // 增加到编辑栏列表
	        editPanels.clear();
	        editPanels.add(tre1);
	        editPanels.add(tre2);

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
	    protected void setUpdateInfo() {
	        ArrayList ret = new ArrayList();
	        
	        edt_selectSQL ="select a.BillNo, a.TheName, a.Remark from tbstorageflow a";

	        //edt_whereSQL =
	        //        " TheCode='" +
	         //       billType + "' ";    
	        // 设置编辑框对应的列
	        editColumns.setProperty("0", "0");
	        editColumns.setProperty("1", "1");
	        editColumns.setProperty("2", "2");

	   
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
	    /**
	     * setDetailInfo
	     * 设置详表信息
	     */
	    private void setDetailQueryInfo() {
	        // 详表参数
	        detailSelectSQL = "select 	a.BillNo, a.RowNo, a.TheName, a.ArrayChildRowNo,a.IsFlowMust ,case a.IsFlowMust when '1' then '是' else '否' end IsFlowMustName," +
	        		"\n"+"a.IsSameDep,case a.IsSameDep when '1' then '是' else '否' end IsSameDepName, a.IsFlowers,case a.IsFlowers when '1' then '是' else '否'" +
	        		"\n"+"end IsFlowersName,a.ApproveCodeArray,a.ApproveCodeArray ApproveNameArray, a.remarkitem from tbstorageflowitem a";
	        //detailWhereSQL =" "; // 详表条件
	        detailOrderSQL = " order by a.RowNo"; // 详表排序

	        // 设置列属性(类型，标题，宽度，数据列)
	        detailColumnsInfo.clear();
	        Properties prop1 = new Properties();
	        prop1.setProperty("title", "选择");
	        prop1.setProperty("type", "radio");
	        prop1.setProperty("data", "1");
	        detailColumnsInfo.add(prop1);

	        Properties prop2 = new Properties();
	        prop2.setProperty("title", "环节编号");
	        prop2.setProperty("data", "1");
	        detailColumnsInfo.add(prop2);

	        Properties prop3 = new Properties();
	        prop3.setProperty("title", "环节名称");
	        prop3.setProperty("data", "2");
	        detailColumnsInfo.add(prop3);

	        Properties prop4 = new Properties();
	        prop4.setProperty("title", "环节是否必须");
	        prop4.setProperty("data", "5");
	        detailColumnsInfo.add(prop4);
	      
	        Properties prop5 = new Properties();
	        prop5.setProperty("title", "是否可跨部门");
	        prop5.setProperty("data", "7");
	        detailColumnsInfo.add(prop5);
	        
	        Properties prop6 = new Properties();
	        prop6.setProperty("title", "是否需多人");
	        prop6.setProperty("data", "9");
	        detailColumnsInfo.add(prop6);
	        
	        Properties prop7 = new Properties();
	        prop7.setProperty("title", "审批人员");
	        prop7.setProperty("data", "10");
	        detailColumnsInfo.add(prop7);

	        Properties prop11 = new Properties();
	        prop11.setProperty("title", "备注");
	        prop11.setProperty("data", "12");
	        detailColumnsInfo.add(prop11);
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


	    /**
	     * setDetailAddComp
	     * 设置需要的编辑组件
	     */
	    private void setDetailEditComp() throws wlglException {

	        EditComponent ec1 = new EditComponent();
	        ec1.setField("TheName");
	        ec1.setCaption("环节名称");
	        ec1.setMustFill(true);


	        EditComponent ec2 = new EditComponent();
	        ec2.setEdtType("bool");
	        ec2.setField("IsFlowMust");
	        ec2.setDisID("IsFlowMustName");
	        ec2.setCaption("环节是否必须");

	        EditComponent ec3 = new EditComponent();
	        ec3.setEdtType("bool");
	        ec3.setField("IsSameDep");
	        ec3.setDisID("IsSameDepName");
	        ec3.setCaption("是否可跨部门");


	        EditComponent ec4 = new EditComponent();
	        ec4.setEdtType("bool");
	        ec4.setField("IsFlowers");
	        ec4.setDisID("IsFlowersName");
	        ec4.setCaption("是否需多人");
	        
	        EditComponent ec5 = new EditComponent();
	        ec5.setEdtType("EmployeeEx");
	        ec5.setField("ApproveCodeArray");
	        ec5.setDisID("ApproveNameArray");
	        ec5.setCaption("审批人员");
	        ec5.setDefDisValue("＝请选择＝");
	        ec5.setMode("readonly");
	        ec5.setColspan("3");


	        EditComponent ec6 = new EditComponent();
	        ec6.setField("remarkitem");
	        ec6.setCaption("备注");
	        ec6.setColspan("5");
	        ec6.setWidth("120");

	        HttpBase tre1 = HttpBase.genHttpTR("btd");
	        tre1.getElements().add(ec1.genHttpBase());
	        tre1.getElements().add(ec2.genHttpBase());
	        tre1.getElements().add(ec3.genHttpBase());


	        HttpBase tre2 = HttpBase.genHttpTR("btd");
	        tre2.getElements().add(ec4.genHttpBase());
	        tre2.getElements().add(ec5.genHttpBase());
        
	        
	        HttpBase tre3 = HttpBase.genHttpTR("btd");
	        tre3.getElements().add(ec6.genHttpBase());      
	        
	        // 增加到编辑控件列表
	        editComps.clear();
	        editComps.add(ec1);
	        editComps.add(ec2);
	        editComps.add(ec3);
	        editComps.add(ec4);
	        editComps.add(ec5);
	        editComps.add(ec6);

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
	        detailSelectSQL =  "select 	a.BillNo, a.RowNo, a.TheName, a.ArrayChildRowNo,a.IsFlowMust ,case a.IsFlowMust when '1' then '是' else '否' end IsFlowMustName," +
	        		"\n"+"a.IsSameDep,case a.IsSameDep when '1' then '是' else '否' end IsSameDepName, a.IsFlowers,case a.IsFlowers when '1' then '是' else '否'" +
	        		"\n"+"end IsFlowersName,''  ApproveCodeArray,'' ApproveNameArray,a.remarkitem from tbstorageflowitem a";
	        //detailWhereSQL =
	          //      "  TheCode='" +
	           //     billType + "'  ";

	        
	        // 设置编辑框对应的列
	        editColumns.setProperty("0", "2");
	        editColumns.setProperty("1", "4");
	        editColumns.setProperty("1d", "5");
	        editColumns.setProperty("2", "6");
	        editColumns.setProperty("2d", "7");
	        editColumns.setProperty("3", "8");
	        editColumns.setProperty("3d", "9");
	        editColumns.setProperty("4", "10");
	        editColumns.setProperty("4d", "11");
	        editColumns.setProperty("5", "12");
	        // 设置附加的修改列属性(类型，标题，宽度，数据列)
	        ret.clear();
	        /*
	        Properties prop5 = new Properties();
	        prop5.setProperty("type", "comp");
	        prop5.setProperty("field", "realamount");
	        prop5.setProperty("comp", "oughtamount");
	        ret.add(prop5);
	        */

	        addExtraColumns.addAll(ret);
	    }

	    protected String validate() throws wlglException {
	        // 默认校验
	        String val = super.validate();
	        if (!val.equals("")) {
	            return val;
	        }
	        // 校验是否产品重复
	        if (inDetailMode) {
	            UtilDB utilDB = new UtilDB();

	          ArrayList param = new ArrayList();
	          param.add(UtilWebTools.getRequestParameter("BillNo"));
	          param.add(UtilWebTools.getRequestParameter("ProductCode"));
	          int count = 0;

	              ArrayList rs = utilDB.exeQueryOneRow(
	                      "select count(*) from tbstorageapplyitem "
	                      + " where BillNo=? and ProductCode=? ", param);
	              count = Integer.parseInt((String) rs.get(0));

	          utilDB.closeCon();

	          if (count > 0) {
	              val = "该商品已选择,不允许选择重复的商品!";
	          }


	        }
	        return val;
	    }
}
