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
package com.enation.javashop.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.database.IntegerMapper;
import com.enation.framework.util.FileUtil;
import com.enation.javashop.core.model.Regions;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 地区管理
 * @author kingapex
 * 2010-7-18下午08:12:03
 */
public class RegionsManager extends BaseSupport<Regions> implements IRegionsManager {

	private ISqlFileExecutor sqlFileExecutor;
	public List listCity(int province_id) {
		List list = this.baseDaoSupport.queryForList("select * from regions where region_grade = 2 and p_region_id = ?", Regions.class, province_id);
		list = list == null ? new ArrayList() : list;
		return list;
	}

	
	public List listProvince() {
		// TODO Auto-generated method stub
		List list = this.baseDaoSupport.queryForList("select * from regions where region_grade = 1", Regions.class);
		list = list == null ? new ArrayList() : list;
		return list;
	}

	
	public List listRegion(int city_id) {
		List list = this.baseDaoSupport.queryForList("select * from regions where region_grade = 3 and p_region_id = ?", Regions.class, city_id);
		list = list == null ? new ArrayList() : list;
		return list;
	}

	
	public List listChildren(Integer regionid) {
		StringBuffer sql  = new StringBuffer();
		sql.append("select c.region_id,c.local_name,c.region_grade,c.p_region_id,count(s.region_id) as childnum  from  ");
		sql.append(this.getTableName("regions"));
		sql.append(" as c");
		
		sql.append(" left join ");
		sql.append(this.getTableName("regions"));
		sql.append(" as s");
		
		sql.append(" on  s.p_region_id = c.region_id  where c. p_region_id=?  group by c.region_id   order by region_id");
		
		List list = this.daoSupport.queryForList(sql.toString(),regionid);
		return list;
	}

	
	public List listChildren(String regionid) {
		
		if(regionid==null || regionid.equals("")) return new ArrayList();
		StringBuffer sql  = new StringBuffer();
		sql.append("select c.region_id  from  ");
		sql.append(this.getTableName("regions"));
		sql.append(" as c");
		sql.append("  where c. p_region_id in("+regionid+")    order by region_id");
		List list = this.daoSupport.queryForList(sql.toString(),new IntegerMapper());
		return list;
	}
	

	
	public String getChildrenJson(Integer regionid) {
		List list  = this.listChildren(regionid);
		JSONArray jsonArray = JSONArray.fromObject( list );  
		return jsonArray.toString();
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Regions regions) {
		this.baseDaoSupport.insert("regions", regions);
		String region_path = "";
		int region_id = this.baseDaoSupport.getLastId("regions");
		if(regions.getP_region_id() != 0){
			Regions p = get(regions.getP_region_id());
			region_path = p.getRegion_path() + region_id + ",";
		}else{
			region_path = "," + region_id + ",";
		}
		regions = get(region_id);
		regions.setRegion_path(region_path);
		update(regions);
	}

	
	public void delete(int regionId) {
		this.baseDaoSupport.execute("delete from regions where region_path like ',"+regionId+",%'");
		
	}

	
	public Regions get(int regionId) {
		return this.baseDaoSupport.queryForObject("select * from regions where region_id = ?", Regions.class, regionId);
	}

	
	public void update(Regions regions) {
		this.baseDaoSupport.update("regions", regions, "region_id="+regions.getRegion_id());
		
	}

	@Transactional(propagation = Propagation.REQUIRED) 
	public void reset() {
		String sql  ="truncate table "+ this.getTableName("regions") ;
		this.daoSupport.execute(sql);
		String  content = FileUtil.readFile("com/enation/javashop/city.sql");
		if("2".equals(EopSetting.RUNMODE) ){
			EopSite site  = EopContext.getContext().getCurrentSite();
			content = content.replaceAll("<userid>",String.valueOf( site.getUserid() ));
			content = content.replaceAll("<siteid>",String.valueOf( site.getId()));
		}else{
			content = content.replaceAll("_<userid>","");
			content = content.replaceAll("_<siteid>","");		
	 
		}		
		sqlFileExecutor.execute(content);
	}


	public ISqlFileExecutor getSqlFileExecutor() {
		return sqlFileExecutor;
	}


	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}

	
}
