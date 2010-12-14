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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IAccessRecorder;
import com.enation.eop.core.resource.model.Access;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.ThemeUri;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.ip.IPSeeker;

/**
 * 访问记录器
 * @author kingapex
 * 2010-7-23下午03:47:25
 */
public class AccessRecorder extends BaseSupport implements IAccessRecorder{
 
	/**
	 * 每次都从session中得到上一次的访问，如果不为空则计算停留时间，并记录上一次访问信息<br/>
	 * 存在问题：无法记录最后一次访问
	 */
	public int record(ThemeUri themeUri) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		
		Access access = new Access();
		access.setAccess_time((int) (System.currentTimeMillis()/1000));
		String ipadd=request.getRemoteAddr();
		//System.out.println(ipadd);
		if("0:0:0:0:0:0:0:1".equals(ipadd)){
			ipadd="127.0.0.1";
		}
		access.setIp(ipadd);
		access.setPage(themeUri.getPagename());
		access.setUrl(RequestUtil.getRequestUrl(request));
		access.setPoint(themeUri.getPoint());
		access.setArea(new IPSeeker() .getCountry(  access.getIp() )  );
		
		Access last_access  =(Access) ThreadContextHolder.getSessionContext().getAttribute("user_access");
		if(last_access!=null){
			int stay_time=access.getAccess_time()-last_access.getAccess_time(); 
			last_access.setStay_time(stay_time);
			int last = (int) (System.currentTimeMillis()/1000-3600); //上一个小时的秒数
			String sql ="select count(0)  from access where ip=? and url=? and access_time>=?";
			int count =this.baseDaoSupport.queryForInt(sql, last_access.getIp(),last_access.getUrl(),last);
			if(count==0){
				EopSite site  = EopContext.getContext().getCurrentSite();
				int point  =site.getPoint();
				if(point==-1 ||point>access.getPoint()){
					//更新当前站点各积分
					this.daoSupport.execute("update eop_site set point=point-? where id=?", last_access.getPoint(),site.getId());
					this.baseDaoSupport.insert("access", last_access);
					site.setPoint(site.getPoint()-last_access.getPoint());
				}else{
					return 0;
				}

			}			
			
		}
		ThreadContextHolder.getSessionContext().setAttribute("user_access", access);
		return 1;
		
	}
	
	
	public static void main(String args[]){
		int now  = (int) (System.currentTimeMillis()/1000);
		
		int stime = (int) ( now  - 3600 *24 *30  );
	    Calendar   cal=Calendar.getInstance();  
        cal.setTime(new   Date());  
        int   year=cal.get(Calendar.YEAR);   
		System.out.println(year);
		
	}


	public Page list(String starttime, String endtime, int pageNo, int pageSize) {
		
		//默认的结束时间为当前
		int now  = (int) (System.currentTimeMillis()/1000);
	
		//默认开始时间为当前时间的前30天
		int stime = (int) ( now  - 3600 *24 *30  );
		
		//用户输入了开始时间，则以输入的时间为准
		if(starttime!=null){
			stime  = (int) ( DateUtil.toDate(starttime, "yyyy-MM-dd").getTime()/1000);
		}
		
		//用户输入了结束时间，则以输入的时间为准
		if(endtime!=null){
			now  = (int) ( DateUtil.toDate(endtime, "yyyy-MM-dd").getTime()/1000);
		}
		
		String sql ="select * from access  where  access_time>=? and access_time<=? order by access_time asc";
		return this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, stime,now);
	}


	public void export() {
		 
		//读取所有站点信息
		String sql  ="select * from eop_site ";
		List<Map> list  = this.daoSupport.queryForList(sql);
		
		//为每个用户开启一个线程导出上个月的流量数据
		for(Map map :list){
			AccessExporter accessExporter =  SpringContextHolder.getBean("accessExporter");
			accessExporter.setContext(Integer.valueOf(map.get("userid").toString()), Integer.valueOf( map.get("id").toString()));
			Thread thread = new Thread (accessExporter);
			thread.start();
		}
		
		
	}

 
 
	
}
