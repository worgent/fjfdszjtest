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

/**
 * 配送地区费用配置
 * @author kingapex
 *2010-3-28下午10:41:14
 */
public class TypeAreaConfig {
	/**
	 * 首重重量
	 */
	private Integer firstunit;
	
	
	/**
	 * 续重重量
	 */
	private Integer continueunit; 
	
	
	/**
	 * 是否支持货到付款<br/>
	 * 1为支持，0为否
	 */
	private Integer have_cod; 
	
	/**
	 * 是否启用公式
	 * 1为是，0为否
	 */
	private Integer useexp;
	
	/**
	 * 公式，启用公式时设置此属性
	 */
	private String expression;
	
	/**
	 * 首重费用<br/>
	 */
	private Double firstprice;
	
	
	
	/**
	 * 续重费用 
	 */
	private Double  continueprice;
	
	 
	/**
	 * 配送地区名称字串，以‘,’号分隔
	 */
	private String  areaName;
	
	/**
	 * 配送地区id字串，以','分隔<br/>
	 * 如果某地区选择，且为关闭说明子也选中，则格式为: <areaid>|close
	 */
	private String areaId;
	
	

	public Integer getFirstunit() {
		return firstunit;
	}

	public void setFirstunit(Integer firstunit) {
		this.firstunit = firstunit;
	}

	public Integer getContinueunit() {
		return continueunit;
	}

	public void setContinueunit(Integer continueunit) {
		this.continueunit = continueunit;
	}

	public Integer getHave_cod() {
		return have_cod;
	}

	public void setHave_cod(Integer haveCod) {
		have_cod = haveCod;
	}

	public Double getFirstprice() {
		return firstprice;
	}

	public void setFirstprice(Double firstprice) {
		this.firstprice = firstprice;
	}

	public Double getContinueprice() {
		return continueprice;
	}

	public void setContinueprice(Double continueprice) {
		this.continueprice = continueprice;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getUseexp() {
		return useexp;
	}

	public void setUseexp(Integer useexp) {
		this.useexp = useexp;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	
}
