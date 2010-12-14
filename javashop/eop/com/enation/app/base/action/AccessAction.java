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
package com.enation.app.base.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IAccessRecorder;
import com.enation.eop.core.resource.model.Link;
import com.enation.framework.action.WWAction;

public class AccessAction extends WWAction {

	private IAccessRecorder accessRecorder;
	private int startday;
	private int endday;
	public String list(){
	    Calendar   cal=Calendar.getInstance();  
        cal.setTime(new   Date());  
        int  year=cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        
		String starttime =null;
		String endtime =null;
		
		if(startday!=0){
			starttime = year+"-"+month +"-" + startday;
		}
		if(endday!=0){
			endtime = year+"-"+month +"-" + endday;
		}		
	//	System.out.println(starttime +"--" + endtime);
		this.webpage =this.accessRecorder.list(starttime, endtime, this.getPage(), 50);
		return "list";
	}
	
	private List<Link> linkList;
	
	//流量历史
	public String history(){
		linkList = new ArrayList<Link>();
		String target= EopSetting.IMG_SERVER_PATH + EopContext.getContext().getContextPath()+"/access";
		File file = new File(target);
		String[]  reportList = file.list();
		for(String name:reportList){
			Link link = new Link();
			link.setLink(EopSetting.IMG_SERVER_DOMAIN + EopContext.getContext().getContextPath()+"/access/"+name);
			link.setText(name);
			linkList.add(link);
		}
		return "history";
	}
	
	public IAccessRecorder getAccessRecorder() {
		return accessRecorder;
	}
	public void setAccessRecorder(IAccessRecorder accessRecorder) {
		this.accessRecorder = accessRecorder;
	}
	public int getStartday() {
		return startday;
	}
	public void setStartday(int startday) {
		this.startday = startday;
	}
	public int getEndday() {
		return endday;
	}
	public void setEndday(int endday) {
		this.endday = endday;
	}

	public List<Link> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<Link> linkList) {
		this.linkList = linkList;
	}
	
}
