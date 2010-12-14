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
package com.qzgf.core.model;

import java.util.List;

import com.enation.framework.database.NotDbField;





/**
 * 商品类别
 * @author apexking
 *
 */
public class Cat   {


  
	
	 protected Integer cat_id;
     protected String name;
     protected Integer parent_id;
     protected String cat_path;
     protected Integer goods_count;
     protected int cat_order;
     protected Integer type_id;
     protected String list_show;
     protected String image;
     
     
     /************以下为非数据库字段*************/
     private boolean hasChildren ;
     private List<Cat> children;  //子类别
     private String type_name; //类型名称      

     
     
    public Cat() {
    	hasChildren =false;
    }


	public Integer getCat_id() {
		return cat_id;
	}


	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}


	public int getCat_order() {
		return cat_order;
	}


	public void setCat_order(int cat_order) {
		this.cat_order = cat_order;
	}


	public String getCat_path() {
		return cat_path;
	}


	public void setCat_path(String cat_path) {
		this.cat_path = cat_path;
	}


 


 

	public Integer getGoods_count() {
		return goods_count;
	}


	public void setGoods_count(Integer goods_count) {
		this.goods_count = goods_count;
	}


 


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getParent_id() {
		return parent_id;
	}


	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
 


	public Integer getType_id() {
		return type_id;
	}


	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}


	public String getList_show() {
		return list_show;
	}


	public void setList_show(String listShow) {
		list_show = listShow;
	}

	@NotDbField
	public List<Cat> getChildren() {
		return children;
	}


	public void setChildren(List<Cat> children) {
		this.children = children;
	}



	@NotDbField
	public String getType_name() {
		return type_name;
	}


	public void setType_name(String typeName) {
		type_name = typeName;
	}

	@NotDbField
	public boolean getHasChildren() {
		hasChildren = this.children==null|| this.children.isEmpty() ?false:true;
		return hasChildren;
	}


	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}