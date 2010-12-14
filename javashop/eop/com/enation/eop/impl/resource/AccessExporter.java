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
package com.enation.eop.impl.resource;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.Access;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.ExcelUtil;
import com.enation.framework.util.FileUtil;


public class AccessExporter implements   Runnable  {
 
	private IDaoSupport daoSupport;
	private Integer userid;
	private Integer siteid;
	
	public void setContext(Integer userid,Integer siteid){
		this.userid = userid;
		this.siteid = siteid;
	}
	
	public void run() { 
		//使用excel导出流量报表
		ExcelUtil excelUtil = new ExcelUtil(); 
		
		//流量报表excel模板在类包中，转为流给excelutil
		InputStream in =FileUtil.getResourceAsStream("com/enation/eop/core/resource/access.xls");
		excelUtil.openModal( in );
		
		//查询某站点上个月的流量记录
		String[] lastMonth = DateUtil.getLastMonth(); //得到上个月第一天和最后一天的字串数组 
		int start = (int)( DateUtil.toDate(lastMonth[0], "yyyy-MM-dd").getTime() /1000);  //上个月第一天的秒数
		int end = (int)( DateUtil.toDate(lastMonth[1], "yyyy-MM-dd").getTime() /1000);    //上个月最后一天的秒数
		String sql ="select * from es_access_"+this.userid +"_"+ this.siteid +" where access_time>=? and access_time<=? order by access_time";
		List<Access> list  = this.daoSupport.queryForList(sql,Access.class,start,end);
		
		if(list!=null && !list.isEmpty()){
			//写入报表，行从第2行开始
			int i=1;
			for(Access access :list){
				excelUtil.writeStringToCell(i, 0, access.getIp()); //ip
				excelUtil.writeStringToCell(i, 1, access.getArea()); //地区
				excelUtil.writeStringToCell(i, 2, access.getPage()); //页面名称
				excelUtil.writeStringToCell(i, 3,""+ access.getStay_time()); //停留时间
				excelUtil.writeStringToCell(i, 4, DateUtil.toString(new Date((long)access.getAccess_time()*1000), "yyyy-MM-dd hh:mm:ss")); //访问日期
				excelUtil.writeStringToCell(i, 5, ""+access.getPoint()); //消耗积分
				i++;
			}
			String target= EopSetting.IMG_SERVER_PATH;
			//saas 版导出目录用户上下文目录access文件夹
			if("2".equals( EopSetting.RUNMODE )){
				target =target+"/user/"+userid+"/"+siteid+"/access";
			}else{
				target =target+"/access";
			}
			File file  = new File(target);
			if(!file.exists())file.mkdirs();
			
			excelUtil.writeToFile(target+"/access"+lastMonth[0].replaceAll("-01", "")+".xls");
			//删除导出的数据
			this.daoSupport.execute("delete  from es_access_"+this.userid +"_"+ this.siteid +" where access_time>=? and access_time<=?", start,end);
		}
	}

	public static void main(String[] args){
		String date ="2010-06-03";
		System.out.println( DateUtil.toDate(date, "yyyy-MM-dd") .getTime() /1000);
	}
	
	

	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

	
}
