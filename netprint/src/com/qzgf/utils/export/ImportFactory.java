package com.qzgf.utils.export;

import java.io.File;
import java.io.IOException;

/**
 * 一个单例模式的工厂类，用于创建导出的具体类，有ExportToExcel,ExportXml等。
 * @author chenf
 * */
public class ImportFactory {
	//仅本地可用,因为传入的filename为文件的全路径
	public static ImportIface getInstance(String type,String filename,String[] FileName,String[] Fields){
		ImportIface importdata=null;
		if(type==null){
			return null;
		}else if(type.equals("xls")){
			if(importdata==null || !(importdata instanceof ImportToPageExcel)){
				try {
					importdata = new ImportToPageExcel(filename,FileName,Fields);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(type.equals("pdf")){
			
		}
		return importdata;
		
	}
	
	//支持上传功能
	public static ImportIface getInstance(String type,File file,String filename,String filetype,String[] FileName,String[] Fields){
		ImportIface importdata=null;
		if(type==null){
			return null;
		}else if(type.equals("xls")){
			if(importdata==null || !(importdata instanceof ImportToPageExcel)){
				try {
					importdata = new ImportToPageExcel(file, filename,filetype,FileName,Fields);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(type.equals("pdf")){
			
		}
		return importdata;
		
	}
	
}
