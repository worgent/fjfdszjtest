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
package com.enation.javashop.widget.goods.list;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.enation.app.setting.service.ISettingService;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.model.Tag;
import com.enation.javashop.core.service.ITagManager;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 商品列表挂件   
 * @author kingapex
 *
 */
public class GoodsList extends AbstractWidget {
	
	private GoodsDataProvider goodsDataProvider;
	private ITagManager tagManager;
	
	private ISettingService settingService ;
	
	
	protected void  execute(Map<String, String> params) {
		String  widgetid  = "widget_"+params.get("id");
		this.putData("widgetid", widgetid);
		String settingJson = params.get("setting");
		
		Settings  setting = (Settings)JSONObject.toBean( JSONObject.fromObject(settingJson),Settings.class );
		this.putData("setting", null);
		if(setting!=null){
			//处理商品图片大小
			if(setting.getThumbnail_pic_width()== null){
				String defaultWidth =this.settingService.getSetting("photo", "thumbnail_pic_width");
				defaultWidth = defaultWidth== null?"100":defaultWidth;
				setting.setThumbnail_pic_width(defaultWidth+"px");
			}
		
			if(setting.getThumbnail_pic_height()== null){
				String defaultHeight =this.settingService.getSetting("photo", "thumbnail_pic_height");
				defaultHeight = defaultHeight== null?"100":defaultHeight;
				setting.setThumbnail_pic_height(defaultHeight+"px");
			}
			
			//处理列宽
			setting.setColumnwidth( Integer.valueOf(99/Integer.valueOf(setting.getColumnnum())) );
		 
			this.putData("setting", setting);
			
			String distype = setting.getType();
			
			if(distype==null ||"default".equals(distype)){
				String termJson = params.get("term");
				 this.putData("goodsList", null);
				Term term =(Term)JSONObject.toBean( JSONObject.fromObject(termJson),Term.class );
				if(term!=null){
					 List goodsList =goodsDataProvider.list(term, setting.getGoodsNum());
					 this.putData("goodsList", goodsList);
				}

			}
			
			if(distype==null ||"morediv".equals(distype)){
				String tabsjson=  params.get("tabs");
				JSONArray configArray= JSONArray.fromObject(tabsjson);
				Collection configList  =JSONArray.toCollection(configArray,Tab.class);
				Iterator<Tab> tabIterator= configList.iterator();
				int i=0;
				while(tabIterator.hasNext()){
					Tab tab  =	tabIterator.next();
					if(i==0)tab.setSelected(true);
					else tab.setSelected(false);
					
					tab.setId( widgetid+ "_"+ i);
					
					List goodsList  = this.goodsDataProvider.list(tab.getTerm() ,setting.getGoodsNum() );
					tab.setGoodsList(goodsList);
					i++;
				}
				this.putData("tabList", configList);
				this.setPageName("MoreDivList");
			}
			
		}
		
//		 
//		 
//		 if("morediv".equals(params.get("distype"))){
//			String config_str=  params.get("conifg");
//			JSONArray configArray= JSONArray.fromObject(config_str);
//			Collection configList  =JSONArray.toCollection(configArray,Map.class);
//			this.putData("tabList", configList);
//			this.setPageName("MoreDivList");
//		 }
		
	}
	
 
	
	
	
	protected void config(Map<String, String> params) {
		List<Tag> tagList =tagManager.list();
		this.putData("tagList", tagList);
	}
	
	
	public GoodsDataProvider getGoodsDataProvider() {
		return goodsDataProvider;
	}





	public void setGoodsDataProvider(GoodsDataProvider goodsDataProvider) {
		this.goodsDataProvider = goodsDataProvider;
	}





	public void test(){
		 Configuration cfg = new Configuration();
		 cfg.setClassForTemplateLoading(getClass()  , "");
		 cfg.setObjectWrapper(new DefaultObjectWrapper());  
 		 Map<String, Object> goods = new HashMap<String, Object>();
//		 goods.put("name", "kingapex");
		 List list= new ArrayList();
		 list.add("1");
		 list.add("2");
		 list.add("3");
		 goods.put("nlist", list);
		 
		 try {
			Template temp = cfg.getTemplate("goodslist.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(goods, out);
			out.flush();  
			String name =this.getClass().getName();
			name = name.substring(name.lastIndexOf('.')+1);
			System.out.println( name );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

	public static void main(String[] args){
//		GoodsList goodsList = new GoodsList();
//		goodsList.process(null);
		
		
		 
		String config_str=  "[{ title:'类别1',term:{tag_id:1}},{ title:'类别2',term:{tag_id:1}}]";
		JSONArray array= JSONArray.fromObject(config_str);
		Collection list  =JSONArray.toCollection(array,Map.class);
		
		Iterator<Map> iterator = list.iterator();
		while(iterator.hasNext()){
			Map<String,String> map = iterator.next();
			System.out.println(map.get("title"));			
		}
		
	}




	public ISettingService getSettingService() {
		return settingService;
	}




	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}




	public ITagManager getTagManager() {
		return tagManager;
	}
 
	
	
}
