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
package com.enation.eop.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemarker工具
 * @author kingapex
 * 2010-2-8下午04:23:18
 */
public class FreeMarkerUtil {
	private  FreeMarkerUtil(){}
	
	
	/**
	 * 获取servlet上下文件的Configuration
	 * @param pageFolder
	 * @return
	 */
	public static  Configuration getServletCfg(String pageFolder){
		
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(ThreadContextHolder.getHttpRequest().getSession().getServletContext(), pageFolder);
		cfg.setObjectWrapper(new DefaultObjectWrapper());		
		return cfg;
	}
	
	public static Configuration getFolderCfg(String pageFolder) throws IOException{
		
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(pageFolder));
		cfg.setObjectWrapper(new DefaultObjectWrapper());	
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(java.util.Locale.CHINA);
		cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
		return cfg;
		
	}
	

	public static void test(){
		Configuration cfg;
		try {
			cfg = FreeMarkerUtil.getFolderCfg("D:/workspace/eopnew/eop/WebContent/WEB-INF/classes/com/enation/javashop/core/widget/goodscat");
			Template temp = cfg.getTemplate("GoodsCat.html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream,"UTF-8");
			temp.process(new HashMap(), out);
			
			out.flush();
			String html = stream.toString();
			System.out.println("+++++++++"+html);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) throws IOException, TemplateException{
		Configuration cfg = FreeMarkerUtil.getFolderCfg("D:/workspace/eopnew/eop/WebContent/WEB-INF/classes/com/enation/javashop/core/widget/goodscat");
		Template temp = cfg.getTemplate("GoodsCat.html");
		ByteOutputStream stream = new ByteOutputStream();
		
		Writer out = new OutputStreamWriter(stream,"UTF-8");
		temp.process(new HashMap(), out);
		
		out.flush();
		String html = stream.toString();
		System.out.println(html);
	}
	
	
}
