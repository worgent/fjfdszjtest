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
package com.enation.cms.widget;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.ArticlePluginBundle;
import com.enation.cms.core.plugin.IFieldDispalyEvent;
import com.enation.cms.core.service.IDataFieldManager;
import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.eop.utils.FreeMarkerUtil;
import com.enation.framework.plugin.IPlugin;
import com.enation.framework.util.StringUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 搜索表单挂件
 * @author kingapex
 * 2010-7-15下午09:55:05
 */
public class DataSearchFormWidget extends AbstractWidget {

	private IDataFieldManager dataFieldManager;
	private ArticlePluginBundle articlePluginBundle;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void execute(Map<String, String> params) {
		
		String page  = params.get("formpage");
		String folder  = params.get("folder");
		Integer modelid = Integer.valueOf(params.get("modelid"));
		
		String html =this.getFormHtml(modelid, page, folder);
		this.putData("html", html);
		
	}

	private String getFormHtml(Integer modelid,String page,String folder){
		Map data  = new HashMap();
		data.put("modelid", modelid);
		List<DataField> fieldList  = dataFieldManager.list(modelid);
		for(DataField field: fieldList){
			IPlugin plugin = articlePluginBundle.findPlugin(field.getShow_form());
			if(plugin!=null){
				if(plugin instanceof IFieldDispalyEvent){
					String inpuhtml =((IFieldDispalyEvent)plugin).onDisplay(field, null);
					data.put(field.getEnglish_name()+"_input", inpuhtml);
				}
			}
		}
		
		
		EopSite site  = EopContext.getContext().getCurrentSite();
		String contextPath  = EopContext.getContext().getContextPath();
		if(StringUtil.isEmpty(folder)){
			folder =(contextPath+"/themes/"+site.getThemepath());
		}else{
			folder = (contextPath+"/themes/"+site.getThemepath()+"/"+folder);
		}				
		try{
			Configuration cfg = FreeMarkerUtil.getFolderCfg(EopSetting.EOP_PATH +folder);
			Template temp = cfg.getTemplate(page+".html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			
			out.flush();
			String html = stream.toString();		
			
			return html;
		}
		catch(Exception e){
			this.logger.error(e.getMessage(), e);
			return "挂件解析出错"+e.getMessage();
		}
		
	}

	public IDataFieldManager getDataFieldManager() {
		return dataFieldManager;
	}

	public void setDataFieldManager(IDataFieldManager dataFieldManager) {
		this.dataFieldManager = dataFieldManager;
	}

	public ArticlePluginBundle getArticlePluginBundle() {
		return articlePluginBundle;
	}

	public void setArticlePluginBundle(ArticlePluginBundle articlePluginBundle) {
		this.articlePluginBundle = articlePluginBundle;
	}
	
	
}
