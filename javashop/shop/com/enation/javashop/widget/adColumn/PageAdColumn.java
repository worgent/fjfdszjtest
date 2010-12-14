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
package com.enation.javashop.widget.adColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.model.AdColumn;
import com.enation.javashop.core.model.Adv;
import com.enation.javashop.core.service.IAdColumnManager;
import com.enation.javashop.core.service.IAdvManager;

public class PageAdColumn extends AbstractWidget {
	
	private IAdvManager advManager;
	private IAdColumnManager adColumnManager;

	
	protected void config(Map<String, String> params) {
		this.setPageName("PageAdColumn_config");
		List<AdColumn> adColumnList = adColumnManager.listAllAdvPos();
		adColumnList = adColumnList == null ? new ArrayList<AdColumn>():adColumnList;
		this.putData("adColumnList", adColumnList);
	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("PageAdColumn");
		String acid = params.get("acid");
		AdColumn adc = adColumnManager.getADcolumnDetail(Long.valueOf(acid));
		String adwidth = adc.getWidth();
		String adheight = adc.getHeight();
		int atype = adc.getAtype();
		List<Adv> advList = advManager.listAdv(Long.valueOf(acid));
		advList = advList == null ? new ArrayList<Adv>():advList;
		if(advList.size()!=0){
			int a = (int)((advList.size()) * Math.random());
			this.putData("adv", advList.get(a));
		}else{
			Adv adv = new Adv();
			adv.setAcid(0);
			adv.setAid(0);
			//adv.setUrl("/shop/adv/zhaozu.jsp");//设置为我们的一个招租地址
			adv.setAtturl(EopSetting.IMG_SERVER_DOMAIN + "/images/zhaozu.png");
			this.putData("adv", adv);
		}
		
		this.putData("wid", params.get("id"));
		this.putData("adwidth", adwidth);
		this.putData("adheight", adheight);
		this.putData("atype", atype);
	}
	
	public void setAdvManager(IAdvManager advManager) {
		this.advManager = advManager;
	}

	public void setAdColumnManager(IAdColumnManager adColumnManager) {
		this.adColumnManager = adColumnManager;
	}


}
