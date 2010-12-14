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
package com.enation.eop.sdk.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.widget.nav.Nav;

/**
 * 基于freemarker的挂件基类
 * 
 * @author kingapex 2010-1-29上午10:08:46
 */
abstract public class AbstractWidget extends BaseSupport implements IWidget {

	
	//是否要解析html并显示
	protected boolean showHtml=true;
	protected FreeMarkerPaser freeMarkerPaser;
	private Map<String,String > urls;
	private String customPage; //自定义挂件页面,以当前模板为上下文
	private String folder; //自定义挂件页面所在文件夹，不指定为当前模板
	
	/**
	 * 完成freemarker的模板处理<br/>
	 * 模板路径是子类挂件所在包<br/>
	 * 在解析模板之前会调用子类的 {@link #execute(Map)}方法来设置挂件模板中的变量
	 */
	
	public String process(Map<String, String> params) {
		
		freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(getClass());
		freeMarkerPaser.setPageFolder(null);
		freeMarkerPaser.setPageName(null);
		this.customPage= params.get("custom_page");
		this.folder= params.get("folder");
		if(!StringUtil.isEmpty(customPage) ){
			this.freeMarkerPaser.setPageName(customPage);
			EopSite site  = EopContext.getContext().getCurrentSite();
			String contextPath  = EopContext.getContext().getContextPath();
			if(StringUtil.isEmpty(this.folder)){
				this.freeMarkerPaser.setPageFolder(contextPath+"/themes/"+site.getThemepath());
			}else{
				this.freeMarkerPaser.setPageFolder(contextPath+"/themes/"+site.getThemepath()+"/"+folder);
			}
		}
		
		showHtml=true;
		execute(params);
		if(showHtml){
			 
			return freeMarkerPaser.proessPageContent();
		}
		else return "";
	}

 
	
	
	public String setting(Map<String, String> params) {
		freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(getClass());
		config(params);
 
		if(showHtml)
			return freeMarkerPaser.proessPageContent();
		
		else return "";
	}
	
	
	
	/**
	 * 子类需要实现在挂件处理方法<br/>
	 * 一般子类在此方法中处理挂件的业务逻辑，设置页面变量。
	 * 
	 * @param params
	 *            挂件参数
	 * @return
	 */
	abstract protected void execute(Map<String, String> params);

	
	
	
	/**
	 * 挂件配置处理方法
	 * 
	 * @param params
	 *            挂件参数
	 */
	abstract protected  void config(Map<String, String> params); 
	
	
	
	

	/**
	 * 设置挂件模板的变量
	 * 
	 * @param key
	 * @param value
	 */
	protected void putData(String key, Object value) {
		this.freeMarkerPaser.putData(key, value);
	}

	protected Object getData(String key){
		return this.freeMarkerPaser.getData(key);
	}
	/**
	 * 设置模板路径前缀
	 * 
	 * @param path
	 */
	protected void setPathPrefix(String path) {
		this.freeMarkerPaser.setPathPrefix(path);
	}

	/**
	 * 设置模板文件的名称
	 * 如果用户强制指定了挂件页面文件名，则使自定义页面
	 * 
	 * @param pageName
	 */
	public void setPageName(String pageName) {
		if(this.customPage==null)
			this.freeMarkerPaser.setPageName(pageName);
		 
	}

	/**
	 * 设置模板页面扩展名
	 * 
	 * @param pageExt
	 */
	public void setPageExt(String pageExt) {
		this.freeMarkerPaser.setPageExt(pageExt);
	}

	public void setPageFolder(String pageFolder){
		this.freeMarkerPaser.setPageFolder(pageFolder);
	}
	
	/**
	 * 添加导航项
	 * @param nav
	 */
	protected void putNav(Nav nav){
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		List<Nav> navList  =(List<Nav>)request.getAttribute("site_nav_list");
		navList=navList == null?new ArrayList<Nav>():navList;
		navList.add(nav);
		request.setAttribute("site_nav_list", navList);
	}

	
	/**
	 * 设置操作后的提示信息
	 * @param msg 要设置的信息
	 */
	protected   void setMsg(String msg){
		this.putData("msg", msg);
	}
	
	
	/**
	 * 设置操作后显示页中的下一步可操作url
	 * @param text url的文字
	 * @param url 对应的连接
	 */
	protected void putUrl(String text,String url){
		if(urls==null) 
			urls= new HashMap<String, String>();
		
		urls.put(text, url);
		this.putData("urls",urls);
		this.putData("jumpurl", url);
	}
	
	/**
	 * 显示失败信息-返回上一步操作
	 * @param msg
	 */
	protected void showError(String msg){
		this.setPageFolder("/shop/common/");
		this.setPageName("error");
		this.setMsg(msg);
		 
	}
	
	/**
	 * 显示错误信息--提供跳转连接
	 * @param msg
	 * @param urlText
	 * @param url
	 */
	protected void showError(String msg,String urlText,String url){
		this.setPageFolder("/shop/common/");
		this.setPageName("error");
		this.setMsg(msg);
		if(urlText!=null&&  url!=null)
			this.putUrl(urlText, url);
	}

	/**
	 * 显示成功信息并返回上一步,不提供跳转连接
	 * @param msg
	 */
	protected void showSuccess(String msg){
		this.setPageFolder("/shop/common/");
		this.setPageName("success");
		this.setMsg(msg);
	 
	}
	
	
	/**
	 * 显示成功提示信息
	 * @param msg
	 * @param urlText
	 * @param url
	 */
	protected void showSuccess(String msg,String urlText,String url){
		this.setPageFolder("/shop/common/");
		this.setPageName("success");
		this.setMsg(msg);
		if(urlText!=null&&  url!=null)
		this.putUrl(urlText, url);
	}
	
	
}
