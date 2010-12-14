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
package com.enation.javashop.widget.adv.flash;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.EopSetting;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.AdColumn;
import com.enation.javashop.core.model.Adv;
import com.enation.javashop.widget.abstractadv.AbstractAdvWidget;

public class FlashAdvWidget extends AbstractAdvWidget {

	
	protected void execute(AdColumn adColumn, List<Adv> advList) {
		StringBuffer imgs = new StringBuffer();
		StringBuffer urls = new StringBuffer();
		StringBuffer titles = new StringBuffer();
		for (Adv adv : advList) {
			if(imgs.length()!=0)imgs.append("|");
			imgs.append(adv.getAtturl());
			if(urls.length()!=0)urls.append("|");
			HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
			urls.append(request.getContextPath() +"/shop/adv!click."+EopSetting.EXTENSION+"?advid="+adv.getAid());
			if(titles.length()!=0)titles.append("|");
			titles.append(adv.getAname());
		}
		this.putData("imgs", imgs.toString());
		this.putData("urls", urls.toString());
		this.putData("titles", titles.toString());
		this.putData("width", adColumn.getWidth());
		this.putData("height", adColumn.getHeight());
	}

}
