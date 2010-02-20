package YzSystem.JMain;

import java.util.Properties;

public class FrmSelectClient extends FrmFunctionBase {
	
	   public FrmSelectClient() throws wlglException {
	        super();
	        // 设置页面标题
	        caption = "客户选择";

	        // 设置查询组件
	        qryComps.clear();
	        qryComps.add(new EditComponent("","客户编号", "a.TheCode","like","QTheCode","","","","",""));
	        qryComps.add(new EditComponent("","客户名称","a.TheName","like","QTheName","","","","",""));
	        qryComps.add(new EditComponent("CopartnerType","客户类别", "a.CopartnerType","=","QCopartnerType","","","","",""));

	        // 设置查询SQL
	        qry_selectSQL ="select a.TheCode ClientCode, a.CopartnerType,a.TheName ClientName, a.TheShortName, c.TheName CopartnerTypeName, a.RelFax, a.RelTelephone, a.RelName, a.Remark" +
	               "\n"+ "from tbdatumcopartner a" +
	        		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
	           		"\n"+"where b.Flag='CopartnerType') c on c.RowNo=a.CopartnerType" ;
			
	        
	        qry_orderSQL = "order by a.TheCode desc ";

	        // 设置列属性
	        columnsInfo.clear();
	        Properties prop1= new Properties();
	        columnsInfo.add(prop1);
	        prop1.setProperty("type","choose");
	        prop1.setProperty("title","客户编号");
	        prop1.setProperty("data","0");
	        
	        Properties prop2= new Properties();
	        columnsInfo.add(prop2);
	        prop2.setProperty("type","choose");
	        prop2.setProperty("title","客户名称");
	        prop2.setProperty("data","2");

	        Properties prop3= new Properties();
	        columnsInfo.add(prop3);
	        prop3.setProperty("type","choose");
	        prop3.setProperty("title","简称");
	        prop3.setProperty("data","3");
	        
	        Properties prop6= new Properties();
	        columnsInfo.add(prop6);
	        prop6.setProperty("title","客户类别");
	        prop6.setProperty("data","4");
	        
	        Properties prop4= new Properties();
	        columnsInfo.add(prop4);
	        prop4.setProperty("title","传真");
	        prop4.setProperty("data","5");
	        
	        Properties prop7= new Properties();
	        columnsInfo.add(prop7);
	        prop7.setProperty("title","联系电话");
	        prop7.setProperty("data","6");
	        
	        Properties prop5= new Properties();
	        columnsInfo.add(prop5);
	        prop5.setProperty("title","联系人");
	        prop5.setProperty("data","7");
	        
	        Properties prop8= new Properties();
	        columnsInfo.add(prop8);
	        prop8.setProperty("title","备注");
	        prop8.setProperty("data","8");

	    }
}
