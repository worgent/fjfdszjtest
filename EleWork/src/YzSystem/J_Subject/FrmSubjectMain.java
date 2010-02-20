package YzSystem.J_Subject;

import java.util.*;

import YzSystem.JMain.*;

public class FrmSubjectMain extends FrmFunctionBase {
	private String subjectSort="";

	   public FrmSubjectMain() throws wlglException
	   {
		super();
		Table = "tssystemrecord";
		seqnField = "TheCode";
		DetailTable = "tssystemsubjectrecord";
		DetailSeqnField = "RowNo";
		pageType = "masterDetail";

		subjectSort = UtilWebTools.getRequestParameter("tseqn");
		if (!inDetailMode) {
			// =========================查询设置==============================================
			if (mode.equals("query")) {
				// 设置查询组件
				setQueryComp();
				// 设置查询信息
				setQueryInfo();
			}
			// =========================添加设置==============================================
			// 设置添加组件
			if (mode.equals("add")) {
				setUpdateComp();
				setAddInfo();
			}
			// =========================修改设置==============================================
			// 设置修改组件
			if (mode.equals("edit")) {
				setUpdateComp();
				setUpdateInfo();
				setDetailQueryInfo();
			}
		} else {
			// =========================添加设置==============================================

			setDetailEditComp();
			// 设置添加组件
			if (mode.equals("add")) {
				setDetailAddInfo();
			}
			// =========================修改设置==============================================
			// 设置修改组件
			if (mode.equals("edit")) {
				setDetailUpdateInfo();
			}
		}
	}


	    /**
		 * setQueryComp 设置需要的查询组件
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
	    private void setQueryInfo() {
	        qry_selectSQL =
                "select a.TheCode, a.TheName, a.SubjectSort,b.TheName SubjectName,a.flag,a.Remark "
                +" from tssystemrecord a"
                +" left outer join tssystemfunction b on a.SubjectSort=b.TheCode";
	        
	        qry_whereSQL=" a.SubjectSort='"+subjectSort+"'";

	        qry_orderSQL = "order by a.TheName desc ";

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
	        prop4.setProperty("title", "科目类别");
	        prop4.setProperty("data", "3");
	        columnsInfo.add(prop4);
	        
	        Properties prop6 = new Properties();
	        prop6.setProperty("title", "简称");
	        prop6.setProperty("data", "4");
	        columnsInfo.add(prop6);



	        Properties prop5 = new Properties();
	        prop5.setProperty("title", "备注");
	        prop5.setProperty("data", "5");
	        columnsInfo.add(prop5);
	    }
	    /**
	     * setAddComp
	     * 设置需要的编辑组件
	     */
	    private void setUpdateComp() throws wlglException {
	        EditComponent ec1 = new EditComponent();
	        ec1.setCaption("编号");
	        ec1.setField("thecode");
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
	        
	        EditComponent ec3 = new EditComponent();
	        ec3.setCaption("简称");
	        ec3.setField("flag");
	        ec3.setWidth("30");


	        EditComponent ec4 = new EditComponent();
	        ec4.setCaption("备注");
	        ec4.setField("Remark");
	        ec4.setWidth("30");
	        ec4.setColspan("5");
	        
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
	        
	        

	        
	        //科目类别
	        Properties prop7 = new Properties();
	        ret.add(prop7);
	        prop7.setProperty("type", "data");
	        prop7.setProperty("field", "SubjectSort");
	        prop7.setProperty("value", subjectSort);

	        
	        addExtraColumns.addAll(ret);
 
	    }



	    
	    
