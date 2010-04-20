package com.qzgf.webutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
 
public class ListParseToXML {

	/**
	 * 将列表信息转成xml格式字符串，列表中的每一项必须是HashMap,这样才能获取到表结构
	 * @param list HashMap的列表
	 * @return xml格式字符串
	 * @throws Exception 
	 */
	public static String parseToXML(List list){
		
		if(list== null || list.size()==0){
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
		sb.append("<ROOT>\r\n");
		
		for(int i=0 ; i<list.size(); i++){
			sb.append("\t<NODE>\r\n");
			HashMap map = (HashMap) list.get(i);
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
				String key = ((String)it.next()).toUpperCase();
				sb.append("\t\t<").append(key).append(">")
				  .append(map.get(key))
				  .append("</").append(key).append(">\r\n");
			}
			sb.append("\t</NODE>\r\n");
		}
		
		sb.append("</ROOT>");
//		System.out.println(sb.toString());
		return sb.toString();
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
		
		System.out.println(ListParseToXML.parseToXML(list));
	}
}
