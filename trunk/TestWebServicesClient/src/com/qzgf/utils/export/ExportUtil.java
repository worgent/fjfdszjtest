package com.qzgf.utils.export;

/**
 *@author chenf
 *@date 2005年11月16日
 *@docRoot
 * 导出数据
 * */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExportUtil {
	/**
	 * @author chenf
	 * @date 2005-11-18
	 * @docRoot
	 *  解析导出文件的配置信息，该信息保存于一个xml文件里，格式如：
	 *	<?xml version="1.0" encoding="gb2312" ?>
	 *	<exports>
	 *		<export id="exportMenu" sqlId= "getMenus" filename="menu">
	 *			<description>菜单导出信息</description>
	 *			<column name="menu_id">菜单ID</column>
	 *			<column name="menu_name">菜单名称</column>
	 *			<column name="img_src">图片地址</column>
	 *		</export>
	 *	</exports>
	 *  将如上配置信息解析后以ExportInfo的类对象保存在List里
	 *  @param conifgfile 配置文件路径
	 *  @return java.util.HashMap
	 */
	public HashMap exportConfig (String configfile){
		HashMap configList = new HashMap();
		try{
			InputStream in = getClass().getResourceAsStream(configfile);
			//因为jar文件读取文件会出错xml,所以改为绝对路径.
			//InputStream in = new InputStream()
			
			//这里采用dom4j的xml解析器
			SAXReader saxReader = new SAXReader();
		    Document document = saxReader.read(in);
		    //从配置文件里取得所有需要导出的节点信息
		    Iterator it = document.selectNodes("//exports/export").iterator();

		    while(it.hasNext()){
		    	Element element =(Element)it.next();
		    	//配置信息对应的类
		    	ExportInfo ei = new ExportInfo();
		    	ei.setSqlId(element.attribute("sqlId").getValue());
		    	ei.setFileName(element.attribute("filename").getValue());

		    	List cols = element.selectNodes("column");
		    	String[] fields = null;
		    	String[] columns = null;
		    	if(cols.size()>0){
		    		fields = new String[cols.size()];
		    		columns = new String[cols.size()];
		    	}
		    	
		    	for(int i=0;i<cols.size();i++){
		    		Element elCol = (Element)cols.get(i);
		    		fields[i]=elCol.attribute("name").getValue();
		    		columns[i]=elCol.getText();
		    	}
		    	ei.setFields(fields);
		    	ei.setColumns(columns);
		    	ei.setDescription(element.selectSingleNode("description").getText());
		    	//根据ID把ExportInfo保存
		    	configList.put(element.attribute("id").getValue(),ei);
		    }
		    in.close();//关闭文件流
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return configList;
		
	}
}
