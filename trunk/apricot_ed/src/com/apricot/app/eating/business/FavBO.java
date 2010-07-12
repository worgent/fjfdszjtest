package com.apricot.app.eating.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;

public class FavBO extends BO{
	public FavBO(){
		
	}

	public Object addFav(DyncParameterMap map) throws NestedException {

		StringBuffer ext = new StringBuffer();
		ext.append("select count(*) from coupon_info where cp_num='");
		ext.append(map.getString("cp_num"));
		ext.append("'");
		StringBuffer sql1=new StringBuffer();
		sql1.append("select count(*) from coupon_info");
		Table table = getTable("coupon_info");
		table.setSqlType(Table.INSERT);
		table.setParameterMap(map);
		if(getInt(sql1)>0){
			map.set("cp_id", getMax("coupon_info", "cp_id"));
		}else{
			map.set("cp_id", "1");
		}
		if(getInt(ext)>0)
			return getMessage("coupon_suc", null);
		execute(table);
		return null;
	}
	public Object addFavMut(DyncParameterMap map) throws NestedException {
		
		int start=map.getInt("start_no");
		int end=map.getInt("end_no");
		StringBuffer ext = new StringBuffer();
		ext.append("select count(*) from coupon_info ");
		Table table = getTable("coupon_info");
		table.setSqlType(Table.INSERT);
		table.setParameterMap(map);
		int oralCP = map.getInt("cp_num");
		for(int i=start-1 ;i<end;i++){
			if(getInt(ext)>0){
				map.set("cp_id", getMax("coupon_info", "cp_id"));
				map.set("cp_num", oralCP+""+(i+1));
				
			}else{
				map.set("cp_id", "1");
				map.set("cp_num", oralCP+""+(i+1));
				
			}
			StringBuffer ext1 = new StringBuffer();
			ext1.append("select count(*) from coupon_info where cp_num='");
			ext1.append(map.getInt("cp_num")+i);
			ext1.append("'");
			if(getInt(ext1)>0)
				return getMessage("coupon_suc", null);	
			execute(table);
		}
		return null;
	}
	public Object couponInfo(DyncParameterMap map) throws NestedException {
			StringBuffer sql=new StringBuffer();
			sql.append("select * from coupon_info");
			if(!DataUtil.isNull(map.getString("cp_num"))&&!DataUtil.isNull(map.getString("cp_num1"))){
				sql.append(" where cp_num>='");
				sql.append(map.getString("cp_num"));
				sql.append("'");
				sql.append(" and cp_num<='");
				sql.append(map.getString("cp_num1"));
				sql.append("'");
			}
			if(!DataUtil.isNull(map.getString("cp_name"))){
				sql.append(" where cp_name='");
				sql.append(map.getString("cp_name"));
				sql.append("'");
			}
			if(!DataUtil.isNull(map.getString("cp_oper"))){
				sql.append(" where cp_oper='");
				sql.append(map.getString("cp_oper"));
				sql.append("'");
			}
			if(!DataUtil.isNull(map.getString("pb_time"))){
				sql.append(" where pb_time like'");
				sql.append(map.getString("pb_time"));
				sql.append("%'");
			}
			if(!DataUtil.isNull(map.getString("cp_status"))){
				sql.append(" where cp_status='");
				sql.append(map.getString("cp_status"));
				sql.append("'");
			}
			if(!DataUtil.isNull(map.getString("start_time"))){
				sql.append(" where start_time like'");
				sql.append(map.getString("start_time"));
				sql.append("%'");
			}
			if(!DataUtil.isNull(map.getString("end_time"))){
				sql.append(" where end_time like'");
				sql.append(map.getString("end_time"));
				sql.append("%'");
			}
			setRuntimeStaticData("coupon_info");
			return showPages(sql.toString(),0,-1);
	}
	public Object couponInfo1(DyncParameterMap map) throws NestedException {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from coupon_info where cp_status='02'");
		if(!DataUtil.isNull(map.getString("pay_money"))){
			sql.append(" and cond_value='");
			sql.append(map.getString("pay_money"));
			sql.append("'");
		}
		setRuntimeStaticData("coupon_info");
		return showPages(sql.toString(),0,-1);
	}
	public Object zuMoney(DyncParameterMap map) throws NestedException {
		HashMap m=new HashMap();
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct cond_value ,send_num  from coupon_info where cp_status='02'");
		List list=getAll(sql);
		ArrayList array=new ArrayList();
		for(int i=0;i<list.size();i++){
			//System.out.println(DataUtil.getString(list.get(i), "cond_value")+",");
			//m.put("cond_value", DataUtil.getString(list.get(i), "cond_value"));
			array.add( DataUtil.getString(list.get(i), "cond_value"));
			//str=str+","+DataUtil.getString(list.get(i), "cond_value");
		}
		System.out.println("Str"+array);
		//m.put("cond_value", str);
		//System.out.println(m.get("cond_value"));
		return array;
	}
	public Object vip_cou(DyncParameterMap map) throws NestedException {
		int  total;
		StringBuffer ext=new StringBuffer();
		ext.append("select count(*)  from coupon_info where cp_status='02' and cond_value='");
		ext.append(map.getString("value"));
		ext.append("'");
		if(getInt(ext)>0){
			StringBuffer sql=new StringBuffer();
			sql.append("select distinct send_num from coupon_info where cp_status='02' and cond_value='");
			sql.append(map.getString("value"));
			sql.append("'");
			Object o=get(sql);
			
			Integer i=Integer.parseInt(DataUtil.getString(o, "send_num"));
			Integer tvalue=Integer.parseInt(map.getString("total"));
			Integer value=Integer.parseInt(map.getString("value"));
			if(tvalue<value){
				total=0;
			}else{
					total=tvalue/value*i;
			}
			if(total>getInt(ext)){
				total=getInt(ext);
			}
			for(int j=0;j<total;j++){
				StringBuffer ext1=new StringBuffer();
				ext1.append("select max(cp_num)-");
				ext1.append(j);
				ext1.append(" as num from coupon_info where cp_status='02'");
				Object o1=get(ext1);
				StringBuffer sql1=new StringBuffer();
				sql1.append("update coupon_info set cp_status='03',vip_cond='");
				sql1.append(map.getString("vip_card_no"));
				sql1.append("'");
				sql1.append(" where cp_status='02'  and cp_num='");
				sql1.append(DataUtil.getString(o1, "num"));
				sql1.append("' and cond_value='");
				sql1.append(map.getString("value"));
				sql1.append("'");
				update(sql1);
				if(update(sql1)<total){
				}
			}
		}else{
			total=999999999;
		}
		
		return total;
	}
	public Object vip_cou1(DyncParameterMap map) throws NestedException {
		int msg = 0;
		if(DataUtil.isNull(map.getString("cp_num"))){
			 msg=99999999;
			return msg;
		}else{
			StringBuffer ext=new StringBuffer();
			ext.append("select cp_status from coupon_info where cp_num='");
			ext.append(map.getString("cp_num"));
			ext.append("'");
			Object o=get(ext);
			if("03".equals(DataUtil.getString(o, "cp_status"))){
				msg=888888;
				return msg;
			}else{
				StringBuffer sql=new StringBuffer();
				sql.append("update coupon_info set cp_status='03'");
				sql.append(" where cp_status='02' and cp_num='");
				sql.append(map.getString("cp_num"));
				sql.append("'");
				update(sql);
			}
		}
		return null;
		
	}
	public Object checkCp(DyncParameterMap map) throws NestedException {
		StringBuffer sql=new StringBuffer();
		HashMap m=new HashMap();
		if(map.getString("cp_num")!=""){
			sql.append("select count(*) from coupon_info where cp_num in(");
			sql.append(map.getString("cp_num"));
			sql.append(")");
			if(getInt(sql)>0){
				m.put("flag", "NO");
			}else{
				m.put("flag", "YES");
			}StringBuffer sqlv=new StringBuffer();
			sqlv.append("select cp_status from coupon_info where cp_num in (");
			sqlv.append(map.getString("cp_num"));
			sqlv.append(")");
			List list=getAll(sqlv);
			for(int i=0;i<list.size();i++){
				if("01".equals(DataUtil.getString(list.get(i), "cp_status")))
				{
					System.out.println("333333333333");
					m.put("flag", "YES");
				}else{
					System.out.println("4444");
					m.put("flag", "NO");
				}
				
			}
		}else{
			System.out.println("55555");
			m.put("flag", "00");
		}
		return m;
		
	}
	public void modifyCp(DyncParameterMap map) throws NestedException {
//		StringBuffer sql=new StringBuffer();
//		HashMap m=new HashMap();
//		sql.append("select count(*) from coupon_info where cp_num='");
//		sql.append(map.getString("cpnum"));
//		sql.append("'");
//		if(getInt(sql)>0)
//				return getMessage("coupon_suc", null);
		Table tab = getTable("coupon_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		//map.set("state_date", new Date());// Current datetime
		execute(tab);
	
		
	}
}
