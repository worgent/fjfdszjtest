package YzSystem.J_System;

import YzSystem.JMain.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.sql.*;

/**
 * <p>
 * Title:jobManage.jsp窗体处理代码
 * </p>
 * 
 * <p>
 * Description: 处理jobManage.jsp主程序
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: 泉州邮政信息技术中心
 * </p>
 * 
 * @author qsy
 * @version 1.0 历史: 2005-04-13 生成代码
 */
public class FrmGroupManage extends FrmFunctionBase{

	   public FrmGroupManage() throws wlglException {
	        super();
	        Table = "tssystemgroup";
	        seqnField = "thecode";
	        DetailTable = "tssystemgrouppower";
	        DetailSeqnField = "FunCode";
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
	        EditComponent e1 = new EditComponent("",
	                                             "编号",
	                                             "a.TheCode",
	                                             "like",
	                                             "QTheCode",
	                                             "",
	                                             "",
	                                             "",
	                                             "",
	                                             "");
	        EditComponent e2 = new EditComponent("",
	                                             "名称",
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
	        qry_selectSQL =
             "select a.TheCode, a.TheName,a.Remark "
             +" from tssystemgroup a";
	        
	        //qry_whereSQL=" a.SubjectSort='"+subjectSort+"'";

	        qry_orderSQL = "order by a.TheCode";

	        // 设置列属性(类型，标题，宽度，数据列)
	        columnsInfo.clear();
	        Properties prop1 = new Properties();
	        columnsInfo.add(prop1);
	        prop1.setProperty("type", "radio");
	        prop1.setProperty("title", "选择");
	        prop1.setProperty("width", "5%");
	        prop1.setProperty("data", "0");
	        
	        Properties prop2 = new Properties();
	        prop2.setProperty("title", "编号");
	        prop2.setProperty("data", "0");
	        columnsInfo.add(prop2);

	        Properties prop3 = new Properties();
	        prop3.setProperty("title", "名称");
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
	    private void setEditComp() throws wlglException {
	        EditComponent ec1 = new EditComponent();
	        ec1.setCaption("编号");
	        ec1.setField("thecode");
	        ec1.setUnique(true);
	        ec1.setMustFill(true);
	        if(!mode.equals("add"))
	        {
	        	ec1.setMode("readonly");
	        }
	        
	        EditComponent ec2 = new EditComponent();
	        ec2.setCaption("名称");
	        ec2.setField("theName");
	        ec2.setUnique(true);
	        ec2.setMustFill(true);
	        ec2.setWidth("30");


	        EditComponent ec3 = new EditComponent();
	        ec3.setCaption("备注");
	        ec3.setField("Remark");
	        ec3.setWidth("110");
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
	    private void setUpdateInfo() {
	        ArrayList ret = new ArrayList();
	        edt_selectSQL ="select a.TheCode, a.TheName,a.Remark from tssystemgroup a";
	        
	        //edt_whereSQL=" a.SubjectSort='"+subjectSort+"'";


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
	    	detailSelectSQL = "select a.TheCode,b.TheName GroupName, a.FunCode,c.TheName FunName, a.FunisShow, "
				+ "\n"
				+ "case a.FunisShow when '1' then '是' else '否' end FunisShowName,a.PowerValue, a.Remark"
				+ "\n"
				+ " from tssystemgrouppower a left outer join tsSystemGroup b on a.TheCode=b.TheCode"
				+ "\n"
				+ " left outer join tsSystemFunction c on a.Funcode=c.TheCode";

		// detailWhereSQL="d.flag='Sex' and f.flag='EmployeTypeCode'and
		// h.flag='CertifyTypeCode' ";

		detailOrderSQL = "order by a.FunCode ";

		// 设置列属性(类型，标题，宽度，数据列)
		detailColumnsInfo.clear();
		Properties prop1 = new Properties();
		detailColumnsInfo.add(prop1);
		prop1.setProperty("type", "radio");
		prop1.setProperty("title", "选择");
		prop1.setProperty("width", "5%");
		prop1.setProperty("data", "2");

		Properties prop2 = new Properties();
		prop2.setProperty("title", "功能模块");
		prop2.setProperty("data", "3");
		detailColumnsInfo.add(prop2);

		Properties prop3 = new Properties();
		prop3.setProperty("title", "是否显示");
		prop3.setProperty("data", "5");
		detailColumnsInfo.add(prop3);

		Properties prop4 = new Properties();
		prop4.setProperty("title", "权限值");
		prop4.setProperty("data", "6");
		detailColumnsInfo.add(prop4);

		Properties prop5 = new Properties();
		prop5.setProperty("title", "备注");
		prop5.setProperty("data", "7");
		detailColumnsInfo.add(prop5);

	    }
	    private void setDetailEditComp() throws wlglException {
	        EditComponent ec1 = new EditComponent();
	        ec1.setEdtType("Function");
	        ec1.setCaption("功能模块");
	        ec1.setField("FunCode");
	        ec1.setDisID("FunName");
	        ec1.setMustFill(true);
	        ec1.setUnique(true);
	        if(!mode.equals("add"))
	        {
	        	ec1.setMode("readonly");
	        }


	        EditComponent ec2 = new EditComponent();
	        ec2.setEdtType("bool");
	        ec2.setCaption("是否显示");
	        ec2.setField("FunisShow");
	        ec2.setDisID("FunisShowName");
	        

	        EditComponent ec3 = new EditComponent();
	        ec3.setCaption("权限值(查,修,增,删)");
	        ec3.setField("PowerValue");

	        EditComponent ec4 = new EditComponent();
	        ec4.setCaption("备注");
	        ec4.setField("Remark");
	        ec4.setColspan("5");
	        ec4.setWidth("100");
	        

	        // 设置界面每行内容
	        HttpBase tre1 = HttpBase.genHttpTR("btd");
	        tre1.getElements().add(ec1.genHttpBase());
	        tre1.getElements().add(ec2.genHttpBase());
	        tre1.getElements().add(ec3.genHttpBase());
	        
	        HttpBase tre2 = HttpBase.genHttpTR("btd");
	        tre2.getElements().add(ec4.genHttpBase());

	        

	        // 增加到编辑控件列表
	        editComps.clear();
	        editComps.add(ec1);
	        editComps.add(ec2);
	        editComps.add(ec3);
	        editComps.add(ec4);


	        // 增加到编辑栏列表
	        editPanels.clear();
	        editPanels.add(tre1);
	        editPanels.add(tre2);
	    }
  
	    private void setDetailUpdateInfo() {
	    	
	        ArrayList ret = new ArrayList();
	        detailSelectSQL ="select a.TheCode,b.TheName GroupName, a.FunCode,c.TheName FunName, a.FunisShow, " +
    		"\n"+"case a.FunisShow when '1' then '是' else '否' end FunisShowName,a.PowerValue, a.Remark" +
    		"\n"+" from tssystemgrouppower a left outer join tsSystemGroup b on a.TheCode=b.TheCode" +
    		"\n"+" left outer join tsSystemFunction c on a.Funcode=c.TheCode";
	        
	        
	        //edt_whereSQL="d.flag='Sex' and  f.flag='EmployeTypeCode'and  h.flag='CertifyTypeCode' ";
	        

	        detailOrderSQL = "order by a.TheCode ";


	        // 设置编辑框对应的列

	        editColumns.setProperty("0", "2");
	        editColumns.setProperty("0d", "3");
	        editColumns.setProperty("1", "4");
	        editColumns.setProperty("1d", "5");
	        editColumns.setProperty("2", "6");
	        editColumns.setProperty("3", "7");
	        
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
	    
	    private void setDetailAddInfo() {
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
	        
	        
	        
	        Properties prop7 = new Properties();
	        ret.add(prop7);
	        prop7.setProperty("type", "seqn");
	        prop7.setProperty("field", "TheCode");
	        
	        

	        addExtraColumns.addAll(ret);
	    }
	    
	    
	    
	    //增加事件
	    protected void afterAddSave(UtilDB utildb) throws wlglException {
	    		
            if (!inDetailMode) {//主表保存时生成子表信息.
            	String tmpSQL = "insert Into  tssystemgrouppower (TheCode,FunCode,FunisShow,PowerValue) values(?,?,?,?)";
			ArrayList paramsvalue = new ArrayList();
			try {
				utildb.exeQuery("select  TheCode from tsSystemFunction");
				while (utildb.myRs.next()) {
					
					paramsvalue.add(seqn);
					paramsvalue.add(utildb.myRs.getString(1));
					paramsvalue.add("1");
					paramsvalue.add("111111");
					utildb.exeUpdate(tmpSQL, paramsvalue);
					paramsvalue.clear();
				}
			} catch (Exception ex) {
				throw new wlglException("自动生成权限表失败!");
				
			}
            }
	    }

	    private void jbInit() throws Exception {
	    }

}
