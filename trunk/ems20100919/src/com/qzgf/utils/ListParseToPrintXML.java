package com.qzgf.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ListParseToPrintXML {

	/**
	 * 将列表信息转成xml格式字符串，列表中的每一项必须是HashMap,这样才能获取到表结构
	 * @param list HashMap的列表
	 * @return xml格式字符串
	 * @throws Exception 
	 */
	public static String parseToXML(List list){
		
		if(list== null || list.size()==0){
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		//定义文件头
		sb.append("<xml xmlns:s='uuid:BDC6E3F0-6DA3-11d1-A2A3-00AA00C14882'"+" \r\n"+
		"xmlns:dt='uuid:C2F41010-65B3-11d1-A29F-00AA00C14882'"+" \r\n"+
		"xmlns:rs='urn:schemas-microsoft-com:rowset'"+" \r\n"+
		"xmlns:z='#RowsetSchema'>"+"\r\n");
		//定义描述,与数据
		StringBuffer sbdesc=new StringBuffer();
		StringBuffer sbdate=new StringBuffer();
		
		
		//描述
		sbdesc.append("<s:Schema id='RowsetSchema'>"+"\r\n"+
				       "<s:ElementType name='row'>"+"\r\n");
		
//		sbdesc.append("<s:AttributeType name='test'>"+"\r\n"+
//				"<s:datatype dt:type='string'/>"+"\r\n"+
//				"</s:AttributeType>"+"\r\n");
		
		//数据
		sbdate.append("<rs:data>"+"\r\n");
		
		sbdate.append("<z:row ");
		HashMap map = (HashMap) list.get(0);
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			//String key = ((String)it.next()).toUpperCase();
			String key = ((String)it.next());
			//描述
			sbdesc.append("<s:AttributeType name='"+key+"'>"+"\r\n"+
					"<s:datatype dt:type='string'/>"+"\r\n"+
					"</s:AttributeType>"+"\r\n");
			//数据
		    sbdate.append(" "+key+"='"+map.get(key)+"'");
		}
		//测试
//		sbdate.append(" test='123' recinsuremoney='null' sendtel='123' mailno='123' mail_feearea='' reccounty='null' sendaddress='132' remark='132' printcount='181' sendcounty='null' recdatemonth='8' create_man='2009091700000164' recprovincename='null' sendinsure='null' execute_date='2009-09-18 10:21:17' height='null' create_date='2009-09-18 10:21:17' senddateday='null' sendpost='123' sendmailtype='null' recpost='13' width='null' sendcity='null' sendcityname='null' execute_man='2009091700000164' editor_man='2009091700000164' sendcount='12' solidity='null' reccity='null' weight='null' recdatehour='10' mail_sendoffice='' state='1' sendsign='ftest' recprovince='null' recname='132' sendunit='123' sendcode='13' id='2009091800000026' recdateyear='2009' senddatemonth='null' sendgoodsname='13' printtime='2009-10-14 20:04:14' sendprovincename='null' rectel='123' sendname='ftest' reccityname='null' recdateday='18' editor_date='2009-09-18 10:21:17' senddatatime='null' length='null' sendcountyname='null' recsign='13' sendoffice='null' recaddress='福建宁德' recunit='132' sendinsuremoney='null' reccode='132' acceptsign='null' charge='null' sendprovince='null' senddatehour='null' senddateyear='null' totalcharge='null' reccountyname='null' />");
		//描述()
		sbdesc.append("</s:ElementType>"+"\r\n"+
					"</s:Schema>"+"\r\n");
		//数据
		sbdate.append("/>"+"\r\n");
		
		for(int i=1 ; i<list.size(); i++){
			//数据
			sbdate.append("<z:row ");
			map = (HashMap) list.get(i);
			it = map.keySet().iterator();
			while(it.hasNext()){
				String key = ((String)it.next());
				//数据
				sbdate.append(" "+key+"='"+map.get(key)+"'");
			}
			//数据
			sbdate.append("/>"+"\r\n");
		}
		//数据
		sbdate.append("</rs:data>"+"\r\n");		
		
		
		//组装数据
		sb.append(sbdesc);
		sb.append(sbdate);
		//定义尾
		sb.append("</xml>");
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
		
		System.out.println(ListParseToPrintXML.parseToXML(list));
	}
}
