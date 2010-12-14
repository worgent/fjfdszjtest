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
package com.enation.cms.plugin;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.eop.EopSetting;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 图片字段插件
 * @author kingapex
 * 2010-7-6下午05:40:29
 */
public class ImageFieldPlugin extends AbstractFieldPlugin {

	@Override
	public int getHaveSelectValue() {
		
		return 0;
	}

 
	/**
	 * 覆盖默认的字段值显示，将本地存储的图片路径转换为静态资源服务器路径
	 */
	@Override
	public Object onShow(DataField field, Object value) {
		if(value!=null){
			value  =UploadUtil.replacePath( value.toString());
		}
		
		return  value;
	}


	public String onDisplay(DataField field, Object value) {
		try{
			
			Map data = new HashMap();
			data.put("fieldname", field.getEnglish_name());
			if(value!=null){
				value  =UploadUtil.replacePath( value.toString());
			}
			data.put("value", value);
			data.put("ctx", ThreadContextHolder.getHttpRequest().getContextPath()); 
			data.put("ext", EopSetting.EXTENSION);
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());	
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
			
		 
			cfg.setClassForTemplateLoading(this.getClass(), "");
			Template temp = cfg.getTemplate("ImageFieldPlugin.html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			
			out.flush();
			String html = stream.toString();		
			
			return html;
		}
		catch(Exception e){
		 
			return "挂件解析出错"+e.getMessage();
		}
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "image";
	}

	
	public String getName() {
		
		return "图片";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

}
