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

import com.enation.app.saler.service.impl.SqlExportService;
import com.enation.eop.EopSetting;
import com.enation.eop.sdk.App;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.ISqlFileExecutor;

/**
 * 网店应用
 * @author kingapex
 * 2010-1-23下午06:21:07
 */
public class ShopApp implements App {
	
    private IDaoSupport daoSupport;
	private IDBRouter baseDBRouter;
	private ISqlFileExecutor sqlFileExecutor;
	private SqlExportService sqlExportService;
	private static List<String > tables;
	static{
		tables= new ArrayList<String>();
		//商品表
		tables.add("goods");
		tables.add("goods_spec");
		tables.add("product");//lzf add
		
		//商品类别表
		tables.add("goods_cat");
		
		//创建品牌表
		tables.add("brand");
		
		//创建类型相关表
		tables.add("goods_type");
		tables.add("type_brand"); //类型品牌关联表
		
		//赠品,lzf add
		tables.add("freeoffer");
		tables.add("freeoffer_category");
		tables.add("seo");

		tables.add("settings");
		
		
		//商品标签
		tables.add("tags");
		tables.add("tag_rel");
		
		//会员标签
		tables.add("member");
		tables.add("member_lv");
		
		//配送方式标签
		tables.add("dly_type");
		tables.add("dly_area");
		tables.add("dly_type_area");
		
		//物流公司
		tables.add("logi_company");
		
		//商品评论
		tables.add("comments");
		tables.add("adv");
		tables.add("adcolumn");
		
		//规格相关表
		tables.add("specification");
		tables.add("spec_values");
		tables.add("goods_spec");
		
		//订单相关
		tables.add("cart");
		tables.add("order");
		tables.add("order_items");
		tables.add("order_log");
		
		tables.add("delivery");
		tables.add("delivery_item");
		tables.add("payment_cfg");
		tables.add("payment_logs");
		tables.add("regions");
		tables.add("member_address");
		tables.add("message");
		tables.add("order_gift");
		//营销推广相关
		tables.add("gnotify");
		tables.add("point_history");
		tables.add("coupons");
		tables.add("promotion");
		tables.add("member_coupon");
		tables.add("pmt_member_lv");
		tables.add("pmt_goods");
		tables.add("favorite");
		tables.add("advance_logs");
		tables.add("promotion_activity");
		tables.add("goods_complex");
		tables.add("goods_adjunct");
		tables.add("article");
		tables.add("article_cat");
		tables.add("package_product");
		tables.add("friends_link");
		tables.add("dly_center");
		tables.add("print_tmpl");
		tables.add("site_menu");
		tables.add("order_pmt");
	}
	
	public void install() {
		 
		// List list  = daoSupport.queryForList("SHOW   TABLES   LIKE   'js_goods_"+userid+"'");
		// if(list.size()==0 || list.isEmpty())
		//System.out.println("runmode " +EopSetting.RUNMODE );
		if("2".equals(EopSetting.RUNMODE)){ //只有saas版安 装数据库
			this.createDatabase();
		}
	}
	
	
	public String dumpSql(){
		return  this.sqlExportService.dumpSql(tables);
	}
	
	

	/**
	 * 创建数据库
	 */
	private void createDatabase(){
		for(String tbName : tables){
			this.baseDBRouter.createTable(tbName);
		}
	}
	


	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}


	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}


	public ISqlFileExecutor getSqlFileExecutor() {
		return sqlFileExecutor;
	}


	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}

 
	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}


	public SqlExportService getSqlExportService() {
		return sqlExportService;
	}


	public void setSqlExportService(SqlExportService sqlExportService) {
		this.sqlExportService = sqlExportService;
	}
	
 
}
