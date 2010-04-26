package com.qzgf.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ListParseToJson {

	/**
	 * 将列表信息转成json格式字符串，列表中的每一项必须是HashMap,这样才能获取到表结构
	 * 这样可以和Jquery很好的配合
	 * @param list HashMap的列表
	 * @return json格式字符串
	 * @throws Exception 
	 */
	public static String parseToJson(List list){
		StringBuffer sb = new StringBuffer(); //转化的结果集
		boolean flag=false;				   //默认控制为
		if(list== null || list.size()==0){
			return "[]";
		}
		for(int i=0 ; i<list.size(); i++){
			sb.append("{");
			HashMap map = (HashMap) list.get(i);
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
					String key = ((String)it.next()).toUpperCase();
					sb.append("\"").append(key).append("\":\"")
					.append(map.get(key))
				    .append("\",");
					flag=true;
			}
			if(flag)
			{
				sb.deleteCharAt(sb.length()-1);
				flag=false;
			}
			sb.append("},");
			flag=true;
		}
		if(flag)
			sb.deleteCharAt(sb.length()-1);
		return ("["+sb.toString()+"]");
	}

	
	public static void main(String[] args) {
		
		HashMap map = new HashMap();
		map.put("CITY_CODE","0591");
		map.put("CITY_NAME","福州");
		
		HashMap map2 = new HashMap();
		map2.put("CITY_CODE","0592");
		map2.put("CITY_NAME","厦门");
		
		ArrayList list = new ArrayList();
		list.add(map);
		list.add(map2);
		
		System.out.println(ListParseToJson.parseToJson(list));
	}
}
