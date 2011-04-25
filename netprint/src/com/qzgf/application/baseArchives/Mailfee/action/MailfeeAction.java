package com.qzgf.application.baseArchives.Mailfee.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.qzgf.PaginactionAction;
import com.qzgf.application.baseArchives.Mailfee.domain.MailfeeFacade;
import com.qzgf.utils.export.ExportInfo;
import com.qzgf.utils.export.ImportFactory;
import com.qzgf.utils.export.ImportIface;
import com.qzgf.utils.export.Importbase;

@SuppressWarnings("serial")
public class MailfeeAction extends PaginactionAction {

	Log log = LogFactory.getLog(MailfeeAction.class);
	private MailfeeFacade mailfeeFacade;
	@SuppressWarnings("unchecked")
	private List mailfeeList;
	@SuppressWarnings("unchecked")
	private HashMap<String, String> search = new HashMap();
	private String actionType;


	// 图片上传2009-11-13 upload
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	private String action;
	private String xml;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		this.exportFlag="TdMailfeeareaImport";
		
		
		
		if ("".equals(actionType) || null == actionType) {
			action = "upload";

			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(mailfeeFacade.countTdMailfeearea(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			mailfeeList = this.mailfeeFacade.findTdMailfeearea(search);
			return "search";
		}
		else if ("report".equals(actionType)) {
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(mailfeeFacade.countTdMailfeearea(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			mailfeeList = this.mailfeeFacade.findTdMailfeearea(search);
			return "report";
		}
		else if ("upload".equals(actionType)) {
			try {
				//导入时定义一些基本参数
				String importFlag = "TdMailfeeareaImport";
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
				ImportIface exportExc = ImportFactory.getInstance(type,upload,uploadFileName, uploadContentType,exportInfo.getColumns(), exportInfo.getFields());
				// 导入数据,读取每一行信息
			  	int i=mailfeeFacade.insertExcelTdMailfeeare(exportExc);
			  	
			  	
			  	if (i >= 1) {
					super.addActionMessage("导入封发局成功!");
					return SUCCESS;
				} else {
					super.addActionMessage("导入封发局失败!");
					return ERROR;
				}
			  	
			  	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "search";
		} else if("new".equals(actionType)){
			action = "insert";
			return "new";
		}else if ("insert".equals(actionType)){
			int i = mailfeeFacade.insertTdMailfeearea(search);
			if (i==1){
				super.addActionMessage("添加封发局信息,操作成功");
				addActionScript("parent.document.ifrm_UploadExcel.window.location.reload();");				
				return SUCCESS;
			}else {
				super.addActionMessage("添加封发局信息,操作失败");
				return ERROR;
			}
		}
		
		else if ("editList".equals(actionType)) {
			search = (HashMap) (this.mailfeeFacade.findTdMailfeearea(search).get(0));
			action = "updateSave";
			return "edit";
		} else if ("updateSave".equals(actionType)) // 修改保存
		{
			int i = mailfeeFacade.updateTdMailfeeareaById(search);
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

				this.mailfeeFacade.deleteTdMailfeeareaById(search);
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


	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}



	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}


	/**
	 * Purpose      : 说明
	 * @return the mailfeeFacade
	 */
	public MailfeeFacade getMailfeeFacade() {
		return mailfeeFacade;
	}


	/**
	 * Purpose      : 说明
	 * @param mailfeeFacade the mailfeeFacade to set
	 */
	
	public void setMailfeeFacade(MailfeeFacade mailfeeFacade) {
		this.mailfeeFacade = mailfeeFacade;
	}


	/**
	 * Purpose      : 说明
	 * @return the mailfeeList
	 */
	public List getMailfeeList() {
		return mailfeeList;
	}


	/**
	 * Purpose      : 说明
	 * @param mailfeeList the mailfeeList to set
	 */
	
	public void setMailfeeList(List mailfeeList) {
		this.mailfeeList = mailfeeList;
	}

	/**
	 * Purpose      : 说明
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * Purpose      : 说明
	 * @param upload the upload to set
	 */
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * Purpose      : 说明
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * Purpose      : 说明
	 * @param uploadFileName the uploadFileName to set
	 */
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * Purpose      : 说明
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * Purpose      : 说明
	 * @param uploadContentType the uploadContentType to set
	 */
	
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
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
