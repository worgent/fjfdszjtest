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
package com.qzgf.core.action.backend;

import java.io.File;
import java.util.List;

import com.enation.eop.impl.support.PermssionRuntimeException;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.framework.database.DBRuntimeException;
import com.enation.framework.util.FileUtil;
import com.qzgf.core.model.Cat;
import com.qzgf.core.service.IGoodsCatManager;
import com.qzgf.core.service.IGoodsTypeManager;


/**
 * 商品分类action
 * @author apexking
 *
 */
public class CatAction extends WWAction {
	
	private IGoodsCatManager emsgoodsCatManager;
	private IGoodsTypeManager emsgoodsTypeManager;
	
	protected List catList;
	private List typeList;
	private Cat cat;
	private File image;
	private String imageFileName;
	protected int cat_id;
	private int[] cat_ids; //分类id 数组,用于保存排序
	private int[] cat_sorts; //分类排序值
	
	//检测类别是否重名
	public String checkname(){
		if( this.emsgoodsCatManager.checkname(cat.getName(),cat.getCat_id() )){
			this.json="{result:1}";
		}else{
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}
	
	//显示列表
	public String list(){
		catList = emsgoodsCatManager.listAllChildren(0);
		return "cat_list";
	}

	
	//到添加页面
	public String add(){
		typeList = emsgoodsTypeManager.listAll();
		catList = emsgoodsCatManager.listAllChildren(0);
		return "cat_add";
	}
	
	
	//编辑
	public String edit(){
		try{
			typeList = emsgoodsTypeManager.listAll();
			catList = emsgoodsCatManager.listAllChildren(0);
			cat = emsgoodsCatManager.getById(cat_id);
		return "cat_edit";
		}catch(DBRuntimeException ex){
			this.msgs.add("您查询的商品不存在");
			return this.MESSAGE; 
		}
	}
	
	
	//保存添加
	public String saveAdd(){
		if (image != null) {
			if (FileUtil.isAllowUp(imageFileName)) {
				cat.setImage( UploadUtil.upload(image,imageFileName,"goodscat") );
				
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		cat.setGoods_count(0);
		emsgoodsCatManager.saveAdd(cat);
		
		this.msgs.add("商品分类添加成功");
		this.urls.put("分类列表", "cat!list.do");
		return this.MESSAGE; 
	}

	
	
	//保存修改 
	public String saveEdit(){
		if (image != null) {
			if (FileUtil.isAllowUp(imageFileName)) {
				cat.setImage( UploadUtil.upload(image,imageFileName,"goodscat") );
				
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		try{
			if(cat.getParent_id().intValue()==0){
				this.emsgoodsCatManager.update(cat);
				this.msgs.add("商品分类修改成功");
				this.urls.put("分类列表", "cat!list.do");
				return this.MESSAGE;
			}
			Cat targetCat = emsgoodsCatManager.getById(cat.getParent_id());//将要修改为父分类的对象
			if(cat.getParent_id().intValue()==cat.getCat_id().intValue() || targetCat.getParent_id().intValue()==cat.getCat_id().intValue()){
				this.msgs.add("保存失败：上级分类不能选择当前分类或其子分类");
				this.urls.put("分类列表", "cat!list.do");
				return this.MESSAGE; 
			}else{
				this.emsgoodsCatManager.update(cat);
				this.msgs.add("商品分类修改成功");
				this.urls.put("分类列表", "cat!list.do");
				return this.MESSAGE;
			}
		}catch(PermssionRuntimeException ex){
			this.msgs.add("非法操作");
			return this.MESSAGE; 
		}
		
	}
	
	
	
	//删除
	public String delete() {

		try {
			int r = this.emsgoodsCatManager.delete(cat_id);

			if (r == 0) {
				json = "{'result':0,'message':'删除成功'}";
			} else if (r == 1) {
				json = "{'result':1,'message':'此类别下存在子类别不能删除!'}";
			} else if (r ==2) {
				json = "{'result':1,'message':'此类别下存在商品不能删除!'}";
			}
		} catch (PermssionRuntimeException ex) {
			json = "{'result':1,'message':'非法操作!'}";
			return JSON_MESSAGE;
		}
		return this.JSON_MESSAGE;
	}

 
	
	public String saveSort(){
		this.emsgoodsCatManager.saveSort(cat_ids, cat_sorts);
		json= "{'result':0,'message':'保存成功'}";
		return this.JSON_MESSAGE;
	}
	
 
	
	public List getCatList() {
		return catList;
	}


	public void setCatList(List catList) {
		this.catList = catList;
	}


	public Cat getCat() {
		return cat;
	}


	public void setCat(Cat cat) {
		this.cat = cat;
	}


	public int getCat_id() {
		return cat_id;
	}


	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}


	public int[] getCat_ids() {
		return cat_ids;
	}


	public void setCat_ids(int[] cat_ids) {
		this.cat_ids = cat_ids;
	}


	public int[] getCat_sorts() {
		return cat_sorts;
	}


	public void setCat_sorts(int[] cat_sorts) {
		this.cat_sorts = cat_sorts;
	}




	public List getTypeList() {
		return typeList;
	}

 

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}



	public File getImage() {
		return image;
	}



	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * Purpose      : 说明
	 * @param emsgoodsCatManager the emsgoodsCatManager to set
	 */
	
	public void setEmsgoodsCatManager(IGoodsCatManager emsgoodsCatManager) {
		this.emsgoodsCatManager = emsgoodsCatManager;
	}

	/**
	 * Purpose      : 说明
	 * @param emsgoodsTypeManager the emsgoodsTypeManager to set
	 */
	
	public void setEmsgoodsTypeManager(IGoodsTypeManager emsgoodsTypeManager) {
		this.emsgoodsTypeManager = emsgoodsTypeManager;
	}
	
	
}