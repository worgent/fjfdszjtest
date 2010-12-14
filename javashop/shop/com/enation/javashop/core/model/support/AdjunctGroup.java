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

import java.util.ArrayList;
import java.util.List;

import com.enation.javashop.core.model.AdjunctItem;

/**
 * 商品的一组配件
 * @author apexking
 *
 */
public class AdjunctGroup {
	private Integer couptype;  //优惠类型  
	private Integer minnum;  //最小购买量
	private Integer maxnum;  // 最大购买量
    private Double  coup;   //优惠额度
 

	public Double getCoup() {
		return coup;
	}

	public void setCoup(Double coup) {
		this.coup = coup;
	}

	public Integer getCouptype() {
		return couptype;
	}

	public void setCouptype(Integer couptype) {
		this.couptype = couptype;
	}

	public Integer getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}

	public Integer getMinnum() {
		return minnum;
	}

	public void setMinnum(Integer minnum) {
		this.minnum = minnum;
	}
    
    
    
}
