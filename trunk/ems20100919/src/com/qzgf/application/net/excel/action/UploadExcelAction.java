package com.qzgf.application.net.excel.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.qzgf.application.BaseAction;
import com.qzgf.application.net.excel.domain.UploadExcelFacade;
import com.qzgf.comm.Constant;
import com.qzgf.utils.Util;
import com.qzgf.utils.export.ExportInfo;
import com.qzgf.utils.export.ImportFactory;
import com.qzgf.utils.export.ImportIface;
import com.qzgf.utils.export.Importbase;

@SuppressWarnings("serial")
public class UploadExcelAction extends BaseAction {

	private UploadExcelFacade uploadExcelFacade;
	private List uploadExcelList; // 上传excel列表信息
	private List addressList;     // 地址列表信息
	private List clientMsgList;   // 客户列表信息

	private String xml;
	private String serfilename;
	
	// 图片上传2009-11-13
	private File upload;
	
	String userid=Constant.NetUser_Excel;//创建人  服务器2010081700000146,本地2010081700000104
	/*
	 * 其实，<s:file/>标志不仅仅是绑定到myFile，还有myFileContentType （上传文件的MIME类型） 和
	 * myFileFileName（上传文件的文件名，该文件名不包括文件的路径）。
	 */
	private String uploadFileName;
	private String uploadContentType;

