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
package com.enation.cms.core.service.impl.cache;

import java.util.List;

import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.cache.AbstractCacheProxy;

/**
 * 文章分类缓存代理
 * @author kingapex
 * 2010-7-5上午09:46:44
 */
public class DataCatCacheProxy extends AbstractCacheProxy<List<DataCat>> implements
		IDataCatManager { 
	
	private static final String cacheName ="cms_data_cat";
	private IDataCatManager articleCatManager;
	public DataCatCacheProxy(IDataCatManager articleCatManager) {
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
	
	public void add(DataCat cat) {
		this.articleCatManager.add(cat);
		this.cleanCache();
	}

	
	public int delete(Integer catid) {
		int r = this.delete(catid);
		if(r==0){
			this.cleanCache();
		}
		return r;
	}

	
	public void edit(DataCat cat) {
		this.articleCatManager.edit(cat);
		this.cleanCache();
	}

	
	public DataCat get(Integer catid) {
		
		return this.articleCatManager.get(catid);
	}

	
	public List<DataCat> listAllChildren(Integer parentid) {
		List<DataCat> catList  = this.cache.get(this.getKey());
		if(catList== null ){
			catList  = this.articleCatManager.listAllChildren(parentid);
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

	
	public void saveSort(int[] catIds, int[] catSorts) {
		this.articleCatManager.saveSort(catIds, catSorts);
		this.cleanCache();
	}

}
