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
package com.enation.eop.sdk.webapp.taglib;

import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;

import com.enation.eop.sdk.webapp.taglib.BaseTaglibSupport;

public abstract class ListTaglibSupport extends BaseTaglibSupport {

	private boolean isFirst = true;

	protected String item;

	protected IListTaglibParam param;

	protected IListTaglibProvider tagProvider;

	private Iterator listIterator;

	private boolean hasNext = false;

	private int index;

	protected String postStart(){
		
		return "";
	}
	
	protected String postEnd(){
		
		return "";
	}
	
	protected String postStartOnce(){
		return "";
	}
	
	protected String postEndOnce() {
		return "";
	}
	
	public int doStartTag() throws JspException {
		
		this.print(this.postStartOnce());
		
		init();

		List list = tagProvider.getData(this.param, this.pageContext);
		listIterator = list.iterator();

		if (listIterator.hasNext()) {
			hasNext = true;
			this.setSope();
		}
		
		if (this.hasNext) {
			this.print(this.postStart());
			return EVAL_BODY_INCLUDE;
		} else {
			this.print(this.postEndOnce());
			return SKIP_BODY;
		}
	}

	public void init() {
		index = 0;
		hasNext = false;

		// 加载标签数据提供者
		loadProvider();

	}

	protected abstract void loadProvider();

	protected void setSope() {
		Object row = listIterator.next();
		pageContext.setAttribute("index", index);
		pageContext.setAttribute(item, row);
		pageContext.getRequest().setAttribute(item, row);
		index++;
	}

	public int doAfterBody() {
		
		this.print(this.postEnd()) ;
	
		if (listIterator.hasNext()) {
			this.print(this.postStart());
			this.setSope();
			return this.EVAL_BODY_AGAIN;
		} else {
			pageContext.removeAttribute(item);
			this.print(this.postEndOnce());
			return this.EVAL_PAGE;
		}
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
