package com.qzgf.application.netprint.emsmail.action;


import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
import com.qzgf.PaginactionAction;
import com.qzgf.application.netprint.emsmail.domain.EmsmailFacade;
import com.qzgf.application.systemManage.manager.dto.UserInfo;
import com.qzgf.utils.ListParseToPrintXML;
import com.qzgf.utils.StringParseToBase64;
import com.qzgf.utils.export.ExportInfo;
import com.qzgf.utils.export.ImportFactory;
import com.qzgf.utils.export.ImportIface;
import com.qzgf.utils.export.Importbase;

/**
 * 
 * @author lsr
 * 
 */
@SuppressWarnings("serial")
public class EmsmailAction extends PaginactionAction {
	Log log = LogFactory.getLog(EmsmailAction.class);
	private EmsmailFacade emsmailFacade;
	@SuppressWarnings("unchecked")
	private List emsmailList;
	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private String actionType;
	private String action;
	private String xml;
	private String base64str;
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("pdeptid", userInfo.getDeptId());
		search.put("commstaffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType) {
			if (super.queryFlag == 0){
				super.setPageRecord(15);
				super.setParameter(search);
				super.setCount(emsmailFacade.findEmsmailCount(search));
			}
			search = (HashMap) super.getParameter();
			emsmailList = emsmailFacade.findEmsmail(search);
			return "search";
		} else if ("edit".equals(actionType)) {
			search = (HashMap) (emsmailFacade.findEmsmail(search).get(0));
			action = "update";
			return "edit";
		} else if ("new".equals(actionType)) {
			//默认寄件人信息(客户档案)
			search.put("staffId", userInfo.getStaffId());
			List ls=emsmailFacade.getUserConfig(search);
			if(ls.size()>0){
				search=(HashMap)ls.get(0);
				//默认交寄人与寄件人一致
				search.put("sendsign", search.get("sendname").toString());
			}
			//默认年份
			/*
			Calendar cal = Calendar.getInstance();
			search.put("recdateyear", cal.get(Calendar.YEAR));//年
			search.put("recdatemonth", cal.get(Calendar.MONTH));//月
			search.put("recdateday", cal.get(Calendar.DAY_OF_MONTH));//日
			search.put("recdatehour", cal.get(Calendar.HOUR_OF_DAY));//时
			*/
			search.put("sendinsuremoney", "0");//默认值
			search.put("sendmailtype", "1");//默认值
			action = "insert";
			return "new";
		} else if ("insert".equals(actionType)) {
			if(!search.containsKey("psendinsure")){
				search.put("psendinsure", "0");
			}
			//取得价格区域
			String address=search.get("precaddress").toString();
			//处理地址去除(省市县区).
			Pattern p = Pattern.compile("省|市|县|区");
			Matcher mat = p.matcher(address.toString());
			address=mat.replaceAll("");
			
			emsmailList=emsmailFacade.procEmsmailFeeArea(address);
			if(emsmailList.size()>0){
				search.put("pmail_feearea",((HashMap)emsmailList.get(0)).get("mail_feearea").toString());  //计费区
				search.put("pmail_sendoffice",((HashMap)emsmailList.get(0)).get("send_office").toString());//发送局
				search.put("pemail_fee",((HashMap)emsmailList.get(0)).get("email_fee").toString());//e邮宝信息
				
				//增加收件人的邮政编码
				if(search.containsKey("precpost")){
					if(search.get("precpost").toString().trim().equals("")){
						search.put("precpost",((HashMap)emsmailList.get(0)).get("recpost").toString());//邮政编号
						search.put("preccode",((HashMap)emsmailList.get(0)).get("city_name").toString());//城市
					}
				}else{
					search.put("precpost",((HashMap)emsmailList.get(0)).get("recpost").toString());//邮政编号
					search.put("preccode",((HashMap)emsmailList.get(0)).get("city_name").toString());//城市
				}
			}
			
			
			search.put("createMan", userInfo.getStaffId());
			int i = emsmailFacade.insertEmsmail(search);
			
			if (i == 1) {
				super.addActionMessage("增加EMS邮件成功!");
				search.clear();
				addActionScript("parent.document.ifrm_EmsMail.window.location.reload();");

				return SUCCESS;
			} else {
				search.clear();
				super.addActionMessage("增加EMS邮件失败!");
				return ERROR;
			}
		}
		else if ("insertclient".equals(actionType)) {
			//取得价格区域
			String address=search.get("precaddress").toString();
			//处理地址去除(省市县区).
			Pattern p = Pattern.compile("省|市|县|区");
			Matcher mat = p.matcher(address.toString());
			address=mat.replaceAll("");
			
			emsmailList=emsmailFacade.procEmsmailFeeArea(address);
			if(emsmailList.size()>0){
				search.put("pmail_feearea",((HashMap)emsmailList.get(0)).get("mail_feearea").toString());  //计费区
				search.put("pmail_sendoffice",((HashMap)emsmailList.get(0)).get("send_office").toString());//发送局
				search.put("pemail_fee",((HashMap)emsmailList.get(0)).get("email_fee").toString());//e邮宝信息
				
				//增加收件人的邮政编码
				if(search.containsKey("precpost")){
					if(search.get("precpost").toString().trim().equals("")){
						search.put("precpost",((HashMap)emsmailList.get(0)).get("recpost").toString());//邮政编号
						search.put("preccode",((HashMap)emsmailList.get(0)).get("city_name").toString());//城市
					}
				}else{
					search.put("precpost",((HashMap)emsmailList.get(0)).get("recpost").toString());//邮政编号
					search.put("preccode",((HashMap)emsmailList.get(0)).get("city_name").toString());//城市
				}
				
			}
			
			int i = emsmailFacade.insertEmsmail(search);
            //保存寄件人信息
			HashMap hs=new HashMap();
			hs.put("pbill_sort", "1");
			hs.put("pcode", search.get("psendcode").toString());
			hs.put("pname", search.get("psendname").toString());
			hs.put("punit", search.get("psendunit").toString());
			hs.put("pnation","中国");
			/*
			hs.put("pprovince",search.get("psendprovince").toString());
			hs.put("pcity", search.get("psendcity").toString());
			hs.put("pcounty", search.get("psendcounty").toString());
			*/
			hs.put("paddress", search.get("psendaddress").toString());
			hs.put("ptel", search.get("psendtel").toString());
			hs.put("ppost", search.get("psendpost").toString());
			hs.put("pdeptid", search.get("pdeptid").toString());
			hs.put("commstaffId", search.get("commstaffId").toString());
			i =i+ emsmailFacade.insertClientmsg(hs);
			//保存收件人信息
			hs.clear();
			hs.put("pbill_sort", "0");
			hs.put("pcode", search.get("preccode").toString());
			hs.put("pname", search.get("precname").toString());
			hs.put("punit", search.get("precunit").toString());
			hs.put("pnation","中国");
			/*
			hs.put("pprovince",search.get("precprovince").toString());
			hs.put("pcity", search.get("preccity").toString());
			hs.put("pcounty", search.get("preccounty").toString());
			*/
			hs.put("paddress", search.get("precaddress").toString());
			hs.put("ptel", search.get("prectel").toString());
			hs.put("ppost", search.get("precpost").toString());
			hs.put("pdeptid", search.get("pdeptid").toString());
			hs.put("commstaffId", search.get("commstaffId").toString());
			i =i+ emsmailFacade.insertClientmsg(hs);
			if (i > 1) {
				super.addActionMessage("增加EMS邮件成功!");
				search.clear();
				addActionScript("parent.document.ifrm_EmsMail.window.location.reload();");
				return SUCCESS;
			} else {
				search.clear();
				super.addActionMessage("增加EMS邮件失败!");
				return ERROR;
			}
		}
		//打印主入口(ocx方式)
		else if ("ocxprint".equals(actionType)) {
			//是否批量
			String type=search.get("printtype").toString();
			//张数
			String pagenum=search.get("pagenum").toString();
			
			//查询数据集
			emsmailList = emsmailFacade.findEmsmailPrint(search);
			//更新次数及时间
			emsmailFacade.updateEmsmailPrint(search);	
			//生成xml文档new String(deliverMsg.getMsgContent(),"UTF-16BE")
			String strocx=ListParseToPrintXML.parseToXML(emsmailList);
			//xml=strocx;
			/*
			FileWriter fw  = new FileWriter("d:\\aa\\test.xml");
			fw.write(strocx);
			fw.close();
			*/
			//strocx=new String(strocx.getBytes(),"UTF-8");
			//strocx=new String(strocx.getBytes(),"GBK");
			//生成base64二进制
			byte[] result = StringParseToBase64.encode(strocx.getBytes());
			//转化为base64字符串
 			base64str=new String(result);

			//默认寄件人信息(客户档案),左边距,上边距
			search.put("staffId", userInfo.getStaffId());
			List ls=emsmailFacade.getUserConfig(search);
			if(ls.size()>0)
				search=(HashMap)ls.get(0);
			
			search.put("printtype", type);
			search.put("pagenum", pagenum);
			//刷新页面
			addActionScript("try{");
			addActionScript("parent.document.ifrm_EmsMail.window.location.reload();");
			addActionScript("}catch(e){ }");
			return "ocxprint";
		} 
		//打印主入口(网页自带)
		else if ("pageprint".equals(actionType)) {
			//查询数据集
			emsmailList = emsmailFacade.findEmsmailPrint(search);
			//更新次数及时间
			emsmailFacade.updateEmsmailPrint(search);	
			//刷新页面
			addActionScript("try{");
			addActionScript("parent.document.ifrm_EmsMail.window.location.reload();");
			addActionScript("}catch(e){ }");
			return "pageprint";
		} 
		//打印主入口
		else if ("print".equals(actionType)) {
			//查询数据集
			emsmailList = emsmailFacade.findEmsmailPrint(search);
			//更新次数及时间
			emsmailFacade.updateEmsmailPrint(search);	
			//刷新页面
			addActionScript("try{");
			addActionScript("parent.document.ifrm_EmsMail.window.location.reload();");
			addActionScript("}catch(e){ }");
			return "report";
		} 
		//applet打印调用
		else if ("printapplet".equals(actionType)) {
			//查询数据集
			String path="/netprint/emsmail/emsmail.shtml?actionType=print&&search.pid="+search.get("pid").toString();
			search.put("path", path);
			return "printapplet";
		}else if ("update".equals(actionType)) {
			if(!search.containsKey("psendinsure")){
				search.put("psendinsure", "0");
			}
			
			//取得价格区域
			String address=search.get("precaddress").toString();
			//处理地址去除(省市县区).
			Pattern p = Pattern.compile("省|市|县|区");
			Matcher mat = p.matcher(address.toString());
			address=mat.replaceAll("");
			
			emsmailList=emsmailFacade.procEmsmailFeeArea(address);
			if(emsmailList.size()>0){
				search.put("pmail_feearea",((HashMap)emsmailList.get(0)).get("mail_feearea").toString());  //计费区
				search.put("pmail_sendoffice",((HashMap)emsmailList.get(0)).get("send_office").toString());//发送局
				search.put("pemail_fee",((HashMap)emsmailList.get(0)).get("email_fee").toString()+" ");//e邮宝信息
				
				//增加收件人的邮政编码
				if(search.containsKey("precpost")){
					if(search.get("precpost").toString().trim().equals("")){
						search.put("precpost",((HashMap)emsmailList.get(0)).get("recpost").toString());//邮政编号
						search.put("preccode",((HashMap)emsmailList.get(0)).get("city_name").toString());//城市
					}
				}else{
					search.put("precpost",((HashMap)emsmailList.get(0)).get("recpost").toString());//邮政编号
					search.put("preccode",((HashMap)emsmailList.get(0)).get("city_name").toString());//城市
				}
			}
		
			
			int i = emsmailFacade.updateEmsmail(search);
			if (i == 1) {
				super.addActionMessage("更新EMS邮件成功!");
				addActionScript("parent.document.ifrm_EmsMail.window.location.reload();");
				
				//增加返回按钮(客户的返馈)
				/*
				List btn=new ArrayList();
				HashMap hs=new HashMap();
				hs.put("name", "返回");
				hs.put("url", "");
				btn.add(hs);
				super.button=btn;
				*/
				return SUCCESS;
			} else {
				super.addActionMessage("更新EMS邮件失败!");
				return ERROR;
			}
		} else if ("delete".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());
			int i = emsmailFacade.deleteEmsmail(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}  else if ("deleteall".equals(actionType)) {
			search.put("editorMan", userInfo.getStaffId());
			int i = emsmailFacade.deleteEmsmail(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append("" + i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		} else if ("view".equals(actionType)) {
			search = (HashMap) (emsmailFacade.findEmsmail(search).get(0));
			return "view";
		}
		return ERROR;
	}

	
	
	// 图片上传2009-11-13 upload
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	public String upload() throws Exception {
		this.exportFlag="EmsmailUpload";
		UserInfo userInfo = (UserInfo) ActionContext.getContext().getSession().get("UserInfo");
		search.put("pdeptid", userInfo.getDeptId());
		search.put("commstaffId", userInfo.getStaffId());
		if ("".equals(actionType) || null == actionType) {
			action = "upload";
			if (super.queryFlag == 0) {// 判断查询状态
				super.setPageRecord(15);// 设置页面单页显示总记录数
				super.setParameter(search); // 设置查询参数
				super.setCount(emsmailFacade.findEmsmailCount(search));// 获取查询结果总记录数
			}
			search = (HashMap) super.getParameter();
			return "search";
		}else if ("upload".equals(actionType)) {
			try {
				//导入时定义一些基本参数
				String importFlag = "EmsmailUpload";
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
				search.put("createMan", userInfo.getStaffId());
				int i=emsmailFacade.insertexcelupload(exportExc,search);
				search.clear();
				if (i >= 1) {
					super.addActionMessage("导入EMS邮件成功!");
					//addActionScript("parent.document.ifrm_EmsMail.window.location.reload();");
					return SUCCESS;
				} else {
					super.addActionMessage("导入EMS邮件失败!");
					return ERROR;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "search";
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
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

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}


	/**
	 * @return the emsmailFacade
	 */
	public EmsmailFacade getEmsmailFacade() {
		return emsmailFacade;
	}


	/**
	 * @param emsmailFacade the emsmailFacade to set
	 */
	public void setEmsmailFacade(EmsmailFacade emsmailFacade) {
		this.emsmailFacade = emsmailFacade;
	}


	/**
	 * @return the emsmailList
	 */
	public List getEmsmailList() {
		return emsmailList;
	}


	/**
	 * @param emsmailList the emsmailList to set
	 */
	public void setEmsmailList(List emsmailList) {
		this.emsmailList = emsmailList;
	}


	/**
	 * @return the base64str
	 */
	public String getBase64str() {
		return base64str;
	}


	/**
	 * @param base64str the base64str to set
	 */
	public void setBase64str(String base64str) {
		this.base64str = base64str;
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
}
