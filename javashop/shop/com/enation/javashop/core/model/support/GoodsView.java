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
package com.enation.javashop.core.model.support;

import java.util.List;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.utils.UploadUtil;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.utils.GoodsUtils;

/**
 * 商品视图，对商品图片和属性做了处理,
 * 并且包含了一些冗余信息。
 * @author kingapex
 * 2010-7-16下午02:05:11
 */
public class GoodsView extends Goods  {

	private Double save_price;// 节省金额

	private Double agio; // 折扣

	private String brand_name; // 所属的品牌名称

	private Map propMap; // 商品属性，以p<index>为key，属性值为value的map

	private int hasSpec; // 是否有规格

	private List specList; // 规格列表
	
	private Integer productid; //如果没有规格时唯一的货品id
	
	private boolean isLast=false;//是否是列表中的最后一个
	private boolean isFirst=false;//是否是列表中的第一个
	
	public Double getAgio() {
		agio = this.getPrice() / this.getMktprice();
		return agio;
	}

	public void setAgio(Double agio) {
		this.agio = agio;
	}

	public Double getSave_price() {
		save_price = this.getMktprice() - this.getPrice();
		return save_price;
	}

	public void setSave_price(Double save_price) {
		this.save_price = save_price;
	}

	/**
	 * 返回商品默认图片<br/>
	 * 如果商品没有图片返回"images/no_picture.gif"<br/>
	 * 如果商品图片是以本地文件形式存储，将商品图片的加上静态资源前缀
	 */
	public String getImage_default() {
		String image_default = super.getImage_default();
		if (image_default == null || image_default.equals("")) {
			return "images/no_picture.gif";
		}else{
			image_default  =UploadUtil.replacePath(image_default); 
		} 
	 
		return image_default;
	}
	
	
	
	/**
	 * 返回商品默认图片的缩略图<br/>
	 * 如果商品没有图片返回"images/no_picture.gif"<br/>
	 * 如果商品图片是以本地文件形式存储，将商品图片的加上静态资源前缀
	 */
	public String getThumbnail_pic(){
		String thumbnail = super.getImage_default();
		if (thumbnail == null || thumbnail.equals("")) {
			return "images/no_picture.gif";
		} else{
			thumbnail= this.getImage_default();
		}
		thumbnail= UploadUtil.getThumbPath(thumbnail, "_thumbnail");
		return thumbnail;		
	}
	
	

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public Map getPropMap() {
		return propMap;
	}

	public void setPropMap(Map propMap) {
		this.propMap = propMap;
	}

	public List getSpecList() {
		return specList;
	}

	public void setSpecList(List specList) {
		this.specList = specList;
	}

	public int getHasSpec() {
		return hasSpec;
	}

	public void setHasSpec(int hasSpec) {
		this.hasSpec = hasSpec;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}

	public boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

}
