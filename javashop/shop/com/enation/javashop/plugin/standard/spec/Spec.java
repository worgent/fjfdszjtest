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
package com.enation.javashop.plugin.standard.spec;




/**
 * 商品规格
 * @author apexking
 *
 */
public class Spec  implements java.io.Serializable {


    // Fields    

     private Integer spec_id;
     private Integer goods_id;
     private String sn;
     private Integer store;
     private Double price;
     private String specs; //存储是什么样的规格组合
      
     
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	public String[] getSpecAr(){
		String[] spec_ar =null;
		
		if(specs!=null){
			String[] tempAr = specs.split(",");
			spec_ar= new String[tempAr.length];
			
			for(int i=0;i<tempAr.length;i++){
				spec_ar[i] = tempAr[i].replaceAll("\"", "");
			}
			
		}
		
		return spec_ar;
	}
 


}