	@SuppressWarnings("unchecked")
	//入口
	public String execute() throws Exception {
		//this.exportFlag="uploadExcelExport";
		//上传主页
		if ("".equals(super.getAction()) || null == super.getAction()|| "index".equals(super.getAction())) {
			this.setAction("upload");
			return "search";
		}
		//上传数据
		else if ("upload".equals(super.getAction())){
			//上传文件到服务器
			serfilename=saveexcel(upload,uploadFileName);
			//取得数据,显示
			uploadExcelList=getExcel();
			//地址信息
			search.put("pcreate_code", userid);
			addressList=uploadExcelFacade.findAddress(search);
			//客户信息
			clientMsgList=uploadExcelFacade.findClientMsg(search);
			this.setAction("import");
			return "search";
		}
		//导入数据
		else if ("import".equals(super.getAction())) {
			// 从session中读取用户信息
			//HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
			//默认地址信息
			//======================================用户数据准备=========================================
			//默认年份
			Calendar cal = Calendar.getInstance();
			search.put("precdateyear", cal.get(Calendar.YEAR));//年
			search.put("precdatemonth", cal.get(Calendar.MONTH)+1);//月
			search.put("precdateday", cal.get(Calendar.DAY_OF_MONTH));//日
			search.put("precdatehour", cal.get(Calendar.HOUR_OF_DAY));//时
			HashMap tmp=new HashMap();
			//默认地址信息
			/*
			tmp.put("pcreate_code", userid);//创建人
			List ls=uploadExcelFacade.setAddress(tmp);
			if(ls.size()>0){
				HashMap hs=(HashMap)ls.get(0);	
				search.put("psendaddress",hs.get("PROVINCENAME").toString()+hs.get("CITYNAME").toString()+hs.get("COUNTYNAME").toString()+ hs.get("ADDRESS").toString());	
			}
			*/
			search.put("psendaddress",search.get("frmprovincename").toString()+search.get("frmcityname").toString()+search.get("frmcountyname").toString()+ search.get("frmaddress").toString());	
			//默认用户信息
			/*
			tmp.clear();
			tmp.put("pid",userid);
			ls=uploadExcelFacade.setUserMsg(tmp);
			if(ls.size()>0){
				HashMap hs=(HashMap)ls.get(0);	
				//默认用户信息
				search.put("psendname", hs.get("NAME").toString());
				search.put("psendtel", hs.get("TEL").toString()+" "+ hs.get("MOBILE").toString());	
				search.put("psendunit", hs.get("UNIT").toString());	
				search.put("psendsign", hs.get("NAME").toString());	
			}
			*/
			search.put("psendname", search.get("frmname").toString());
			search.put("psendtel", search.get("frmmobile").toString()+search.get("frmtel").toString() );	
			search.put("psendunit", search.get("frmunit").toString());	
			search.put("psendsign", search.get("frmname").toString());	
			
			//创建人
			search.put("commstaffId", userid);
			//默认数量(不显示,报表修改格式)
			search.put("psendcount", "0");
			//默认是物品型
			search.put("psendmailtype","2");
			//=============================================================================================
			try {
				//上传文件到服务器
				String filename=Util.getUserWebFilePath(userid)+serfilename;
				//导入时定义一些基本参数
				String importFlag = "uploadExcelImport";
				String type = "xls";
				//得到数据集,即字段名称与字段代码的对应关系
				Importbase ib = new Importbase();
				ib.init();// 初始化目的是得到需要的List数据处理
				HashMap list = ib.getList();
				//ib.service(exFlag, uploadFileName, type)以下具体化该操作
				ExportInfo exportInfo = (ExportInfo) list.get(importFlag);
				if (exportInfo == null) {
						System.out.println("无法从资源文件里获得导出信息!");
					return "search";
				}
				// 获得需要导出的记录集,读取Excel文件信息
				ImportIface exportExc = ImportFactory.getInstance(type,filename, exportInfo.getColumns(), exportInfo.getFields());
				// 导入数据,读取每一行信息
				HashMap line = exportExc.readLine();
				String remark="";
				while (line != null &&line.size()>0) {
					try{
					//行号空,而货号不为空,说明数据要合并上一条记录
					if(!line.get("pproductno").toString().equals(" ") && line.get("pno").toString().equals(" ")){
						//从excel取数据
						search.put("pstorageno", line.get("pstorageno").toString());
						search.put("pproductno", line.get("pproductno").toString());
						search.put("pcolour", line.get("pcolour").toString());
						search.put("psize", line.get("psize").toString());
						search.put("pnumber", line.get("pnumber").toString());
					}else{
						/*导入数据在确定已经可以导入*/
						if(search.containsKey("pproductno")){
							if(!search.get("pproductno").toString().equals(" ")){
								//导入数据
								uploadExcelFacade.insertUploadExcel(search);
								remark="";
							}
						}
						//从excel取数据
						search.putAll(line);
					}
					//分析数据
					remark=remark+search.get("pstorageno").toString()+","+search.get("pproductno").toString()+","+search.get("pcolour").toString()+","+search.get("psize").toString()+","+search.get("pnumber").toString()+"\r\n";
					search.put("psendgoodsname", remark);
					
					search.put("precaddress", search.get("precprovince").toString()+search.get("preccity").toString()+search.get("preccounty").toString()+search.get("pstree").toString());
					//准备下一轮
					line = exportExc.readLine();
					
					/*
					if(line==null||!line.containsKey("pproductno")){
						//准备下一轮
						break;
					}
					*/
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
				//===================================================================
				/*最后一行*/
				if(search.containsKey("pproductno")){
					if(!search.get("pproductno").toString().equals(" ")){
						//导入数据
						uploadExcelFacade.insertUploadExcel(search);
						remark="";
					}
				}
				//===================================================================

				
				//关闭连接
				exportExc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setAction("upload");
			return "search";
		}else if ("print".equals(super.getAction())) {
			//取得数据,显示
			uploadExcelList=getExcel();
			return "report";
		}
		return ERROR;

	}

	
	/**
	 * 
	 * Purpose      : 将excel数据转化为list集合
	 * @return
	 */
	private List getExcel(){
		List result=new ArrayList();
		// 从session中读取用户信息
		try {
			//上传文件到服务器
			String filename=Util.getUserWebFilePath(userid)+serfilename;
			//导入时定义一些基本参数
			String importFlag = "uploadExcelImport";
			String type = "xls";
			//得到数据集,即字段名称与字段代码的对应关系
			Importbase ib = new Importbase();
			ib.init();// 初始化目的是得到需要的List数据处理
			HashMap list = ib.getList();
			//ib.service(exFlag, uploadFileName, type)以下具体化该操作
			ExportInfo exportInfo = (ExportInfo) list.get(importFlag);
			if (exportInfo == null) {
					System.out.println("无法从资源文件里获得导出信息!");
			}
			// 获得需要导出的记录集,读取Excel文件信息
			ImportIface exportExc = ImportFactory.getInstance(type,filename, exportInfo.getColumns(), exportInfo.getFields());
			// 导入数据,读取每一行信息
			HashMap line = exportExc.readLine();
			String remark="";
			while (line != null) {
				//行号空,而货号不为空,说明数据要合并上一条记录
				if(!line.get("pproductno").toString().equals(" ") && line.get("pno").toString().equals(" ")){
					//从excel取数据
					search.put("pstorageno", line.get("pstorageno").toString());
					search.put("pproductno", line.get("pproductno").toString());
					search.put("pcolour", line.get("pcolour").toString());
					search.put("psize", line.get("psize").toString());
					search.put("pnumber", line.get("pnumber").toString());
				}else{
					//从excel取数据
					search=line;
				}
				//将数据放到数据集中
					if(search.get("pproductno").toString().equals(" ")){
						break;
					}
					result.add(new HashMap(search));
				//准备下一轮
				line = exportExc.readLine();
			}
			//关闭连接
			exportExc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	
	/**
	 * 
	 * Purpose      : 上传excel文件到服务器
	 * @param file :  file控件
	 * @param filename 文件名称
	 * @return
	 */
	private String saveexcel(File file,String filename){
		   String result="";
	       try {
				String userId = userid;//默认用户
				if (file == null || StringUtils.isBlank(filename))
	              {
					
	              }
				else{
					
					// 生成的文件名:用户Id+系统当前时间+"."+当前文件的后缀
					String distFileName = FilenameUtils.getBaseName(filename) + System.currentTimeMillis() + "."+ FilenameUtils.getExtension(filename);
					
					String fullPicFile=Util.getUserWebFilePath(userId)+ distFileName;;	
		
					// 保存到本地
					OutputStream bos = new FileOutputStream(fullPicFile);
					IOUtils.copy(new FileInputStream(file), bos);
					
					
					//关闭数据流
					bos.close();
					//保存到数据库,名称
					result=distFileName;
				}
				
			} catch (Exception e) {
				//log.debug(e.toString());
				System.out.print(e.toString());
			}
			return result;
		
	}


	public UploadExcelFacade getUploadExcelFacade() {
		return uploadExcelFacade;
	}

	public void setUploadExcelFacade(UploadExcelFacade uploadExcelFacade) {
		this.uploadExcelFacade = uploadExcelFacade;
	}



	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
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

	/**
	 * Purpose      : 说明
	 * @return the uploadExcelList
	 */
	public List getUploadExcelList() {
		return uploadExcelList;
	}

	/**
	 * Purpose      : 说明
	 * @param uploadExcelList the uploadExcelList to set
	 */
	
	public void setUploadExcelList(List uploadExcelList) {
		this.uploadExcelList = uploadExcelList;
	}

	/**
	 * Purpose      : 说明
	 * @return the serfilename
	 */
	public String getSerfilename() {
		return serfilename;
	}

	/**
	 * Purpose      : 说明
	 * @param serfilename the serfilename to set
	 */
	
	public void setSerfilename(String serfilename) {
		this.serfilename = serfilename;
	}


	/**
	 * Purpose      : 说明
	 * @return the addressList
	 */
	public List getAddressList() {
		return addressList;
	}


	/**
	 * Purpose      : 说明
	 * @param addressList the addressList to set
	 */
	
	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}


	/**
	 * Purpose      : 说明
	 * @return the clientMsgList
	 */
	public List getClientMsgList() {
		return clientMsgList;
	}


	/**
	 * Purpose      : 说明
	 * @param clientMsgList the clientMsgList to set
	 */
	
	public void setClientMsgList(List clientMsgList) {
		this.clientMsgList = clientMsgList;
	}
}
