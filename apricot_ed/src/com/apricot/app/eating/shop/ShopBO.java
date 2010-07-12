/**
 * 
 */
package com.apricot.app.eating.shop;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.beanutils.LazyDynaBean;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DataSet;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class ShopBO extends BO {
	/**
	 * 
	 */
	public ShopBO() {
		// TODO Auto-generated constructor stub
	}

	public Object delete(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer("select count(1) from ");
		sql.append("storefront_info t1 where sf_status='1' ");
		if (getInt(sql) < 2)
			return getMessage("on_shop_exist", null);
		sql = new StringBuffer();
		sql.append("update storefront_info set sf_status='0' where sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		update(sql);
		return null;
	}

	public Object page(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer("select * from storefront_info t1 where sf_status='1'  ");
		sql.append(getWhereByNotNull(map, "storefront_info", "t1"));
		setRuntimeStaticData("storefront_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object getDinnerSetPages(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.sf_name from dining_set_info t1,storefront_info t2 ");
		sql.append("where t1.sf_id=t2.sf_id ");
		sql.append(getWhereByNotNull(map, "dining_set_info", "t1"));
		setRuntimeStaticData("dining_set_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object getFoodInfoPages(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.sf_name,t3.dept_id,t3.relat_type,t4.dept_name from food_info t1,storefront_info t2,food_dept_relat t3,dept_info t4 ");
		sql.append("where t1.sf_id=t2.sf_id ");
		sql.append(" and t1.food_id=t3.food_id ");
		sql.append(" and t3.dept_id=t4.dept_id ");
		sql.append(getWhereByNotNull(map, "food_info", "t1"));
		System.out.println(sql);
		setRuntimeStaticData("food_info");
		DataSet ds =  showPages(sql.toString(), map.getStartRows(), map.getPageSize());
//		System.out.println(DataUtil.getString(ds.getRowSet(), "food_price_unit"));
//		List list = (List)ds.getRowSet();
//		for(int i =0;i<list.size();i++){
//			System.out.println(list.get(i));
//			LazyDynaBean bean = (LazyDynaBean) list.get(i);
//			System.out.println(bean.get("relat_type"));
//			int temp = Integer.parseInt((String) bean.get("relat_type"));
//			if((temp & 1) ==1){
//				bean.set("relat_type1", 1);
//			}
//			if((temp & 2) ==2){
//				bean.set("relat_type2", "2");
//			}
//			if((temp & 4) ==4){
//				bean.set("relat_type3", 4);
//			}
//		}
//		for(int i =0;i<list.size();i++){
//			System.out.println(list.get(i));
//			LazyDynaBean bean = (LazyDynaBean) list.get(i);
//			System.out.println(bean.get("relat_type"));
//			System.out.println(bean.get("relat_type1"));
//			System.out.println(bean.get("relat_type2"));
//			System.out.println(bean.get("relat_type3"));
//		}
		return ds;
	}

	public Object getFoodMapMaterial(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.FOOD_NAME,t2.food_price,t3.mat_name,t3.mat_price ");
		sql.append("from food_material_info t1,food_info t2,material_info t3 ");
		sql.append("where t1.food_id=t2.food_id and t1.mat_id=t3.mat_id and t1.food_id=");
		sql.append(map.getString("food_id", "-1"));
		setRuntimeStaticData("food_material_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object delFoodMapMaterial(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from food_material_info where food_id=");
		sql.append(map.getString("food_id", "-1"));
		sql.append(" and mat_id=");
		sql.append(map.getString("mat_id", "-1"));
		update(sql);
		return null;
	}

	/**
	 * <p>
	 * 批量添加餐位信息
	 * </p>
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object multiAddDinnerSet(DyncParameterMap map) throws NestedException {
		int startNo = map.getInt("balcony_code_start");
		int endNo = map.getInt("balcony_code_end");
		StringBuffer exist = new StringBuffer();
		exist.append("select count(*) from dining_set_info ");
		exist.append("where balcony_code={0} and sf_id={1}");
		int nameSize = map.getString("balcony_name").length();
//		System.out.println(map.getString("balcony_name"));
//		System.out.println(nameSize);
		for (; startNo <= endNo; startNo++) {
			String name = map.getString("balcony_name");
			String newName = "";
			newName = name.replaceAll("[']","");
			newName = newName.substring(0,nameSize);
			map.set("balcony_name", newName+startNo);
			String[] args = new String[] {
					"'"+DataUtil.lpad(String.valueOf(startNo), '0', 3)+"'",
					map.getString("sf_id", "-1"),
					map.getSqlString("dining_floor", " ") };

			if(getInt(new StringBuffer(MessageFormat.format(exist.toString(), args)))>0) {
				return getMessage("on_dinnerset_exist", args);
			}
			
			Table tab=getTable("dining_set_info");
			tab.setParameterMap(map);
			tab.setSqlType(Table.INSERT);
			map.set("set_no", getMax("dining_set_info", "set_no"));
			map.set("balcony_code", DataUtil.lpad(String.valueOf(startNo), '0', 3));
			
			execute(tab);
			
		}
		
		return null;
	}
	
	public Object addDinnerSet1111(DyncParameterMap map) throws NestedException {
		System.out.println("xxoo");
		return null;
	}
}
