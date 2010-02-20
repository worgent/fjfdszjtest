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
public class FrmSelectProduct extends FrmFunctionBase {
    public FrmSelectProduct() throws wlglException {
        super();
        // 设置页面标题
        caption = "商品选择";

        // 设置查询组件
        qryComps.clear();
        qryComps.add(new EditComponent("","商品编号", "a.TheCode","like","QTheCode","","","","",""));
        qryComps.add(new EditComponent("","商品名称","a.TheName","like","QTheName","","","","",""));
        qryComps.add(new EditComponent("ProductTypeCode","商品名称类别", "a.ProductTypeCode","=","QProductTypeCode","","","","",""));

        // 设置查询SQL
        qry_selectSQL ="select 	a.TheCode ProductCode,a.ProductTypeCode,  a.TheName ProductName,g.TheName ProductTypeName,  a.SpecCode,c.TheName SpecName, a.UnitCode,e.TheName UnitName,a.ModelCode,b.TheName ModelName ,a.Remark" +
		"\n"+"from tbdatumproduct a " +
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='ModelCode') b on b.RowNo=a.ModelCode" +
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='SpecCode') c on c.RowNo=a.SpecCode" +
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='UnitCode') e on e.RowNo=a.UnitCode"+
   		"\n"+"left outer join (select a.RowNo,a.TheName from tssystemsubjectrecord  a left outer join tssystemrecord b on b.TheCode=a.TheCode and b.SubjectSort=b.SubjectSort" +
   		"\n"+"where b.Flag='ProductTypeCode') g on g.RowNo=a.ProductTypeCode";
		
        
        qry_orderSQL = "order by a.TheCode desc ";

        // 设置列属性
        columnsInfo.clear();
        Properties prop1= new Properties();
        columnsInfo.add(prop1);
        prop1.setProperty("type","choose");
        prop1.setProperty("title","商品编号");
        prop1.setProperty("data","0");
        
        Properties prop2= new Properties();
        columnsInfo.add(prop2);
        prop2.setProperty("type","choose");
        prop2.setProperty("title","商品名称");
        prop2.setProperty("data","2");

        Properties prop3= new Properties();
        columnsInfo.add(prop3);
        prop3.setProperty("type","choose");
        prop3.setProperty("title","商品类别");
        prop3.setProperty("data","3");
        
        /*
        Properties prop8= new Properties();
        columnsInfo.add(prop8);
        prop8.setProperty("title","型号编号");
        prop8.setProperty("data","8");
        */
        Properties prop9= new Properties();
        columnsInfo.add(prop9);
        prop9.setProperty("title","型号");
        prop9.setProperty("data","9");
        
        /*
        Properties prop6= new Properties();
        columnsInfo.add(prop6);
        prop6.setProperty("title","规格编号");
        prop6.setProperty("data","4");
        */
        
        Properties prop4= new Properties();
        columnsInfo.add(prop4);
        prop4.setProperty("title","规格");
        prop4.setProperty("data","5");
        
        /*
        Properties prop7= new Properties();
        columnsInfo.add(prop7);
        prop7.setProperty("title","单位编号");
        prop7.setProperty("data","6");
        */
        
        Properties prop5= new Properties();
        columnsInfo.add(prop5);
        prop5.setProperty("title","单位");
        prop5.setProperty("data","7");
        

    }
}
