package com.qzgf.application.baseArchives.UploadExcel.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.qzgf.PaginactionAction;
import com.qzgf.application.baseArchives.UploadExcel.domain.UploadExcelFacade;
import com.qzgf.utils.export.ExportInfo;
import com.qzgf.utils.export.ImportFactory;
import com.qzgf.utils.export.ImportIface;
import com.qzgf.utils.export.Importbase;

@SuppressWarnings("serial")
public class UploadExcelAction extends PaginactionAction {

	Log log = LogFactory.getLog(UploadExcelAction.class);
	private UploadExcelFacade uploadExcelFacade;
	@SuppressWarnings("unchecked")
	private List uploadExcelList;
	@SuppressWarnings("unchecked")
	private HashMap<String, String> search = new HashMap();
	private String actionType;

	private String fileName;

	private String action;
	private String xml;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		this.exportFlag="uploadExcelExport";
		if ("".equals(actionType) || null == actionType) {
			action = "upload";

			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(uploadExcelFacade
						.findUploadExcelQueryCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			uploadExcelList = this.uploadExcelFacade.UploadExcelQueryList(search);
			return "search";
		}
		else if ("report".equals(actionType)) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(uploadExcelFacade
						.findUploadExcelQueryCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			uploadExcelList = this.uploadExcelFacade.UploadExcelQueryList(search);
			return "report";
		}
		else if ("upload".equals(actionType)) {
			try {
				//导入时定义一些基本参数
				String importFlag = "uploadExcelImport";
				String type = "xls";
				//得到数据集,即字段名称与字段代码的对应关系
				Importbase ib = new Importbase();
				ib.init();// 初始化目的是得到需要的List数据处理
				HashMap list = ib.getList();
				//ib.service(exFlag, filename, type)以下具体化该操作
				ExportInfo exportInfo = (ExportInfo) list.get(importFlag);
				if (exportInfo == null) {
					if (log.isDebugEnabled()) {
						log.error("无法从资源文件里获得导出信息!");
					}
					return "search";
				}

				// 获得需要导出的记录集,读取Excel文件信息
				ImportIface exportExc = ImportFactory.getInstance(type,fileName, exportInfo.getColumns(), exportInfo.getFields());
				// 导入数据,读取每一行信息
				HashMap line = exportExc.readLine();

				while (line != null) {
					search=line;
					//一些额外字段的信息处理
					// 1.运营商确定
					search.put("pOperators", MobileSupply(line.get("pnumber").toString()));
					// 3.文件名确定
					String[] fileStr = fileName.split("\\\\");
					String fileNameStr = fileStr[fileStr.length - 1].toString();// 提取文件名
					search.put("pfileName", fileNameStr);
					// 4.数据处理
					// 查询手机号数据是否存在
					List ls= uploadExcelFacade.findUploadExcelNumber(search);
					if (ls.size()==0) { // 不存在
						uploadExcelFacade.insertUploadExcel(search);
					} else {
						// 2.更新网点确定
						HashMap hs=(HashMap)ls.get(0);
						if(!hs.get("updateNet").toString().equals("")&&line.get("pupdateNet").toString().equals(""))
						{
							search.put("pupdateNet", "share");
						}
						uploadExcelFacade.updateUploadExcel(search);
					}
					//循环处理信息
					line = exportExc.readLine();
				}
				//关闭连接
				exportExc.close();

				
				//返回所有数据
				if (super.queryFlag == 0) {// 判断查询状态
					super.setPageRecord(15);// 设置页面单页显示总记录数
					super.setParameter(search); // 设置查询参数
					super.setCount(uploadExcelFacade
							.findUploadExcelQueryCount(search));// 获取查询结果总记录数
				}
				search = (HashMap) super.getParameter();
				uploadExcelList = this.uploadExcelFacade
						.UploadExcelQueryList(search);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "search";
		} else if ("editList".equals(actionType)) {
			search = (HashMap) (this.uploadExcelFacade
					.findUploadExcelOnlyList(search).get(0));
			action = "updateSave";
			return "edit";
		} else if ("updateSave".equals(actionType)) // 修改保存
		{
			int i = uploadExcelFacade.updateSaveUploadExcel(search);
			if (i == 1) {
				super.addActionMessage("修改成功");
				addActionScript("parent.document.ifrm_UploadExcel.window.location.reload();");
				return SUCCESS;
			} else {
				super.addActionMessage("修改失败");
				return ERROR;
			}

		} else if ("delete".equals(actionType)) {
			try {

				this.uploadExcelFacade.deleteUploadExcel((String) search
						.get("id"));
				// 删除成功
				java.lang.StringBuffer sb = new StringBuffer();
				sb.append("<delete>");
				sb.append("<value>").append("1").append("</value>");
				sb.append("</delete>");

				xml = sb.toString();
			} catch (Exception e) {
				java.lang.StringBuffer sb = new StringBuffer();
				// 删除失败
				sb.append("<delete>");
				sb.append("<value>").append("2").append("</value>");
				sb.append("</delete>");
				xml = sb.toString();

			}
			return "xml";
		}

		return ERROR;

	}

	
	private String MobileSupply(String mobile){
		String resultstr="";
		String mobilenum="134,135,136,137,138,139,150,151,158,159";//移动号段
		String unicomnum="130,131,132,156";//联通号段
		String chinanum="133,153,189";//电信号段
		
		String substr=mobile.substring(0,3);
		
		if(mobilenum.indexOf(substr)>-1)
		{
			resultstr="移动";
		}
		else if(unicomnum.indexOf(substr)>-1)
		{
			resultstr="联通";
		}
		else
		{
			resultstr="电信";
		}
		return resultstr;
	}

	public UploadExcelFacade getUploadExcelFacade() {
		return uploadExcelFacade;
	}

	public void setUploadExcelFacade(UploadExcelFacade uploadExcelFacade) {
		this.uploadExcelFacade = uploadExcelFacade;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public List getUploadExcelList() {
		return uploadExcelList;
	}

	public void setUploadExcelList(List uploadExcelList) {
		this.uploadExcelList = uploadExcelList;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	//测试
//    public static void main(String[] args) { 
//        try { 
//            JasperCompileManager.compileReportToFile( 
//                    "G:\\Java\\linMingSystem\\WebRoot\\WEB-INF\\pages\\application\\baseArchives\\UploadExcel\\report3.5.3.jrxml", 
//                    "G:\\Java\\linMingSystem\\WebRoot\\WEB-INF\\pages\\application\\baseArchives\\UploadExcel\\report3.5.3.jasper"); 
//        } catch (Exception e) { 
//            e.printStackTrace(); 
//        } 
//    } 

}
