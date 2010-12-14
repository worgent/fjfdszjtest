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
package com.enation.cms.core.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.plugin.AutoRegisterPlugin;

public abstract class AbstractFieldPlugin extends AutoRegisterPlugin implements
		IDataSaveEvent, IFieldDispalyEvent,IFieldValueShowEvent {

	/**
	 * 下面两个方法废弃
	 */
	 
	public void register() {

	}

 
	public void perform(Object... params) {

	}
	
	
	/**
	 * 定义是否有选择值<br>
	 * 如下拉框或单选按钮类控件是有选择值控件
	 * @return
	 */
	public abstract int getHaveSelectValue();
	 
	
	
	/**
	 * 数据保存事件默认响应<br>
	 * 逻辑为以name为字段为字段名，值为request.getParameter(fieldname);
	 */
	public void onSave(Map article, DataField field) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		article.put(field.getEnglish_name(),request.getParameter(field.getEnglish_name()));
	}
	
 
	
	/**
	 * 数据显示默认响应事件<br>
	 * 逻辑为直接返回字段值<br>
	 * 如果为null返回空串
	 */
	public Object onShow(DataField field, Object value) {
		if(value!=null)
		return value.toString();
		else return "";
	}
	
	/**
	 * 根据字段类型，提供字段校验的html
	 * @param field 
	 * @return
	 */
	protected String  wrappValidHtml(DataField field ){
		
		StringBuffer html  = new StringBuffer();
		if(field.getIs_validate() ==1 ){
			html.append(" required=\"true\" datatype=\"" );
		}
		
		int data_type = field.getData_type();
		if(data_type==1){
			html.append("string");
		}
		if(data_type==2){
			html.append("int");
		}
		if(data_type == 3 ){
			html.append("float");
		}
		if(data_type == 4 ){
			html.append("string");
		}
		if(data_type== 5){
			html.append("date");
		}
 
		html.append("\"");
		return html.toString();
	}
	
	/**
	 * 当前版本cms只支持varchar(255) 和text两种数据类型
	 * 定义默认的数据类型，为1(varchar(255))
	 * 子类可覆写此方法返回2为text型。
	 * @return
	 */
	public int getDataType(){
		return 1;
	}
 
}
