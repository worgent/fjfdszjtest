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

import java.util.List;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.PrintTmpl;
import com.enation.javashop.core.service.IPrintTmplManager;

public class PrintTmplManager extends BaseSupport<PrintTmpl> implements
		IPrintTmplManager {

	
	public void add(PrintTmpl printTmpl) {
		this.baseDaoSupport.insert("print_tmpl", printTmpl);

	}

	
	public void clean(Integer[] id) {
		if(id== null  || id.length==0  ) return ;
		String ids = StringUtil.arrayToString(id, ",");
		this.baseDaoSupport.execute("delete from print_tmpl where prt_tmpl_id in (" + ids + ")");

	}

	
	public void delete(Integer[] id) {
		if(id== null  || id.length==0  ) return ;
		String ids = StringUtil.arrayToString(id, ",");
		this.baseDaoSupport.execute("update print_tmpl set disabled = 'true' where prt_tmpl_id in (" + ids + ")");

	}

	
	public void edit(PrintTmpl printTmpl) {
		this.baseDaoSupport.update("print_tmpl", printTmpl, "prt_tmpl_id = " + printTmpl.getPrt_tmpl_id());
	}

	
	public List list() {
		return this.baseDaoSupport.queryForList("select * from print_tmpl where disabled = 'false'", PrintTmpl.class);
	}

	
	public void revert(Integer[] id) {
		if(id== null  || id.length==0  ) return ;
		String ids = StringUtil.arrayToString(id, ",");
		this.baseDaoSupport.execute("update print_tmpl set disabled = 'false' where prt_tmpl_id in (" + ids + ")");

	}

	
	public List trash() {
		return this.baseDaoSupport.queryForList("select * from print_tmpl where disabled = 'true'", PrintTmpl.class);
	}

	
	public PrintTmpl get(int prt_tmpl_id) {
		return this.baseDaoSupport.queryForObject("select * from print_tmpl where prt_tmpl_id = ?", PrintTmpl.class, prt_tmpl_id);
	}

	
	public List listCanUse() {
		return this.baseDaoSupport.queryForList("select * from print_tmpl where disabled = 'false' and shortcut = 'true'", PrintTmpl.class);
	}

}
