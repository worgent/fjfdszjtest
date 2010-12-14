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
package com.enation.javashop.core.service.impl.cache;

import java.util.List;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.javashop.core.model.ArticleCat;
import com.enation.javashop.core.service.IArticleCatManager;

public class ArticleCatCacheProxy extends AbstractCacheProxy<List<ArticleCat>> implements
		IArticleCatManager {
	
	private static final String cacheName ="article_cat";
	private IArticleCatManager articleCatManager ;
	public ArticleCatCacheProxy(IArticleCatManager articleCatManager) {
		super(cacheName);
		this.articleCatManager = articleCatManager;
	}
	
	private String getKey(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		return cacheName+"_"+site.getUserid() +"_"+site.getId();
	}
	private void cleanCache(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		this.cache.remove(getKey());
	}

	
	public int delete(int catId) {
		int r = this.articleCatManager.delete(catId);
		if(r==0){
			this.cleanCache();
		}
		return r;
	}

	
	public ArticleCat getById(int catId) {
		return this.articleCatManager.getById(catId);
	}

	
	public List listChildById(Integer catId) {
		List<ArticleCat> catList  = this.cache.get(this.getKey());
		if(catList== null ){
			catList  = this.articleCatManager.listChildById(catId);
			this.cache.put(this.getKey(), catList);
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load article cat form database");
			}
		}else{
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load article cat form cache");
			}
		}
		
		return catList;
	}

	
	public List listHelp(int catId) {
		return this.articleCatManager.listHelp(catId);
	}

	
	public void saveAdd(ArticleCat cat) {
		this.articleCatManager.saveAdd(cat);
		this.cleanCache();
	}

	
	public void saveSort(int[] catIds, int[] catSorts) {
		this.articleCatManager.saveSort(catIds, catSorts);
		this.cleanCache();
	}

	
	public void update(ArticleCat cat) {
		this.articleCatManager.update(cat);
		this.cleanCache();
	}



}
