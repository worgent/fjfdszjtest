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
package com.enation.app.base.widget;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.database.IDBRouter;

public class SqlListWidget extends AbstractWidget {
	private IDBRouter baseDBRouter;
	@Override
	protected void config(Map<String, String> params) {
		
	}

	@Override
	protected void execute(Map<String, String> params) {
		String sql  =params.get("sql");
		sql = filterSql(sql);
		List list = this.daoSupport.queryForList(sql);
		this.putData("dataList", list);
	}

	private String filterSql(String sql ){
		 
		String pattern = "#(.*?)#";
 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(sql);
		while(m.find()) {
				String tb  = m.group(0);
				 
				String newTb  = tb.replaceAll("#", "");
				newTb = this.baseDBRouter.getTableName(newTb);
				sql = sql.replaceAll(tb, newTb);
		}		
		return sql;
	}
	
	public static void main(String[] args){
		String sql  ="select * from  #goods# g ,#goods_cat# where cat_id=1";
		String pattern = "#(.*?)#";
 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(sql);
		while(m.find()) {
				String tb  = m.group(0);
				System.out.println(tb);
				String newTb  = tb.replaceAll("#", "");
				sql = sql.replaceAll(tb, newTb);
		}		
		System.out.println(sql);
	}

	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}
	
}
