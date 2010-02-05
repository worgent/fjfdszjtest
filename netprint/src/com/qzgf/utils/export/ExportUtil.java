package com.qzgf.utils.export;

/**
 *@author chenf
 *@date 2005年11月16日
 *@docRoot
 *    导出数据
 * */
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.qzgf.utils.StatementIface;

public class ExportUtil {
	private Log log = LogFactory.getLog(ExportUtil.class);
	//StatementIface一个可以执行sql的对象
	private StatementIface statementmanager = null;
	
	
	public ExportUtil(StatementIface statementmanager) {
		this.statementmanager = statementmanager;
	}

	/**
	 * @author chenf
	 * @date 2005-11-18
	 * @docRoot
	 *  根据sql和对应参数、导出类型、数据字段、列名导出相应的文件
	 * @param out 客户端建立的输出流
	 * @param sql 
	 * @param para sql所需要的参数，如果没有参数可为null
	 * @param type 导出类型 ，与导出文件的扩展名命名，如excel为xls,xml为xml等
	 * @param sheetName 文件名称
	 * @param fields 跟上面的sql所查询出来的字段里的某些字段对应
	 * @param colName 对应的中文名称
	 */
	public void export(PrintWriter out, String sql, Object[] para, String type,int reportType, String sheetName, String[] fields, String[] colName) {
		//获得执行sql的实现类
		try {
			//获得需要导出的记录集
			ResultSet rs = statementmanager.executeSql(sql, para);
			//根据导出的类型实例化导出类
			ExportIface exportExc = ExportFactory.getInstance(type,reportType);
			//导出数据
			exportExc.export(out, rs, sheetName, fields, colName);

		} catch (Exception ex) {
			if(log.isErrorEnabled()){
				log.error(ex);
			}
		} finally {
			//释放
			statementmanager.clear();
		}
	}

	
	
	public void export(ServletOutputStream out, String sql, Object[] para, String type,int reportType, String sheetName, String[] fields, String[] colName) {
		//获得执行sql的实现类
		try {
			//获得需要导出的记录集
			ResultSet rs = statementmanager.executeSql(sql, para);
			//根据导出的类型实例化导出类
			ExportIface exportExc = ExportFactory.getInstance(type,reportType);
			//导出数据
			exportExc.export(out, rs, sheetName, fields, colName);

		} catch (Exception ex) {
			if(log.isErrorEnabled()){
				log.error(ex);
			}
		} finally {
			//释放
			statementmanager.clear();
		}
	}
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
	@SuppressWarnings("unchecked")
	public HashMap exportConfig (String configfile){
		HashMap configList = new HashMap();
		try{
			InputStream in = (com.qzgf.utils.export.ExportUtil.class).getResourceAsStream(configfile);
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
		    
		}catch(Exception ex){
			ex.printStackTrace();
			if(log.isErrorEnabled())
				log.error(ex);
		}
		return configList;
		
	}

	public StatementIface getStatementmanager() {
		return statementmanager;
	}

	public void setStatementmanager(StatementIface statementmanager) {
		this.statementmanager = statementmanager;
	}
}
