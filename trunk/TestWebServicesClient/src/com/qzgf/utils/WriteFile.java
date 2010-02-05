package com.qzgf.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.io.*;

import com.qzgf.utils.export.ExportInfo;
import com.qzgf.utils.export.ExportUtil;


public class WriteFile { 
	public String ProWriteFilebyList(String CarNum,String FilePathPre,String cmdStr,List ls)
	{
		String resultFilePathName = "";// 文件名称，包括路径.
		//命令解读
		String[] cmdArrary=cmdStr.split(",");

		try {
		    //读取配置信息 
			ExportUtil eu=new ExportUtil();
			HashMap exporthsmap=eu.exportConfig("export-config.xml");
			ExportInfo ei=(ExportInfo)exporthsmap.get(cmdArrary[1].toString().toUpperCase());//命令标识id.转化为大写
			//文件流定义
			resultFilePathName=FilePathPre + FileNameByDateTime(CarNum);
			FileWriter fw  = new FileWriter(resultFilePathName);
			//测试写文件时间
			fw.write("写文件开始时间:"+(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())+"\r\n");
			// 文件头
			fw.write("信息类型:"+ei.getFileName()+"\t请求报文信息:"+cmdStr+"\r\n");
			// 字段名称
			for(int i=0;i<ei.getColumns().length;i++)
				fw.write(ei.getColumns()[i].toString()+"\t");
			fw.write("\r\n");
			//字段值
			Iterator it = ls.iterator();// 主类叠代:得到记录
			while (it.hasNext()) {
				HashMap rowlist= (HashMap) it.next();	
				for(int i=0;i<ei.getFields().length;i++)
				{
					String FiledValue=rowlist.get(ei.getFields()[i].toString()).toString();
					fw.write(FiledValue+ "\t");
				}
				fw.write("\r\n");// 回车换行
			}
			//测试写文件时间
			fw.write("写文件结束时间:"+(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())+"\r\n");
			fw.close();
		} catch (IOException e) {
			resultFilePathName=e.toString();
			e.printStackTrace();
		}
		return resultFilePathName;

	}
	/**
	 * 文件名生成规则：前缀＋日期+后缀(文件类型).
	 * @return
	 */
	private String FileNameByDateTime(String PreCarName)
	{
		String resultName="";//返回值
		String Pre="星网GPS数据-"+PreCarName+"-";  //前缀
		String Suffix=".txt";//后缀
	    Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //日期格式化
	    resultName=Pre+dateFormat.format(date)+Suffix;
		return resultName;
	}

}
