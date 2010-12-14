/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.app.saler.action.backend;

import java.io.File;

import com.enation.app.saler.service.IProductService;
import com.enation.eop.EopSetting;
import com.enation.eop.core.resource.model.EopProduct;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.action.WWAction;


/**
 * 导出解决方案action
 * @author kingapex
 * 2010-7-20下午06:55:14
 */
public class SolutionAction extends WWAction {

	//1是导 0是不导
	private int exportData;
	private int exportTheme;
	private int exportProfile;
	private int exportAttr;
	private String name;
	private File zip;
	private String zipFileName;
	private EopProduct product;
	private Integer id;
	private IProductService productService;
	private String productid;
	
	public String add(){
		
		return "add";
	}
	
	public String edit(){
		this.product = productService.getProduct(id);
		return "edit";
	}
	
	public String delete(){
		this.productService.delete(productid);
		this.msgs.add("解决方案删除成功");
		return this.MESSAGE;
	}
	
	public String saveEdit(){
		try{
			String zipPath = UploadUtil.upload(zip, zipFileName, "solution");
			zipPath  =zipPath.replaceAll(EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_PATH);
			this.productService.update(product, zipPath);
			this.msgs.add("解决方案修改成功");
			}catch (Exception e) {  
				this.logger.error(e.getMessage(), e);
				this.msgs.add("解决方案修改失败");
			}
			return this.MESSAGE;
	}
	
	public String saveAdd(){
		try{
			String zipPath = UploadUtil.upload(zip, zipFileName, "solution");
			zipPath  =zipPath.replaceAll(EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_PATH);
			int result = this.productService.add(product, zipPath);
			if(result==1)
				this.msgs.add("解决方案添加成功");
			else
				this.msgs.add("解决方案添加失败：解决方案id:"+product.getProductid() +"已存在");
		}catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			this.msgs.add("解决方案添加失败");
		}
		return this.MESSAGE;
	}
	/**
	 * 读取列表
	 * @return
	 */
	public String list(){
		this.webpage = productService.pageProduct(null, this.getPage(), this.getPageSize());
		return "list";
	}
	
	
	public String toExport(){
		
		return "export";
	}
	
	public String toImport(){
		
		return "import";
	}
	
	public String export(){
		try{
			productService.export(name, exportData==1, exportTheme==1, exportProfile==1,exportAttr==1);
			this.json="{result:1,path:'"+EopSetting.IMG_SERVER_DOMAIN+"/data/"+name+".zip"+"'}";
		}catch (RuntimeException e) {
			e.printStackTrace();
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}

	
	public String imported(){
		try{ 
			String productid = "temp_"+System.currentTimeMillis();
			
			String zipPath = UploadUtil.upload(zip, zipFileName, "solution");
			zipPath  =zipPath.replaceAll(EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_PATH);
			productService.imported(productid,zipPath);
			this.json="{result:1}";
			
		}catch (Exception e) {
			e.printStackTrace();
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}
	
	public int getExportData() {
		return exportData;
	}

	public void setExportData(int exportData) {
		this.exportData = exportData;
	}

	public int getExportTheme() {
		return exportTheme;
	}

	public void setExportTheme(int exportTheme) {
		this.exportTheme = exportTheme;
	}

	public int getExportProfile() {
		return exportProfile;
	}

	public void setExportProfile(int exportProfile) {
		this.exportProfile = exportProfile;
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExportAttr() {
		return exportAttr;
	}

	public void setExportAttr(int exportAttr) {
		this.exportAttr = exportAttr;
	}

	public File getZip() {
		return zip;
	}

	public void setZip(File zip) {
		this.zip = zip;
	}

	public String getZipFileName() {
		return zipFileName;
	}

	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}
	public EopProduct getProduct() {
		return product;
	}
	public void setProduct(EopProduct product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	} 
	
}
