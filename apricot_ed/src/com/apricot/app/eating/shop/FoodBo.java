/**
 * 
 */
package com.apricot.app.eating.shop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.ResponseBASE64File;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 * 
 */
public class FoodBo extends BO {

	/**
	 * 
	 */
	public FoodBo() {
		// TODO Auto-generated constructor stub
	}
	
	public Object getImages(DyncParameterMap map) throws NestedException{
		HashMap m=new HashMap();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from sys_table_attribute where TABLE_COLUMN = 'SPICY_LEVEL'");
		Object o = get(sql);
		String attrId = DataUtil.getString(o, "attr_id");
		sql=new StringBuffer();
		sql.append("select fi.food_id,fi.food_name,fi.food_price,fi.spicy_level,sav.attr_desc from food_info as fi left join sys_attribute_value as sav on (fi.spicy_level=sav.attr_value)  ");
		sql.append("where fi.sf_id=").append(map.getInt("sf_id", -1));
		sql.append(" and fi.food_type=").append(map.getSqlString("food_type", "00"));
		sql.append(" and sav.attr_id="+attrId);
		System.out.println(sql);
		setRuntimeStaticData("food_info");
		m.put("images", getAll(sql));
		return m;
	}
	

	public Object addImage(DyncParameterMap map) throws NestedException {
	


		//
		PreparedStatement stmt = null;
		try {
			//byte[] imgd=new sun.misc.BASE64Decoder().decodeBuffer(map.getString("food_image"));
			String sql = "delete from food_images where food_id=?";
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, map.getInt("food_id"));
			stmt.execute();
			stmt.close();
			//
			ByteArrayInputStream in = new ByteArrayInputStream(map.getString("food_image").getBytes());
			sql = "insert into food_images(food_id,food_image) values(?,?)";
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, map.getInt("food_id"));
			stmt.setBinaryStream(2, in, in.available());
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new NestedException("SYS-0011", e);
		} finally {

			closeCursor(stmt, null);
		}

		return null;
	}

	public Object getImage(DyncParameterMap map) throws NestedException {
		ResponseBASE64File rbf = new ResponseBASE64File();
		PreparedStatement stmt = null;
		java.sql.ResultSet res = null;
		try {
			String sql = "select food_image from food_images where food_id=?";
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, map.getInt("food_id"));
			res = stmt.executeQuery();
			if (res.next()) {
				
				ByteArrayOutputStream out=new ByteArrayOutputStream();
				InputStream in=res.getBinaryStream(1);
				byte[] b=new byte[1024];
				int l=0;
				while((l=in.read(b))>0){
					out.write(b, 0, l);
				}
				in.close();
				out.close();
				
				rbf.setContent(new String(out.toByteArray()));
				
			}
	

		} catch (Exception e) {
			throw new NestedException("SYS-0011", e);
		} finally {

			closeCursor(stmt, res);
		}

		return rbf;
	}
	public Object AddFood(DyncParameterMap map) throws NestedException {
		
		Table tab = getTable("food_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		long primaryKey = getMax("food_info", "food_id");
		map.set("food_id", primaryKey);
		execute(tab);
		int relat = 0;
		if(!DataUtil.isNull(map.getString("relat_type1"))){
			relat += Integer.parseInt(map.getString("relat_type1"));
		}
		if(!DataUtil.isNull(map.getString("relat_type2"))){
			relat += Integer.parseInt(map.getString("relat_type2"));
		}
		if(!DataUtil.isNull(map.getString("relat_type3"))){
			relat += Integer.parseInt(map.getString("relat_type3"));
		}
		tab = getTable("food_dept_relat");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("relat_type", relat);
		execute(tab);
//		System.out.println(map.getString("relat_type"));
		return null;
	}
	
public Object ModifyFood(DyncParameterMap map) throws NestedException {
		
		Table tab = getTable("food_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		execute(tab);
		int relat = 0;
		if(!DataUtil.isNull(map.getString("relat_type1"))){
			relat += Integer.parseInt(map.getString("relat_type1"));
		}
		if(!DataUtil.isNull(map.getString("relat_type2"))){
			relat += Integer.parseInt(map.getString("relat_type2"));
		}
		if(!DataUtil.isNull(map.getString("relat_type3"))){
			relat += Integer.parseInt(map.getString("relat_type3"));
		}
		tab = getTable("food_dept_relat");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		map.set("relat_type", relat);
		execute(tab);
//		System.out.println(map.getString("relat_type"));
		return null;
	}
	
public Object DeleteFood(DyncParameterMap map) throws NestedException {
	
	Table tab = getTable("food_info");
	tab.setParameterMap(map);
	tab.setSqlType(Table.DELETE);
	execute(tab);
	tab = getTable("food_dept_relat");
	tab.setParameterMap(map);
	tab.setSqlType(Table.DELETE);
	execute(tab);
	return null;
}

}
