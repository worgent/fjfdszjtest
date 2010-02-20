package YzSystem.JMain;

import java.util.ArrayList;
import java.util.Properties;

/**
 * <p>Title:selectProduct.jsp处理代码 </p>
 *
 * <p>Description: 处理selectProduct.jsp主程序</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-17  生成代码
 */
public class FrmSelectEmployee extends FrmFunctionBase {
    public FrmSelectEmployee() throws wlglException {
        super();
        // 设置页面标题
        caption = "员工选择";

        // 设置查询组件
        qryComps.clear();
        qryComps.add(new EditComponent("","工号", "a.TheCode","like","QTheCode","","","","",""));
        qryComps.add(new EditComponent("","名称","a.TheName","like","QTheName","","","","",""));
        qryComps.add(new EditComponent("EmployeTypeCode","工种", "a.EmployeTypeCode","=","QEmployeTypeCode","","","","",""));

        // 设置查询SQL
        qry_selectSQL ="select a.TheCode EmployeeCode,  a.EmployeTypeCode,a.TheName EmployeeName, a.RelTelphone, a.DepCode,i.TheName DepName" +
	        		"\n"+ " from tbdatumemployee a "+
	           		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
	           		"\n"+"where b.Flag='EmployeTypeCode') e on e.RowNo=a.EmployeTypeCode"+
	        		"\n"+"left outer join tbdatumdepartment i on a.DepCode=i.Thecode";
        
	    //qry_whereSQL=" f.flag='EmployeTypeCode' ";

        // 设置列属性
        columnsInfo.clear();
        Properties prop1= new Properties();
        columnsInfo.add(prop1);
        prop1.setProperty("type","choose");
        prop1.setProperty("title","工号");
        prop1.setProperty("data","0");
        
        Properties prop2= new Properties();
        columnsInfo.add(prop2);
        prop2.setProperty("type","choose"); 	
        prop2.setProperty("title","姓名");
        prop2.setProperty("data","2");

        Properties prop3= new Properties();
        columnsInfo.add(prop3);
        prop3.setProperty("title","部门");
        prop3.setProperty("data","5");
        
        Properties prop4= new Properties();
        columnsInfo.add(prop4);
        prop4.setProperty("title","联系电话");
        prop4.setProperty("data","3");

    }
}