	    /**
	     * setAddInfo
	     * 设置需要的编辑组件
	     */
	    private void setUpdateInfo() {
	        ArrayList ret = new ArrayList();
	        edt_selectSQL ="select a.TheCode, a.TheName, a.flag, a.Remark," +
	        		  "\n"+"a.Stater,a.StateTime,a.Editer,a.EditeTime from tssystemrecord a";
	        
	        edt_whereSQL=" a.SubjectSort='"+subjectSort+"'";


	        // 设置编辑框对应的列
	        editColumns.setProperty("0", "0");
	        editColumns.setProperty("1", "1");
	        editColumns.setProperty("2", "2");
	        editColumns.setProperty("3", "3"); 

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
	    
	    //明细
	    
	    /**
	     * setQueryInfo
	     * 设置需要的查询信息
	     */
	    private void setDetailQueryInfo() {

	    	detailSelectSQL  =
                "select a.TheCode,c.TheName RecordName, a.RowNo,a.TheName, a.SubjectSort,b.TheName SubjectName,a.RemarkItem "
                +" from tssystemsubjectrecord a"
                +" left outer join tssystemfunction b on a.SubjectSort=b.TheCode"+
                "\n"+" left outer join tssystemrecord c on a.TheCode=c.TheCode  and a.SubjectSort=c.SubjectSort";
        
	    	detailWhereSQL =" a.SubjectSort='"+subjectSort+"'";

	    	detailOrderSQL  = "order by a.TheName desc ";
	    	

	        // 设置列属性(类型，标题，宽度，数据列)
	        //columnsInfo.clear();
	        detailColumnsInfo.clear();
	        Properties prop1 = new Properties();
	        detailColumnsInfo.add(prop1);
	        prop1.setProperty("type", "radio");
	        prop1.setProperty("title", "选择");
	        prop1.setProperty("width", "5%");
	        prop1.setProperty("data", "2");
	        
	        Properties prop2 = new Properties();
	        prop2.setProperty("title", "编号");
	        prop2.setProperty("data", "2");
	        detailColumnsInfo.add(prop2);

	        Properties prop3 = new Properties();
	        prop3.setProperty("title", "名称");
	        prop3.setProperty("data", "3");
	        detailColumnsInfo.add(prop3);

	        Properties prop6 = new Properties();
	        prop6.setProperty("title", "备注");
	        prop6.setProperty("data", "6");
	        detailColumnsInfo.add(prop6);
	    }
	    
	    private void setDetailEditComp() throws wlglException {

	        EditComponent ec1 = new EditComponent();
	        ec1.setField("TheName");
	        ec1.setCaption("名称");
	        ec1.setMustFill(true);
	  

	        EditComponent ec2 = new EditComponent();
	        ec2.setField("RemarkItem");
	        ec2.setCaption("备注");
	        ec2.setWidth("50");
	        ec2.setColspan("5");

	        HttpBase tre1 = HttpBase.genHttpTR("btd");
	        tre1.getElements().add(ec1.genHttpBase());
	        tre1.getElements().add(ec2.genHttpBase());


	        // 增加到编辑控件列表
	        editComps.clear();
	        editComps.add(ec1);
	        editComps.add(ec2);

	        // 增加到编辑栏列表
	        editPanels.clear();
	        editPanels.add(tre1);
	    }
	    
	    /**
	     * setAddInfo
	     * 设置需要的编辑组件
	     */
	    private void setDetailUpdateInfo() {
	        ArrayList ret = new ArrayList();
	        detailSelectSQL = "select a.TheCode,c.TheName RecordName, a.RowNo,a.TheName, a.SubjectSort,b.TheName SubjectName,a.RemarkItem "
                +" from tssystemsubjectrecord a"
                +" left outer join tssystemfunction b on a.SubjectSort=b.TheCode"+
                "\n"+" left outer join tssystemrecord c on a.TheCode=c.TheCode  and a.SubjectSort=c.SubjectSort";
	    	detailWhereSQL =" a.SubjectSort='"+subjectSort+"'";

	    	detailOrderSQL  = "order by a.TheCode ";

	        
	        // 设置编辑框对应的列
	        editColumns.setProperty("0", "3");
	        editColumns.setProperty("1", "6");
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
	        prop2.setProperty("field", "TheCode");
	        
	        Properties prop3 = new Properties();
	        ret.add(prop3);
	        prop3.setProperty("type", "data");
	        prop3.setProperty("field", "SubjectSort");
	        prop3.setProperty("value", subjectSort);
	        
	        addExtraColumns.addAll(ret);
	    }

	    protected void doDelete(String atable, String aseqnfield, String aseqn)
			throws wlglException {

	        UtilDB utilDB = new UtilDB();
	        utilDB.beginTransaction();
	        try {
	            if (inDetailMode) {
	                beforeDelete(utilDB);
	                String tmpSQL =
	                        "delete from " + DetailTable + " where " +
	                        DetailSeqnField + " ='" +
	                        detailseqn + "'  and "+seqnField+"='"+seqn+"'" +" and SubjectSort='"+subjectSort+"'";
	                utilDB.exeUpdate(tmpSQL);
	                afterDelete(utilDB);
	                utilDB.commit();
	                utilDB.closeCon();

	            } else {
	                beforeDelete(utilDB);
	                String tmpSQL = "";
	                if (pageType.equals("masterDetail")) {
	                    tmpSQL =
	                            "delete from " + DetailTable + " where " +
	                            seqnField + "='" +seqn + "' "+" and SubjectSort='"+subjectSort+"'";
	                    utilDB.exeUpdate(tmpSQL);
	                }
	                tmpSQL = "delete from " + Table + " where " + seqnField +
	                         "='" +seqn + "' "+" and SubjectSort='"+subjectSort+"'";
	                utilDB.exeUpdate(tmpSQL);
	                afterDelete(utilDB);
	                utilDB.commit();
	                utilDB.closeCon();
	            }
	        } catch (wlglException ex) {
	            utilDB.rollback();
	            utilDB.closeCon();
	            throw ex;
	        }
	}


	    private void jbInit() throws Exception {
	    }
}
