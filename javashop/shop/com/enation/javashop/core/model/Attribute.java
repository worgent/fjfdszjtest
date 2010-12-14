/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.javashop.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品属性
 * @author apexking
 *
 */
public class Attribute {
	
	
	private String name;
	private String options;
	/**
	 * 1输入项 可搜索 
		2输入项 不可搜索
		3选择项 渐进式搜索 
		4选择项 普通搜索 
		5选择项 不可搜索 
	 */
	private int type;
	private String value;
	private List valueList;
	private int[] nums=null;
	private int hidden; //过滤时是否要显示这个属性
	
	public Attribute(){
		valueList = new ArrayList();
		hidden=0; 
	}
	
	public void addValue(String _value){
		valueList.add(_value);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValStr(){
	 
		if(type<3){
			 
		}else if(type>=3 && type<=5){
			if(value!=null && !value.equals("") && !value.equals("null"))
				return  getOptionAr()[Integer.valueOf(value)];
			
		}
		 
		return value;
	}
	
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	
	public String[] getOptionAr(){
		if(options==null || options.equals("")){
			return new String[]{};
		}
		
		String[] ar_options = options.split(",");
		
		return ar_options;
	}
	
	
	private Map[] maps ;
	/**
	 * 显示类别下的商品列表时过滤所用。
	 * @return
	 */
	public Map[] getOptionMap(){
		
		
		String[] optionAr = this.getOptionAr();
		
		if(maps==null) {
			maps = new Map[optionAr.length];
		 
		for(int i=0;i<optionAr.length;i++){
			Map m = new HashMap(3 );
			m.put("name", optionAr[i]);
			m.put("num",this.getNums()[i]);
			m.put("url", "");
			maps[i] = m;
		}
		}
		return maps;
	}
	
	
	
	
	public List getValueList() {
		return valueList;
	}
	public void setValueList(List valueList) {
		this.valueList = valueList;
	}
	
	
	public int[] getNums() {
		if(nums==null) nums= new int[this.getOptionAr().length];
		
		return nums;
	}

	public void setNums(int[] nums) {
		this.nums = nums;
	}
	
	

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public static void main(String[] args){
		
	 
		 
		
//		String json = "{\"name\":\"kingapex\",\"type:1,valueList:['诺',2,3]}";
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		Attribute a = (Attribute)JSONObject.toBean(jsonObject, Attribute.class);
//		System.out.println(a.name + " _- " + a.getType() + "--" + a.getValueList().get(0)); \
//		List values  = new ArrayList();
//		values.add("1111111111111");
//		values.add("22222222222");
//		values.add("33333333333");
//		Attribute attr  = new Attribute();
//		attr.setName("kingapex");
//		attr.setType(1);
//		attr.setValue("abc");
//		attr.setValueList(values);
		
//		JSONObject jsonObject = JSONObject.fromObject(attr);
//		System.out.println(jsonObject);
		
		
//		String json = jsonObject.toString();
//		jsonObject = JSONObject.fromObject(json);
//		Attribute a = (Attribute)JSONObject.toBean(jsonObject, Attribute.class);
//		System.out.println(a.name + " _- " + a.getType() + "--" + a.getValue() );
		
		
//		List<String> values  = new ArrayList<String>();
//		values.add("1111111111111");
//		values.add("22222222222");
//		values.add("33333333333");
//		Attribute attr  = new Attribute();
//		attr.setName("kingapex");
//		attr.setType(1);
//		attr.setValue("abc");
//		attr.setValueList(values);
//		
//		Attribute attr1  = new Attribute();
//		attr1.setName("kingapex");
//		attr1.setType(1);
//		attr1.setValue("abc");
//		attr1.setValueList(values);
//		
//		List jlist =  new ArrayList();
//		jlist.add(attr);
//		jlist.add(attr1);
//		
//		JSONArray jsonArray = JSONArray.fromObject( jlist );  
//		System.out.println(jsonArray);
//		String temp = jsonArray.toString();
//		JSONArray j1 = JSONArray.fromObject(temp);
//		
//		List<Attribute> list =(List) JSONArray.toCollection(j1, Attribute.class);
//		System.out.println(list.get(0).getName());
	}

 
	
	
}
