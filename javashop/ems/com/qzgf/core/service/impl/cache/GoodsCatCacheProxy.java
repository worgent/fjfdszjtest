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
package com.qzgf.core.service.impl.cache;

import java.util.List;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.cache.AbstractCacheProxy;
import com.qzgf.core.model.Cat;
import com.qzgf.core.service.IGoodsCatManager;


/**
 * 商品分类缓存代理
 * @author kingapex
 * 2010-5-25上午10:52:51
 */
public class GoodsCatCacheProxy extends AbstractCacheProxy<List<Cat> > implements
		IGoodsCatManager {

	private IGoodsCatManager emsgoodsCatManager;
	
	private static final String CACHE_KEY= "ems_goods_cat" ;
	public GoodsCatCacheProxy(IGoodsCatManager emsgoodsCatManager) {
		super(CACHE_KEY);
		this.emsgoodsCatManager = emsgoodsCatManager;
	}
	
	
	private void cleanCache(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		this.cache.remove(CACHE_KEY+"_"+site.getUserid() +"_"+ site.getId()) ;
	}
	
	

	
	public int delete(int catId) {
		int r  =this.emsgoodsCatManager.delete(catId);
		if(r == 0){
			this.cleanCache();
		}
		return r;
	}

	
	public Cat getById(int catId) {
		return emsgoodsCatManager.getById(catId);
	}

	
	public List<Cat> listAllChildren(Integer catId) {
		EopSite site  = EopContext.getContext().getCurrentSite();
		List<Cat> catList  = this.cache.get(CACHE_KEY+"_"+site.getUserid() +"_"+ site.getId());
		if(catList == null){
			catList  = this.emsgoodsCatManager.listAllChildren(catId);
			this.cache.put(CACHE_KEY+"_"+site.getUserid() +"_"+ site.getId(), catList);
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load goods cat from database");
			}			
		} else{
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load goods cat from cache");
			}
		}
		return catList;
	}

	
	public List<Cat> listChildren(Integer catId) {
		return this.emsgoodsCatManager.listChildren(catId);
	}

	
	public void saveAdd(Cat cat) {
		this.emsgoodsCatManager.saveAdd(cat);
		this.cleanCache();
	}

	
	public void saveSort(int[] catIds, int[] catSorts) {
		this.emsgoodsCatManager.saveSort(catIds, catSorts);
		this.cleanCache();
	}

	
	public void update(Cat cat) {
		this.emsgoodsCatManager.update(cat);
		this.cleanCache();
	}


	public boolean checkname(String name,Integer catid) {
		return this.emsgoodsCatManager.checkname(name,catid);
	}

}
